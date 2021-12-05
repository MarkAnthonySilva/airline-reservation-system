package airlinereservationsystem.controller;

import java.sql.SQLException;
import java.util.HashMap;
import airlinereservationsystem.helper;
import airlinereservationsystem.dao.TicketDao;
import airlinereservationsystem.model.Airline;
import airlinereservationsystem.model.Blacklist;
import airlinereservationsystem.model.Ticket;
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
			// Insert New Ticket
			Ticket ticket = new Ticket();
			this.tv.diplayInsert(ticket);
			Boolean isInserted = this.td.insertBlacklist(ticket);
			this.tv.displayInsertSucess(isInserted, ticket);
			this.ticketMainMenu();
			break;
		}

		case 2: {
			// Get Blacklist by aID
			String aIDAsString = this.bv.displayAidPrompt();
			int aID = Integer.parseInt(aIDAsString);
			Airline a = this.bd.selectBlacklist(aID);
			if(a == null) {
				System.out.println("Airline does not have a blacklist");
				this.ticketMainMenu();
			} else {
				PassengerView pv = new PassengerView(this.hc.getSc());
				pv.displayListOfPassengers("List of Blacklisted passenger for airline " + a.getName(), false, a.getBlacklistOfPassenger());
				this.ticketMainMenu();
			}
			break;
		}

		default: {
			System.out.println("Invalid Navigation Integer\n");
			this.ticketMainMenu();
			break;
		}
	}
	}
}
