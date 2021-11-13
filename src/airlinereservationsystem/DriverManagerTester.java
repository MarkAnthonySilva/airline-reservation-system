package airlinereservationsystem;
import java.sql.Connection;

import airlinereservationsystem.dao.PassengerDao;
import airlinereservationsystem.model.Passenger;

//import java.sql.DriverManager;
//import java.io.FileReader;
//import java.util.Properties;

public class DriverManagerTester
{

	public static void main(String[] argv) {
		final Connection connection = JdbcConnector.createNewConnection();
		System.out.println(connection.toString());
		
		Passenger passenger = new Passenger();
		passenger.setFirstName("Mark");
		passenger.setLastName("Silva");
		passenger.setAge(21);
		
		PassengerDao pd = new PassengerDao();
		pd.insertPassenger(passenger);
		
//		System.out.println("-------- MySQL JDBC Connection Testing ------------");
//		/* Since JDBC 4.0, loading is automatically done .
//		 *      try {
//		 *                      Class.forName("com.mysql.jdbc.Driver");
//		 *                              } catch (ClassNotFoundException e) {
//		 *                                              System.out.println("Where is your MySQL JDBC Driver?");
//		 *                                                              e.printStackTrace();
//		 *                                                                              return;
//		 *                                                                                      }
//		 *                                                                                       */
//		System.out.println("MySQL JDBC Driver Registered!");
//		Connection connection = null;
//
//		try {
//			Properties prop = new Properties();
//			String localDir = System.getProperty("user.dir");
//			System.out.println(localDir);
//			FileReader fReader = new FileReader(localDir + "/src/airlinereservationsystem/db.properties/");
//			prop.load(fReader);
//			
//			String dbUrl = prop.getProperty("db.conn.url");
//			String dbUsername = prop.getProperty("db.username");
//			String dbPassword = prop.getProperty("db.password");
//			
//			System.out.println("URL: " + dbUrl + " Username: " + dbUsername);
//			
//			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//
//
//		} catch (Exception e) {
//			System.out.println("Connection Failed! Check output console");
//			e.printStackTrace();
//			return;
//		}
//
//		if (connection != null) {
//			System.out.println("You made it, take control your database now!");
//		} else {
//			System.out.println("Failed to make connection!");
//		}
	}
}