package airlinereservationsystem.controller;

import java.sql.SQLException;

import airlinereservationsystem.helper;
import airlinereservationsystem.dao.PurchaseDao;
import airlinereservationsystem.model.Purchase;
import airlinereservationsystem.model.Ticket;
import airlinereservationsystem.view.PurchaseView;

public class PurchaseController {
	private HomeController hc;
	private PurchaseView pv;
	private PurchaseDao pd;
	
	public PurchaseController(HomeController hc) {
		this.hc = hc;
		this.pv = new PurchaseView(this.hc.getSc());
		this.pd = new PurchaseDao();
	}
	
	public void purchaseMainMenu() throws SQLException {
		String navIntAsString = this.pv.display();

		int navInt = 0;
		if(helper.isStringNumeric(navIntAsString)) {
			navInt = Integer.parseInt(navIntAsString);
		} else {
			System.out.println("Input Must be an integer");
			this.purchaseMainMenu();
		}

		switch(navInt) {
		case 0: {
			// Navigate Back to Home Menu
			this.hc.homeMenu();
			break;
		}

		case 1: {
			// New Ticket Purchase to be inserted
			Ticket t = new Ticket();
			Purchase p = new Purchase();
		}

		default: {
			System.out.println("Invalid Navigation Integer\n");
			this.purchaseMainMenu();
			break;
		}
		}
	}
}
