package airlinereservationsystem.view;

import java.util.Scanner;

public class HomeMenu {
	private Scanner sc;
	
	public HomeMenu(Scanner sc) {
		this.sc = sc;
	}
	
	/**
	 * Home menu for Airline Reservation System. Can Access all the different information for the application.
	 * @return
	 */
	public String displayHome() {
		System.out.println("\nHOME MENU\nNavigate to where");
		System.out.println("0: Close Airline System");
		System.out.println("1: Passenger");
		System.out.println("2: Ticket");
		System.out.println("3: Purchase");
		System.out.println("4: Airline");
		System.out.println("5: Blacklist");
		System.out.println("6: Airport");
		System.out.print("\nEnter integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
	}
}
