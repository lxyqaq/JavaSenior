package javacode;

/**
 * @ClassName WindowTest
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/12 15:09
 * @Version 1.0
 */

/**
 * 三个窗口买票，存在线程安全问题，需要解决
 */
public class WindowTest {
    public static void main(String[] args) {

        Window window1 = new Window();
        Window window2 = new Window();
        Window window3 = new Window();

        window1.start();
        window2.start();
        window3.start();

    }
}

class Window extends Thread {

    private static int ticket = 100;

    @Override
    public void run() {

        while (true) {

            if (ticket > 0) {
                System.out.println(getName() + ": 买票，票号为: " + ticket);
                ticket--;
            } else {
                break;
            }

        }

    }

}
