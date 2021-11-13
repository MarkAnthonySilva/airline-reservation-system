package airlinereservationsystem;
import java.sql.DriverManager;
import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;

public class JdbcConnector {
	
	// JDBC connection used by the airline reservation system
	public static Connection createNewConnection() {
		try {
		// Get database information from properties file
		Properties prop = new Properties();
		String localDir = System.getProperty("user.dir");
//		System.out.println(localDir);
		FileReader fReader = new FileReader(localDir + "/src/airlinereservationsystem/db.properties/"); // If using in eclipse
//		FileReader fReader = new FileReader(localDir + "/airlinereservationsystem/db.properties/");	// For use in external console 
		prop.load(fReader);
		
		String dbUrl = prop.getProperty("db.conn.url");
		String dbUsername = prop.getProperty("db.username");
		String dbPassword = prop.getProperty("db.password");
		
		Connection connection = null; 
		connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		
		if (connection != null) {
			return connection;
		} else {
			System.out.println("Failed to make connection!");
			return null;
		}
		
		} catch (Exception e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}
	}
}
