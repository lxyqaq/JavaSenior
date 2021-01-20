package sockets;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ReadFileServerApp {
    public static void main(String args[]) throws Exception {
        Socket s;
        ServerSocket server = new ServerSocket(2001);
        // Register your service on port 2001
        while (true) {
            System.out.println("Server is Ready wait for client");
            s = server.accept();

            InputStream in = s.getInputStream();
            Scanner r = new Scanner(in);
            OutputStream serverOut = s.getOutputStream();
            PrintWriter pw = new PrintWriter(serverOut);

            String fileName;
            fileName = r.nextLine();
            System.out.println("File requested " + fileName);

            FileInputStream fin = new FileInputStream(fileName);
            Scanner f = new Scanner(fin);

            while (f.hasNextLine()) {
                pw.println(f.nextLine());
            }
            pw.close();
        }
    }   //main end
}   //class end