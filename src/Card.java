//Kadiatou Diallo
//110331331
//CSE 114
//Project 1
public class Card {
	//Date Field
	private String name;
	private String cardImage;
	private String color;
	private int number;
	
	//Default Constructor
	Card(){name = ""; color = ""; number = 0;}
	
	//Constructor #1
	Card(String name,int number){
		this.name = name;
		this.number = number;
	}
	
	//Constructor #2
	Card(String name, String color, int number){
		this.name = name;
		this.color = color;
		this.number = number;
	}
	
	//Set Color
	public void setColor(String color){
		this.color = color;
	}
	
	//Set Image Name
	public void setImageName(String cardImage){
		this.cardImage = cardImage;
	}
	
	//Get Name
	public String getName(){return name;}
	
	//Get Color
	public String getColor(){return color;}
	
	//Get Number
	public int getNumber(){return number;}
	
	//Get Image Name
	public String getImageName(){return cardImage;}
	
	//ToString
	public String toString(){
		return "\nCard: " + " Name-" + name + " Color-" + color + " Number-" + number + " CardImage-" + cardImage;
	}
}


