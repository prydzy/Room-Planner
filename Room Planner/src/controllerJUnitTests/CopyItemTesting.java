package controllerJUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

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

public class CopyItemTesting {

	JFXPanel fxPanel = new JFXPanel();
	Board board = new Board();
	Pallet pallet = new Pallet();
	Group group = new Group();
	BuildUI view = new BuildUI();
	Controller controller = new Controller(view, board, pallet, group);
	ArrayList<StackPane> testitems = new ArrayList<StackPane>();
	
	@Test
	public void copyItemTesting(){
		
		/*
		 * Have to disable image.setId(image.getImage().impl_getUrl());
		 * in the Pallet / MakeImageView method to run this test. As
		 * CopyItem(); can't reference the copied items file location.
		 * 
		 */
		 
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
		Image testImage1 = new Image("file:rug.png");
		ImageView testImageView1 = new ImageView();
		testImageView.setImage(testImage1);
		pallet.makeImageView(testImageView);
		Image testImage2 = new Image("file:tv.png");
		ImageView testImageView2 = new ImageView();
		testImageView.setImage(testImage2);
		pallet.makeImageView(testImageView);
				
		pane.getChildren().add(testImageView);
		pane1.getChildren().add(testImageView1);
		pane2.getChildren().add(testImageView2);
		
		group.addItem(testImageView);
		group.addItem(testImageView1);		
		group.addItem(testImageView2);
		
		testitems = controller.copyItem();
		
		assertFalse(testitems.isEmpty());
		
		testitems.forEach(i -> {
			assertTrue(i instanceof StackPane);
						
			String testClass = "ImageView";
			
			boolean contains = i.getChildren().toString().contains(testClass);	
			
			assertTrue(contains);
						
		});
		
		
		
	}
}