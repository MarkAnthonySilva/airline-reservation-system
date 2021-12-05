package airlinereservationsystem.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import airlinereservationsystem.helper;
import airlinereservationsystem.model.Ticket;

public class TicketView {
	
	private Scanner sc;
	
	public TicketView(Scanner sc) {
		this.sc = sc;
	}
	
	/**
	 * Main Menu for Ticket Commands. Allows for insertion, read, and deletion of Tickets
	 * @return the integer of the selected command
	 */
	public String display() {
		System.out.println("\nTICKET MENU\nFunctions of Ticket");
		System.out.println("0: Go to Home Menu");
		System.out.println("1: Select by pID (TODO)");
//		System.out.println("2: Select All Tickets (TODO)");
//		System.out.println("3: Delete Ticket by Ticket ID (TODO)");
//		System.out.println("4: Update Ticket (TODO)");

		
		System.out.print("\nEnter an integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
	}
	
//	public String displayPromptPid() {
//		System.out.println("Display")
//	}
	/**
	 * Display the prompt for a new Ticket Update
	 * @param a the ticket information to be saved into the database
	 */
	public String displayUpdate(Ticket t) {
		System.out.println("\nTicket Information to be updated");
		
		System.out.println("\nWhat would you like to be updated?");
		System.out.println("1: Update departure airport");
		System.out.println("2: Update arrival airport");
		System.out.println("3: Update departure time");
		System.out.println("4: Update arrival time");
		
		System.out.print("\nEnter an integer: ");
		String userInput = this.sc.next();
		return userInput;
		
		t.settID(Integer.parseInt(tID));
	}
	
	

}
