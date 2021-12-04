package airlinereservationsystem;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class helper {
	
	public static boolean isStringNumeric(String s)
	{
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static Timestamp convertStringToTimestamp(String timestampAsString) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parsedDate = dateFormat.parse(timestampAsString);
			Timestamp timestamp = new Timestamp(parsedDate.getTime());
			
			return timestamp;
		} catch (Exception e) {
			return null;
		}
	}
}
