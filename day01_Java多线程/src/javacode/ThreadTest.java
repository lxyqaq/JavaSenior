package javacode;

/**
 * @ClassName ThreadTest
 * @Description Thread
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/11 23:11
 * @Version 1.0
 */

/**
 * 多线程的创建，方式一：继承于Thread类
 * 1. 创建一个继承于Thread类的子类
 * 2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
 * 3. 创建Thread类的子类的对象
 * 4. 通过此对象调用start()
 */
public class ThreadTest {

    public static void main(String[] args) {

        MyThread myThread = new MyThread();

        myThread.start();

        MyThread myThread1 = new MyThread();

        myThread1.start();

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }

    }

}

class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

}
