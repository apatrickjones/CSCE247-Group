import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class PayTest {
	
	@Test
	void testSetPaymentInformation() {
		Pay p1 = new Pay();
		String testPaymentInformation = "1234";
		p1.setPaymentInformation(testPaymentInformation);
		assertEquals(testPaymentInformation, p1.getPaymentInformation(), "Expected payment information: "+testPaymentInformation+"\nActual Payment information: "+p1.getPaymentInformation());
	}

	@Test
	void testGetPaymentInformation() {
		Pay p1 = new Pay();
		String testPaymentInformation = "1234";
		p1.setPaymentInformation(testPaymentInformation);
		assertNotNull(p1.getPaymentInformation());
	}

	
	@Test
	void testCheckout() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader file = new FileReader("Events.json");
		
		Object obj = parser.parse(file);
		JSONArray JArr = (JSONArray) obj;
		
		Iterator<JSONObject> iterator = JArr.iterator();
		JSONObject jsonObj = iterator.next();
		Pay p1 = new Pay();
		p1.checkout(jsonObj);
	}

	@Test
	void testUserCheckout() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader file = new FileReader("Events.json");
		
		Object obj = parser.parse(file);
		JSONArray JArr = (JSONArray) obj;
		
		Iterator<JSONObject> iterator = JArr.iterator();
		JSONObject jsonObj = iterator.next();
		Pay p1 = new Pay();
		AccountHandler newUser = AccountHandler.getInstance();
		newUser.createNewAccount();
		p1.userCheckout(jsonObj, newUser);
	}

	@Test
	void testGuestCheckout() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader file = new FileReader("Events.json");
		
		Object obj = parser.parse(file);
		JSONArray JArr = (JSONArray) obj;
		
		Iterator<JSONObject> iterator = JArr.iterator();
		JSONObject jsonObj = iterator.next();
		System.out.println(jsonObj);
		Pay p1 = new Pay();
		p1.guestCheckout(jsonObj);
	}
	
	
	//Testing helper methods
	@Test
	void testShowingInformation() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader file = new FileReader("Events.json");
		
		Object obj = parser.parse(file);
		JSONArray JArr = (JSONArray) obj;
		
		Iterator<JSONObject> iterator = JArr.iterator();
		JSONObject jsonObj = iterator.next();
		Pay p1 = new Pay();
		assertNotNull(p1.showingInformation(jsonObj));
	}
	
	@Test 
	void testSeatSelection() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader file = new FileReader("Events.json");
		
		Object obj = parser.parse(file);
		JSONArray JArr = (JSONArray) obj;
		
		Iterator<JSONObject> iterator = JArr.iterator();
		JSONObject jsonObj = iterator.next();
		System.out.println(jsonObj);
		Pay p1 = new Pay();
		assertNotNull(p1.seatSelection(jsonObj));
	}

	@Test
	void testPrintTicket() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader file = new FileReader("Events.json");
		
		Object obj = parser.parse(file);
		JSONArray JArr = (JSONArray) obj;
		
		Iterator<JSONObject> iterator = JArr.iterator();
		JSONObject jsonObj = iterator.next();
		System.out.println(jsonObj);
		Pay p1 = new Pay();
		p1.printTicket(jsonObj, "1234 1234");
	}
	
	@Test
	void testCheckForPaymentInformationNull() {
		Pay p1 = new Pay();
		String testPaymentInfo = p1.checkForPaymentInformation(null);
		assertNotNull(testPaymentInfo);
	}
	
	@Test
	void testCheckForPaymentInformationUser() {
		AccountHandler newUser = AccountHandler.getInstance();
		newUser.createNewAccount();
		Pay p1 = new Pay();
		String testPaymentInfo = p1.checkForPaymentInformation(newUser);
		assertNotNull(testPaymentInfo);
	}

}
