package airlinereservationsystem;

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
}
