//Kadiatou Diallo
//110331331
//CSE 114
//Project 1


import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;



public class Game extends Application {
	//Private Data Field
	private ArrayList<Card> drawPile = new ArrayList<>(); //Stores the draw Pile
	
	private ArrayList<Card> discardPile = new ArrayList<>(); //Stores the discard Pile
	
	private boolean mustPickUp = false; //Tracks if the next player must pick up card
	
	private Player player = new Player(); //Creates Player Object
	
	private Computer computer = new Computer();	// Creates Computer Object
	
	private Deck deckOfCards = new Deck(); //Creates Deck Object
	
	private Text message = new Text(); //Stores current Message
	
	private boolean playerGoesNext = true;	//Tracks whose playing
	
	private int goesFirst = -1;	//gets which player had the highest card
	
	private boolean endGame = false; //tracks if someone has won
	
	//HBOX
	private HBox playerDeck = new HBox(10);		
	private HBox computerDeck = new HBox(10);
	private HBox drawP = new HBox(10);
	private HBox discardP = new HBox();
	
	//VBOX
	private VBox userInput = new VBox (2);
	
	//PANE
	private Pane parent = new Pane();	//Creates Pane Object

	//Main method
	public static void main(String args[]){          
	      launch(args);     
	  }      
	 
	//Start Method
  @Override     
  public void start(Stage primaryStage) throws Exception {  
	  primaryStage.setScene(new Scene(mainParent()));
      primaryStage.setWidth(900);
      primaryStage.setHeight(600);
      primaryStage.setResizable(false);
      primaryStage.setTitle("UNO GAME");
      primaryStage.show();
	
      
  }    
    
  //Creates the Scene for Game
  public Parent mainParent(){
	  
	  parent.setPrefSize(800, 600);
	  
	  Region background  = new Region();
	  background.setPrefSize(900, 600); 
	  background.setStyle("-fx-background-color: rgba(255, 255, 255, 1)");
	  
	  VBox playArea = new VBox(40);
	  Rectangle leftSide = new Rectangle(570, 600);
	  leftSide.setFill(Color.LIGHTBLUE);
	  playArea.getChildren().add(leftSide);
	  	  
	  Button btnPlay = new Button("PLAY");
	  HBox playButton = new HBox(btnPlay);
	  playButton.setTranslateX(700);
	  playButton.setTranslateY(250);
	  playButton.setScaleX(2);
	  playButton.setScaleY(2);
	  playButton.setAlignment(Pos.CENTER);
	  
	  userInput.setTranslateX(610);
	  userInput.setTranslateY(220);
	  
		playerDeck.setTranslateX(10);
		playerDeck.setTranslateY(390);
		
		computerDeck.setTranslateX(10);
		computerDeck.setTranslateY(80);
		
	  parent.getChildren().addAll(background, playArea, playButton, message);
	  
	  
	  btnPlay.setOnAction(event -> {
		 parent.getChildren().remove(playButton);
		 startGame();
		  });
	  
	  return parent;
	  
  }
	
	
	//Starts Game
	public void startGame(){
	
		
		//Adds cards to the deck
		deckOfCards.addCards();

		//Shuffles deck
		deckOfCards.shuffleDeck();
		
		//Gets who goes first
		goesFirst = chooseWhoStarts();
	

	}
	
	
	//Chooses the first Player
	public int chooseWhoStarts(){
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
		
		cardsPicked(playerCard, computerCard);
		
		//If the player has a higher number card
		if(playerCard.getNumber() > computerCard.getNumber()){
			message.setText("Player goes first!");
			return 0;
		}
		//If they have the same number 
		else if(playerCard.getNumber() == computerCard.getNumber()){
			int getNum;  //1 = Computer and 0 = Player
			
			//Generate a number between 0 and 1
			getNum = (int)Math.random() * 2;
			if(getNum == 0)
				message.setText("Player goes first!");

			else
				message.setText("Computer goes first!");
			
			return getNum;
		}
		//If the computer has a higher number card
		else{	
			message.setText("Computer goes first!");
			return 1;
		}
	}
	
