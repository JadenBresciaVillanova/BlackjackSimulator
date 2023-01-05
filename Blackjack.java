import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
	
	private int aceValue;
	private int currentCardValue = 4; 
	private CardDeck CDeck;
	private ArrayList<Card> playerDeck;
	private ArrayList<Card> dealerDeck;
	private boolean playerWent;
	private boolean dealerWent;
	private String userInput;
	
	public int getAceValue(ArrayList<Card> givenDeck)
	{
		int aceCounter = 0;
		for (int i = 0; i <= givenDeck.size()-1; i++)
		{
			if (givenDeck.get(i).getRank().equals(Card.Rank.Ace))
			{
				aceCounter++;
			}
		
		}
		
		int notAce = 0;
		for (int i = 0; i <= givenDeck.size()-1; i++)
		{
			if (givenDeck.get(i).getRank().equals(Card.Rank.Ace) == false)
			{
				notAce = notAce + givenDeck.get(i).getRank().getValue();
			}
		}
		
		aceValue = 0;
		if (aceCounter == 1 && notAce > 10)
		{
			aceValue = 1;
		}
		
		else if (aceCounter == 1)
		{
			aceValue = 11;
		}
		else if (aceCounter == 2)
		{
			aceValue = 11 + 1;
		}
		else if (aceCounter == 3)
		{
			aceValue = 11 + 1 + 1;
		}
		else if (aceCounter == 4)
		{
			aceValue = 11 + 1 + 1 + 1;
		}
		
		return aceValue;
	}
	
	public void seeShuffledDeck()
	{
		for (int i = 0; i <= CDeck.deck.size()-1; i++)
		{
			System.out.println("Card("+i+"): "+ CDeck.deck.get(i).getRank() + " of " + CDeck.deck.get(i).getSuit() + "s " + "("+CDeck.deck.get(i).getRank().getValue()+"), ");
		}
	}
	
	public void startGame()
	{
		CardDeck sharedDeck = new CardDeck(); 
		sharedDeck.shuffle();
		CDeck = sharedDeck;
		
		ArrayList<Card> personalDeck = new ArrayList<Card>();
		ArrayList<Card> computerDeck = new ArrayList<Card>();
		
		personalDeck.add(CDeck.deck.get(0)); 
		personalDeck.add(CDeck.deck.get(1));
		
		computerDeck.add(CDeck.deck.get(2));
		computerDeck.add(CDeck.deck.get(3));

		playerDeck = personalDeck;
		dealerDeck = computerDeck;
		gameStatus(personalDeck, computerDeck);
		playerMove();
		
	}
	
	public int getDeckValue(ArrayList<Card> givenDeck)
	{
		
		int aceCounter = 0;
		for (int i = 0; i <= givenDeck.size()-1; i++)
		{
			if (givenDeck.get(i).getRank().equals(Card.Rank.Ace))
			{
				aceCounter++;
			}
		
		}
		
		int notAce = 0;
		for (int i = 0; i <= givenDeck.size()-1; i++)
		{
			if (givenDeck.get(i).getRank().equals(Card.Rank.Ace) == false)
			{
				notAce = notAce + givenDeck.get(i).getRank().getValue();
			}
		}
		
		int aceValue = 0;
		if (aceCounter == 1 && notAce > 10)
		{
			aceValue = 1;
		}
		
		else if (aceCounter == 1)
		{
			aceValue = 11;
		}
		else if (aceCounter == 2)
		{
			aceValue = 11 + 1;
		}
		else if (aceCounter == 3)
		{
			aceValue = 11 + 1 + 1;
		}
		else if (aceCounter == 4)
		{
			aceValue = 11 + 1 + 1 + 1;
		}
		
		return notAce + aceValue;
	}
	
	public void gameRunningStill()
	{
		if (getDeckValue(dealerDeck) > 21)
		{
			System.out.println("You Win!");
			someoneWon(playerDeck, dealerDeck);
			System.exit(0);
		}
		
		if (getDeckValue(playerDeck) > 21)
		{
			System.out.println("You Lose! Dealer Wins.");
			someoneWon(playerDeck, dealerDeck);
			System.exit(0);
		}
		
		if (getDeckValue(playerDeck) == 21 && getDeckValue(dealerDeck) == 21 && playerWent && dealerWent)
		{
			if (getDeckValue(playerDeck) == 21 && getDeckValue(dealerDeck) == 21)
			{
				System.out.println("Push! No one wins.");
				someoneWon(playerDeck, dealerDeck); //delete potentially
				System.exit(0);
			}
		}
		
		if (getDeckValue(playerDeck) < 21 && getDeckValue(dealerDeck) < 21 && getDeckValue(playerDeck) == getDeckValue(dealerDeck) && playerWent && dealerWent)
		{
			System.out.println("It's a Tie!");
			someoneWon(playerDeck, dealerDeck);
			System.exit(0);
		}
		
		if (getDeckValue(playerDeck) <= 21 && getDeckValue(playerDeck) > getDeckValue(dealerDeck) && playerWent && dealerWent)
		{
			System.out.println("You Win!");
			someoneWon(playerDeck, dealerDeck);
			System.exit(0);
		}
		
		if (getDeckValue(dealerDeck) <= 21 && getDeckValue(dealerDeck) > getDeckValue(playerDeck) && playerWent && dealerWent)
		{
			System.out.println("You Lose! Dealer Wins.");
			someoneWon(playerDeck, dealerDeck);
			System.exit(0);
		}
	}
	
	public void playerMove()
	{
		gameRunningStill();
		Scanner input = new Scanner(System.in);
		System.out.print("Would you like to Hit or Stay?  ");
		userInput = input.next();
		userInput = userInput.toLowerCase();
		
		if (userInput.equals("hit") == false && userInput.equals("stay") == false)
		{
			System.out.println("Wrong command, terminating Blackjack.");
			System.exit(0);
		}
		
		if (userInput.equals("hit"))
		{
			
			playerDeck.add(CDeck.deck.get(currentCardValue));
			currentCardValue++;
			playerWent = true;
			gameRunningStill();
			gameStatus(playerDeck, dealerDeck);
			playerMove();
		}
		
		if (userInput.equals("stay"))
		{
			playerWent = true;
			dealerMove();
		}
	}
	
	public void dealerMove() 
	{
		dealerWent = true; //needs to be here in case he gets a hand of 17 to 21
		while (getDeckValue(dealerDeck) < 17)
		{
			dealerWent = true;
			dealerDeck.add(CDeck.deck.get(currentCardValue));
			System.out.println();
			System.out.println("Dealer Hit!");
			gameStatus(playerDeck, dealerDeck);
			currentCardValue++;
			if (getDeckValue(dealerDeck) > 21)
			{
				System.out.println("You Win! Dealer Loses");
				someoneWon(playerDeck, dealerDeck);
				System.exit(0);
			}
		
		}
		
		if (getDeckValue(dealerDeck) <= 21)
		{
			if (userInput.equals("stay"))
			{
				gameRunningStill();
			}
			
		
		}
		
	}
	
	public void gameStatus (ArrayList<Card> playerDeck, ArrayList<Card> dealerDeck)
	{
		System.out.println(); 
		System.out.println("******************************************************************************************************************************************************************************************");
		int k = 1;
		int playerDeckValue = getDeckValue(playerDeck);
		for (int i = 0; i <= playerDeck.size()-1; i++)
		{
			if (k == 1) 
			{ 
			System.out.print("Player's Hand("+playerDeckValue+"): ");
			}
			
			if (playerDeck.get(i).getRank().equals(Card.Rank.Ace))
			{
				System.out.print("Card_"+k+": " + playerDeck.get(i).getRank() + " of " + playerDeck.get(i).getSuit() + "s " + "("+getAceValue(playerDeck)+"), ");
			}
			
			else 
			{
			System.out.print("Card_"+k+": " + playerDeck.get(i).getRank() + " of " + playerDeck.get(i).getSuit() + "s " + "("+playerDeck.get(i).getRank().getValue() + "), ");
			
			}
			k++;
		}
		
		System.out.println();
		System.out.println();
		int j = 1;
		for (int i = 0; i <= dealerDeck.size()-1; i++) //i starts at 0 b/c we now see all cards
		{
			
			if (j == 1) 
			{ 
			System.out.print("Dealer's Hand: ");
			j++;
			}
			if (i == 0)
			{
				System.out.print("Card_"+j+":  Unknown Card, ");
				continue;
			}
			
			System.out.print("Card("+j+"): " + dealerDeck.get(i).getRank() + " of " + dealerDeck.get(i).getSuit() + "s " + "("+dealerDeck.get(i).getRank().getValue() + "), ");
			j++;
		}
		System.out.println();
		System.out.println("******************************************************************************************************************************************************************************************");
		System.out.println();
		
	}
	
	public void someoneWon(ArrayList<Card> playerDeck, ArrayList<Card> dealerDeck)
	{
		System.out.println(); 
		System.out.println("******************************************************************************************************************************************************************************************");
		int k = 1;
		int playerDeckValue = getDeckValue(playerDeck);
		int dealerDeckValue = getDeckValue(dealerDeck);
		for (int i = 0; i <= playerDeck.size()-1; i++)
		{
			
			if (k == 1) 
			{ 
			System.out.print("Player's Hand("+playerDeckValue+"): ");
			}
			
			if (playerDeck.get(i).getRank().equals(Card.Rank.Ace))
			{
				System.out.print("Card_"+k+": " + playerDeck.get(i).getRank() + " of " + playerDeck.get(i).getSuit() + "s " + "("+getAceValue(playerDeck)+"), ");
			}
			
			else 
			{
			System.out.print("Card_"+k+": " + playerDeck.get(i).getRank() + " of " + playerDeck.get(i).getSuit() + "s " + "("+playerDeck.get(i).getRank().getValue() + "), ");
			}
			k++;
		}
		
		System.out.println();
		System.out.println();
		int j = 1;
		for (int i = 0; i <= dealerDeck.size()-1; i++) //i starts at 0 b/c we now see all cards
		{
			if (j == 1) 
			{ 
			System.out.print("Dealer's Hand("+dealerDeckValue+"): ");
			}
			
			if (dealerDeck.get(i).getRank().equals(Card.Rank.Ace))
			{
				getDeckValue(dealerDeck);
				System.out.print("Card_"+k+": " + dealerDeck.get(i).getRank() + " of " + dealerDeck.get(i).getSuit() + "s " + "("+getAceValue(dealerDeck)+"), ");
			}
			
			else 
			{
			System.out.print("Card_"+j+": " + dealerDeck.get(i).getRank() + " of " 	+ dealerDeck.get(i).getSuit() + "s " + "("+dealerDeck.get(i).getRank().getValue() + "), ");
			}
			j++;
		}
		System.out.println();
		System.out.println("******************************************************************************************************************************************************************************************");
		System.out.println();
	}

	public static void main(String args[]) {
		
		Blackjack test1 = new Blackjack();
		test1.startGame();
		
		
		
	}
	
}
