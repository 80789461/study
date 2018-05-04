package com.study.socket.nioSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
	public static void main(String[] args) {
		try {
			ServerSocketChannel  serverSocketChannel = 	ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 1111));
			Selector selector = Selector.open();
		   serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		   while(true) {
			   if(selector.select(3000)==0) {
				   System.out.println("超时");
			   }else {
				   Set<SelectionKey> set=  selector.selectedKeys();
				   Iterator<SelectionKey> iterator=  set.iterator();
				   while (iterator.hasNext()) {
					   SelectionKey skey =iterator.next();
					 if(skey.isAcceptable()) {
						 SocketChannel socketChannel=	( (ServerSocketChannel)skey.channel()).accept();
						 socketChannel.configureBlocking(false);
						 socketChannel.register(skey.selector(), SelectionKey.OP_READ,ByteBuffer.allocate(1024));
					 }
					 if(skey.isReadable()) {
						 SocketChannel socketChannel =	 (SocketChannel)skey.channel();
						 ByteBuffer buffer =ByteBuffer.allocate(1024);
						 buffer =(ByteBuffer)skey.attachment();
						 
						 buffer.clear();
						String  receivedString = Charset.forName("GBK").newDecoder().decode(buffer).toString();
						System.out.println("接收到客户端发来的数据：：：："+receivedString);
						socketChannel.register(selector, SelectionKey.OP_WRITE);
					 }else if(skey.isWritable()) {
						 SocketChannel socketChannel =	 (SocketChannel)skey.channel();
							String sendString = "服务端数据：：：我是服务端";
							ByteBuffer buferr =ByteBuffer.wrap(sendString.getBytes("GBK"));
							socketChannel.write(buferr);
							socketChannel.register(selector, SelectionKey.OP_READ);
							
					 }
					 
		   }
				   set.clear();
			   } }} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
