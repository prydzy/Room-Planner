package modelJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.Board;
import view.BuildUI;

public class BoardModelTesting {

	protected Board test = new Board();
	protected int testcolumn = 7;
	protected int testrow = 7;
	
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
		test.setColumn(testcolumn);
		int column = test.getColumn();
		assertEquals(7, column);
	}

	@Test
	public void rowTest() {
		test.setRow(testrow);
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
		GridPane board = new GridPane();
		test.createBoard(board, testcolumn, testrow);
		boolean isBoard = board.getChildren().isEmpty();
		assertFalse(isBoard);
	}
	
	@Test
	public void createBoardGetBoardTest(){
		GridPane board = new GridPane();
		test.createBoard(board, testcolumn, testrow);
		assertEquals(test.getBoard(), board);
	}
	
	@Test
	public void createBoardRowAndColumnTest(){
		GridPane board = new GridPane();
		test.createBoard(board, testcolumn, testrow);
		assertEquals(test.getColumn(), 7);
		assertEquals(test.getRow(), 7);
	}
	
	@Test
	public void getBoardIDTest(){
		GridPane board = new GridPane();
		test.createBoard(board, testcolumn, testrow);
		assertEquals(board.getId(), "wood");
	}
	
	@Test
	public void deleteBoardTest(){
		GridPane board = new GridPane();
		test.createBoard(board, testcolumn, testrow);
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
	
	 @Test
	 public void getAllNodesTesting(){
		test.createBoard(test, testcolumn, testrow);		
		assertFalse(test.getAllNodes().isEmpty());
		
		test.getChildren().forEach(i -> {
			assertTrue(i instanceof StackPane);
		});
		
		int size = test.getChildren().size();	
		assertEquals(size, 49);
		
	 }
	 
		@Test
		public void getNodeTesting(){		
			test.createBoard(test, testcolumn, testrow);		
			StackPane pane = (StackPane) test.getNode(5, 3);
				
			assertFalse(pane.equals(null));
			assertTrue(pane instanceof StackPane);
				
			int paneColumn = GridPane.getColumnIndex(pane);
			int paneRow = GridPane.getRowIndex(pane);
			
			assertEquals(paneColumn, 5);
			assertEquals(paneRow, 3);
				
			assertNull(test.getNode(10, 10));		
			assertNotNull(test.getNode(4, 4));
		}
	 
}
