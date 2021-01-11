package exercise;

/**
 * @ClassName ThreadDemo
 * @Description 创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/11 23:35
 * @Version 1.0
 */
public class ThreadDemo {

    public static void main(String[] args) {

        //MyThread1 myThread1 = new MyThread1();
        //MyThread2 myThread2 = new MyThread2();

        //myThread1.start();
        //myThread2.start();

        //创建Thread类的匿名子类的方式
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    }
                }
            }
        }.start();

    }

}

class MyThread1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

}

class MyThread2 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

}
