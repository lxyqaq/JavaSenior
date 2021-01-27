package lab2.q1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName WorkerThread
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/27 13:22
 * @Version 1.0
 */
public class WorkerThread extends Thread {

    Socket s;

    WorkerThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {

        try {
            InputStream in = s.getInputStream();
            Scanner scanner = new Scanner(in);
            OutputStream out = s.getOutputStream();
            PrintStream p = new PrintStream(out);

            String fileName;
            fileName = scanner.nextLine();
            System.out.println(getName() + " handling " + fileName);

            FileInputStream fin = new FileInputStream(fileName);
            Scanner scanner1 = new Scanner(fin);

            String line;
            while (scanner1.hasNextLine()) {
                line = scanner1.nextLine();
                p.println(line);
            }

            try {
                sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(getName() + " finished handling " + fileName);

            p.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
