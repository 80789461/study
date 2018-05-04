package com.study.jdk;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class studyArrayList {
	public static void main(String[] args) {
		BufferedInputStream bi=null;
		BufferedOutputStream bo=null;
	        try {
				InputStream is = new FileInputStream(new File("D:\\1.txt"));
				  bi  = new BufferedInputStream(is);
				  bo = new BufferedOutputStream(
						 new FileOutputStream(new File("D:\\2.txt"))
						  );
				
				 int  n ;
				 byte[] b= new byte[1024];
				while((n =bi.read(b, 0, b.length))!=-1){
					bo.write(b,0,n);
				}
				bo.flush();
				System.out.println(new String(b));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					bo.close();
					bi.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	    }  
}
