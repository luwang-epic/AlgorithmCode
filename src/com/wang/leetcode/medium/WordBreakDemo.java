package com.wang.leetcode.medium;

import java.util.Arrays;
import java.util.List;

public class WordBreakDemo {
	
	
	/**
	 * Given a non-empty string s and a dictionary wordDict containing a list of 
	 * non-empty words, determine if s can be segmented into a space-separated sequence of 
	 * one or more dictionary words. 
	 * You may assume the dictionary does not contain duplicate words.

			For example, given
			s = "leetcode",
			dict = ["leet", "code"].
			
			Return true because "leetcode" can be segmented as "leet code".

		UPDATE (2017/1/4):
		The wordDict parameter had been changed to a list of strings 
		(instead of a set of strings). 
		Please reload the code definition to get the latest changes.
	 * 
	 */
	
	public class Solution {
	    public boolean wordBreak(String s, List<String> wordDict) {
	    	
	    	if(s==null || s.equals("") || wordDict == null || wordDict.size() ==0){
	    		return false;
	    	}
	    	
	    	return solve(s,wordDict,new boolean[wordDict.size()]);
	    }
	
	
		private boolean solve(String s, List<String> wordDict, boolean[] flag) {

			if (s.replaceAll("#", "").equals("")) {
				return true;
			}
			
			boolean isSolve = false;

			for (int i = 0; i < wordDict.size(); i++) {
				if (!flag[i]) {			
					if (s.contains(wordDict.get(i))) {			
						isSolve = isSolve 
								|| solve(s.replaceAll(wordDict.get(i), "#"), wordDict, Arrays.copyOf(flag, flag.length));
					}else
						flag[i] = true;
				}
				
				//有一个true则匹配完成
				if(isSolve)
					return true;
			}

			return isSolve;
		}

	}
	
	/**   参考答案     ：   Java implementation using DP in two ways
			public class Solution {
			    public boolean wordBreak(String s, Set<String> dict) {
			        
			        boolean[] f = new boolean[s.length() + 1];
			        
			        f[0] = true;
			        
			        
//			        First DP
//			        for(int i = 1; i <= s.length(); i++){
//			            for(String str: dict){
//			                if(str.length() <= i){
//			                    if(f[i - str.length()]){
//			                        if(s.substring(i-str.length(), i).equals(str)){
//			                            f[i] = true;
//			                            break;
//			                        }
//			                    }
//			                }
//			            }
//			        }
			        
			        //Second DP
			        for(int i=1; i <= s.length(); i++){
			            for(int j=0; j < i; j++){
			                if(f[j] && dict.contains(s.substring(j, i))){
			                    f[i] = true;
			                    break;
			                }
			            }
			        }
			        
			        return f[s.length()];
			    }
			} 
	 * 
	 * 
	 */
	
	
	
	
	public static void main(String[] args) {
		WordBreakDemo demo = new WordBreakDemo();
		Solution solution = demo.new Solution();
		
		String s= "ccaccc"; //"ccbb"; //"cars";
		List<String> wordDict = Arrays.asList("cc","ac");//("bc","cb");//("car","ca","rs");
		
		System.out.println(solution.wordBreak(s, wordDict));
	}

}
