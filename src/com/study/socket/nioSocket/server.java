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
			 ssc.socket().bind(new InetSocketAddress(1111));  //绑定端口
			 Selector sc= Selector.open();      //创建selector
			 ssc.configureBlocking(false);      //设置非阻塞模式
			 ssc.register(sc, SelectionKey.OP_ACCEPT);  //注册selector  为监听
			 Handler handler = new Handler(1024);
			 while(true){
				 if(sc.select(3000)==0){
					 System.out.println("等待请求超时");
					 continue;
				 }
				 System.out.println("开启处理请求");
				 //获取到客户端的请求  返回 selectkey  这里面包含的有channel 和  selecttor
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
