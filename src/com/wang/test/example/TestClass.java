package com.wang.test.example;


//�����java�����������ͨ�����Ϊ��testMethod
public class TestClass {

	private static void testMethod(){
		System.out.println("testMethod");
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		((TestClass)null).testMethod();
	}

}
