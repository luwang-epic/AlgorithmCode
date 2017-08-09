package com.wang.leetcode.medium;

public class Matrix01Demo {

	/**
	 * 
		Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
		The distance between two adjacent cells is 1.
		Example 1: 
		Input:
		
		0 0 0
		0 1 0
		0 0 0
		Output:
		0 0 0
		0 1 0
		0 0 0
		Example 2: 
		Input:
		
		0 0 0
		0 1 0
		1 1 1
		Output:
		0 0 0
		0 1 0
		1 2 1
		Note:
		The number of elements of the given matrix will not exceed 10,000.
		There are at least one 0 in the given matrix.
		The cells are adjacent in only four directions: up, down, left and right.
		
	 */
	
	public class Solution {
	    public int[][] updateMatrix(int[][] matrix) {
	    	int[][] result = new int[matrix.length][matrix[0].length];
	    	
	    	for(int i=0; i<matrix.length; i++){
	    		for(int j=0; j<matrix[0].length; j++){
	    			//搜索最接近的0
	    			for(int k=0; k<matrix.length; k++){
	    	    		
	    	    	}
	    		}
	    	}
	    	
	
	        return result;
	    }
	}
	
	public static void main(String[] args) {
		Matrix01Demo demo = new Matrix01Demo();
		Solution solution = demo.new Solution();
		
		int[][] matrix = new int[][]{{0,0,0},{0,1,0},{1,1,1}};
		
		int[][] result = solution.updateMatrix(matrix);
		for(int i=0; i<result.length; i++){
			for(int j=0; j<result[0].length; j++){
				System.out.print(result[i][j] + "  ");
			}
			System.out.println();
		}	
	}

}
