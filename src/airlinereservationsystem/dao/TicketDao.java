package airlinereservationsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Ticket;

public class TicketDao {
	
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_TICKET = "INSERT INTO ticket (pID, fromAirport, destinationAirport, departure, arrival) VALUES (?, ?, ?, ?, ?)";
	private final String SELECT_TIK_TID_PID = "SELECT * FROM ticket WHERE tID = ? AND pID = ?";
//	private final String SELECT_TIK_PID = "SELECT * FROM ticket WHERE pID = ?";
//	private final String DELETE_TIK_PID = "DELETE FROM ticket WHERE pID = ?";
	private final String DELETE_TIK_TID_PID = "DELETE FROM ticket WHERE tID = ? AND pID = ?";
	

	/**
	 * Insert a Ticket row into the Ticker table
	 * @param ticket the Ticket row to be inserted
	 * @return true if insertion was successfully completed, otherwise false
	 */
	public void insertTicket(Ticket ticket) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.INSERT_TICKET);
			//ps.setInt(1, ticket.gettId()); 
			ps.setInt(1, ticket.getpID());
			ps.setString(2, ticket.getFromAirport());
			ps.setString(3, ticket.getDestinationAirport());
			ps.setTimestamp(4, ticket.getDeparture());
			ps.setTimestamp(5, ticket.getArrival());
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Ticket Could not be found.");
			e.printStackTrace();
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
	 * @param pID the Passenger to be deleted
	 */
	public void deleteTicketByPidTid(int tID, int pID) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.DELETE_TIK_TID_PID);
			ps.setInt(1, tID);
			ps.setInt(2, pID);
			ps.executeUpdate();
			System.out.println("Ticket (tID: " + tID + " + pID: " + pID + ") Sucessfully deleted");
		} catch (Exception e) {
			System.out.println("Ticket (tID: " + tID + " + pID: " + pID + ") Sucessfully deleted");
			e.printStackTrace();
		}
	}
}
