package javacode;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @ClassName ReflectionTest
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/3/3 01:43
 * @Version 1.0
 */
public class ReflectionTest {

    //反射之前
    @Test
    public void test1() {

        Person p1 = new Person("Tom", 12);

        System.out.println(p1);

    }

    //反射之后
    @Test
    public void test2() throws Exception {

        Class clazz = Person.class;

        //1.通过反射，创建Person类的对象
        Constructor cons = clazz.getConstructor(String.class, int.class);

        Object obj = cons.newInstance("Tom", 12);

        Person p2 = (Person) obj;

        System.out.println(p2);

    }

}
