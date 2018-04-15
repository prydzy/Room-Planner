package controllerJUnitTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

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

public class SaveTesting {

	JFXPanel fxPanel = new JFXPanel();
	Board board = new Board();
	Pallet pallet = new Pallet();
	Group group = new Group();
	BuildUI view = new BuildUI();
	Controller controller = new Controller(view, board, pallet, group);
	
	@Test
	public void saveBoardTesting(){
		 
		board = view.getGrid();	
			
		StackPane pane = (StackPane) controller.getNode(board, 0, 1);
		StackPane pane1 = (StackPane) controller.getNode(board, 2, 3);
		StackPane pane2 = (StackPane) controller.getNode(board, 3, 1);

		String sofaString = "file:sofa.png";
		String rugString = "file:rug.png";
		String tvString = "file:tv.png";
		
		String sofaToString = "[ImageView[id=file:sofa.png, styleClass=image-view]]";
		String rugToString = "[ImageView[id=file:rug.png, styleClass=image-view]]";
		String tvToString = "[ImageView[id=file:tv.png, styleClass=image-view]]";
		
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
		
		ArrayList<String> save = controller.saveBoard();
			
		assertFalse(save.isEmpty());		
				
		assertEquals(save.size(), 49);
		
		save.forEach(i ->{	
			boolean containsPane = save.contains("[]");
			assertTrue(containsPane);		
		});
				
		assertTrue(save.contains(sofaToString) && save.contains(rugToString) && save.contains(tvToString));
		
		try {
			controller.createFile("testBoard", save);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}