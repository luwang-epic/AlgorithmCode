package com.wang.other.problem;


/**
*���⣺������������������� 
*дһ��ʱ�临�ӶȾ����ܵ͵ĳ�����һ��һά���飨N��Ԫ�أ��е�����������еĳ��ȡ� 
*���磺������1��-1,2��-3,4��-5,6��-7�У�����ĵ���������Ϊ1,2,4,6,����������� 
*�ĳ���Ϊ4�� 
*���˼·��ʹ�ö�̬�滮�㷨 ʱ�临�Ӷ�O(n^2) 
*/  
public class MaxSubsequence {
	
	/**
	 * @param array ԭʼ����
	 * @param subseq  ��������������������е��±�
	 * @return ���������
	 */
	public static int[] solve(final int[] array, int[][] subseq){
		//��ʼ��subseq
		for(int i=0; i<array.length; i++){
			subseq[i][0] = i;
		}
		
		int[] lis = new int[array.length]; //���ڼ�¼��ǰ��Ԫ����Ϊ���Ԫ�ص���������г���  
		
		for(int i=0; i<array.length; i++){
			lis[i] = 1; //���õ�ǰԪ��array[i]��Ϊ���Ԫ�ص���������г���Ϊ1  
			
			for(int j=0; j<i; j++){
				if(array[i] > array[j] && lis[j] + 1 >lis[i]){
					lis[i] = lis[j] + 1; //����lis[i]��ֵ����Ϊ�и�����������
					for(int k=0; k<lis[j]; k++){
						subseq[i][k] = subseq[j][k];
					}
					subseq[i][lis[j]] = i;
				}
			}
		}
		
		int pos = max(lis);
		
		System.out.println("length --- >" +lis[pos]);
		
		int[] result = new int[lis[pos]];
		for(int i=0; i<result.length; i++){
			result[i] = array[subseq[pos][i]];
		}
		
		return result;
	}
	
	
	public static int max(int[] array){
		int maxValue = array[0];
		int pos = 0;
		
		for(int i =0; i<array.length; i++){
			if(maxValue < array[i]){
				maxValue = array[i];
				pos = i;
			}
		}
		
		return pos;
	}
	
	
	public static void main(String[] args) {
		int[] array = new int[]{1,-1,2,-3,4,-5,6,-7}; 
		int[][] subseq = new int[array.length][array.length];
		
		int[] result =MaxSubsequence.solve(array, subseq);
		for(int temp : result){
			System.out.print("  "+ temp);
		}
	}

}

