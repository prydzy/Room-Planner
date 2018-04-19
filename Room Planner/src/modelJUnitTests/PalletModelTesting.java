package modelJUnitTests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import model.LocatedImage;
import model.Pallet;

import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PalletModelTesting {
	
	protected JFXPanel fxPanel = new JFXPanel();
	protected Pallet test = new Pallet();
	protected Image rug = new LocatedImage("file:rug.png");

	@Test
	public void allImagesTest(){
		List<Image> images = new LinkedList<Image>();
		test.setAllImages(images);
		assertEquals(test.getAllImages(), images);
	}
	
	@Test
	public void addImageTest(){
		test.addImage(rug);
		assertTrue(test.getAllImages().contains(rug));
	}
	
	@Test
	public void removeImageTest(){
		test.addImage(rug);
		test.removeImage(rug);
		assertFalse(test.getAllImages().contains(rug));
	}
	
	@Test
	public void clearImagesTest(){
		test.addImage(rug);
		test.clear();
		assertTrue(test.getAllImages().isEmpty());
	}

	@Test
	public void palletSizeTest(){
		test.addImage(rug);
		assertEquals(test.size(), 1);
	}
	
	@Test
	public void makeImageViewDimensionTest(){
		ImageView image = new ImageView();
		image.setImage(rug);
		test.makeImageView(image);
		int height = (int) image.getFitHeight();
		int width = (int) image.getFitWidth();
		assertEquals(height, 100);
		assertEquals(width, 100);
		assertTrue(image.isSmooth());
		assertTrue(image.isPreserveRatio());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void makeImageViewURLTest(){
		ImageView image = new ImageView();
		image.setImage(rug);
		test.makeImageView(image);
		String url = image.getImage().impl_getUrl();
		assertEquals(url, "file:rug.png");
	}
	
	
}
