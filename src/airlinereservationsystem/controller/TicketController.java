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
			// Insert New Passenger to be blacklisted
			Blacklist blacklist = new Blacklist();
			this.bv.diplayInsert(blacklist);
			Boolean isInserted = this.bd.insertBlacklist(blacklist);
			this.bv.displayInsertSucess(isInserted, blacklist);
			this.blacklistMenu();
			break;
		}

		case 2: {
			// Get Blacklist by aID
			String aIDAsString = this.bv.displayAidPrompt();
			int aID = Integer.parseInt(aIDAsString);
			Airline a = this.bd.selectBlacklist(aID);
			if(a == null) {
				System.out.println("Airline does not have a blacklist");
				this.blacklistMenu();
			} else {
				PassengerView pv = new PassengerView(this.hc.getSc());
				pv.displayListOfPassengers("List of Blacklisted passenger for airline " + a.getName(), false, a.getBlacklistOfPassenger());
				this.blacklistMenu();
			}
			break;
		}

		default: {
			System.out.println("Invalid Navigation Integer\n");
			this.blacklistMenu();
			break;
		}
		}
	}
