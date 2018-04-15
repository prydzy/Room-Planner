package model;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * This class extends the GridPane class and implements a board
 * that contains Stack Panes and acts as a basis for the floor plan. 
 * @author Sam Clark
 * @version 1.0
 */

public class Board extends GridPane {

	private GridPane board;
	private StackPane pane;
	private int column;
	private int row;
	/**
	 * Constructs a blank board.
	 */
	public Board(){
	}
	/**
	 * This returns the current instance of the board.
	 * @return The board;
	 */
	public GridPane getBoard(){
		return board;
	}
	/**
	 * This initialises the board.
	 * @param The board
	 */
	public void setBoard(GridPane board){
		this.board = board;
	}
	/**
	 * This returns the amount of columns the board has.
	 * @return The number of the columns
	 */
	public int getColumn(){
		return column;
	}
	/**
	 * This sets the amount of columns the board has.
	 * @param The number of columns
	 */
	public void setColumn(int column){
		this.column = column;
	}
	
	/**
	 * This returns the number of rows the board has.
	 * @return The number of rows
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * This sets the number of rows the board has.
	 * @param The number of rows
	 */
	public void setRow(int row){
		this.row = row;
	}
	/**
	 * This returns the column index of the specified node.
	 * @param The node located on the board
	 * @return The column number the node is located in
	 */
	public Integer getColumnInd(Node pane){
		return GridPane.getColumnIndex(pane);
	}
	/**
	 * This returns the row index of the specified node.
	 * @param The node located on the board
	 * @return The row number the node is located in
	 */
	public Integer getRowInd(Node pane){
		return GridPane.getRowIndex(pane);
	}
	/**
	 * This deletes every child element of the board.
	 */
	public void deleteBoard() {
		board.getChildren().clear();
	}
	/**
	 * This creates a new board with modifiable row and
	 * and column numbers and  populates it with 
	 * Stack Panes, the layout of which is calculated by 
	 * the makePane() method.
	 * @param board The board to be created
	 * @param column The number of columns
	 * @param row The number of rows
	 */
	public void createBoard(GridPane board, int column, int row){
    			
		System.out.println("Board Created");
		
		this.board = board;
		this.column = column;
		this.row = row;
	    			
		for(int x = 0; x < column; x++){
			for(int y = 0; y < row; y++){
				pane = makePane();
				board.add(pane, x, y);
				board.setId("wood");				
			}
		}		
	}
	/**
	 * Initialises a new StackPane and sets it layout and
	 * style.
	 * @return The StackPane that has been styled
	 */
	public StackPane makePane(){
		StackPane pane = new StackPane();
		pane.setStyle("-fx-border-color: white");
		pane.setPrefHeight(150);
		pane.setPrefWidth(150);              
		pane.setPadding(new Insets(5, 5, 5, 5));
        return pane;
	}
}
