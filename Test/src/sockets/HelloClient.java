package sockets;

import java.io.*;
import java.net.*;
import java.util.Scanner;  

public class HelloClient
{  
	public static void main(String[] args) throws Exception
	{  
		InetAddress inet = InetAddress.getByName("localhost");
		Socket s = new Socket(inet,2000);  
		
		OutputStream o = s.getOutputStream();
		PrintWriter p = new PrintWriter(o);
		InputStream in = s.getInputStream();
		Scanner r = new Scanner(in);
		
		p.println("Thiago");
		p.flush();
		
		String inputLine = r.nextLine();
		System.out.println("Client: " + inputLine);  
	}  
}  