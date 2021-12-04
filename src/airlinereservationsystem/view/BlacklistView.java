package airlinereservationsystem.view;

import java.util.Scanner;

import airlinereservationsystem.helper;
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
			pIDAsString = this.sc.nextLine();
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
}
