
public class CardTester {
	
	public static void main(String[] args) {
		
		//create a new deck of cards
		CardDeck deck = new CardDeck();
		//shuffles the deck
		deck.shuffle();
						
		//traverse deck showing card's suit, rank and value
		while(deck.hasNextCard()) {
			Card c = deck.nextCard();	
			int cardValue = c.getRank().getValue();
			System.out.println(c.toString()+" - has suit "+c.getSuit() 
			+ " and rank "+c.getRank()+" and value  "+cardValue);			
		} 
		
		deck = new CardDeck();
		Card c1 = deck.nextCard();
		Card c2 = deck.nextCard();		
		
	}

}