package javacode;

/**
 * @ClassName WindowTest2
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/12 17:54
 * @Version 1.0
 */

/**
 * 使用同步代码块解决继承Thread类的方式的线程安全问题
 *
 * 例子：创建三个窗口卖票，总票数为100张.使用继承Thread类的方式
 *
 * 说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器。
 */
public class WindowTest2 {
    public static void main(String[] args) {

        Window2 t1 = new Window2();
        Window2 t2 = new Window2();
        Window2 t3 = new Window2();


        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}

class Window2 extends Thread {

    private static int ticket = 100;

    private static Object object = new Object();

    @Override
    public void run() {

        while (true) {
            synchronized (object) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + ": 卖票，票号为: " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }

    }

}