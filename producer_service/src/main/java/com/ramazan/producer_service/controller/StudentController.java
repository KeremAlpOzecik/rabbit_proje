package com.ramazan.producer_service.controller;

import com.ramazan.producer_service.dto.RegisterStudentRequest;
import com.ramazan.producer_service.model.Student;
import com.ramazan.producer_service.service.RabbitMQSender;
import com.ramazan.producer_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final RabbitMQSender rabbitMQSender;

    @Value("${app.message}")
    private String message;

    @Autowired
    public StudentController(StudentService studentService,
                             RabbitMQSender rabbitMQSender) {
        this.studentService = studentService;
        this.rabbitMQSender = rabbitMQSender;
    }

    //CRUD
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/createStudent")
    public Student addStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/updateStudent/{id}")
    public Student updateStudent(@PathVariable String id,
                                 @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable String id){
        studentService.deleteStudentById(id);
        return "Student Deleted";
    }

    //rabbitmq
    @PostMapping("/register/{classId}")
    public String registerStudent(@RequestBody RegisterStudentRequest registerStudentRequest) {
        System.out.println(registerStudentRequest.toString());
        rabbitMQSender.send(registerStudentRequest);
        return message;
    }
}
