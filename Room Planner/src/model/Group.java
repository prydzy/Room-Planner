package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.ImageView;

public class Group {

	private Set<ImageView> group = new HashSet<>();

	public Group(){
	}

	public Set<ImageView> getGroup() {
		return group;
	}

	public void setGroup(Set<ImageView> group) {
		this.group = group;
	}
	
	public void addItem(ImageView node) {
		if (!node.getStyleClass().contains("highlight")) {
			node.getStyleClass().add("highlight");
		}
		group.add(node);
	}

	public void removeItem(ImageView node) {
		node.getStyleClass().remove("highlight");
		group.remove(node);
	}

	public void clearGroup(){
		while(!group.isEmpty()) {
			removeItem(group.iterator().next());
		}
	}

	public boolean groupContains(ImageView node){
		return group.contains(node);
    	}

	public int groupSize(){
		return group.size();
	}

	public void groupLog(){
        System.out.println( "Items in model: " + Arrays.asList(group.toArray()));
	}

}
