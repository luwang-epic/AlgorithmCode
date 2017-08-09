package com.wang.concurrency;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//ͨ��Condition��PriorityQueueʵ�������ߡ�������ģʽ��
public class ConsumerProducerPattern {

	private int queueSize = 10;
	private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
	
	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	
	
	class Producer extends Thread{
		private volatile boolean flag = true;
		
		@Override
		public void run() {
			produce();
		}

		private void produce() {
			while(flag){
				lock.lock();
				try {
					while(queue.size() == queueSize){
						System.out.println("���������ȴ��п���ռ�"); 
						try {
							notFull.await();
						} catch (InterruptedException e) {
							flag = false;
							//e.printStackTrace();
						}
					}
					
					queue.offer(1); //ÿ�β���һ��Ԫ��  
					notEmpty.signal();  //�������в�Ϊ�գ�����һ���ź�
					System.out.println("�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺"+(queueSize-queue.size()));
					
				} finally {
					lock.unlock();
				}
			}	
		}
	}
		
	class Consumer extends Thread{
		private volatile boolean flag = true;
		
		@Override
		public void run() {
			consume();
		}

		private void consume() {
			while(flag){
				lock.lock();
				try {
					
					while(queue.isEmpty()){
						System.out.println("���пգ��ȴ�����");
						try {
							notEmpty.await();
						} catch (InterruptedException e) {
							flag = false;
							//e.printStackTrace();
						}
					}
					
					queue.poll();  //ÿ�����߶���Ԫ��
					notFull.signal(); //����һ������в�Ϊ�գ�����һ���ź�
					System.out.println("�Ӷ���ȡ��һ��Ԫ�أ�����ʣ��"+queue.size()+"��Ԫ��"); 
					
				} finally {
					lock.unlock();
				}
			}
		}
	}
		
		
	public static void main(String[] args) throws Exception{
		
		ConsumerProducerPattern demo = new ConsumerProducerPattern();
		
		Producer producer = demo.new Producer();
		Consumer consumer = demo.new Consumer();
		
		producer.start();
		consumer.start();
		
		Thread.sleep(0);
		
		producer.interrupt();
		consumer.interrupt();
	}
	
	
	
	
}
