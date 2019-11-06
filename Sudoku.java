import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;

import javafx.scene.paint.Color;
import javafx.geometry.Pos;

public class Sudoku extends Application
{

	private static int tl[][] = {
	{1, 0, 0,		0, 2, 0,		0, 0, 0},
	{0, 4, 5,		0, 0, 0,		1, 0, 7},
	{0, 0, 0,		0, 6, 5,		2, 0, 0},
			
			
	{0, 0, 0,		8, 0, 0,		0, 0, 4},
	{0, 7, 0,		0, 1, 0,		3, 0, 0},
	{5, 0, 0,		7, 0, 6,		0, 8, 0},
			
			
	{3, 2, 0,		6, 0, 0,		0, 0, 0},
	{0, 0, 8,		0, 3, 0,		0, 0, 0},
	{0, 0, 0,		0, 0, 9,		8, 3, 0}, };
	private static int tr[][] = {
	{0, 0, 0,		0, 0, 5,		2, 7, 8},
	{0, 0, 0,		0, 9, 1,		5, 0, 0},
	{5, 3, 0,		0, 0, 0,		0, 0, 4},
			
			
	{0, 0, 8,		1, 7, 0,		0, 0, 0},
	{1, 0, 6,		0, 2, 0,		0, 0, 9},
	{0, 2, 0,		0, 0, 6,		4, 0, 0},
			
			
	{3, 6, 0,		4, 0, 0,		0, 0, 0},
	{8, 7, 0,		0, 0, 9,		0, 4, 0},
	{4, 0, 0,		0, 8, 0,		0, 3, 7}, };
	private static int mi[][] = {
	{0, 0, 0,		1, 8, 0,		3, 6, 0},
	{0, 0, 0,		0, 0, 0,		8, 7, 0},
	{8, 3, 0,		7, 0, 0,		4, 0, 0},
			
			
	{0, 0, 0,		0, 5, 3,		2, 0, 0},
	{2, 0, 6,		0, 0, 0,		0, 1, 0},
	{3, 4, 0,		0, 2, 0,		0, 0, 6},
			
			
	{5, 6, 0,		3, 0, 0,		1, 8, 7},
	{9, 0, 0,		0, 7, 0,		6, 0, 0},
	{7, 0, 0,		0, 0, 6,		5, 0, 0}, };
	private static int bl[][] = {
	{0, 3, 0,		0, 0, 0,		5, 6, 0},
	{0, 0, 0,		6, 0, 5,		9, 0, 0},
	{0, 1, 5,		0, 4, 0,		7, 0, 0},
			
			
	{0, 0, 1,		0, 0, 0,		0, 3, 0},
	{0, 6, 0,		8, 0, 0,		0, 0, 0},
	{8, 0, 0,		3, 2, 0,		4, 0, 0},
			
			
	{0, 0, 0,		2, 9, 8,		1, 0, 0},
	{0, 5, 0,		0, 7, 0,		0, 0, 0},
	{9, 7, 0,		0, 0, 1,		3, 0, 8}, };
	private static int br[][] = {
	{1, 8, 7,		2, 0, 9,		0, 0, 0},
	{6, 0, 0,		0, 5, 8,		0, 0, 1},
	{5, 0, 0,		0, 0, 0,		0, 7, 0},
			
			
	{9, 1, 0,		5, 0, 0,		6, 0, 0},
	{0, 6, 0,		0, 0, 0,		0, 0, 8},
	{0, 0, 0,		0, 0, 1,		0, 3, 0},
			
			
	{0, 9, 0,		3, 4, 0,		0, 0, 0},
	{4, 5, 0,		0, 0, 0,		0, 0, 7},
	{8, 0, 3,		0, 6, 0,		2, 0, 4}, };
	
	static int attempts = 0;
	Board board;
	
	static int[][] puzzle[] = {tl, tr, bl, br, mi};
	
