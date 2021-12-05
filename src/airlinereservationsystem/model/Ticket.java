package airlinereservationsystem.model;
import java.sql.Timestamp;

public class Ticket {
	
	private int tID;
	private int pID;
	private String fromAirport;
	private String destinationAirport;
	private Timestamp departure;
	private Timestamp arrival;
	private Timestamp update_at;
	
	public int gettID() {
		return tID;
	}
	
	public void settID(int tId) {
		this.tID = tId;
	}
	
	public int getpID() {
		return pID;
	}
	
	public void setpID(int pID) {
		this.pID = pID;
	}
	
	public String getFromAirport() {
		return fromAirport;
	}
	
	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}
	
	public String getDestinationAirport() {
		return destinationAirport;
	}
	
	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}
	
	public Timestamp getDeparture() {
		return departure;
	}
	
	public void setDeparture(Timestamp departure) {
		this.departure = departure;
	}
	
	public Timestamp getArrival() {
		return arrival;
	}
	
	public void setArrival(Timestamp arrival) {
		this.arrival = arrival;
	}
	
	public Timestamp getUpdate_at() {
		return update_at;
	}
	
	public void setUpdate_at(Timestamp update_at) {
		this.update_at = update_at;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrival == null) ? 0 : arrival.hashCode());
		result = prime * result + ((departure == null) ? 0 : departure.hashCode());
		result = prime * result + ((destinationAirport == null) ? 0 : destinationAirport.hashCode());
		result = prime * result + ((fromAirport == null) ? 0 : fromAirport.hashCode());
		result = prime * result + pID;
		result = prime * result + tID;
		result = prime * result + ((update_at == null) ? 0 : update_at.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (arrival == null) {
			if (other.arrival != null)
				return false;
		} else if (!arrival.equals(other.arrival))
			return false;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (destinationAirport == null) {
			if (other.destinationAirport != null)
				return false;
		} else if (!destinationAirport.equals(other.destinationAirport))
			return false;
		if (fromAirport == null) {
			if (other.fromAirport != null)
				return false;
		} else if (!fromAirport.equals(other.fromAirport))
			return false;
		if (pID != other.pID)
			return false;
		if (tID != other.tID)
			return false;
		if (update_at == null) {
			if (other.update_at != null)
				return false;
		} else if (!update_at.equals(other.update_at))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [tId=" + tID + ", pID=" + pID + ", fromAirport=" + fromAirport + ", destinationAirport="
				+ destinationAirport + ", departure=" + departure + ", arrival=" + arrival + ", update_at=" + update_at
				+ "]";
	}
}
