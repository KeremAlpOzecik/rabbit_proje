package com.ramazan.consumer_service.model;

import org.springframework.transaction.annotation.Propagation;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    //private String studentId;

    @ElementCollection // 1
    private List<String> studentList;
    //TODO
    //OneToMany Student
    //Create Student Model -> ManyToMany Classroom

    public Classroom(String id, String name, List<String> studentList){
        this.id = id;
        this.name = name;
        this.studentList = studentList;
    }

    public Classroom() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<String> studentList) {
        this.studentList = studentList;
    }

    public void addStudentToList(String id){
        studentList.add(id);
    }
}