		// static int[][] puzzles[] = {tl, tr, bl, br, mi};
		// static int puzzles[][][] = {tl, tr, bl, br, mi};
		// static int[][][] puzzles = {tl, tr, bl, br, mi};
		// static int[] puzzles[][] = {tl, tr, bl, br, mi};
		// int[][] puzzle;
		// puzzle = puzzles[which];
	
	public void start(Stage stage)
	{
		
		// int section = 0;
		// int row = 6;
		// int col = 6;
		// int guess = 53;
		
		// puzzle[0][2-1][2-1] = 42;
		// puzzle[0][2-1][3-1] = 42;
		// puzzle[0][3-1][2-1] = 42;
		// puzzle[0][3-1][3-1] = 42;
		
		// puzzle[4][0][5] = 53;
		
		// guess(section * 81 + row * 9 + col, guess);
		// attempts = 0;
		// printSud(puzzle);
		// guess(0);
		
		// create sudoku board
		board = new Board(this, puzzle);
		board.setAlignment(Pos.CENTER);
		
		
		Scene scene = new Scene(board, 31*21, 31*21);
		stage.setScene(scene);		
		stage.show();
		
	
	}
		
	static boolean alrdy = false;	
	
	public static boolean guess(int pos) {
		
		// if(pos == 64 && !alrdy) {
			// return true;
			// alrdy = true;
			// printSud(puzzle);
		// }
		
		// calculate section, row, column, and square of cell
		int sec = pos / 81;
		int row = pos % 81 / 9;
		int col = pos % 81 % 9;
		int sqr = 3 * (row / 3) + (col / 3);
		
		// skip over presolved cells
		while((pos < 81*5) && (puzzle[sec][row][col] != 0)) {
			pos++;
			sec = pos / 81;
			row = pos % 81 / 9;
			col = pos % 81 % 9;
			sqr = 3 * (row / 3) + (col / 3);
		}
		
		// spittballing (don't know if needed w/ above loop)
		// if(sec == 5 && sqr == 0)
			// pos += 3;
		
		// if it's the last cell, print out how many moves it took and then return true
		if(pos >= 81*5) {
			System.out.println(attempts);
			return true;
		}
		
		// increase attempts
		attempts++;

		// precaution to check on progress of solution if it's taking awhile
		if(attempts == 20000000)
			return true;
		
		// loop through possible values for cell (1-9)
		for(int n = 1; n <= 9; n++) {
			
			int ans = n;
			
			// cell 1 (1-9) cell 2 (9-1) cell 3 (1-9)
			// if(pos % 2 == 0)
				// ans = 10 - ans;
				
			// cell 1 (1 - 9) cell 2 (2-9, 1) cell 3 (3-9, 1-2) cell 4 (4-9, 1-3)...
			// cool result with only row check active from isValid
			// mod 9 has same result as no mod
			// ans = (n + pos - 1) % 9 + 1;
			// ans = (n + (pos % 9) - 1) % 9 + 1;
			
			// check result using "java Sudoku > output"
			// System.out.println(ans);
			
			// cell 1 (1 - 9) cell 2 (2-9, 1) cell 3 (3-9, 1-2) cell 4 (1-9)...
			// mod 3 w/ only square check makes all squares the same
			// ans = (n + (pos % 3) - 1) % 9 + 1;
			
			if(isValid(sec, row, col, sqr, ans)) {
				
				// assign number to cell
				puzzle[sec][row][col] = ans;
				// don't know if this is needed (if we were to do it another way (i.e., pos = sec == 4 && (sqr == 0 || sqr == 2 || sqr == 6 || sqr == 8) ? pos + 1 : pos;))
				// using this, the while loop skips over these cells since they will have a nonzero value
				if(sec == 0 && sqr == 8)
					puzzle[4][row - 6][col - 6] = ans;
				else if(sec == 1 && sqr == 6)
					puzzle[4][row - 6][col + 6] = ans;
				else if(sec == 2 && sqr == 2)
					puzzle[4][row + 6][col - 6] = ans;
				else if(sec == 3 && sqr == 0)
					puzzle[4][row + 6][col + 6] = ans;
				
				// reset cell and choose a new number if the next cell couldn't find a valid number
				if(!guess(pos + 1)) {
					puzzle[sec][row][col] = 0;
					if(sec == 0 && sqr == 8)
						puzzle[4][row - 6][col - 6] = 0;
					else if(sec == 1 && sqr == 6)
						puzzle[4][row - 6][col + 6] = 0;
					else if(sec == 2 && sqr == 2)
						puzzle[4][row + 6][col - 6] = 0;
					else if(sec == 3 && sqr == 0)
						puzzle[4][row + 6][col + 6] = 0;
					continue;
				}

				// return true if subsequent cells are valid
				return true;
				
			}
			
		}
		
		// return false if every number (1-9) wasn't valid
		return false;

	}
	
