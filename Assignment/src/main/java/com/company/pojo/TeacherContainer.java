package com.company.pojo;

import java.util.List;

/**
 * @ClassName TeacherContainer
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/4/1 00:45
 * @Version 1.0
 */
public class TeacherContainer implements Container {

    public List<Teacher> list;

    @Override
    public Iterator getIterator() {
        return new Teacher(list);
    }

}
