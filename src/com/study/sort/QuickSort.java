package com.study.sort;

public class QuickSort {
/**
 * 快速排序	
 */
	
	public static int getMiddle(int[] numbers, int low,int high)
    {
        int temp = numbers[low]; //数组的第一个作为中轴
        while(low < high)
        {
        while(low < high && numbers[high] > temp)
        {
            high--;
        }
        numbers[low] = numbers[high];//比中轴小的记录移到低端
        while(low < high && numbers[low] < temp)
        {
            low++;
        }
        numbers[high] = numbers[low] ; //比中轴大的记录移到高端
        }
        numbers[low] = temp ; //中轴记录到尾
        return low ; // 返回中轴的位置
    }
	 public static void quickSort(int[] numbers,int low,int high)
	    {
	        if(low < high)
	        {
	        int middle = getMiddle(numbers,low,high); //将numbers数组进行一分为二
	        quickSort(numbers, low, middle-1);   //对低字段表进行递归排序
	        quickSort(numbers, middle+1, high); //对高字段表进行递归排序
	        }
	    
	    }
	public static void main(String[] args) {
		long  start =  System.currentTimeMillis();
		int[] values = new int[]{23,48,19,47,90,101,34,100,200,13,1567,177,12345,1344};
		quickSort(values,0,values.length-1);
		for (int i = 0; i < values.length; i++) {
			System.out.println(values[i]);
		}
		long  end =  System.currentTimeMillis();
		
		System.out.println(end-start);
	}
	
}
