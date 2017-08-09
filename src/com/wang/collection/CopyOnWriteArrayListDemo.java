package com.wang.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Copy-On-Write���COW����һ�����ڳ�������е��Ż����ԡ�
 * �����˼·�ǣ���һ��ʼ��Ҷ��ڹ���ͬһ�����ݣ���ĳ������Ҫ�޸�������ݵ�ʱ��
 * �Ż�����������Copy��ȥ�γ�һ���µ�����Ȼ���ٸģ�����һ����ʱ������ԡ�
 * ��JDK1.5��ʼJava���������ṩ������ʹ��CopyOnWrite����ʵ�ֵĲ�������,
 * ������CopyOnWriteArrayList��CopyOnWriteArraySet��
 * CopyOnWrite�����ǳ����ã������ڷǳ���Ĳ���������ʹ�õ���
 * 
 * CopyOnWrite�����������ڶ���д�ٵĲ������������������������������Ʒ��Ŀ�ķ��ʺ͸��³���
 * 
 * CopyOnWrite����ֻ�ܱ�֤���ݵ�����һ���ԣ����ܱ�֤���ݵ�ʵʱһ���ԡ�
 * ���������ϣ��д��ĵ����ݣ������ܶ������벻Ҫʹ��CopyOnWrite������
 * 
 * CopyOnWriteArrayList��add������ʵ�֣���CopyOnWriteArrayList�����Ԫ�أ���
 * ���Է�������ӵ�ʱ������Ҫ�����ģ�������߳�д��ʱ���Copy��N����������
 * 
 * ����ʱ����Ҫ�������������ʱ���ж���߳�������CopyOnWriteArrayList������ݣ������ǻ�����ɵ����ݣ�
 * ��Ϊд��ʱ�򲻻���ס�ɵ�CopyOnWriteArrayList��
 *
 */
public class CopyOnWriteArrayListDemo {

	public static void main(String[] args) throws Exception{
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		final CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>(list);
		
		Thread t = new Thread(new Runnable() {
			int count = -1;
			
			@Override
			public void run() {
				while(true){
					cowList.add(count++ + "");
				}	
			}
		});
		
		t.setDaemon(true);
		t.start();
		
		Thread.sleep(3);
		
		//��δ�����forѭ���б���list��ʱ��ͬʱ�����list��hashcode������list�ǲ���ͬһ��list��
		for (String s : cowList) {  
            System.out.println(cowList.hashCode());  
            System.out.println(s);  
        }  
	}
	
}
