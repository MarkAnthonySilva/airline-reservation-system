package airlinereservationsystem.controller;

import java.sql.SQLException;

import airlinereservationsystem.helper;
import airlinereservationsystem.dao.BlacklistDao;
import airlinereservationsystem.model.Airline;
import airlinereservationsystem.model.Blacklist;
import airlinereservationsystem.view.BlacklistView;
import airlinereservationsystem.view.PassengerView;

public class BlacklistController {
	private HomeController hc;
	private BlacklistView bv;
	private BlacklistDao bd;
	
	public BlacklistController(HomeController hc) {
		this.hc = hc;
		this.bv = new BlacklistView(this.hc.getSc());
		this.bd = new BlacklistDao();
	}
	
	public void blacklistMenu() throws SQLException {
		String navIntAsString = this.bv.display();
		
		int navInt = 0;
		if(helper.isStringNumeric(navIntAsString)) {
			navInt = Integer.parseInt(navIntAsString);
		} else {
			System.out.println("Input Must be an integer");
			this.blacklistMenu();
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
}
