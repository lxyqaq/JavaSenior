package sockets;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ReadFileClientApp {
    public static void main(String[] args) throws Exception {
        InetAddress inet = InetAddress.getLocalHost();
        Socket s = new Socket(inet, 2001);

        InputStream in = s.getInputStream();
        Scanner r = new Scanner(in);
        OutputStream o = s.getOutputStream();
        PrintWriter p = new PrintWriter(o);

        p.println("test.txt");
        p.flush();

        while (r.hasNextLine()) {
            System.out.println(r.nextLine());
        }
    }
}  