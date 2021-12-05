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
		System.out.println("1: Select All Tickets");
		System.out.println("2: Delete Ticket by Ticket ID");
		System.out.println("3: Update Ticket");

		
		System.out.print("\nEnter an integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
	}
	
	/**
	 * Display the prompt for a new Ticket Insertion
	 * @param a the ticket information to be saved into the database
	 */
	public void displayUpdate(Ticket t) {
		System.out.println("\nTicket Information to be updated");
		
		System.out.println("\nWhat would you like to be updated?");
		System.out.println("1: Update departure airport");
		System.out.println("2: Update arrival airport");
		System.out.println("3: Update departure time");
		System.out.println("4: Update arrival time");
		
		String userInput = this.sc.nextLine();
		
		int input = Integer.parseInt(userInput);
		
		if (input == 1) {
			
		} else if (input == 2) {
			
		} else if (input == 3) {
			
		} else if (input == 4) {
			
			
		} else {
			System.out.println("Not a valid option");
			//break;
		}
		
		
		System.out.print("Ticket ID: ");
		this.sc.nextLine();
		String tID = this.sc.nextLine();
		while(tID.equals("")) {
			System.out.println("\nTicket ID cannot be blank");
			System.out.print("Ticket ID: ");
			tID = this.sc.next();
		}
	
		
		
		
		t.settID(Integer.parseInt(tID));
	}
	
	

}
