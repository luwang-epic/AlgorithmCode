package com.wang.sort;

import java.util.Arrays;

/**    һЩ�����������㷨
 *   ��Ҫ���� ���¼���  �� 
 *     1. ��������  ��    ֱ�Ӳ�������
 *     				ϣ������
 *     2. ѡ������  ��     ��ѡ������
 *     				������
 *     3.��������     ��    ð������
 *     			  ��     ��������
 *     4.�鲢����
 *     
 *     5.������
 *
 */
public class CommonSort {

	// ֱ�Ӳ�������
	public static void insertSort(int[] data) {

		int temp = 0;

		for (int i = 1; i < data.length; i++) {

			int j = i - 1;

			temp = data[i];

			for (; j >= 0 && temp < data[j]; j--) {

				data[j + 1] = data[j]; // ������temp��ֵ�������һ����λ

			}

			data[j + 1] = temp;

		}

		System.out.println("ֱ�Ӳ������������£�");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");
	}

	// ϣ������
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

		System.out.println("ϣ�����������£�");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");
	}

	// ð������
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

		System.out.println("ð�����������£�");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");
	}

	// ��������
	public static void quickSort(int[] data) {

		_quickSort(data, 0, data.length - 1);

		System.out.println("�������������£�");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");
	}

	private static void _quickSort(int[] data, int low, int high) {

		if (low < high) {

			int middle = getMiddle(data, low, high); // ��list�������һ��Ϊ��

			_quickSort(data, low, middle - 1); // �Ե��ֱ���еݹ�����

			_quickSort(data, middle + 1, high); // �Ը��ֱ���еݹ�����

		}

	}

	private static int getMiddle(int[] array, int low, int high) {

		int tmp = array[low]; // ����ĵ�һ����Ϊ����

		while (low < high) {

			while (low < high && array[high] >= tmp) {

				high--;

			}

			array[low] = array[high]; // ������С�ļ�¼�Ƶ��Ͷ�

			while (low < high && array[low] <= tmp) {

				low++;

			}

			array[high] = array[low]; // �������ļ�¼�Ƶ��߶�

		}

		array[low] = tmp; // �����¼��β

		return low; // ���������λ��

	}

	// ѡ������
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
		
		System.out.println("��ѡ�����������£�");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");	
	}
	
	
	//�鲢����
	public static void mergeSort(int[] data, int left, int right){
		
		if(left<right){  
	        //�ҳ��м�����  
	        int center=(left+right)/2;  
	        //�����������еݹ�  
	        mergeSort(data,left,center);  
	        //���ұ�������еݹ�  
	        mergeSort(data,center+1,right);  
	        //�ϲ�  
	        merge(data,left,center,right);         
	    }  
		
	}
	
	public static void merge(int[] data, int left, int center, int right) {   
	    int [] tmpArr=new int[data.length];  
	    int mid=center+1;  
	    //third��¼�м����������  
	    int third=left;  
	    int tmp=left;  
	    while(left<=center&&mid<=right){  
	        //������������ȡ����С�ķ����м�����  
	        if(data[left]<=data[mid]){  
	            tmpArr[third++]=data[left++];  
	        }else{  
	            tmpArr[third++]=data[mid++];  
	        }  
	    }  
	    //ʣ�ಿ�����η����м�����  
	    while(mid<=right){  
	        tmpArr[third++]=data[mid++];  
	    }  
	    while(left<=center){  
	        tmpArr[third++]=data[left++];  
	    }  
	    //���м������е����ݸ��ƻ�ԭ����  
	    while(tmp<=right){  
	        data[tmp]=tmpArr[tmp++];  
	    }  
	    System.out.println(Arrays.toString(data));  
	} 

	/** 
	 * ��֪H[s��m]����H[s] �������ѵĶ��� 
	 * ����H[s],ʹ���Ϊ�󶥶�.�����Ե�s�����Ϊ��������ɸѡ,  
	 * 
	 * @param H�Ǵ������Ķ����� 
	 * @param s�Ǵ�����������Ԫ�ص�λ�� 
	 * @param length������ĳ���      //�����õ�Ԫ�ؾͲ����ڶѵò��֣���������������õ�Ԫ��
	 */
	public static void heapAdjust(int heap[], int s, int length){
		int tmp = heap[s];
		int child = 2*s + 1;//���ӽ���λ�á�(child+1 Ϊ��ǰ���������Һ��ӽ���λ��)
		
		while(child < length){
			// ����Һ��Ӵ�������(�ҵ��ȵ�ǰ����������ĺ��ӽ��)
			if(child+1 < length && heap[child] < heap[child+1]){
				child++;
			}
			// ����ϴ���ӽ����ڸ����
			if(heap[s] < heap[child]){
				heap[s] = heap[child]; // ��ô�ѽϴ���ӽ�������ƶ����滻���ĸ����
				s = child;  // ��������s ,������������һ������λ��
				child = 2*s+1;
			}
			// �����ǰ�������������������Һ��ӣ�����Ҫ������ֱ���˳�  
			else{
				break;
			}
			
			// ��ǰ�������Ľ��ŵ������ĺ��ӽ��λ����
			heap[s] = tmp;
		}
	}
	
	/** 
	 * ��ʼ�ѽ��е��� 
	 * ��H[0..length-1]���ɶ� 
	 * ������֮���һ��Ԫ�������е���С��Ԫ�� 
	 */  
	public static void BuildingHeap(int[] heap) {
		// ���һ���к��ӵĽڵ��λ�� i= (length -1) / 2
		for (int i = (heap.length - 1) / 2; i >= 0; --i)
			heapAdjust(heap, i, heap.length);
	}

	//������
	public static void heapSort(int[] heap){
		// ��ʼ��
		BuildingHeap(heap);
		// �����һ��Ԫ�ؿ�ʼ�����н��е���
		for (int i = heap.length - 1; i > 0; --i) {
			// �����Ѷ�Ԫ��H[0]�Ͷ������һ��Ԫ��
			int temp = heap[i];
			heap[i] = heap[0];
			heap[0] = temp;
			
			// ÿ�ν����Ѷ�Ԫ�غͶ������һ��Ԫ��֮�󣬶�Ҫ�Զѽ��е���
			heapAdjust(heap, 0, i);
		}
		
		System.out.println(Arrays.toString(heap));
	}
	
 

	public static void main(String[] args) {

		int[] data = new int[] { 49, 38, 65, 97, 76, 13, 27, 49, 55, 4 };

		System.out.println("�������������£�");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println("\n===========================================================\n");

		insertSort(copyArray(data));

		shellSort(copyArray(data));

		bubbleSort(copyArray(data));

		quickSort(copyArray(data));
		
		selectionSort(copyArray(data));
		
		System.out.println("�鲢���������£�");
		mergeSort(copyArray(data), 0, data.length-1);
		System.out.println("\n===========================================================\n");
		
		System.out.println("�����������£�");
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
	//дһ����������ĳ���
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
			//�Ӹ�λ��һ��С����������λ
			while(low < high && nums[high] > temp){
				high--;
			}
			nums[low] = nums[high];
			
			//�ӵ�λ��һ��������������λ
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
