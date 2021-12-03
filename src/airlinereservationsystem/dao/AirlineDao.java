package airlinereservationsystem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Airline;

public class AirlineDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_AIRLINE = "INSERT INTO a (firstName, lastName, age) VALUES (?, ?, ?)";
	private final String SELECT_AIRLINE_NAME = "SELECT * FROM passenger WHERE firstName = ?";
	private final String SELECT_AIRLINE_AID = "SELECT * FROM passenger WHERE pID = ?";
	private final String DELETE_AIRLINE = "DELETE FROM passenger WHERE pID = ?";
	
	
}
