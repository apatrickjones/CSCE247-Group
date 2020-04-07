/**
 * The pay class that deals with purchasing a ticket to a certain event that the user has chosen
 * @author laddjackson
 *
 */

import java.io.*;
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
	
	public void checkout(JSONObject a) {
		Scanner key = new Scanner(System.in);
		System.out.println("Do you have an account? (Enter yes or no)");
		String accountResponse = key.nextLine();
		if(accountResponse.equalsIgnoreCase("yes")) {
			
		} else if(accountResponse.equalsIgnoreCase("no")) {
			guestCheckout(a);
		}
	}
	
	
	public void userCheckout() {
		
	}
	/*
	* This method deals with a guest without an actual account
	* @param personal information is filled out and stored for the guest user
	*/
	//I've only created guestCheckout so far, once user classes are functional I will implement AccountHolderCheckout()
	public void guestCheckout(JSONObject a) {
		
		Scanner key = new Scanner(System.in);
		System.out.println(a.get("Title"));
		String eventName = (String) a.get("Title");
		System.out.println("\n--Showing information--");
		
		System.out.println("Event Name: "+eventName);
		JSONObject showingsInfo = (JSONObject) a.get("Showings");
		System.out.println("Theatre: "+showingsInfo.get("Theatre"));
		System.out.println("Date: "+showingsInfo.get("Month")+" "+showingsInfo.get("Day"));
		System.out.println("Time: "+showingsInfo.get("Hour")+":"+showingsInfo.get("Minute"));
		System.out.println("\nAvalibale seating for this showing: (0 = Empty seat, 1 = Reserved seat)");
		
		//Display matrix of available seating
		JSONArray seatMatrix = (JSONArray) a.get("Seating");
		for(int i = 0; i < seatMatrix.size(); ++i) {
			Object row = seatMatrix.get(i);
			System.out.println("ROW "+i+":"+row);
		}
		System.out.println("Enter seat selection: ");
		String seatChoice = key.nextLine();
		

		//Now since it is a guest transaction, I request payment info
		System.out.println("Please enter payment information: ");
		String paymentInformation = key.nextLine();
		while(paymentInformation.isEmpty()) {
			System.out.println("INVALID PAYMENT INFORMATION! -- Please enter your payment information");
			paymentInformation = key.nextLine();
		}
		
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
	* This method deals with priting out the ticket if the user selects to print the ticket out
	* @param event, showingChoice, seatChoice, paymentInformation will all be printed out when the user chooses to
	*/
	//Functional, but will be polished before final version
	public void printTicket(JSONObject a, String paymentInformation) throws IOException {
		
		String event = (String) a.get("Title");
		JSONObject showingsInfo = (JSONObject) a.get("Showings");
		String showingString = "Theatre: "+showingsInfo.get("Theatre")+"\nDate: "+showingsInfo.get("Month")+" "+showingsInfo.get("Day")+"\nRuntime: "+showingsInfo.get("Hour")+":"+showingsInfo.get("Minute");
		System.out.println(showingString);
		paymentInformation = "**** "+paymentInformation.substring(paymentInformation.length()-4,paymentInformation.length());
		String ticketContent = ("-------TICKET-------\nEvent: "+event+"\nShowing Information: \n "+showingString+"\nSeat(s): NULL"+"\nPayment Information: "+paymentInformation+"\n--------------------\n");
		System.out.println(ticketContent);
		event = event.replace(" ", "_");
		System.out.println("EVENT RENAMED SAVED");
		FileWriter writer = new FileWriter(event+"_Ticket.txt");
		writer.write(ticketContent);
		writer.close();
		System.out.println("\nTICKET HAS BEEN SAVED!\nFile Name: "+event+"_Ticket.txt");
	}
}
