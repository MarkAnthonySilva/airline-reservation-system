package airlinereservationsystem.model;

import java.util.HashMap;

public class Airline {
	private int aID;
	private int numOfPassengerBlacklisted;
	private String name;
	private HashMap<Integer, Passenger> blacklistOfPassenger; 
	
	public int getaID() {
		return aID;
	}
	public void setaID(int aID) {
		this.aID = aID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<Integer, Passenger> getBlacklistOfPassenger() {
		return blacklistOfPassenger;
	}
	public void setBlacklistOfPassenger(HashMap<Integer, Passenger> blacklistOfPassenger) {
		this.blacklistOfPassenger = blacklistOfPassenger;
	}
	public int getNumOfPassengerBlacklisted() {
		return numOfPassengerBlacklisted;
	}
	public void setNumOfPassengerBlacklisted(int numOfPassengerBlacklisted) {
		this.numOfPassengerBlacklisted = numOfPassengerBlacklisted;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Airline other = (Airline) obj;
		if (aID != other.aID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Airline [aID=" + aID + ", name=" + name + "]";
	}
	
	
}
