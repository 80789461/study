package com.study.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private BlockingQueue queue;
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        System.out.println("�����������̣߳�");
        Random r = new Random();
        boolean isRunning = true;
        try {
            while (isRunning) {
                System.out.println("���Ӷ��л�ȡ����...");
                String data = (String) queue.poll(2, TimeUnit.SECONDS);
                if (null != data) {
                    System.out.println("�õ����ݣ�" + data);
                    System.out.println("�����������ݣ�" + data);
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                } else {
// ����2s��û���ݣ���Ϊ���������̶߳��Ѿ��˳����Զ��˳������̡߳�
                    isRunning = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("�˳��������̣߳�");
        }
    }

}