package com.study.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clinet implements Runnable {
	String s ="";
	public Clinet(String s){
		this.s=s;
	}
	
	public  void client(){
		
		try {
			Socket	socket = new Socket("127.0.0.1", 8080);
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println(s);
			pw.flush();
			BufferedReader  br =	new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("客户端接收::::"+br.readLine());
			
			pw.close();
			br.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new Clinet("我的世界")).start();
		new Thread(new Clinet("他们的")).start();
		new Thread(new Clinet("9810")).start();
		new Thread(new Clinet("1111我的世界")).start();
		new Thread(new Clinet("2222我的世界")).start();
		new Thread(new Clinet("3333我的世界")).start();
		new Thread(new Clinet("4444我的世界")).start();
		new Thread(new Clinet("5555我的世界")).start();
		new Thread(new Clinet("6666我的世界")).start();
		new Thread(new Clinet("7777我的世界")).start();
		
	}

	public void run() {
		
		client();
	}

}
