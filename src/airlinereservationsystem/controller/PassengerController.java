package airlinereservationsystem.controller;

import java.sql.SQLException;
import java.util.HashMap;

import airlinereservationsystem.helper;
import airlinereservationsystem.dao.PassengerDao;
import airlinereservationsystem.model.Passenger;
import airlinereservationsystem.view.PassengerView;

public class PassengerController {
	private HomeController hc;
	private PassengerView pv;
	private PassengerDao pd;

	public PassengerController(HomeController hc) {
		this.hc = hc;
		this.pv = new PassengerView(this.hc.getSc());
		this.pd = new PassengerDao();
	}

	/**
	 * Main menu for Passenger that displays all possible function with Passenger
	 * @throws SQLException
	 */
	public void passengerMainMenu() throws SQLException {
		String navIntAsString = this.pv.display();
		
		int navInt = 0;
		if(helper.isStringNumeric(navIntAsString) == true){
			navInt = Integer.parseInt(navIntAsString);
		} else {
			System.out.println("Input Must be an integer");
			this.passengerMainMenu();
		}

		switch(navInt) {
		case 0: {
			// Navigate back to Home Menu
			this.hc.homeMenu();
			break;
		}
		
		case 1:{
			// Insert new Passenger
			Passenger passenger = new Passenger();
			this.pv.displayInsert(passenger);
			//System.out.println(passenger);
			this.pd.insertPassenger(passenger);
			this.passengerMainMenu();
			break;
		}

		case 2: {
			// Select Passenger by name
			String firstName = this.pv.displayNameSelect();
			this.passengerTable("PASSENGER LIST FOR SELECTION", firstName);
			break;
		}
		case 3: {
			// Select Passenger by pid
			String pIDAsString = this.pv.displaypIDSelect();
			int pID = 0;
			// If output was 0 go back to Passenger Main Menu or not a number
			if(pIDAsString.equals("0")) {
				this.passengerMainMenu();
			} else if (!helper.isStringNumeric(pIDAsString)) {
				System.out.println("Input Must be an Integer");
				this.passengerMainMenu();
			} else {
				pID = Integer.parseInt(pIDAsString);
			}
			
			Passenger p = this.pd.selectPassengerByPid(pID);
			this.passengerPIDMenu(p);
			break;
		}
		case 4:{
			// Delete Passengers by firstName
			String firstName = this.pv.displayNameSelect();
			if(firstName.equals("0")) {
				this.passengerMainMenu();
			}
			
			this.passengerTable("PASSENGER LIST FOR DELETE", firstName);
			break;
		}

		default:
			System.out.println("Invalid Navigation Integer\n");
			this.passengerMainMenu();
			break;

		}
	}

	/**
	 * Displays a table of all passenger meeting a certain criteria specified by an input (Usually the firstname of the Passenger)
	 * Then accepts an input of a row of passenger information and will either get more detailed information about that passenger
	 * or delete it depending on the params. 
	 * 0 for input is reserved for navigating back to the Passenger Menu
	 * @throws SQLException
	 */
	public void passengerTable(String menuTitle, String firstName) throws SQLException {
		
		if(firstName.equals("0")) {
			this.passengerMainMenu();
		}
		
		// Display input for passenger bane
		HashMap<Integer, Passenger> passengerMap = this.pd.selectPassengerName(firstName);	// Hash Map with All Pasengers with a specifc Name

		// If hm is return as null, Passenger with specified firstName was not found
		if(passengerMap == null) {
			System.out.println("Passenger Not Found");
			firstName = this.pv.displayNameSelect();
			this.passengerTable(menuTitle, firstName);
		}

		// Display all passengers with the firstName being searched for
		this.pv.displayListOfPassengers(menuTitle, true, passengerMap);
		String rowInputAsString = this.pv.displayPassengerPrompt();
		
		// Check if rowInputAsString is a numeric then convert that into rowInput
		int passengerRow = 0;	// Row of the Passenger in table
		if(helper.isStringNumeric(rowInputAsString) == true){
			passengerRow = Integer.parseInt(rowInputAsString);

			// Input is not numeric, so Table must be redisplayed
		} else {
			System.out.println("Input Must be an integer");
			this.passengerTable(menuTitle, firstName);
			return;
		}

		// Check if row Input is a valid Input
		if (passengerRow == 0)	{
			this.passengerMainMenu();	// If row input is 0 navigate back to Passenger Menu
			return;
		} else if(!passengerMap.containsKey(passengerRow)) {
			System.out.println("Input is not a valid row");
			this.passengerTable(menuTitle, firstName);
			return;
		}
		//System.out.println("PID IS: " + resultSetHash.get(rowInput));

		// Table behavior dictated by query type
		if(menuTitle.equals("PASSENGER LIST FOR SELECTION"))
		{
			this.passengerPIDMenu(passengerMap.get(passengerRow));
			return;
		} else if(menuTitle.equals("PASSENGER LIST FOR DELETE")) {
			this.pd.deletePassengerByPid(passengerMap.get(passengerRow).getpID());
			this.passengerMainMenu();
			return;
		}
		 return;
	}
	
	/**
	 * Controls which function to execute while in the individual Passenger view that contains more detailed information about a Passenger
	 * @param p the Passenger that is to be displayed in more detail
	 * @throws SQLException
	 */
	public void passengerPIDMenu(Passenger p) throws SQLException{
		String navIntAsString = this.pv.displayPassenger(p);
		
		int navInt = 0;
		if(helper.isStringNumeric(navIntAsString) == true){
			navInt = Integer.parseInt(navIntAsString);
		} else {
			System.out.println("Input Must be an integer");
			this.pv.displayPassenger(p);
		}
		
		switch(navInt) {
		case 0: 
			// Navigate back to Passenger Menu
			this.passengerMainMenu();
			break;

//		case 1:
//			// Get Ticket information for Passenger
//			break;
//
//		case 2: 
//			// Get Purchase Information
//			break;
//
//		case 3: 
//			// Get if this passenger is blacklisted
//			break; 
		default:
			System.out.println("Invalid Navigation Integer\n");
			this.passengerPIDMenu(p);
			break;

		}
	}
}
