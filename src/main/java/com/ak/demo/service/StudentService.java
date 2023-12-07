package com.ak.demo.service;


import com.ak.demo.exception.StudentNotFoundException;
import com.ak.demo.model.Student;

import java.util.List;


public interface StudentService {

     Student setStudent(Student s);

     Student updateStudent(Long id, Student s);

     Long deleteStudent(Long id);

     List<Student> getStudentWithName(String name) throws StudentNotFoundException;
}
