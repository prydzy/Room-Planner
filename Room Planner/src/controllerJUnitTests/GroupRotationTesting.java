package controllerJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		 
		board = view.getGrid();
		
		StackPane pane = (StackPane) controller.getNode(board, 4, 2);
		StackPane pane1 = (StackPane) controller.getNode(board, 4, 4);
		StackPane pane2 = (StackPane) controller.getNode(board, 4, 3);
		
		String sofaString = "file:sofa.png";
		String rugString = "file:rug.png";
		String tvString = "file:tv.png";
		
		Image sofaImage = new Image(sofaString);
		ImageView sofaImageView = new ImageView();
		sofaImageView.setImage(sofaImage);
		pallet.makeImageView(sofaImageView);
		
		Image rugImage = new Image(rugString);
		ImageView rugImageView = new ImageView();
		rugImageView.setImage(rugImage);
		pallet.makeImageView(rugImageView);
		
		Image tvImage = new Image(tvString);
		ImageView tvImageView = new ImageView();
		tvImageView.setImage(tvImage);
		pallet.makeImageView(tvImageView);
				
		pane2.getChildren().add(tvImageView);
		pane1.getChildren().add(rugImageView);
		pane.getChildren().add(sofaImageView);
	
		group.addItem(tvImageView);
		group.addItem(sofaImageView);
		group.addItem(rugImageView);
	
		controller.groupRotation();
		
		int newColumn = board.getColumnInd(sofaImageView.getParent());
		int newRow = board.getRowInd(sofaImageView.getParent());
		
		int newColumn1 = board.getColumnInd(rugImageView.getParent());
		int newRow1 = board.getRowInd(rugImageView.getParent());
		
		int newColumn2 = board.getColumnInd(tvImageView.getParent());
		int newRow2 = board.getRowInd(tvImageView.getParent());

		/* The Center of Rotation should be about Row 3, Column 5, with the
		 * Image rotating to either side on column 4 and 6. The row
		 * should remain 3. This demonstrated a 90 degree rotation.
		 */	
		
		assertEquals(newColumn1, 3);
		assertEquals(newRow1, 3);
		
		assertEquals(newColumn, 5);
		assertEquals(newRow, 3);
		
		assertEquals(newColumn2, 4);
		assertEquals(newRow2, 3);
		
	}
}