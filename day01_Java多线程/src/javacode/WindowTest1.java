package javacode;

/**
 * @ClassName WindowTest1
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/12 15:33
 * @Version 1.0
 */

/**
 * 使用Runnable接口创建线程，也存在线程安全问题
 */
public class WindowTest1 {
    public static void main(String[] args) {

        RWindowThread thread = new RWindowThread();

        Thread thread1 = new Thread(thread);
        Thread thread2 = new Thread(thread);
        Thread thread3 = new Thread(thread);

        thread1.start();
        thread2.start();
        thread3.start();

    }
}

class RWindowThread implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ": 卖票，票号为: " + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }

}
