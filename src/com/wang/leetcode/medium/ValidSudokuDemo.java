package com.wang.leetcode.medium;

public class ValidSudokuDemo {

	/**
	 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

		The Sudoku board could be partially filled, 
		where empty cells are filled with the character '.'.

		A partially filled sudoku which is valid.
		
		Note:
		A valid Sudoku board (partially filled) is not necessarily solvable. 
		Only the filled cells need to be validated.
	 * 
	 */
	public class Solution {
	    public boolean isValidSudoku(char[][] board) {
	        return false;
	    }
	}
	
	public static void main(String[] args) {
		ValidSudokuDemo demo = new ValidSudokuDemo();
		Solution solution = demo.new Solution();
		
		char[][] board = new char[][]{};
		
		System.out.println(solution.isValidSudoku(board));
	}

}
