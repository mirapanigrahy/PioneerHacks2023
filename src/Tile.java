import engine.Actor;
import javafx.scene.image.Image;

public class Tile extends Actor {
	
	private int val;
	private double dx;
	private double dy;
	private int row;
	private int col;
	private double curDist;
	private double dist;
	private boolean combine;
	private Tile dest;
	private Tile next;
	private int SHIFT = 10;
	private int DIST = 100;
	private boolean newTile;
	
	public Tile(int val, int row, int col) {
		newTile = false;
		combine = false;
		dest = null;
		dx = 0;
		dy = 0;
		dist = 1;
		curDist = 0;
		this.row = row;
		this.col = col;
		this.val = val;
		setImage(new Image("file:images/" + val + ".png"));
	}
	
	public Tile combine(boolean c, Tile dest) {
		next = new Tile(dest.getVal()*2, dest.getRow(), dest.getCol());
		next.setFitWidth(dest.getWidth());
		next.setPreserveRatio(true);
		next.newTile = true;
		combine = c;
		this.dest = dest;
		return next;
	}
	
	public boolean getNewTile() {
		return newTile;
	}
	
	public void setNewTile(boolean b) {
		newTile = b;
	}
	
	
	public int getVal() {
		return val;
	}
	
	public void setImg(int val) {
		this.val = val;
		setImage(new Image("file:images/" + val + ".png"));
	}
	
	public int compareTo(Tile t) {
		return val - t.getVal();
	}
	
	public void setDistance(double d) {
		dist = d;
	}
	
	public void setPosition(int r, int c) {
		row = r;
		col = c;
	}
		
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setMovement(double x, double y) {
		dx = x;
		dy = y;
	}

	@Override
	public void act(long now) {
		move(dx, dy);
		curDist += Math.abs(dx + dy);
		if (curDist >= dist) {
			setX(SHIFT + row * DIST);
			setY(SHIFT + col * DIST);
			dist = 1;
			curDist = 0;
			dx = 0;
			dy = 0;
			if (combine) {	
				next.setX(dest.getX());
				next.setY(dest.getY()); 
				getWorld().add(next);
				getWorld().remove(dest);
				getWorld().remove(this);
				next = null;
				dest = null;
				combine = false;
			}
		}
	}
}
