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
	
	Cell[][] cell;
	Sudoku sudoku;
	
	public Board(Sudoku sudoku, int puzzle[][])
	{
		this.sudoku = sudoku;
		cell = new Cell[puzzle.length][puzzle[0].length];
	
		for(int row = 0; row < puzzle.length; row++) {
			
			for(int col = 0; col < puzzle[row].length; col++) {
				
				cell[row][col] = new Cell(puzzle[row][col]);
				
				if(  ((col / 3 == 0 || col / 3 == 2) && (row / 3 == 0 || row / 3 == 2)) || ((col / 3 == 1) && (row / 3 == 1)) )
					cell[row][col].setColor(Color.rgb(255, 255, 200));
				else
					cell[row][col].setColor(Color.WHITE);
				
				add(cell[row][col], col, row);
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
		
		public Cell(int n)
		{
			
			number = n;
			setFocusTraversable(true);
			setOnMouseClicked(e -> requestFocus());
			
			box = new Rectangle(40, 40);
			
			box.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
			
			
			num = new Label(String.valueOf(n));
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
				// System.out.println(TF.getCharacters());
						
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
		
		for(int row = 0; row < cell.length; row++)
			for(int col = 0; col < cell[row].length; col++)
				cell[row][col].setNumber(0);
		
		
	}
	
	public int[][] getPuzzle() {
		
		int[][] userPuzzle = new int[cell.length][cell[0].length];
		
		for(int row = 0; row < cell.length; row++)
			for(int col = 0; col < cell[row].length; col++)
				userPuzzle[row][col] = cell[row][col].getNumber();
		
		return userPuzzle;
		
	}
	
	public void showSolution(int[][] puzzle) {
		
		for(int row = 0; row < puzzle.length; row++)
			for(int col = 0; col < puzzle[row].length; col++)
				cell[row][col].setNumber(puzzle[row][col]);
		
		
	}
	
}