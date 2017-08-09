package com.wang.bobo.test;

public class Test {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {	
        if(t1==null && t2==null) {
        	return null;
        } else if(t1==null && t2!=null) {
        	return t2;
        } else if(t1!=null && t2==null) {
        	return t1;
        }else {
        	TreeNode megeTree = null;
        	megeTree = new TreeNode(t1.val + t2.val);
        	megeTree.left = mergeTrees(t1.left, t2.left);
        	megeTree.right = mergeTrees(t1.right, t2.right);
        	return megeTree;
        }
    }
	
	public static int hammingDistance(int x, int y) {
        int result = x ^ y;
        int dis = 0;
        int pow = 1;
        for(int i=0; i<31; i++) {
        	if((pow&result) != 0)
        		dis++;
        	pow = pow * 2;
        }
        return dis;
    }
	
	public static void main(String[] args) {
		int dis = hammingDistance(1577962638,1727613287);
		System.out.println(dis);
	}

}
