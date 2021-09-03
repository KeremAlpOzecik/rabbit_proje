package com.ramazan.consumer_service.service;

import com.ramazan.consumer_service.dto.StudentInformation;
import com.ramazan.consumer_service.model.Classroom;
import com.ramazan.consumer_service.dto.RegisterStudentRequest;
import com.ramazan.consumer_service.repository.ClassroomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    RestTemplate restTemplate = new RestTemplate();
    String studentServiceURL = "http://localhost:8081/api/v1/student/";

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    // rabbitmq call this method
    public void addStudent(RegisterStudentRequest registerStudentRequest) {
        String studentId = registerStudentRequest.getId();
        Classroom classroom = findById(registerStudentRequest.getClassId());
        if (classroom != null){
            classroom.addStudentToList(studentId);
            classroomRepository.save(classroom);
        }
        else{
            System.out.println("Classroom is null!!");
        }
    }

    public Classroom findClassroomById(String id) {
        Optional<Classroom> classroom = classroomRepository.findById(id);
        return classroom.orElse(null);
    }

    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    public Classroom update(String id, Classroom classroom) {
        Classroom foundClassroom = findById(id);
        foundClassroom.setName(classroom.getName());
        foundClassroom.setStudentList(classroom.getStudentList());
        return classroomRepository.save(classroom);
    }

    private Classroom findById(String id) {
        Optional<Classroom> optionalClassroom = classroomRepository.findById(id);
        return optionalClassroom.orElse(null);
    }

    public void deleteClassroom(String id) {
        classroomRepository.deleteById(id);
    }

    public List<StudentInformation> getStudentInformation(String id) {
        Classroom classroom = findById(id);
        List<StudentInformation> temp = new ArrayList<>();
        for (String studentId : classroom.getStudentList()){
            StudentInformation studentInformation = restTemplate.getForObject(studentServiceURL + studentId, StudentInformation.class);
            temp.add(studentInformation);
        }
        return temp;
    }
}
