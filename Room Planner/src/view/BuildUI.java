package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import model.Board;

public class BuildUI {

	private StackPane pane;
	private Board board;
	private GridPane floor;
	private Board copyBoard;
	private GridPane modifyBoard;
	private TilePane imgBox;
	private Label columnLabel = new Label("Columns:");
	private Label rowLabel = new Label("Rows:");
	private NumberTextField columnText = new NumberTextField();
	private NumberTextField rowText = new NumberTextField();
	private NumberTextField widthText = new NumberTextField();
	private NumberTextField heightText = new NumberTextField();
	private Button sofaBtn = new Button("Add Sofa");
	private Button bedBtn = new Button("Add Bed");
	private Button bookshelfBtn = new Button("Add Book Shelf");
	private Button bathBtn = new Button("Add Bath");
	private Button clearBtn = new Button("Clear Pallet");
	private Button clearboardBtn = new Button("Reset Board");
	private Button rotateboardBtn = new Button("Rotate Board");
	private Button copyBtn = new Button("Copy Selection");
	private Button deleteBtn = new Button("Delete Selection");
	private Button gridLines = new Button("Toggle Grid Lines");
	private Button modifyGran = new Button("Modify Grid Granularity");
	private Button rotateBtn = new Button("Rotate Selection");
	private Button resizeBtn = new Button("Resize Item");
	private Button saveButton = new Button("Save");
	private Button loadButton = new Button("Load");
    private HBox box = new HBox(5);
    private VBox leftbuttons = new VBox();
    private VBox rightButtons = new VBox();
    private VBox middleButtons = new VBox();
    private Scene scene;
    
    public BuildUI(){	
    	buildGrid();  	
    }

    public HBox buildView(){  	
    	    	    	    	
    	copyBoard = new Board(); 	
    	copyBoard.setColumn(3);
    	copyBoard.setRow(3);
    	
    	int column = copyBoard.getColumn();
    	int row = copyBoard.getRow();
    	copyBoard.createBoard(copyBoard, column, row);
    	
    	modifyBoard = new GridPane();

        imgBox = new TilePane();
        imgBox.setHgap(5.0);
        imgBox.setVgap(5.0);
        imgBox.setPadding(new Insets(5, 5, 5, 5));
        imgBox.setMinWidth(250);
        imgBox.setMaxWidth(250);

        ScrollPane scroll = new ScrollPane(imgBox);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setMinWidth(250);
        scroll.setMaxWidth(250);

        modifyBoard.add(columnLabel, 0, 0);
        modifyBoard.add(columnText, 1, 0);
        modifyBoard.add(rowLabel, 0, 1);
        modifyBoard.add(rowText, 1, 1);
        modifyBoard.setHgap(10);
        modifyBoard.setVgap(5);    

        leftbuttons.getChildren().addAll(clearBtn, sofaBtn, bedBtn, bathBtn, bookshelfBtn);
        middleButtons.getChildren().addAll(copyBtn, deleteBtn, rotateBtn, widthText, heightText, resizeBtn);
        rightButtons.getChildren().addAll(clearboardBtn, rotateboardBtn, gridLines, modifyBoard, modifyGran, copyBoard);        

        rightButtons.setPadding(new Insets(5, 5, 5, 5));
        rightButtons.setSpacing(10);
        leftbuttons.setPadding(new Insets(5, 5, 5, 5));
        leftbuttons.setSpacing(10);
        middleButtons.setPadding(new Insets(5, 5, 5, 5));
        middleButtons.setSpacing(10);

    	box.getChildren().addAll(leftbuttons, scroll, middleButtons, board, rightButtons);

    	box.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;");
    	        	
    	return box;
    	
		}
    
    	public void buildGrid(){
    		
        	pane = new StackPane();
        	board = new Board();
        	floor = new GridPane();
       	
        	board.setFloor(board);           	
        	board.setBoard(floor);   
        	        	
        	board.setColumn(11);
        	board.setRow(11); 	 
        	
        	int column = board.getColumn();
        	int row = board.getRow();
        	board.createBoard(board, column, row);
        	
        	board.setMaxWidth(975);
        	board.setMaxHeight(975);
        	board.setMinWidth(975);
        	board.setMinHeight(975);
        	        	        	
    	}
    	
    	public NumberTextField getWidth(){
    		return widthText;
    	}
    	
    	public NumberTextField getHeight(){
    		return heightText;
    	}
    	
    	public void setWidth(String value){
    		widthText.setText(value);
    	}
    	
    	public void setHeight(String value){
    		heightText.setText(value);
    	}
    	
    	public Board getCopyGrid(){
    		return copyBoard;
    	}
    	
    	public Board getGrid(){
    		return board;
    	}
    
    	public void addAll(ImageView[] image){
    		imgBox.getChildren().addAll(image);
    	}
    	
    	public void clearAll(){
    		imgBox.getChildren().clear();
    	}
    	
    	public void addSofaHandler(EventHandler<ActionEvent>handler){
    		sofaBtn.setOnAction(handler);
    	}
    	
    	public void addBedHandler(EventHandler<ActionEvent>handler){
    		bedBtn.setOnAction(handler);
    	}
    	
    	public void addBathHandler(EventHandler<ActionEvent>handler){
    		bathBtn.setOnAction(handler);
    	}
    	
    	public void addBookshelf(EventHandler<ActionEvent>handler){
    		bookshelfBtn.setOnAction(handler);
    	}
    	
    	public void addClearHandler(EventHandler<ActionEvent>handler){
    		clearBtn.setOnAction(handler);
    	}   	 
    	
    	public void addDragOverHandler(EventHandler<DragEvent>handler){  		
    		
    		board.getChildren().forEach(i -> {		    			
    			i.setOnDragOver(handler);
    		});
    		
    	}
    	
    	public void addDragDroppedHandler(EventHandler<DragEvent>handler){
    				
    		board.getChildren().forEach(i -> {
    			i.setOnDragDropped(handler);
    		});

    	}
    	
        public void addScrollHandler(EventHandler<ScrollEvent>handler){
        	board.setOnScroll(handler);
        }    
        
        public void addRotateHandler(EventHandler<ActionEvent>handler){
        	rotateBtn.setOnAction(handler);
        }
        
        public void addResetHandler(EventHandler<ActionEvent>handler){
        	clearboardBtn.setOnAction(handler);
        }
        
        public void addRotateBoardHandler(EventHandler<ActionEvent>handler){
        	rotateboardBtn.setOnAction(handler);
        }
        
        public void addCopyHandler(EventHandler<ActionEvent>handler){
        	copyBtn.setOnAction(handler);
        }
        
        public void addDeleteHandler(EventHandler<ActionEvent>handler){
        	deleteBtn.setOnAction(handler);
        }
        
    }
