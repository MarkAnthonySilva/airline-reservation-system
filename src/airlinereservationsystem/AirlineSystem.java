package airlinereservationsystem;

import java.util.Scanner;
import airlinereservationsystem.controller.HomeController;

public class AirlineSystem {
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		HomeController hc = new HomeController(sc);
		hc.displayHomeMenu();
		
	}
}
