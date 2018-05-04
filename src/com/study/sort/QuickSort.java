package com.study.sort;

public class QuickSort {
/**
 * ��������	
 */
	
	public static int getMiddle(int[] numbers, int low,int high)
    {
        int temp = numbers[low]; //����ĵ�һ����Ϊ����
        while(low < high)
        {
        while(low < high && numbers[high] > temp)
        {
            high--;
        }
        numbers[low] = numbers[high];//������С�ļ�¼�Ƶ��Ͷ�
        while(low < high && numbers[low] < temp)
        {
            low++;
        }
        numbers[high] = numbers[low] ; //�������ļ�¼�Ƶ��߶�
        }
        numbers[low] = temp ; //�����¼��β
        return low ; // ���������λ��
    }
	 public static void quickSort(int[] numbers,int low,int high)
	    {
	        if(low < high)
	        {
	        int middle = getMiddle(numbers,low,high); //��numbers�������һ��Ϊ��
	        quickSort(numbers, low, middle-1);   //�Ե��ֶα���еݹ�����
	        quickSort(numbers, middle+1, high); //�Ը��ֶα���еݹ�����
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
