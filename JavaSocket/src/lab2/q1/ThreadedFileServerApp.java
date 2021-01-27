package lab2.q1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName ThreadedFileServerApp
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/27 13:18
 * @Version 1.0
 */
public class ThreadedFileServerApp {

    public static void main(String[] args) {

        try {
            System.out.println("Server ready");

            ServerSocket ss = new ServerSocket(2001);

            while (true) {
                Socket s = ss.accept();
                Thread t = new WorkerThread(s);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
