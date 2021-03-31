package com.company.Bean;

/**
 * @InterfaceName Visitor
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/3/31 02:07
 * @Version 1.0
 */
public interface Visitor {

    //student
    void visit(Student student);

    //teacher
    void visit(Teacher teacher);

    //user
    void visit(User user);

}
