package controllerJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.Board;
import model.Group;
import model.Pallet;
import view.BuildUI;

public class DragAndDropTesting {

	JFXPanel fxPanel = new JFXPanel();
	Board board = new Board();
	Pallet pallet = new Pallet();
	Group group = new Group();
	BuildUI view = new BuildUI();
	Controller controller = new Controller(view, board, pallet, group);
	Integer[] bCoords = new Integer[2]; 
	Integer[] aCoords = new Integer[2];
	Integer[] mCoords = new Integer[2];
	Integer[] nCoords = new Integer[2];
	Integer[] a2Coords = new Integer[2];
	Integer[] newCoords = new Integer[2];
	int testInitialColumn;
	int testInitialRow;
	int testMoveColumn;
	int testMoveRow;
	int testReplaceColumn;
	int testReplaceRow;
	int testFinalColumn;
	int testFinalRow;
	
	 @Test
	 public void coordinateTesting(){	 
		 
		/*
		 * This Unit test will demonstrate the behaviour and the coordinate calculations
		 * for the drag and drop event handlers for two pieces of furnitures. testImage 
		 * will be treated as the image that is being dragged and dropped. testImage2 is 
		 * treated as the other image in the group.
		 * 
		 */
		 
		board = view.getGrid();
		
		ImageView testImage = new ImageView();
		ImageView testImage2 = new ImageView();
		
		StackPane imageLocation = (StackPane) board.getNode(2, 2);
		StackPane imageLocation2 = (StackPane) board.getNode(2, 3);

		imageLocation.getChildren().add(testImage);
		imageLocation2.getChildren().add(testImage2);
		
		// Test the initial Coordinates, these are calculated on Drag Event.
		
		int beforeColumn = board.getColumnInd(testImage.getParent());
   		int beforeRow = board.getRowInd(testImage.getParent());  
   		   		   		   		
   		bCoords = controller.setbCoords(beforeColumn, beforeRow);
   		
   		testInitialColumn = bCoords[0];
   		testInitialRow = bCoords[1];
   		
   		assertEquals(testInitialColumn, 2);
   		assertEquals(testInitialRow, 2);
   		
		int beforeColumn2 =  board.getColumnInd(testImage2.getParent());
   		int beforeRow2 = board.getRowInd(testImage2.getParent());  
   		
   		assertEquals(beforeColumn2, 2);
   		assertEquals(beforeRow2, 3); 	
   		
   		/* Simulate a drop event by placing an image into a new cell, 
   		 * this is called on the drag-drop handler. the ACoords are the
   		 * coordinates of the drop event. JUnit test cases won't cover
   		 * drop handling so in this instance it will be simulated.
   		 */
   		
   		imageLocation.getChildren().remove(testImage);
   		StackPane newImageLocation = (StackPane) board.getNode(3, 4);   		
   		newImageLocation.getChildren().add(testImage);
   		
   		// Simulating a drag event to column 3 and row 4.
   		
   		aCoords = controller.setaCoords(3, 4);
   		
   		aCoords = controller.setaCoords(aCoords[0], aCoords[1]);
   		
   		mCoords = controller.calculateMoveDistance();
   		
   		testMoveColumn = mCoords[0];
   		testMoveRow = mCoords[1];
   		
   		// The distance the furnitures have to move should be 1 Column and 2 Rows.
   		
   		assertEquals(testMoveColumn, 1);
   		assertEquals(testMoveRow, 2);
   		
   		/*
   		 * Testing the Replace Function, if a piece of furniture already
   		 * exists on the location the furniture is dragged then the two
   		 * images will swap. For Example if an image was to be dragged onto
   		 * the below location.
   		 * StackPane overLapImageLocation = (StackPane) controller.getNode(grid, 3, 4);
   		 * newImageLocation.getChildren().add(randomImage);
   		 * 
   		 * The desired outcome is for the new coordinates to be (2,2) as that would effectively
   		 * swap the two image locations.
   		 */
   		
   		nCoords = controller.calculateReplaceCoords();
   		
   		testReplaceColumn = nCoords[0];
   		testReplaceRow = nCoords[1];

   		assertEquals(testReplaceColumn, 2);
   		assertEquals(testReplaceRow, 2);
   		
   		/*
   		 * Now the final bits of logic for the drag and drop handler is the coordinates
   		 * of the grouped item. This is the furniture that isn't displayed
   		 * in the Drag View. In this case the A2 coordinates will be (2,3),
   		 * the moveCoordinates were 1 and 2. So the final coordinates should be
   		 * 3 and 5.
   		 * 
   		 */
   		
   		a2Coords[0] = board.getColumnInd(testImage2.getParent());
   		a2Coords[1] = board.getRowInd(testImage2.getParent());
   		
   		a2Coords = controller.seta2Coords(a2Coords[0], a2Coords[1]);
   		
   		newCoords = controller.calculateNewCoords();
   		
   		testFinalColumn = newCoords[0];
   		testFinalRow = newCoords[1];
   		
   		assertEquals(testFinalColumn, 3);
   		assertEquals(testFinalRow, 5);
   		
	 }
	 
}