package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.ImageView;
/**
 * This class implements a grouping system for ImageViews on the board.
 * 
 * @author Sam Clark
 * @version 1.0
 */
public class Group {

	private Set<ImageView> group = new HashSet<>();
	/**
	 * Constructs an empty group.
	 */
	public Group(){
	}
	/**
	 * This returns the group of ImageViews.
	 * @return The group of ImageViews
	 */
	public Set<ImageView> getGroup() {
		return group;
	}
	/**
	 * This initialises the group of ImageViews.
	 * @param The group of ImageViews
	 */
	public void setGroup(Set<ImageView> group) {
		this.group = group;
	}
	/**
	 * This adds an ImageView to the group. It also adds
	 * a highlight around the ImageView.
	 * @param node The ImageView to add
	 */
	public void addItem(ImageView node) {
		if (!node.getStyleClass().contains("highlight")) {
			node.getStyleClass().add("highlight");
		}
		group.add(node);
	}
	/**
	 * This removes an ImageView from the group. It also
	 * removes the highlight.
	 * @param node The ImageView to remove
	 */
	public void removeItem(ImageView node) {
		node.getStyleClass().remove("highlight");
		group.remove(node);
	}
	/**
	 * This clears the group and removes every ImageView.
	 */
	public void clearGroup(){
		while(!group.isEmpty()) {
			removeItem(group.iterator().next());
		}
	}
	/**
	 * This returns true or false dependent 
	 * if the ImageView is located in the group.
	 * @param node The ImageView to check
	 * @return true if the group contains the ImageView, false if it doesn't
	 */
	public boolean groupContains(ImageView node){
		return group.contains(node);
    }
	/**
	 * This calculates the number of elements in the group.
	 * @return The number of elements in the group
	 */
	public int groupSize(){
		return group.size();
	}
	/**
	 * This prints a string representation of the group in the console.
	 */
	public void groupLog(){
        System.out.println( "Items in model: " + Arrays.asList(group.toArray()));
	}

}
