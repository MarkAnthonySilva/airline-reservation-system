package airlinereservationsystem.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

import airlinereservationsystem.helper;
import airlinereservationsystem.model.Airline;
import airlinereservationsystem.model.Blacklist;

public class BlacklistView {
	private Scanner sc;
	
	public BlacklistView(Scanner sc) {
		this.sc = sc;
	}
	
	/**
	 * Display the main menu for the functions for the Blacklist
	 * @return the navigation integer of what function the user wants to execute
	 */
	public String display() {
		System.out.println("\nBLACKLIST MENU\nFunctions of Blacklist");
		System.out.println("0: Go to Home Menu");
		System.out.println("1: Insert Passenger to be Blacklisted");
		System.out.println("2: Get Blacklist by aID");
		System.out.println("3: Get Airline with Minimum number of Blacklisted Passenger");
		System.out.println("4: Get All Blacklisted Passenger Info");
		System.out.println("5: Archive Blacklist");
		
		System.out.print("\nEnter integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
	}
	
	public void diplayInsert(Blacklist b) {
		System.out.println("\nPassenger to be Blacklisted from an Airline");
//		System.out.println("PRESS ESC TO GO BACK TO BLACKLIST MENU");
		
//		System.out.println("PRESS 1 to get List of Passengers");
		System.out.print("pID of Passenger: ");
		this.sc.nextLine();
		String pIDAsString = this.sc.nextLine();
		while(pIDAsString.equals("") || !helper.isStringNumeric(pIDAsString)) {
			System.out.println("\nPassenger pID cannot be blank and must be an integer");
			System.out.print("pID of Passenger: ");
			pIDAsString = this.sc.nextLine();
		}
		
//		System.out.println("PRESS 1 to get List of Airlines");
		System.out.print("aID of Airline: ");
		String aIDAsString = this.sc.nextLine();
		while(aIDAsString.equals("") || !helper.isStringNumeric(aIDAsString)) {
			System.out.println("\nAirline aID cannot be blank and must be an integer");
			System.out.print("aID of Airline: ");
			aIDAsString = this.sc.nextLine();
		}	
		
		System.out.print("Reason: ");
		String reason = this.sc.nextLine();
		while(reason.equals("")) {
			System.out.println("\nReason cannot be blank");
			System.out.print("Reason: ");
			reason = this.sc.nextLine();
		}	
		
		b.setpID(Integer.parseInt(pIDAsString));
		b.setaID(Integer.parseInt(aIDAsString));
		b.setReason(reason);
	}
	
	/**
	 * Display of the insertion into blacklist table was successful
	 * @param isInserted true if insertion was successful, otherwise false
	 * @param b the information about the inserted row into blacklist table
	 */
	public void displayInsertSucess(Boolean isInserted, Blacklist b) {
		if(isInserted) {
			System.out.println("The Passenger(pID: " + b.getpID() + ") was successfully blacklisted from the airline(aID: " + b.getaID() + ")");
		} else {
			System.out.println("The Passenger(pID: " + b.getpID() + ") was NOT successfully blacklisted from the airline(aID: " + b.getaID() + ")");
		}
	}
	
	/**
	 * Display the prompt for the aID of an airline
	 * @return the aID of the airline
	 */
	public String displayAidPrompt() {
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
	
	/**
	 * Display the prompt for number of blacklisted Passenger() 
	 */
	public String displayPassNumPrompt() {
		System.out.println("\nAirline Blacklist Number of Passenger");
		
		System.out.print("Minimum number of Passengers: ");
		this.sc.nextLine();
		String numPassAsString = this.sc.nextLine();
		while(numPassAsString.equals("") || !helper.isStringNumeric(numPassAsString)) {
			System.out.println("\nMinimum number of Passengers cannot be blank and must be an integer");
			System.out.print("Minimum number of Passengers: ");
			numPassAsString = this.sc.nextLine();
		}

		return numPassAsString;
	}
	
	/**
	 * Display a table format of list of airlines and their number of blacklisted passengers
	 * @param listOfAirlines the list of airlines with at least numPass of blacklisted passengers
	 * @param numPass minimum number of passengers that are blacklisted
	 */
	public void displayListOfAirlines(ArrayList<Airline> listOfAirlines, int numPass) {
		
		if(listOfAirlines == null) {
			System.out.println("There are no airlines with blacklisted passengers of size " + numPass);
		}
		System.out.println("\nList of Airlines with a Blacklist of size " + numPass + " or larger");
		System.out.printf("%-32s %-3s %-34s\n", "Airline Name", "aID", "Number of Passengers Blacklisted");
		
		for (Airline a : listOfAirlines) {
			System.out.printf("%-32s %-3s %-3d\n", a.getName(), a.getaID(), a.getNumOfPassengerBlacklisted());
		}
	}
	
	/**
	 * Display the prompt for a TimeStamp
	 * @return
	 */
	public String displayPromptTimestamp() {
		System.out.println("\nArchive Blacklist");

		System.out.print("Cut off Timestamp (yyyy-mm-dd hh:mm:ss): ");
		this.sc.nextLine();
		String timeStampAsString = this.sc.nextLine();
		while(timeStampAsString.equals("") || helper.convertStringToTimestamp(timeStampAsString) == null) {
			System.out.println("\nTimestamp cannot be blank and must be form yyyy-mm-dd hh:mm:ss");
			System.out.print("Cut off Timestamp (yyyy-mm-dd hh:mm:ss): ");
			timeStampAsString = this.sc.nextLine();
		}

		return timeStampAsString;
	}
	
	/**
	 * Display a table of blacklisted Passenger info
	 * @param rs
	 * @throws SQLException 
	 */
	public void displayListOfPassenger(ResultSet rs) throws SQLException {
		System.out.println("\nLIST OF ALL BLACKLISTED PASSENGERS");
		
		System.out.printf("%-3s %-24s %-24s %-3s %-24s %-50s\n", "pID", "First Name", "Last Name", "aID", "Creation Date", "Reason");
		if(rs.next() == false) {
			System.out.println("NO BLACKLISTED PASSENGERS");
		} else {
			do {
				int pID = rs.getInt("pID");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				int aID = rs.getInt("aID");
				Timestamp creationDate = rs.getTimestamp("creationDate");
				String reason = rs.getString("reason");
				
				System.out.printf("%-3d %-24s %-24s %-3d %-24s %-50s\n", pID, firstName, lastName, aID, creationDate.toString(), reason);
			} while (rs.next());
		}
	}
}
