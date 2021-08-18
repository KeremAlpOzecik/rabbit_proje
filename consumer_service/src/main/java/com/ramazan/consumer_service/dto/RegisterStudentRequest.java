package com.ramazan.consumer_service.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = RegisterStudentRequest.class)
public class RegisterStudentRequest implements Serializable{
    private String id;
    private String name;
    private int grade;
    private String classId;

    public RegisterStudentRequest(String id, String name, int grade, String classId) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.classId = classId;
    }

    public RegisterStudentRequest() {
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "RegisterStudentRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", classId='" + classId + '\'' +
                '}';
    }
}
