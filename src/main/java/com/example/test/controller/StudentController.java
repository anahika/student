package com.example.test.controller;

import com.example.test.model.Student;
import com.example.test.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentForName(@PathVariable String name) {
        log.info("Got request to get list of students for given name: "+ name);
        List<Student> student = studentService.getStudentWithName(name);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @PostMapping("/set-student")
    public ResponseEntity<?> setStudent(@RequestBody Student s){
        log.info("Got request to set student: "+s);
        Student student = studentService.setStudent(s);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }



}