	//Shows the Cards Picked and Add to Parent
	public void cardsPicked(Card playerCard, Card computerCard){

		//Text
		Text firstCards = new Text("Here are the first cards picked by both players");
		firstCards.setFont(new Font(18));
		firstCards.setFill(Color.BLACK);
		firstCards.setTranslateX(100);
		firstCards.setTranslateY(-140);
	      
		//------------Player's Card-------------
		VBox card1 = new VBox(50);
		
		//Load Images
		  Image image1 = new Image(playerCard.getImageName());
		//Display Image View
	      ImageView iv1 = new ImageView();
	      
	      //Image 1
	      iv1.setImage(image1);
	      iv1.setFitHeight(170);
	      iv1.setFitWidth(120);
	      iv1.setTranslateX(150);
	      iv1.setTranslateY(190);
	      
	      //Text 
	      Text playerText = new Text("Player's Card");
	      playerText.setFont(new Font(15));
	      playerText.setFill(Color.BLACK);
	      playerText.setTranslateX(170);
	      playerText.setTranslateY(150);
	      
	      card1.getChildren().addAll(iv1, playerText,firstCards);
	    //------------Computer's Card-------------
	      VBox card2 = new VBox(50);
			//Load Images
			 Image image2 = new Image(computerCard.getImageName());
			//Display Image View
			 ImageView iv2 = new ImageView();
			 
			//Image 2
		      iv2.setImage(image2);
		      iv2.setFitHeight(170);
		      iv2.setFitWidth(120);
		      iv2.setTranslateX(340);
		      iv2.setTranslateY(190);
		      
		      //Text
		      Text computerText = new Text("Computer's Card");
		      computerText.setFont(new Font(15));
		      computerText.setFill(Color.BLACK);
		      computerText.setTranslateX(340);
		      computerText.setTranslateY(150);
		      
		      card2.getChildren().addAll(iv2, computerText);
	     
		      //Message
		      message.setTranslateX(210);
		      message.setTranslateY(420);
		      message.setFont(new Font(22));
		      message.setFill(Color.BLACK);
		      
		      Button btnOkay = new Button("Okay");
			  btnOkay.setTranslateX(280);
			  btnOkay.setTranslateY(450);
			  btnOkay.setAlignment(Pos.CENTER);
			  
			  parent.getChildren().addAll(card1, card2, btnOkay);

			  btnOkay.setOnAction(event -> {
				  parent.getChildren().remove(card1);
				  parent.getChildren().remove(card2);
				  parent.getChildren().remove(btnOkay);
				  
				  addToScreen();
				
			});
	}

	//Adds to Screen
	public void addToScreen(){
		 //Gives both players 5 cards
		 give5Cards();
		 
		//Adds left cards to the Draw Pile
		addToDrawPile();
			
		//Adds a card to the Discard Pile
		addToDiscardPile();

		//Displays Computer and Player play area
		  message.setText("");
		  Text txt1 = new Text("Computer's Cards: ");
		  txt1.setFill(Color.BLACK);
		  txt1.setTranslateX(50);
		  txt1.setTranslateY(50);
		  txt1.setFont(new Font(15));
		 
		  Text txt2 = new Text("Player's Cards: ");
		  txt2.setFill(Color.BLACK);
		  txt2.setTranslateX(50);
		  txt2.setTranslateY(370);
		  txt2.setFont(new Font(15));
		  
		  parent.getChildren().addAll(txt1, txt2);
		  
		  //Calls dialog pop up
		 createPopUp("STARTING GAME....");
		 
		 //Calls the first player
		 firstPlayer();

	}
	

	 //Gets the first player based on who got the highest card
	public void  firstPlayer(){
		
		if(goesFirst == 0){
			playerTurn(player);	
			playerGoesNext = true;
		}
		else {
			computerTurn(computer);
			playerGoesNext = false;
		}
		
	}
	
