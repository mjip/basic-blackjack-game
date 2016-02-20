import java.util.ArrayList;
import java.util.Collections;
public class CardPile {
  
  private ArrayList<Card> cards;
  
  private int numCards;
  private static final int ARRAY_SIZE = 52;

  public CardPile() {
    this.cards = new ArrayList<Card>();
  }

  public int getNumCards() {
   return this.cards.size();
  }

  public void addToBottom(Card c) {
    this.cards.add(c);
  }

  public boolean isEmpty() {
   return this.cards.isEmpty();
  }

  public Card get(int i) {
   return this.cards.get(i); 
  }

  public int find(Suit s, Value v) {
    for (int i = 0; i < this.cards.size(); i++){
      if (this.cards.get(i).getSuit() == s && this.cards.get(i).getValue() == v){
      return i;
    }
  }
  return -1; 
}

  public Card remove(int i) {
    return this.cards.remove(i);
  }

  public String toString() {
   String combined = "";
   for (int i = 0; i < this.cards.size(); i++) {
     combined = combined + this.cards.get(i) + ", ";
   }

   combined = combined + "\t\t Total number: " + this.cards.size();
   return combined;
  }
  
  public static CardPile makeFullDeck() {
    CardPile deck = new CardPile();
    for (Suit s : Suit.values()) {
      for (Value v : Value.values()) {
       deck.addToBottom(new Card(s,v)); 
      }
    }

    Collections.shuffle(deck.cards);
    
    return deck;
  }

  public static CardPile makeFullDeck(int n){
    CardPile fullDeck = new CardPile();
    for(int i=0; i<n; i++){
      fullDeck.cards.addAll(makeFullDeck().cards);
    }
    Collections.shuffle(fullDeck.cards);
    return fullDeck;
  }
}