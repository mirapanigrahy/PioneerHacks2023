package engine;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public abstract class World extends javafx.scene.layout.Pane {
	
	private AnimationTimer timer;
	private boolean isWidthSet;
	private boolean isHeightSet;
	private boolean isTimerOn;	
	private HashSet<KeyCode> keycodeDown;
	
	public World() {
		isWidthSet = false;
		isHeightSet = false;
		keycodeDown = new HashSet<KeyCode>();
		isTimerOn = false;
		
		widthProperty().addListener((obs, oldVal, newVal) -> {
			if (oldVal.intValue() == 0 && newVal.intValue() > 0) {
				isWidthSet = true;
				if (isHeightSet) {
					onDimensionsInitialized();
				}
			}
		});
		
		heightProperty().addListener((obs, oldVal, newVal) -> {
			if (oldVal.intValue() == 0 && newVal.intValue() > 0) {
				isHeightSet = true;
				if (isWidthSet) {
					onDimensionsInitialized();
				}
			}	
		});
		
		focusedProperty().addListener((obs, oldVal, newVal) -> {
			requestFocus();	
		});
		
		sceneProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != null) {
				requestFocus();
			}
		});
		
		setOnKeyPressed( k -> {
			keycodeDown.add(k.getCode());
			k.consume();
		});
		
		setOnKeyReleased( k -> {
			keycodeDown.remove(k.getCode());
			k.consume();
		});
		
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				act(now);
				List<Actor> a = getObjects(Actor.class);
				for (int i = 0; i < a.size(); i++) {
					if (a.get(i).getWorld() != null) {
						a.get(i).act(now);
					}
				}
			}
		};
	}
	
	public abstract void onDimensionsInitialized();	
	public abstract void act(long now);
	
	public void add(Actor actor) {
		getChildren().add(actor);
		actor.addedToWorld();
	}
	
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}
	
	public void start() {
		timer.start();
		isTimerOn = true;
	}
	
	public void stop() {
		timer.stop();
		isTimerOn = false;
	}
	
	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls) {
		ArrayList<A> arr = new ArrayList<A>();
		for (Node a : getChildren()) {
			if (cls.isInstance(a)) {
				arr.add(cls.cast(a));
			}
		}
		return arr;
	}
	
	public <A extends Actor> java.util.List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls) {
		ArrayList <A> list = new ArrayList<A>();
		for (int i = 0; i < getObjects(cls).size(); i++) {
		
			if (getObjects(cls).get(i).getBoundsInParent().contains(x, y)) {
				list.add(getObjects(cls).get(i));	
			}
		}
		return list;
	}
	
	public boolean isStopped() {
		return !isTimerOn;
	}
	
	public void addKeyCode(KeyCode k) {
		keycodeDown.add(k);
	}
	
	public void removeKeyCode(KeyCode k) {
		keycodeDown.remove(k);
	}
	
	public boolean isKeyPressed(KeyCode k) {
		return keycodeDown.contains(k);
	}
	 
}
