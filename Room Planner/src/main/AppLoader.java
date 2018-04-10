package main;

import controller.Controller;
import controller.UIController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Board;
import model.Group;
import model.Pallet;
import view.BuildUI;

public class AppLoader extends Application {

    private BuildUI view;
    private Scene scene;

    @Override
    public void init() {

        view = new BuildUI(); 
        Pallet pallet = new Pallet();
        Group group = new Group();
        Board board = new Board();         
        
        new Controller(view, board, pallet, group);
        new UIController(view, board, pallet, group);
               
    }

    @Override
    public void start(Stage stage){

    	HBox root = view.buildView();
        stage.setMaximized(true);
        scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Room Planner");    
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());                    
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
