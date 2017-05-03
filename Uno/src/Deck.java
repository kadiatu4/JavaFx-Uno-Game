//Kadiatou Diallo
//110331331
//CSE 114
//Project 

import java.util.ArrayList;

public class Deck {
	//Data field
	private ArrayList <Card> deckOfCards = new ArrayList<>();
	
	//Default Constructor
	Deck(){}
	
	//Get Deck
		public ArrayList<Card> getDeck(){ return deckOfCards;}
		
	//Get Array Size
		public int getSize(){ return deckOfCards.size();}
		
	//Add Cards to Deck
	public void addCards(){
		//Array of Regular NAME,COLOR & NUMBER
		String[] regularCardColor = {"Red", "Yellow", "Green", "Blue"};
		int [] regularCardNumber = {1,2, 3, 4, 5, 6, 7};
		String[] regularCardName = {"Big Bird", "Zoe" , "Cookie Monster", "Elmo", "Baby Bear" , "Rosita","Grover"};
		
		//Add Regular Cards to the Deck
		for(int i = 0; i < regularCardColor.length; i++){
			for(int j= 0; j < regularCardName.length; j++){
				deckOfCards.add(new Card(regularCardName[j],regularCardColor[i], regularCardNumber[j])); 
			}
		}
		//Add Draw #1 
		deckOfCards.add(new SpecialCard("Ernie and Bert", "Red", 8, "Draw 1"));
		deckOfCards.add(new SpecialCard("Ernie and Bert", "Blue", 8, "Draw 1"));
		
		//Add Draw #2
		deckOfCards.add(new SpecialCard("Oscar the Grouch", "Yellow", 9, "Draw 2"));
		deckOfCards.add(new SpecialCard("Oscar the Grouch", "Green", 9, "Draw 2"));
		
		//Wild Card
		for (int count = 0; count < 4; count++)
			deckOfCards.add(new SpecialCard("Monster", 10, "Wild"));
	}
	
	//Shuffles Deck
	public void shuffleDeck(){
		java.util.Collections.shuffle(deckOfCards);
	}
	
	//Remove From Deck
	public void RemoveCardFromDeck(){
		//get size
		int index = deckOfCards.size() - 1;
				
		deckOfCards.remove(index);
	}
	
	//Get the top card on Deck
	public Card CardOnTop(){
		//get size
		int index = deckOfCards.size() - 1;
		
		//Copy the current card
		Card topCard = deckOfCards.get(index);
		
		//Return the copied card
		return topCard;
	}
	
	public Card CardAfter(){
		//get size
				int index = deckOfCards.size() - 2;
				
				//Copy the current card
				Card topCard = deckOfCards.get(index);
				
				//Return the copied card
				return topCard;
	}
	
}

