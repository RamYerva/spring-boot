package com.example.school.repository;

import java.util.*;

import com.example.school.model.Student;

public interface StudentRepository {

    ArrayList<Student> getStudent();

    Student addStudent(Student student);

    ArrayList<Student> addMultipleStudents(ArrayList<Student> student);

    Student getStudentById(int studentId);

    Student updateStudent(int studentId, Student student);

    void deletStudent(int studentId);

}