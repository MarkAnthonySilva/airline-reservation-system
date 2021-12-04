package airlinereservationsystem.view;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import airlinereservationsystem.helper;
import airlinereservationsystem.model.Passenger;

public class PassengerView {
	private Scanner sc;
	
	public PassengerView(Scanner sc) {
		this.sc = sc;
	}
	
	/**
	 * Main Menu For Passenger SQL Commands. Displays command to Manipulate Passenger Table like
	 * Insertion, Read, and Delete.
	 * @return
	 */
	public String display() {
		System.out.println("\nPASSENGER MENU\nFunctions of Passenger");
		System.out.println("0: Go to Home Menu");
		System.out.println("1: Insert Passenger");
		System.out.println("2: Select Passengers by Name");
		System.out.println("3: Select Passengers by pID");
		System.out.println("4: Delete Passengers by Name");
		
		System.out.print("\nEnter integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
		
	}
	
	/**
	 * Displays a detailed view of more information about the Passenger
	 * @param passenger the passenger to be displayed
	 * @return
	 */
	public String displayPassenger(Passenger passenger) {
		System.out.println("\n" + passenger.getFirstName() + " " + passenger.getLastName() + " (pID: " + passenger.getpID() + ")" );
		System.out.println("0: Go Back to Passenger Menu");
//		System.out.println("1: Get Ticket Information");
//		System.out.println("2: Get Purchase Information");
//		System.out.println("3: Get Black List Information");
		
		System.out.print("\nEnter integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
	}
	
	/**
	 * Display in console the prompts for Passenger info to be inserted into the table
	 * @param passenger the passenger information passed from controller that will be updated in displayInsert()
	 */
	public void displayInsert(Passenger passenger){
		
		System.out.println("\nPassenger Information for Insertion");
		
		System.out.print("Passenger First Name: ");
		this.sc.nextLine();
		String firstName = this.sc.nextLine();
		while(firstName.equals("")) {
			System.out.println("\nFirst Name cannot be blank");
			System.out.print("Passenger First Name: ");
			firstName = this.sc.nextLine();
		}
		
		System.out.print("Passenger Last Name: ");
		String lastName = this.sc.nextLine();
		while(lastName.equals("")) {
			System.out.println("\nLast Name cannot be blank");
			System.out.print("Passenger Last Name: ");
			lastName = this.sc.nextLine();
		}
		
		System.out.print("Passenger Age: ");
		String ageString = this.sc.nextLine();
		while(ageString.equals("") || !helper.isStringNumeric(ageString)) {
			System.out.println("\nAge cannot be blank and must be an integer");
			System.out.print("Passenger Age: ");
			ageString = this.sc.nextLine();
		}
		
		passenger.setFirstName(firstName);
		passenger.setLastName(lastName);
		passenger.setAge(Integer.parseInt(ageString));
	}
	
	/**
	 * A prompt to display which name is to be searched for in the database
	 * @return the name to be searched for
	 */
	public String displayNameSelect() {
		System.out.println("0: Go Back to Passenger Menu");
		System.out.print("Select Passenger First Name: ");
		return this.sc.next();
	}
	
	/**
	 * Display a list of Passengers to be selected from
	 * @param menuTitle	The title of this Passenger Table. Two Possibilities (SELECT PASSENGER) OR (DELETE PASSENGER)
	 * @param displayRowNumber display (rowNumber): alongside the passenger info
	 * @param hm a HashMap<Integer, Passenger> that contains all the Passengers to be displayed. Key = Row number, Value = Passenger Object with information about Passenger
	 * @throws SQLException
	 */
	public void displayListOfPassengers(String menuTitle, Boolean displayRowNumber, HashMap<Integer, Passenger> hm) throws SQLException {
		System.out.println("\n" + menuTitle);

		if(displayRowNumber) {
			System.out.println("0: Go Back to Passenger Menu");
		}
		// Columns For Passenger List
		if(displayRowNumber) {
			System.out.printf("%6s %-24s %-24s %-6s \n", "pID", "First Name", "Last Name", "Age");
		} else {
			System.out.printf("%3s %-24s %-24s %-6s \n", "pID", "First Name", "Last Name", "Age");
		}

		for(Map.Entry<Integer, Passenger> entry : hm.entrySet())
		{
			Passenger p = entry.getValue();
			if(displayRowNumber) {
				System.out.format("%d: %-3d %-24s %-24s %-6d\n" , entry.getKey(), p.getpID(), p.getFirstName(), p.getLastName(), p.getAge());
			} else {
				System.out.format("%-3d %-24s %-24s %-6d\n" , p.getpID(), p.getFirstName(), p.getLastName(), p.getAge());
			}
		}

	}
	
	/**
	 * Prompt for passenger table that will take in the row number of a specific passenger
	 * @return the row of the specific Passenger selected
	 */
	public String displayPassengerPrompt() {
		System.out.print("\nEnter integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
	}
	
	/**
	 * A prompt to display which PID is to be searched for in the database
	 * @return the PID to be searched for
	 */
	public String displaypIDSelect() {
		System.out.println("0: Go Back to Passenger Menu");
		System.out.print("Select Passenger by PID: ");
		return this.sc.next();
	}
}
