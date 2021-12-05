package airlinereservationsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Purchase;
import airlinereservationsystem.model.Ticket;

public class PurchaseDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_PURCHASE = "INSERT INTO purchase VALUES(tID, pID, price) VALUES (?, ?, ?)";
	
	/**
	 * 
	 * @param p
	 * @param t
	 * @return
	 */
	private String insertPurchase(Purchase p) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.INSERT_PURCHASE);
			return "Purchase was Successfully completed";
		} catch(SQLException e) {
			return e.getMessage();
		}
	}
}
