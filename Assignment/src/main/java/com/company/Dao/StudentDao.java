package com.company.Dao;

import com.company.Bean.Student;
import com.company.Bean.Teacher;

import java.util.List;

public interface StudentDao {
    Student selectStudent(String ID);

    int addStudent(Student student);

    List<Student> QueryStudent(String StudentName);

    int UpdateStudent(Student student);

    int DeleteStudent(String ID);
}
