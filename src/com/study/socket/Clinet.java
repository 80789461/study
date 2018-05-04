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
			System.out.println("�ͻ��˽���::::"+br.readLine());
			
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
		new Thread(new Clinet("�ҵ�����")).start();
		new Thread(new Clinet("���ǵ�")).start();
		new Thread(new Clinet("9810")).start();
		new Thread(new Clinet("1111�ҵ�����")).start();
		new Thread(new Clinet("2222�ҵ�����")).start();
		new Thread(new Clinet("3333�ҵ�����")).start();
		new Thread(new Clinet("4444�ҵ�����")).start();
		new Thread(new Clinet("5555�ҵ�����")).start();
		new Thread(new Clinet("6666�ҵ�����")).start();
		new Thread(new Clinet("7777�ҵ�����")).start();
		
	}

	public void run() {
		
		client();
	}

}
