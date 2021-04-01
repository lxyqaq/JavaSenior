package com.company.pojo;

import java.util.List;

/**
 * @ClassName StudentContainer
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/4/1 00:41
 * @Version 1.0
 */
public class StudentContainer implements Container {

    public List<Student> list;

    @Override
    public Iterator getIterator() {
        return new Student(list);
    }

}
