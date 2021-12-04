package airlinereservationsystem.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import airlinereservationsystem.helper;
import airlinereservationsystem.model.Airline;

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
		System.out.println("3: Delete Airline by aID");
		System.out.println("4: Get Blacklist by aID");
		
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
	
	/**
	 * Display prompt for deletion of an airline using their aID
	 * @return the aID of the airline to be deleted
	 */
	public String displayDelete() {
		System.out.println("\nAirline to be Deleted");
		
		System.out.print("Airline aID: ");
		this.sc.nextLine();
		String aIDAsString = this.sc.nextLine();
		while(aIDAsString.equals("") || !helper.isStringNumeric(aIDAsString)) {
			System.out.println("\nAirline aID cannot be blank and must be an integer");
			System.out.print("Airline aID: ");
			aIDAsString = this.sc.nextLine();
		}
		
		return aIDAsString;
	}
	
	/**
	 * Display the success of deletion of an airline
	 * @param isDeleted true if airline was successfully deleted, otherwise false
	 * @param aID the aID of the airline that was deleted
	 */
	public void displayDeleteSucess(Boolean isDeleted, int aID) {
		if(isDeleted) {
			System.out.println("The Airline with the aID: " + aID + " was successfully deleted or does not exist within database");
		} else {
			System.out.println("The Airline with the aID: " + aID + " was NOT successfully deleted");
		}
	}
	
	/**
	 * Display All airlines in the airline table
	 * @param airlineMap the hashmap that contains all airlines
	 */
	public void displayListOfAirlines(HashMap<Integer, Airline> airlineMap) {
		System.out.println("\n" + "LIST OF ALL AIRLINES");
		System.out.printf("%-3s %-32s\n", "aID", "Airline Name");
		
		for(Map.Entry<Integer, Airline> entry : airlineMap.entrySet())
		{
			Airline a = entry.getValue();
			System.out.format("%-3d %-32s\n" , a.getaID(), a.getName());
		}
	}
	
	public String displayaIDPrompt() {
		System.out.println("\nAirline Blacklist");

		System.out.print("Airline aID: ");
		this.sc.nextLine();
		String aIDAsString = this.sc.nextLine();
		while(aIDAsString.equals("") || !helper.isStringNumeric(aIDAsString)) {
			System.out.println("\nAirline aID cannot be blank and must be an integer");
			System.out.print("Airline aID: ");
			aIDAsString = this.sc.nextLine();
		}

		return aIDAsString;
	}
}
