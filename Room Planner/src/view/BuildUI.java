package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import model.Board;

public class BuildUI {

	private Board board;
	private GridPane floor;
	private Board copyBoard = new Board();
	private GridPane modifyBoard;
	private TilePane imgBox = new TilePane();
    private ScrollPane scroll = new ScrollPane(imgBox);
	private Label columnLabel = new Label("Columns:");
	private Label rowLabel = new Label("Rows:");
	private NumberTextField columnText = new NumberTextField();
	private NumberTextField rowText = new NumberTextField();
	private NumberTextField widthText = new NumberTextField();
	private NumberTextField heightText = new NumberTextField();
	private Button sofaBtn = new Button("Sofa");
	private Button bedBtn = new Button("Bed");
	private Button bookshelfBtn = new Button("Book Shelf");
	private Button wardrobeBtn = new Button("Wardrobe");
	private Button deskBtn = new Button("Desk");
	private Button tvBtn = new Button("TV");
	private Button deskchairBtn = new Button("Desk Chair");
	private Button drawerBtn = new Button("Drawer");
	private Button tableBtn = new Button("Table");
	private Button chairBtn = new Button("Sofa Chair");
	private Button squaretableBtn = new Button("Square Table");
	private Button sideBtn = new Button("Sideboard");
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
	private Button sinkBtn = new Button("Sink");
	private Button fridgeBtn = new Button("Fridge");
	private Button worktopBtn = new Button("Work Top");
	private Button ovenBtn = new Button("Oven");
	private Button bathBtn = new Button("Bath");
	private Button showerBtn = new Button("Shower");
	private Button bathroomsinkBtn = new Button("Bathroom Sink");
	private Button sideunitBtn = new Button("Vanity Unit");
	private Button rugBtn = new Button("Rug");
	private Button pooltableBtn = new Button("Pool Table");
    private HBox box = new HBox(5);
    private VBox leftbuttons = new VBox();
    private VBox rightButtons = new VBox();
    private VBox middleButtons = new VBox();
    private VBox saveButtons = new VBox();
    private RadioButton wood = new RadioButton();
    private RadioButton stone = new RadioButton();
    private RadioButton marble = new RadioButton();    
    private StackPane rooms = new StackPane();
    private Pane bedroom = new Pane();
    private Pane kitchen = new Pane();
    private Pane livingroom = new Pane();
    private Pane bathroom = new Pane();
    private Pane misc = new Pane();
    private int btnOffset = 35;
    private ComboBox<String> furniture = new ComboBox<String>();  
    
    public BuildUI(){	
    	    	
    	buildFloor();  	
    	buildPallet();
    	buildButtons();
    } 
    
	public void buildButtons(){		
    	
    	modifyBoard = new GridPane();
        modifyBoard.add(columnLabel, 0, 0);
        modifyBoard.add(columnText, 1, 0);
        modifyBoard.add(rowLabel, 0, 1);
        modifyBoard.add(rowText, 1, 1);
        modifyBoard.setHgap(10);
        modifyBoard.setVgap(5);    
        
        String columnString = Integer.toString(board.getColumn());
        String rowString = Integer.toString(board.getRow());
        
        columnText.setText(columnString);
        rowText.setText(rowString);
        
        wood.setText("Wood");
        wood.setSelected(true);       
        stone.setText("Stone");     
        marble.setText("Marble");
                		
        saveButtons.getChildren().addAll(saveButton, loadButton);                 
        furniture.getItems().addAll("Furniture Selection","Bedroom", "Living Room", "Bathroom", "Kitchen", "Misc");
        furniture.getSelectionModel().selectFirst();
        
        bathroom.getChildren().addAll(sideunitBtn, bathroomsinkBtn, bathBtn, showerBtn);
        bathroom.setVisible(false);
        kitchen.getChildren().addAll(sinkBtn, ovenBtn, worktopBtn, fridgeBtn); 
        kitchen.setVisible(false);           
        bedroom.getChildren().addAll(bedBtn, deskBtn, deskchairBtn, drawerBtn, wardrobeBtn);
        bedroom.setVisible(false);
        livingroom.getChildren().addAll(sofaBtn, squaretableBtn, chairBtn, tableBtn, tvBtn, sideBtn);
        livingroom.setVisible(false);      
        misc.getChildren().addAll(pooltableBtn, rugBtn);
        misc.setVisible(false);
        
        rooms.getChildren().addAll(bedroom, kitchen, bathroom, livingroom, misc);       
        rooms.setPickOnBounds(false);
                       
        for(int i = 0; i < bedroom.getChildren().size(); i++){
        	bedroom.getChildren().get(i).setLayoutY(i*btnOffset);
        }
        
        for(int i = 0; i < kitchen.getChildren().size(); i++){
        	kitchen.getChildren().get(i).setLayoutY(i*btnOffset);
        }
        
        for(int i = 0; i < livingroom.getChildren().size(); i++){
        	livingroom.getChildren().get(i).setLayoutY(i*btnOffset);
        }
        
        for(int i = 0; i < bathroom.getChildren().size(); i++){
        	bathroom.getChildren().get(i).setLayoutY(i*btnOffset);
        }
        
        for(int i = 0; i < misc.getChildren().size(); i++){
        	misc.getChildren().get(i).setLayoutY(i*btnOffset);
        }
        
        leftbuttons.getChildren().addAll(clearBtn, furniture, rooms);        
        middleButtons.getChildren().addAll(rotateBtn, copyBtn, deleteBtn, widthText, heightText, resizeBtn);
        rightButtons.getChildren().addAll(clearboardBtn, rotateboardBtn, gridLines, modifyBoard, modifyGran, wood, stone, marble, copyBoard, saveButtons);        

        rightButtons.setPadding(new Insets(5, 5, 5, 5));
        rightButtons.setSpacing(10);
        leftbuttons.setPadding(new Insets(5, 5, 5, 5));
        leftbuttons.setSpacing(10);
        middleButtons.setPadding(new Insets(5, 5, 5, 5));
        middleButtons.setSpacing(10);
        
	}

