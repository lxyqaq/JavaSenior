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
     * String 与基本数据类型、包装类之间的转换。
     * <p>
     * String --> 基本数据类型、包装类：调用包装类的静态方法：parseXxx(str)
     * 基本数据类型、包装类 --> String:调用String重载的valueOf(xxx)
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

    /**
     * String 与 char[]之间的转换
     * <p>
     * String --> char[]:调用String的toCharArray()
     * char[] --> String:调用String的构造器
     */
    @Test
    public void test2() {

        String str1 = "abc123";

        char[] charArray = str1.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            System.out.println(charArray[i]);
        }

        char[] arr = new char[]{'h', 'e', 'l', 'l', 'o'};
        String str2 = new String(arr);
        System.out.println(str2);

    }

}