	//Switches the Player
	public void switchPlayer(){
			playerGoesNext = !playerGoesNext;
			if(player.hasWon() || computer.hasWon()){
				endGame = true;
				if(player.hasWon()){
					createPopUp("You Won!!!");
					System.exit(0);
				}
				else{
					createPopUp("Computer Won");
					System.exit(0);
				}
			}
			else if(endGame == false){
				if(playerGoesNext == true){
					playerTurn(player);	
					computerHasUno();
				}
				else{
					
					computerTurn(computer);
					playerHasUno();
			
				}
			}		
			
	}
	
		
	//Gives each player 5 cards
	public void give5Cards(){
		ImageView img1;
		ImageView img2;
		//Stores a card
		Card card1, card2;
		
		//Re-shuffles the deck
		deckOfCards.shuffleDeck();
		
		for(int i = 0; i < 5; i++){
			
			//Give Player Cards
			card1 = deckOfCards.CardOnTop();
			player.addCard(card1);
			
			img1 = new ImageView(new Image(player.getCards().get(i).getImageName()));
			img1.setFitHeight(100);
			img1.setFitWidth(60);
			
			playerDeck.getChildren().add(img1);
			deckOfCards.RemoveCardFromDeck();
				
			//Give Computer Cards
			card2 = deckOfCards.CardOnTop();
			computer.addCard(card2);
			img2 = new ImageView(new Image(computer.getCards().get(i).getImageName()));
			img2.setFitHeight(100);
			img2.setFitWidth(60);
			computerDeck.getChildren().add(img2);
			deckOfCards.RemoveCardFromDeck();
		}
	
		parent.getChildren().addAll(playerDeck, computerDeck);
	}
	
	//Adds the leftOver Cards to the Pile
	public void addToDrawPile(){
		ImageView img = new ImageView(new Image("back.jpg"));
		img.setFitHeight(100);
		img.setFitWidth(60);
		
		
		ArrayList<Card> currentDeck = deckOfCards.getDeck();
		
		//Adds left over cards into drawPile
		for(int i = 0; i < deckOfCards.getSize(); i++)
			drawPile.add(currentDeck.get(i));
		
		//Adds image of draw Pile to screen
		drawP.getChildren().add(img);
		drawP.setTranslateX(430);
		drawP.setTranslateY(220);
		parent.getChildren().add(drawP);	
	}

	//Add a card to DiscardPile
	public void addToDiscardPile(){
		
		//Adds the top card in Draw Pile to Discard Pile
		int topCard = drawPile.size() - 1;
		Card card = drawPile.get(topCard);
		
		//The card can't be a Draw or Wild card
		while(card.getNumber() > 7){
			card = drawPile.get(topCard - 1);
		}
		
		//Adds card to discard pile
		discardPile.add(card);
		
		//Removes card from draw pile
		drawPile.remove(card);
		
		//Shows the discard card
		Image img = new Image(discardPile.get(0).getImageName());
		ImageView iv1 = new ImageView(img);
		iv1.setFitHeight(100);
		iv1.setFitWidth(60);
		
		discardP.getChildren().add(iv1);
		discardP.setTranslateX(220);
		discardP.setTranslateY(220);
		
		parent.getChildren().add(discardP);
	}

