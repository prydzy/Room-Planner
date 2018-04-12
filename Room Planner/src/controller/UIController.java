package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.Board;
import model.Group;
import model.Pallet;
import view.BuildUI;

public class UIController{
		
	private BuildUI view;
	private Pallet pallet;
	private ImageView[] images;
	private Board board;
	private Group group;
	
	public UIController(BuildUI view, Board board, Pallet pallet, Group group){
		this.view = view;
		this.board = board;
		this.pallet = pallet;
		this.group = group;
		this.attachEventHandler();
	}

	private void attachEventHandler(){
		view.addChoiceBoxHandler(this::addChoiceBoxHandling);
		view.addStoneHandler(this::toggleStoneHandler);
		view.addWoodListener(this::toggleWoodHandler);
		view.addMarbleListener(this::toggleMarbleHandler);
		view.addRotateBoardHandler(this::rotateBoard);
		view.addToggleGridHandler(this::toggleGridLines);
		view.addSaveHandler(arg0 -> {
			try {
				saveBoardHandling(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	private void addChoiceBoxHandling(ActionEvent event){
		if(view.getChoice().getSelectionModel().getSelectedItem().equals("Furniture Selection")){
			view.getBedroom().setVisible(false);
			view.getBathroom().setVisible(false);
			view.getKitchen().setVisible(false);
			view.getLivingroom().setVisible(false);
			view.getMisc().setVisible(false);
		}
		if(view.getChoice().getSelectionModel().getSelectedItem().equals("Bedroom")){
			view.getBedroom().setVisible(true);
			view.getBathroom().setVisible(false);
			view.getKitchen().setVisible(false);
			view.getLivingroom().setVisible(false);
			view.getMisc().setVisible(false);
		}
		if(view.getChoice().getSelectionModel().getSelectedItem().equals("Living Room")){
			view.getBedroom().setVisible(false);
			view.getBathroom().setVisible(false);
			view.getKitchen().setVisible(false);
			view.getLivingroom().setVisible(true);
			view.getMisc().setVisible(false);
		}
		if(view.getChoice().getSelectionModel().getSelectedItem().equals("Bathroom")){
			view.getBedroom().setVisible(false);
			view.getBathroom().setVisible(true);
			view.getKitchen().setVisible(false);
			view.getLivingroom().setVisible(false);
			view.getMisc().setVisible(false);
		}
		if(view.getChoice().getSelectionModel().getSelectedItem().equals("Kitchen")){
			view.getBedroom().setVisible(false);
			view.getBathroom().setVisible(false);
			view.getKitchen().setVisible(true);
			view.getLivingroom().setVisible(false);
			view.getMisc().setVisible(false);
		}
		if(view.getChoice().getSelectionModel().getSelectedItem().equals("Misc")){
			view.getBedroom().setVisible(false);
			view.getBathroom().setVisible(false);
			view.getKitchen().setVisible(false);
			view.getLivingroom().setVisible(false);
			view.getMisc().setVisible(true);
		}
		
		System.out.println(view.getChoice().getSelectionModel().getSelectedItem());		
		
	}
	
	private void toggleStoneHandler(ActionEvent event){
		board = view.getGrid();
		view.getStone().setSelected(true);
		view.getMarble().setSelected(false);
		view.getWood().setSelected(false);
		board.setId("stone");
		
		board.getChildren().forEach(i -> {
			i.setStyle("-fx-border-color: white");
		});
		
	}
	
	private void toggleWoodHandler(ActionEvent event){
		board = view.getGrid();
		view.getStone().setSelected(false);
		view.getMarble().setSelected(false);
		view.getWood().setSelected(true);
		board.setId("wood");
		
		board.getChildren().forEach(i -> {
			i.setStyle("-fx-border-color: white");
		});
		
	}
	
	private void toggleMarbleHandler(ActionEvent event){
		board = view.getGrid();
		view.getStone().setSelected(false);
		view.getMarble().setSelected(true);
		view.getWood().setSelected(false);
		board.setId("marble");
		
		board.getChildren().forEach(i -> {
			i.setStyle("-fx-border-color: black");
		});
		
	}
	
    private void rotateBoard(ActionEvent event){
    	board = view.getGrid();
    	board.setRotate(board.getRotate() + 90);
    }
    
	private void toggleGridLines(ActionEvent event){
		
		board = view.getGrid();
		
		board.getChildren().forEach(i -> {
			if(i.getStyle() == ""){
				i.setStyle("-fx-border-color: white");
			} else if (i.getStyle() == "-fx-border-color: white"){
				i.setStyle("");
			}
		});	
	}
	
	
	private void createFile(String file, ArrayList<String> arrData)
            throws IOException {
        FileWriter writer = new FileWriter(file + ".txt");
        int size = arrData.size();
        for (int i=0;i<size;i++) {
            String str = arrData.get(i).toString();
            writer.write(str);
            if(i < size-1)
                writer.write("\n");
        }
        
        writer.write("\n");
        
        String column = "column=" + Integer.toString(board.getColumn());       
        String row = "row=" + Integer.toString(board.getRow());
        
        writer.write(column);
        writer.write("\n");
        writer.write(row);
        
        writer.close();
    }
	
	private void saveBoardHandling(ActionEvent event) throws IOException{
		board = view.getGrid();
		ArrayList<String> save = saveBoard();
		createFile("board", save);
	}
	
	private ArrayList<String> saveBoard(){
		
		board = view.getGrid();
		
		ArrayList<StackPane> panes = new ArrayList<>();
		ArrayList<String> images = new ArrayList<>();
		
		panes = getAllNodes(board);
		
		panes.forEach(i -> {
			images.add(i.getChildren().toString());
		});

		System.out.println(images);
		
		return images;
		
	}
	
	public ArrayList<StackPane> getAllNodes(GridPane board){
		
		ArrayList<StackPane> nodes = new ArrayList<>();
		
		StackPane pane = new StackPane();
		
		for(Node node : board.getChildren()) {
			pane = (StackPane) node;
			nodes.add(pane);
		}
		
		return nodes;
		
	}
	
}
