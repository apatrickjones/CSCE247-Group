/*
 * This class deals with the refund process if the user chooses to get a refund
 * @Author Adampallante
 *
 */
import java.util.Scanner;
public class Refund {

  public String paymentType;
  public int cardNumber;
  public int cvv;
  public int expMonth;
  public int expYear;
  public String seats;
  Scanner in = new Scanner(System.in);

  /*
  * This method asks and sets the users information so that the refund can go to the right credit or debit card
  * @param payment information is where the users info has been stored
  */
  public void setPaymentInformation(String paymentType) {
    System.out.println("Please enter your form of payment, either credit, debit, or cash");
    paymentType = in.nextLine();
    if(paymentType.equalsIgnoreCase("debit") || paymentType.equalsIgnoreCase("credit")) {
      setCardInformation();
    }
    else if(paymentType.equalsIgnoreCase("cash"))
      System.out.println("Please pay at the concession booth for your ticket.");
    else {
      System.out.println("Please enter a valid form of payment, either credit, debit, or cash");
      return;
    }
    System.out.println("Thank you, your payment information has been set");
  }
  
  /**
   * This method utilizes the "ret" helper methods below to retrieve and set card information 
   */
  public void setCardInformation() {
	  this.cardNumber = retCardNumber();
	  this.cvv = retCvv();
	  this.expMonth = retExpMonth();
	  this.expYear = retExpYear();
  }

  /**
   * Below helper methods request and return card information to be set
   * @return cardNumber, CVVnumber, expMonth, expYear
   */
  public int retCardNumber() {
	System.out.println("Please enter your card number: ");
	int cardNumber = in.nextInt();
	return cardNumber;
  }


  public int retCvv() {
	System.out.println("Please enter your CVV (security code): ");
	int cvv = in.nextInt();
	return cvv;
  }


  public int retExpMonth() {
	System.out.println("Please enter your experation Month: ");
	int expMonth = in.nextInt();
	return expMonth;
  }


  public int retExpYear() {
	System.out.println("Please enter your experation Year: ");
	int expYear = in.nextInt();
	return expYear;
  }


/*
  * This method gets the payment information and returns it so the user can confirm their info is correct
  * @returns the payment info of the user
  */
  public String getPaymentInformation() {
    return "\nCard Number: " + this.cardNumber + "\nCVV: " + this.cvv + "\nExperation MM/YY: " + expMonth + "/" + expYear;
  }

  public void setSeats(String seats) {
    this.seats = seats;
  }
  
  public String getSeats() {
	return this.seats;
  }

  /*
  * This method confirms that the users information is right and gets them to confirm it so they can be refunded
  * @param event seats paymentInformation is returned to the user so they can double check that is correct 
  */

  public void refund(String seats, String paymentInformation) {
    System.out.println("Does this information look correct? Yes or No" + getPaymentInformation());
    String answer = in.nextLine();
    if(answer.equalsIgnoreCase("no")) {
      System.out.println("Too bad");
      return;
  }
    else if(answer != "yes" || answer != "no") {    ////////////////////////////
      System.out.println("Please enter 'Yes' or 'No'");
      return;
    }
    System.out.println("Your refund is being processed. Thank you.");
  }

}
