package controllerJUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controller.Controller;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.StackPane;
import model.Board;
import model.Group;
import model.Pallet;
import view.BuildUI;

public class FileAndLoadTesting {

	JFXPanel fxPanel = new JFXPanel();
	Board board = new Board();
	Pallet pallet = new Pallet();
	Group group = new Group();
	BuildUI view = new BuildUI();
	Controller controller = new Controller(view, board, pallet, group);
	ArrayList<String> loadArray = new ArrayList<>();
	ArrayList<String> filteredArray = new ArrayList<>();
	ArrayList<String> cmon = new ArrayList<>();
	int sofaCount = 0;
	int rugCount = 0;
	int tvCount = 0;
	
	@Test
	public void LoadAndFilterTesting(){
		 
		board = view.getGrid();	
		
		loadArray = controller.loadFile("testBoard.txt");	
		
		assertFalse(loadArray.isEmpty());
		
		// 51 = 49 Cells, 1 Column and 1 Row value.
			
		assertEquals(loadArray.size(), 51);
		
		String column = "column=7";
		String row = "row=7";
		String sofaString = "file:sofa.png";
		String rugString = "file:rug.png";
		String tvString = "file:tv.png";
		String sofaToString = "[ImageView[id=file:sofa.png, styleClass=image-view]]";
		String rugToString = "[ImageView[id=file:rug.png, styleClass=image-view]]";
		String tvToString = "[ImageView[id=file:tv.png, styleClass=image-view]]";
		
		assertTrue(loadArray.contains(column) && loadArray.contains(row) 
				&&loadArray.contains(sofaToString) && loadArray.contains(rugToString)
				&&loadArray.contains(tvToString));
		
		assertTrue(filteredArray.isEmpty());
		
		filteredArray = controller.filterArray(loadArray);
		
		assertFalse(filteredArray.isEmpty());
		assertEquals(filteredArray.size(), 51);
		assertTrue(filteredArray.contains(sofaString) && filteredArray.contains(rugString) && filteredArray.contains(tvString));
		assertTrue(filteredArray.contains("7"));
		
		
		board.deleteBoard();
		assertTrue(board.getChildren().isEmpty());
		
		controller.loadBoard(filteredArray);
		
		assertEquals(board.getColumn(), 7);
		assertEquals(board.getRow(), 7);
		assertFalse(board.getChildren().isEmpty());
				
		ArrayList<StackPane> panes = controller.getAllNodes(board);
		
		panes.forEach(i -> {	
			
			System.out.println(i.getChildren());
			
			if(i.getChildren().toString().contains(sofaToString)){
				sofaCount++;			
			}
			else if(i.getChildren().toString().contains(rugToString)){
				rugCount++;			
			}
			else if(i.getChildren().toString().contains(tvToString)){
				tvCount++;			
			}
		});
		
		assertEquals(sofaCount, 1);
		assertEquals(rugCount, 1);
		assertEquals(tvCount, 1);
		
	}
}