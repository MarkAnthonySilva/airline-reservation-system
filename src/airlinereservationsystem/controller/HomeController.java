package airlinereservationsystem.controller;

import java.sql.SQLException;
import java.util.Scanner;

import airlinereservationsystem.helper;
import airlinereservationsystem.view.HomeMenu;

public class HomeController {
	private int navInt;	// Refers to the navigation int displayed in the console
	private Scanner sc;
	
	public HomeController(Scanner sc){
		this.sc = sc;
	}
	
	/**
	 * Starts the airline reservation system by invoking the homeMenu view in the console
	 * @throws SQLException 
	 */
	public void homeMenu() throws SQLException {
		HomeMenu hm = new HomeMenu(this.sc);
		String navIntAsString = hm.displayHome();

		if(helper.isStringNumeric(navIntAsString) == true) {
			this.navInt = Integer.parseInt(navIntAsString);
		} else {
			System.out.println("Input Must be an integer");
			this.homeMenu();
		}

		switch(this.navInt) {
		case 0: {
			System.out.println("Airline System is closed");
			this.sc.close();
			break;
		}
		case 1: { 
			PassengerController pc = new PassengerController(this);
			pc.passengerMainMenu();
			break;
		}
		case 4: {
			AirlineController ac = new AirlineController(this);
			ac.airlineMainMenu();
			break;
		}
		case 5: {
			BlacklistController bc = new BlacklistController(this);
			bc.blacklistMenu();
			break;
		}
		default: {
			System.out.println("Invalid Navigation Integer");
			this.homeMenu();
			break;
		}
		}
	}

	public Scanner getSc() {
		return this.sc;
	}
}
