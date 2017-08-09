package com.wang.leetcode.medium;

public class ProductOfArrayExceptSelfDemo {

	/**
	 * 
	 * Given an array of n integers where n > 1, nums, 
	 * return an array output such that output[i] is equal to 
	 * the product of all the elements of nums except nums[i].
	 * 
	 * Solve it without division (不用除法解决) and in O(n).
	 * 
	 * For example, given [1,2,3,4], return [24,12,8,6].bh
	 *
	 */
	public class Solution {
	    public int[] productExceptSelf(int[] nums) {
	        
	    	int[] results = new int[nums.length];
	    	
	    	//保存顺乘积   
	    	int[] positiveProduct = new int[nums.length];
	    	//保存逆乘积
	    	int[] reverseProduct = new int[nums.length];
	    	
	    	for(int i=0; i<nums.length; i++){
	    		if(i == 0){
	    			positiveProduct[i] = nums[i];
	    			reverseProduct[i] = nums[nums.length-1];
	    		}else{
	    			positiveProduct[i] = positiveProduct[i-1]*nums[i];
	    			reverseProduct[i] = reverseProduct[i-1]*nums[nums.length-i-1];
	    		}	
	    	}
	    	
	    	for(int i=0; i<nums.length; i++){
	    		if(i == 0){
	    			results[i] = reverseProduct[nums.length-2];
	    		}else if(i==nums.length-1){
	    			results[i] = positiveProduct[i-1];
	    		}else{
	    			results[i] = positiveProduct[i-1] * reverseProduct[nums.length-i-2];
	    		}
	    	}
	    	
	    	return results;
	    }
	}
	
	
	/**   参考答案：  solution in O(n) without extra space
	public class Solution {
		public int[] productExceptSelf(int[] nums) {
		    int n = nums.length;
		    int[] res = new int[n];
		    res[0] = 1;
		    for (int i = 1; i < n; i++) {
		        res[i] = res[i - 1] * nums[i - 1];
		    }
		    int right = 1;
		    for (int i = n - 1; i >= 0; i--) {
		        res[i] *= right;
		        right *= nums[i];
		    }
		    return res;
		}
	}
	 * 
	 */
	
	public static void main(String[] args) {
		ProductOfArrayExceptSelfDemo demo = new ProductOfArrayExceptSelfDemo();
		Solution solution = demo.new Solution();
		
		int[] nums = new int[]{1,2,3,4};
		
		int[] results = solution.productExceptSelf(nums);
		
		for(int result : results){
			System.out.print(result +"  ");
		}
		
	}

}
