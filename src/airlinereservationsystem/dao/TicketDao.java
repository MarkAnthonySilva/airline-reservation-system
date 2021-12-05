package airlinereservationsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Ticket;

public class TicketDao {
	
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_TICKET = "INSERT INTO ticket (pID, aID, fromAirport, destinationAirport, departure, arrival) VALUES (?, ?, ?, ?, ?, ?)";
	private final String SELECT_TIK_TID_PID = "SELECT * FROM ticket WHERE tID = ? AND pID = ?";
	private final String SELECT_ALL_TICKETS = "SELECT * FROM ticket";
	private final String SELECT_TID = "SELECT tID FROM ticket WHERE pID = ? AND pID = ? AND fromAirport = ? AND destinationAirport = ? AND departure = ? AND arrival = ?";
//	private final String SELECT_TIK_PID = "SELECT * FROM ticket WHERE pID = ?";
//	private final String DELETE_TIK_PID = "DELETE FROM ticket WHERE pID = ?";
	private final String DELETE_TIK_TID_PID = "DELETE FROM ticket WHERE tID = ? AND pID = ?";
	
	// Aggregation
	private final String AVG_PRICE_OF_TICKER_FOR_PASSENGER = "SELECT ps.pID, firstName, lastName, AVG(price) AS AvgTicketPrice, COUNT(tID) AS numberOfTickets "
			+ "FROM passenger ps, purchase pr "
			+ "WHERE ps.pID = ? AND pr.PID = ps.PID";
	

	/**
	 * Insert a Ticket row into the Ticker table
	 * @param ticket the Ticket row to be inserted
	 * @return true if insertion was successfully completed, otherwise false
	 */
	public Boolean insertTicket(Ticket ticket) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.INSERT_TICKET);
			//ps.setInt(1, ticket.gettId()); 
			ps.setInt(1, ticket.getpID());
			ps.setInt(2, ticket.getaID());
			ps.setString(3, ticket.getFromAirport());
			ps.setString(4, ticket.getDestinationAirport());
			ps.setTimestamp(5, ticket.getDeparture());
			ps.setTimestamp(6, ticket.getArrival());
			ps.executeUpdate();
			
			// Get Ticket tID and store it into ticket
			ps = this.CONNECTION.prepareStatement(this.SELECT_TID);
			ps.setInt(1, ticket.getpID());
			ps.setInt(2, ticket.getaID());
			ps.setString(3, ticket.getFromAirport());
			ps.setString(4, ticket.getDestinationAirport());
			ps.setTimestamp(5, ticket.getDeparture());
			ps.setTimestamp(6, ticket.getArrival());
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			ticket.settID(rs.getInt("tID"));
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Returns a Result Set of a Ticket with a specific tID, pID. Should only 
	 * return one Ticket since tID and pID is unique to each Ticket
	 * @param tID the unique tID to be searched in the Ticket Table
	 * @param pID the unique pID to be searched in the Ticket Table
	 * @return Result Set of a single Ticket with a specific tID and pID in param
	 */
	public Ticket selectTicketByTidPid(int tID, int pID) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.SELECT_TIK_TID_PID);
			ps.setInt(1, tID);
			ps.setInt(2, pID);
			ResultSet rs = ps.executeQuery();
			// ResultSet is empty return an empty Hashmap
			if(rs.next() == false) {
				return null;
			}

			Ticket t = new Ticket();
			String fromAirport = rs.getString("fromAirport");
			String destinationAirport = rs.getString("destinationAirport");
			Timestamp departure = rs.getTimestamp("departure"); 
			Timestamp arrival = rs.getTimestamp("arrival"); 

			t.settID(tID);
			t.setpID(pID);
			t.setFromAirport(fromAirport);
			t.setDestinationAirport(destinationAirport);
			t.setDeparture(departure);
			t.setArrival(arrival);
			return t;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Deletes a specific Ticket with a given tID and pID
	 * @param tID and pID the Ticket to be deleted
	 */
	public void deleteTicketByPidTid(int tID, int pID) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.DELETE_TIK_TID_PID);
			ps.setInt(1, tID);
			ps.setInt(2, pID);
			ps.executeUpdate();
			System.out.println("Ticket (tID: " + tID + " + pID: " + pID + ") Sucessfully deleted");
		} catch (Exception e) {
			System.out.println("Ticket (tID: " + tID + " + pID: " + pID + ") Failed to delete");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Gets all the airlines in the airline table and returns the data as Hashmap
	 * @return HashMap of Airlines in the airline table
	 */
	public HashMap<Integer, Ticket> selectAllTickets(){
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.SELECT_ALL_TICKETS);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() == false) {
				return null;
			} else {
				int rowCount = 1;
				HashMap<Integer, Ticket> resultSetHash = new HashMap<Integer, Ticket>();
				do {
					Ticket t = new Ticket();
					int tID = rs.getInt("tID");
					int pID = rs.getInt("pid");
					
					t.settID(tID);
					t.setpID(pID);
					
					resultSetHash.put(rowCount, t);
					rowCount++;
				} while(rs.next());
				
				return resultSetHash;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getAvgPriceForPass(int pID) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.AVG_PRICE_OF_TICKER_FOR_PASSENGER);
			ps.setInt(1, pID);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			return null;
		}
	}
}
