package airlinereservationsystem.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import airlinereservationsystem.helper;
import airlinereservationsystem.model.Passenger;

public class PassengerView {
	private Scanner sc;
	
	public PassengerView(Scanner sc) {
		this.sc = sc;
	}

	public String display() {
		System.out.println("\nPASSENGER MENU\nFunctions of Passenger");
		System.out.println("0: Go to Home Menu");
		System.out.println("1: Insert Passenger");
		System.out.println("2: Select Passengers by Name");
		System.out.println("3: Select Passengers by pID");
		
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
		System.out.println("1: Get Ticket Information");
		System.out.println("2: Get Purchase Information");
		System.out.println("3: Get Black List Information");
		
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
		while(ageString.equals("") && helper.isStringNumeric(ageString) == false) {
			System.out.println("\nAge cannot be blank");
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
		System.out.print("Select Passenger First Name: ");
		this.sc.nextLine();
		return this.sc.nextLine();
	}
	
	/**
	 * Will receive a param of ResultSet rs and will display that information in table format with a navigation integer.
	 * This navigation integer can then be used to get more details on that passenger or delete that Passenger depending on the query.
	 * @param rs the ResultSet to be displayed by the console in table format.
	 * @param menuTitle title of the menu which depends on which query is being done on the table
	 * @parm hm	Hashmap to store rowCount as key and the corresponding pID as the value
	 */
	public String displayListOfPassengers(ResultSet rs, String menuTitle, HashMap<Integer, Integer> hm) throws SQLException {
		System.out.println("\n" + menuTitle);

		if(rs.next() == false) {
			System.out.println("Passenger Not Found");
			this.display();
		}
		else {
			int rowCount = 1;
			System.out.println("0: Go Back to Passenger Menu");
			System.out.printf("%6s %24s %24s %6s \n", "pID", "First Name", "Last Name", "Age");
			do {
				int pID = rs.getInt("pID");
				int age = rs.getInt("age");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				System.out.format("%d: %3d %24s %24s %6d\n" , rowCount, pID, firstName, lastName, age);
				hm.put(rowCount, pID);
				rowCount++;
			} while(rs.next());
		}
		
		System.out.print("\nEnter integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
	}
}
