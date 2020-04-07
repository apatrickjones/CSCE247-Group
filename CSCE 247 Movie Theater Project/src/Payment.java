/*
 * This class deals with payment information for a user with an account
 * @author laddjackson
 *
 */
import java.util.Scanner;

public class Payment {

	protected String paymentInformation;
	protected boolean savePayment;
	public String seats;

	/*
	* This method tells the user if their payment info has been properly saved
	* @param response tells the user if the payment info has been properly stored
	*/
	public boolean savePaymentInformation(boolean response) {
		if(response == true) {
			System.out.println("Payment information saved!");
			return true;
		} else {
			return false;
		}
	}

	/*
	* This method sets the users payment information
	* @returns that the info is being saved and what the user entered for their payment information
	*/
	public void setPaymentInformation() {
		System.out.println("\nEnter Payment Information: ");
		Scanner key = new Scanner(System.in);
		String paymentInformation = key.nextLine();
		System.out.println("SAVING PAYMENT INFORMATION: "+paymentInformation);
		this.paymentInformation = paymentInformation;
	}

	/*
	* This method will deal with setting the user's seats that they choose while paying
	* @returns seats will be returned to the user to show them the exact seats that they chose
	*/
	public void setSeats(String seats) {
		this.seats = seats;
	}

}
