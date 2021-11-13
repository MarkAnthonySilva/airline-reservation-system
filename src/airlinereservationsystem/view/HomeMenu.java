package airlinereservationsystem.view;

import java.io.Console;

import airlinereservationsystem.helper;

public class HomeMenu {
	
	public String displayHome() {
		Console console = System.console();
		if(console !=null){
			System.out.println("Navigate to where");
			System.out.println("1: Passenger");
			String navIntAsString = console.readLine("Enter integer: ");
			return navIntAsString;
		}
		else {
			System.out.println("Console is null: " + console);
		}
		
		return null;
	}
}
