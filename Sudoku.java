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
	// private static int puzzle[][] = {
// /*		 0  1  2		3  4  5			6  7  8
// /*0*/	{0, 1, 0,		4, 3, 0,		0, 0, 6},
// /*1*/	{0, 0, 8,		0, 6, 1,		0, 0, 0},
// /*2*/	{0, 4, 0,		0, 0, 2,		0, 0, 0},
		
		
// /*3*/	{2, 0, 0,		0, 0, 0,		4, 5, 0},
// /*4*/	{0, 9, 0,		0, 0, 0,		0, 3, 0},
// /*5*/	{0, 3, 7,		0, 0, 0,		0, 0, 8},
		
		
// /*6*/	{0, 0, 0,		8, 0, 0,		0, 6, 0},
// /*7*/	{0, 0, 0,		9, 1, 0,		3, 0, 0},
// /*8*/	{8, 0, 0,		0, 4, 3,		0, 2, 0}, };

	// private static int puzzle[][] = {
// /*		 0  1  2		3  4  5			6  7  8
// /*0*/	{0, 0, 0,		0, 0, 0,		0, 0, 0},
// /*1*/	{0, 0, 0,		0, 0, 0,		0, 0, 0},
// /*2*/	{0, 0, 0,		0, 0, 0,		0, 0, 0},
		
		
// /*3*/	{0, 0, 0,		0, 0, 0,		0, 0, 0},
// /*4*/	{0, 0, 0,		0, 0, 0,		0, 0, 0},
// /*5*/	{0, 0, 0,		0, 0, 0,		0, 0, 0},
		
		
// /*6*/	{0, 0, 0,		0, 0, 0,		0, 0, 0},
// /*7*/	{0, 0, 0,		0, 0, 0,		0, 0, 0},
// /*8*/	{0, 0, 0,		0, 0, 0,		0, 0, 0}, };

	// private static int puzzle[][] = {
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
			
			
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
			
			
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
	// {0, 0, 0,		0, 0, 0,		0, 0, 0}, };
	// private static int puzzle[][] = {
	// {0, 3, 6,		0, 0, 9,		0, 0, 0},
	// {1, 4, 7,		0, 0, 0,		0, 0, 0},
	// {2, 5, 8,		0, 0, 0,		0, 0, 0},
			
			
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
			
			
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
	// {0, 0, 0,		0, 0, 0,		0, 0, 0},
	// {0, 0, 0,		0, 0, 0,		0, 0, 0}, };

	private static int puzzle[][] = {
/*		 0  1  2		3  4  5			6  7  8
/*0*/	{0, 0, 0,		0, 0, 9,		0, 0, 7},
/*1*/	{0, 9, 0,		0, 5, 0,		0, 2, 0},
/*2*/	{0, 0, 0,		0, 0, 3,		4, 0, 0},
		
		
/*3*/	{0, 0, 0,		0, 0, 0,		6, 0, 1},
/*4*/	{0, 3, 0,		0, 0, 0,		0, 5, 0},
/*5*/	{6, 0, 4,		0, 0, 0,		0, 0, 0},
		
		
/*6*/	{0, 0, 1,		8, 0, 0,		0, 0, 0},
/*7*/	{0, 2, 0,		0, 6, 0,		0, 9, 0},
/*8*/	{7, 0, 0,		4, 0, 0,		0, 0, 0}, };
	
	static int attempts = 0;
	Board board;
	
	public void start(Stage stage)
	{
		
		
		
		// int row = 0;
		// int col = 3;
		// int guess = 46;
		
		// guess(row * 9 + col, guess);
		
		// start solving from 1st cell
		// guess(0);
		
		// create sudoku board
		board = new Board(this, puzzle);
		board.setAlignment(Pos.CENTER);
		
		
		Scene scene = new Scene(board, 41*9, 41*9);
		stage.setScene(scene);		
		stage.show();
		
	}
	/**/
	public static boolean guess(int pos)
	{

		// skip over presolved cells
		while((pos < 81) && (puzzle[pos / 9][pos % 9] != 0))
			pos++;
		
		// if it's the last cell, print out how many moves it took and then return true
		if(pos >= 81) {
			System.out.println(attempts);
			return true;
		}
	
		// increase attempts
		attempts++;
		
		// precaution to check on progress of solution if it's taking awhile
		// if(attempts == 1000)
			// return true;
		
		// loop through possible values for cell (1-9)
		for(int n = 1; n <= 9; n++) {
			
			int ans = n;
			
			// cell 1 (1-9) cell 2 (9-1) cell 3 (1-9)
			// if(pos % 2 == 0)
				// ans = 10 - ans;
				
			// cell 1 (1 - 9) cell 2 (2-9, 1) cell 3 (3-9, 1-2) cell 4 (4-9, 1-3)...
			// cool result with only row check active from isValid
			// mod 9 has same result as no mod
			ans = (n + pos - 1) % 9 + 1;
			// ans = (n + (pos % 9) - 1) % 9 + 1;
			
			// check result using "java Sudoku > output"
			// System.out.println(ans);
			
			// cell 1 (1 - 9) cell 2 (2-9, 1) cell 3 (3-9, 1-2) cell 4 (1-9)...
			// mod 3 w/ only square check makes all squares the same
			// ans = (n + (pos % 3) - 1) % 9 + 1;
			
			if(isValid(pos/9, pos%9, ans)) {
				
				// assign number to cell
				puzzle[pos / 9][pos % 9] = ans;
				
				// reset cell and choose a new number if the next cell couldn't find a valid number
				if(!guess(pos + 1)) {
					puzzle[pos / 9][pos % 9] = 0;
					continue;
				}

				// return true if subsequent cells are valid
				return true;
				
			}
			
		}
		
		// return false if every number (1-9) wasn't valid
		return false;
		
	}
	/*/
	public static void guess(int pos, int n)
	{
		
		// while(n <= 9) {
			
			if(isValid(pos/9, pos%9, n))
				puzzle[pos / 9][pos % 9] = n;
			else
				puzzle[pos / 9][pos % 9] = -1;
			
			// pos++;
			// n++;
			
			// if(pos <= 80)
				// guess(pos, n);
			
			
		// }
		
		
	}
	/**/
	
	// check if number is already in the row, column, or square
	public static boolean isValid(int row, int col, int n)
	{
		
		boolean check_row = true;
		boolean check_col = true;
		boolean check_sqr = true;
		
		// check_row = false;
		// check_col = false;
		// check_sqr = false;
		
		if(check_col) {
			// check if number is already in the column
			for(int i = 0; i < puzzle.length; i++) {
				
				// skip current column (shouldn't be needed since value isn't assigned to cell until proven valid)
				if(i == row)
					continue;
				
				// return false if number is already in column
				if(puzzle[i][col] == n)
					return false;
				
			}
		}
		
		if(check_row) {
			// check if number is already in the row
			for(int i = 0; i < puzzle[row].length; i++) {
		
				// skip current row (shouldn't be needed)
				if(i == col)
					continue;
				
				// return false if number is already in row
				if(puzzle[row][i] == n)
					return false;
				
			}
		}
		
		if(check_sqr) {
			// check if number is already in the square
			for(int i = 0; i < 3; i++) {
				
				for(int j = 0; j < 3; j++) {
					
					// determine the 9 cells that makeup the square which the current cell is located
					int r = row / 3 * 3 + i;
					int c = col / 3 * 3 + j;
					
					// skip current cell (shouldn't be needed)
					if(r == row && c == col)
						continue;
					
					// return false if number is already in square
					if(puzzle[r][c] == n)
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
		if(guess(0))
			board.showSolution(puzzle);
		else
			System.out.println("NO SOLUTION: " + attempts);
		
	}
	
}