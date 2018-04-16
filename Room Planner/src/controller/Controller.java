package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import model.Board;
import model.Group;
import model.LocatedImage;
import model.Pallet;
import view.BuildUI;

/**
 * 
 * @author Sam Clark
 *
 * This class controls all the user interaction on the board and all the respective
 * event handlers for each component. The methods for all the user driven controls 
 * are calculated by the controller, including the board and the buttons.
 *
 * @version 1.0
 */

public class Controller {

	private BuildUI view;
	private Pallet pallet;
	private ImageView[] images;
	private Board board;
	private Group group;
    private final DataFormat imageFormat = new DataFormat("MyImage");
    private DragDetected dragdetected = new DragDetected();
    private ImageView draggingImage;  
	private Integer[] bCoords = new Integer[2];
	private Integer[] aCoords = new Integer[2];
	private Integer[] mCoords = new Integer[2];
	private Integer[] nCoords = new Integer[2];
	private Integer[] a2Coords = new Integer[2];
	private Integer[] newCoords = new Integer[2];
	private Integer[] granularityCoords = new Integer[2];
	
	/**
	 * This initialises the controller with the three models and
	 * the view. It also attaches the event handler method.
	 * 
	 * @param view The user interface
	 * @param board The board model
	 * @param pallet The pallet model
	 * @param group The group model
	 */
	
