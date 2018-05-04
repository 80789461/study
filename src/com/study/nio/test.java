package com.study.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomAccessFile aFile;
		try {
			aFile = new RandomAccessFile("D:\\1.txt", "rw");
			FileChannel inChannel = aFile.getChannel();

			ByteBuffer buf = ByteBuffer.allocate(Integer.parseInt(String.valueOf(aFile.length())));
			

			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1) {

			System.out.println("Read " + bytesRead);
			buf.flip();
			while(buf.hasRemaining()){
			System.out.print((char) buf.get());
			}

			buf.clear();
			bytesRead = inChannel.read(buf);
			}
			aFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
