package airlinereservationsystem;

import java.sql.SQLException;
import java.util.Scanner;
import airlinereservationsystem.controller.HomeController;

public class AirlineSystem {
	
	public static void main(String args[]) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		HomeController hc = new HomeController(sc);
		hc.displayHomeMenu();
		
	}
}