	//Player Turn
	public void playerTurn(Player playerCards){
		
		//Checks if player has a matching card
		boolean playerHasMatchingCard = playerCards.hasCard(discardCard());
	
		
		//Checks if the discard card is a +1 card
		if(discardCard().getNumber() == 8 && mustPickUp == true){
			createPopUp("Player Draw 1 card");
			
			//give player cards
			playerCards.addCard(drawnCard());
			
			//removes from draw pile
			drawPile.remove(drawnCard());
			
			updatePlayerDeck();
			//tracks if a player picked up
			mustPickUp = false;
			switchPlayer();
	
			
		}
		
		//Checks if the discard Card is a +2 card
		else if(discardCard().getNumber() == 9 && mustPickUp == true){
			createPopUp("Player Draw 2 cards");
			Card getCard;
			
			//give player a card
			getCard = drawnCard();
			playerCards.addCard(getCard);
			drawPile.remove(getCard);
			
			//give player a card
			getCard = drawnCard();
			playerCards.addCard(getCard);
			drawPile.remove(getCard);
			
			updatePlayerDeck();	
			//tracks if a player picked up
			mustPickUp = false;
			switchPlayer();
			

			
		}
		
		//Checks if the player has a matching card
		else if(playerHasMatchingCard == true){
			
			userInput.getChildren().clear();
			parent.getChildren().remove(userInput);
		
			Text info = new Text();
			info.setText("To pick a special card: \nEnter:  \n 8 - Draw 1 \n 9 - Draw 2 \n 10 - Wild");
			info.setFont(new Font(15));
			info.setTranslateX(-10);
			info.setTranslateY(-190);
			
			 
			 Text txt3 = new Text("If You pick 10 -Wild Card \n What color do you want to make this\n card(Red, Green, Yellow or Blue)?");
			 txt3.setTranslateY(-150);
			 txt3.setTranslateX(-22);
			 
				 VBox holdTxt1 = new VBox();
				 holdTxt1.setTranslateX(-22);
				 holdTxt1.setTranslateY(-130);
				 Text txt1 = new Text("Enter Card Number: ");
				 final TextField getCardNum = new TextField();
				 getCardNum.setPromptText("Card Number");
				 getCardNum.setPrefColumnCount(8);
				 holdTxt1.getChildren().addAll(txt1, getCardNum);
				
				 VBox holdTxt2 = new VBox();
				 holdTxt2.setTranslateX(-22);
				 holdTxt2.setTranslateY(-120);
				 Text txt2 = new Text("Enter Card Color: ");
				  final TextField getCardColor = new TextField();
				 getCardColor.setPromptText("Card Color");
				 getCardColor.setPrefColumnCount(8);
				 holdTxt2.getChildren().addAll(txt2,getCardColor);
				
				
				
				Button btnSubmit = new Button("Submit");
				btnSubmit.setTranslateX(-22);
				btnSubmit.setTranslateY(-112);
				 userInput.getChildren().addAll(info, txt3, holdTxt1, holdTxt2, btnSubmit);
				parent.getChildren().add(userInput);

				 btnSubmit.setOnAction(e-> {
					 if(checkIfCardExist(convertNumber(getCardNum.getText()), getCardColor.getText(), playerCards) == true){
						 findCard(convertNumber(getCardNum.getText()), getCardColor.getText(), playerCards);
					  }
				 });
	
				
		}
		//If player does not any matching cards
		else{
			createPopUp("You do not have any matching cards. Pick up a card:");
			createImagePopUp();
			
			Card cardDrawn = drawnCard();
			if(cardDrawn.getNumber() == 10){

				
				 VBox holdTxt = new VBox();
				 holdTxt.setTranslateX(-22);
				 holdTxt.setTranslateY(-120);
				 Text message = new Text("You picked up a Wild Card");
				 Text txt = new Text("\n\nEnter Card Color (Red, Green, Blue, or Yellow): ");
				 final TextField getCardColor = new TextField();
				 getCardColor.setPromptText("Card Color");
				 getCardColor.setPrefColumnCount(8);
				 holdTxt.getChildren().addAll(message, txt, getCardColor);
				 
				Button btnSubmit = new Button("Submit");
				btnSubmit.setTranslateX(-22);
				btnSubmit.setTranslateY(-112);
				
				
				userInput.getChildren().clear();
				parent.getChildren().remove(userInput);
				userInput.getChildren().addAll(holdTxt, btnSubmit);
				 parent.getChildren().add(userInput);
				 
				 	 btnSubmit.setOnAction(e-> {
				 		if(getCardColor.getText().equalsIgnoreCase("yellow") || getCardColor.getText().equalsIgnoreCase("red") || getCardColor.getText().equalsIgnoreCase("blue") || getCardColor.getText().equalsIgnoreCase("green")){
							
				 			cardDrawn.setColor(getCardColor.getText());
				 			discardPile.add(cardDrawn);
				 			drawPile.remove(cardDrawn);
				 			updateDiscardPile();
				 			userInput.getChildren().clear();
							parent.getChildren().remove(userInput);
							switchPlayer();
				 		}
					 });
			
							
			}
			else if(cardDrawn.getColor().equalsIgnoreCase(discardCard().getColor()) ||  cardDrawn.getNumber() == discardCard().getNumber()){
				discardPile.add(cardDrawn);
				if(cardDrawn.getNumber() == 8 || cardDrawn.getNumber() == 9)
					mustPickUp = true;
				drawPile.remove(cardDrawn);
				switchPlayer();
			}
			else{
				createPopUp("The card you picked does not match");
				playerCards.addCard(cardDrawn);
				updatePlayerDeck();	
				drawPile.remove(cardDrawn);
				switchPlayer();
			}
	
		}
	}
	
