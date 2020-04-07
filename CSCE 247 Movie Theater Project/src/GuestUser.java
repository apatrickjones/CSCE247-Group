/*
 * This class is handling the options  that the guest user will have if they don't sign into an account
 * @Author: Team ME
 *
 */
import java.util.Scanner;
public class GuestUser extends AccountHandler  {

    public int cardNumber;
    public int cvv;
    public int expMonth;
    public int expYear;
    Scanner in = new Scanner(System.in);

    /*
    * This method is setting the payment information for the user
    * @returns the payment information the user has entered to confirm the payment info is correct
    */
    public void setPaymentInformation() { // Setter for paymentInformation
        System.out.println("Please enter your form of payment, either credit, debit, or cash");
        String paymentType = in.nextLine();
        if(paymentType.equalsIgnoreCase("debit") || paymentType.equalsIgnoreCase("credit")) {
            System.out.println("Please enter your card number:");
            this.cardNumber = in.nextInt();
            System.out.println("Please enter your CVV (security code):");
            this.cvv = in.nextInt();
            System.out.println("Please enter your expiration month:");
            this.expMonth = in.nextInt();
            System.out.println("Please enter your expiration year:");
            this.expYear = in.nextInt();
        }
        else if(paymentType.equalsIgnoreCase("cash"))
            System.out.println("Please pay at the concession booth for your ticket");
        else {
            System.out.println("Please enter a valid form of payment, either credit, debit, or cash");
            return;
        }
        System.out.println("Payment information successfully set");
    }

    /*
    * This method is returning the payment information the user has entered
    * @returns the payment information
    */
    public String getPaymentInformation() {    // Getter for personalInformation
        return "\nCard Number: " + this.cardNumber + "\nCVV: " + cvv + "\nExpiration MM/YY: " + expMonth + "/" + expYear;
    }

    /*
    * This method is allowing for the guest user to double check their payment info
    * @returns that the user's payment info is either correct or not 
    */
    public void guestCheckout() {
        System.out.println("Does this information look correct?" +  getPaymentInformation());
        String answer = in.nextLine();
        if(answer.equalsIgnoreCase("no")) {
            setPaymentInformation();
            return;
        }
        else if(answer != "yes") {
            System.out.println("Please enter 'yes' or 'no'");
            return;
        }
        //TODO
    }

}
