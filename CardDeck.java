//----------------------------------------------------------------------
// CardDeck.java           by Dale/Joyce/Weems                 Chapter 6
//
// Models a deck of cards. Includes shuffling and dealing.
//----------------------------------------------------------------------

import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

public class CardDeck {
  public static final int NUMCARDS = 52;
  
  protected ArrayList<Card> deck;
  protected Iterator<Card> deal;
  
  public CardDeck() {
    deck = new ArrayList<Card>(NUMCARDS);
    for (Card.Suit suit : Card.Suit.values())
       for (Card.Rank rank : Card.Rank.values()) {     
         deck.add(new Card(rank, suit));
       }
    deal = deck.iterator();
  }

  /**
   * Randomizes the order of the cards in the deck.
   * Resets the current deal.
   */
  public void shuffle() {
    Random rand = new Random(); // to generate random numbers 
    int randLoc;                // random location in card deck
    Card temp;                  // for swap of cards
    
    for (int i = (NUMCARDS - 1); i > 0; i--) {
      randLoc = rand.nextInt(i);  // random integer between 0 and i - 1
      temp = deck.get(randLoc);
      deck.set(randLoc, deck.get(i));
      deck.set(i, temp);
    }
    
    deal = deck.iterator();
  }
  
  /**
   * Returns true if there are still cards left to be dealt; 
   * otherwise, returns false.
   * @return
   */
  public boolean hasNextCard() {
	  
	  if (deck.size() > 0) //if current deck size is above 0, return true
	  {
		  return true;
	  }
	  
    return (deal.hasNext());
  }
  
  /**
   * Precondition:  this.hasNextCard() == true
   * Returns the next card for the current 'deal'.
   * @return the next Card
   */
  public Card nextCard() {
	  
	  if (hasNextCard() == true)
	  {
		  return deal.next();
	  }
	  
	return null;
  
  }
}
 