	//Checks if player has the card they are calling or a Wild card
	public boolean checkIfCardExist(int number, String color, Player playerCards){
		
		//Gets the user's cards
		ArrayList<Card> cards = playerCards.getCards();
		boolean found = false;
		
		if(number == 10){
			for(int i = 0; i < cards.size(); i++){
				if(cards.get(i).getNumber() == 10){
					cards.get(i).setColor(color);
					found = true;
					break;
				}
				else
					found = false;
			}
			
		}
		else{
			int i = 0;
			for(; i < cards.size(); i++){
				if(cards.get(i).getNumber() == number && cards.get(i).getColor().equalsIgnoreCase(color)){
					found =  true;
					break;
				}
			}
			if(cards.get(i).getNumber() == discardCard().getNumber() || cards.get(i).getColor().equalsIgnoreCase(discardCard().getColor()))
					found = true;
			else
				found = false;
			
		}
		return found;
	}
	
	
	public void findCard(int number, String color, Player playerCards){
		//Gets the user's cards
		ArrayList<Card> cards = playerCards.getCards();
		
		Card cardPicked = null;
		for(int i = 0; i < cards.size(); i++){
			if(cards.get(i).getNumber() == number && cards.get(i).getColor().equalsIgnoreCase(color)){
				cardPicked = cards.get(i);
				break;
			}
				parent.getChildren().remove(userInput);
				
		}
		
		if(cardPicked.getNumber() == 10){
			discardPile.add(cardPicked);
			playerCards.removeCard(cardPicked);	
		}
			if(cardPicked.getNumber() == 8 || cardPicked.getNumber() == 9){
				mustPickUp = true;
			}
			discardPile.add(cardPicked);
			playerCards.removeCard(cardPicked);

		updatePlayerDeck();
		switchPlayer();
	}

	
	public int convertNumber(String n){
		if(n.equals("1"))
			return 1;
		else if(n.equals("2"))
			return 2;
		else if(n.equals("3"))
			return 3;
		else if(n.equals("4"))
			return 4;
		else if(n.equals("5"))
			return 5;
		else if(n.equals("6"))
			return 6;
		else if(n.equals("7"))
			return 7;
		else if(n.equals("8"))
			return 8;
		else if(n.equals("9"))
			return 9;
		else if(n.equals("10"))
			return 10;
		else 
			return 0;
		
	}