	public Controller(BuildUI view, Board board, Pallet pallet, Group group){
		this.view = view;
		this.board = board;
		this.pallet = pallet;
		this.group = group;
		this.attachEventHandler();
	}
	/**
	 * This attaches each individual event handler in the view
	 * to the controller.
	 */
	private void attachEventHandler(){
		
		view.addSofaHandler(this::addSofaHandling);
		view.addBedHandler(this::addBedHandling);
		view.addBathHandler(this::addBathHandling);
		view.addBookshelf(this::addBookshelfHandling);
		view.addClearHandler(this::clearPalletHandling);
		view.addDragOverHandler(this::addDragOverHandling);
		view.addDragDroppedHandler(this::addDragDroppedHandling);
		view.addScrollHandler(this::addScrollHandling);
		view.addRotateHandler(this::addRotationHandling);
		view.addResetHandler(this::clearBoardHandling);
		view.addCopyHandler(this::copyItemHandling);
		view.addDeleteHandler(this::deleteItemHandling);
		view.addChairHandler(this::addChairHandling);
		view.addDeskHandler(this::addDeskHandling);
		view.addFridgeHandler(this::addFridgeHandling);
		view.addTableHandler(this::addLongtableHandling);
		view.addOvenHandler(this::addOvenHandling);
		view.addShowerHandler(this::addShowerHandling);
		view.addSinkHandler(this::addSinkHandling);
		view.addbSinkHandler(this::addSink1Handling);
		view.addDeskchairHandler(this::addDchairHandling);
		view.addTvHandler(this::addTVHandling);
		view.addSquareTableHandler(this::addSquareTableHandling);
		view.addSideboardHandler(this::addSideBoardHandling);
		view.addWardrobeHandler(this::addWardrobeHandling);
		view.addDrawerHandler(this::addDrawerHandling);
		view.addVanityUnitHandler(this::addVanityUnitHandling);
		view.addWorkTopHandler(this::addWorkTopHandling);
		view.addPoolTableHandler(this::addPoolTableHandling);
		view.addRugHandler(this::addRugHandling);
		view.addSliderHandler(this::addSliderHandling);
		view.addLoadHandler(this::loadFileHandling);
		view.addRotateBoardHandler(this::rotateBoardHandling);
		view.addSaveHandler(arg0 -> {
			try {
				saveBoardHandling(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});	
	}
	/**
	 * Creates a new Sofa Image
	 * @param event on button pressed
	 */
    private void addSofaHandling(ActionEvent event){
    	Image sofa = new LocatedImage("file:sofa.png");
    	addFurniture(sofa);   	
    }
	/**
	 * Creates a new side board Image
	 * @param event on button pressed
	 */  
    private void addSideBoardHandling(ActionEvent event){
    	Image sideboard = new LocatedImage("file:sideboard.png");
    	addFurniture(sideboard);     
    }
	/**
	 * Creates a new square table Image
	 * @param event on button pressed
	 */
    private void addSquareTableHandling(ActionEvent event){
    	Image squaretable = new LocatedImage("file:squaretable.png");
    	addFurniture(squaretable); 
    }
	/**
	 * Creates a new tv Image
	 * @param event on button pressed
	 */
    private void addTVHandling(ActionEvent event){
    	Image tv = new LocatedImage("file:tv.png");
    	addFurniture(tv);   	    	
    }
	/**
	 * Creates a new pool table Image
	 * @param event on button pressed
	 */
    private void addPoolTableHandling(ActionEvent event){
    	Image pooltable = new LocatedImage("file:pooltable.png");
    	addFurniture(pooltable);   
    }
	/**
	 * Creates a new rug Image
	 * @param event on button pressed
	 */
    private void addRugHandling(ActionEvent event){
    	Image rug = new LocatedImage("file:rug.png");
    	addFurniture(rug);   
    }
	/**
	 * Creates a new work top Image
	 * @param event on button pressed
	 */
    private void addWorkTopHandling(ActionEvent event){
    	Image worktop = new LocatedImage("file:worktop.png");
    	addFurniture(worktop);   	    	
    }
	/**
	 * Creates a new wardrobe Image
	 * @param event on button pressed
	 */
    private void addWardrobeHandling(ActionEvent event){
    	Image wardrobe = new LocatedImage("file:wardrobe.png");
    	addFurniture(wardrobe);   	    	
    }
	/**
	 * Creates a new drawer Image
	 * @param event on button pressed
	 */
    private void addDrawerHandling(ActionEvent event){
    	Image drawer = new LocatedImage("file:drawer.png");
    	addFurniture(drawer);   	    	
    }
	/**
	 * Creates a new chair Image
	 * @param event on button pressed
	 */
    private void addChairHandling(ActionEvent event){
    	Image chair = new LocatedImage("file:chair.png");
    	addFurniture(chair);   	    	
    }
	/**
	 * Creates a new desk chair Image
	 * @param event on button pressed
	 */
    private void addDchairHandling(ActionEvent event){
    	Image dchair = new LocatedImage("file:dchair.png");
    	addFurniture(dchair);   	    	
    }
	/**
	 * Creates a new desk Image
	 * @param event on button pressed
	 */
    private void addDeskHandling(ActionEvent event){
    	Image desk = new LocatedImage("file:desk.png");
    	addFurniture(desk);
    }
	/**
	 * Creates a new fridge Image
	 * @param event on button pressed
	 */
    private void addFridgeHandling(ActionEvent event){
    	Image fridge = new LocatedImage("file:fridge.png");
    	addFurniture(fridge);
    }
	/**
	 * Creates a new long table Image
	 * @param event on button pressed
	 */
    private void addLongtableHandling(ActionEvent event){
    	Image ltable = new LocatedImage("file:ltable.png");
    	addFurniture(ltable);
    }
	/**
	 * Creates a new vanity unit Image
	 * @param event on button pressed
	 */
    private void addVanityUnitHandling(ActionEvent event){
    	Image vanityunit = new LocatedImage("file:vanityunit.png");
    	addFurniture(vanityunit);
    }
	/**
	 * Creates a new oven Image
	 * @param event on button pressed
	 */
    private void addOvenHandling(ActionEvent event){
    	Image oven = new LocatedImage("file:oven.png");
    	addFurniture(oven);
    }
	/**
	 * Creates a new shower Image
	 * @param event on button pressed
	 */
    private void addShowerHandling(ActionEvent event){
    	Image shower = new LocatedImage("file:shower.png");
    	addFurniture(shower);
    }
	/**
	 * Creates a new sink Image
	 * @param event on button pressed
	 */
    private void addSinkHandling(ActionEvent event){
    	Image sink = new LocatedImage("file:sink.png");
    	addFurniture(sink);
    }
	/**
	 * Creates a new sink Image
	 * @param event on button pressed
	 */
    private void addSink1Handling(ActionEvent event){
    	Image sink1 = new LocatedImage("file:sink1.png");
    	addFurniture(sink1);
    }
	/**
	 * Creates a new bed Image
	 * @param event on button pressed
	 */
    private void addBedHandling(ActionEvent event){
    	Image bed = new LocatedImage("file:bed.png");
    	addFurniture(bed);   	    	
    }
	/**
	 * Creates a new bath Image
	 * @param event on button pressed
	 */
	private void addBathHandling(ActionEvent event){
    	Image bath = new LocatedImage("file:bath.png");
    	addFurniture(bath);   	    	
    }
	/**
	 * Creates a new bookshelf Image
	 * @param event on button pressed
	 */
    private void addBookshelfHandling(ActionEvent event){
    	Image bookshelf = new LocatedImage("file:bookshelf.png");
    	addFurniture(bookshelf);   	    	
    }
    /**
     * This add the image to the pallet model and updates the view.
     * @param furniture Image to be added
     */
    private void addFurniture(Image furniture){
    	pallet.addImage(furniture);
    	images = updatePallet();
    	view.addAll(images);
    }
    /**
     * This takes the Image to be added and makes it into an ImageView.
     * The image is formatted and handlers are attached so it can be dragged
     * hovered and selected.
     * @return The image to be added to the pallet.
     */
    private ImageView[] updatePallet() {
    	
    	System.out.println("Pallet Updated");
        images = new ImageView[pallet.size()];
        
        for (int j = 0; j < images.length; j++) {
        	images[j] = new ImageView(pallet.getAllImages().get(j));
        	pallet.makeImageView(images[j]);
        	selectImage(images[j]);
        	hoverImage(images[j]);
        	dragdetected.dragImage(images[j]);
        }     
        
        pallet.clear();
		return images;     
		
    }
    /**
     * Clears the pallet model and the view pallet.
     * @param event On Button pressed
     */
    private void clearPalletHandling(ActionEvent event){
    	pallet.clear();
    	view.clearAll();
    }
    /**
     * Creates a move cursor when the image is hovered over.
     * @param image The image hovered over.
     */
	private void hoverImage(ImageView image) {
		image.setOnMouseEntered(i -> {
			image.setCursor(Cursor.MOVE);
		});
	}
	/**
	 * Allows the image to be added or removed from the group
	 * model, Shift to add and Ctrl to remove.
	 * @param The image selected.
	 */
	private void selectImage(ImageView image){
		
		image.setOnMousePressed(i -> {
												        			
			if(!i.isShiftDown() && !i.isControlDown()){
				group.clearGroup();
			}			
			if(group.getGroup().isEmpty()){
				group.addItem(image);
				System.out.print("Adding Item\n");
			}
			else if (i.isShiftDown()){
				group.addItem(image);
				System.out.print("Adding Item\n");
			} 
			else if (i.isControlDown() && group.groupContains(image)) {
				group.removeItem(image);
				System.out.print("Removing Item\n");
			}   			
			
			group.groupLog();
			
			System.out.println("Items in Model: " + group.groupSize());
			 			
		});
		    		
	}	
	/**
	 * 
	 * @author Sam Clark
	 * This class allows the image to be dragged and moved.
	 * @version 1.0
	 */
    public class DragDetected {     	        	
        
    	/**
    	 * Attaches an event handler to the image when dragged.
    	 * @param The image that is dragged.
    	 */
    	private void dragImage(final ImageView image){
    		image.setOnDragDetected(onDragDetectedEventHandler);
    	}
    	/**
    	 * Calls the moveImage method on drag detected.
    	 */
    	EventHandler<MouseEvent> onDragDetectedEventHandler = event -> {
    			moveImage();
    	};      	
    }   
    /**
     * Allows the board to take drag events, displays this visually.
     * @param e The drag event.
     */
    private void addDragOverHandling(DragEvent e){   	
    		Dragboard dImage = e.getDragboard(); //dImage is the dragboard being dragged
    		if(dImage.hasContent(imageFormat) && draggingImage != null) { // if the selected image is being dragged and isn't null
    			e.acceptTransferModes(TransferMode.MOVE); //Accepts the moving transfermode of the drag
    		}	
    }
    /**
     * Allows the Image to be dragged, displays an image next to the cursor of the image being dragged.
     * Stores the coordinates where the drag event originated from if the drag occurred on the grid.
     */
    public void moveImage(){
		for(ImageView image : group.getGroup()){    
			
			board = view.getGrid();
			        			   			
			Dragboard dImage = image.startDragAndDrop(TransferMode.MOVE); 
			dImage.setDragView(image.snapshot(null, null));       			
			ClipboardContent cc = new ClipboardContent();
			cc.put(imageFormat, " "); 
			dImage.setContent(cc); 
			draggingImage = image;
			
			if(image.getParent() instanceof StackPane){				
				int beforeColumn = board.getColumnInd(image.getParent());
	       		int beforeRow = board.getRowInd(image.getParent());  
	       		setbCoords(beforeColumn, beforeRow);          	                	
			}   	     			        			     			            		
		}
    }
    /**
     * Sets the initial coordinates where the drag view image was dragged from.
     * @param column The column number
     * @param row The row number
     * @return the column and row number
     */
	public Integer[] setbCoords(int column, int row){			
		bCoords[0] = column;
		bCoords[1] = row;	
		return bCoords;
	}	
    /**
     * Sets the coordinates that the drag view image was dragged to
     * @param column The column number
     * @param row The row number
     * @return the column and row number
     */
	public Integer[] setaCoords(int column, int row){
		aCoords[0] = column;
		aCoords[1] = row;		
		return aCoords;
    }
	/**
	 * Sets the coordinates for all the grouped items before the movement, excluding the dragview.
	 * The drag view image's coordinates are calculated from after the movement. 
	 * @param column The column number
	 * @param row The row number
	 * @return the column and row number
	 */
	public Integer[] seta2Coords(int column, int row){
		a2Coords[0] = column;
		a2Coords[1] = row;
		return a2Coords;
	}
    /**
     * Calculates difference in columns and rows from the initial coordinates to
     * the final coordinates
     * @return the column and row number
     */
	public Integer[] calculateMoveDistance(){
    	mCoords[0] = aCoords[0] - bCoords[0];
    	mCoords[1] = aCoords[1] - bCoords[1];
    	return mCoords;
    }
    /**
     * Calculates the coordinates of the drag view image if it is dragged onto another
     * grouped image.
     * @return the column and row number
     */
	public Integer[] calculateReplaceCoords(){
    	nCoords[0] = aCoords[0] - mCoords[0];
    	nCoords[1] = aCoords[1] - mCoords[1];
    	return nCoords;
    }
    /**
     * Calculates the new coordinates for each image in the group after a drag view is dropped.
     * @return the column and row number
     */
	public Integer[] calculateNewCoords(){
    	newCoords[0] = a2Coords[0] + mCoords[0];
    	newCoords[1] = a2Coords[1] + mCoords[1];
    	return newCoords;
    }
    /**
     * Adds the dragged image to the pane where the image is dropped.
     * 
     * Initialises coordinate locations from the dragged image and the grouped images and calculates
     * the geometric movement based on the grid coordinates and image coordinates. Images can move, 
     * swap and avoid null locations.
     * @param e the drag dropped event
     */
    private void addDragDroppedHandling(DragEvent e){
    	
		Dragboard dImage = e.getDragboard(); 
		
		if (dImage.hasContent(imageFormat)) { 
									
			((Pane)draggingImage.getParent()).getChildren().remove(draggingImage);				
			((StackPane)e.getSource()).getChildren().add(draggingImage);					
			e.setDropCompleted(true); 			
			draggingImage = null;
			
		}						  			
					
		aCoords[0] = board.getColumnInd((Node) e.getSource());
		aCoords[1] = board.getRowInd((Node) e.getSource()); 
					
		System.out.println("aColumn: " + aCoords[0] + " aRow: " + aCoords[1]);
			
		setaCoords(aCoords[0], aCoords[1]);
		
		calculateMoveDistance();	
		
		System.out.println("mColumn: " + mCoords[0] + " mRow: " + mCoords[1]);
		boolean overlap = false;
		int index = 0;  			
		board = view.getGrid();
		for(ImageView image : group.getGroup()){   	
				   					
   			int size = group.groupSize() - 2;
				
            if(index <= size){           
       					
       	        if(group.groupSize() > 1){ 
       	               		   	
       				StackPane multipleImage = (StackPane) getNode(board, aCoords[0], aCoords[1]);
       	   	               		               	        	
       	   	        if(multipleImage.getChildren().contains(image)){
       	   	               			
       	   	        	calculateReplaceCoords();
       	   	               		
       	   	            StackPane splitPane = ((StackPane) getNode(board, nCoords[0], nCoords[1])); 
       	   	            splitPane.getChildren().remove(multipleImage.getChildren().get(0));
       	   	            splitPane.getChildren().add(multipleImage.getChildren().get(0));      	
       	   	               		
                        overlap = true;                         		
       	   	        }
       	   	        
       	   	        index++;  
       	        }
            }
		}	
			  				
       	for(ImageView image : group.getGroup()){        
       		        
       		a2Coords[0] = board.getColumnInd(image.getParent());
       		a2Coords[1] = board.getRowInd(image.getParent());
       		     
    		System.out.println("a2Column: " + a2Coords[0] + " a2Row: " + a2Coords[1]);
       		
       		calculateNewCoords();
       		
       		if(group.groupSize() > 1){              			

       			if ((a2Coords[0] != aCoords[0] || a2Coords[1] != aCoords[1]) && overlap == false){     		       				       	           				       			
       				   				 				
       				StackPane groupMove = (StackPane) getNode(board, newCoords[0], newCoords[1]);
       				groupMove.getChildren().add(image);
                  		                	
               	}          		
       		}
       	}      	  	
    }
    /**
     * Returns the node at the specified location on the board.
     * @param board The board to be searched
     * @param col The column number to search
     * @param row The row number to look search
     * @return The node that is located, or null if none present.
     */
	public Node getNode(GridPane board, int col, int row){
		
		for(Node node : board.getChildren()) {
			if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}
	/**
	 * Allows images to be increased and decreased in size through the scroll wheel. Upper bound of 150 and lower bound of 50.
	 * @param event Scroll event
	 */
	private void addScrollHandling(ScrollEvent event) {
		
   		double zoom = 1.02;
		double deltaY = event.getDeltaY();
		
			if (deltaY < 0) {
				zoom = 2.0 - zoom;
			}
			
			for(ImageView node : group.getGroup()) {
				
				double scaleX = node.getFitHeight();
				double scaleY = node.getFitWidth();
				
				if (scaleY < 50 || scaleX < 50){
					node.setFitHeight(51);
					node.setFitWidth(51);
				}
				
				else if (scaleX > 150 || scaleY > 150){
					node.setFitHeight(149);
					node.setFitWidth(149);
				}
				
				else if (scaleX > 50  && scaleY > 50) {		
					
					if(node.getFitHeight() < 150  && node.getFitWidth() < 150) {				
						node.setFitHeight(scaleX * zoom);
						node.setFitWidth(scaleY * zoom);
						System.out.println("Height: " + node.getFitHeight() + " Width: " + node.getFitWidth() + " Zoom: " + zoom + " deltaY: " + deltaY);
					}
				}
			}
		}
	/**
	 * Performs a single rotation of 90 degrees for one ImageView.
	 */
	public void singleRotation(){
		
		for(ImageView node : group.getGroup()) {   				   							
			node.setPreserveRatio(true);   					    
		    node.getStyleClass().remove("highlight");
			System.out.println("rotating image");
			SnapshotParameters parameters = new SnapshotParameters();
			parameters.setFill(Color.TRANSPARENT);
			parameters.setTransform(new Rotate(90, node.getFitHeight() / 2, node.getFitWidth() / 2));  			
			Image snapshot = node.snapshot(parameters, null);   	
			node.setImage(snapshot); 				
			node.getStyleClass().add("highlight");
		}			
	}
	/**
	 * Allows a group of images to be rotated around a center axis by 90 degrees.
	 * New ImageView coordinates are calculated based on the center axis coordinates.
	 */
	public void groupRotation(){
		
		board = view.getGrid();	
		
		ArrayList<Integer> xCoords = new ArrayList<Integer>();
		ArrayList<Integer> yCoords = new ArrayList<Integer>();
			
		int centerX = 0;
		int centerY = 0;
		
		for(ImageView node : group.getGroup()) {   				   					
			
			if(group.groupSize() > 1){
				
				int imageColumn = board.getColumnInd(node.getParent());					
				int imageRow = board.getRowInd(node.getParent());
				
				xCoords.add(imageColumn);
				yCoords.add(imageRow);    							
							    							
				System.out.println("Rotated Image Column: " + imageColumn + " Row: " + imageRow);
							
				}    						      				     				
			}    			
				
			if(group.groupSize() > 1){
					    				
    			int xCenter = (xCoords.size() + 1) / 2;
    			int yCenter = (yCoords.size() + 1) / 2;		
    			
        		centerX = xCoords.get(xCenter);
        		centerY = yCoords.get(yCenter); 		
    							     				       				   	
    			System.out.println("Center Axis Column: " + centerX + " Row: " + centerY);
    				
			}
			    				     		   				   				    				    				
			for(ImageView node : group.getGroup()){
					   					
				if(group.groupSize() > 1){
					
					int imageColumn = board.getColumnInd(node.getParent());
					int imageRow = board.getRowInd(node.getParent());
					
					int dx = centerX - imageColumn;
					int dy = centerY - imageRow;
							
					int newPosX = 0;
					int newPosY = 0;		
							
					newPosX = centerX + dy;
					newPosY = centerY - dx;			
							
					if(newPosX < 0){
						newPosX = 1;
					} else if(newPosX > (board.getColumn() - 1)){
						newPosX = board.getColumn() - 2;
					}
					
					if(newPosY < 0){
						newPosY = 1;
					} else if(newPosY > (board.getRow() - 1)){
						newPosY = board.getRow() - 2;
					}
					
	                StackPane groupMove = (StackPane) getNode(board, newPosX, newPosY);
	                   	                    
	                groupMove.getChildren().remove(node);                        			
	                groupMove.getChildren().add(node);   											    																							
				}				
			} 	 
	}
	/**
	 * Rotation handling attaching the single rotation and grouped rotation together.
	 * @param event action event
	 */
	private void addRotationHandling(ActionEvent event){
		
		singleRotation();
		groupRotation();
			 		
	}	    
	/**
	 * Rotate board handling
	 * @param event action event
	 */
    private void rotateBoardHandling(ActionEvent event){
    	rotateBoard();
    }
    /**
     * Rotate the board by 90 degrees.
     */
    public void rotateBoard(){
    	board = view.getGrid();
    	board.setRotate(board.getRotate() + 90);
    }
	/**
	 * Calls the reset board method, recreate the board and attach the drag and drop handlers.
	 * @param event action event
	 */
	private void clearBoardHandling(ActionEvent event){
		resetBoard();
		board = view.getGrid();
		board.createBoard(board, board.getColumn(), board.getRow());
		view.addDragOverHandler(this::addDragOverHandling);
		view.addDragDroppedHandler(this::addDragDroppedHandling);
	}
	/**
	 * Delete all child elements of the board.
	 */
    private void resetBoard(){
    	System.out.println("Board Cleared");
    	board = view.getGrid();
    	board.deleteBoard();
    }
    /**
     * Sets the granularity, recreates the board and attaches the drag and drop handlers.
     * @param event mouse event
     */
	private void addSliderHandling(MouseEvent event){
		board = view.getGrid();
		
		setGranularity();		
		resetBoard();
		
		board.createBoard(board, granularityCoords[0], granularityCoords[1]);	
		view.addDragOverHandler(this::addDragOverHandling);
		view.addDragDroppedHandler(this::addDragDroppedHandling);
	}
	/**
	 * Sets the granularity of the board based on the slider value located on the view.
	 * @return The granularity in columns and rows.
	 */
	public Integer[] setGranularity(){
		
		granularityCoords[0] = 0;
		granularityCoords[1] = 0;
		
		int count = (int) view.getSlider().getValue();			
		
		switch (count) {
			default:granularityCoords[0] = 7; 
					granularityCoords[1] = 7;
					break;
			case 1: granularityCoords[0] = 8; 
					granularityCoords[1] = 8;
					break;
			case 2: granularityCoords[0] = 9; 
					granularityCoords[1] = 9;
					break;
			case 3: granularityCoords[0] = 10; 
					granularityCoords[1] = 10;
					break;
			case 4: granularityCoords[0] = 11; 
					granularityCoords[1] = 11;
					break;		
			case 5: granularityCoords[0] = 12; 
					granularityCoords[1] = 12;
					break;
		}
		return granularityCoords;
	}
    /**
     * Copy item handling, calls copy item.
     * @param event action event
     */
    private void copyItemHandling(ActionEvent event){
    	copyItem();                 	
    }
    /**
     * Copies the image and keeps the rotation and size values. New images get placed
     * in a custom copy grid which size depends on the amount of images copied. Copied Images
     * have hover, select and drag image handlers reattached.
     * @return the copied items
     */
    public ArrayList<StackPane> copyItem(){
    	Board copyGrid = view.getCopyGrid();
    	copyGrid.getChildren().clear();
    	String floor = view.getGrid().getId(); 	
   	 	ArrayList<StackPane> items = new ArrayList<StackPane>();
        items.clear();
             	 	
    	for(ImageView item : group.getGroup()){ 	
    		
            StackPane newPane = board.makePane();    		
    		ImageView copiedItem = new ImageView();
    		copiedItem.setImage(item.getImage());
    		
    		pallet.makeImageView(copiedItem);
        	selectImage(copiedItem);
        	hoverImage(copiedItem);
        	dragdetected.dragImage(copiedItem);
        	
    		copiedItem.setFitHeight(item.getFitHeight());
    		copiedItem.setFitWidth(item.getFitWidth());
		   		        	
            newPane.getChildren().add(copiedItem);       
            items.add(newPane);
    		
    	}     

    	int arraySize = items.size();  
    	
    	int count = 0;      	      

        for (int x = 0; x < (items.size()); x++){
            for(int j = 0; j < 2; j++){
            	if(count < arraySize){
            		System.out.println("Row: " + j);
            		System.out.println("Column: " + x);
            		copyGrid.add(items.get(count++), j, x);
            		copyGrid.setId(floor);
            		}
            	}
    		}
		return items;   
    	}
    /**
     * Delete item handling, calls delete item.
     * @param event action event
     */
    private void deleteItemHandling(ActionEvent event){
    	deleteItem();
    }	
    /**
     * Deletes all items that are in the group, if they are placed in a stack pane.
     * Clears the group when items are deleted.
     */
    public void deleteItem(){
       	boolean clear = false;
        	
        for(ImageView node : group.getGroup()) {				
    		if(node.getParent() instanceof StackPane){
    			((StackPane)node.getParent()).getChildren().clear();
    			clear = true;
    		}		
    	}
    		
    	if(clear == true){
    		group.clearGroup();;
    	}	
    }
    /**
     * Returns all nodes that are located in the board/grid pane.
     * @param board The board to search.
     * @return all the nodes on the board.
     */
    public ArrayList<StackPane> getAllNodes(GridPane board){
    		
    	ArrayList<StackPane> nodes = new ArrayList<>();  		
    	StackPane pane = new StackPane();
    		
    	for(Node node : board.getChildren()) {
    		pane = (StackPane) node;
    		nodes.add(pane);
    	}   		
    	return nodes;  		
    }
    /**
     * Creates a file where each element on the board is stored in a new line as a string. 
     * Column and Row numbers from the board are stored at the end of the file.
     * @param file The name of the file to be created
     * @param arrData The array to be saved to the file
     * @throws IOException if the saving is interrupted or failed.
     */
    public void createFile(String file, ArrayList<String> arrData) throws IOException {
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
    /**
     * Save Board handling populates an array and passes it to createFile.
     * @param event action event
     * @throws IOException if the saving is interrupted or failed.
     */
    private void saveBoardHandling(ActionEvent event) throws IOException{
    	board = view.getGrid();
    	ArrayList<String> save = saveBoard();
    	createFile("board", save);
    }
    /**
     * Each stack pane on the board has its contents saved to an array list
     * either blank or containing an image. Elements are stored as a string.
     * @return the ArrayList of stack pane contents in string format
     */
    public ArrayList<String> saveBoard(){		
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
    /**
     * Filters an array of strings and removes redundant string formatting
     * so that it can be read properly.
     * @param arr ArrayList to be filtered
     * @return filtered ArrayList
     */
	public ArrayList<String> filterArray(ArrayList<String> arr){
		
		ArrayList<String> imageStrings = new ArrayList<String>();
		ArrayList<Integer> imageRotations = new ArrayList<Integer>();
		
		for(String node : arr){
			if(node.equals("[]")){
				imageStrings.add(node);
				imageRotations.add(0);
			}
			else if(!node.equals("[]") && !node.contains("column") && !node.contains("row")){			
				String imagename = node.substring(node.lastIndexOf("id=") + 3, node.indexOf(","));
				imageStrings.add(imagename);
				System.out.println(imagename);
				imageRotations.add(0);
			} 
			else if(node.contains("column")){
				String column = node.substring(7);
				imageStrings.add(column);
			}
			else if(node.contains("row")){
				String row = node.substring(4);
				imageStrings.add(row);
			}
		}
		    		
		System.out.println(imageStrings);
		System.out.println(imageRotations);
				
		return imageStrings;
	}
	
	/**
	 * Load file handling loads file of strings to an ArrayList and filters the ArrayList then 
	 * loads it to the board.
	 * @param event action event
	 */
	private void loadFileHandling(ActionEvent event){
		ArrayList<String> loadFile = loadFile("board.txt");
		ArrayList<String> filteredArray = filterArray(loadFile);
		loadBoard(filteredArray);
	}
	/**
	 * Reads in each line from the file as a new element in the array list as a string.
	 * @param file
	 * @return
	 */
	public ArrayList<String> loadFile(String file){
		
		ArrayList<String> array = new ArrayList<String>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                array.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }   	
				
		return array;
		
	}
	/**
	 * Initialises a new board with the column and row numbers from the Array list.
	 * Takes the file URL string from the Array List and creates a new ImageView.
	 * Styles the images and adds the drag, hover and select handlers.
	 * Adds the drag and drop handlers to the new board.
	 * 
	 * @param the ArrayList of elements to load the board with
	 */
	public void loadBoard(ArrayList<String> arr){
		
		resetBoard();
		
		int column = Integer.parseInt(arr.get(arr.size() - 2));
		int row = Integer.parseInt(arr.get(arr.size() - 1));
		
		arr.remove(arr.size() - 1);
		arr.remove(arr.size() - 1);
		    		
		board = view.getGrid();
		
		board.createBoard(board, column, row);
		
		int index = 0;
		
		for(int x = 0; x < column; x++){
			for(int y = 0; y < row; y++){
				StackPane pane = (StackPane) getNode(board, x, y); 
				String sTemp = arr.get(index);
			//	Integer iTemp = ints.get(index);
				if(sTemp.equals("[]")){    					
				}
			else{
				Image loadImage = new Image(sTemp);
				ImageView loadImageView = new ImageView();
				loadImageView.setImage(loadImage);
				pallet.makeImageView(loadImageView);
		      	selectImage(loadImageView);
		       	hoverImage(loadImageView);
		       	dragdetected.dragImage(loadImageView);
		//       	loadImageView.setRotate(iTemp);
				pane.getChildren().add(loadImageView);
				}
				
				index++;
				
				view.addDragOverHandler(this::addDragOverHandling);
				view.addDragDroppedHandler(this::addDragDroppedHandling);
			}
		}	   		
	}
}