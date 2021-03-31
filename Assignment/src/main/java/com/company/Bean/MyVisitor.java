package com.company.Bean;

/**
 * @ClassName StudentVisitor
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/3/31 17:38
 * @Version 1.0
 */
public class MyVisitor implements Visitor {

    @Override
    public void visit(Person person) {
        person.login();
    }

}