	public void computerTurn(Computer computerCards){

		ArrayList<Card> cards = computerCards.getCards();
		boolean computerHasMatchingCard = computerCards.hasCard(discardCard());
		String[] colors = { "Blue", "Red", "Green", "Yellow"};
		
		
		if(discardCard().getNumber() == 8 && mustPickUp == true){
			createPopUp("Computer Draw 1 card");
			computerCards.addCard(drawnCard());
			drawPile.remove(drawnCard());
			mustPickUp = false;
			updateComputerDeck();
			switchPlayer();
		}
		else if(discardCard().getNumber() == 9 && mustPickUp == true){
			createPopUp("Computer Draw 2 cards");
			Card getCard;
		
				getCard = drawnCard();
				computerCards.addCard(getCard);
				drawPile.remove(getCard);
				
				getCard = drawnCard();
				computerCards.addCard(getCard);
				drawPile.remove(getCard);
				mustPickUp = false;
				updateComputerDeck();
				switchPlayer();	
		}
		else if(computerHasMatchingCard == true){
	
			createPopUp("\nComputer is picking a card... ");	
			
			Card cardPicked = null;
			Card discardCard = discardCard();
			int getColor;
			for(int i = 0; i < cards.size(); i++){
				cardPicked = cards.get(i);
				if(cardPicked.getNumber() == 10 ){
					getColor =  (int)(Math.random() * 4);
					cardPicked.setColor(colors[getColor]);
					
					discardPile.add(cardPicked);
					computerCards.removeCard(cardPicked);
					break;
				}
				else if(cardPicked.getNumber() == discardCard.getNumber() || cardPicked.getColor().equalsIgnoreCase(discardCard.getColor())){
				
					discardPile.add(cardPicked);
					if(cardPicked.getNumber() == 8 || cardPicked.getNumber() == 9)
						mustPickUp = true;
					computerCards.removeCard(cardPicked);
					break;
				}
			}

			System.out.print("\nHere is the card Computer picked: \n" + discardCard().toString() + "\n");
			
			updateComputerDeck();
			switchPlayer();	
		}
		else{
			int getColor;
			createPopUp("Computer does not have any matching cards. \n Computer picking up a card.");
			createImagePopUp();
			Card cardDrawn = drawnCard();
			if(cardDrawn.getNumber() == 10){
				getColor = (int) Math.random() * 2;
				cardDrawn.setColor(colors[getColor]);
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
				createPopUp("The card Computer picked up does not match the discard card.");
				computerCards.addCard(cardDrawn);
				
				drawPile.remove(cardDrawn);
			}
			updateComputerDeck();
			switchPlayer();		
		}
		
	}
	public void updateComputerDeck(){
		
		computerDeck.getChildren().clear();
		parent.getChildren().remove(computerDeck);
		ArrayList<Card> cards = computer.getCards();
		ImageView img;
		for(int i = 0; i < cards.size(); i++){
			img = new ImageView(cards.get(i).getImageName());
			img.setFitHeight(100);
			img.setFitWidth(60);
			computerDeck.getChildren().add(img);
		}
		
		parent.getChildren().add(computerDeck);
		
		updateDiscardPile();
	}
	
	public void updatePlayerDeck(){
		playerDeck.getChildren().clear();
		parent.getChildren().remove(playerDeck);
		ArrayList<Card> cards = player.getCards();
		ImageView img;
		for(int i = 0; i < cards.size(); i++){
			img = new ImageView(cards.get(i).getImageName());
			img.setFitHeight(100);
			img.setFitWidth(60);
			playerDeck.getChildren().add(img);
		}
		parent.getChildren().add(playerDeck);
		updateDiscardPile();
	}
	
