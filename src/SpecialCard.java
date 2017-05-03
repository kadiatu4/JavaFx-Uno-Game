//Kadiatou Diallo
//110331331
//CSE 114
//Project 1

public class SpecialCard extends Card{
	//Data Field
	private String speciality;
	
	//Default Constructor
	SpecialCard(){speciality = "";}
	
	//Constructor #1
	SpecialCard(String name, String imageName, int number, String speciality){
		super(name, number );
		setCardImage(imageName);
		this.speciality = speciality;
	}
	
	//Constructor #2
	SpecialCard(String name, String imageName, String color, int number, String speciality){
		super(name, color, number);
		setCardImage(imageName);
		this.speciality = speciality;
	}
	
	//Set Color
	public void setColor(String color){
		super.setColor(color);
	}
	
	public void setCardImage(String cardImage){
		super.setImageName(cardImage);
	}
	//Get Speciality
	public String getSpeciality(){return speciality;}
	
	//ToString
		public String toString(){
			return super.toString() + " Speciality-" + speciality;
		}
}


