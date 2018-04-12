package jUnitTest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import model.LocatedImage;
import model.Pallet;

import org.junit.Test;

import javafx.scene.image.Image;

public class PalletTesting {
	
	Pallet test = new Pallet();

	@Test
	public void allImagesTest(){
		List<Image> images = new LinkedList<Image>();
		test.setAllImages(images);
		assertSame(test.getAllImages(), images);
	}
	
	@Test
	public void addImageTest(){
		Image rug = new LocatedImage("file:rug.png");
		test.addImage(rug);
	}

}
