package javacode;

/**
 * @ClassName ThreadTest1
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/12 15:19
 * @Version 1.0
 */
public class ThreadTest1 {

    public static void main(String[] args) {

        RThread rThread = new RThread();

        Thread thread1 = new Thread(rThread);
        Thread thread2 = new Thread(rThread);

        thread1.start();
        thread2.start();

    }

}

class RThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

}
