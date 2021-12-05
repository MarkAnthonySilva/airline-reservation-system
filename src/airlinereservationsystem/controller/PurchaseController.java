package airlinereservationsystem.controller;

import java.sql.SQLException;

import airlinereservationsystem.helper;
import airlinereservationsystem.dao.PurchaseDao;
import airlinereservationsystem.dao.TicketDao;
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
			
			this.pv.displayInsertTicket(t);
			TicketDao td = new TicketDao();
			Boolean isTicketInserted = td.insertTicket(t);
			if(isTicketInserted) {
				System.out.println("Success on Inserting Ticket: " + t.toString());
				
				this.pv.displayInsertPurchase(p);
				p.settID(t.gettID());
				String message = this.pd.insertPurchase(p);
				System.out.println(message);
			} else {
				System.out.println("Failure on Inserting Ticket: " + t.toString());
			}
			
			
			this.purchaseMainMenu();
			break;
		}

		default: {
			System.out.println("Invalid Navigation Integer\n");
			this.purchaseMainMenu();
			break;
		}
		}
	}
}
