package modelJUnitTests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import javafx.scene.image.ImageView;
import model.Group;

public class GroupModelTesting {

	protected Group test = new Group();
	protected ImageView node = new ImageView();
	protected ImageView node1 = new ImageView();
	protected ImageView node2 = new ImageView();
	
	@Test
	public void groupTest() {
		Set<ImageView> group = new HashSet<>();
		test.setGroup(group);
		assertEquals(test.getGroup(), group);
	}
	
	@Test
	public void addItemTest(){
		test.addItem(node);
		assertTrue(test.getGroup().contains(node));
		assertTrue(node.getStyleClass().contains("highlight"));
		assertFalse(test.getGroup().isEmpty());
	}
	
	@Test
	public void removeItemTest(){
		test.addItem(node);
		test.removeItem(node);
		assertFalse(test.getGroup().contains(node));
		assertFalse(node.getStyleClass().contains("highlight"));
		assertTrue(test.getGroup().isEmpty());
	}
	
	@Test
	public void clearGroupTest(){
		test.addItem(node);
		test.clearGroup();
		assertTrue(test.getGroup().isEmpty());
	}
	
	@Test
	public void groupContainsTest(){
		test.addItem(node);
		assertTrue(test.groupContains(node));
	}
	
	@Test
	public void groupDoesntContainTest(){
		test.addItem(node);
		test.removeItem(node);
		assertFalse(test.groupContains(node));
	}

	@Test
	public void groupSizeTest(){
		test.addItem(node);
		test.addItem(node1);
		test.addItem(node2);
		assertEquals(test.groupSize(), 3);
	}

}
