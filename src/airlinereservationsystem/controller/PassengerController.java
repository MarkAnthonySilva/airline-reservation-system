package airlinereservationsystem.controller;

import java.sql.ResultSet;
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
		case 0: 
			// Navigate back to Home Menu
			this.hc.homeMenu();
			break;

		case 1:
			// Insert new Passenger
			Passenger passenger = new Passenger();
			this.pv.displayInsert(passenger);
			//System.out.println(passenger);
			this.pd.insertPassenger(passenger);
			this.passengerMainMenu();
			break;

		case 2: 
			// Select Passenger by name	
			this.passengerTable("PASSENGER LIST FOR SELECTION");
			break;

		case 3: 
			// Select Passenger by pid
			break; 
		case 4:
			// Delete Passengers by firstName
			this.passengerTable("PASSENGER LIST FOR DELETE");
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
	public void passengerTable(String menuTitle) throws SQLException {
		// Display input for passenger bane
		String firstName = this.pv.displayNameSelect();
		ResultSet rs = this.pd.selectPassengerName(firstName);

		// Display all passengers with the firstName being searched for
		HashMap<Integer , Integer> resultSetHash = new HashMap<Integer, Integer>();	//Key is row count, value is the pID
		String rowInputAsString = this.pv.displayListOfPassengers(rs, menuTitle, resultSetHash);

		// Check if rowInputAsString is a String and if it is converts that into an integer
		int rowInput = 0;
		if(helper.isStringNumeric(rowInputAsString) == true){
			rowInput = Integer.parseInt(rowInputAsString);
		} else {
			System.out.println("Input Must be an integer");
			rs = this.pd.selectPassengerName(firstName);
			pv.displayListOfPassengers(rs, menuTitle, resultSetHash);;
		}

		// If the row input does not exist within the table reinput
		if (rowInput == 0)	{
			this.passengerMainMenu();	// If row input is 0 navigate back to Passenger Menu
		} else if(!resultSetHash.containsKey(rowInput)) {
			System.out.println("Input is not a valid row");
			rs = this.pd.selectPassengerName(firstName);
			pv.displayListOfPassengers(rs, menuTitle, resultSetHash);;
		}
		//System.out.println("PID IS: " + resultSetHash.get(rowInput));

		// Table behavior dictated by query type
		if(menuTitle.equals("PASSENGER LIST FOR SELECTION"))
		{
			// Get The information of the Passenger that was inputted by the user at rowInput
			rs = this.pd.selectPassengerByPid(resultSetHash.get(rowInput));
			Passenger p = new Passenger();
			rs.next();
			p.setFirstName(rs.getString("firstName"));
			p.setLastName(rs.getString("lastName"));
			p.setpID(rs.getInt("pID"));
			p.setAge(rs.getInt("age"));
			this.passengerPIDMenu(p);
		} else if(menuTitle.equals("PASSENGER LIST FOR DELETE")) {
			this.pd.deletePassengerByPid(resultSetHash.get(rowInput));
			this.passengerMainMenu();
		}

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

		case 1:
			// Get Ticket information for Passenger
			break;

		case 2: 
			// Get Purchase Information
			break;

		case 3: 
			// Get if this passenger is blacklisted
			break; 
		default:
			System.out.println("Invalid Navigation Integer\n");
			this.passengerPIDMenu(p);
			break;

		}
	}
}
