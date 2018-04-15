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

public class DeleteItemTesting {

	JFXPanel fxPanel = new JFXPanel();
	Board board = new Board();
	Pallet pallet = new Pallet();
	Group group = new Group();
	BuildUI view = new BuildUI();
	Controller controller = new Controller(view, board, pallet, group);
	 
	@Test
	public void deleteItemTesting(){
		 
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
		
		assertFalse(group.getGroup().isEmpty());		
		assertFalse(pane.getChildren().isEmpty());
		assertFalse(pane1.getChildren().isEmpty());
		assertFalse(pane2.getChildren().isEmpty());

		controller.deleteItem();
		
		assertTrue(group.getGroup().isEmpty());		
		assertTrue(pane.getChildren().isEmpty());
		assertTrue(pane1.getChildren().isEmpty());
		assertTrue(pane2.getChildren().isEmpty());		
		
	}
}