import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;

import javafx.scene.paint.Color;


import javafx.scene.input.KeyCode;


public class Board extends GridPane
{
	
	Cell[][][] cell;
	Sudoku sudoku;
	
	public Board(Sudoku sudoku, int puzzle[][][])
	{
		this.sudoku = sudoku;
		cell = new Cell[puzzle.length][puzzle[0].length][puzzle[0][0].length];
		
		for(int sec = 0; sec < puzzle.length; sec++) {
		// for(int sec = 4; sec >= 0; sec--) {
			
			for(int row = 0; row < puzzle[sec].length; row++) {
				
				for(int col = 0; col < puzzle[sec][row].length; col++) {
					
					cell[sec][row][col] = new Cell(puzzle[sec][row][col], sec, 3 * (row / 3) + (col / 3));
					
					// if(sec == 0)
						// cell[sec][row][col].setColor(Color.rgb(200, 255, 200));
					// else 
					if(  ((col / 3 == 0 || col / 3 == 2) && (row / 3 == 0 || row / 3 == 2)) || ((col / 3 == 1) && (row / 3 == 1)) )
						cell[sec][row][col].setColor(Color.rgb(255, 255, 200));
					else
						cell[sec][row][col].setColor(Color.WHITE);
					
					
					
					if(sec == 0)
						add(cell[sec][row][col], col, row);
					else if(sec == 1)
						add(cell[sec][row][col], col + 9 + 3, row);
					else if(sec == 2)
						add(cell[sec][row][col], col, row + 9 + 3);
					else if(sec == 3)
						add(cell[sec][row][col], col + 9 + 3, row + 9 + 3);
					else if(sec == 4)
						add(cell[sec][row][col], col + 6, row + 6);
					
				}
				
			}
			
		}

	}
	

	class Cell extends StackPane
	{
		
		private Label num;
		private Rectangle box;
		private Color defaultColor;
		
		private int number;
		
		public Cell()
		{
			
		}
		
		public Cell(int n, int sec, int sqr)
		{
			
			number = n;
			if(   (sec == 0 && sqr == 8)   ||   (sec == 1 && sqr == 6)   ||   (sec == 2 && sqr == 2)   |   (sec == 3 && sqr == 0)   )
				setFocusTraversable(false);
			else
				setFocusTraversable(true);
			setOnMousePressed(e -> requestFocus());
			
			box = new Rectangle(30, 30);
			
			box.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
			
			num = new Label(String.valueOf(n));
			num.setStyle("-fx-font: bold 20 arial;");
			num.setTextFill(Color.BLACK);
			
			
			setOnKeyPressed(e -> {
				
				KeyCode code = e.getCode();
				
				if(Board.isNum(code)) {
					num.setText(e.getText());
					number = Integer.parseInt(e.getText());
				}
				else if(code == KeyCode.ENTER && e.isControlDown()) {
					sudoku.solve();
				}
				else if(code == KeyCode.R && e.isControlDown())
					reset();
						
			});
			
			
			focusedProperty().addListener((a, b, c) -> {
				
				if(c) {
					box.setFill(Color.BLACK);
					num.setTextFill(Color.WHITE);
					// box.setStyle("-fx-stroke: blue; -fx-stroke-width: 1;");
				}
				else {
					box.setFill(defaultColor);
					num.setTextFill(Color.BLACK);
					// box.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
				}
				
			});
			
			
// TextField yourTextField = new TextField();
// yourTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
// {
    // @Override
    // public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    // {
        // if (newPropertyValue)
        // {
            // System.out.println("Textfield on focus");
        // }
        // else
        // {
            // System.out.println("Textfield out focus");
        // }
    // }
// });
			
			
			num.setStyle("-fx-font: bold 22 arial;");
			
			getChildren().addAll(box, num);
			
		}
		
		
		public void setColor(Color color)
		{
			defaultColor = color;
			box.setFill(color);
			
		}
		
		public int getNumber()
		{
			return number;
		}
		
		public void setNumber(int number)
		{
			this.number = number;
			num.setText(String.valueOf(number));
		}
		
	}
	
	private static boolean isNum(KeyCode code) {
		
		if(code == KeyCode.NUMPAD0 || code == KeyCode.DIGIT0 || 
		code == KeyCode.NUMPAD1 || code == KeyCode.DIGIT1 || 
		code == KeyCode.NUMPAD2 || code == KeyCode.DIGIT2 || 
		code == KeyCode.NUMPAD3 || code == KeyCode.DIGIT3 || 
		code == KeyCode.NUMPAD4 || code == KeyCode.DIGIT4 || 
		code == KeyCode.NUMPAD5 || code == KeyCode.DIGIT5 || 
		code == KeyCode.NUMPAD6 || code == KeyCode.DIGIT6 || 
		code == KeyCode.NUMPAD7 || code == KeyCode.DIGIT7 || 
		code == KeyCode.NUMPAD8 || code == KeyCode.DIGIT8 || 
		code == KeyCode.NUMPAD9 || code == KeyCode.DIGIT9)
			return true;
			
		return false;
		
	}
	
	public void reset() {
		
		for(int sec = 0; sec < cell.length; sec++)
			for(int row = 0; row < cell[sec].length; row++)
				for(int col = 0; col < cell[sec][row].length; col++)
					cell[sec][row][col].setNumber(0);
		
		
	}
	
	public int[][][] getPuzzle() {
		
		int[][][] userPuzzle = new int[cell.length][cell[0].length][cell[0][0].length];
		
		int sqr;
		
		for(int sec = 0; sec < cell.length; sec++)
			for(int row = 0; row < cell[sec].length; row++)
				for(int col = 0; col < cell[sec][row].length; col++) {
					
					sqr = 3 * (row / 3) + (col / 3);
					
					// assign all the cells to userPuzzle
					// if the cells were hidden by the center puzzle, get the values from the center puzzle
					if(sec == 0 && sqr == 8)
					{	userPuzzle[sec][row][col] = cell[4][row-6][col-6].getNumber();
						// System.out.println(sec + "0 8" + sqr + ": " + cell[4][row-6][col-6].getNumber());
					}
					else if(sec == 1 && sqr == 6)
					{	userPuzzle[sec][row][col] = cell[4][row-6][col+6].getNumber();
						// System.out.println(sec + "1 6" + sqr + ": " + cell[4][row-6][col+6].getNumber());
					}
					else if(sec == 2 && sqr == 2)
					{	userPuzzle[sec][row][col] = cell[4][row+6][col-6].getNumber();
						// System.out.println(sec + "2 2" + sqr + ": " + cell[4][row+6][col-6].getNumber());
					}
					else if(sec == 3 && sqr == 0)
					{	userPuzzle[sec][row][col] = cell[4][row+6][col+6].getNumber();
						// System.out.println(sec + "3 0" + sqr + ": " + cell[4][row+6][col+6].getNumber());
					}
					else
						userPuzzle[sec][row][col] = cell[sec][row][col].getNumber();
					
					
					
				}
		
		return userPuzzle;
		
	}
	
	public void showSolution(int[][][] puzzle) {
		
		for(int sec = 0; sec < puzzle.length; sec++)
			for(int row = 0; row < puzzle[sec].length; row++)
				for(int col = 0; col < puzzle[sec][row].length; col++)
					cell[sec][row][col].setNumber(puzzle[sec][row][col]);
		
		
	}	
}