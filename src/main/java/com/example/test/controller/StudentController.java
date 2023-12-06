package com.example.test.controller;

import com.example.test.model.Student;
import com.example.test.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentForName(@PathVariable String name) {
        log.debug("Got request to get list of students for given name: "+ name);
        List<Student> student = studentService.getStudentWithName(name);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @PostMapping("/set-student")
    public ResponseEntity<Student> setStudent(@RequestBody Student s){
        log.debug("Got request to set student: "+s.toString());
        Student student = studentService.setStudent(s);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student s){
        log.debug("Got request to update student: "+s.toString());
        Student student = studentService.updateStudent(id, s);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        log.debug("Got request to delete student with given id: "+id);
        Long studentId = studentService.deleteStudent(id);

        return new ResponseEntity<>("student deleted successfully for id: " + studentId, HttpStatus.OK);
    }


}
