package model;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pallet {

    private List<Image> AllImages = new LinkedList<Image>();
	private ImageView[] imageView;
	
	public Pallet(){
	}
		
	public List<Image> getAllImages() {
		return AllImages;
	}

	public void setAllImages(List<Image> allImages) {
		this.AllImages = allImages;
	}

	public ImageView[] getImageView() {
		return imageView;
	}

	public void setImageView(ImageView[] imageView) {
		this.imageView = imageView;
	}

	public void addImage(Image image){
		AllImages.add(image);
	}

	public void removeImage(Image image){
		AllImages.remove(image);
	}

	public void clear(){
		System.out.println("Pallet Cleared");
		AllImages.clear();
	}

	public int size(){
		return AllImages.size();
	}
    
    public ImageView makeImageView(ImageView image){
    	image.setSmooth(true);
    	image.setPreserveRatio(true);
    	image.setFitHeight(100);
    	image.setFitWidth(100);
    	return image;
    }
    
	
}
