package com.example.test.controller;

import com.example.test.model.Student;
import com.example.test.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/name")
    public ResponseEntity<String> getStudentName(){
        log.info("Got request to get student name.");
        Optional<String> name = Optional.ofNullable(studentService.getStudentName());
        if(name.isPresent()){
            return new ResponseEntity<>(name.get(), HttpStatusCode.valueOf(200));
        }

        return new ResponseEntity<>("student not there", HttpStatusCode.valueOf(404));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Student> getStudentForID(@PathVariable String name) throws Exception {
        log.info("Got request to get student for given name: "+ name);
        Optional<Student> student = Optional.ofNullable(studentService.getStudentWithID(name));
        student.orElseThrow(() -> new RuntimeException());

        return new ResponseEntity<>(student.get(), HttpStatusCode.valueOf(200));
    }


    @PostMapping("/set-student")
    public ResponseEntity<?> setStudent(@RequestBody Student s){
        log.info("Got request to set student: "+s);
        Student student = studentService.setStudent(s);

        return new ResponseEntity<>(student, HttpStatusCode.valueOf(200));
    }



}
