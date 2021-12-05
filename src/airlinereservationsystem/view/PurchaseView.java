package airlinereservationsystem.view;

import java.util.Scanner;

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
		System.out.println("1: Make a Ticket Purchase (Insert Purchase):");
		
		System.out.print("\nEnter integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
	}
	
//	public void displayInsert(Ticket t, Purchase p){
//		
//	}
}
