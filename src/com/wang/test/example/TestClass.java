package com.wang.test.example;


//下面的java代码可以运行通，输出为：testMethod
public class TestClass {

	private static void testMethod(){
		System.out.println("testMethod");
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		((TestClass)null).testMethod();
	}

}
