package airlinereservationsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Airline;
import airlinereservationsystem.model.Blacklist;
import airlinereservationsystem.model.Passenger;

public class BlacklistDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_AIRLINE = "INSERT INTO blacklist (aID, pID, reason) VALUES (?, ?, ?)";

	// Correlated Subquery
	private final String SELECT_BLACKLIST_FROM_AID = "SELECT * FROM passenger p, airline a WHERE a.aID = ? AND (aID, pID) IN (SELECT aID, pID FROM blacklist b WHERE b.pID  = p.pID AND b.aID = a.aID) ";
	
	// Group by Having Query
	private final String SELECT_BLACKLIST_NUM_PASS = "SELECT NAME, a.aid, COUNT(b.pid) AS numOfPassengerBlacklisted FROM blacklist b, airline a WHERE a.aid = b.aid GROUP BY b.aid HAVING COUNT(b.pid) >= ?";
	
	/**
	 * Insert a blacklist row into the blacklist table
	 * @param blacklist the blacklist row to be inserted
	 * @return true if insertion was successfully completed, otherwise false
	 */
	public boolean insertBlacklist(Blacklist blacklist) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.INSERT_AIRLINE);
			ps.setInt(1, blacklist.getaID());
			ps.setInt(2, blacklist.getpID());
			ps.setString(3, blacklist.getReason());
			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Get the blacklist of a certain airline
	 * @param aID the airline to be searched for the blacklist
	 * @return an airline object containing the blacklisted passengers and name of the airline
	 */
	public Airline selectBlacklist(int aID) {
		// Get Airline Information
		Airline a = new Airline();
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.SELECT_BLACKLIST_FROM_AID);
			ps.setInt(1,  aID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() == false) {
				return null;
			} else {
				String airlineName = rs.getString("name");
				a.setName(airlineName);
				a.setaID(aID);
				
				int rowCount = 1;
				HashMap<Integer, Passenger> blacklistOfPassenger = new HashMap<Integer, Passenger>();
				a.setBlacklistOfPassenger(blacklistOfPassenger);
				
				// Loop through all the result of the query
				do {
					Passenger p = new Passenger();
					int pID = rs.getInt("pID");
					int age = rs.getInt("age");
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					
					p.setpID(pID);
					p.setAge(age);
					p.setFirstName(firstName);
					p.setLastName(lastName);
					a.getBlacklistOfPassenger().put(rowCount, p);
					rowCount++;
				} while(rs.next());
			}
			
			return a;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * Select the blacklist with more than a certain number of Blacklisted Passengers
	 * @return
	 */
	public ArrayList<Airline> selectBlacklistNumber(int numOfBlacklisted) {
		ArrayList<Airline> listOfAirlines = new ArrayList<Airline>();
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.SELECT_BLACKLIST_NUM_PASS);
			ps.setInt(1, numOfBlacklisted);
			ResultSet rs = ps.executeQuery();

			if(rs.next() == false ) {
				return null;
			} else {
				do {
					Airline a = new Airline();
					String airlineName = rs.getString("name");
					int numOfPassengerBlacklisted = rs.getInt("numOfPassengerBlacklisted");
					int aID = rs.getInt("aID");
					
					a.setName(airlineName);
					a.setNumOfPassengerBlacklisted(numOfPassengerBlacklisted);
					a.setaID(aID);
					
					listOfAirlines.add(a);
				} while(rs.next());
				return listOfAirlines;
			}
		} catch (SQLException e) {
			return null;
		}
	}
}
