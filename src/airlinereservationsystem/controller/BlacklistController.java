package airlinereservationsystem.controller;

import java.sql.SQLException;

import airlinereservationsystem.helper;
import airlinereservationsystem.dao.BlacklistDao;
import airlinereservationsystem.model.Blacklist;
import airlinereservationsystem.view.BlacklistView;

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
		case 0:{
			// Navigate Back to Home Menu
			this.hc.homeMenu();
			break;
		}
		
		case 1: {
			// Insert New Passenger to be blacklisted
			Blacklist blacklist = new Blacklist();
			
		}
		
		default: {
			System.out.println("Invalid Navigation Integer\n");
			this.blacklistMenu();
			break;
		}
		}
	}
}
