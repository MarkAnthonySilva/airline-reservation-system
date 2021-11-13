package airlinereservationsystem.controller;

import airlinereservationsystem.helper;
import airlinereservationsystem.model.Passenger;
import airlinereservationsystem.view.PassengerView;

public class PassengerController {
	private int navInt; 
	
	private Passenger passenger;
	private HomeController hc;
	
	public PassengerController(HomeController hc) {
		this.hc = hc;
	}
	
	public void displayPassengerMenu() {
		PassengerView pv = new PassengerView();
		String navIntAsString = pv.display();
		
		if(helper.isStringNumeric(navIntAsString) == true){
			this.navInt = Integer.parseInt(navIntAsString);
		} else {
			System.out.println("Input Must be an integer\n");
			this.displayPassengerMenu();
		}
		
		switch(this.navInt) {
		case 0: 
			this.hc.displayHomeMenu();
		case 1:
			
		default:
			System.out.println("Invalid Navigation Integer\n");
			this.displayPassengerMenu();

		}
	}
}
