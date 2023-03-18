import java.util.*;

public class Deck {
	
    ArrayList<Card> cards = new ArrayList<Card>();
   // private int numCards = 0;
    private String name;

    public Deck(String name, int numCards, ArrayList<Card> cards) {
       // this.numCards = numCards;
        this.cards = cards;
        this.name = name;
    }
    
    public Deck(String name) {
         this.name = name;
    }

    public void addCard(Card card) {
    	cards.add(card);
    }
    
    public int getNumCards() {
    	//return numCards;
    	return null;
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
}
