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

class EventDriverTest {

	/*@Test
	public void testRun() {
		EventDriver ed = new EventDriver();
		assertTrue(ed.run(1) = ed.displayEvents());
	}*/

	@Test
	void testDisplayMainMenu() {

	}

	@Test
	void testGetUserCommand() {
		EventDriver ed = new EventDriver();
		int numCommands = 2;
		int actual = ed.getUserCommand(numCommands);
		int expected = 1;

		assertEquals(expected, actual);
	}

	@Test
	void testErrGetUserCommand() {
		EventDriver ed = new EventDriver();
		int numCommands = 1;
		int actual = ed.getUserCommand(numCommands);
		int expected = 1;

		assertEquals(expected, actual);
	}

	@Test
	void testPayment() {
		EventHandler p1 = new EventHandler();
		String[] menuOptions = ;
		p1.printMenuArray(menuOptions);
	}

	@Test
	void testDisplayEvents() {
		System.out.println("This test ran");
	}

	@Test
	void testAccountOptions() {
		System.out.println("This test ran");
	}
}
