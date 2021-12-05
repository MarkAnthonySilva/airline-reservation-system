package airlinereservationsystem.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import airlinereservationsystem.helper;
import airlinereservationsystem.model.Ticket;
import java.sql.Timestamp;

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
	
	public void diplayInsert(Ticket t) {
		System.out.println("\nTicket to be added");
		
		System.out.print("tID of Ticket: ");
		this.sc.nextLine();
		String tIDAsString = this.sc.nextLine();
		while(tIDAsString.equals("") || !helper.isStringNumeric(tIDAsString)) {
			System.out.println("\nTicket tID cannot be blank and must be an integer");
			System.out.print("tID of Ticket: ");
			tIDAsString = this.sc.nextLine();
		}
		
		System.out.print("\npID of Ticket: ");
		String pIDAsString = this.sc.nextLine();
		while(pIDAsString.equals("") || !helper.isStringNumeric(pIDAsString)) {
			System.out.println("\nTicket pID cannot be blank and must be an integer");
			System.out.print("pID of Ticket: ");
			pIDAsString = this.sc.nextLine();
		}	
		
		System.out.print("Departure airport: ");
		String fromAirport = this.sc.nextLine();
		while(fromAirport.equals("")) {
			System.out.println("\nDeparture airport cannot be blank");
			System.out.print("Departure airport: ");
			fromAirport = this.sc.nextLine();
		}	
		
		System.out.print("Destination airport: ");
		String destinationAirport = this.sc.nextLine();
		while(destinationAirport.equals("")) {
			System.out.println("\nDestination airport cannot be blank");
			System.out.print("Destination airport: ");
			destinationAirport = this.sc.nextLine();
		}	
		
		System.out.print("Departure Time in YYYY-MM-DD HH:MI:SS format: ");
		String departure = this.sc.nextLine();
		while(departure.equals("")) {
			System.out.println("\nDeparture time cannot be blank");
			System.out.print("Departure Time in YYYY-MM-DD HH:MI:SS format: ");
			departure = this.sc.nextLine();
		}	
		
		System.out.print("Arrival Time in YYYY-MM-DD HH:MI:SS format: ");
		String arrival = this.sc.nextLine();
		while(arrival.equals("")) {
			System.out.println("\nArrival time cannot be blank");
			System.out.print("Arrival Time in YYYY-MM-DD HH:MI:SS format: ");
			arrival = this.sc.nextLine();
		}	
		
		t.settID(Integer.parseInt(tIDAsString));
		t.setpID(Integer.parseInt(pIDAsString));
		t.setFromAirport(fromAirport);
		t.setDestinationAirport(destinationAirport);
		t.setDeparture(Timestamp.valueOf(departure));
		t.setArrival(Timestamp.valueOf(arrival));
	}
	
	
	/**
	 * A prompt to display which Ticket is to be searched for in the database
	 * @return the name to be searched for
	 */
	public String[] displayTicketSelect() {
		System.out.println("0: Go Back to Ticket Menu");
		System.out.print("Select Passenger Ticket tID: ");
		
		String[] tIDpID = new String[2];
		tIDpID[0] = this.sc.next();
		System.out.print("Select Passenger Ticket pID: ");
		tIDpID[1] = this.sc.next();
		
		return tIDpID;
	}
	
	public String[] displayDelete() {
		System.out.println("\nTicket to be Deleted");
		String[] tIDpID = new String[2];
		System.out.print("Ticket tID: ");
		this.sc.nextLine();
		String tIDAsString = this.sc.nextLine();
		while(tIDAsString.equals("") || !helper.isStringNumeric(tIDAsString)) {
			System.out.println("\nTicket tID cannot be blank and must be an integer");
			System.out.print("Ticket tID: ");
			tIDpID[0] = this.sc.nextLine();
		}
		
		System.out.print("Ticket pID: ");
		this.sc.nextLine();
		String pIDAsString = this.sc.nextLine();
		while(pIDAsString.equals("") || !helper.isStringNumeric(pIDAsString)) {
			System.out.println("\nTicket pID cannot be blank and must be an integer");
			System.out.print("Ticket pID: ");
			tIDpID[1] = this.sc.nextLine();
		}
		
		return tIDpID;
	}

	
	/**
	 * Display All Tickets in the airline table
	 * @param ticketMap the hashmap that contains all airlines
	 */
	public void displayListOfTickets(HashMap<Integer, Ticket> ticketMap) {
		System.out.println("\n" + "LIST OF ALL TICKETS");
		System.out.printf("%-3s %-32s\n", "tID", "pID");
		
		for(Map.Entry<Integer, Ticket> entry : ticketMap.entrySet())
		{
			Ticket t = entry.getValue();
			System.out.format("%-3d %-32s\n" , t.gettID(), t.getpID());
		}
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
