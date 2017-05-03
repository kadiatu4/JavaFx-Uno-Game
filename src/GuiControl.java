//import javafx.application.Application;
//import javafx.event.*;
//import static javafx.application.Application.launch;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.control.TextFormatter;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.geometry.Rectangle2D;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.*;
//
//
//public class GuiControl extends Application {
//
//	private Text message = new Text(); //Stores current Message
//	
//	private HBox playerDeck = new HBox(10);		
//	private HBox computerDeck = new HBox(10);
//	private HBox drawP = new HBox(10);
//	private HBox discardP = new HBox();
//	private VBox userInput = new VBox (10);
//	private Pane parent = new Pane();	//Creates Pane Object
//	  
//	private Game game = new Game();
//	
//	//Main method
//	public static void main(String args[]){          
//	      launch(args);     
//	  }      
//	 
//  @Override     
//  public void start(Stage primaryStage) throws Exception {  
//	  primaryStage.setScene(new Scene(mainParent()));
//      primaryStage.setWidth(800);
//      primaryStage.setHeight(600);
//      primaryStage.setResizable(false);
//      primaryStage.setTitle("UNO GAME");
//      primaryStage.show();
//	
//      
//  }    
//private Parent mainParent(){
//	  
//	  parent.setPrefSize(800, 600);
//	  
//	  Region background  = new Region();
//	  background.setPrefSize(800, 600); 
//	  background.setStyle("-fx-background-color: rgba(255, 255, 255, 1)");
//	  
//	  VBox playArea = new VBox(40);
//	  Rectangle leftSide = new Rectangle(570, 600);
//	  leftSide.setFill(Color.LIGHTBLUE);
//	  playArea.getChildren().add(leftSide);
//	  	  
//	  Button btnPlay = new Button("PLAY");
//	  HBox playButton = new HBox(btnPlay);
//	  playButton.setTranslateX(650);
//	  playButton.setTranslateY(250);
//	  playButton.setAlignment(Pos.CENTER);
//	  
//	  userInput.setTranslateX(590);
//	  userInput.setTranslateY(220);
//	  
//		playerDeck.setTranslateX(10);
//		playerDeck.setTranslateY(390);
//		
//		computerDeck.setTranslateX(10);
//		computerDeck.setTranslateY(80);
//		
//		message.setText(game.getMessage());
//		
//	  parent.getChildren().addAll(background, playArea, playButton, message);
//	  
//	  
//	  btnPlay.setOnAction(event -> {
//		 parent.getChildren().remove(playButton);
//		 game.startGame();
//		  });
//	  
//	  return parent;
//	  
//  }
//
////Shows the Cards Picked and Add to Parent
//	public void cardsPicked(Card playerCard, Card computerCard){
//
//		//Text
//		Text firstCards = new Text("Here are the first cards picked by both players");
//		firstCards.setFont(new Font(18));
//		firstCards.setFill(Color.BLACK);
//		firstCards.setTranslateX(100);
//		firstCards.setTranslateY(-140);
//	      
//		//------------Player's Card-------------
//		VBox card1 = new VBox(50);
//		
//		//Load Images
//		  Image image1 = new Image(playerCard.getImageName());
//		//Display Image View
//	      ImageView iv1 = new ImageView();
//	      
//	      //Image 1
//	      iv1.setImage(image1);
//	      iv1.setFitHeight(170);
//	      iv1.setFitWidth(120);
//	      iv1.setTranslateX(150);
//	      iv1.setTranslateY(190);
//	      
//	      //Text 
//	      Text playerText = new Text("Player's Card");
//	      playerText.setFont(new Font(15));
//	      playerText.setFill(Color.BLACK);
//	      playerText.setTranslateX(170);
//	      playerText.setTranslateY(150);
//	      
//	      card1.getChildren().addAll(iv1, playerText,firstCards);
//	    //------------Computer's Card-------------
//	      VBox card2 = new VBox(50);
//			//Load Images
//			 Image image2 = new Image(computerCard.getImageName());
//			//Display Image View
//			 ImageView iv2 = new ImageView();
//			 
//			//Image 2
//		      iv2.setImage(image2);
//		      iv2.setFitHeight(170);
//		      iv2.setFitWidth(120);
//		      iv2.setTranslateX(340);
//		      iv2.setTranslateY(190);
//		      
//		      //Text
//		      Text computerText = new Text("Computer's Card");
//		      computerText.setFont(new Font(15));
//		      computerText.setFill(Color.BLACK);
//		      computerText.setTranslateX(340);
//		      computerText.setTranslateY(150);
//		      
//		      card2.getChildren().addAll(iv2, computerText);
//	     
//		      //Message
//		      message.setTranslateX(210);
//		      message.setTranslateY(420);
//		      message.setFont(new Font(22));
//		      message.setFill(Color.BLACK);
//		      
//		      Button btnOkay = new Button("Okay");
//			  HBox OkayButton = new HBox(btnOkay);
//			  btnOkay.setTranslateX(280);
//			  btnOkay.setTranslateY(450);
//			  btnOkay.setAlignment(Pos.CENTER);
//			  
//			  parent.getChildren().addAll(card1, card2, btnOkay);
//
//			  btnOkay.setOnAction(event -> {
//				  parent.getChildren().remove(card1);
//				  parent.getChildren().remove(card2);
//				  parent.getChildren().remove(btnOkay);
//				  
//				  addToScreen();
//				
//			});
//	}
//	
//	public void addToScreen(){
//		  message.setText("");
//		  Text txt1 = new Text("Computer's Cards: ");
//		  txt1.setFill(Color.BLACK);
//		  txt1.setTranslateX(50);
//		  txt1.setTranslateY(50);
//		  txt1.setFont(new Font(15));
//		 
//		  Text txt2 = new Text("Player's Cards: ");
//		  txt2.setFill(Color.BLACK);
//		  txt2.setTranslateX(50);
//		  txt2.setTranslateY(370);
//		  txt2.setFont(new Font(15));
//		  
//		  parent.getChildren().addAll(txt1, txt2);
//	} 
//	
//}
