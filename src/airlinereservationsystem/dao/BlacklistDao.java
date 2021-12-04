package airlinereservationsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import airlinereservationsystem.JdbcConnector;
import airlinereservationsystem.model.Blacklist;

public class BlacklistDao {
	private final Connection CONNECTION = JdbcConnector.createNewConnection();
	private final String INSERT_AIRLINE = "INSERT INTO blacklist (aID, pID, reason) VALUES (?, ?, ?)";
	
	/**
	 * Insert a blacklist row into the blacklist table
	 * @param blacklist the blacklist row to be inserted
	 * @return true if insertion was successfully completed, otherwise false
	 */
	public boolean insertBlacklist(Blacklist blacklist) {
		try {
			PreparedStatement ps = this.CONNECTION.prepareStatement(this.INSERT_AIRLINE);
			ps.setInt(1, blacklist.getaID());
			ps.setInt(2, blacklist.getpID());
			ps.setString(3, blacklist.getReason());
			ps.executeUpdate();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
