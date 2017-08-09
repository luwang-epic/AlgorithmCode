package com.wang.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Lock�ӿ��Լ�����ʹ�����������ŵĿ����˾�����Դ�İ�ȫ���ʣ����������������ֶ�д����������Ϊ��ͨ����
 * Ϊ��������ܣ�Java�ṩ�˶�д�����ڶ��ĵط�ʹ�ö�������д�ĵط�ʹ��д���������ƣ�
 * ���û��д��������£�������������,��һ���̶�������˳����ִ��Ч�ʡ�
 * Java�ж�д���и��ӿ�java.util.concurrent.locks.ReadWriteLock��Ҳ�о����ʵ��ReentrantReadWriteLock
 * 
 * ReentrantReadWriteLock �� ReentrantLock ���Ǽ̳й�ϵ�������ǻ��� AbstractQueuedSynchronizer��ʵ�֡�
 * 
 * ע�⣺ ��ͬһ�߳��У����ж����󣬲���ֱ�ӵ���д����lock���� ����������������
 *
 */
public class ReadWriteLockDemo {
	
	
	static class MyCount {
		private String oid; // �˺�
		private int cash; // �˻����

		MyCount(String oid, int cash) {
			this.oid = oid;
			this.cash = cash;
		}

		public String getOid() {
			return oid;
		}

		public void setOid(String oid) {
			this.oid = oid;
		}

		public int getCash() {
			return cash;
		}

		public void setCash(int cash) {
			this.cash = cash;
		}

		@Override
		public String toString() {
			return "MyCount{" + "oid= " + oid  + ", cash=" + cash + '}';
		}
	}
	
	
	static class User implements Runnable{
		private String name;                 //�û���  
        private MyCount myCount;         //��Ҫ�������˻�  
        private int iocash;                 //�����Ľ���Ȼ������֮����  
        
        //��д��
        private ReadWriteLock lock;
        
        //�Ƿ��ȡ���
        private boolean isCheck;         //�Ƿ��ѯ  
        
        User(String name, MyCount myCount, int iocash, ReadWriteLock lock, boolean isCheck) {  
                this.name = name;  
                this.myCount = myCount;  
                this.iocash = iocash;  
                this.lock = lock;  
                this.isCheck = isCheck;  
        } 

		@Override
		public void run() {
			
			if(isCheck){
				//��ȡ����
				lock.readLock().lock();
				try {
					System.out.println("����" + name + "���ڲ�ѯ" + myCount 
											+ "�˻�����ǰ���Ϊ" + myCount.getCash());
				} finally {
					//�ͷŶ���
					lock.readLock().unlock();
				}	
			}else{
				
				try {
					
					//���д��
					lock.writeLock().lock();
					System.out.println("д��" + name + "���ڲ���" + myCount + "�˻������Ϊ" + iocash +"����ǰ���Ϊ" + myCount.getCash());
					
					//д���˺�
					myCount.setCash(myCount.getCash()+ iocash);
					System.out.println("д��" + name + "����" + myCount + "�˻��ɹ������Ϊ" + iocash +"����ǰ���Ϊ" + myCount.getCash()); 
						
				} finally {
					//�ͷ�д��
					lock.writeLock().unlock();
				}
				
				
			}
			
			
		}
		
	}
	
	

	public static void main(String[] args) {
		
		//�����������ʵ��˻�  
        MyCount myCount = new MyCount("95599200901215522", 10000);  
        //����һ��������  
        ReadWriteLock lock = new ReentrantReadWriteLock(false);
        
        //����2���̵߳Ĺ̶���С���̳߳�
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
		// ����һЩ���������û���һ�����ÿ�����Ĵ棬ȡ��ȡ�������ְ�
		User u1 = new User("����", myCount, -4000, lock, false);
		User u2 = new User("����", myCount, 6000, lock, false);
		User u3 = new User("����", myCount, 800, lock, false);
		User u4 = new User("����", myCount, 0, lock, true);
		
		// ���̳߳���ִ�и����û��Ĳ���
		executor.execute(u1);
		executor.execute(u2);
		executor.execute(u3);
		executor.execute(u4);
		
		
		// �ر��̳߳�
		executor.shutdown();
	
		
	}

}
