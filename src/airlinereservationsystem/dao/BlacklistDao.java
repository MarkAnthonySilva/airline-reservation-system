package airlinereservationsystem.dao;

import java.sql.Connection;

import airlinereservationsystem.JdbcConnector;

public class BlacklistDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	
}
