import java.util.Arrays;

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
	
	public void flip() {
		front = !front;
	}
	
	public int answerChecker(String answer) {
		if (answer.equals("")) {
			return -1;
		}
        String lowerAnswer = answer.toLowerCase();
        String correctTranslation = getTranslation().toLowerCase();
        if (correctTranslation.equals(lowerAnswer)) {
        	//right
            return 0;
        } else {
            int[] dictionaryAnswer = new int[26];
            int[] dictionaryTranslation = new int[26];

            for (int i = 0; i < answer.length(); i++) {
                dictionaryAnswer[answer.charAt(i) - 97] = dictionaryAnswer[answer.charAt(i) - 97] + 1;
            }
            for (int i = 0; i < getTranslation().length(); i++) {
            	dictionaryTranslation[getTranslation().charAt(i) - 97] = dictionaryTranslation[getTranslation().charAt(i) - 97] + 1;
            }
            int counter = 0;
            int difference = 0;
            for (int i = 0; i < 26; i++){
                //checking through each char, and counting the same number of characters
                if (dictionaryAnswer[i] != 0 && dictionaryTranslation[i] != 0 && dictionaryTranslation[i] == dictionaryAnswer[i]) {
                    counter += dictionaryTranslation[i];
                } else if (dictionaryAnswer[i] != dictionaryTranslation[i]){
                    difference += Math.abs(dictionaryAnswer[i] - dictionaryTranslation[i]);
                }
            }

            if((double)difference / (difference + counter) > 0.5) {
            	//wrong
                return 1;
            } else {
            	//spelling mistake
                return 2;
            }
        }
	}
	
	@Override
	public String toString() {
		return getPhrase() + " " + getTranslation();
	}

	@Override
	public void act(long now) {
		
	}
}
