/**
 * 
 * @author laddjackson
 * @author wlj1@email.sc.edu
 * 
 */
import java.util.Scanner;

public class Payment {
	
	protected String paymentInformation;
	protected boolean savePayment;
	public String seats; 
	
	public boolean savePaymentInformation(boolean response) {
		if(response == true) {
			System.out.println("Payment information saved!");
			return true;
		} else {
			return false;
		}
	}
	
	public void setPaymentInformation() {
		System.out.println("\nEnter Payment Information: ");
		Scanner key = new Scanner(System.in);
		String paymentInformation = key.nextLine();
		System.out.println("SAVING PAYMENT INFORMATION: "+paymentInformation);
		this.paymentInformation = paymentInformation;
	}
	
	public void setSeats(String seats) {
		this.seats = seats;
	}
	
}