	// method to test input and isValid method
	public static boolean guess(int pos, int n) {
		
		// calculate section, row, column, and square of cell
		int sec = pos / 81;
		int row = pos % 81 / 9;
		int col = pos % 81 % 9;
		int sqr = 3 * (row / 3) + (col / 3);
		
		// skip over presolved cells
		while((pos < 81*5) && (puzzle[sec][row][col] != 0)) {
			pos++;
			sec = pos / 81;
			row = pos % 81 / 9;
			col = pos % 81 % 9;
			sqr = 3 * (row / 3) + (col / 3);
		}
		
		// if it's the last cell, print out how many moves it took and then return true
		if(pos >= 81*5) {
			System.out.println(attempts);
			return true;
		}
		
		// increase attempts
		attempts++;

		// spittballing
		// if(sec == 5 && sqr == 0)
			// pos += 3;
		
		
		if(isValid(sec, row, col, sqr, n)) {
			puzzle[sec][row][col] = n;
			// don't know if this is needed
			// if(sec == 0 && sqr == 8)
				// puzzle[4][row - 6][col - 6] = n;
			// if(sec == 1 && sqr == 6)
				// puzzle[4][row - 6][col + 6] = n;
			// if(sec == 2 && sqr == 2)
				// puzzle[4][row + 6][col - 6] = n;
			// if(sec == 3 && sqr == 0)
				// puzzle[4][row + 6][col + 6] = n;
		}
		else {
			puzzle[sec][row][col] = -1;
		}
		
		// puzzle[sec][row][col] = sqr;
		// guess(pos + 1, n);
		
		// if(isValid(sec, row, col, n))
			// puzzle[sec][row][col] = n;
		// else
			// puzzle[sec][row][col] = -1;
		
		// if(isValid(pos/9, pos%9, n))
			// puzzle[pos / 9][pos % 9] = n;
		// else
			// puzzle[pos / 9][pos % 9] = -1;
			

		return false;
	}
	
