package sockets;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HelloServer 
{
	public static void main(String args[]) throws Exception
	{
		Socket s;
		ServerSocket server = new ServerSocket(2000);
		// Register your service on port 5432
		while(true) {
			System.out.println("Server is Ready wait for client");
			s = server.accept();
			System.out.println("Connection is establised with the client");

			InputStream in = s.getInputStream();
			Scanner r = new Scanner(in);			
			// Get output stream associated with the socket
			OutputStream serverOut = s.getOutputStream();
			PrintWriter pw = new PrintWriter(serverOut);
			
			String inputLine;
			inputLine = r.nextLine();
			
			// Send your string!
			pw.println("Hello " + inputLine + " from Hello Server");
			pw.close();
		}
	}   //main end
}   //class end