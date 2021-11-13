package airlinereservationsystem.controller;

import airlinereservationsystem.helper;
import airlinereservationsystem.view.HomeMenu;

public class HomeController {
	private int navInt;	// Refers to the navigation int displayed in the console
	
	/**
	 * Starts the airline reservation system by invoking the homeMenu view in the console
	 */
	public void displayHomeMenu() {
		HomeMenu hm = new HomeMenu();
		String navIntAsString = hm.displayHome();
		
		if(helper.isStringNumeric(navIntAsString) == true){
			this.navInt = Integer.parseInt(navIntAsString);
		} else {
			System.out.println("Input Must be an integer");
			this.displayHomeMenu();
		}
		
		switch(this.navInt) {
		case 1: 
			PassengerController pc = new PassengerController(this);
			pc.displayPassengerMenu();
			break;
		default:
			System.out.println("Invalid Navigation Integer");
			this.displayHomeMenu();
			break;
		}
	}
}
