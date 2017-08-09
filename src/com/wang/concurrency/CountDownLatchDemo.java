package com.wang.concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * һ��ͬ�������࣬�����һ�����������߳���ִ�еĲ���֮ǰ��������һ�������߳�һֱ�ȴ���
 * �ø����ļ��� ��ʼ�� CountDownLatch�����ڵ����� countDown() ������
 * �����ڵ�ǰ����������֮ǰ��await ������һֱ��������֮�󣬻��ͷ����еȴ����̣߳�await �����к������ö����������ء�
 * ��������ֻ����һ�Ρ��������޷������á� һ���߳�(���߶��)�� �ȴ�����N���߳����ĳ������֮�����ִ��
 *
 * CountDownLatch������ܹ�ʹһ���̵߳ȴ������߳���ɸ��ԵĹ�������ִ�С�
 * ���磬Ӧ�ó�������߳�ϣ���ڸ���������ܷ�����߳��Ѿ��������еĿ�ܷ���֮����ִ�С�
 *
 */
public class CountDownLatchDemo {
	
	final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static class Worker extends Thread{
		String workerName;
		int workTime;
		CountDownLatch latch;
		
		public Worker(String workerName, int workTime, CountDownLatch latch) {
			this.workerName = workerName;
			this.workTime = workTime;
			this.latch = latch;
		}
		
		@Override
		public void run() {
			System.out.println("Worker "+workerName
								+" do work begin at "+sdf.format(new Date()));
			
			doWork(); // ��ʼ����
			
			System.out.println("Worker "+workerName
								+" do work complete at "+sdf.format(new Date()));
			
			latch.countDown(); //����ɹ�������������һ
			
		}
		
		
		private void doWork(){
			try {
				Thread.sleep(workTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String[] args) throws Exception{
		
		CountDownLatch latch = new CountDownLatch(2); //�������˵�Э��
		
		Worker work1 = new Worker("zhangsan", 5000, latch);
		Worker work2 = new Worker("lisi", 8000, latch);
		
		work1.start();
		work2.start();
		
		latch.await();//�ȴ����й�����ɹ���  ����Ժ�ſ���ִ����������
		
		System.out.println("all work done at "+sdf.format(new Date()));
		
	}
	

}
