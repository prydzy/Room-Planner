package controllerJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;
import javafx.embed.swing.JFXPanel;
import model.Board;
import model.Group;
import model.Pallet;
import view.BuildUI;

public class GranularityTesting {

	JFXPanel fxPanel = new JFXPanel();
	Board board = new Board();
	Pallet pallet = new Pallet();
	Group group = new Group();
	BuildUI view = new BuildUI();
	Controller controller = new Controller(view, board, pallet, group);
	Integer[] granularityCoords = new Integer[2];

	 
	@Test
	public void granularityTesting(){
		 				
		view.getSlider().setValue(0);	
		granularityCoords = controller.setGranularity();
		assertEquals(granularityCoords[0].intValue(), 7);
		assertEquals(granularityCoords[1].intValue(), 7);
		
		view.getSlider().setValue(1);	
		granularityCoords = controller.setGranularity();
		assertEquals(granularityCoords[0].intValue(), 8);
		assertEquals(granularityCoords[1].intValue(), 8);
		
		view.getSlider().setValue(2);	
		granularityCoords = controller.setGranularity();
		assertEquals(granularityCoords[0].intValue(), 9);
		assertEquals(granularityCoords[1].intValue(), 9);
		
		view.getSlider().setValue(3);	
		granularityCoords = controller.setGranularity();
		assertEquals(granularityCoords[0].intValue(), 10);
		assertEquals(granularityCoords[1].intValue(), 10);
		
		view.getSlider().setValue(4);	
		granularityCoords = controller.setGranularity();
		assertEquals(granularityCoords[0].intValue(), 11);
		assertEquals(granularityCoords[1].intValue(), 11);
		
		view.getSlider().setValue(5);	
		granularityCoords = controller.setGranularity();
		assertEquals(granularityCoords[0].intValue(), 12);
		assertEquals(granularityCoords[1].intValue(), 12);
			
	}
}