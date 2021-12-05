package airlinereservationsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Purchase;
import airlinereservationsystem.model.Ticket;

public class PurchaseDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_PURCHASE = "INSERT INTO purchase (tID, pID, price) VALUES (?, ?, ?)";
	
	/**
	 * A Purchase of a ticket was executed
	 * @param p the information of the purchase to be inserted into the purchase table
	 * @return
	 */
	public String insertPurchase(Purchase p) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.INSERT_PURCHASE);
			ps.setInt(1, p.gettID());
			ps.setInt(2, p.getpId());
			ps.setInt(3, p.getPrice());
			
			ps.executeUpdate();
			return "Purchase was Successfully completed";
		} catch(SQLException e) {
			return e.getMessage() + e.getErrorCode();
		}
	}
}
