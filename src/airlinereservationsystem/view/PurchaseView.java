package airlinereservationsystem.view;

import java.util.Scanner;

import airlinereservationsystem.helper;
import airlinereservationsystem.model.Purchase;
import airlinereservationsystem.model.Ticket;

public class PurchaseView {
	private Scanner sc;
	
	public PurchaseView(Scanner sc){
		this.sc = sc;
	}
	
	/**
	 * Display the menu for the functions for Purchase
	 * @return
	 */
	public String display() {
		System.out.println("\nBLACKLIST MENU\nFunctions of Blacklist");
		System.out.println("0: Go to Home Menu");
		System.out.println("1: Make a Ticket Purchase (Insert Purchase)");
		
		System.out.print("\nEnter integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
	}
	
	public void displayInsertTicket(Ticket t){
		System.out.println("\nPurchase a new ticket");
		
		System.out.print("Passenger pID(Rider): ");
		this.sc.nextLine();
		String pIDAsString = this.sc.nextLine();
		while(pIDAsString.equals("") || !helper.isStringNumeric(pIDAsString)) {
			System.out.println("\nPassenger pID cannot be blank and must be an integer");
			System.out.print("Passenger pID(Rider): ");
			pIDAsString = this.sc.nextLine();
		}
		
		System.out.print("Airline on Ticket (aID): ");
		String aIDAsString = this.sc.nextLine();
		while(aIDAsString.equals("") || !helper.isStringNumeric(aIDAsString)) {
			System.out.println("\nAirline aID cannot be blank and must be an integer");
			System.out.print("Airline on Ticket (aID): ");
			aIDAsString = this.sc.nextLine();
		}
		
		System.out.print("Depature Airport (IDENT): ");
		String fromIdent = this.sc.nextLine();
		while(fromIdent.length() != 4 ) {
			System.out.println("\nDeparture Airport IDENT must be exactly 4 characters");
			System.out.print("Depature Airport (IDENT): ");
			fromIdent = this.sc.nextLine();
		}
		
		System.out.print("Destination Airport (IDENT): ");
		String desIdent = this.sc.nextLine();
		while(desIdent.length() != 4 ) {
			System.out.println("\nDeparture Airport IDENT must be exactly 4 characters");
			System.out.print("Destination Airport (IDENT): ");
			desIdent = this.sc.nextLine();
		}
		
		System.out.print("Depature Timestamp (yyyy-mm-dd hh:mm:ss): ");
		String fromTimeStampAsString = this.sc.nextLine();
		while(fromTimeStampAsString.equals("") || helper.convertStringToTimestamp(fromTimeStampAsString) == null) {
			System.out.println("\nTimestamp cannot be blank and must be form yyyy-mm-dd hh:mm:ss");
			System.out.print("Depature Timestamp (yyyy-mm-dd hh:mm:ss): ");
			fromTimeStampAsString = this.sc.nextLine();
		}
		
		System.out.print("Arrival Timestamp (yyyy-mm-dd hh:mm:ss): ");
		String arrivalTimeStampAsString = this.sc.nextLine();
		while(arrivalTimeStampAsString.equals("") || helper.convertStringToTimestamp(arrivalTimeStampAsString) == null) {
			System.out.println("\nTimestamp cannot be blank and must be form yyyy-mm-dd hh:mm:ss");
			System.out.print("Arrival Timestamp (yyyy-mm-dd hh:mm:ss): ");
			arrivalTimeStampAsString = this.sc.nextLine();
		}
		
		t.setpID(Integer.parseInt(pIDAsString));
		t.setaID(Integer.parseInt(aIDAsString));
		t.setFromAirport(fromIdent);
		t.setDestinationAirport(desIdent);
		t.setDeparture(helper.convertStringToTimestamp(fromTimeStampAsString));
		t.setArrival(helper.convertStringToTimestamp(arrivalTimeStampAsString));
	}

	public void displayInsertPurchase(Purchase p) {
		System.out.println("\nPurchase Information");

		System.out.print("Passenger pID(Purchaser): ");
		String pIDAsString = this.sc.nextLine();
		while(pIDAsString.equals("") || !helper.isStringNumeric(pIDAsString)) {
			System.out.println("\nPassenger pID cannot be blank and must be an integer");
			System.out.print("Passenger pID(Purchaser): ");
			pIDAsString = this.sc.nextLine();
		}
		
		System.out.print("Ticket Price: ");
		String priceAsString = this.sc.nextLine();
		while(priceAsString.equals("") || !helper.isStringNumeric(priceAsString)) {
			System.out.println("\nPrice cannot be blank and must be an integer");
			System.out.print("Ticket Price: ");
			priceAsString = this.sc.nextLine();
		}
		
		p.setpId(Integer.parseInt(pIDAsString));
		p.setPrice(Integer.parseInt(priceAsString));
	}
}
