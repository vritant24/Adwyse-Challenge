import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.junit.Test;

@SuppressWarnings("static-access")
public class ReadFromFileTest {
	
	Problem2 problem2 = new Problem2();
	ArrayList<JSONObject> result;
	
	@Test
	public void testValidFile() {
		System.out.println("Testing valid file");
		result = problem2.readFromFile("src/customers.json");
		if(result == null) 
			fail("Should read valid objects from file, but returned null");
	}
	
	@Test
	public void testNonExistentFile() {
		System.out.println("Testing non-existent file");
		result = problem2.readFromFile("src/nobody.json");
		if(result != null) 
			fail("Should return null for non-existent file");

	}
	
	@Test
	public void testEmptyFile() {
		System.out.println("Testing empty file");
		try {
			PrintWriter writer = new PrintWriter("src/file.json", "UTF-8");
			writer.println("");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = problem2.readFromFile("src/emptyfile.json");
		if(result != null && result.size() > 0) 
			fail("Should return null for empty file");
	}
	
	@Test
	public void testInvalidJSONFormat() {
		System.out.println("Testing file with invalid JSON");
		String string = "\"latitude\": \"12.986375\", \"user_id\": 12, \"name\": \"Chris\", \"longitude\": \"77.043701\"";
		
		try {
			PrintWriter writer = new PrintWriter("src/file2.json", "UTF-8");
			writer.println(string);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = problem2.readFromFile("src/file2.json");
		if(result != null) 
			fail("Should return null for invalid json");
	}
}
