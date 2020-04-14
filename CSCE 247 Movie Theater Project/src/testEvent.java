import static org.junit.Assert.assertNotNull;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testEvent {

	static JSONArray JArr;
	static Event e1 = new Event();
	
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
	void tearDown() throws Exception, IOException {
		FileWriter fileW;
		fileW = new FileWriter("Events.json");
		fileW.write(JArr.toJSONString());
        fileW.flush();
        fileW.close();
		
	}

	@Test
	void getJSONTest() {
		assertNotNull(e1.addEvent());
	}
	@Test
	void editCommentTest() {
		assertNotEquals(e1.editComment(),JArr);
	}
	@Test
	void editRatings() {
		assertNotEquals(e1.editRatings(),JArr);
	}

}
