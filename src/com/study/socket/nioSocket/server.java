package com.study.socket.nioSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;


public class server {
 
	public static void main(String[] args) {
		try {
			ServerSocketChannel ssc=  ServerSocketChannel.open();
			 ssc.socket().bind(new InetSocketAddress(1111));  //�󶨶˿�
			 Selector sc= Selector.open();      //����selector
			 ssc.configureBlocking(false);      //���÷�����ģʽ
			 ssc.register(sc, SelectionKey.OP_ACCEPT);  //ע��selector  Ϊ����
			 Handler handler = new Handler(1024);
			 while(true){
				 if(sc.select(3000)==0){
					 System.out.println("�ȴ�����ʱ");
					 continue;
				 }
				 System.out.println("������������");
				 //��ȡ���ͻ��˵�����  ���� selectkey  �������������channel ��  selecttor
				 Iterator<SelectionKey>  iterator= sc.selectedKeys().iterator();
				 while(iterator.hasNext()){
					 SelectionKey  key = iterator.next();
					 try {
						 if(key.isAcceptable()){
							 handler.handlerAccept(key);
						 }
						 if(key.isReadable()){
							 
							 handler.handlerRead(key);
						 }
						 	if(key.isWritable()){
							 
						 }
					} catch (Exception e) {
						key.cancel();
					}
					 
					 
					
				 }
				 iterator.remove();
			 }
			 
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
