package com.example.test.service.impl;

import com.example.test.exception.StudentNotFoundException;
import com.example.test.model.Student;
import com.example.test.repository.StudentRepository;
import com.example.test.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student setStudent(Student s1) {
        Student s = new Student(s1.getFirstName(), s1.getLastName(), s1.getAge());
        studentRepository.save(s);
        log.debug("Student set successfully.");
        return s;
    }

    @Override
    public List<Student> getStudentWithName(String name) throws StudentNotFoundException {
            List<Student> student = studentRepository.getByFirstName(name);
            if(student.isEmpty()) {
                log.error("student not found exception.");
                throw new StudentNotFoundException("student not found.");

            }
        log.debug("student with given id is: " + student);
        return studentRepository.getByFirstName(name);

    }

}
