package javacode1;

/**
 * @ClassName BankTest
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/13 21:26
 * @Version 1.0
 */

/**
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的
 */
public class BankTest {
    public static void main(String[] args) {

    }
}

class Bank {

    private Bank(){

    }

    private static Bank instance = null;

    public static Bank getInstance() {
        //方式一：效率稍差
//        synchronized (Bank.class) {
//            if(instance == null){
//
//                instance = new Bank();
//            }
//            return instance;
//        }
        //方式二: 效率更高
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }

}
