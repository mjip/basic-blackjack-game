import java.util.Scanner;
public class Blackjack{
  
  public static void main(String[] args){
    
    CardPile deck = CardPile.makeFullDeck(4);
    while(deck.getNumCards() > 10){
      playRound(deck);
    }
  }
  
  public static int getScore(Card c){
    int score = 0;
    Value v = c.getValue();
    if(v.equals(Value.JACK) || v.equals(Value.TEN) || v.equals(Value.QUEEN) || v.equals(Value.KING)){
      score = 10;
    } else if(v.equals(Value.NINE)){
      score = 9;
    } else if(v.equals(Value.EIGHT)){
      score = 8;
    } else if(v.equals(Value.SEVEN)){
      score = 7;
    } else if(v.equals(Value.SIX)){
      score = 6;
    } else if(v.equals(Value.FIVE)){
      score = 5;
    } else if(v.equals(Value.FOUR)){
      score = 4;
    } else if(v.equals(Value.THREE)){
      score = 3;
    } else if(v.equals(Value.TWO)){
      score = 2;
    } else if(v.equals(Value.ACE)){
      score = 11;
    }
    return score;
  }
  
  public static int countValues(CardPile o){

    int totalScore = 0;
    for(int i=0; i<o.getNumCards(); i++){
      totalScore += getScore(o.get(i));
      for(int j=0; j<o.getNumCards(); j++){
        if(totalScore>21 && getScore(o.get(j))==11){
          totalScore -= 10;
        }
      }
    }
    return totalScore;
  }
  
  public static void playRound(CardPile deck){
    System.out.println("********************************");
    System.out.println("*\tNEW ROUND      \t*");
    System.out.println("********************************");
    System.out.println("(Type 'quit' to exit game)\n");
    CardPile userHand = new CardPile();
    CardPile dealerHand = new CardPile();
    userHand.addToBottom(deck.remove(0));
    userHand.addToBottom(deck.remove(0));
    dealerHand.addToBottom(deck.remove(0));
    dealerHand.addToBottom(deck.remove(0));
    
    while(true){
      System.out.println("DEALER'S HAND: (?), " + dealerHand.get(1) + "\t\t Total number: 2 \n");
      System.out.println("YOUR HAND: " + userHand.toString() + "\nYour score is: " + countValues(userHand) + "\n");

      if(countValues(userHand) == 21 && countValues(dealerHand) == 21){
        System.out.println("Blackjack! You tied with the dealer. Game over.");
        break;
      } else if(countValues(userHand) == 21){
        System.out.println("Blackjack! You won. Game over.");
        break;
      }
      
      System.out.println("Hit or stay?");
      Scanner userInput = new Scanner(System.in);
      String move = userInput.next();

      if(move.equals("hit") || move.equals("HIT")){
        userHand.addToBottom(deck.remove(0));
        if(countValues(userHand)>21){
          System.out.println("YOUR HAND: " + userHand.toString() + "\nYour score is: " + countValues(userHand) + "\nBust. You lose, game over.");
          break;
        }
        
      } else if(move.equals("stay") || move.equals("STAY")){
        while(countValues(dealerHand)<18){
          dealerHand.addToBottom(deck.remove(0));
        }
        System.out.println("Dealer's hand: " + dealerHand.toString() + "\nThe dealer's score was: " + countValues(dealerHand) + "\n");
        if(countValues(userHand)>countValues(dealerHand)){
          System.out.println("You win! Game end.\n");
        } else if(countValues(userHand)<countValues(dealerHand) && countValues(dealerHand)<22){
          System.out.println("Dealer wins. Game end.\n");
        } else if(countValues(userHand)<countValues(dealerHand)){
          System.out.println("You win! Game end.\n");
        } else if(countValues(userHand)==countValues(dealerHand)){
          System.out.println("Tie! Game end.\n");
        }
        break;
      } else if(move.equals("quit")){
        System.out.println("gg no re");
        System.exit(1);
      } else if(deck.getNumCards() <= 10){
        System.out.println("There aren't enough cards to keep playing. Game over.");
        break;
      } else {
        System.out.println("Invalid input, type 'hit' or 'stay' (or 'quit')");
      }
      userInput.close();
    }
  }
}