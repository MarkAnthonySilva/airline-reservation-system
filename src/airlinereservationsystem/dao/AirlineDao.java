package airlinereservationsystem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Airline;

public class AirlineDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_AIRLINE = "INSERT INTO airline (name) VALUES (?)";
//	private final String SELECT_AIRLINE_NAME = "SELECT * FROM passenger WHERE firstName = ?";
//	private final String SELECT_AIRLINE_AID = "SELECT * FROM passenger WHERE pID = ?";
//	private final String DELETE_AIRLINE = "DELETE FROM passenger WHERE pID = ?";
	
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
}
