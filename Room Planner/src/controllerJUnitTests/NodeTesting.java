package controllerJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.Board;
import model.Group;
import model.Pallet;
import view.BuildUI;

public class NodeTesting {

	JFXPanel fxPanel = new JFXPanel();
	Board board = new Board();
	Pallet pallet = new Pallet();
	Group group = new Group();
	BuildUI view = new BuildUI();
	Controller controller = new Controller(view, board, pallet, group);
	 
	@Test
	public void getNodeTesting(){
		 
		int column = 7;
		int row = 7;
		
		GridPane grid = new GridPane();
		board.createBoard(grid, column, row);		
		StackPane pane = (StackPane) board.getNode(5, 3);
			
		assertFalse(pane.equals(null));
		assertTrue(pane instanceof StackPane);
			
		int paneColumn = GridPane.getColumnIndex(pane);
		int paneRow = GridPane.getRowIndex(pane);
		
		assertEquals(paneColumn, 5);
		assertEquals(paneRow, 3);
			
		assertNull(board.getNode(10, 10));		
		assertNotNull(board.getNode(4, 4));
	}
}