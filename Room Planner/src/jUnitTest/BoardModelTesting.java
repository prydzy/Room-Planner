package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.Board;

public class BoardModelTesting {

	Board test = new Board();
	
	@Test
	public void boardTest(){
		GridPane board = new GridPane();
		test.setBoard(board);
		assertNotNull(test.getBoard());
		assertEquals(test.getBoard(), board);
		assertSame(test.getBoard(), board);
	}
	
	@Test
	public void columnTest() {
		int testColumn = 7;
		test.setColumn(testColumn);
		int column = test.getColumn();
		assertEquals(7, column);
	}

	@Test
	public void rowTest() {
		int testRow = 7;
		test.setRow(testRow);
		int row = test.getRow();
		assertEquals(7, row);
	}
	
	@Test
	public void rowIndexTest(){
		StackPane pane = new StackPane();
		test.add(pane, 10, 5);
		int row = test.getRowInd(pane);
		assertEquals(row, 5);
	}
	
	@Test
	public void columnIndexTest(){
		StackPane pane = new StackPane();
		test.add(pane, 5, 10);
		int column = test.getColumnInd(pane);
		assertEquals(column, 5);
	}
	
	@Test	
	public void createBoardIsEmptyTest(){
		int column = 7;
		int row = 7;
		GridPane board = new GridPane();
		test.createBoard(board, column, row);
		boolean isBoard = board.getChildren().isEmpty();
		assertFalse(isBoard);
	}
	
	@Test
	public void createBoardGetBoardTest(){
		int column = 7;
		int row = 7;
		GridPane board = new GridPane();
		test.createBoard(board, column, row);
		assertEquals(test.getBoard(), board);
	}
	
	@Test
	public void createBoardRowAndColumnTest(){
		int column = 7;
		int row = 7;
		GridPane board = new GridPane();
		test.createBoard(board, column, row);
		assertEquals(test.getColumn(), 7);
		assertEquals(test.getRow(), 7);
	}
	
	@Test
	public void getBoardIDTest(){
		int column = 7;
		int row = 7;
		GridPane board = new GridPane();
		test.createBoard(board, column, row);
		assertEquals(board.getId(), "wood");
	}
	
	@Test
	public void deleteBoardTest(){
		int column = 7;
		int row = 7;
		GridPane board = new GridPane();
		test.createBoard(board, column, row);
		test.deleteBoard();
		assertTrue(board.getChildren().isEmpty());
	}
	
	@Test
	public void makePaneTest(){
		StackPane pane = new StackPane();
		pane = test.makePane();
		int prefWidth = (int) pane.getPrefWidth();
		int prefHeight = (int) pane.getPrefHeight();
		Insets compare = new Insets(5, 5, 5, 5);
		assertEquals(pane.getPadding(), compare);
		assertEquals(pane.getStyle(), "-fx-border-color: white");
		assertEquals(prefWidth, 150);
		assertEquals(prefHeight, 150);
	}
}
