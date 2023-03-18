package engine;
import java.util.ArrayList;

import javafx.scene.Node;

public abstract class Actor extends javafx.scene.image.ImageView {
	
	public abstract void act(long now);
	
	public double getHeight() {
		return getBoundsInParent().getHeight();
	}
	
	public double getWidth() {
		return getBoundsInParent().getWidth();
	}
	
	public World getWorld() {
		return (World)getParent();
	}
	
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		ArrayList<A> arr = new ArrayList<A>();
		for (Node a : getWorld().getChildren()) {
			if (a != this && cls.isInstance(a) && a.intersects(getBoundsInParent())) {
				arr.add(cls.cast(a));
			}
		}
		return arr;
	}
	
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		for (Node a : getWorld().getChildren()) {
			if (a != this && cls.isInstance(a) && a.intersects(getBoundsInParent())) {
				return (cls.cast(a));
			}
		}
		return null;
	}
	
	public void addedToWorld() {
		
	}
	
	
}
