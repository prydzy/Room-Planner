package model;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Board extends GridPane {

	private GridPane board;
	private StackPane pane;
	private int column;
	private int row;
	
	public Board(){
	}
	
	public GridPane getBoard(){
		return board;
	}

	public void setBoard(GridPane board){
		this.board = board;
	}
	
	public int getColumn(){
		return column;
	}
	
	public void setColumn(int column){
		this.column = column;
	}
	
	public int getRow(){
		return row;
	}
	
	public void setRow(int row){
		this.row = row;
	}
	
	public Integer getColumnInd(Node pane){
		return GridPane.getColumnIndex(pane);
	}
	
	public Integer getRowInd(Node pane){
		return GridPane.getRowIndex(pane);
	}
	
	public void deleteBoard() {
		board.getChildren().clear();
	}
	
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

	public StackPane makePane(){
		StackPane pane = new StackPane();
		pane.setStyle("-fx-border-color: white");
		pane.setPrefHeight(150);
		pane.setPrefWidth(150);              
		pane.setPadding(new Insets(5, 5, 5, 5));
        return pane;
	}
}
