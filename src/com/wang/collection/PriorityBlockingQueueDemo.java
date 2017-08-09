package com.wang.collection;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue��һ��û�б߽�Ķ��У������������� java.util.PriorityQueueһ����
 * ��Ҫע�⣬PriorityBlockingQueue���������null����
 * ���в���PriorityBlockingQueue�Ķ������ʵ�� java.lang.Comparable�ӿڣ�
 * �������ȼ������������ǰ������Ƕ�����ӿڵ�ʵ��������ġ�
 * ���⣬���ǿ��Դ�PriorityBlockingQueue���һ��������Iterator�������������������֤�������ȼ�˳����е�����
 *
 */
public class PriorityBlockingQueueDemo {

	public static class PriorityElement implements Comparable<PriorityElement>{
		
		private int priority;//�������ȼ�
		
		PriorityElement(int priority) {
		    //��ʼ�����ȼ�
		    this.priority = priority;
		}
		
		public int getPriority() {
			return priority;
		}
		public void setPriority(int priority) {
			this.priority = priority;
		}


		@Override
		public int compareTo(PriorityElement o) {
			//�������ȼ�����
			return priority >= o.getPriority() ? 1 : -1;
		}
		
		@Override
		public String toString() {
		    return "PriorityElement [priority=" + priority + "]";
		}
		
	}
	
	
	public static void main(String[] args) throws Exception{
		
		PriorityBlockingQueue<PriorityElement> blockingQueue = 
						new PriorityBlockingQueue<PriorityElement>();
		
		for(int i=0; i<5; i++){
			Random random = new Random();
			PriorityElement element = new PriorityElement(random.nextInt(10));
			blockingQueue.put(element);
		}
		
		while(!blockingQueue.isEmpty()){
	        System.out.println(blockingQueue.take());
	    }
	}
	
	
	
}
