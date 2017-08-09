package com.wang.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition����Java 1.5�вų��ֵģ������������ͳ��Object��wait()��notify()ʵ���̼߳��Э����
 * ���ʹ��Object��wait()��notify()��ʹ��Condition��await()��signal()���ַ�ʽʵ���̼߳�Э�����Ӱ�ȫ�͸�Ч��
 * ���ͨ����˵�Ƚ��Ƽ�ʹ��Condition����������ʵ������ʹ����Condition��ģ���̼߳�Э����
 *
 *Condition�Ǹ��ӿڣ������ķ�������await()��signal()������(����Object��wait()��notify()����)
 *Condition������Lock�ӿڣ�����һ��Condition�Ļ���������lock.newCondition() 
 *����Condition��await()��signal()��������������lock����֮�ڣ�
 *				����˵������lock.lock()��lock.unlock֮��ſ���ʹ��
 */
public class ConditionDemo {
	
	final private Lock lock = new ReentrantLock();
	final private Condition condition = lock.newCondition();
	
	class Consumer extends Thread{
		
		@Override
		public void run() {
			consume();
		}

		private void consume() {
			try {
				lock.lock();
				System.out.println("���ڵ�һ�����ź�"+Thread.currentThread().getName());  
				condition.await();
				System.out.println("���Ѿ��ȵ���һ���ź�"+Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
	
	
	class Producer extends Thread{
		@Override
		public void run() {
			producer();
		}

		private void producer() {
			try {
				lock.lock();
				System.out.println("���õ���"+Thread.currentThread().getName()); 
				
				condition.signalAll();
				System.out.println("�ҷ�����һ���źţ�"+Thread.currentThread().getName()); 
			} finally {
				lock.unlock();
			}
		}
	}
	
	
	public static void main(String[] args) {
		ConditionDemo demo = new ConditionDemo();
		
		demo.new Consumer().start();
		demo.new Producer().start();
		
	}
	
	

}
