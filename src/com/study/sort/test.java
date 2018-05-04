package com.study.sort;


public class test {

	
	public static int go(int[] values,int index,int length){
		int  temp=values[index];
		while(index<length){
			while(index<length && temp<values[length]){
				length--;
			}
			values[index]=values[length];
			while(index<length && temp>values[index]){
				index++;
			}
			values[length]=values[index];
		}
		values[index] = temp ; //ÖÐÖá¼ÇÂ¼µ½Î²
		return index;
	}
	
	public static void ss(int[] values,int index,int length){
		if(index<length){
			
			int sd= go(values,index,length);
			ss(values,index,sd-1);
			ss(values,sd+1,length);
		}
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]  s = new int[]{9,12,1,34,24,78,2};
		ss(s,0,s.length-1);
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
	}

}
