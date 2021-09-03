package com.ramazan.producer_service.service;

import com.ramazan.producer_service.dto.Classroom;
import com.ramazan.producer_service.model.Student;
import com.ramazan.producer_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private StudentRepository studentRepository;
    RestTemplate restTemplate = new RestTemplate();
    String classRoomServiceURL = "http://localhost:8080/api/v1/classroom/";


    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(String id) {
        return findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(String id, Student studentRequest) {
        Student student = findById(id);
        student.setName(studentRequest.getName());
        student.setGrade(studentRequest.getGrade());
        return studentRepository.save(student);
    }

    private Student findById(String id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(null);
    }

    public void deleteStudentById(String id) {
        studentRepository.deleteById(id);
    }

    public void addClassToStudent(String studentId, String classId) {
        Student student = findById(studentId);
        student.addClassToAStudent(classId);
        studentRepository.save(student);
    }

    public List<Classroom> getStudentsClasses(String id) {
        Student student = findById(id);
        List<Classroom> temp = new ArrayList<>();
        for (String classId : student.getClassList()){
            Classroom classroom = restTemplate.getForObject(classRoomServiceURL + classId, Classroom.class);
            temp.add(classroom);
        }
        return temp;
    }

}