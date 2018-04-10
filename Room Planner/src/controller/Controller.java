package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Toggle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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
	private double imageWidth;
	private double imageHeight;
	private Pane room;
	
	public Controller(BuildUI view, Board board, Pallet pallet, Group group){
		this.view = view;
		this.board = board;
		this.pallet = pallet;
		this.group = group;
		this.attachEventHandler();
	}

	private void attachEventHandler(){
		
		view.addSofaHandler(this::addSofa);
		view.addBedHandler(this::addBed);
		view.addBathHandler(this::addBath);
		view.addBookshelf(this::addBookshelf);
		view.addClearHandler(this::clearPallet);
		view.addDragOverHandler(this::addDragOverHandling);
		view.addDragDroppedHandler(this::addDragDroppedHandling);
		view.addScrollHandler(this::addScrollHandling);
		view.addRotateHandler(this::addKeyHandling);
		view.addResetHandler(this::clearBoardHandling);
		view.addCopyHandler(this::copyItem);
		view.addDeleteHandler(this::deleteItem);
		view.addModifyGranularity(this::modifyGranularity);
		view.addChairHandler(this::addChair);
		view.addDeskHandler(this::addDesk);
		view.addFridgeHandler(this::addFridge);
		view.addTableHandler(this::addLongtable);
		view.addOvenHandler(this::addOven);
		view.addShowerHandler(this::addShower);
		view.addSinkHandler(this::addSink);
		view.addbSinkHandler(this::addSink1);
		view.addDeskchairHandler(this::addDchair);
		view.addTvHandler(this::addTV);
		view.addSquareTableHandler(this::addSquareTable);
		view.addSideboardHandler(this::addSideBoard);
		view.addWardrobeHandler(this::addWardrobe);
		view.addDrawerHandler(this::addDrawer);
		view.addVanityUnitHandler(this::addVanityUnit);
		view.addWorkTopHandler(this::addWorkTop);
		view.addPoolTableHandler(this::addPoolTable);
		view.addRugHandler(this::addRug);
	}
	
	private void modifyGranularity(ActionEvent event){
			
		board = view.getGrid();
		
		int newColumn = Integer.parseInt(view.getColumnText());
		int newRow = Integer.parseInt(view.getRowText());
		
		resetBoard();
		board.createBoard(board, newColumn, newRow);
		
		view.addDragOverHandler(this::addDragOverHandling);
		view.addDragDroppedHandler(this::addDragDroppedHandling);
		
	}
	
    private void addSofa(ActionEvent event){
    	Image sofa = new LocatedImage("file:sofa.png");
    	addFurniture(sofa);   	    	
    }
       
    private void addSideBoard(ActionEvent event){
    	Image sideboard = new LocatedImage("file:sideboard.png");
    	addFurniture(sideboard);     }
    
    private void addSquareTable(ActionEvent event){
    	Image squaretable = new LocatedImage("file:squaretable.png");
    	addFurniture(squaretable); 
    }
    
    private void addTV(ActionEvent event){
    	Image tv = new LocatedImage("file:tv.png");
    	addFurniture(tv);   	    	
    }
    
    private void addPoolTable(ActionEvent event){
    	Image pooltable = new LocatedImage("file:pooltable.png");
    	addFurniture(pooltable);   
    }
    
    private void addRug(ActionEvent event){
    	Image rug = new LocatedImage("file:rug.png");
    	addFurniture(rug);   
    }
    
    private void addWorkTop(ActionEvent event){
    	Image worktop = new LocatedImage("file:worktop.png");
    	addFurniture(worktop);   	    	
    }
    
    private void addWardrobe(ActionEvent event){
    	Image wardrobe = new LocatedImage("file:wardrobe.png");
    	addFurniture(wardrobe);   	    	
    }
    
    private void addDrawer(ActionEvent event){
    	Image drawer = new LocatedImage("file:drawer.png");
    	addFurniture(drawer);   	    	
    }
    
    private void addChair(ActionEvent event){
    	Image chair = new LocatedImage("file:chair.png");
    	addFurniture(chair);   	    	
    }
    
    private void addDchair(ActionEvent event){
    	Image dchair = new LocatedImage("file:dchair.png");
    	addFurniture(dchair);   	    	
    }
    
    private void addDesk(ActionEvent event){
    	Image desk = new LocatedImage("file:desk.png");
    	addFurniture(desk);
    }
    
    private void addFridge(ActionEvent event){
    	Image fridge = new LocatedImage("file:fridge.png");
    	addFurniture(fridge);
    }
    
    private void addLongtable(ActionEvent event){
    	Image ltable = new LocatedImage("file:ltable.png");
    	addFurniture(ltable);
    }
    
    private void addVanityUnit(ActionEvent event){
    	Image vanityunit = new LocatedImage("file:vanityunit.png");
    	addFurniture(vanityunit);
    }
    
    private void addOven(ActionEvent event){
    	Image oven = new LocatedImage("file:oven.png");
    	addFurniture(oven);
    }
    
    private void addShower(ActionEvent event){
    	Image shower = new LocatedImage("file:shower.png");
    	addFurniture(shower);
    }
    
    private void addSink(ActionEvent event){
    	Image sink = new LocatedImage("file:sink.png");
    	addFurniture(sink);
    }
    
    private void addSink1(ActionEvent event){
    	Image sink1 = new LocatedImage("file:sink1.png");
    	addFurniture(sink1);
    }
    
    private void addBed(ActionEvent event){
    	Image bed = new LocatedImage("file:bed.png");
    	addFurniture(bed);   	    	
    }
    
	private void addBath(ActionEvent event){
    	Image bath = new LocatedImage("file:bath.png");
    	addFurniture(bath);   	    	
    }
    
    private void addBookshelf(ActionEvent event){
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
    
    private void clearPallet(ActionEvent event){
    	pallet.clear();
    	view.clearAll();
    }
    
	private void hoverImage(ImageView image) {
		image.setOnMouseEntered(i -> {
			image.setCursor(Cursor.MOVE);
		});
	}
	    
	private void setTextColumn(double fwidth, double fheight, double width, double height){
		
		String sWidth = Double.toString(width);
		String sHeight = Double.toString(height);
		
		System.out.println("FitWidth: " + fwidth + " FitHeight: " + fheight);
		System.out.println("Width: " + width + " Height: " + height);
		
		view.setWidth(sWidth);
		view.setHeight(sHeight);
		
		
	}
	
	private void selectImage(ImageView image){
		
		image.setOnMousePressed(i -> {
			
			double imagefWidth = image.getFitWidth();
			double imagefHeight = image.getFitHeight();
			
			double imageWidth = image.getImage().getWidth();
			double imageHeight = image.getImage().getHeight();
						
			setTextColumn(imagefWidth, imagefHeight, imageWidth, imageHeight);
			        			
			if(!i.isShiftDown() && !i.isControlDown()){
				group.clearGroup();
			}			
			if(group.getGroup().isEmpty()){
				group.addItem(image);
				System.out.print("Adding Selection\n");
			}
			else if (i.isShiftDown()){
				group.addItem(image);
				System.out.print("Adding Selection\n");
			} 
			else if (i.isControlDown() && group.groupContains(image)) {
				group.removeItem(image);
				System.out.print("Removing Selection\n");
			}   			
			
			group.groupLog();
			
			System.out.println("Items in Model: " + group.groupSize());
			 			
		});
		    		
	}	
	
    public class DragDetected {     	        	
        
    	public void dragImage(final ImageView image){
    		image.setOnDragDetected(onDragDetectedEventHandler);
    	}
    	       	
    	EventHandler<MouseEvent> onDragDetectedEventHandler = event -> {
    		        		
    		for(ImageView image : group.getGroup()){    
    			        			   			
    			Dragboard dImage = image.startDragAndDrop(TransferMode.MOVE); 
    			dImage.setDragView(image.snapshot(null, null));       			
    			ClipboardContent cc = new ClipboardContent();
    			cc.put(imageFormat, " "); 
    			dImage.setContent(cc); 
    			draggingImage = image;
    			
    			if(image.getParent() instanceof StackPane){
    				
    				int beforeColumn = GridPane.getColumnIndex(image.getParent());
    	       		int beforeRow = GridPane.getRowIndex(image.getParent());     
                	bCoords[0] = beforeColumn;
                	bCoords[1] = beforeRow;
                	                	
    			}   
   			     			        			     			            		
    		}
    	};     	
    }   

    private void addDragOverHandling(DragEvent e){   	
    		Dragboard dImage = e.getDragboard(); //dImage is the dragboard being dragged
    		if(dImage.hasContent(imageFormat) && draggingImage != null) { // if the selected image is being dragged and isn't null
    			e.acceptTransferModes(TransferMode.MOVE); //Accepts the moving transfermode of the drag
    		}	
    }
    
    private void addDragDroppedHandling(DragEvent e){
    	
		Dragboard dImage = e.getDragboard(); 
		
		if (dImage.hasContent(imageFormat)) { 
									
			((Pane)draggingImage.getParent()).getChildren().remove(draggingImage);				
			((StackPane)e.getSource()).getChildren().add(draggingImage);					
			e.setDropCompleted(true); 			
			draggingImage = null;
			
		}						  			
					
		int finalColumn = GridPane.getColumnIndex((Node) e.getSource());
		int finalRow = GridPane.getRowIndex((Node) e.getSource());  
			
		System.out.println("fColumn: " + finalColumn + " fRow: " + finalRow);
			
		aCoords[0] = finalColumn;
		aCoords[1] = finalRow;
			
		int moveColumn = aCoords[0] - bCoords[0];
		int moveRow = aCoords[1] - bCoords[1]; 
		
		System.out.println("mColumn: " + moveColumn + " mRow: " + moveRow);
			
		int index = 0;  				 								
		boolean overlap = false;
				
		for(ImageView image : group.getGroup()){   	
				   					
   			int size = group.groupSize() - 2;
				
            if(index <= size){
              			
              	int newColumn = 0;
       			int newRow = 0;
       					
       	        if(group.groupSize() > 1){ 
       	               		
       	        	board = view.getGrid();   	
       				StackPane multipleImage = (StackPane) getNode(board, finalColumn, finalRow);
       	   	               		               	        	
       	   	        if(multipleImage.getChildren().contains(image)){
       	   	               			
       	   	        	newColumn = finalColumn - moveColumn;
       	   	            newRow = finalRow - moveRow;
       	   	               		
       	   	            StackPane splitPane = ((StackPane) getNode(board, newColumn, newRow));     	   	            
       	   	            splitPane.getChildren().add(multipleImage.getChildren().get(0));      	
       	   	               		
                        overlap = true;
                          		
       	   	        }
       	   	        
       	   	        index++;  
       	        }
            }
		}
			  			
			  				
       	for(ImageView image : group.getGroup()){        
       		               		
       		int aColumn = GridPane.getColumnIndex((StackPane)image.getParent());
       		int aRow = GridPane.getRowIndex((StackPane)image.getParent()); 
       		       		       		
       		int movedColumn = aColumn + moveColumn;
       		int movedRow = aRow + moveRow;
       		
       		if(group.groupSize() > 1){              			

       			if ((aColumn != finalColumn || aRow != finalRow) && overlap == false){     		       				       	           				       			
       				   				
       				board = view.getGrid();	
       				
       				StackPane groupMove = (StackPane) getNode(board, movedColumn, movedRow);
       				groupMove.getChildren().add(image);
                  		                	
               	}          		
       		}
       	}     
    	  	
    }
    
	private Node getNode(Board board, int col, int row){
		
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
	
	private void addKeyHandling(ActionEvent event){
														
		ArrayList<Integer> xCoords = new ArrayList<Integer>();
		ArrayList<Integer> yCoords = new ArrayList<Integer>();
			
		int centerX = 0;
		int centerY = 0;
			
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
						
			if(group.groupSize() > 1){
							    							
				int imageRow = GridPane.getRowIndex(node.getParent());
				int imageColumn = GridPane.getColumnIndex(node.getParent());
							
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
					
					int imageRow = GridPane.getRowIndex(node.getParent());
					int imageColumn = GridPane.getColumnIndex(node.getParent());

					int dx = centerX - imageColumn;
					int dy = centerY - imageRow;
							
					int newPosX = 0;
					int newPosY = 0;		
							
					newPosX = centerX + dy;
					newPosY = centerY - dx;			
							
					board = view.getGrid();	
							
	                StackPane groupMove = (StackPane) getNode(board, newPosX, newPosY);
	                   	                    
	                groupMove.getChildren().remove(node);                        			
	                groupMove.getChildren().add(node);   											    										
													
				}
					
			} 	  		
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
    	board.getChildren().clear();
    }
    
    private void copyItem(ActionEvent event){
    	
    	Board copyGrid = view.getCopyGrid();
    	copyGrid.getChildren().clear();
   	 	copyGrid.setId("floor");
   	 	
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
            		copyGrid.setId("copy");
            		}
            	}
    		}                                
    	}
    
    	private void deleteItem(ActionEvent event){
    		
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
	
}