	public void updateDiscardPile(){
		discardP.getChildren().clear();
		parent.getChildren().remove(discardP);
		
		ImageView img = new ImageView(new Image(discardCard().getImageName()));
		img.setFitHeight(100);
		img.setFitWidth(60);
		
		if(discardCard().getNumber() == 10){
		
			Text colorOfWild = new Text();
			
			
			colorOfWild.setTranslateX(15);
			colorOfWild.setTranslateY(26);
	
			
			if(discardCard().getColor().equalsIgnoreCase("Yellow")){
				colorOfWild.setText("Color: Yellow");
				colorOfWild.setFill(Color.YELLOW);
			}
			else if(discardCard().getColor().equalsIgnoreCase("Red")){
				colorOfWild.setText("Color: Red");
				colorOfWild.setFill(Color.RED);
			}
			else if(discardCard().getColor().equalsIgnoreCase("Green")){
				colorOfWild.setText("Color: Green");
				colorOfWild.setFill(Color.GREENYELLOW);
			}
			else if(discardCard().getColor().equalsIgnoreCase("Blue")){
				colorOfWild.setText("Color: Blue");
				colorOfWild.setFill(Color.CORNFLOWERBLUE);
			}
			
			discardP.getChildren().addAll(img, colorOfWild);
			parent.getChildren().addAll(discardP);
		}
		
		else{
			discardP.getChildren().addAll(img);
			parent.getChildren().addAll(discardP);
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
		//get size
		int index = drawPile.size() - 1;
				
		//Copy the current card
		Card topCard = drawPile.get(index);
				
		//Return the copied card
		return topCard;
	}

	public void replaceDrawPile(){
		
		Card getDiscardCard = discardCard();
		for(int i = 0; i < discardPile.size() - 1; i++){
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

	public void computerHasUno(){
		if(computer.has2CardsLeft() && computer.hasCard(discardCard())){
				showUnoButton();
		}
	}
	
	public void playerHasUno(){
		if(player.has2CardsLeft() && player.hasCard(discardCard())){
			showUnoButton();
			
		}
	}
	public void showUnoButton(){
		Button btnUno = new Button("UNO");
		btnUno.setTranslateX(690);
		btnUno.setTranslateY(400);
		btnUno.setScaleX(1.2);
		btnUno.setScaleY(1.2);
		
		parent.getChildren().add(btnUno);

		userInput.setVisible(false);
	
		long startTime = System.currentTimeMillis();
	    btnUno.setOnAction(e-> {
	    	final long currentTime = System.currentTimeMillis();
	    	final boolean pressedFast =  checkTimeUnoPressed(startTime, currentTime);
	    	if(pressedFast == true){
	    		
	    		if(computer.has2CardsLeft()){
	    			createPopUp("Player pressed Uno before Computer.\n Computer picking up 2 cards");
	    			Card getCard;
					getCard = drawnCard();
					computer.addCard(getCard);
					drawPile.remove(getCard);
					
					getCard = drawnCard();
					computer.addCard(getCard);
					drawPile.remove(getCard);
					updateComputerDeck();
					switchPlayer();	
	    		}
	    		
	    	}
	    	
	    	else if(pressedFast == false){
	  
	    		if(player.has2CardsLeft()){
	    			createPopUp("Computer pressed Uno before you.\n Now You must pick up 2 cards");
	    			Card getCard;
					getCard = drawnCard();
					player.addCard(getCard);
					drawPile.remove(getCard);
					
					getCard = drawnCard();
					player.addCard(getCard);
					drawPile.remove(getCard);
					updatePlayerDeck();
					switchPlayer();	
	    		}
	    		

	    	} 
	    	userInput.setVisible(true);
	    	btnUno.setVisible(false);
	    	parent.getChildren().remove(btnUno);
	
		});
	
	}

	public boolean checkTimeUnoPressed(long startTime, long endTime){
		final int time = 1500;
		boolean pressedFast;
		
		
		long difference = endTime - startTime;
		System.out.println("Start: " + startTime + " End: " + endTime + " Diff: " + difference);
		if(difference <= time){
			pressedFast = true;
		}
		else{
			pressedFast = false;
		}
			
		return pressedFast;
	}
	
	public void createPopUp(String text){
		Alert dialogBox = new Alert(AlertType.INFORMATION);
		 dialogBox.setTitle("Information Dialog");
		 dialogBox.setHeaderText(null);
		 dialogBox.setContentText(text);
		 dialogBox.showAndWait();
	}
	
	public void createImagePopUp(){
		Alert dialogBox = new Alert(AlertType.INFORMATION);
		 dialogBox.setTitle("Information Dialog");
		 dialogBox.setHeaderText(null);
		 dialogBox.setContentText("Here's the card picked up from the Draw Pile");
		 ImageView img = new ImageView(new Image(drawnCard().getImageName()));
		 img.setFitHeight(100);
		 img.setFitWidth(60);
		 dialogBox.setGraphic(img);
		 dialogBox.showAndWait();
	}
}


