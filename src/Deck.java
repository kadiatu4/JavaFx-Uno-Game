//Kadiatou Diallo
//110331331
//CSE 114
//Project 1

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
		String[] cardImageName = {"1_red.jpg", "2_red.jpg", "3_red.jpg", "4_red.jpg", "5_red.jpg", "6_red.jpg", "7_red.jpg",
								  "1_yellow.jpg", "2_yellow.jpg", "3_yellow.jpg", "4_yellow.jpg","5_yellow.jpg","6_yellow.jpg","7_yellow.jpg",
								  "1_green.jpg", "2_green.jpg", "3_green.jpg" ,"4_green.jpg","5_green.jpg","6_green.jpg","7_green.jpg",
								  "1_blue.jpg", "2_blue.jpg","3_blue.jpg","4_blue.jpg","5_blue.jpg","6_blue.jpg","7_blue.jpg"};
		
		//Add Regular Cards to the Deck
		for(int i = 0; i < regularCardColor.length; i++){
			for(int j= 0; j < regularCardName.length; j++){
				deckOfCards.add(new Card(regularCardName[j],regularCardColor[i], regularCardNumber[j])); 
			}
		}
		
		for(int i = 0; i < deckOfCards.size(); i++){
			deckOfCards.get(i).setImageName(cardImageName[i]);
		}
		//Add Draw #1 
		deckOfCards.add(new SpecialCard("Ernie and Bert", "1_plus_red.jpg", "Red", 8, "Draw 1"));
		deckOfCards.add(new SpecialCard("Ernie and Bert", "1_plus_blue.jpg", "Blue", 8, "Draw 1"));
		
		//Add Draw #2
		deckOfCards.add(new SpecialCard("Oscar the Grouch", "2_plus_yellow.jpg", "Yellow", 9, "Draw 2"));
		deckOfCards.add(new SpecialCard("Oscar the Grouch", "2_plus_green.jpg", "Green", 9, "Draw 2"));
		
		//Wild Card
		for (int count = 0; count < 4; count++)
			deckOfCards.add(new SpecialCard("Monster", "wild.jpg", 10, "Wild"));
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


