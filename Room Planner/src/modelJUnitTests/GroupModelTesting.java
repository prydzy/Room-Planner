package modelJUnitTests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import javafx.scene.image.ImageView;
import model.Group;

public class GroupModelTesting {

	Group test = new Group();
	
	@Test
	public void groupTest() {
		Set<ImageView> group = new HashSet<>();
		test.setGroup(group);
		assertSame(test.getGroup(), group);
	}
	
	@Test
	public void addItemTest(){
		ImageView node = new ImageView();
		test.addItem(node);
		assertTrue(test.getGroup().contains(node));
		assertTrue(node.getStyleClass().contains("highlight"));
		assertFalse(test.getGroup().isEmpty());
	}
	
	@Test
	public void removeItemTest(){
		ImageView node = new ImageView();
		test.addItem(node);
		test.removeItem(node);
		assertFalse(test.getGroup().contains(node));
		assertFalse(node.getStyleClass().contains("highlight"));
		assertTrue(test.getGroup().isEmpty());
	}
	
	@Test
	public void clearGroupTest(){
		ImageView node = new ImageView();
		test.addItem(node);
		test.clearGroup();
		assertTrue(test.getGroup().isEmpty());
	}
	
	@Test
	public void groupContainsTest(){
		ImageView node = new ImageView();
		test.addItem(node);
		assertTrue(test.groupContains(node));
	}
	
	@Test
	public void groupDoesntContainTest(){
		ImageView node = new ImageView();
		test.addItem(node);
		test.removeItem(node);
		assertFalse(test.groupContains(node));
	}

	@Test
	public void groupSizeTest(){
		ImageView node = new ImageView();
		ImageView node1 = new ImageView();
		ImageView node2 = new ImageView();
		test.addItem(node);
		test.addItem(node1);
		test.addItem(node2);
		assertEquals(test.groupSize(), 3);
	}

}
