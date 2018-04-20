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

public class DeleteItemTesting {

	JFXPanel fxPanel = new JFXPanel();
	Board board = new Board();
	Pallet pallet = new Pallet();
	Group group = new Group();
	BuildUI view = new BuildUI();
	Controller controller = new Controller(view, board, pallet, group);
	 
	@Test
	public void deleteItemTesting(){
		 
		board = view.getGrid();
		
		StackPane pane = (StackPane) board.getNode(5, 2);
		StackPane pane1 = (StackPane) board.getNode(5, 4);
		StackPane pane2 = (StackPane) board.getNode(5, 3);
		
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
				
		pane.getChildren().add(sofaImageView);
		pane1.getChildren().add(rugImageView);
		pane2.getChildren().add(tvImageView);
		
		group.addItem(sofaImageView);
		group.addItem(rugImageView);		
		group.addItem(tvImageView);
		
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