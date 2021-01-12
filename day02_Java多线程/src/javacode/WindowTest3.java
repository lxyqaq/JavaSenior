package javacode;

/**
 * @ClassName WindowTest3
 * @Description 使用同步方法解决时限Runnable接口的线程安全问题
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/12 21:06
 * @Version 1.0
 */
public class WindowTest3 {
    public static void main(String[] args) {
        Window3 window1 = new Window3();

        Thread thread1 = new Thread(window1);
        Thread thread2 = new Thread(window1);
        Thread thread3 = new Thread(window1);

        thread1.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Window3 implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    public synchronized void show() {
        if (ticket > 0) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
            ticket--;
        }
    }

}
