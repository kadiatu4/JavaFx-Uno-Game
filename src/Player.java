//Kadiatou Diallo
//110331331
//CSE 114
//Project 1

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Player {
	
	//Data Field
	private ArrayList<Card> playerCards = new ArrayList<>();
	
	//Default Constructor
	public Player(){}
	
	//GetPlayerCards
	public ArrayList<Card> getCards(){return playerCards;}
	
	//Number of Cards
		public int getNumberOfCards(){ return playerCards.size();}
		
	//Add Card 
	 public void addCard(Card currentCard){
		playerCards.add(currentCard);
	 }
	 
	 //Place in Discard Pile 
	 public void removeCard(Card currentCard){
		 playerCards.remove(currentCard);
	 }
	 
	 //Check if player has card
	 public boolean hasCard(Card currentCard){
		 boolean foundMatch = false;

		 for(int count = 0; count < playerCards.size(); count++){
			 if(currentCard.getColor().equalsIgnoreCase(playerCards.get(count).getColor()) ||currentCard.getNumber() == playerCards.get(count).getNumber())
				 foundMatch = true;
			 else if (playerCards.get(count).getNumber() == 10){
			 	foundMatch = true;
			 	break;
			 }
		 }
		return foundMatch;
	 }
	 
	 //Check if player has two cards left
	 public boolean has2CardsLeft(){
		 if(playerCards.size() == 2)
			 return true;
		 else
			 return false;
	 }
	
	 //Checks if player has no more cards
	 public boolean hasWon(){
		 if(playerCards.size() == 0)
			 return true;
		 else
			 return false;
	 }
	public String toString(){
		String str = "";
		str = "\nPlayer's current cards: "; 
		for(int count = 0; count < playerCards.size(); count++)
			str += playerCards.get(count).toString();
		return str;
	}


}


