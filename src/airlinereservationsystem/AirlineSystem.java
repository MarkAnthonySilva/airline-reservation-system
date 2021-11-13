package airlinereservationsystem;

import airlinereservationsystem.controller.HomeController;

public class AirlineSystem {
	
	public static void main(String args[])
	{
		HomeController hc = new HomeController();
		hc.displayHomeMenu();
		
	}
}
