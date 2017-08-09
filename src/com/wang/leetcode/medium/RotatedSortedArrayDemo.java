package com.wang.leetcode.medium;


public class RotatedSortedArrayDemo {
	
	/**
	 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
	 * 
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 * 
	 * Write a function to determine if a given target is in the array.
	 * 
	 * The array may contain duplicates.
	 * 
	 */
	
	public class Solution {
	    public boolean search(int[] nums, int target) {
	    	
	    	if(nums.length == 0){
	    		return false;
	    	}
	    	
	    	if(nums.length == 1){
	    		if(target == 1)
	    			return true;
	    		else
	    			return false;
	    	}
	    	
	    	
	    	int index = 0; //记录开始出现降序的位置   
	    	boolean flag = true;  //记录数组是否所有元素都相等
	    	
	    	//如果数组元素都相等     可能出现相等的情况，这是有多个位置
	    	for(int i=1; i<nums.length; i++){
	    		if(nums[i] != nums[0]){
	    			flag = false;
	    			break;
	    		}
	    	}
	    	
	    	if(flag){
	    		if(target <= nums.length && target > 0)
	    			return true;
	    		else
	    			return false;
	    	}
	    	
	    	
	    	for(int i=0; i<nums.length-1; i++){
	    		if(nums[i+1] <= nums[i]){
	    			index = i+1;
	    			break;
	    		}
	    	}
	    	
	    	if(target == nums.length -index){
	    		return true;
	    	}
	    	
	        return false;
	    }
	}
	
	
	//注意：   这里的 target  指数组中的一个元素    是否在该元素处旋转  
	
	/**   参考答案    ：  使用二分法
	 * 
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid = -1;
        while(start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //If we know for sure right side is sorted or left side is unsorted
            if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            //If we know for sure left side is sorted or right side is unsorted
            } else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            //If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
            //any of the two sides won't change the result but can help remove duplicate from
            //consideration, here we just use end-- but left++ works too
            } else {
                end--;
            }
        }
        
        return false;
    }

	 * 
	 */
	
	
	
	public static void main(String[] args) {
		RotatedSortedArrayDemo demo = new RotatedSortedArrayDemo();
		Solution solution = demo.new Solution();
		
		int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
		int target = 3;
		
		//int[] nums = new int[]{1,1};
		//int target = 1;
		
		System.out.println(solution.search(nums, target));
		
	}
	

}
