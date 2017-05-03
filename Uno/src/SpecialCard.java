//Kadiatou Diallo
//110331331
//CSE 114
//Project 

public class SpecialCard extends Card{
	//Data Field
	private String speciality;
	
	//Default Constructor
	SpecialCard(){speciality = "";}
	
	//Constructor #1
	SpecialCard(String name, int number, String speciality){
		super(name, number);
		this.speciality = speciality;
	}
	
	//Constructor #2
	SpecialCard(String name, String color, int number, String speciality){
		super(name, color, number);
		this.speciality = speciality;
	}
	
	//Set Color
	public void setColor(String color){
		super.setColor(color);
	}
	
	//Get Speciality
	public String getSpeciality(){return speciality;}
	
	//ToString
		public String toString(){
			return super.toString() + " Speciality-" + speciality;
		}
}

