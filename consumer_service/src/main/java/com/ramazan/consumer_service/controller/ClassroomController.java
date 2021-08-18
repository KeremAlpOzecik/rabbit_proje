package com.ramazan.consumer_service.controller;

import com.ramazan.consumer_service.model.Classroom;
import com.ramazan.consumer_service.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

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

    @GetMapping("/getAll")
    public List<Classroom> getAllClassrooms(){
        return classroomService.findAll();
    }

    @PutMapping("/updateClassroom/{id}")
    public Classroom updateClassroom(@PathVariable String id,
                                    @RequestBody Classroom classroom){
        return classroomService.update(id, classroom);
    }

    @DeleteMapping("/deleteClassroom/{id}")
    public String deleteClassroom(@PathVariable String id){
        classroomService.deleteClassroom(id);
        return "Classroom Deleted!";
    }



}
