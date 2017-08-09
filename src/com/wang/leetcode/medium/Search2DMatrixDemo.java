package com.wang.leetcode.medium;

public class Search2DMatrixDemo {

	/**
	 * Write an efficient algorithm that searches for a value in an m x n matrix. 
	 * This matrix has the following properties:

			Integers in each row are sorted from left to right.
			The first integer of each row is greater than the last integer of the previous row.
			For example,
			
			Consider the following matrix:

			[
			  [1,   3,  5,  7],
			  [10, 11, 16, 20],
			  [23, 30, 34, 50]
			]
			Given target = 3, return true.
	 * 
	 * 
	 */
	
	public class Solution {
	    public boolean searchMatrix(int[][] matrix, int target) {
	    	
	    	if(matrix.length <= 0 || matrix[0].length <=0){
	    		return false;
	    	}
	    	
	    	if(target < matrix[0][0]){
	    		return false;
	    	}
	    	    	
	    	int low = 0;
	    	int high = matrix.length -1;
	    	int mid = 0;
	    	
	    	//找到哪一行
	    	while(low < high){
	    		mid = (low + high)/2;
	    		if(target > matrix[mid][0]){
	    			low = mid+1;
	    		}else if(target < matrix[mid][0]){
	    			high = mid-1;
	    		}else
	    			return true;
	    	}

	    	//记录哪一行   如果low == high == 0  那么就不执行上面的循环  程序也没有问题
	    	int index = low;
	    	if(matrix[low][0] > target){
	    		index = low - 1;
	    	}
	    	
	    	//找到哪一列
	    	low = 0;
	    	high = matrix[0].length - 1;
	    		
	    	while(low <= high){
	    		mid = (low + high)/2;
	    		if(target > matrix[index][mid]){
	    			low = mid+1;
	    		}else if(target < matrix[index][mid]){
	    			high = mid-1;
	    		}else
	    			return true;
	    	}
	    	
	        return false;
	    }
	}
	
	
	/** 参考答案   ：     Use binary search.

			n * m matrix convert to an array => matrix[x][y] => a[x * m + y]
		
			an array convert to n * m matrix => a[x] =>matrix[x / m][x % m];
	*
	*/
	
	public static void main(String[] args) {
		Search2DMatrixDemo demo = new Search2DMatrixDemo();
		Solution solution = demo.new Solution();
		
		int[][] matrix = new int[][]/*{{1,3}};*/{{1,   3,  5,  7},
									  {10, 11, 16, 20},
									  {23, 30, 34, 50}};
		int target = 34;
		
		System.out.println(solution.searchMatrix(matrix, target));
	}

}
