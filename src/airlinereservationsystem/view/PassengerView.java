package airlinereservationsystem.view;

import java.io.Console;

import airlinereservationsystem.model.Passenger;

public class PassengerView {
	
	public String display() {
		Console console = System.console();
		if(console !=null){
			System.out.println("\nPASSENGER MENY\nFunctions of Passenger");
			System.out.println("0: Go to Home Menu");
			System.out.println("1: Insert Passenger");
			String navIntAsString = console.readLine("Enter integer: ");
			return navIntAsString;
		}
		else {
			System.out.println("Console is null: " + console);
		}
		
		return null;
	}
	
	public void displayInsert(){
		
	}
}
