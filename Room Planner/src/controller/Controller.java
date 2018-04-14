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
	private Integer[] fCoords = new Integer[2];
	private Integer[] nCoords = new Integer[2];
	private Integer[] a2Coords = new Integer[2];
	private Integer[] newCoords = new Integer[2];
	private Integer[] granularityCoords = new Integer[2];
	
	public Controller(BuildUI view, Board board, Pallet pallet, Group group){
		this.view = view;
		this.board = board;
		this.pallet = pallet;
		this.group = group;
		this.attachEventHandler();
	}

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
	
    private void addSofaHandling(ActionEvent event){
    	Image sofa = new LocatedImage("file:sofa.png");
    	addFurniture(sofa);   	
    }
       
    private void addSideBoardHandling(ActionEvent event){
    	Image sideboard = new LocatedImage("file:sideboard.png");
    	addFurniture(sideboard);     
    }
    
    private void addSquareTableHandling(ActionEvent event){
    	Image squaretable = new LocatedImage("file:squaretable.png");
    	addFurniture(squaretable); 
    }
    
    private void addTVHandling(ActionEvent event){
    	Image tv = new LocatedImage("file:tv.png");
    	addFurniture(tv);   	    	
    }
    
    private void addPoolTableHandling(ActionEvent event){
    	Image pooltable = new LocatedImage("file:pooltable.png");
    	addFurniture(pooltable);   
    }
    
    private void addRugHandling(ActionEvent event){
    	Image rug = new LocatedImage("file:rug.png");
    	addFurniture(rug);   
    }
    
    private void addWorkTopHandling(ActionEvent event){
    	Image worktop = new LocatedImage("file:worktop.png");
    	addFurniture(worktop);   	    	
    }
    
    private void addWardrobeHandling(ActionEvent event){
    	Image wardrobe = new LocatedImage("file:wardrobe.png");
    	addFurniture(wardrobe);   	    	
    }
    
    private void addDrawerHandling(ActionEvent event){
    	Image drawer = new LocatedImage("file:drawer.png");
    	addFurniture(drawer);   	    	
    }
    
    private void addChairHandling(ActionEvent event){
    	Image chair = new LocatedImage("file:chair.png");
    	addFurniture(chair);   	    	
    }
    
    private void addDchairHandling(ActionEvent event){
    	Image dchair = new LocatedImage("file:dchair.png");
    	addFurniture(dchair);   	    	
    }
    
    private void addDeskHandling(ActionEvent event){
    	Image desk = new LocatedImage("file:desk.png");
    	addFurniture(desk);
    }
    
    private void addFridgeHandling(ActionEvent event){
    	Image fridge = new LocatedImage("file:fridge.png");
    	addFurniture(fridge);
    }
    
    private void addLongtableHandling(ActionEvent event){
    	Image ltable = new LocatedImage("file:ltable.png");
    	addFurniture(ltable);
    }
    
    private void addVanityUnitHandling(ActionEvent event){
    	Image vanityunit = new LocatedImage("file:vanityunit.png");
    	addFurniture(vanityunit);
    }
    
    private void addOvenHandling(ActionEvent event){
    	Image oven = new LocatedImage("file:oven.png");
    	addFurniture(oven);
    }
    
    private void addShowerHandling(ActionEvent event){
    	Image shower = new LocatedImage("file:shower.png");
    	addFurniture(shower);
    }
    
    private void addSinkHandling(ActionEvent event){
    	Image sink = new LocatedImage("file:sink.png");
    	addFurniture(sink);
    }
    
    private void addSink1Handling(ActionEvent event){
    	Image sink1 = new LocatedImage("file:sink1.png");
    	addFurniture(sink1);
    }
    
    private void addBedHandling(ActionEvent event){
    	Image bed = new LocatedImage("file:bed.png");
    	addFurniture(bed);   	    	
    }
    
	private void addBathHandling(ActionEvent event){
    	Image bath = new LocatedImage("file:bath.png");
    	addFurniture(bath);   	    	
    }
    
    private void addBookshelfHandling(ActionEvent event){
    	Image bed = new LocatedImage("file:bookshelf.png");
    	addFurniture(bed);   	    	
    }
    
    private void addFurniture(Image furniture){
    	pallet.addImage(furniture);
    	images = updatePallet();
    	pallet.getImageView();
    	view.addAll(images);
    }
        
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
    
    private void clearPalletHandling(ActionEvent event){
    	pallet.clear();
    	view.clearAll();
    }
    
	private void hoverImage(ImageView image) {
		image.setOnMouseEntered(i -> {
			image.setCursor(Cursor.MOVE);
		});
	}
	    
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
	
    public class DragDetected {     	        	
        
    	private void dragImage(final ImageView image){
    		image.setOnDragDetected(onDragDetectedEventHandler);
    	}
    	       	
    	EventHandler<MouseEvent> onDragDetectedEventHandler = event -> {
    			moveImage();
    	};      	
    }   

    private void addDragOverHandling(DragEvent e){   	
    		Dragboard dImage = e.getDragboard(); //dImage is the dragboard being dragged
    		if(dImage.hasContent(imageFormat) && draggingImage != null) { // if the selected image is being dragged and isn't null
    			e.acceptTransferModes(TransferMode.MOVE); //Accepts the moving transfermode of the drag
    		}	
    }
    
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
    
	public void setbCoords(int column, int row){			
		bCoords[0] = column;
		bCoords[1] = row;	
	}	
    
	public void setaCoords(int column, int row){
		aCoords[0] = column;
		aCoords[1] = row;		
    }
    
	public void calculateMoveDistance(){
    	mCoords[0] = aCoords[0] - bCoords[0];
    	mCoords[1] = aCoords[1] - bCoords[1];
    }
    
	public void calculateReplaceCoords(){
    	nCoords[0] = fCoords[0] - mCoords[0];
    	nCoords[1] = fCoords[1] - mCoords[1];
    }
    
	public void calculateNewCoords(){
    	newCoords[0] = a2Coords[0] + mCoords[0];
    	newCoords[1] = a2Coords[1] + mCoords[1];
    }
    
    private void addDragDroppedHandling(DragEvent e){
    	
		Dragboard dImage = e.getDragboard(); 
		
		if (dImage.hasContent(imageFormat)) { 
									
			((Pane)draggingImage.getParent()).getChildren().remove(draggingImage);				
			((StackPane)e.getSource()).getChildren().add(draggingImage);					
			e.setDropCompleted(true); 			
			draggingImage = null;
			
		}						  			
					
		fCoords[0] = board.getColumnInd((Node) e.getSource());
		fCoords[1] = board.getRowInd((Node) e.getSource());  
			
		System.out.println("fColumn: " + fCoords[0] + " fRow: " + fCoords[1]);
			
		setaCoords(fCoords[0], fCoords[1]);
		calculateMoveDistance();	
		
		System.out.println("mColumn: " + mCoords[0] + " mRow: " + mCoords[1]);
		boolean overlap = false;
		int index = 0;  			
		board = view.getGrid();
		for(ImageView image : group.getGroup()){   	
				   					
   			int size = group.groupSize() - 2;
				
            if(index <= size){           
       					
       	        if(group.groupSize() > 1){ 
       	               		   	
       				StackPane multipleImage = (StackPane) getNode(board, fCoords[0], fCoords[1]);
       	   	               		               	        	
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
       		     
       		calculateNewCoords();
       		
       		if(group.groupSize() > 1){              			

       			if ((a2Coords[0] != fCoords[0] || a2Coords[1] != fCoords[1]) && overlap == false){     		       				       	           				       			
       				   				 				
       				StackPane groupMove = (StackPane) getNode(board, newCoords[0], newCoords[1]);
       				groupMove.getChildren().add(image);
                  		                	
               	}          		
       		}
       	}      	  	
    }
    
	public Node getNode(GridPane board, int col, int row){
		
		for(Node node : board.getChildren()) {
			if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}
	
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
	
	private void singleRotation(){
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
	
	private void groupRotation(){
		
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
	
	private void addRotationHandling(ActionEvent event){
		
		singleRotation();
		groupRotation();
			 		
	}	    
	
    private void rotateBoardHandling(ActionEvent event){
    	rotateBoard();
    }
    
    private void rotateBoard(){
    	board = view.getGrid();
    	board.setRotate(board.getRotate() + 90);
    }
	
	private void clearBoardHandling(ActionEvent event){
		resetBoard();
		board = view.getGrid();
		board.createBoard(board, board.getColumn(), board.getRow());
		view.addDragOverHandler(this::addDragOverHandling);
		view.addDragDroppedHandler(this::addDragDroppedHandling);
	}

    private void resetBoard(){
    	System.out.println("Board Cleared");
    	board = view.getGrid();
    	board.deleteBoard();
    }
    
	private void addSliderHandling(MouseEvent event){
		board = view.getGrid();
		
		setGranularity();		
		resetBoard();
		
		board.createBoard(board, granularityCoords[0], granularityCoords[1]);	
		view.addDragOverHandler(this::addDragOverHandling);
		view.addDragDroppedHandler(this::addDragDroppedHandling);
	}
	
	
	private void setGranularity(){
		
		granularityCoords[0] = 0;
		granularityCoords[1] = 0;
		
		int count = 0;
		count = (int) view.getSlider().getValue();			
		
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
	}
    
    private void copyItemHandling(ActionEvent event){
    	copyItem();                 	
    }
    
    private void copyItem(){
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
    	}
    
    	private void deleteItemHandling(ActionEvent event){
    		deleteItem();
    	}	
    	
    	private void deleteItem(){
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
	
    	private void filterArray(ArrayList<String> arr){
    		
    		ArrayList<String> imageStrings = new ArrayList<String>();
    		ArrayList<Integer> imageRotations = new ArrayList<Integer>();
    		
    		for(String string : arr){
    			if(string.equals("[]")){
    				imageStrings.add(string);
    				imageRotations.add(0);
    			}
    			else if(!string.equals("[]") && !string.contains("column") && !string.contains("row")){			
    				String imagename = string.substring(string.lastIndexOf("id=") + 3, string.indexOf(","));
    				imageStrings.add(imagename);
    				System.out.println(imagename);
    				imageRotations.add(0);
    			} 
    			else if(string.contains("column")){
    				String column = string.substring(7);
    				imageStrings.add(column);
    			}
    			else if(string.contains("row")){
    				String row = string.substring(4);
    				imageStrings.add(row);
    			}
    		}
    		    		
    		System.out.println(imageStrings);
    		System.out.println(imageRotations);
    		
    		loadBoard(imageStrings, imageRotations);    		
    	}
    	
    	private void loadFileHandling(ActionEvent event){
    		loadFile();
    	}
    	
    	private void loadFile(){
    		ArrayList<String> array = new ArrayList<String>();
    		try(BufferedReader br = new BufferedReader(new FileReader("board.txt")))
            {

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    array.add(sCurrentLine);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }   		    		
    		filterArray(array);  
    	}
    	
    	private void loadBoard(ArrayList<String> arr, ArrayList<Integer> ints){
    		
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
    				Integer iTemp = ints.get(index);
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
    		        	loadImageView.setRotate(iTemp);
    					pane.getChildren().add(loadImageView);
    				}
    				
    				index++;
    				
    				view.addDragOverHandler(this::addDragOverHandling);
    				view.addDragDroppedHandler(this::addDragDroppedHandling);
    		}
    	}	   		
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