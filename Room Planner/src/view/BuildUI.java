package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import model.Board;

/**
 * 
 * @author Sam Clark
 *
 * This class represents the user interface of the Room Planner. 
 * It initialises the components and their layout before they are 
 * displayed. 
 * 
 * @version 1.0
 */

public class BuildUI {

	private Board board;
	private GridPane floor;
	private Board copyBoard = new Board();
	private TilePane imgBox = new TilePane();
    private ScrollPane scroll = new ScrollPane(imgBox);
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
	private Button rotateBtn = new Button("Rotate Selection");
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
    private VBox granBox = new VBox();
    private Slider granularity = new Slider();
    private Label granLabel = new Label("Modify Granularity (Warning, Resets Board)");
    private int defaultColumn = 7;
    private int defaultRow = 7;
    /**
     * This constructs the user interface and by default sets the layouts
     * for the room category panes, the floor, the pallet and the buttons.
     */
    public BuildUI(){	 	
    	buildFloor(); 
    	buildRooms();
    	buildPallet();
    	buildButtons();
    } 
    
    /**
     * This builds each individual room category and populates each category
     * with the suitable furniture buttons. This is located left of the pallet.
     */
    
    public void buildRooms(){
    	
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
        
    }
    
    /**
     * This sets the positioning and layouts for the buttons and other 
     * interactive elements on the user interface that aren't located on 
     * the board.
     */
    
	public void buildButtons(){		
                  
        wood.setText("Wood");
        wood.setSelected(true);       
        stone.setText("Stone");     
        marble.setText("Marble");              	                 
        
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
        
        granularity.setMin(0);
        granularity.setMax(5);
        granularity.setMajorTickUnit(1);
        granularity.setMinorTickCount(0);
        granularity.setShowTickLabels(true);
        granularity.setShowTickMarks(true);
        granularity.setSnapToTicks(true);         
        
	}

	/**
	 * This sets the layouts for the pallet located left of the board.
	 */
	
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

	/**
	 * This initialises and sets the layouts for the board/floor.
	 */
	
	public void buildFloor(){
		
    	board = new Board();
    	floor = new GridPane();  	        	
    	board.setBoard(floor); 

    	board.createBoard(board, defaultColumn, defaultRow);
    	
    	board.setMaxWidth(950);
    	board.setMaxHeight(950);
    	board.setMinWidth(950);
    	board.setMinHeight(950);  
    	        	        	
	}

	/**
	 * This is the default method which is called by the AppLoader. It constructs
	 * each individual element on the user interface and displays them all on the 
	 * same HBox.
	 * @return
	 */
	
    public HBox buildView(){  	
    	    	    	    
    	saveButtons.getChildren().addAll(saveButton, loadButton);
        granBox.getChildren().addAll(granularity, granLabel);     
        leftbuttons.getChildren().addAll(clearBtn, furniture, rooms);        
        middleButtons.getChildren().addAll(rotateBtn, copyBtn, deleteBtn);
        rightButtons.getChildren().addAll(clearboardBtn, rotateboardBtn, gridLines, wood, stone, marble, saveButtons, granBox, copyBoard);  
    	box.getChildren().addAll(leftbuttons, scroll, middleButtons, board, rightButtons);
    	
        rightButtons.setPadding(new Insets(5, 5, 5, 5));
        rightButtons.setSpacing(10);
        leftbuttons.setPadding(new Insets(5, 5, 5, 5));
        leftbuttons.setSpacing(10);
        middleButtons.setPadding(new Insets(5, 5, 5, 5));
        middleButtons.setSpacing(10);

    	box.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 15;"
                + "-fx-border-radius: 2;");
    	        	
