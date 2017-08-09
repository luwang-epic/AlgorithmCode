package com.wang.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrixDemo {

	
	/**
	 * Given a matrix of m x n elements (m rows, n columns), 
	 * return all elements of the matrix in spiral order.
	 * 
	 * For example,Given the following matrix:
	 * 	[
	 * 		[ 1, 2, 3 ],
	 * 		[ 4, 5, 6 ],
	 * 		[ 7, 8, 9 ]
	 * 	]
	 * 
	 * You should return [1,2,3,6,9,8,7,4,5].
	 * 
	 */
	public class Solution {
		public List<Integer> spiralOrder(int[][] matrix) {
			List<Integer> results = new ArrayList<Integer>();
			
			OutOrder(matrix, results);
			
			return results;
		}
		
		//不知道提交的时候为什么会出现matrix[0]数组下标越界  ？？  在本地运行没有错误
		private void OutOrder(int[][] matrix, List<Integer> list){
			int row = matrix.length;
			int col = matrix[0].length;
			
			
			if(row == 1){
				for(int i=0; i<col; i++){
					list.add(matrix[0][i]);
				}
				
				return;
			}
			
			if(col == 1){
				for(int i=0; i<row; i++){
					list.add(matrix[i][0]);
				}
				
				return;
			}
			
			//最上面一行
			for(int i=0; i<col; i++){
				list.add(matrix[0][i]);
			}
			//最右边一行
			for(int i=1; i<row; i++){
				list.add(matrix[i][col-1]);
			}
			//最下面一行
			for(int i=col-2; i>=0; i--){
				list.add(matrix[row-1][i]);
			}
			//最左边一行
			for(int i=row-2; i>0; i--){
				list.add(matrix[i][0]);
			}
			
			if(row == 2 || col == 2){
				return;
			}else{
				int[][] temp = new int[row-2][col-2];
				for(int i=0; i<temp.length; i++){
					//temp[i] = new int[col-2];   // 不需要再次申请空间了
					for(int j=0; j<temp[0].length; j++){
						temp[i][j] = matrix[i+1][j+1];
					}
				}
				OutOrder(temp, list);
			}
			
		}
		
	}
	
	
	/**		参考答案  :   
	 * 
		public class Solution {
		    public List<Integer> spiralOrder(int[][] matrix) {
		        
		        List<Integer> res = new ArrayList<Integer>();
		        
		        if (matrix.length == 0) {
		            return res;
		        }
		        
		        int rowBegin = 0;
		        int rowEnd = matrix.length-1;
		        int colBegin = 0;
		        int colEnd = matrix[0].length - 1;
		        
		        while (rowBegin <= rowEnd && colBegin <= colEnd) {
		            // Traverse Right
		            for (int j = colBegin; j <= colEnd; j ++) {
		                res.add(matrix[rowBegin][j]);
		            }
		            rowBegin++;
		            
		            // Traverse Down
		            for (int j = rowBegin; j <= rowEnd; j ++) {
		                res.add(matrix[j][colEnd]);
		            }
		            colEnd--;
		            
		            if (rowBegin <= rowEnd) {
		                // Traverse Left
		                for (int j = colEnd; j >= colBegin; j --) {
		                    res.add(matrix[rowEnd][j]);
		                }
		            }
		            rowEnd--;
		            
		            if (colBegin <= colEnd) {
		                // Traver Up
		                for (int j = rowEnd; j >= rowBegin; j --) {
		                    res.add(matrix[j][colBegin]);
		                }
		            }
		            colBegin ++;
		        }
		        
		        return res;
		    }
		}
	 * 
	 * 
	 * 
	 */
	
	
	public static void main(String[] args) {

		SpiralMatrixDemo demo = new SpiralMatrixDemo();
		Solution solution = demo.new Solution();
		
		int[][] data = new int[][]{{1, 2, 3,10},
			 						{4, 5, 6, 11},
			 						{7, 8, 9,12},
			 						{13,14,15,16}};
		
		//int[][] data = new int[][]{{1,2}/*,{3,4}*/};
		
/*		int[][] data = new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}
												,{16,17,18,19,20},{21,22,23,24,25}};*/
												
		
		
		for(Integer num : solution.spiralOrder(data)){
			System.out.print(num + "  ");
		}
		
	}

}
