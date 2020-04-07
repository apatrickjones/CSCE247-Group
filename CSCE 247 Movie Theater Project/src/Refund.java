/*
 * This class deals with the refund process if the user chooses to get a refund
 * @Author Adampallante
 *
 */
import java.util.Scanner;
public class Refund {

  public String paymentInformation;
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
  public void setPaymentInformation(String paymentInformation) {
    System.out.println("Please enter your form of payment, either credit, debit, or cash");
    String paymentInformation = in.nextLine();
    if(paymentInformation.equalsIgnoreCase("debit") || paymentInformation.equalsIgnoreCase("credit")) {
      System.out.println("Please enter your card number: ");
      this.cardNumber = in.nextInt();
      System.out.println("Please enter your CVV (security code): ");
      this.cvv = in.nextInt();
      System.out.println("Please enter your experation Month: ");
      this.expMonth = in.nextInt();
      System.out.pritnln("Please enter your experation year: ");
      this.expYear = in.nextInt();
    }
    else if(paymentInformation.equalsIgnoreCase("cash"))
      System.out.println("Please pay at the concession booth for your ticket.");
    else {
      System.out.println("Please enter a valid form of payment, either credit, debit, or cash");
      return;
    }
    System.out.println("Thank you, your payment information has been set");
  }

  /*
  * This method gets the payment information and returns it so the user can confirm their info is correct
  * @returns the payment info of the user
  */
  public String getPaymentInformation() {
    return "\nCard Number: " + this.cardNumber + "\nCVV: " + this.cvv + "\nExperation MM/YY: " + expMonth + "/" + expYear;
  }

  public void setSeats(String seats) {
    //TODO
  }

  /*
  * This method confirms that the users information is right and gets them to confirm it so they can be refunded
  * @param event seats paymentInformation is returned to the user so they can double check that is correct 
  */
  public void refund(Event event, String seats, String paymentInformation) {
    System.out.println("Does this information look correct? Yes or No" + getPaymentInformation());
    String answer = in.nextLine();
    if(answer.equalsIgnoreCase("no")) {
      setPaymentInformation();
      return;
    }
    else if(answer != answer.equalsIgnoreCase("yes")) {
      System.out.println("Please enter 'Yes' or 'No'");
      return;
    }
  }
System.out.println("Your refund is being processed. Thank you.")
}
