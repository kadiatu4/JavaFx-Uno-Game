//Kadiatou Diallo
//110331331
//CSE 114
//Project 
import java.util.ArrayList;

public class Computer{
	
	//Data Field
	private ArrayList<Card> computerCards = new ArrayList<>();
	
	//Default Constructor
	public Computer(){}
	
	//Get Number of Cards
	public int getNumberOfCards(){ return computerCards.size();}
	
	//getComputer Cards
	public ArrayList<Card>getCards(){return computerCards;}
	

	 public void addCard(Card currentCard){
		computerCards.add(currentCard);
	 }
	 
	 public void removeCard(Card currentCard){
		 computerCards.remove(currentCard);
	 }
	
	 public boolean hasCard(Card currentCard){
		 boolean foundMatch = false;

		 for(int count = 0; count < computerCards.size(); count++){
			 if(currentCard.getColor() == computerCards.get(count).getColor() || currentCard.getName() == computerCards.get(count).getName() ||
					 currentCard.getNumber() == computerCards.get(count).getNumber())
				 foundMatch = true;
			 
		 }
		return foundMatch;
	 }
	
	public Card chooseACard(Card currentCard){
		Card choosenCard = null;
		 for(int count = 0; count < computerCards.size(); count++){
			 if(currentCard.getColor() == computerCards.get(count).getColor() || currentCard.getName() == computerCards.get(count).getName() ||
					 currentCard.getNumber() == computerCards.get(count).getNumber())
				 choosenCard = computerCards.get(count);	 
		 }
		 return choosenCard;
	}
	
	//Check if player has two cards left
		 public boolean has2CardsLeft(){
			 if(computerCards.size() == 2)
				 return true;
			 else
				 return false;
		 }
	
	//player won
//	 public boolean won(){
//			 
//	}
	public String toString(){
		String str = "";
		str = "\nComputer's current cards: "; 
		for(int count = 0; count < computerCards.size(); count++)
			str += computerCards.get(count).toString();
		return str;
	}
//	public timer(){}
//	public boolean Uno(){
//	
//	}

}

