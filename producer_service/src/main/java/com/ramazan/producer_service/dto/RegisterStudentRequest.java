package com.ramazan.producer_service.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = RegisterStudentRequest.class)
public class RegisterStudentRequest implements Serializable{
    private String id;
    private String classId;

    public RegisterStudentRequest(String id, String classId) {
        this.id = id;
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

    @Override
    public String toString() {
        return "RegisterStudentRequest{" +
                "id='" + id + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }
}
