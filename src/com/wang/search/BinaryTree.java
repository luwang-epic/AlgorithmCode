package com.wang.search;

import java.util.Stack;

/**  ������
 * ������� ������� ������� �����Ͷ�ջʵ��
 *
 */
public class BinaryTree<T> {

	final private Node<T> root;

	public static class Node<T> {
		protected Node<T> lchild;
		protected T data;
		// protected Node parent;
		protected Node<T> rchild;

		public Node(T t) {
			lchild = null;
			rchild = null;
			this.data = t;
		}

		@Override
		public String toString() {
			return data.toString();
		}

	}

	public Node<T> getRoot(){
		return this.root;
	}
	
	public BinaryTree(Node<T> root) {
		this.root = root;
	}

	public Node<T> insertRChild(Node<T> parent, Node<T> node) {
		parent.rchild = node;
		return node;
	}

	public Node<T> insertLChild(Node<T> parent, Node<T> node) {
		parent.lchild = node;
		return node;
	}	
	
	
	private static void visitNode(Node<?> node){
		System.out.print(node + "  ");
	}
	
	/**
	 *  ��������ĵ���ʵ��
	 *  1.�ȱ������ڵ�
	 *  2.�ٱ���������
	 *  3.������������
	 *  
	 *	�ڵ����������ҲҪ��������������
	 */
	public static void preOrderTraverse(Node<?> root) {
		//�ȷ��ʸ��ڵ�
		visitNode(root);
				
		//�ٷ���������
		if(root.lchild != null)
			preOrderTraverse(root.lchild);
		
		//������������
		if(root.rchild != null)
			preOrderTraverse(root.rchild);
	}
	
	
	/**
	 *  ��������Ķ�ջʵ��ʵ��
	 */
	public static void preOrderTraverseByStack(Node<?> root) {
		Stack<Node<?>> stack = new Stack<Node<?>>();
		stack.push(root);
		
		while(!stack.isEmpty()){
			Node<?> node = stack.peek();
			while(node != null){
				//�ȷ��ʸ��ڵ�
				visitNode(node);
				
				//�ٷ���������
				node = node.lchild;
				stack.push(node);
			}
			
			//��ָ���ջ
			stack.pop();
			
			//�ڷ���������
			if(!stack.isEmpty()){
				node = stack.pop();
				stack.push(node.rchild);
			}
		}
	}
	
	
	
	/**
	 *  ��������ĵ���ʵ��
	 *  1.�ȱ���������
	 *  2.�ٱ������ڵ�
	 *  3.������������
	 *  
	 *	�ڵ����������ҲҪ��������������
	 */
	public static void inOrderTraverse(Node<?> root) {		
		//�ȷ���������
		if (root.lchild != null)
			inOrderTraverse(root.lchild);

		// �ٷ��ʸ��ڵ�
		visitNode(root);
		
		// ������������
		if (root.rchild != null)
			inOrderTraverse(root.rchild);
	}
	
	/**
	 *  ��������Ķ�ջʵ��ʵ��
	 */
	public static void inOrderTraverseByStack(Node<?> root) {
		Stack<Node<?>> stack = new Stack<Node<?>>();
		stack.push(root);
		
		while(!stack.isEmpty()){
			//�ȷ���������
			Node<?> node;
			while((node=stack.peek()) != null){
				stack.push(node.lchild);
			}
			//��ָ����ջ
			stack.pop();
			
			if(!stack.isEmpty()){
				//���ʸ��ڵ�
				node = stack.pop();
				visitNode(node);
				
				//����������
				stack.push(node.rchild);
			}
		}

	}
	
	
	
	/**
	 *  ��������ĵ���ʵ��
	 *  1.�ȱ���������
	 *  2.�ٱ���������
	 *  3.���������ڵ�
	 *  
	 *	�ڵ����������ҲҪ��������������
	 */
	private static void postOrderTraverse(Node<?> root) {					
		// �ȷ���������
		if (root.lchild != null)
			postOrderTraverse(root.lchild);

		// �ٷ���������
		if (root.rchild != null)
			postOrderTraverse(root.rchild);
		
		// �����ʸ��ڵ�
		visitNode(root);
	}
	
	
	/**
	 *  ��������Ķ�ջʵ��ʵ��
	 */
	public static void postOrderTraverseByStack(Node<?> root) {
		Stack<Node<?>> stack = new Stack<Node<?>>();
		stack.push(root);
		
		Node<?> node = stack.pop();
		Node<?> prev = node;
		while(node != null || !stack.isEmpty()){
			//�ȷ���������
			while(node != null){
				stack.push(node);
				node = node.lchild;
			}
			
			//�ٷ���������
			if(!stack.isEmpty()){
				node = stack.peek().rchild;
				
				if(node == null || node == prev){
					node = stack.pop();
					//���ʸ��ڵ�
					visitNode(node);
					prev = node;
					node = null;
				}
			}		
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Node<String> root = new Node<String>("A");
		BinaryTree<String> tree = new BinaryTree<String>(root);
		
		/* �������µ���
		 * 				A
		 * 		B				C
		 * 	D		E		F
		 * 
		 */
		
		Node<String> temp = tree.insertLChild(root, new Node<String>("B"));
		tree.insertLChild(temp, new Node<String>("D"));
		tree.insertRChild(temp, new Node<String>("E"));
		temp = tree.insertRChild(root, new Node<String>("C"));
		tree.insertLChild(temp, new Node<String>("F"));
		
		System.out.println("********��������ĵ���ʵ��**************");
		BinaryTree.preOrderTraverse(root);
		System.out.println("\n********��������ĵ���ʵ��**************");
		BinaryTree.inOrderTraverse(root);
		System.out.println("\n********��������ĵ���ʵ��**************");
		BinaryTree.postOrderTraverse(root);
		
		System.out.println("\n********��������Ķ�ջʵ��**************");
		BinaryTree.preOrderTraverseByStack(root);
		System.out.println("\n********��������Ķ�ջʵ��**************");
		BinaryTree.inOrderTraverseByStack(root);
		System.out.println("\n********��������Ķ�ջʵ��**************");
		BinaryTree.postOrderTraverseByStack(root);
		
	}

}
