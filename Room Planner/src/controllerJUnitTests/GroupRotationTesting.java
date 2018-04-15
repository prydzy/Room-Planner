package controllerJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.Board;
import model.Group;
import model.Pallet;
import view.BuildUI;

public class GroupRotationTesting {

	JFXPanel fxPanel = new JFXPanel();
	Board board = new Board();
	Pallet pallet = new Pallet();
	Group group = new Group();
	BuildUI view = new BuildUI();
	Controller controller = new Controller(view, board, pallet, group);
	 
	@Test
	public void groupRotationTesting(){
		 
		int column = 7;
		int row = 7;
		
		GridPane grid = new GridPane();
		board.createBoard(grid, column, row);
		StackPane pane = (StackPane) controller.getNode(grid, 5, 2);
		StackPane pane1 = (StackPane) controller.getNode(grid, 5, 4);
		StackPane pane2 = (StackPane) controller.getNode(grid, 5, 3);
		
		Image testImage = new Image("file:sofa.png");
		ImageView testImageView = new ImageView();
		testImageView.setImage(testImage);
		pallet.makeImageView(testImageView);
		Image testImage1 = new Image("file:sofa.png");
		ImageView testImageView1 = new ImageView();
		testImageView1.setImage(testImage1);
		pallet.makeImageView(testImageView1);
		Image testImage2 = new Image("file:sofa.png");
		ImageView testImageView2 = new ImageView();
		testImageView2.setImage(testImage2);
		pallet.makeImageView(testImageView2);
				
		pane.getChildren().add(testImageView);
		pane1.getChildren().add(testImageView1);
		pane2.getChildren().add(testImageView2);
		
		group.addItem(testImageView);
		group.addItem(testImageView1);		
		group.addItem(testImageView2);
		
		controller.groupRotation();
		
		int newColumn = board.getColumnInd(testImageView.getParent());
		int newRow = board.getRowInd(testImageView.getParent());
		
		int newColumn1 = board.getColumnInd(testImageView1.getParent());
		int newRow1 = board.getRowInd(testImageView1.getParent());
		
		int newColumn2 = board.getColumnInd(testImageView2.getParent());
		int newRow2 = board.getRowInd(testImageView2.getParent());

		/* The Center of Rotation should be about Row 3, Column 5, with the
		 * Image rotating to either side on column 4 and 6. The row
		 * should remain 3. This demonstrated a 90 degree rotation.
		 */
		
		assertEquals(newColumn1, 4);
		assertEquals(newRow1, 3);
		
		assertEquals(newColumn, 6);
		assertEquals(newRow, 3);
		
		assertEquals(newColumn2, 5);
		assertEquals(newRow2, 3);
		
	}
}