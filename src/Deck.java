import java.util.*;

public class Deck {
	
    ArrayList<Card> cards = new ArrayList<Card>();
    private String name;

    public Deck(String name, ArrayList<Card> cards) {
        this.cards = cards;
        this.name = name;
    }
    
    public Deck(String name) {
         this.name = name;
    }

    public void addCard(Card card) {
    	cards.add(card);
    }
    
    public Card getCard(int i) {
    	return cards.get(i);
    }
    
    public int getNumCards() {
    	return cards.size();
    }
    
    public String getName() {
    	return name;
    }
    
    public Card findCard(String phrase) {
    	for (int i = 0; i < cards.size(); i++) {
    		if (cards.get(i).getPhrase().equals(phrase)) {
    			return cards.get(i);
    		}
    	}
    	return null;
    }
    
    public void removeCard(Card card) {
    	cards.remove(card);
    }
    
    public void shuffle() {
    	 Random rand = new Random();
         for (int i = cards.size() - 1; i > 0; i--) {
             Card c = cards.get(i);
             int idx = rand.nextInt(cards.size());
             cards.set(i, cards.get(idx));
             cards.set(idx, c);
         }
    }
    
    @Override
    public String toString() {
    	String s = cards.toString();
    	return s;
    }


    public void answerChecker(Card card, String answer) {
        String lowerAnswer = answer.toLowerCase();
        String correctPhrase = card.getPhrase().toLowerCase();
        String correctTranslation = card.getPhrase().toLowerCase();
        if (correctPhrase.equals(lowerAnswer) || correctTranslation.equals(lowerAnswer)) {
            System.out.println("Correct");
        } else {
            int[] dictionaryAnswer = new int[26];
            int[] dictionaryPhrase = new int[26];

            for (int i = 0; i < answer.length(); i++) {
                dictionaryAnswer[answer.charAt(i) - 61] = dictionaryAnswer[answer.charAt(i) - 61] + 1;
            }
            for (int i = 0; i < card.getPhrase().length(); i++) {
                dictionaryPhrase[card.getPhrase().charAt(i) - 61] = dictionaryPhrase[card.getPhrase().charAt(i) - 61] + 1;
            }
            int counter = 0;
            int difference = 0;
            for (int i = 0; i < 26; i++){
                //checking through each char, and counting the same number of characters
                if (dictionaryAnswer[i] != 0 && dictionaryPhrase[i] != 0 && dictionaryPhrase[i] == dictionaryAnswer[i]) {
                    counter++;
                } else if (dictionaryAnswer[i] != dictionaryPhrase[i]){
                    difference++;
                }
            }

            if(difference / counter >= 0.25) {
                System.out.println("Incorrect");
            } else {
                System.out.println("Fix your spelling mistake");
            }
        }

    }
}
