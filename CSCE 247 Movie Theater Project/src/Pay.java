/**
 * 
 * @author laddjackson
 *
 */

import java.io.*;
import java.util.Scanner;

public class Pay {
	public String paymentInformation;
	
	public void setPaymentInformation(String paymentInformation) {
		this.paymentInformation = paymentInformation;
	}
	
	public String getPaymentInformation() {
		return this.paymentInformation;
	}
	
	//I've only created guestCheckout so far, once user classes are functional I will implement AccountHolderCheckout() 
	public void guestCheckout(/*String personalInformation*/) {
		
		System.out.println("Enter the name of movie or event you wish to purchase tickets for: ");
		Scanner key = new Scanner(System.in);
		String event = key.nextLine();
		System.out.println("\nDisplaying showings for "+event);
		//Here is where I want to display the avaliable showings for the event
		System.out.println("Enter number for showing you wish to attend: ");
		int showingChoice = Integer.parseInt(key.nextLine());
		//Here is where I want to display the avaliable seating for the event
		System.out.println("Enter seat selection: ");
		String seatChoice = key.nextLine();
		
		//Now since it is a guest transaction, I request payment info
		System.out.println("Please enter payment information: ");
		String paymentInformation = key.nextLine();
		while(paymentInformation.isEmpty()) {
			System.out.println("INVALID PAYMENT INFORMATION! -- Please enter your payment information");
			paymentInformation = key.nextLine();
		}
		
		System.out.println("\n--Checkout Details--\nEvent: "+event+"\nShowing number: "+showingChoice+"\nSeat(s):"+seatChoice+"\nStatus: Purchased!\n--------------------\n");
		
		System.out.println("Would you like to print your ticket? (Enter yes or no)");
		String printTicket = key.nextLine();
		while(true) {
			if(printTicket.equalsIgnoreCase("yes")) {
				try {
					printTicket(event, showingChoice, seatChoice, paymentInformation);
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
	
	//Functional, but will be polished before final version
	public void printTicket(String event, int showingChoice, String seatChoice, String paymentInformation) throws IOException {
		paymentInformation = "**** "+paymentInformation.substring(paymentInformation.length()-4,paymentInformation.length());
		String ticketContent = ("-------TICKET-------\nEvent: "+event+"\nShowing #: "+showingChoice+"\nSeat(s): "+seatChoice+"\nPayment Information: "+paymentInformation);
		event = event.replace(" ", "_");
		File eventTicket = new File(event+"_Ticket.txt");
		FileWriter writer = new FileWriter(event+"_Ticket.txt");
		writer.write(ticketContent);
		writer.close();
		System.out.println("\nTICKET HAS BEEN SAVED!\nFile Name: "+event+"_Ticket.txt");
	}
}
