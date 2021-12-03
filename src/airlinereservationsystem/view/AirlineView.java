package airlinereservationsystem.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import airlinereservationsystem.helper;
import airlinereservationsystem.model.Airline;
import airlinereservationsystem.model.Passenger;

public class AirlineView {
	private Scanner sc;
	
	public AirlineView(Scanner sc) {
		this.sc = sc;
	}
	
	/**
	 * Main Menu for Airline Commands. Allows for insertion, read, and deletion of airlines
	 * @return the integer of the selected command
	 */
	public String display() {
		System.out.println("\nAIRLINE MENU\nFunctions of Airline");
		System.out.println("0: Go to Home Menu");
		System.out.println("1: Insert Airline");
		System.out.println("2: Select All Airline");
//		System.out.println("3: Select Airline by aID");
//		System.out.println("4: Delete Airline by Name");
		
		System.out.print("\nEnter integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
	}
	
	/**
	 * Display the prompt for a new Airline Insertion
	 * @param a the airline information to be saved into the database
	 */
	public void displayInsert(Airline a) {
		System.out.println("\nAirline Information for Insertion");

		System.out.print("Airline Name: ");
		this.sc.nextLine();
		String name = this.sc.nextLine();
		while(name.equals("")) {
			System.out.println("\nAirline Name cannot be blank");
			System.out.print("Passenger First Name: ");
			name = this.sc.next();
		}
		
		a.setName(name);
	}
	
	/**
	 * Displays of the insertion process was a success or a failure
	 * @param isInserted true if insertion was a sucess, otherwise false
	 * @param a	airline that was inserted
	 */
	public void displayInsertSuccess(Boolean isInserted, Airline a) {
		if(isInserted) {
			System.out.println("The Airline " + a.getName() + " was successfully added to database");
		} else {
			System.out.println("The Airline " + a.getName() + " was NOT successfully added to database");
		}
	}
	
	public void displayListOfAirlines(HashMap<Integer, Airline> airlineMap) {
		System.out.println("\n" + "LIST OF ALL AIRLINES");
		System.out.printf("%6s %-32s\n", "aID", "Airline Name");
		
		for(Map.Entry<Integer, Airline> entry : airlineMap.entrySet())
		{
			Airline a = entry.getValue();
			System.out.format("%d: %-3d %-32s\n" , entry.getKey(), a.getaID(), a.getName());
		}
	}
}
