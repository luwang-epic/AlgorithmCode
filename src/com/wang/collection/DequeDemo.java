package com.wang.collection;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ˫�����(Deque),��Queue��һ���ӽӿڣ�˫�������ָ�ö������˵�Ԫ�ؼ������(offer)Ҳ�ܳ���(poll),
 * �����Deque����Ϊֻ�ܴ�һ����Ӻͳ��ӣ����ʵ��ջ�����ݽṹ��
 * ����ջ���ԣ�����ջ(push)�ͳ�ջ(pop)����ѭ�Ƚ����ԭ��
 *
 * Java����һ������Stack���࣬ȴû�н���Queue���ࣨ���Ǹ��ӿ����֣���
 * ����Ҫʹ��ջʱ��Java�Ѳ��Ƽ�ʹ��Stack�������Ƽ�ʹ�ø���Ч��ArrayDeque��
 * ��ȻQueueֻ��һ���ӿڣ�����Ҫʹ�ö���ʱҲ����ѡArrayDeque�ˣ���ѡ��LinkedList����
 *
 */
public class DequeDemo {

	public static void main(String[] args) {
		//Deque<String> deque = new LinkedList<String>();
		Deque<String> deque = new ArrayDeque<>();
		
        deque.push("a");
        deque.push("b");
        deque.push("c");
        System.out.println(deque);
        
        //��ȡջ��Ԫ�غ�Ԫ�ز����ջ
        String str = deque.peek();
        System.out.println(str);
        System.out.println(deque);
        
        while(deque.size() > 0) {
            //��ȡջ��Ԫ�غ�Ԫ�ؽ����ջ
            System.out.println(deque.pop());
        }
        System.out.println(deque);
	}
	
	
}
