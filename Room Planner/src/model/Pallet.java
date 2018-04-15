package model;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * 
 * @author Sam Clark
 * @version 1.0
 */
public class Pallet {

    private List<Image> AllImages = new LinkedList<Image>();
//	private ImageView[] imageView;
	/**
	 * Constructs an empty pallet.
	 */
	public Pallet(){
	}
	/**
	 * This returns all the Images in the List to be added to the pallet.
	 * @return The list of images to be added
	 */
	public List<Image> getAllImages() {
		return AllImages;
	}
	/**
	 * This initialises a list of images to be added to the pallet.
	 * @param allImages The list of images to be added
	 */
	public void setAllImages(List<Image> allImages) {
		this.AllImages = allImages;
	}
	/**
	 * This add an image to the list of images to be added to the pallet.
	 * @param image The image to be added
	 */
	public void addImage(Image image){
		AllImages.add(image);
	}
	/**
	 * This remove an images from the list of images to be added to the pallet.
	 * @param image The image to be removed
	 */
	public void removeImage(Image image){
		AllImages.remove(image);
	}

	/**
	 * This clears all images from the list of images to be added to the pallet.
	 */
	public void clear(){
		System.out.println("Pallet Cleared");
		AllImages.clear();
	}
	/**
	 * This returns the number of elements to be added to the pallet.
	 * @return The number of elements to be added to the pallet
	 */
	public int size(){
		return AllImages.size();
	}
	/**
	 * This takes an ImageView and formats it and styles it before it's
	 * ready to be placed on the pallet.
	 * @param image The ImageView to be styled
	 * @return The styled ImageView
	 */
    @SuppressWarnings("deprecation")
	public ImageView makeImageView(ImageView image){
    	image.setSmooth(true);
    	image.setPreserveRatio(true);
    	image.setFitHeight(100);
    	image.setFitWidth(100);
    	image.setId(image.getImage().impl_getUrl());
    	return image;
    }
    
	
}
