package com.company.dao;

import com.company.pojo.Teacher;

import java.util.List;

public interface TeacherDao {

    int addTeacher(Teacher teacher);

    List<Teacher> QueryTeacher(String TeacherName);

    int UpdateTeacher(Teacher teacher);

    int DeleteTeacher(String ID);

}
