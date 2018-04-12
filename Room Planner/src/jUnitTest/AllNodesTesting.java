package jUnitTest;

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

public class AllNodesTesting {

	JFXPanel fxPanel = new JFXPanel();
	Board board = new Board();
	Pallet pallet = new Pallet();
	Group group = new Group();
	BuildUI view = new BuildUI();
	Controller controller = new Controller(view, board, pallet, group);
	 
	 @Test
	 public void getAllNodesTesting(){
		 
		int column = 7;
		int row = 7;
		
		GridPane grid = new GridPane();
		board.createBoard(grid, column, row);
		
		assertFalse(controller.getAllNodes(grid).isEmpty());
		
		grid.getChildren().forEach(i -> {
			assertTrue(i instanceof StackPane);
		});
		
		int size = grid.getChildren().size();	
		assertEquals(size, 49);
		
	 }
}

/*
 * 
 * SEPERATE EVENTS FROM METHODS IN THE CONTROLLER SO THAT YOU CAN TEST THEM!
 * 
 * */
