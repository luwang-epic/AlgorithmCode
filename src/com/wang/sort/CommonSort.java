package com.wang.sort;

import java.util.Arrays;

/**    一些常见的排序算法
 *   主要包括 如下几类  ： 
 *     1. 插入排序  ：    直接插入排序
 *     				希尔排序
 *     2. 选择排序  ：     简单选择排序
 *     				堆排序
 *     3.交换排序     ：    冒泡排序
 *     			  ：     快速排序
 *     4.归并排序
 *     
 *     5.基排序
 *
 */
public class CommonSort {

	// 直接插入排序
	public static void insertSort(int[] data) {

		int temp = 0;

		for (int i = 1; i < data.length; i++) {

			int j = i - 1;

			temp = data[i];

			for (; j >= 0 && temp < data[j]; j--) {

				data[j + 1] = data[j]; // 将大于temp的值整体后移一个单位

			}

			data[j + 1] = temp;

		}

		System.out.println("直接插入排序结果如下：");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");
	}

	// 希尔排序
	public static void shellSort(int[] data) {
		double d1 = data.length;

		int temp = 0;

		while (true) {

			d1 = Math.ceil(d1 / 2);

			int d = (int) d1;

			for (int x = 0; x < d; x++) {

				for (int i = x + d; i < data.length; i += d) {

					int j = i - d;

					temp = data[i];

					for (; j >= 0 && temp < data[j]; j -= d) {

						data[j + d] = data[j];

					}

					data[j + d] = temp;

				}

			}

			if (d == 1)

				break;

		}

		System.out.println("希尔排序结果如下：");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");
	}

