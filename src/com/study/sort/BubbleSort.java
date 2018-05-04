package com.study.sort;

public class BubbleSort {

	/**
	 * 冒泡排序
	 * 思路，将最后一个数与其余数逐个比较如比它大就交换位置
	 */
	public static void main(String[] args) {
		long  start =  System.currentTimeMillis();
		// TODO Auto-generated method stub
		int[] values = new int[]{23,48,19,47,90,101,34,100,200,13,1567,177,12345,1344};
		int temp=0;
		int length = values.length;
		for(int i=0;i<length;i++){
			for(int j=length-1;j>i;j--){
				if(values[j]<values[j-1]){
					temp=values[j-1];
					values[j-1] = values[j];
					values[j]=temp;
					
				}
				
			}
			
			
		}
		for(int k=0;k<length;k++){
			System.out.println(values[k]);
		}
		long  end =  System.currentTimeMillis();
		System.out.println(end-start);
	}

}
