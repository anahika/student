package com.ak.demo.controller;

import com.ak.demo.model.Student;
import com.ak.demo.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Test
    public void getStudentForNameTest(){
        Student student = new Student("anshika", "kapoor", 21);
        List<Student> list = new ArrayList<>();
        list.add(student);

        when(studentService.getStudentWithName(anyString())).thenReturn(list);

        ResponseEntity<List<Student>> response = studentController.getStudentForName("anshika");

        assertEquals(list, response.getBody());
    }

    @Test
    public void setStudentTest(){
        Student student = new Student("anshika", "kapoor", 21);

        when(studentService.setStudent(any(Student.class))).thenReturn(student);

        ResponseEntity<Student> response = studentController.setStudent(student);

        assertEquals(student, response.getBody());
    }

    @Test
    public void updateStudentTest(){
        Student student = new Student("anshika", "kapoor", 21);

        when(studentService.updateStudent(any(Long.class), any(Student.class))).thenReturn(student);

        ResponseEntity<Student> response = studentController.updateStudent(1L, student);

        assertEquals(student, response.getBody());
    }

    @Test
    public void deleteStudentTest(){

        when(studentService.deleteStudent(any(Long.class))).thenReturn(1L);

        ResponseEntity<String> response = studentController.deleteStudent(1L);

        assertTrue(response.getBody().contains("student deleted successfully"));
    }
}
