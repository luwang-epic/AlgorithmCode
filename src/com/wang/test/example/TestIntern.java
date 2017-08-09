package com.wang.test.example;

public class TestIntern {

	public static void main(String[] args) {
		String str1 = new StringBuilder("¼ÆËã»ú").append("Èí¼ş").toString();
		System.out.println(str1.intern() == str1);
		
		String str2 = new StringBuilder("jav").append("a").toString();
		System.out.println(str2.intern() == str2);
		
	}
}