	public void buildPallet(){
		
        imgBox.setHgap(5.0);
        imgBox.setVgap(5.0);
        imgBox.setPadding(new Insets(5, 5, 5, 5));
        imgBox.setMinWidth(250);
        imgBox.setMaxWidth(250);

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setMinWidth(250);
        scroll.setMaxWidth(250);
        
	}

	public void buildFloor(){
		
    	board = new Board();
    	floor = new GridPane();
    	        	
    	board.setColumn(11);
    	board.setRow(11); 	 
    	
    	int column = board.getColumn();
    	int row = board.getRow();
    	board.createBoard(board, column, row);
    	
    	board.setMaxWidth(950);
    	board.setMaxHeight(950);
    	board.setMinWidth(950);
    	board.setMinHeight(950);
    	
    	board.setFloor(board);           	
    	board.setBoard(floor);   
    	        	        	
	}

    public HBox buildView(){  	
    	    	    	    	
    	box.getChildren().addAll(leftbuttons, scroll, middleButtons, board, rightButtons);

    	box.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 15;"
                + "-fx-border-radius: 2;");
    	        	
    	return box;
    	
	}
    
    public ComboBox<String> getChoice(){
    	return furniture;
    }
    
    public Pane getBedroom(){
    	return bedroom;
    }
    
    public Pane getLivingroom(){
    	return livingroom;
    }
    
    public Pane getKitchen(){
    	return kitchen;
    }
    
    public Pane getBathroom(){
    	return bathroom;
    }
    
    public Pane getMisc(){
    	return misc;
    }
    
    public String getColumnText(){
    	return columnText.getText();
    }
    
    public String getRowText(){
    	return rowText.getText();
    }
    
    public Toggle getStone(){
    	return stone;
    }
    
    public Toggle getMarble(){
    	return marble;
    }
    
    public Toggle getWood(){
    	return wood;
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
    	
    public void addChoiceBoxHandler(EventHandler<ActionEvent>handler){
    	furniture.setOnAction(handler);
    }
    
    public void addSofaHandler(EventHandler<ActionEvent>handler){
    	sofaBtn.setOnAction(handler);
    }
    	
    public void addBedHandler(EventHandler<ActionEvent>handler){
    	bedBtn.setOnAction(handler);
    }
    	
    public void addChairHandler(EventHandler<ActionEvent>handler){
    	chairBtn.setOnAction(handler);
    }
    
    public void addDeskHandler(EventHandler<ActionEvent>handler){
    	deskBtn.setOnAction(handler);
    }
    
    public void addSquareTableHandler(EventHandler<ActionEvent>handler){
    	squaretableBtn.setOnAction(handler);
    }
    
    public void addSideboardHandler(EventHandler<ActionEvent>handler){
    	sideBtn.setOnAction(handler);
    }
    
    public void addDrawerHandler(EventHandler<ActionEvent>handler){
    	drawerBtn.setOnAction(handler);
    }
    
    public void addWardrobeHandler(EventHandler<ActionEvent>handler){
    	wardrobeBtn.setOnAction(handler);
    }
    
    public void addPoolTableHandler(EventHandler<ActionEvent>handler){
    	pooltableBtn.setOnAction(handler);
    }
    
    public void addRugHandler(EventHandler<ActionEvent>handler){
    	rugBtn.setOnAction(handler);
    }
    
    public void addVanityUnitHandler(EventHandler<ActionEvent>handler){
    	sideunitBtn.setOnAction(handler);
    }
    
    public void addDeskchairHandler(EventHandler<ActionEvent>handler){
    	deskchairBtn.setOnAction(handler);
    }
    
    public void addFridgeHandler(EventHandler<ActionEvent>handler){
    	fridgeBtn.setOnAction(handler);
    }
    
    public void addWorkTopHandler(EventHandler<ActionEvent>handler){
    	worktopBtn.setOnAction(handler);
    }
    
    public void addTableHandler(EventHandler<ActionEvent>handler){
    	tableBtn.setOnAction(handler);
    }
    
    public void addOvenHandler(EventHandler<ActionEvent>handler){
    	ovenBtn.setOnAction(handler);
    }
    
    public void addShowerHandler(EventHandler<ActionEvent>handler){
    	showerBtn.setOnAction(handler);
    }
    
    public void addTvHandler(EventHandler<ActionEvent>handler){
    	tvBtn.setOnAction(handler);
    }
    
    public void addSinkHandler(EventHandler<ActionEvent>handler){
    	sinkBtn.setOnAction(handler);
    }
    
    public void addbSinkHandler(EventHandler<ActionEvent>handler){
    	bathroomsinkBtn.setOnAction(handler);
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
    
    public void addToggleGridHandler(EventHandler<ActionEvent>handler){
    	gridLines.setOnAction(handler);
    }
    
    public void addModifyGranularity(EventHandler<ActionEvent>handler){
    	modifyGran.setOnAction(handler);
    }
    
    public void addStoneHandler(EventHandler<ActionEvent> handler){
    	stone.setOnAction(handler);
    }
    
    public void addWoodListener(EventHandler<ActionEvent> handler){
    	wood.setOnAction(handler);
    }
    
    public void addMarbleListener(EventHandler<ActionEvent> handler){
    	marble.setOnAction(handler);
    }
        
    public void addSaveHandler(EventHandler<ActionEvent> handler){
    	saveButton.setOnAction(handler);
    }
    
}
