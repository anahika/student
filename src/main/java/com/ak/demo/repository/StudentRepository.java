package com.ak.demo.repository;

import com.ak.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, String>{
     List<Student> getByFirstName(String name);

     Optional<Student> findById(Long id);

     Optional<Student> findByFirstName(String name);

     Long deleteById(Long id);

}
