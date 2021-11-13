package airlinereservationsystem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Passenger;

public class PassengerDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_PASSENGER = "INSERT INTO passenger (firstName, lastName, age) VALUES (?, ?, ?)";
	
	public void insertPassenger(Passenger passenger) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.INSERT_PASSENGER);
			ps.setString(1, passenger.getFirstName());
			ps.setString(2, passenger.getLastName());
			ps.setInt(3, passenger.getAge());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
