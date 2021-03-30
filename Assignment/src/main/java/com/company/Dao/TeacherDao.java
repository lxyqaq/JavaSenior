package com.company.Dao;

import com.company.Bean.Laboratory;
import com.company.Bean.Teacher;

import java.util.List;

public interface TeacherDao {
    int addTeacher(Teacher teacher);

    List<Teacher> QueryTeacher(String TeacherName);

    int UpdateTeacher(Teacher teacher);

    int DeleteTeacher(String ID);
}
