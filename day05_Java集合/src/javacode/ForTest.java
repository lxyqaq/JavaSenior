package javacode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName ForTest
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/2/2 15:38
 * @Version 1.0
 */
public class ForTest {

    @Test
    public void test1() {

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //for(集合元素的类型 局部变量 : 集合对象)
        //内部仍然调用了迭代器。
        for (Object obj : coll) {
            System.out.println(obj);
        }

    }

    @Test
    public void test2() {

        int[] array = new int[]{1, 2, 3, 4, 5, 6};

        //for(数组元素的类型 局部变量 : 数组对象)
        for (int i : array) {
            System.out.println(i);
        }

    }

}
