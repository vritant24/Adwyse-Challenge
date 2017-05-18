import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class CheckJSONObjectsTest {
	
	Problem2 problem2 = new Problem2();
	boolean result;
	ArrayList<JSONObject> input = new ArrayList<>();
	JSONObject testObject = new JSONObject();
	
	@Test
	public void testValidInput() {
		testObject.put("name", "Larry");
		testObject.put("latitude", "12.08234");
		testObject.put("longitude", "77.08703");
		testObject.put("user_id", "1");
		input.add(testObject);
		result = problem2.checkJSONObjects(input);
		if(result == false) {
			fail("Should return true for valid input");
		}
	}
	
	@Test
	public void testLessElements() {
		testObject.put("name", "Larry");
		input.add(testObject);
		result = problem2.checkJSONObjects(input);
		if(result != false) {
			fail("Should return false for not enough elements");
		}
	}
	
	@Test
	public void testIncorrectElements() {
		testObject.put("name", "Larry");
		testObject.put("latitude", "12.08234");
		testObject.put("longitude", "77.08703");
		testObject.put("user", "1");
		input.add(testObject);
		result = problem2.checkJSONObjects(input);
		if(result != false) {
			fail("Should return false for incorrect element key");
		}
	}

	@Test
	public void testInvalidValue() {
		testObject.put("name", "Larry");
		testObject.put("latitude", "12.08234abc");
		testObject.put("longitude", "77.08703a");
		testObject.put("user_id", "1a");
		input.add(testObject);
		result = problem2.checkJSONObjects(input);
		if(result != false) {
			fail("Should return false for invalid value in element");
		}	
	}
}
