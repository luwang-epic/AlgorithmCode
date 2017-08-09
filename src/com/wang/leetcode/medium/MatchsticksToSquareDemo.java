package com.wang.leetcode.medium;

public class MatchsticksToSquareDemo {

	/**
	 * Remember the story of Little Match Girl? By now, 
	 * you know exactly what matchsticks the little match girl has, 
	 * please find out a way you can make one square by using up all those matchsticks. 
	 * You should not break any stick, but you can link them up, 
	 * and each matchstick must be used exactly one time.
	 * 
	 * Your input will be several matchsticks the girl has, 
	 * represented with their stick length. Your output will either be true or false, 
	 * to represent whether you could make one square using all the matchsticks 
	 * the little match girl has.

		Example 1:
		Input: [1,1,2,2,2]
		Output: true
		
		Explanation: You can form a square with length 2, 
		one side of the square came two sticks with length 1.
		
		
		Example 2:
		Input: [3,3,3,3,4]
		Output: false
		
		Explanation: You cannot find a way to form a square with all the matchsticks.
		Note:
		The length sum of the given matchsticks is in the range of 0 to 10^9.
		The length of the given matchstick array will not exceed 15.
	 * 
	 * 
	 *
	 */
	
	public class Solution {
	    public boolean makesquare(int[] nums) {
	    	if(nums.length < 3){
	    		return false;
	    	}
	    	
	    	int sum = 0;
	    	for(int i =0; i<nums.length; i++){
	    		sum += nums[i];
	    	}
	    	
	    	if(sum % 4 != 0){
	    		return false;
	    	}
	    	
	    	int ave = sum/4;
	    	
	    	boolean[] flag = new boolean[nums.length];
	    	for(int i=0; i<flag.length; i++){
	    		flag[i] = true;
	    	}
	    	
	    	//构建4条变
	    	for(int i=0; i<4; i++){
	    		if(!buildEdge(nums, ave,flag)){
	    			return false;
	    		}
	    	}
	    	
	    	for(int i=0; i<flag.length; i++){
	    		if(flag[i]){
	    			return false;
	    		}
	    	}
	    	
	        return true;
	    }

	    
	    //这样查找  这个没法通过  new int[]{5,5,5,5,4,4,4,4,3,3,3,3}
	    
	    //flag   --- >   true : 可以使用     false：  表示不可以使用
		private boolean buildEdge(int[] nums, int ave, boolean[] flag) {
			int min = ave+1;
			int max = 0;
			
			int index = 0;
			for(int i=0; i<nums.length; i++){
				if(flag[i]){
					if(nums[i] > max){
						max = nums[i];
						index = i;
					}
				}
			}
			flag[index] = false;
			
			if(max == ave){
				return true;
			}
			
			if(max > ave){
				return false;
			}
			
			while(true){
				for(int i=0; i<nums.length; i++){
					if(flag[i]){
						if(nums[i] < min){
							min = nums[i];
							index = i;
						}
					}
				}
				
				if(min == ave+1){
					return false;
				}else
					flag[index] = false;
				
				max = max + min;
				if(max > ave)
					return false;
				else if(max == ave)
					return true;
			}
		}
	}
	
	
	/**   Simple Recursion Java solution. 66ms
	 * 
		Since it is square, the basic idea is get width of each side(total 4 sides) 
		and use recursive way to figure out which group each matchstick belongs.

		public boolean makesquare(int[] nums) {
		        Long sum=0l;
		        for(int x:nums){
		            sum=sum+x;
		        }
		        if(sum%4!=0||nums.length<4) return false;
		        long width=(sum/4);
		        Arrays.sort(nums);
		        long sum1=0,sum2=0,sum3=0,sum4=0;
		        return helper(nums,nums.length-1,sum1,sum2,sum3,sum4,width);
		        
		    }
		    public boolean helper(int[] a, int i,long sum1,long sum2,long sum3,long sum4, long width){
		        if(sum1>width||sum2>width||sum3>width||sum4>width) return false;
		        if(i==-1){
		            if(sum1==width&&sum2==width&&sum3==width&&sum4==width) return true;
		            else return false;
		        }
				//check a[i]  belonging to side1,side2,side3,side4
		        return helper(a,i-1,sum1+a[i],sum2,sum3,sum4,width)||
		        helper(a,i-1,sum1,sum2+a[i],sum3,sum4,width)||
		        helper(a,i-1,sum1,sum2,sum3+a[i],sum4,width)||
		        helper(a,i-1,sum1,sum2,sum3,sum4+a[i],width);
		    }
	 * 
	 * 
	 * 
	 */
	
	
	
	
	public static void main(String[] args) {
		MatchsticksToSquareDemo demo = new MatchsticksToSquareDemo();
		Solution solution = demo.new Solution();
		
		int[] nums = new int[]{5,5,5,5,4,4,4,4,3,3,3,3};
		
		System.out.println(solution.makesquare(nums));
	}

}
