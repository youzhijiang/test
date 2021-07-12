package com.nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer02 {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8802);
		while(true){
			Socket socket = serverSocket.accept();
			service(socket);
		}
	}
	
	public static void service(Socket socket){
		try {
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
			printWriter.println("HTTP/1.1 200 ok");
			printWriter.println("Content-Type:text/html;charset=utf-8");
			String body = "hello,nio8802";
			printWriter.println("Content-Length:"+body.getBytes().length);
			printWriter.println();
			printWriter.write(body);
			printWriter.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