	// check if number is already in the row, column, or square
	public static boolean isValid(int sec, int row, int col, int sqr, int n) {
		
		boolean check_row = true;
		boolean check_col = true;
		boolean check_sqr = true;
		
		// check_row = false;
		// check_col = false;
		// check_sqr = false;
		
		// check if number is already in the square
		if(check_sqr) {
			
			for(int i = 0; i < 3; i++) {
				
				for(int j = 0; j < 3; j++) {
					
					// determine the 9 cells that makeup the square which the current cell is located
					int r = row / 3 * 3 + i;
					int c = col / 3 * 3 + j;
					
					// skip current cell (shouldn't be needed)
					if(r == row && c == col)
						continue;
					
					// return false if number is already in square
					if(puzzle[sec][r][c] == n)
						return false;
					
				}
				
			}
		}
		
		// check if number is already in the column
		if(check_col) {
			
			for(int i = 0; i < puzzle[sec].length; i++) {
				
				// skip current column (shouldn't be needed since value isn't assigned to cell until proven valid)
				if(i == row)
					continue;
				
				// return false if number is already in column
				if(puzzle[sec][i][col] == n)
					return false;
				
				// spittballing - [i][col] might not be right
				if((sec == 0 && sqr == 8) && (puzzle[4][i][col - 6] == n))
					return false;
				if((sec == 1 && sqr == 6) && (puzzle[4][i][col + 6] == n))
					return false;
				if((sec == 2 && sqr == 2) && (puzzle[4][i][col - 6] == n))
					return false;
				if((sec == 3 && sqr == 0) && (puzzle[4][i][col + 6] == n))
					return false;
				
				if(i == 0 || i == 1 || i == 2) {
					if((sec == 0 && sqr == 8) && (puzzle[2][i][col] == n))
						return false;
					if((sec == 1 && sqr == 6) && (puzzle[3][i][col] == n))
						return false;
				}
				
				if(i == 6 || i == 7 || i == 8) {
					if((sec == 2 && sqr == 2) && (puzzle[0][i][col] == n))
						return false;
					if((sec == 3 && sqr == 0) && (puzzle[1][i][col] == n))
						return false;
				}
					
				// don't know if needed
				if(sec == 4) {
					
					if(sqr == 0 && puzzle[0][i][col + 6] == n)
						return false;
					
					if(sqr == 2 && puzzle[1][i][col - 6] == n)
						return false;
					
					if(sqr == 6 && puzzle[2][i][col + 6] == n)
						return false;
					
					if(sqr == 8 && puzzle[3][i][col - 6] == n)
						return false;
					
				}
					
			}
		}
		
		// check if number is already in the row
		if(check_row) {
			
			for(int i = 0; i < puzzle[sec][row].length; i++) {
		
				// skip current row (shouldn't be needed)
				if(i == col)
					continue;
				
				// return false if number is already in row
				if(puzzle[sec][row][i] == n)
					return false;
				
				// spittballing
				if((sec == 0 && sqr == 8) && (puzzle[4][row - 6][i] == n))
					return false;
				if((sec == 1 && sqr == 6) && (puzzle[4][row - 6][i] == n))
					return false;
				if((sec == 2 && sqr == 2) && (puzzle[4][row + 6][i] == n))
					return false;
				if((sec == 3 && sqr == 0) && (puzzle[4][row + 6][i] == n))
					return false;
					
					
				if(i == 0 || i == 1 || i == 2) {
					if((sec == 0 && sqr == 8) && (puzzle[1][row][i] == n))
						return false;
					if((sec == 2 && sqr == 2) && (puzzle[3][row][i] == n))
						return false;
				}
				
				if(i == 6 || i == 7 || i == 8) {
					if((sec == 1 && sqr == 6) && (puzzle[0][row][i] == n))
						return false;
					if((sec == 3 && sqr == 0) && (puzzle[2][row][i] == n))
						return false;
				}
				
				
				// don't know if needed
				if(sec == 4) {
					
					if(sqr == 0 && puzzle[0][row + 6][i] == n)
						return false;
					
					if(sqr == 2 && puzzle[1][row + 6][i] == n)
						return false;
					
					if(sqr == 6 && puzzle[2][row - 6][i] == n)
						return false;
					
					if(sqr == 8 && puzzle[3][row - 6][i] == n)
						return false;
					
				}
				
			}
		}
		
		// return true if number wasn't found in the row, column, and square
		return true;
		
	}

	public void solve() {
		
		attempts = 0;
		
		puzzle = board.getPuzzle();
		
		// printSud(puzzle);
		// guess(0);
		if(guess(0))
			board.showSolution(puzzle);
		else
			System.out.println("NO SOLUTION: " + attempts);
		
	}	
	
	public static void printSud(int[][][] puzzle)
	{
		
		for(int sec = 0; sec < puzzle.length; sec++)
			for(int row = 0; row < puzzle[sec].length; row++)
				for(int col = 0; col < puzzle[sec][row].length; col++)
					System.out.print(puzzle[sec][row][col]+"-");
		
	}
	
}