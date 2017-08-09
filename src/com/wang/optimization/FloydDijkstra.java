package com.wang.optimization;



/**   Floyd��Dijkstra�㷨   �Աȣ� 
 * 1��������ζ�ĳ����������Dijkstra�㷨������Floyd�㷨��ȣ��ܶ�·���ͽ���������ظ��ģ�
 * 			��Ȼ���Ӷ���ͬ���������������˺ܶ�.
 * 2����Ϊ��Ҫ���ǣ�Dijkstra�㷨ʹ�õ�ǰ����ͼ��·�����ȱ�����ڵ���0��
 * 			����Floyd�㷨�����Ҫ��û���ܺ�С��0�Ļ�·�Ϳ�����
 * 
 * ���Floyd �㷨Ӧ�÷�Χ��Dijkstra�㷨Ҫ�㡣
 */
public class FloydDijkstra {

	private static final int INF = Integer.MAX_VALUE;   // ���ֵ   ��ʾ ��·��ͨ
	
	/*
	 * floyd���·����
	 * ����ͳ��ͼ�и������������·����
	 *
	 * ����˵����
	 *     path -- ·����path[i][j]=k��ʾ��"����i"��"����j"�����·���ᾭ������k��
	 *     dist -- �������顣����dist[i][j]=sum��ʾ��"����i"��"����j"�����·���ĳ�����sum��
	 *     weight -- �ڽӾ���
	 */
	public static void floyd(int[][] weight) {	
		//��¼���·��
		int[][] path = new int[weight.length][weight.length];
		//��¼���·������
		int[][] dist = new int[weight.length][weight.length];
		
		//��ʼ��
		for(int i=0; i<weight.length; i++){
			for(int j=0; j<weight.length; j++){
				dist[i][j] = weight[i][j]; // "����i"��"����j"��·������Ϊ"i��j��Ȩֵ"
				if(weight[i][j] < INF){
					path[i][j] = j; // "����i"��"����j"�����·���Ǿ�������j��
				}else
					path[i][j] = -1;
			}
		}
		
		
		//���£��������·��
		for(int k=0; k<weight.length; k++){  //i��j�����·���Ƿ񾭹�k  ����������������ѭ��
			for(int i=0; i<weight.length; i++){
				for(int j=0; j<weight.length; j++){
					 // ��������±�Ϊk����·����ԭ�����·�����̣������dist[i][j]��path[i][j]
					if(dist[i][k]!=INF && dist[k][j]!=INF 
							&& dist[i][j] > dist[i][k]+dist[k][j]){
						// "i��j���·��"��Ӧ��·��������k
						path[i][j]=path[i][k];
						// "i��j���·��"��Ӧ��ֵ�裬Ϊ��С��һ��(������k)
						dist[i][j] = dist[i][k]+dist[k][j];
					}

				}
			}
		}
		
	    // ��ӡfloyd���·���Ľ��
	    System.out.printf("floyd: \n");
	    for (int i = 0; i < weight.length; i++) {
	        for (int j = 0; j < weight.length; j++)
	            System.out.printf("%2d  ", dist[i][j]);
	        System.out.printf("\n");
	    }
	    
	    System.out.printf("path: \n");
	    for (int i = 0; i < weight.length; i++) {
	        for (int j = 0; j < weight.length; j++)
	            System.out.printf("%2d  ", path[i][j]);
	        System.out.printf("\n");
	    }
	    
	    System.out.println("+++++++++++++++++++++++++++++++++++");  
	    
	    
	    //��ӡ���·��
	    for (int i = 0; i < weight.length; i++) {  
	         for (int j = 0; j < weight.length; j++) {  
	             System.out.println("���i=" + i + "��j=" + j + "���·����");  
	             System.out.print(i);
	             int k = path[i][j];  
	             if (k == -1) {  
	                 System.out.println("û�����·��");  
	             } else {  
	                 System.out.print(" " + k);  
	                 while (k != j) {  
	                     k = path[k][j];  
	                     System.out.print(" " + k);  
	                 }  
	                 
	                 System.out.println();  
	             }  
	         }  
	     } 
	    
	}
	
	
	// ע�⣺�ú�����ı��ڽӾ���weight��ֵ����˺��治����ʹ�ø��ٽ����������һ���ڽӾ��󴫸��ú���
	public static int[] dijkstra(int[][] weight, int start) {
		// ����һ������ͼ��Ȩ�ؾ��󣬺�һ�������start����0��ţ�������������У�
		// ����һ��int[] ���飬��ʾ��start���������·������
		int n = weight.length; // �������
		int[] shortPath = new int[n]; // ����start��������������·��
		String[] path = new String[n]; // ����start�������������·�����ַ�����ʾ
		for (int i = 0; i < n; i++)
			path[i] = new String(start + "-->" + i);
		int[] visited = new int[n]; // ��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ�����

		// ��ʼ������һ�������Ѿ����
		shortPath[start] = 0;
		visited[start] = 1;

		for (int count = 1; count < n; count++) { // Ҫ����n-1������
			int k = -1; // ѡ��һ�������ʼ����start�����δ��Ƕ���
			int dmin = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && weight[start][i] < dmin) {
					dmin = weight[start][i];
					k = i;
				}
			}

			// ����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin
			shortPath[k] = dmin;
			visited[k] = 1;

			// ��kΪ�м�㣬������start��δ���ʸ���ľ���
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && weight[start][k] != INF && weight[k][i]!=INF
						&& weight[start][k] + weight[k][i] < weight[start][i]) {
					weight[start][i] = weight[start][k] + weight[k][i];
					path[i] = path[k] + "-->" + i;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.println("��" + start + "������" + i + "�����·��Ϊ��" + path[i]);
		}
		System.out.println("=====================================");
		return shortPath;
	}
	
	
	
	
	public static void main(String[] args) {
		int[][] data = new int[][]{
			{0,12,INF,INF,INF,16,14},
			{12,0,10,INF,INF,7,INF},
			{INF,10,0,3,5,6,INF},
			{INF,INF,3,0,4,INF,INF},
			{INF,INF,5,4,0,2,8},
			{16,7,6,INF,2,0,9},
			{14,INF,INF,INF,8,9,0}
			};
						
			floyd(data);	
			
			
			int start = 0;
			int[] shortPath = dijkstra(data, start);

			for (int i = 0; i < shortPath.length; i++)
				System.out.println("��" + start + "������" + i + "����̾���Ϊ��" + shortPath[i]);
			
	}

}
