package com.wang.concurrency;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Exchanger�����������߳�֮�佻�����ݣ�ֻ����2���̣߳�����֧�ָ�����߳�֮�以�����ݡ�
 * 
 * ���߳�A����Exchange�����exchange()������������������״̬��ֱ���߳�BҲ������exchange()������
 * Ȼ�����̰߳�ȫ�ķ�ʽ�������ݣ�֮���߳�A��B��������
 * 
 */
public class ExchangerDemo {
	
	private static volatile boolean isDone = false;
	
	
	static class ExchangerProducer implements Runnable{
		
		private Exchanger<Integer> exchanger;
		private static int data = 1;  // ��Ҫ����������
		
		public ExchangerProducer(Exchanger<Integer> exchanger) {
			this.exchanger = exchanger;
		}
		
		@Override
		public void run() {
			while(!Thread.interrupted() && !isDone){
				for(int i=1; i<=3; i++){
					try {
						TimeUnit.SECONDS.sleep(1);
						data = i;
						//����֮ǰ������Ϊ����
						System.out.println("producer before��" + data);
						
						//��������
						data = exchanger.exchange(data);
						//����֮�������Ϊ����
						System.out.println("producer after: " + data);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				//����
				isDone = true;
			}
		}
	}
	
	
	static class ExchangerConsumer implements Runnable{

		private Exchanger<Integer> exchanger;
		private static int data = 0;  // ��Ҫ����������
		
		public ExchangerConsumer(Exchanger<Integer> exchanger) {
			this.exchanger = exchanger;
		}
		
		@Override
		public void run() {
			while(!Thread.interrupted() && !isDone){
				data = 0;
				System.out.println("consumer before: " + data);
				
				try {
					TimeUnit.SECONDS.sleep(1);
					
					//��������
					data = exchanger.exchange(data);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//����֮�������
				System.out.println("consumer after: " + data);
				
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		//������
		Exchanger<Integer> exchanger = new Exchanger<>();
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		ExchangerProducer producer = new ExchangerProducer(exchanger);
		ExchangerConsumer consumer = new ExchangerConsumer(exchanger);
		executor.execute(producer);
		executor.execute(consumer);
		
		//�ر�
		executor.shutdown();
		
		
		try {
            executor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
	}

}
