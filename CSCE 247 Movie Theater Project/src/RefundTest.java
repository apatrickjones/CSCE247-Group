import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

class RefundTest {

	@Test
	void testDebitSetPaymentInformation() {
		Refund r1 = new Refund();
		String paymentType = "debit";
		r1.setPaymentInformation(paymentType);
	}
	
	@Test
	void testCashSetPaymentInformation() {
		Refund r1 = new Refund();
		String paymentType = "cash";
		r1.setPaymentInformation(paymentType);
	}

	@Test
	void testGetPaymentInformation() {
		Refund r1 = new Refund();
		//setting data in class to be tested
		r1.cardNumber = 1234;
		r1.cvv = 111;
		r1.expMonth = 1;
		r1.expYear = 1;
		assertEquals("\nCard Number: " + r1.cardNumber + "\nCVV: " + r1.cvv + "\nExperation MM/YY: " + r1.expMonth + "/" + r1.expYear, r1.getPaymentInformation());
	}
	
	@Test
	void testRetCardNumber() {
		Refund r1 = new Refund();
		int actual = r1.retCardNumber();
		assertNotSame("",r1.cardNumber);
	}
	
	@Test
	void testRetCvv() {
		Refund r1 = new Refund();
		r1.retCvv();
		assertNotSame("",r1.cvv);
	}
	
	@Test
	void testRetExpMonth() {
		Refund r1 = new Refund();
		r1.retExpMonth();
		assertNotSame("",r1.expMonth);
	}
	
	@Test
	void testRetExpYear() {
		Refund r1 = new Refund();
		r1.retExpYear();
		assertNotSame("",r1.expYear);
	}

	@Test
	void testSetSeats() {
		Refund r1 = new Refund();
		String selectedSeats = "1-2 1-3 1-4";
		r1.setSeats(selectedSeats);
		assertEquals(selectedSeats, r1.getSeats());
	}
	
	@Test
	void testGetSeats() {
		Refund r1 = new Refund();
		String selectedSeats = "1-2 1-3 1-4";
		r1.seats = selectedSeats;
		assertEquals(selectedSeats, r1.getSeats());
	}

	@Test
	void testRefund() {
		Refund r1 = new Refund();
		r1.refund(r1.seats, r1.getPaymentInformation());
	}

}
