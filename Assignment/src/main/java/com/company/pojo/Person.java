package com.company.pojo;

/**
 * @InterfaceName Visitable
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/3/31 02:07
 * @Version 1.0
 */
public interface Person {

    void accept(Visitor v);

    void login();

}
