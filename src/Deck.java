import java.util.*;

public class Deck {
	
    ArrayList<Card> cards = new ArrayList<Card>();
    private String name;

    public Deck(String info, boolean lang) {
        cards = new ArrayList<Card>();
        String[] str = info.split(" ");
        int count = 1;
        name = str[0];
        for (int i = 0; i < Integer.parseInt(str[1]); i++) {
            String phrase = str[++count];
            String translation = str[++count];
            Card c = new Card(phrase, translation, true);
            cards.add(c);
        }
    }
    
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

    
    public String convToInfo() {
        String info = name + " " + cards.size() + " ";
        for (int i = 0; i < cards.size(); i++) {
            Card c = cards.get(i);
            info += c.getPhrase() + " ";
            info += c.getTranslation() + " ";
        }
        return info;
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

}
