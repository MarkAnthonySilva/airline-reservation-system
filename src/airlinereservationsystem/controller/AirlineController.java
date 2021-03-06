package airlinereservationsystem.controller;

import java.sql.SQLException;
import java.util.HashMap;

import airlinereservationsystem.helper;
import airlinereservationsystem.dao.AirlineDao;
import airlinereservationsystem.model.Airline;
import airlinereservationsystem.view.AirlineView;

public class AirlineController {
	private HomeController hc;
	private AirlineView av;
	private AirlineDao ad;
	
	public AirlineController(HomeController hc){
		this.hc = hc;
		this.av = new AirlineView(this.hc.getSc());
		this.ad = new AirlineDao();
	}
	
	/**
	 * Controller for the Main Menu of the airline. Gets the input from Airline View to execute 
	 * a insert, read, or delete of an airline
	 * @throws SQLException
	 */
	public void airlineMainMenu() throws SQLException {
		String navIntAsString = this.av.display();

		int navInt = 0;
		if(helper.isStringNumeric(navIntAsString) == true){
			navInt = Integer.parseInt(navIntAsString);
		} else {
			System.out.println("Input Must be an integer");
			this.airlineMainMenu();
		}

		switch(navInt) {
		case 0: {
			// Navigate back to Home Menu
			this.hc.homeMenu();
			break;
		}
		
		case 1: {
			// Insert new Passenger
			Airline airline = new Airline();
			this.av.displayInsert(airline);
			//System.out.println(passenger);
			Boolean isInserted = this.ad.insertAirline(airline);
			this.av.displayInsertSuccess(isInserted, airline);
			this.airlineMainMenu();
			break;
		}
		
		case 2: {
			// Display all airlines
			this.airlineTable();
			this.airlineMainMenu();
			break;
		}
		
		case 3: {
			// Delete Airline Given an AID
			// Only Display Delete if there are actual airlines to delete
			if(this.airlineTable()) {
				String aIDAsString = this.av.displayDelete();
				int aID = Integer.parseInt(aIDAsString);
				Boolean isDeleted = this.ad.deletePassengerByAid(aID);
				this.av.displayDeleteSucess(isDeleted, aID);
			}
			
			this.airlineMainMenu();
			break;
		}
		
		default:
			System.out.println("Invalid Navigation Integer\n");
			this.airlineMainMenu();
			break;

		}
	}
	
	public boolean airlineTable() throws SQLException {
		HashMap<Integer, Airline> airlineMap = this.ad.selectAllAirlines();
		
		if(airlineMap == null) {
			System.out.println("No Airlines within the database");
			return false;
		}
		
		this.av.displayListOfAirlines(airlineMap);
		return true;
	}
}
