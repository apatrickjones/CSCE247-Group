/**
 * 
 * @author laddjackson
 *
 */

public class Pay {
	public String paymentInformation;
	public String seats;
	
	public void setPaymentInformation(String paymentInformation) {
		this.paymentInformation = paymentInformation;
	}
	
	public void setSeats(String seats) {
		this.seats = seats;
	}
	
	public String getPaymentInformation() {
		return this.paymentInformation;
	}
	
	public String getSeats() {
		return this.seats;
	}

	public String guestCheckout(String personalInformation) {
		
		return "checked out!";
	}
	
	public String checkout(Event event, String seats, String paymentInformation) {
		return "checked out!";
	}
}
