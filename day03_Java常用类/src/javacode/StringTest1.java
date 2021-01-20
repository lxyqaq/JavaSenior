package javacode;

import org.junit.Test;

/**
 * @ClassName StringTest1
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/20 14:38
 * @Version 1.0
 */
public class StringTest1 {

    /**
     * 复习：
     *     String 与基本数据类型、包装类之间的转换。
     *
     *     String --> 基本数据类型、包装类：调用包装类的静态方法：parseXxx(str)
     *     基本数据类型、包装类 --> String:调用String重载的valueOf(xxx)
     */
    @Test
    public void test1() {

        String str1 = "123";
        //int num = (int) str1;

        int num = Integer.parseInt(str1);
        System.out.println(num);

        String str2 = String.valueOf(num);
        System.out.println(str2);

    }

}
