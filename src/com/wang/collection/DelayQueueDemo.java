package com.wang.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * DelayQueue��һ��BlockingQueue�����ػ��Ĳ�����Delayed��
 * Delayed��չ��Comparable�ӿڣ��ȽϵĻ�׼Ϊ��ʱ��ʱ��ֵ��Delayed�ӿڵ�ʵ����getDelay�ķ���ֵӦΪ�̶�ֵ��final����
 * DelayQueue�ڲ���ʹ��PriorityQueueʵ�ֵġ�
 * 
 * DelayQueue = BlockingQueue + PriorityQueue + Delayed
 * 
 * DelayQueue�Ĺؼ�Ԫ��BlockingQueue��PriorityQueue��Delayed��
 * ������ô˵��DelayQueue��һ��ʹ�����ȶ��У�PriorityQueue��ʵ�ֵ�BlockingQueue�����ȶ��еıȽϻ�׼ֵ��ʱ�䡣
 * 
 * 
 * 
 * ������
 * a) �رտ������ӡ��������У��кܶ�ͻ��˵����ӣ�����һ��ʱ��֮����Ҫ�ر�֮��
 * b) ���档�����еĶ��󣬳����˿���ʱ�䣬��Ҫ�ӻ������Ƴ���
 * c) ����ʱ����������Э�黬����������Ӧ��ʽ����ʱ������ʱδ��Ӧ������
 * 
 * һ�ֱ����İ취���ǣ�ʹ��һ����̨�̣߳��������ж��󣬰�����顣���ֱ����İ취�򵥺��ã����Ƕ�����������ʱ��
 * ���ܴ����������⣬�����ʱ�䲻�����ã����ʱ�����Ӱ�쾫ȷ�ȣ���С�����Ч�����⡣��������������ʱ��ʱ��˳���� 
 * 
 * �ⳡ����ʹ��DelayQueue���ʺ��ˡ�
 *
 */
public class DelayQueueDemo {
	
	//������Sample����һ������ļ�ʵ�֡�������������Pair��DelayItem��Cache�����£�
	
	
	static class Pair<K,V>{
		
		public K first;
		
		public V second;
		
		public Pair(){};
		
		public Pair(K first, V second){
			this.first = first;
			this.second = second;
		}
			
	}
	
	//DelayQueue�е�Ԫ��     ����ʵ��Delayed�ӿ�
	static class DelayItem<T> implements Delayed{
		
		//Base of nanosecond timings, to avoid wrapping 
		private static final long NANO_ORIGIN = System.nanoTime();
		
		// Sequence number to break scheduling ties, and in turn to guarantee FIFO order among tiedentries.
		private static final AtomicLong sequencer = new AtomicLong(0);
		
		//Sequence number to break ties FIFO
		private final long sequenceNumber;
		
		//The time the task is enabled to execute in nanoTime units
		private final long time;
		
		private final T item;
		
		
		public DelayItem(T submit, long timeout) {
		        this.time = now() + timeout;
		        this.item = submit;
		        this.sequenceNumber = sequencer.getAndIncrement();
		}
		
		
		//Returns nanosecond time offset by origin
		final static long now(){
			return System.nanoTime() - NANO_ORIGIN;
		}
		
		
		public T getItem() {
			return this.item;
		}
		

		@Override
		public int compareTo(Delayed o) {
			if(o == this){
				return 0;
			}
			if(o instanceof DelayItem){
				DelayItem<?> x = (DelayItem<?>) o;
				long diff = time - x.time;
				if(diff <0)
					return -1;
				else if(diff>0)
					return 1;
				else if(sequenceNumber < x.sequenceNumber)
					return -1;
				else
					return 1;
			}
			
			long d = (getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS));
	        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
		}

		@Override
		public long getDelay(TimeUnit unit) {
			long d = unit.convert(time-now(), TimeUnit.NANOSECONDS);
			return d;
		}
		
	}
	
	
	
	static class Cache<K,V>{
		
		private ConcurrentMap<K, V> cacheMap = new ConcurrentHashMap<>();
		
		private DelayQueue<DelayItem<Pair<K, V>>> queue = new DelayQueue<>();
		
		private Thread daemonThread;
		
		public Cache() {

			Runnable daemonTask = new Runnable() {
				public void run() {
					daemonCheck();
				}

			};

			daemonThread = new Thread(daemonTask);
			daemonThread.setDaemon(true);
			daemonThread.setName("Cache Daemon");
			daemonThread.start();
		}

		private void daemonCheck() {
			System.out.println("cache service started.");
			
			for(;;){
				try {
					DelayItem<Pair<K,V>> delayItem = queue.take();
					if(delayItem !=null){
						//��ʱ������
						Pair<K,V> pair = delayItem.getItem();
						cacheMap.remove(pair.first, pair.second); // compare and remove
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}	
			
			System.out.println("cache service stopped.");
		}
		
		
		// ��Ӷ��󵽻�����
		public void put(K key, V value, long time, TimeUnit unit){
			V oldValue = cacheMap.put(key, value);
			
			/* �����if���û�б�Ҫ��    ԭ�����£�
			 *  ��ʹcacheMap��ԭ����Ԫ���Ѿ���ı䣬
			 *  ���ǵ�queue�и�Ԫ�ع���ʱ��ִ�е�cacheMap.remove(pair.first, pair.second)����
			 *  �����Ҳ���Ԫ�ض���ִ���κβ�������û�б�Ҫ��queue���Ƴ��ɵ�Ԫ��
			 *  
			 *  ע�⣺������Ƴ���������⣬�����޷�ȷ����Pair��key value����Ӧ��DelayItem����һ����
			 */
			if(oldValue != null){
		//		queue.remove(key);  //���� ����ò��������   Ӧ���Ƴ�����DelayItemԪ�زŶ԰�
			}
			
			long nanoTime = TimeUnit.NANOSECONDS.convert(time, unit);
			queue.put(new DelayItem<Pair<K,V>>(new Pair<K,V>(key, value), nanoTime));

		}
		
		
		public V get(K key){
			return cacheMap.get(key);
		}
		
	}
	
	
	
	//����Sample��main����ִ�еĽ����������У���һ��Ϊaaa���ڶ���Ϊnull��
	public static void main(String[] args) throws Exception{	
		Cache<Integer, String> cache = new Cache<Integer, String>();
        cache.put(1, "aaaa", 3, TimeUnit.SECONDS);

        Thread.sleep(1000 * 2);
        
        {
            String str = cache.get(1);
            System.out.println(str);
        }

        Thread.sleep(1000 * 2);
        
        {
            String str = cache.get(1);
            System.out.println(str);
        }
    }
		
		
	
	
}
