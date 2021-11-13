package airlinereservationsystem.controller;

import airlinereservationsystem.helper;
import airlinereservationsystem.dao.PassengerDao;
import airlinereservationsystem.model.Passenger;
import airlinereservationsystem.view.PassengerView;

public class PassengerController {
	private int navInt; 
	
	private HomeController hc;
	
	public PassengerController(HomeController hc) {
		this.hc = hc;
	}
	
	public void displayPassengerMenu() {
		PassengerView pv = new PassengerView(this.hc.getSc());
		String navIntAsString = pv.display();
		
		if(helper.isStringNumeric(navIntAsString) == true){
			this.navInt = Integer.parseInt(navIntAsString);
		} else {
			System.out.println("Input Must be an integer");
			this.displayPassengerMenu();
		}
		
		switch(this.navInt) {
		case 0: 
			// Navigate back to Home Menu
			this.hc.displayHomeMenu();
			break;
		case 1:
			// Insert new Passenger
			Passenger passenger = new Passenger();
			pv.displayInsert(passenger);
//			System.out.println(passenger);
			PassengerDao pd = new PassengerDao();
			pd.insertPassenger(passenger);
			this.displayPassengerMenu();
			break;
			
		default:
			System.out.println("Invalid Navigation Integer\n");
			this.displayPassengerMenu();
			break;

		}
	}
}
