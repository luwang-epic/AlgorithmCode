package com.wang.search;

public class Search {

	public final static int MAXSIZE = 20;

	public static void main(String[] args) {
		int[] f = fibonacci();
		for (int i : f) {
			System.out.print(i + " ");
		}
		System.out.println();

		int[] data = { 1, 5, 15, 22, 25, 31, 39, 42, 47, 49, 59, 68, 88 };

		int search = 39;
		int position = fibonacciSearch(data, search);
		System.out.println("ֵ" + search + "��Ԫ��λ��Ϊ��" + position);
	}

	/**
	 * 쳲���������
	 * 
	 * @return
	 */
	public static int[] fibonacci() {
		int[] f = new int[20];
		int i = 0;
		f[0] = 1;
		f[1] = 1;
		for (i = 2; i < MAXSIZE; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}

	public static int fibonacciSearch(int[] data, int key) {
		int low = 0;
		int high = data.length - 1;
		int mid = 0;

		// 쳲������ָ���ֵ�±�
		int k = 0;

		// ����Ԫ�ظ���
		int i = 0;

		// ��ȡ쳲���������
		int[] f = fibonacci();

		// ��ȡ쳲������ָ���ֵ�±�
		while (data.length > f[k] - 1) {
			k++;
		}

		// ������ʱ����
		int[] temp = new int[f[k] - 1];
		for (int j = 0; j < data.length; j++) {
			temp[j] = data[j];
		}

		// ���в�����f[k]��Ԫ��
		// �����Ԫ��ֵΪ���һ��Ԫ�ص�ֵ
		for (i = data.length; i < f[k] - 1; i++) {
			temp[i] = temp[high];
		}

		for (int j : temp) {
			System.out.print(j + " ");
		}
		System.out.println();

		while (low <= high) {
			// low����ʼλ��
			// ǰ�벿����f[k-1]��Ԫ�أ������±��0��ʼ
			// ��-1 ��ȡ �ƽ�ָ�λ��Ԫ�ص��±�
			mid = low + f[k - 1] - 1;

			if (temp[mid] > key) {
				// ����ǰ�벿�֣���λָ���ƶ�
				high = mid - 1;
				// ��ȫ��Ԫ�أ� = ��ǰ�벿�֣�+����벿�֣�
				// f[k] = f[k-1] + f[k-1]
				// ��Ϊǰ�벿����f[k-1]��Ԫ�أ����� k = k-1
				k = k - 1;
			} else if (temp[mid] < key) {
				// ���Һ�벿�֣���λָ���ƶ�
				low = mid + 1;
				// ��ȫ��Ԫ�أ� = ��ǰ�벿�֣�+����벿�֣�
				// f[k] = f[k-1] + f[k-1]
				// ��Ϊ��벿����f[k-1]��Ԫ�أ����� k = k-2
				k = k - 2;
			} else {
				// ���Ϊ�����ҵ���Ӧ��λ��
				if (mid <= high) {
					return mid;
				} else {
					// ������������ǲ��ҵ������Ԫ��
					// �������Ԫ����highλ�õ�Ԫ��һ��
					return high;
				}
			}
		}
		return -1;
	}

}
