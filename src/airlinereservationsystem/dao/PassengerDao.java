package airlinereservationsystem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Passenger;

public class PassengerDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_PASSENGER = "INSERT INTO passenger (firstName, lastName, age) VALUES (?, ?, ?)";
	private final String SELECT_PASS_NAME = "SELECT * FROM passenger WHERE firstName = ?";
	private final String SELECT_PASS_PID = "SELECT * FROM passenger WHERE pID = ?";
	private final String DELETE_PASS_PID = "DELETE FROM passenger WHERE pID = ?";
	
	/**
	 * SQL Command Call to insert Passenger into the Passenger Table. Accepts the type Passenger
	 * @param passenger
	 */
	public void insertPassenger(Passenger passenger) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.INSERT_PASSENGER);
			ps.setString(1, passenger.getFirstName());
			ps.setString(2, passenger.getLastName());
			ps.setInt(3, passenger.getAge());
			ps.executeUpdate();
			System.out.println("Passenger Succesfully Added");
		} catch (Exception e) {
			System.out.println("Passenger Could not be added Added");
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a HashMap of all Passengers in the Passenger Table with a specific Name. 
	 * @param name the Name of Passenger to be Searched for
	 * @return HashMap of all Passengers with the name as param. Key = rowCount for display, value = Passenger object with passenger information
	 */
	public HashMap<Integer, Passenger> selectPassengerName(String name) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.SELECT_PASS_NAME);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			
			// ResultSet is empty return an empty Hashmap
			if(rs.next() == false) {
				return null;
			}
			else {
				// Loop thorough the whole Result Set and store results into a HashMap
				int rowCount = 1;
				HashMap<Integer , Passenger> resultSetHash = new HashMap<Integer, Passenger>();	//Key is row count, value is the Passenger
				// Loop Through all Passengers in the Result Set and store it into a Hashmap
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
					
					resultSetHash.put(rowCount, p);
					rowCount++;
				} while(rs.next());
				
				return resultSetHash;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Returns a Result Set of a Passenger with a specific PID. Should only return one passenger since PID is unique to each passenger
	 * @param pID the unique PID to be searched in the Passenger Table
	 * @return Result Set of a single Passenger with a specific PID in param
	 */
	public Passenger selectPassengerByPid(int pID) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.SELECT_PASS_PID);
			ps.setInt(1, pID);
			ResultSet rs = ps.executeQuery();
			// ResultSet is empty return an empty Hashmap
			if(rs.next() == false) {
				return null;
			}

			Passenger p = new Passenger();
			int age = rs.getInt("age");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");

			p.setpID(pID);
			p.setAge(age);
			p.setFirstName(firstName);
			p.setLastName(lastName);

			return p;

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Deletes a specific Passenger with a given pID
	 * @param pID the Passenger to be deleted
	 */
	public void deletePassengerByPid(int pID) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.DELETE_PASS_PID);
			ps.setInt(1, pID);
			ps.executeUpdate();
			System.out.println("Passenger (pID: " + pID + ") Sucessfully deleted");
		} catch (Exception e) {
			System.out.println("Passenger (pID: " + pID + ") Failed to delete");
			e.printStackTrace();
		}
	}
}
