//Kadiatou Diallo
//110331331
//CSE 114
//Project 1
import java.util.ArrayList;
import javafx.scene.image.Image;

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
			 if(currentCard.getColor().equalsIgnoreCase(computerCards.get(count).getColor())|| currentCard.getName() == computerCards.get(count).getName() ||
					 currentCard.getNumber() == computerCards.get(count).getNumber())
				 foundMatch = true;
			 else if (computerCards.get(count).getNumber() == 10){
				 	foundMatch = true;
				 	break;
				 }
			 
		 }
		return foundMatch;
	 }
	
	
	//Check if computer has two cards left
		 public boolean has2CardsLeft(){
			 if(computerCards.size() == 2)
				 return true;
			 else
				 return false;
		 }
	
		 //Checks if computer has no more cards
		public boolean hasWon(){
			if(computerCards.size() == 0)
				 return true;
			 else
				 return false;
		 }
		 
	public String toString(){
		String str = "";
		str = "\nComputer's current cards: "; 
		for(int count = 0; count < computerCards.size(); count++)
			str += computerCards.get(count).toString();
		return str;
	}
}


