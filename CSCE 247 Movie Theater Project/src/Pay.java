/**
 * The pay class that deals with purchasing a ticket to a certain event that the user has chosen
 * @author laddjackson
 *
 */

import java.io.*;
import java.security.AccessControlContext;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Pay {
	public String paymentInformation;

	/*
	* This method sets the payment information for the user
	* @param the paymentInformation is stored for the user and set
	*/
	public void setPaymentInformation(String paymentInformation) {
		this.paymentInformation = paymentInformation;
	}

	/*
	* This method gets the payment information that is stored for the certain user
	* @returns the payment information for that user
	*/
	public String getPaymentInformation() {
		return this.paymentInformation;
	}
	
	/**
	 * This method checks to see if user is an account holder, then calls on either UserCheckout() or guestCheckout()
	 * - Also gives the user the option to register for an account if they don't already have one
	 * @param An event
	 */
	public void checkout(JSONObject a) {
		Scanner key = new Scanner(System.in);
		System.out.println("Do you have an account? (Enter yes or no)");
		String accountResponse = key.nextLine();
		if(accountResponse.equalsIgnoreCase("yes")) {
			AccountHandler accountHandler = AccountHandler.getInstance();
			if(accountHandler.login() != false) {
				userCheckout(a, accountHandler);
			}
			
		} else if(accountResponse.equalsIgnoreCase("no")) {
			System.out.println("Would you like to register for an account?");
			accountResponse = key.nextLine();
			if(accountResponse.equalsIgnoreCase("yes")) {
				AccountHandler newUser = AccountHandler.getInstance();
				newUser.createNewAccount();
				System.out.println("New accout registered!");
				checkout(a);
			} else if (accountResponse.equalsIgnoreCase("no")) {
				guestCheckout(a);
			}
		}
	}
	
	/**
	 * 
	 * This method walks an account holding user through the checkout process
	 * @param An event, A User 
	 */
	public void userCheckout(JSONObject a, AccountHandler user) {
		Scanner key = new Scanner(System.in);
		String eventName = (String) a.get("Title");
	
		//Display showing information
		System.out.print(showingInformation(a));
		
		//Display matrix of available seating
		String seatChoice = seatSelection(a);
		
		//Retrieve User's paymentInformation
		String paymentInformation = checkForPaymentInformation(user);
				
		System.out.println("\n--Checkout Details--\nEvent: "+eventName+"\nShowing number: "+a.get("showing")+"\nSeat(s):"+seatChoice+"\nStatus: Purchased!\n--------------------\n");
		System.out.println("Would you like to print your ticket? (Enter yes or no)");
		String printTicket = key.nextLine();
		while(true) {
			if(printTicket.equalsIgnoreCase("yes")) {
				try {
					printTicket(a,paymentInformation);
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (printTicket.equalsIgnoreCase("no")) {
				break;
			} else {
				System.out.println("INVALID INPUT! ");
				printTicket = key.nextLine();
			}
		}
		
	}
	
	/*
	* This method walks a guest user through the checkout process
	* @param personal information is filled out and stored for the guest user
	*/
	public void guestCheckout(JSONObject a) {
		
		Scanner key = new Scanner(System.in);
		String eventName = (String) a.get("Title");

		//Display showing information
		System.out.print(showingInformation(a));
		
		//Display matrix of available seating
		String seatChoice = seatSelection(a);
		
		//Now since it is a guest transaction, I request payment info with a param of null
		String paymentInformation = checkForPaymentInformation(null);
		
		System.out.println("\n--Checkout Details--\nEvent: "+eventName+"\nShowing number: "+a.get("showing")+"\nSeat(s):"+seatChoice+"\nStatus: Purchased!\n--------------------\n");
		System.out.println("Would you like to print your ticket? (Enter yes or no)");
		String printTicket = key.nextLine();
		while(true) {
			if(printTicket.equalsIgnoreCase("yes")) {
				try {
					printTicket(a,paymentInformation);
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (printTicket.equalsIgnoreCase("no")) {
				System.out.println("***Transaction Complete***\nThank you!\n");
				break;
			} else {
				System.out.println("INVALID INPUT! ");
				printTicket = key.nextLine();
			}
		}
	}
	
	/*
	* This method deals with printing out the ticket if the user selects to print the ticket out
	* @param An event, string paymentInformation
	*/
	public void printTicket(JSONObject a, String paymentInformation) throws IOException {
		
		String event = (String) a.get("Title");
		JSONObject showingsInfo = (JSONObject) a.get("Showings");
		String showingString = "Theatre: "+showingsInfo.get("Theatre")+"\nDate: "+showingsInfo.get("Month")+" "+showingsInfo.get("Day")+"\nRuntime: "+showingsInfo.get("Hour")+":"+showingsInfo.get("Minute");
		System.out.println(showingString);
		paymentInformation = "**** "+paymentInformation.substring(paymentInformation.length()-4,paymentInformation.length());
		String ticketContent = ("-------TICKET-------\nEvent: "+event+"\nShowing Information: \n"+showingString+"\nSeat(s): NULL"+"\nPayment Information: "+paymentInformation+"\n--------------------\n");
		System.out.println(ticketContent);
		event = event.replace(" ", "_");
		System.out.println("EVENT RENAMED SAVED");
		FileWriter writer = new FileWriter(event+"_Ticket.txt");
		writer.write(ticketContent);
		writer.close();
		System.out.println("\nTICKET HAS BEEN SAVED!\nFile Name: "+event+"_Ticket.txt");
	}
	
	/**
	 * This method displays the showing information for the param event
	 * @param JSONObject Event
	 * @return a string of the showing information
	 */
	public String showingInformation(JSONObject a) {
		String eventName = (String) a.get("Title");
		JSONObject showingsInfo = (JSONObject) a.get("Showings");
		
		return "\n--Showing information--"+"\nEvent Name: "+eventName
				+"\nTheatre: "+showingsInfo.get("Theatre")+"\nDate: "+showingsInfo.get("Month")+" "+showingsInfo.get("Day")
				+"\nTime: "+showingsInfo.get("Hour")+":"+showingsInfo.get("Minute")+"\n";
	}
	
	/**
	 * This method displays the avaliable seating for the param event.
	 * @param JSONObject Event
	 * @return The users desired seats for the event
	 */
	public String seatSelection(JSONObject a) {
		Scanner key = new Scanner(System.in);
		
		System.out.println("\nAvailable seating for this showing: (0 = Empty seat, 1 = Reserved seat)");
		JSONArray seatMatrix = (JSONArray) a.get("Seating");
		for(int i = 0; i < seatMatrix.size(); ++i) {
			Object row = seatMatrix.get(i);
			System.out.println("ROW "+i+":"+row);
		}
		System.out.println("Enter seat selection (Example input: 3-1, where 3 is the row and 1 is the first seat from left to right): ");
		
		return key.nextLine();
	}
	
	/**
	 * This method attempts to retrieve payment information of a user or accept user input if a guest user
	 * @param user (if guest, user param will == null)
	 * @return a string of payment information
	 */
	public String checkForPaymentInformation(AccountHandler user) {
		Scanner key = new Scanner(System.in);
		String paymentInformation = null;

		if (user != null) {
			System.out.println("Retreving account payment information...");
			//Retrieve user's paymentInformation
			System.out.println("Payment information not found!\n");
			
			System.out.println("Please enter payment information: ");
			paymentInformation = key.nextLine();
			while(paymentInformation.isEmpty()) {
				System.out.println("INVALID PAYMENT INFORMATION! -- Please enter your payment information");
				paymentInformation = key.nextLine();
			}
		} else if(user == null){
			System.out.println("Please enter payment information: ");
			paymentInformation = key.nextLine();
			while(paymentInformation.isEmpty()) {
				System.out.println("INVALID PAYMENT INFORMATION! -- Please enter your payment information");
				paymentInformation = key.nextLine();
			}
		}
		
		return paymentInformation;
	}
}
