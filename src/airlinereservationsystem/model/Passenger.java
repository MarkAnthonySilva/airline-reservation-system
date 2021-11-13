package airlinereservationsystem.model;

import java.util.Objects;

public class Passenger {
	private int pID;
	private int age;
	private String firstName;
	private String lastName;
	
	// Getters and setters for Passenger attributes
	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(pID);
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
		Passenger other = (Passenger) obj;
		if (pID != other.pID)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Passenger [pID=" + pID + ", age=" + age + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
