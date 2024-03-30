package com.example.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.school.model.Student;
import com.example.school.service.StudentH2Service;

@RestController
public class StudentController {
    @Autowired
    public StudentH2Service studentservice;

    @GetMapping("/students")
    public ArrayList<Student> getStudent() {
        return studentservice.getStudent();
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return studentservice.addStudent(student);
    }

    @PostMapping("/students/bulk")
    public ArrayList<Student> addMultipleStudents(@RequestBody ArrayList<Student> student) {
        return studentservice.addMultipleStudents(student);
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return studentservice.getStudentById(studentId);
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        return studentservice.updateStudent(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentservice.deletStudent(studentId);
    }
}
