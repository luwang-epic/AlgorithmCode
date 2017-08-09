package com.wang.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class TinyURLDemo {
	
	
	/**
	 * TinyURL is a URL shortening service where you enter a URL 
	 * such as https://leetcode.com/problems/design-tinyurl and 
	 * it returns a short URL such as http://tinyurl.com/4e9iAk.
	 *
	 */
	public static class Codec {
		
		private static Map<String,String> maps = new HashMap<String,String>();

	    // Encodes a URL to a shortened URL.
	    public String encode(String longUrl) {
	        String shortUrl = "http://"+longUrl.substring(longUrl.lastIndexOf("/")+1);
	        
	        //add to maps
	        maps.put(shortUrl, longUrl);
	        
	        return shortUrl;
	    }

	    // Decodes a shortened URL to its original URL.
	    public String decode(String shortUrl) {
	        return maps.get(shortUrl);
	    }
	}
	
	
	
	/**   参考答案   ：  使用HashCode
	 * 
	public class Codec {
	    Map<Integer, String> map = new HashMap<>();
	    public String encode(String longUrl) {
	        map.put(longUrl.hashCode(),longUrl);
	        return "http://tinyurl.com/"+longUrl.hashCode();
	    }
	    public String decode(String shortUrl) {
	        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	    }
	}
	 * 
	 */
	
	
	
	public static void main(String[] args) {
		
	}

}
