import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventHandlerTest {

	static JSONArray JArr;
	static EventHandler e1 = new EventHandler();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FileReader fileR;
		try {
			fileR = new FileReader("Events.json");
			JSONParser parser = new JSONParser();
	        Object obj = parser.parse(fileR);
	        JArr = (JSONArray) obj;
	        fileR.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void getTargetMenuPaymentTest() {
		String menuType = "payment";
		String[] expected = {"Buy Ticket(s)", "Request Refund", "Exit to Main Menu"};
		assertArrayEquals(expected, e1.getTargetMenu(menuType));
	}
	@Test
	void getTargeMenuEventsTest() {
		String menuType = "displayEvents";
		String[] expected = {"View All Events", "View Movies", "View Plays", "View Concerts", "View Details about an Event","Exit to Main Menu"};
		assertArrayEquals(expected, e1.getTargetMenu(menuType));
	}
	@Test
	void getTargetAccountTest() {
		String menuType = "account";
		String[] expected = {"Create an Account", "Add New Event", "Add a Review", "Add a Rating", "Exit to Main Menu"};
		assertArrayEquals(expected, e1.getTargetMenu(menuType));
	}
	@Test
	void getUserCommandCorrectInputTest() {
		int expected = 0;
		System.out.println("Enter 0");
		assertEquals(expected,e1.getUserCommand(2));
	}
	@Test
	void getUserCommandWrongInputTest() {
		int expected = -1;
		System.out.println("Enter 3");
		assertEquals(expected,e1.getUserCommand(2));
	}
	@Test
	void findEventSuccessTest() {
		String selection = "Wicked";
		assertNotNull(e1.findEvent(selection,JArr));
	}
	@Test
	void findEventFailTest() {
		String selection = "Happy Feet";
		assertNull(e1.findEvent(selection,JArr));
	}
	@Test
	void parseJSONTest() {
		String selection = "Wicked";
		JSONObject selectionJSON = e1.findEvent(selection, JArr);
		assertTrue(e1.parseJSON(selectionJSON));
	}
	@Test
	void viewDetailsTest() {
		String selection = "Wicked";
		JSONObject selectionJSON = e1.findEvent(selection, JArr);
		assertTrue(e1.viewDetails(selectionJSON));
	}
	@Test
	void runPaymentTest() {
		System.out.println("Run through each command then run the exit command");
		assertTrue(e1.run("payment"));
	}
	@Test
	void runDisplayEventTest() {
		System.out.println("Run through each command then run the exit command");
		assertTrue(e1.run("displayEvents"));
	}
	@Test
	void runAccountTest() {
		System.out.println("Run through each command then run the exit command");
		assertTrue(e1.run("account"));
	}
}
