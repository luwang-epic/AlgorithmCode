package com.wang.regular_expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {

	public static void main(String[] args) {

		// 使用了正则表达式 .*runoob.* 用于查找字符串中是否包了 runoob 子串：
		// 第一中使用方式，只是简单的判断是否包含
		String content = "I am noob from runoob.com.";

		String pattern = ".*runoob.*";

		boolean isMatch = Pattern.matches(pattern, content);

		System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);

		
		
		// 第二种使用方式， 具体的判断包含什么是否串以及有多少了，前后是什么是否串

		// 按指定模式在字符串查找
		String line = "This order was placed for QT3000! OK?";
		String pattern2 = "(\\D*)(\\d+)(.*)";

		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern2);

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println("Found value: " + m.group(0));  //0 ：整个字符串
			System.out.println("Found value: " + m.group(1));  //1 ：第一个匹配处之前的字符串
			System.out.println("Found value: " + m.group(2));  //2 ：第一个匹配字符串
			System.out.println("Found value: " + m.group(3));  //3 ：第一个匹配之后的字符串，类推
										//4 ：如果有第二个匹配字符串的话，第一个和第二自己的字符串
		} else {
			System.out.println("NO MATCH");
		}
	}

}
