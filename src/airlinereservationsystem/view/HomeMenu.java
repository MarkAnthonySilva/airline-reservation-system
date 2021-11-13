package airlinereservationsystem.view;

import java.util.Scanner;

public class HomeMenu {
	private Scanner sc;
	
	public HomeMenu(Scanner sc) {
		this.sc = sc;
	}
	public String displayHome() {
		System.out.println("\nHOME MENU\nNavigate to where");
		System.out.println("0: Close Airline System");
		System.out.println("1: Passenger");
		System.out.print("\nEnter integer: ");
		String navIntAsString = sc.next();
		return navIntAsString;
	}
}
