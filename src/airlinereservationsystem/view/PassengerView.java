package airlinereservationsystem.view;

import java.util.Scanner;

import airlinereservationsystem.helper;
import airlinereservationsystem.model.Passenger;

public class PassengerView {
	private Scanner sc;
	
	public PassengerView(Scanner sc) {
		this.sc = sc;
	}

	public String display() {
		System.out.println("\nPASSENGER MENU\nFunctions of Passenger");
		System.out.println("0: Go to Home Menu");
		System.out.println("1: Insert Passenger");
		System.out.print("\nEnter integer: ");
		String navIntAsString = this.sc.next();
		return navIntAsString;
		
	}
	
	public void displayInsert(Passenger passenger){
		
		System.out.println("\nPassenger Information for Insertion");
		
		System.out.print("Passenger First Name: ");
		this.sc.nextLine();
		String firstName = this.sc.nextLine();
		while(firstName.equals("")) {
			System.out.println("\nFirst Name cannot be blank");
			System.out.print("Passenger First Name: ");
			firstName = this.sc.nextLine();
		}
		
		System.out.print("Passenger Last Name: ");
		String lastName = this.sc.nextLine();
		while(lastName.equals("")) {
			System.out.println("\nLast Name cannot be blank");
			System.out.print("Passenger Last Name: ");
			lastName = this.sc.nextLine();
		}
		
		System.out.print("Passenger Age: ");
		String ageString = this.sc.nextLine();
		while(ageString.equals("") && helper.isStringNumeric(ageString) == false) {
			System.out.println("\nAge cannot be blank");
			System.out.print("Passenger Age: ");
			ageString = this.sc.nextLine();
		}
		
		passenger.setFirstName(firstName);
		passenger.setLastName(lastName);
		passenger.setAge(Integer.parseInt(ageString));
	}
}
