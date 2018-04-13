package controller;

import javafx.event.ActionEvent;
import model.Board;
import view.BuildUI;

public class UIController{
		
	private BuildUI view;
	private Board board;
	
	public UIController(BuildUI view, Board board){
		this.view = view;
		this.board = board;
		this.attachEventHandler();
	}

	private void attachEventHandler(){
		view.addChoiceBoxHandler(this::addChoiceBoxHandling);
		view.addStoneHandler(this::toggleStoneHandler);
		view.addWoodListener(this::toggleWoodHandler);
		view.addMarbleListener(this::toggleMarbleHandler);
		view.addToggleGridHandler(this::toggleGridLines);
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
	
}
