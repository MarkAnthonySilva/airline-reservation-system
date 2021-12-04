package airlinereservationsystem.view;

import java.util.Scanner;

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
		
		System.out.print("\nEnter integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
	}
	
	public void diplayInsert(Blacklist b) {
		System.out.println("\nPassenger to be Blacklisted");
		System.out.println("PRESS ESC TO GO BACK TO BLACKLIST MENU");
		
//		System.out.println("PRESS 1 to get List of Passengers");
		System.out.print("pID of Passenger: ");
		this.sc.nextLine();
		
	}
}
