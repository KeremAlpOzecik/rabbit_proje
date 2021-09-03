package com.ramazan.producer_service.service;

import com.ramazan.producer_service.model.Student;
import com.ramazan.producer_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private StudentRepository studentRepository;

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
}