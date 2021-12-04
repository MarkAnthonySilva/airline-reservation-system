package airlinereservationsystem.controller;

import java.sql.SQLException;
import java.util.HashMap;

import airlinereservationsystem.helper;
import airlinereservationsystem.dao.TicketDao;
import airlinereservationsystem.model.Airline;
import airlinereservationsystem.model.Blacklist;
import airlinereservationsystem.view.PassengerView;
import airlinereservationsystem.view.TicketView;

public class TicketController {
	private HomeController hc;
	private TicketView tv;
	private TicketDao td;


	public TicketController(HomeController hc) {
		this.hc = hc;
		this.tv = new TicketView(this.hc.getSc());
		this.td = new TicketDao();
	}

	public void ticketMainMenu() throws SQLException {
		String navIntAsString = this.tv.display();

		int navInt = 0;
		if(helper.isStringNumeric(navIntAsString)) {
			navInt = Integer.parseInt(navIntAsString);
		} else {
			System.out.println("Input Must be an integer");
			this.ticketMainMenu();
		}

		switch(navInt) {
		case 0: {
			// Navigate Back to Home Menu
			this.hc.homeMenu();
			break;
		}
		
		case 1: {
			// Select by pID
			break;
		}
		
//		case 2: {
//			// Select All Tickets
//			break;
//		}
//		
//		case 3: {
//			// Delete Tickets by tid
//			break;
//		}
//		
//		case 4: {
//			// Update Ticket
//			break;
//		}
		
		default: {
			System.out.println("Invalid Navigation Integer\n");
			this.ticketMainMenu();
			break;
		}
		}
	}
}
