package com.study.socket.nioSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NewNioServer {
	public  ByteBuffer readBuffer =ByteBuffer.allocate(1024); 
	public  ByteBuffer writeBuffer =ByteBuffer.allocate(1024); 
	public void  server() {
		try {
			//���������
			ServerSocketChannel  server =  ServerSocketChannel.open();
			server.socket().bind( new InetSocketAddress(1111));
			//���÷�����
			server.configureBlocking(false);
			Selector  selector = Selector.open();
			//selectorע��ͨ��
			server.register(selector, SelectionKey.OP_ACCEPT);
			while(true) {
				if(selector.select(3000)==0) {
					System.out.println("�ȴ���ʱ");
					continue;
				}
				Set<SelectionKey> set =  selector.selectedKeys();
				Iterator<SelectionKey> iterator = set.iterator();
				while(iterator.hasNext()) {
					SelectionKey key = iterator.next();
					SocketChannel clinet = null ;
					if(key.isAcceptable()) {
						clinet = ((ServerSocketChannel)key.channel()).accept();
						clinet.configureBlocking(false);
						clinet.register(selector, SelectionKey.OP_READ);
					}
					if(key.isReadable()) {
						System.out.println("��ʼ��ȡ..........");
						clinet = (SocketChannel)key.channel();
						readBuffer.clear();
						try {
							int n = clinet.read(readBuffer);
							if(n>0) {
								System.out.println(key.attachment()
		                                + " - ��ȡ���ݣ�" + getString(readBuffer));
								readBuffer.flip();
									String sendString ="recevied from client 111111";
									writeBuffer.clear();
									writeBuffer=ByteBuffer.wrap(sendString.getBytes("GBK"));
									clinet.write(writeBuffer);
									writeBuffer.flip();
									clinet.register(selector, SelectionKey.OP_WRITE);
							}else {
								key.channel();
							}
						} catch (Exception e) {
							key.channel();
						}
					}
					if(key.isWritable()) {
						//SocketChannel clinet = (SocketChannel)key.channel();
						for(SelectionKey key2 : selector.keys()) {
							
							Channel targetchannel = key2.channel();
				            //���except��Ϊ�գ����ط������ʹ����ݵĿͻ���
				            if(targetchannel instanceof SocketChannel && targetchannel!=clinet)
				            {
				                
								try {
									SocketChannel clinet2 = (SocketChannel)targetchannel;
					                writeBuffer.clear();
									writeBuffer.wrap("HAHAHA".getBytes());
									clinet2.write(writeBuffer);
									writeBuffer.flip();
								} catch (Exception e) {
									key2.channel();
								}
								
				            }
							
						}
					}
				}
				set.clear();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public static String getString(ByteBuffer buffer)
	    {
	        String string = "";
	        try
	        {
	            for(int i = 0; i<buffer.position();i++){
	                string += (char)buffer.get(i);
	            }
	            return string;
	        }
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	            return "";
	        }
	    }
	public static void main(String[] args) {
		new NewNioServer().server();
	}
}
