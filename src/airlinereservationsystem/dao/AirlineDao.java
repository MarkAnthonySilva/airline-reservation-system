package airlinereservationsystem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Airline;
import airlinereservationsystem.model.Passenger;

public class AirlineDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_AIRLINE = "INSERT INTO airline (name) VALUES (?)";
	private final String SELECT_ALL_AIRLINE = "SELECT * FROM airline";
	private final String DELETE_AIRLINE = "DELETE FROM airline WHERE aID = ?";
	
	// Correlated Subquery
	private final String SELECT_BLACKLIST_FROM_AID = "SELECT * FROM passenger p, airline a WHERE a.aID = ? AND (aID, pID) IN (SELECT aID, pID FROM blacklist b WHERE b.pID  = p.pID AND b.aID = a.aID) ";
	
	/**
	 * Insert an airline into the airline database
	 * @param a the airline to be inserted
	 * @return true if the airline was inserted sucessfully, otherwise false
	 */
	public boolean insertAirline(Airline a) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.INSERT_AIRLINE);
			ps.setString(1, a.getName());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Gets all the airlines in the airline table and returns the data as Hashmap
	 * @return HashMap of Airlines in the airline table
	 */
	public HashMap<Integer, Airline> selectAllAirlines(){
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.SELECT_ALL_AIRLINE);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() == false) {
				return null;
			} else {
				int rowCount = 1;
				HashMap<Integer, Airline> resultSetHash = new HashMap<Integer, Airline>();
				do {
					Airline a = new Airline();
					int aID = rs.getInt("aID");
					String name = rs.getString("name");
					
					a.setaID(aID);
					a.setName(name);
					
					resultSetHash.put(rowCount, a);
					rowCount++;
				} while(rs.next());
				
				return resultSetHash;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Delete An airline by a given aID
	 * @param aID the aID of the airline to be deleted
	 * @return true if deletion was completed, otherwise false
	 */
	public boolean deletePassengerByAid(int aID) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.DELETE_AIRLINE);
			ps.setInt(1, aID);
			ps.executeUpdate();
			return true;
		} catch (Exception e){
//			e.printStackTrace();
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
}
