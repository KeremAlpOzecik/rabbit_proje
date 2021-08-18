package com.ramazan.consumer_service.controller;

import com.ramazan.consumer_service.model.Classroom;
import com.ramazan.consumer_service.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/classroom")
public class ClassroomController {

    private ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService){
        this.classroomService = classroomService;
    }

    @PostMapping("/")
    public Classroom addClassroom(@RequestBody Classroom classroom){
        return classroomService.saveClassroom(classroom);
    }

    @GetMapping("/{id}")
    public Classroom getClassroom(@PathVariable String id){
        return classroomService.findClassroomById(id);
    }

}
