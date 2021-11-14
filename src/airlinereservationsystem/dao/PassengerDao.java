package airlinereservationsystem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Passenger;

public class PassengerDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_PASSENGER = "INSERT INTO passenger (firstName, lastName, age) VALUES (?, ?, ?)";
	private final String SELECT_PASS_NAME = "SELECT * FROM passenger WHERE firstName = ?";
	private final String SELECT_PASS_PID = "SELECT * FROM passenger WHERE pID = ?";
	private final String DELETE_PASS_PID = "DELETE FROM passenger WHERE pID = ?";
	
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
	
	public ResultSet selectPassengerName(String name) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.SELECT_PASS_NAME);
			ps.setString(1, name);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ResultSet selectPassengerByPid(int pID) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.SELECT_PASS_PID);
			ps.setInt(1, pID);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

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
