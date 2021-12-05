package airlinereservationsystem.controller;

import java.sql.SQLException;
import java.util.HashMap;
import airlinereservationsystem.helper;
import airlinereservationsystem.dao.TicketDao;
import airlinereservationsystem.model.Airline;
import airlinereservationsystem.model.Blacklist;
import airlinereservationsystem.model.Passenger;
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
			this.td.insertTicket(ticket);
			this.ticketMainMenu();
			break;
		}

		case 2: {
			// Get Ticket by tID and pID
			int tID = 0, pID = 0;
			String[] tIDpIDAsString = this.tv.displayTicketSelect();
			// If output was 0 go back to Ticket Main Menu or not a number
			if(tIDpIDAsString[0].equals("0") || tIDpIDAsString[1].equals("0")) {
				this.ticketMainMenu();
			} else if (!helper.isStringNumeric(tIDpIDAsString[0]) || !helper.isStringNumeric(tIDpIDAsString[1])) {
				System.out.println("Input Must be an Integer");
				this.ticketMainMenu();
			} else {
				tID = Integer.parseInt(tIDpIDAsString[0]);
				pID = Integer.parseInt(tIDpIDAsString[1]);
			}

			Ticket t = this.td.selectTicketByTidPid(tID, pID);
			this.ticketMainMenu();
			break;
		}

		case 3: {

			// Delete Ticket Given a tID and pID
			// Only Display Delete if there are actual Tickets to delete
			if(this.ticketTable()) {
				String[] tIDpIDAsString = this.tv.displayDelete();
				int tID = Integer.parseInt(tIDpIDAsString[0]);
				int pID = Integer.parseInt(tIDpIDAsString[1]);
				this.td.deleteTicketByPidTid(tID, pID);
			}

			this.ticketMainMenu();
			break;

		}

		default: {
			System.out.println("Invalid Navigation Integer\n");
			this.ticketMainMenu();
			break;
		}
		}
	}
	
	public boolean ticketTable() throws SQLException {
		HashMap<Integer, Ticket> ticketMap = this.td.selectAllTickets();
		
		if(ticketMap == null) {
			System.out.println("No Tickets within the database");
			return false;
		}
		
		this.tv.displayListOfTickets(ticketMap);
		return true;
	}
}
