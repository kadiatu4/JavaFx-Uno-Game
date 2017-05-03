
//Kadiatou Diallo
//110331331
//CSE 114
//Project 

import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;  
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class Game extends Application {
	//Data Field
	private ArrayList<Card> drawPile = new ArrayList<>(); //Stores the draw Pile
	private ArrayList<Card> discardPile = new ArrayList<>(); //Stores the discard Pile
	private boolean mustPickUp = false; //Tracks if the next player must pick up card
	
	public static void main(String args[]){          
	      launch(args);     
	  }      
	 
  @Override     
  public void start(Stage primaryStage) throws Exception {            
     //creating a Group object 
     Group group = new Group(); 
      
     //Creating a Scene by passing the group object, height and width   
     Scene scene = new Scene(group ,800, 800); 
     
     //setting color to the scene 
     scene.setFill(Color.CADETBLUE);  
     
     //Setting the title to Stage. 
     primaryStage.setTitle("UNO Game"); 
     
     Rectangle playArea = new Rectangle(0, 0, 800, 550);
     playArea.setFill(Color.LIGHTCYAN);
     group.getChildren().add(playArea);
    
     Image pic = new Image("file: 1_blue.jpg");
     ImageView iv = new ImageView();
     iv.setImage(pic);
     iv.setY(0);
     iv.setX(10);
     
     //Adding the scene to Stage 
     primaryStage.setScene(scene); 
      
     //Displaying the contents of the stage 
     primaryStage.show(); 
  }    
    
	
	
	//Starts Game
	public void startGame(){
		//Create Objects
		Player player = new Player();
		Computer computer = new Computer();
		Deck deckOfCards = new Deck();
		
		//Initialize to -1
		int goesFirst = -1;
		
		//Adds cards to the deck
		deckOfCards.addCards();
		
		//Shuffles deck
		deckOfCards.shuffleDeck();
		
		//Gets who goes first
		goesFirst = chooseWhoStarts(deckOfCards);
		
		//Gives both players 5 cards
		give5Cards(deckOfCards, player, computer);
		
		//Adds left cards to the Draw Pile
		addToDrawPile(deckOfCards);
		
		//Adds a card to the Discard Pile
		addToDiscardPile();
		
		//Based on who got the highest card, they go first
		if(goesFirst == 0){
			//if Player had the highest card number
			playerTurn(player);
			computerTurn(computer);
		}
		else{
			//if Computer had the highest card number
			computerTurn(computer);
			playerTurn(player);
			computerTurn(computer);
		}
		
		//Loops until a player wins
		while(player.getCards().size() != 0 || computer.getCards().size() != 0){
			playerTurn(player);
			computerTurn(computer);
		}
		
	}
	
	//Prints the Discard card
	public void printDiscardCard(){
		System.out.print( "\nDiscard Card:\n" + discardCard() +"\n\n");
	}
	
	
	public void printDrawPile(){
		System.out.print("\nThe number of Cards in the Draw Pile: \n" + drawPile.size() + "\n");
	}
	
	//Chooses the first Player
	public int chooseWhoStarts(Deck deckOfCards){
		//Player gets the top card
		Card playerCard = deckOfCards.CardOnTop();
		
		//Computer get the second card
		Card computerCard = deckOfCards.CardAfter();
		
		//The card cannot be a special card
		while(computerCard.getNumber() > 7 || playerCard.getNumber() > 7){
			deckOfCards.shuffleDeck();
			playerCard = deckOfCards.CardOnTop();
			computerCard = deckOfCards.CardAfter();
		}
		
		//Displays Message
		System.out.print("The Cards picked: \n\n" + "Player picked: " + playerCard + "\nComputer picked: " + computerCard + "\n");
		
		//If the player has a higher number card
		if(playerCard.getNumber() > computerCard.getNumber())
			return 0;
		//If they have the same number 
		else if(playerCard.getNumber() == computerCard.getNumber()){
			int getNum;  //1 = Computer and 0 = Player
			
			//Generate a number between 0 and 1
			getNum = (int)Math.random() * 2;
			return getNum;
		}
		//If the computer has a higher number card
		else	
			return 1;
	}
	
	//Gives each player 5 cards
	public void give5Cards(Deck deckOfCards, Player playerCard, Computer computerCard){
		//Stores a card
		Card card1, card2;
		
		//Reshuffles the deck
		deckOfCards.shuffleDeck();
		
		for(int i = 0; i < 5; i++){
			//Give Player Cards
			card1 = deckOfCards.CardOnTop();
			playerCard.addCard(card1);
			deckOfCards.RemoveCardFromDeck();
			
			//Give Computer Cards
			card2 = deckOfCards.CardOnTop();
			computerCard.addCard(card2);
			deckOfCards.RemoveCardFromDeck();
		}

		System.out.println(playerCard);
		System.out.println(computerCard);
	}
	
	//Adds the leftOver Cards to the Pile
	public void addToDrawPile(Deck deckOfCards){
		//reference to Deck ArrayList class
		ArrayList<Card> currentDeck = deckOfCards.getDeck();
		
		//Adds left over cards into drawPile
		for(int i = 0; i < deckOfCards.getSize(); i++)
			drawPile.add(currentDeck.get(i));
		
		//Shows current cards
		System.out.print("\nThe Current Cards in Draw Pile");
		for(int i = 0; i < drawPile.size(); i++)
			System.out.print(drawPile.get(i));
			
	}

	//Add a card to DiscardPile
	public void addToDiscardPile(){
		
		//Adds the top card in Draw Pile to Discard Pile
		int topCard = drawPile.size() - 1;
		Card card = drawPile.get(topCard);
		while(card.getNumber() > 7){
			card = drawPile.get(topCard - 1);
		}
		discardPile.add(card);
		
		//Removes card from draw pile
		drawPile.remove(card);
	}

	//Player Turn
	public void playerTurn(Player playerCards){
		
		//Stores user's input
		Scanner input = new Scanner(System.in);
		
		//Gets the user's cards
		ArrayList<Card> cards = playerCards.getCards();
		
		//Stores color and number
		String color;
		int number;
		
		//booleans
		boolean cannotUseCard = true;
		boolean playerHasMatchingCard = playerCards.hasCard(discardCard());
		
		printDiscardCard();
		
		//Checks if the discard card is a +1 card
		if(discardCard().getNumber() == 8 && mustPickUp == true){
			System.out.print("Draw 1 card");
			
			//give player cards
			playerCards.addCard(drawnCard());
			
			//removes from draw pile
			drawPile.remove(drawnCard());
			
			//tracks if a player picked up
			mustPickUp = false;
		}
		
		//Checks if the discard Card is a +2 card
		else if(discardCard().getNumber() == 9 && mustPickUp == true){
			System.out.print("Draw 2 cards");
			Card getCard;
			
			//give player a card
			getCard = drawnCard();
			playerCards.addCard(getCard);
			drawPile.remove(getCard);
			
			//give player a card
			getCard = drawnCard();
			playerCards.addCard(getCard);
			drawPile.remove(getCard);
			
			//tracks if a player picked up
			mustPickUp = false;
		}
		
		//Checks if the player has a matching card
		else if(playerHasMatchingCard == true){
			System.out.print("Pick a card you want to play. Here are your cards: \n");
			
			for(int i = 0; i < cards.size(); i++)
				System.out.print(cards.get(i));
			
			do{
				System.out.print("\nEnter the Card Number: \n");
				number = input.nextInt();
				
				if(number == 10){
					System.out.print("What color you want to make this card (Red, Green, Yellow or Blue):");
					color = input.next();
					for(int i = 0; i < cards.size(); i++){
						if(cards.get(i).getNumber() == 10)
							cards.get(i).setColor(color);
					}
				}
				else{
					System.out.print("Enter the Card Color: \n");
					color = input.next();
				}
				
				Card cardPicked = null;
				for(int i = 0; i < cards.size(); i++){
					if(cards.get(i).getNumber() == number && cards.get(i).getColor().equalsIgnoreCase(color))
						cardPicked = cards.get(i);
				}
				
				if(cardPicked.getNumber() == 10){
					cannotUseCard = false;
					discardPile.add(cardPicked);
					playerCards.removeCard(cardPicked);
					
				}
				else if(cardPicked.getColor().equals(discardCard().getColor()) || cardPicked.getNumber() == discardCard().getNumber()){
					cannotUseCard = false;
					if(cardPicked.getNumber() == 8 || cardPicked.getNumber() == 9){
						mustPickUp = true;
					}
					discardPile.add(cardPicked);
					playerCards.removeCard(cardPicked);
				}
				if(cannotUseCard){
					System.out.print("Please Enter a matching Card:");
				}
			}while(cannotUseCard);	
		}
		else{
			System.out.print("You do not have any matching cards. Pick up a card:");
			System.out.print("\nHere's the card you picked up: \n" + drawnCard().toString() + "\n");
			Card cardDrawn = drawnCard();
			if(cardDrawn.getNumber() == 10){
				System.out.print("Enter the Card Color: (Red, Green, Blue, or Yellow)\n");
				color = input.next();
				cardDrawn.setColor(color);
				discardPile.add(cardDrawn);
				drawPile.remove(cardDrawn);
			}
			else if(cardDrawn.getColor().equalsIgnoreCase(discardCard().getColor()) ||  cardDrawn.getNumber() == discardCard().getNumber()){
				discardPile.add(cardDrawn);
				if(cardDrawn.getNumber() == 8 || cardDrawn.getNumber() == 9)
					mustPickUp = true;
				drawPile.remove(cardDrawn);
			}
			else{
				System.out.print("The card you picked does not match");
				playerCards.addCard(cardDrawn);
				drawPile.remove(cardDrawn);
			}
						
		}
	}
	public void computerTurn(Computer computerCards){
		ArrayList<Card> cards = computerCards.getCards();
		boolean computerHasMatchingCard = computerCards.hasCard(discardCard());
		String[] colors = {"Red", "Blue", "Green", "Yellow"};
		printDiscardCard();
		if(discardCard().getNumber() == 8 && mustPickUp == true){
			System.out.print("\nDraw 1 card");
			computerCards.addCard(drawnCard());
			drawPile.remove(drawnCard());
			mustPickUp = false;
		}
		else if(discardCard().getNumber() == 9 && mustPickUp == true){
			System.out.print("\nDraw 2 cards");
			Card getCard;
		
				getCard = drawnCard();
				computerCards.addCard(getCard);
				drawPile.remove(getCard);
				
				getCard = drawnCard();
				computerCards.addCard(getCard);
				drawPile.remove(getCard);
				mustPickUp = false;
				
		}
		else if(computerHasMatchingCard == true){
			System.out.print("\nHere are the computer cards: \n");
			for(int i = 0; i < cards.size(); i++)
				System.out.print(cards.get(i));
			System.out.print("\nComputer is picking a card... ");	
			
			Card cardPicked = null;
			int getNumber = (int) Math.random() * 2;
			int getColor;
			for(int i = 0; i < cards.size(); i++){
				cardPicked = cards.get(i);
				if(cardPicked.getNumber() == 10 && getNumber == 1){
					getColor = (int) Math.random() * 4;
					cardPicked.setColor(colors[getColor]);
					discardPile.add(cardPicked);
					computerCards.removeCard(cardPicked);
					break;
				}
				else if(cardPicked.getNumber() == 10 && discardCard().getNumber() == 10){
					getColor = (int) Math.random() * 4;
					cardPicked.setColor(colors[getColor]);
					discardPile.add(cardPicked);
					computerCards.removeCard(cardPicked);
					break;
				}
				else if(cardPicked.getNumber() == discardCard().getNumber() || cardPicked.getColor() == discardCard().getColor()){
					discardPile.add(cardPicked);
					if(cardPicked.getNumber() == 8 || cardPicked.getNumber() == 9)
						mustPickUp = true;
					computerCards.removeCard(cardPicked);
					break;
				}
				getNumber = (int) Math.random() * 2;
			}
				System.out.print("\nHere is the card Computer picked: \n" + discardCard().toString() + "\n");
				
		}
		else{
			int getColor;
			System.out.print("\nComputer picked up a card");
			System.out.print("\nHere's the card Computer picked up: \n" + drawnCard().toString() + "\n");
			Card cardDrawn = drawnCard();
			if(cardDrawn.getNumber() == 10){
				getColor = (int) Math.random() * 4;
				cardDrawn.setColor(colors[getColor]);
				discardPile.add(cardDrawn);
				drawPile.remove(cardDrawn);
			}
			else if(cardDrawn.getColor().equals(discardCard().getColor()) ||  cardDrawn.getNumber() == discardCard().getNumber()){
				discardPile.add(cardDrawn);
				if(cardDrawn.getNumber() == 8 || cardDrawn.getNumber() == 9)
					mustPickUp = true;
				drawPile.remove(cardDrawn);
			}
			else{
				System.out.print("The card Computer picked does not match");
				computerCards.addCard(cardDrawn);
				drawPile.remove(cardDrawn);
			}
						
		}
	}
	
	public Card discardCard(){
		//get size
		int index = discardPile.size() - 1;
		
		//Copy the current card
		Card topCard = discardPile.get(index);
		
		//Return the copied card
		return topCard;
	}
	
	public Card drawnCard(){
		boolean needMoreCards = threeCardsLeft();
		if(needMoreCards == true){
			replaceDrawPile();
		}
		printDrawPile();
		//get size
		int index = drawPile.size() - 1;
				
		//Copy the current card
		Card topCard = drawPile.get(index);
				
		//Return the copied card
		return topCard;
	}
	public Card drawnCard2(){
		boolean needMoreCards = threeCardsLeft();
		if(needMoreCards == true){
			replaceDrawPile();
		}
		//get size
		int index = drawPile.size() - 2;
				
		//Copy the current card
		Card topCard = drawPile.get(index);
				
		//Return the copied card
		return topCard;
	}
	public void replaceDrawPile(){
		
		Card getDiscardCard = discardCard();
		for(int i = 0; i < discardPile.size() -1; i++){
			drawPile.add(discardPile.get(i));
		}
		discardPile.clear();
		discardPile.add(getDiscardCard);
		
	}
	public boolean threeCardsLeft(){
		if(drawPile.size() <= 3){
			return true;	
		}
		else 
			return false;
	}

	
}

