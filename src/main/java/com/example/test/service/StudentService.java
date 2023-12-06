package com.example.test.service;


import com.example.test.exception.StudentNotFoundException;
import com.example.test.model.Student;


public interface StudentService {
     String getStudentName();

     Student setStudent(Student s);

     Student getStudentWithID(String name) throws StudentNotFoundException;
}
