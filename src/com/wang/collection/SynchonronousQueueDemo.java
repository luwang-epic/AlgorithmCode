package com.wang.collection;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue Ҳ��һ���������ģ��������ر�֮���������ڲ�û��������
 * һ�������̣߳�����������Ʒ����put��ʱ�򣩣������ǰû������Ҫ���Ѳ�Ʒ(����ǰû���߳�ִ��take)��
 * �������̱߳����������ȴ�һ�������̵߳���take������take�������ỽ�Ѹ������̣߳�
 * ͬʱ�����̻߳��ȡ�����̵߳Ĳ�Ʒ�������ݴ��ݣ���������һ�����̳�Ϊһ����Թ���(��ȻҲ������take��put,ԭ����һ����)��
 *
 */
public class SynchonronousQueueDemo {
	
	public static void main(String[] args) throws InterruptedException{
		final SynchronousQueue<Integer> queue = new SynchronousQueue<>();
		
		Thread putThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("put thread start");
				try {
					queue.put(1);
				} catch (InterruptedException e) {
				}
				System.out.println("put thread end");
			}
		});
		
		Thread takeThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("take thread start");
				try {
					System.out.println("take from putThread: "+ queue.take());
				} catch (InterruptedException e) {

				}
				System.out.println("take thread end");
			}
		});
		
		
		 putThread.start();
	     Thread.sleep(1000);
	     takeThread.start();
	     
	     /*
	      * �ӽ�����Կ�����put�߳�ִ��queue.put(1) ��ͱ������ˣ�ֻ��take�߳̽��������ѣ�put�̲߳ſ��Է��ء�
	      * ������Ϊ����һ���߳����̼߳�һ��һ������Ϣ��ģ�͡�
	      */
	     	     
	}

}
