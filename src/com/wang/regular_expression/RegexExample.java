package com.wang.regular_expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {

	public static void main(String[] args) {

		// ʹ����������ʽ .*runoob.* ���ڲ����ַ������Ƿ���� runoob �Ӵ���
		// ��һ��ʹ�÷�ʽ��ֻ�Ǽ򵥵��ж��Ƿ����
		String content = "I am noob from runoob.com.";

		String pattern = ".*runoob.*";

		boolean isMatch = Pattern.matches(pattern, content);

		System.out.println("�ַ������Ƿ������ 'runoob' ���ַ���? " + isMatch);

		
		
		// �ڶ���ʹ�÷�ʽ�� ������жϰ���ʲô�Ƿ��Լ��ж����ˣ�ǰ����ʲô�Ƿ�

		// ��ָ��ģʽ���ַ�������
		String line = "This order was placed for QT3000! OK?";
		String pattern2 = "(\\D*)(\\d+)(.*)";

		// ���� Pattern ����
		Pattern r = Pattern.compile(pattern2);

		// ���ڴ��� matcher ����
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println("Found value: " + m.group(0));  //0 �������ַ���
			System.out.println("Found value: " + m.group(1));  //1 ����һ��ƥ�䴦֮ǰ���ַ���
			System.out.println("Found value: " + m.group(2));  //2 ����һ��ƥ���ַ���
			System.out.println("Found value: " + m.group(3));  //3 ����һ��ƥ��֮����ַ���������
										//4 ������еڶ���ƥ���ַ����Ļ�����һ���͵ڶ��Լ����ַ���
		} else {
			System.out.println("NO MATCH");
		}
	}

}
