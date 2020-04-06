/*
 * @Author Adampallante
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

  public String getPaymentInformation() {
    return "\nCard Number: " + this.cardNumber + "\nCVV: " + this.cvv + "\nExperation MM/YY: " + expMonth + "/" + expYear;
  }

  public void setSeats(String seats) {
    //TODO
  }

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
