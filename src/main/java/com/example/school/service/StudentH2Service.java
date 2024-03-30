package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;

@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getStudent() {
        List<Student> studentlist = db.query("select * from STUDENT", new StudentRowMapper());
        ArrayList<Student> students = new ArrayList<>(studentlist);
        return students;
    }

    @Override
    public Student addStudent(Student student) {
        db.update("insert into STUDENT(studentName, gender, standard) values (?,?,?)", student.getStudentName(),
                student.getGender(), student.getStandard());
        Student savedstudent = db.queryForObject(
                "select * from STUDENT where studentName=? and gender=? and standard=?", new StudentRowMapper(),
                student.getStudentName(), student.getGender(), student.getStandard());
        return savedstudent;

    }

    @Override
    public ArrayList<Student> addMultipleStudents(ArrayList<Student> student) {
        for (Student students : student) {
            db.update("INSERT INTO STUDENT (studentName, gender, standard) VALUES (?, ?, ?)", students.getStudentName(),
                    students.getGender(), students.getStandard());
        }
        System.out.println("Successfully added " + student.size() + " students");
        ArrayList<Student> addedStudents = getStudent();
        return addedStudents;
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            Student student = db.queryForObject("select * from STUDENT where studentId=?", new StudentRowMapper(),
                    studentId);
            return student;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student updateStudent(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("update STUDENT set studentName=? where studentId=?", student.getStudentName(), studentId);
        }
        if (student.getGender() != null) {
            db.update("update STUDENT set gender=? where studentId=?", student.getGender(), studentId);
        }
        if (student.getStandard() != 0) {
            db.update("update STUDENT set standard=? where studentId=?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }

    @Override
    public void deletStudent(int studentId) {
        db.update("delete from STUDENT where studentId=?", studentId);
    }
}