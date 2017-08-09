package com.wang.leetcode.medium;

public class UniquePathsDemo {

	/**
	 * A robot is located at the top-left corner of a m x n grid
	 * (marked 'Start' in the diagram below).
	 * The robot can only move either down or right at any point in time. 
	 * The robot is trying to reach the bottom-right corner of the grid 
	 * (marked 'Finish' in the diagram below).
	 * 
	 * How many possible unique paths are there?
	 * 
	 */
	public class Solution {
	    public int uniquePaths(int m, int n) {
	        int result =0;
	        
	        if(m ==1 || n==1){
	        	return 1;
	        }
	        
	        /*这种方法  时间复杂度过高   如何改进？
	        
	        //向右走相当于子问题
	        result += uniquePaths(m, n-1);
	        
	        //向下走也相当于子问题
	        result += uniquePaths(m-1, n);   */
	        
	        //如何将   递归  改为    循环    ？  
	        
	        
	        return result;
	    }
	}
	
	
	/**   参考答案     Java DP solution with complexity O(n*m)
	 * 
			 public class Solution {
			    public int uniquePaths(int m, int n) {
			        Integer[][] map = new Integer[m][n];
			        for(int i = 0; i<m;i++){
			            map[i][0] = 1;
			        }
			        for(int j= 0;j<n;j++){
			            map[0][j]=1;
			        }
			        for(int i = 1;i<m;i++){
			            for(int j = 1;j<n;j++){
			                map[i][j] = map[i-1][j]+map[i][j-1];
			            }
			        }
			        return map[m-1][n-1];
			    }
			}

		思路：
			The assumptions are
			When (n==0||m==0) the function always returns 1 since the robot
			can't go left or up.
			For all other cells. The result = uniquePaths(m-1,n)+uniquePaths(m,n-1)
			Therefore I populated the edges with 1 first and use DP to get the full 2-D array.
			
			Please give any suggestions on improving the code.
	 * 
	 * 
	 * 
	 */
	
	
	public static void main(String[] args) {
		UniquePathsDemo demo = new UniquePathsDemo();
		Solution solution = demo.new Solution();
		
		int m=23;
		int n=12;
		
		System.out.println(solution.uniquePaths(m, n));	
	}

}