    	return box;
    	
	}
    /**
     * This returns the selection of room categories.
     * @return The selection of room categories
     */
    public ComboBox<String> getChoice(){
    	return furniture;
    }
    /**
     * This returns the granularity slider.
     * @return The slider
     */
    public Slider getSlider(){
    	return granularity;
    }
    /**
     * This returns the Bed Room display.
     * @return The bedroom pane
     */
    public Pane getBedroom(){
    	return bedroom;
    }
    /**
     * This returns the Living Room display.
     * @return The living room pane
     */
    public Pane getLivingroom(){
    	return livingroom;
    }
    /**
     * This returns the Kitchen display.
     * @return The kitchen pane
     */
    public Pane getKitchen(){
    	return kitchen;
    }
    /**
     * This returns the Bathroom display.
     * @return The bathroom pane
     */
    public Pane getBathroom(){
    	return bathroom;
    }
    /**
     * This returns the Miscellaneous display.
     * @return The miscellaneous pane
     */
    public Pane getMisc(){
    	return misc;
    }
    /**
     * This returns the Stone toggle.
     * @return The stone toggle
     */
    public Toggle getStone(){
    	return stone;
    }
    /**
     * This returns the Marble toggle.
     * @return the marble toggle
     */
    public Toggle getMarble(){
    	return marble;
    }
    /**
     * This returns the Wood toggle.
     * @return the wood toggle.
     */
    public Toggle getWood(){
    	return wood;
    }
    /**
     * This returns the Grid that items are positioned on when copied.
     * @return the copy board
     */
    public Board getCopyGrid(){
    	return copyBoard;
    }
    /**
     * This returns the floor grid that is used as the floor plan.
     * @return the board
     */
    public Board getGrid(){
    	return board;
    }
    /**
     * This adds all the images to the ImgBox which is the pallet.
     * @param image The images to be added.
     */
    public void addAll(ImageView[] image){
    	imgBox.getChildren().addAll(image);
    }
    /**
     * This clears all images from the ImgBox which is the pallet.
     */
    public void clearAll(){
    	imgBox.getChildren().clear();    	
    }
    /**
     * Attaches an event handler to the Choice Box.
     * @param handler The choice box handler
     */
    public void addChoiceBoxHandler(EventHandler<ActionEvent>handler){
    	furniture.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the Sofa button.
     * @param handler The sofa button handler
     */
    public void addSofaHandler(EventHandler<ActionEvent>handler){
    	sofaBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the bed button.
     * @param handler The bed button handler
     */    	
    public void addBedHandler(EventHandler<ActionEvent>handler){
    	bedBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the chair button.
     * @param handler The chair button handler
     */    	
    public void addChairHandler(EventHandler<ActionEvent>handler){
    	chairBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the desk button.
     * @param handler The desk button handler
     */    
    public void addDeskHandler(EventHandler<ActionEvent>handler){
    	deskBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the squaretable button.
     * @param handler The squaretable button handler
     */    
    public void addSquareTableHandler(EventHandler<ActionEvent>handler){
    	squaretableBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the side button.
     * @param handler The side button handler
     */    
    public void addSideboardHandler(EventHandler<ActionEvent>handler){
    	sideBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the drawer button.
     * @param handler The drawer button handler
     */    
    public void addDrawerHandler(EventHandler<ActionEvent>handler){
    	drawerBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the wardrobe button.
     * @param handler The wardrobe button handler
     */
    public void addWardrobeHandler(EventHandler<ActionEvent>handler){
    	wardrobeBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the pooltable button.
     * @param handler The pooltable button handler
     */
    public void addPoolTableHandler(EventHandler<ActionEvent>handler){
    	pooltableBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the rug button.
     * @param handler The rug button handler
     */
    public void addRugHandler(EventHandler<ActionEvent>handler){
    	rugBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the sideunit button.
     * @param handler The sideunit button handler
     */
    public void addVanityUnitHandler(EventHandler<ActionEvent>handler){
    	sideunitBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the deskchair button.
     * @param handler The deskchair button handler
     */
    public void addDeskchairHandler(EventHandler<ActionEvent>handler){
    	deskchairBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the fridge button.
     * @param handler The fridge button handler
     */
    public void addFridgeHandler(EventHandler<ActionEvent>handler){
    	fridgeBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the worktop button.
     * @param handler The worktop button handler
     */
    public void addWorkTopHandler(EventHandler<ActionEvent>handler){
    	worktopBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the table button.
     * @param handler The table button handler
     */
    public void addTableHandler(EventHandler<ActionEvent>handler){
    	tableBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the oven button.
     * @param handler The oven button handler
     */
    public void addOvenHandler(EventHandler<ActionEvent>handler){
    	ovenBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the shower button.
     * @param handler The shower button handler
     */
    public void addShowerHandler(EventHandler<ActionEvent>handler){
    	showerBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the tv button.
     * @param handler The tv button handler
     */
    public void addTvHandler(EventHandler<ActionEvent>handler){
    	tvBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the sink button.
     * @param handler The sink button handler
     */
    public void addSinkHandler(EventHandler<ActionEvent>handler){
    	sinkBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the bathroom sink button.
     * @param handler The bathroom sink button handler
     */
    public void addbSinkHandler(EventHandler<ActionEvent>handler){
    	bathroomsinkBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the bath button.
     * @param handler The bath button handler
     */
    public void addBathHandler(EventHandler<ActionEvent>handler){
    	bathBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the bookshelf button.
     * @param handler The bookshelf button handler
     */
    public void addBookshelf(EventHandler<ActionEvent>handler){
    	bookshelfBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the clear board button.
     * @param handler The clear board button handler
     */
    public void addClearHandler(EventHandler<ActionEvent>handler){
    	clearBtn.setOnAction(handler);
    }   	 
    /**
     * Attaches an event handler to the modify granularity slider.
     * @param handler the granularity slider handler
     */
    public void addSliderHandler(EventHandler<MouseEvent>handler){  	       	
    	granularity.setOnMouseClicked(handler);  	
    }
    /**
     * Attaches a drag over handler to each child element of the board.
     * @param handler
     */
    public void addDragOverHandler(EventHandler<DragEvent>handler){  		
    		
    	board.getChildren().forEach(i -> {		    			
    		i.setOnDragOver(handler);
    	});
    		
    }
    /**
     * Attaches a drag drop handler to each child element of the board.
     * @param handler
     */
    public void addDragDroppedHandler(EventHandler<DragEvent>handler){
    				
    	board.getChildren().forEach(i -> {
    		i.setOnDragDropped(handler);
    	});

    }
    /**
     * Attaches a scroll handler to the board.
     * @param handler 
     */
    public void addScrollHandler(EventHandler<ScrollEvent>handler){
       	board.setOnScroll(handler);
    }    
    /**
     * Attaches an event handler to the Rotate Item button.
     * @param handler
     */
    public void addRotateHandler(EventHandler<ActionEvent>handler){
    	rotateBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the Reset Board button.
     * @param handler
     */
    public void addResetHandler(EventHandler<ActionEvent>handler){
        clearboardBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the Rotate Board button.
     * @param handler
     */
    public void addRotateBoardHandler(EventHandler<ActionEvent>handler){
        rotateboardBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the copy item button.
     * @param handler
     */
    public void addCopyHandler(EventHandler<ActionEvent>handler){
       	copyBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the delete item handler;   
     * @param handler
     */
    public void addDeleteHandler(EventHandler<ActionEvent>handler){
        deleteBtn.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the toggle grid lines handler.
     * @param handler
     */
    public void addToggleGridHandler(EventHandler<ActionEvent>handler){
    	gridLines.setOnAction(handler);
    }    
    /**
     * Attaches an event handler to the Stone toggle.
     * @param handler
     */
    public void addStoneHandler(EventHandler<ActionEvent> handler){
    	stone.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the Wood toggle.
     * @param handler
     */
    public void addWoodListener(EventHandler<ActionEvent> handler){
    	wood.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the Marble toggle.
     * @param handler
     */
    public void addMarbleListener(EventHandler<ActionEvent> handler){
    	marble.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the Save board handler.
     * @param handler
     */
    public void addSaveHandler(EventHandler<ActionEvent> handler){
    	saveButton.setOnAction(handler);
    }
    /**
     * Attaches an event handler to the Load board handler.
     * @param handler
     */
    public void addLoadHandler(EventHandler<ActionEvent> handler){
    	loadButton.setOnAction(handler);
    }
}