	// 冒泡排序
	public static void bubbleSort(int[] data) {
		int temp = 0;
		for (int i = 0; i < data.length - 1; i++) {
			for (int j = 0; j < data.length - 1 - i; j++) {
				if (data[j] > data[j + 1]) {
					temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}

		System.out.println("冒泡排序结果如下：");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");
	}

	// 快速排序
	public static void quickSort(int[] data) {

		_quickSort(data, 0, data.length - 1);

		System.out.println("快速排序结果如下：");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");
	}

	private static void _quickSort(int[] data, int low, int high) {

		if (low < high) {

			int middle = getMiddle(data, low, high); // 将list数组进行一分为二

			_quickSort(data, low, middle - 1); // 对低字表进行递归排序

			_quickSort(data, middle + 1, high); // 对高字表进行递归排序

		}

	}

	private static int getMiddle(int[] array, int low, int high) {

		int tmp = array[low]; // 数组的第一个作为中轴

		while (low < high) {

			while (low < high && array[high] >= tmp) {

				high--;

			}

			array[low] = array[high]; // 比中轴小的记录移到低端

			while (low < high && array[low] <= tmp) {

				low++;

			}

			array[high] = array[low]; // 比中轴大的记录移到高端

		}

		array[low] = tmp; // 中轴记录到尾

		return low; // 返回中轴的位置

	}

	// 选择排序
	public static void selectionSort(int[] data) {
		int position = 0;

		for (int i = 0; i < data.length; i++) {

			int j = i + 1;

			position = i;

			int temp = data[i];

			for (; j < data.length; j++) {

				if (data[j] < temp) {

					temp = data[j];

					position = j;

				}

			}

			data[position] = data[i];

			data[i] = temp;

		}
		
		System.out.println("简单选择排序结果如下：");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");	
	}
	
	
	//归并排序
	public static void mergeSort(int[] data, int left, int right){
		
		if(left<right){  
	        //找出中间索引  
	        int center=(left+right)/2;  
	        //对左边数组进行递归  
	        mergeSort(data,left,center);  
	        //对右边数组进行递归  
	        mergeSort(data,center+1,right);  
	        //合并  
	        merge(data,left,center,right);         
	    }  
		
	}
	
	public static void merge(int[] data, int left, int center, int right) {   
	    int [] tmpArr=new int[data.length];  
	    int mid=center+1;  
	    //third记录中间数组的索引  
	    int third=left;  
	    int tmp=left;  
	    while(left<=center&&mid<=right){  
	        //从两个数组中取出最小的放入中间数组  
	        if(data[left]<=data[mid]){  
	            tmpArr[third++]=data[left++];  
	        }else{  
	            tmpArr[third++]=data[mid++];  
	        }  
	    }  
	    //剩余部分依次放入中间数组  
	    while(mid<=right){  
	        tmpArr[third++]=data[mid++];  
	    }  
	    while(left<=center){  
	        tmpArr[third++]=data[left++];  
	    }  
	    //将中间数组中的内容复制回原数组  
	    while(tmp<=right){  
	        data[tmp]=tmpArr[tmp++];  
	    }  
	    System.out.println(Arrays.toString(data));  
	} 

	/** 
	 * 已知H[s…m]除了H[s] 外均满足堆的定义 
	 * 调整H[s],使其成为大顶堆.即将对第s个结点为根的子树筛选,  
	 * 
	 * @param H是待调整的堆数组 
	 * @param s是待调整的数组元素的位置 
	 * @param length是数组的长度      //调整好的元素就不属于堆得部分，即后面存放了排序好的元素
	 */
	public static void heapAdjust(int heap[], int s, int length){
		int tmp = heap[s];
		int child = 2*s + 1;//左孩子结点的位置。(child+1 为当前调整结点的右孩子结点的位置)
		
		while(child < length){
			// 如果右孩子大于左孩子(找到比当前待调整结点大的孩子结点)
			if(child+1 < length && heap[child] < heap[child+1]){
				child++;
			}
			// 如果较大的子结点大于父结点
			if(heap[s] < heap[child]){
				heap[s] = heap[child]; // 那么把较大的子结点往上移动，替换它的父结点
				s = child;  // 重新设置s ,即待调整的下一个结点的位置
				child = 2*s+1;
			}
			// 如果当前待调整结点大于它的左右孩子，则不需要调整，直接退出  
			else{
				break;
			}
			
			// 当前待调整的结点放到比其大的孩子结点位置上
			heap[s] = tmp;
		}
	}
	
	/** 
	 * 初始堆进行调整 
	 * 将H[0..length-1]建成堆 
	 * 调整完之后第一个元素是序列的最小的元素 
	 */  
	public static void BuildingHeap(int[] heap) {
		// 最后一个有孩子的节点的位置 i= (length -1) / 2
		for (int i = (heap.length - 1) / 2; i >= 0; --i)
			heapAdjust(heap, i, heap.length);
	}

	//堆排序
	public static void heapSort(int[] heap){
		// 初始堆
		BuildingHeap(heap);
		// 从最后一个元素开始对序列进行调整
		for (int i = heap.length - 1; i > 0; --i) {
			// 交换堆顶元素H[0]和堆中最后一个元素
			int temp = heap[i];
			heap[i] = heap[0];
			heap[0] = temp;
			
			// 每次交换堆顶元素和堆中最后一个元素之后，都要对堆进行调整
			heapAdjust(heap, 0, i);
		}
		
		System.out.println(Arrays.toString(heap));
	}
	
 

	public static void main(String[] args) {

		int[] data = new int[] { 49, 38, 65, 97, 76, 13, 27, 49, 55, 4 };

		System.out.println("待排序数据如下：");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");

		insertSort(copyArray(data));

		shellSort(copyArray(data));

		bubbleSort(copyArray(data));

		quickSort(copyArray(data));
		
		selectionSort(copyArray(data));
		
		System.out.println("归并排序结果如下：");
		mergeSort(copyArray(data), 0, data.length-1);
		System.out.println("\n===========================================================\n");
		
		System.out.println("堆排序结果如下：");
		heapSort(copyArray(data));
		System.out.println("\n===========================================================\n");

	}

	public static int[] copyArray(int[] data) {
		int[] copyData = new int[data.length];
		for (int i = 0; i < data.length; i++) {
			copyData[i] = data[i];
		}
		return copyData;
	}
	
	/**
	//写一个快速排序的程序
	public void quickSort(int[] nums, int low, int high){
		
		if(low < high){
			
			int mid = quick(nums,low,high);
			quickSort(nums,low,mid-1);
			quickSort(nums,mid+1,high);
			
		}
			
	}
	
	
	private int quick(int[] nums, int low, int high){
		int temp = nums[low];
		
		while(low < high){
			//从高位找一个小于中枢放入低位
			while(low < high && nums[high] > temp){
				high--;
			}
			nums[low] = nums[high];
			
			//从低位找一个大于中枢放入高位
			while(low < high && nums[low] < temp){
				low++;
			}
			nums[high] = nums[low];
		}
		
		nums[low] = temp;
		
		return low;
	}
	
	
	
	

	public static void main(String[] args) {
		CommonSort sort = new CommonSort();
		int[] nums = new int[]{9,2,5,8,4,7,1,6,3};
		sort.quickSort(nums, 0, nums.length-1);
		
		for(int num: nums){
			System.out.print(num + "  ");
		}
	}
*/
}
