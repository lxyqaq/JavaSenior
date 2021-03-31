package com.company.Bean;

/**
 * @ClassName MyVisitor
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/3/31 15:50
 * @Version 1.0
 */
public class MyVisitor implements Visitor {

    @Override
    public boolean visit(Person person) {
        return person.getType();
    }

}
