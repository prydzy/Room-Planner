package controllerJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;
import javafx.embed.swing.JFXPanel;
import model.Board;
import model.Group;
import model.Pallet;
import view.BuildUI;

public class BoardRotateTesting {

	JFXPanel fxPanel = new JFXPanel();
	Board board = new Board();
	Pallet pallet = new Pallet();
	Group group = new Group();
	BuildUI view = new BuildUI();
	Controller controller = new Controller(view, board, pallet, group);
	 
	@Test
	public void boardRotateTesting(){
		 
		board = view.getGrid();	
		
		controller.rotateBoard();
		
		int rotateValue = (int) board.getRotate();
		
		assertEquals(rotateValue, 90);
		
	}
}