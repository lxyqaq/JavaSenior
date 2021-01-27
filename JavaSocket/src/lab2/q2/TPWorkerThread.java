package lab2.q2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName TPWorkerThread
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/27 13:51
 * @Version 1.0
 */
public class TPWorkerThread extends Thread {

    BlockingQueue<Socket> queue;

    TPWorkerThread(BlockingQueue<Socket> b) {
        this.queue = b;
    }

    @Override
    public void run() {

        try {
            while (true) {

                Socket s = queue.take();

                InputStream in = s.getInputStream();
                Scanner r = new Scanner(in);
                OutputStream o = s.getOutputStream();
                PrintStream p = new PrintStream(o);

                String fileName;
                fileName = r.nextLine();
                System.out.println(getName() + " handing " + fileName);

                FileInputStream fin = new FileInputStream(fileName);
                Scanner f = new Scanner(fin);

                String line;
                while (f.hasNextLine()) {
                    line = f.nextLine();
                    p.println(line);
                }

                sleep(5000);

                p.close();

                System.out.println(getName() + " finished handling " + fileName);
            }
        } catch (Exception e) {
            System.out.println("an exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }

}

