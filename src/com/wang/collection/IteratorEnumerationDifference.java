package com.wang.collection;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * 		Iterator��Enumeration����
 * 1.�ӿ��ڷ�����ͬ  
 * 2.Iterator֧��fail-fast���ƣ���Enumeration��֧�֡�
 * 3.�������ǽӿڣ��������ޱȽ����塣���Ƕ��ھ���ʵ������˵��Enumeration �� Iterator �ı����ٶȸ��졣
 * 			Ϊʲô�أ�������Ϊ��Hashtable��Iterator��ͨ��Enumerationȥʵ�ֵģ�
 * 			����Iterator����˶�fail-fast���Ƶ�֧�֣����ԣ�ִ�еĲ�����ȻҪ��һЩ�� 
 * 			�����������ǲ�ȷ���ģ��е�ʱ��Iterator�������죩
 * 4.Enumeration���Ƚ��������Iterator���Ƚ��ȳ���    //������֤���
 *
 */
public class IteratorEnumerationDifference {
	
	
	public static void main(String[] args) {
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		HashMap<String,String> hashmap = new HashMap<String,String>();

		//���õ�table�е�˳��  1,2    Hashtable ʵ���� Enumeration�ӿ�
		hashtable.put("1", "1");
		hashtable.put("2", "2");
		
		//���õ�map�е�˳�� 1,2 HashMap ʵ����Iterator�ӿ�
		hashmap.put("1", "1");
		hashmap.put("2", "2");
		
		
		System.out.println("Enumeration����˳��Ϊ1,2 ����˳�����£�");
		Enumeration<String> enumeration = hashtable.elements();
		while (enumeration.hasMoreElements()) {
			String result = enumeration.nextElement();
			System.out.println(result);
		}
		
		
		System.out.println("Iterator����˳��Ϊ1,2 ����˳�����£�");
		Iterator<Entry<String, String>> iterator  = hashmap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, String> entry = iterator.next();
			String value = entry.getValue();
			System.out.println(value);
		}
		
		
		
	}

}
