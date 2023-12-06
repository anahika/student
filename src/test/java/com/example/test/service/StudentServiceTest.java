package com.example.test.service;


import com.example.test.exception.StudentNotFoundException;
import com.example.test.model.Student;
import com.example.test.repository.StudentRepository;
import com.example.test.service.impl.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Test
    public void setStudentTest(){
        Student student = new Student("anshika", "kapoor", 21);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student student1 = studentService.setStudent(student);

        assertEquals(student.getFirstName(), student1.getFirstName());
    }

    @Test
    public void updateStudentTest(){
        Student student = new Student("anshika", "kapoor", 21);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Student student1 = studentService.updateStudent(1L, student);

        assertEquals(student, student1);
    }

    @Test
    public void updateStudentTest2(){
        Student student = new Student("anshika", "kapoor", 21);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        Student student1 = studentService.updateStudent(1L, student);

        assertEquals(student, student1);
    }

    @Test
    public void deleteStudentTest(){
        Student student = new Student("anshika", "kapoor", 21);

        when(studentRepository.deleteById(any(Long.class))).thenReturn(1L);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        Long id = studentService.deleteStudent(1L);

        assertEquals(Optional.of(1L).get(), id);
    }

    @Test()
    public void deleteStudentTest1() {
        when(studentRepository.deleteById(any(Long.class))).thenReturn(1L);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> {
            studentService.deleteStudent(1L);
        });
    }

    @Test()
    public void getStudentWithNameTest() {
        List<Student> list = new ArrayList<>();

        when(studentRepository.getByFirstName(anyString())).thenReturn(list);

        assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudentWithName("anshika");
        });
    }

    @Test()
    public void getStudentWithNameTest1() {
        Student student = new Student("anshika", "kapoor", 21);
        List<Student> list = new ArrayList<>();
        list.add(student);

        when(studentRepository.getByFirstName(anyString())).thenReturn(list);

        List<Student> studentList = studentService.getStudentWithName("anshika");

        assertEquals(studentList.get(0).getFirstName(), student.getFirstName());

    }

}
