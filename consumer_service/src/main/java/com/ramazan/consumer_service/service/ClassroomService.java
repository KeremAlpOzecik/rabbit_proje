package com.ramazan.consumer_service.service;

import com.ramazan.consumer_service.model.Classroom;
import com.ramazan.consumer_service.dto.RegisterStudentRequest;
import com.ramazan.consumer_service.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassroomService {

    private ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public void addStudent(RegisterStudentRequest registerStudentRequest) {
        String studentId = registerStudentRequest.getId();
        Optional<Classroom> optionalClassroom = classroomRepository.findById(registerStudentRequest.getClassId());
        Classroom classroom = null;
        if(optionalClassroom.isPresent()){
            classroom = optionalClassroom.get();
        }
        assert classroom != null;
        classroom.setStudentId(studentId);
        classroomRepository.save(classroom);
    }

    public Classroom findClassroomById(String id) {
        Optional<Classroom> classroom = classroomRepository.findById(id);
        return classroom.orElse(null);
    }
}
