package com.example.test.service;


import com.example.test.exception.StudentNotFoundException;
import com.example.test.model.Student;

import java.util.List;


public interface StudentService {

     Student setStudent(Student s);

     List<Student> getStudentWithName(String name) throws StudentNotFoundException;
}
