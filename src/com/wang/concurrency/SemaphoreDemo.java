package com.wang.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *  Semaphore��һ�ֻ��ڼ������ź������������趨һ����ֵ�����ڴˣ�����߳̾�����ȡ����źţ�
 *  �����Լ��������黹��������ֵ���߳���������źŽ��ᱻ������
 *  Semaphore������������һЩ����أ���Դ��֮��ģ��������ݿ����ӳأ�
 *  ����Ҳ���Դ�������Ϊ1��Semaphore��������Ϊһ�����ƻ������Ļ��ƣ���Ҳ�ж�Ԫ�ź�������ʾ���ֻ���״̬��
 *
 */
public class SemaphoreDemo {
	
	public static class MyThread extends Thread{
		private volatile Semaphore sem; // �ź���
		private int count; //�����ź����Ĵ�С
		
		public MyThread(Semaphore sem, int count) {
			this.sem = sem;
			this.count = count;
		}
		
		@Override
		public void run() {
			try {
				sem.acquire(count);
				Thread.sleep(200);
				System.out.println(Thread.currentThread().getName() + " acquire count="+count);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//�ͷŸ������ݵ���ɣ����䷵�ص��ź���
				sem.release(count);
				System.out.println(Thread.currentThread().getName() + " release " + count + "");
			}	
		}
	}
	
	private Semaphore semaphore;
	
	public SemaphoreDemo(int parallelism){
		semaphore = new Semaphore(parallelism);
	}
	
	
	// Semaphore��Ĭ��ʵ���Ƿǹ�ƽ��
	public SemaphoreDemo(int parallelism,boolean fair){
		semaphore = new Semaphore(parallelism,fair);
	}
	
	
	public Semaphore getSemaphore(){
		return semaphore;
	}
	
	public static void main(String[] args) {
		
		SemaphoreDemo demo = new SemaphoreDemo(10);
		
		//�����̳߳�
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		
		//���߳���ִ������
		threadPool.execute(new MyThread(demo.getSemaphore(), 5));
		threadPool.execute(new MyThread(demo.getSemaphore(), 4));
		threadPool.execute(new MyThread(demo.getSemaphore(), 7));
		
		//�ر��̳߳�
		threadPool.shutdown();
		
		
	}

}
