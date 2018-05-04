package com.study.socket.nioSocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import sun.applet.Main;

public class Handler {
		 private int bufferSize=1024;
		 private String localCharts="GBK";
		 public Handler(){}
		 public Handler(int bufferSize){
			 this(bufferSize,null);
		 }
		 public Handler(String localCharset){
			 this(-1,localCharset);
		 }
		 public Handler(int bufferSize,String localCharset){
			 if(bufferSize>0){
				 this.bufferSize=bufferSize;
			 }
			 if(localCharset!=null){
				 this.localCharts=localCharset;
			 }
		 }
		 public void handlerAccept(SelectionKey key)throws IOException{
			SocketChannel socketChannel =((ServerSocketChannel) key.channel()).accept();
			if(socketChannel!=null){
			socketChannel.configureBlocking(false);
			socketChannel.register(key.selector(), SelectionKey.OP_READ,ByteBuffer.allocate(bufferSize));
			}
		 }
		 public void handlerRead(SelectionKey key)throws IOException{
			 SocketChannel socktChannel =	 (SocketChannel)key.channel();
			 ByteBuffer buffer = (ByteBuffer)key.attachment();
			 buffer.clear();
			 if(socktChannel.read(buffer)==-1){
				 socktChannel.close();
			 }else{
				 buffer.flip();
				String  receivedString = Charset.forName(localCharts).newDecoder().decode(buffer).toString();
				System.out.println("服务端接收::::"+receivedString);
				
				String sendString ="recevied from client 111111";
				ByteBuffer buferr =ByteBuffer.wrap(sendString.getBytes(localCharts));
				socktChannel.write(buferr);
				socktChannel.close();
			 }
			 key.cancel();
			// key.interestOps(key.interestOps()|key.OP_READ);
		 }
		 
		public static void main(String[] args) {
			
			System.out.println(4 | 5);
			System.out.println(3 >> 2);
			System.out.println(3 >> 3);
			System.out.println(3 >> 4);
		} 
	 }

