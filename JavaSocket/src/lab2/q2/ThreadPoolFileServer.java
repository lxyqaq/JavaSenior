package lab2.q2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName ThreadPoolFileServer
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/27 13:49
 * @Version 1.0
 */
public class ThreadPoolFileServer {

    public static void main(String[] args) throws Exception {

        System.out.println("Server Ready");

        BlockingQueue<Socket> queue = new ArrayBlockingQueue<Socket>(10);

        ServerSocket ss = new ServerSocket(2001);

        for (int i = 0; i < 2; i++) {
            TPWorkerThread f = new TPWorkerThread(queue);
            f.start();
        }

        while (true) {
            Socket s = ss.accept();
            queue.put(s);
        }

    }

}
