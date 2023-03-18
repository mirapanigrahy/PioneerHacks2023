import engine.Actor;
import javafx.scene.image.Image;

public class Card extends Actor {
	
	private boolean front = true;
	private String phrase = "";
	private String translation = "";
	
	public Card(String phrase, String translation, boolean front) {
		this.front = front;
		this.phrase = phrase;
		this.translation = translation;
	}
	
	public String getTranslation() {
		return translation;
	}
	
	public String getPhrase() {
		return phrase;
	}
	
	public boolean isFront() {
		return front;
	}

	@Override
	public void act(long now) {
		
	}
}
