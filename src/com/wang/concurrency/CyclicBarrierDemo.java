package com.wang.concurrency;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * һ��ͬ�������࣬������һ���̻߳���ȴ���ֱ������ĳ���������ϵ� (common barrier point)��
 * ���漰һ��̶���С���̵߳ĳ����У���Щ�̱߳��벻ʱ�ػ���ȴ�����ʱ CyclicBarrier �����á�
 * ��Ϊ�� barrier ���ͷŵȴ��̺߳�������ã����Գ���Ϊѭ�� �� barrier��
 * 
 * CountDownLatch�� һ��������һ�����̣߳��ȴ�����һ���̶߳���ɲ����� 
 * CyclicBarrier�� �����̻߳���ȴ���ɡ�
 * 
 * ����ʱ���ȴ������˶�׼����ʱ�������ܣ�
 * 
 */
public class CyclicBarrierDemo {

	
	static class Runner implements Runnable{

		// һ��ͬ�������࣬������һ���̻߳���ȴ���ֱ������ĳ���������ϵ� (common barrier point)
		private CyclicBarrier barrier;
		private String name;
		
		public Runner(CyclicBarrier barrier, String name) {
			this.barrier = barrier;
			this.name = name;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1000 * (new Random()).nextInt(8));
	            System.out.println(name + " ׼������...");
	            
	            // barrier��await�����������в����߶��Ѿ��ڴ� barrier �ϵ��� await ����֮ǰ����һֱ�ȴ���
	            barrier.await();   
	            
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//�����з��������﹫�����ϵ�ʱ����ʼִ�к�������   �磺����
			System.out.println(name + " ���ܣ�");
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		CyclicBarrier barrier = new CyclicBarrier(3);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		//�ύ�����˶�Ա����
		executor.submit(new Runner(barrier, "1��ѡ��"));
		executor.submit(new Runner(barrier, "2��ѡ��"));
		executor.submit(new Runner(barrier, "3��ѡ��"));
		
		//�ر�
		executor.shutdown();
		
	}

}
