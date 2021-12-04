package airlinereservationsystem.dao;

import java.sql.Connection;

import airlinereservationsystem.JdbcConnector;

public class TicketDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	
}
