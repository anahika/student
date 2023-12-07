package com.ak.demo.service.impl;

import com.ak.demo.exception.StudentNotFoundException;
import com.ak.demo.repository.StudentRepository;
import com.ak.demo.service.StudentService;
import com.ak.demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService, UserDetailsService {

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
    public Student updateStudent(Long id, Student s) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            log.debug("updating the existing student.");
            s.setId(id);
            return studentRepository.save(s);
        }
        log.debug("student not there for given id, creating new user.");
        return studentRepository.save(s);
    }

    @Override
    public Long deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            studentRepository.deleteById(id);
            log.debug("student deleted successfully");
            return id;
        }
        log.error("student not found exception.");
        throw new StudentNotFoundException("student not found.");
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

    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        Student student = studentRepository.findByFirstName(firstName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with name: " + firstName));
        return StudentDetailImpl.build(student);
    }

}
