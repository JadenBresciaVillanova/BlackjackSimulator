/**
 * Supports playing card objects having a suit and a rank.
 * Only rank is used when comparing cards. Ace is  "high".
 * @author Dale/Joyce/Weems modified by Henry Carter
 *
 */
public class Card implements Comparable<Card> {

	//Rank is an enum with a value associated with it
	//You will need the value to calculate the hand of the dealer and player.
	//To get the value, use card.rank.getValue()
	  public enum Rank {
		  Two(2), 
		  Three(3), 
		  Four(4), 
		  Five(5), 
		  Six(6), 
		  Seven(7), 
		  Eight(8), 
		  Nine(9), 
	      Ten(10), 
	      Jack(10), 
	      Queen(10), 
	      King(10), 	      
	      Ace(11);
	  
	      private int value;
		  private Rank(int value){
			  this.value = value;
		  }
		  				
		  public int getValue(){
		  		return value;
		  }
	  }

	public enum Suit {Club, Diamond, Heart, Spade}
	
	protected final Rank rank;
	protected final Suit suit;
    
	Card(Rank rank, Suit suit)  {
		this.rank = rank; 
		this.suit = suit; 
	}

	public Rank getRank() { 
	  return rank; 
	}
	
	public Suit getSuit() { 
	  return suit; 
	}

    @Override 
    /**
     * Returns true if 'obj' is a Card with same rank  
     * as this Card, otherwise returns false.
     */
  	public boolean equals(Object obj) {
    	if (obj == this)
            return true;
    
        if (obj == null || obj.getClass() != this.getClass())
             return false;
         else {
             Card c = (Card) obj; 
             return (this.rank == c.rank);
         }
    }

 
    /**
     * Compares this Card with 'other' for order. Returns a 
     * negative integer, zero, or a positive integer as this object 
     * is less than, equal to, or greater than 'other'.
     */
    public int compareTo(Card other) {
    	return this.rank.compareTo(other.rank);
    }

  @Override
  public String toString() { 
	  return suit + " " + rank; 
  }
}
 