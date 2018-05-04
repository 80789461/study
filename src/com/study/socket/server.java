package com.study.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//创建socket服务端
			ServerSocket serverSocket= new  ServerSocket(8080);
			Socket socket= serverSocket.accept();  //开启监听
			BufferedReader  br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String received = br.readLine();
			System.out.println("recevied from clinet :::"+received);
			PrintWriter  pw = new PrintWriter(socket.getOutputStream());
			pw.println("recevied from server::::8080back");
			pw.flush();
			pw.close();
			br.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
