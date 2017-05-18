import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author Vritant Bhardwaj
 * 
 * Solution to Problem 2 
 * Outputs sorted list of people that are within 100km distance from Adwyse's Bangalore office
 *
 */
public class Problem2 {
	
	/**
	 * Reads given json file and stored the objects in an arraylist
	 *  
	 * @param pathToFile	path to the json file
	 * @return 				arraylist of JSON objects
	 */
	public static ArrayList<JSONObject> readFromFile(String pathToFile) {
		ArrayList<JSONObject> friends = new ArrayList<>();
		JSONParser parser = new JSONParser();
		
		int n = 1;		//counts number of objects
		
		File file=new File(pathToFile);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				friends.add((JSONObject) parser.parse(scanner.nextLine()));
				n++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Given path to file not found");
			return null;
		} catch (ParseException e) {
			System.out.println("Object " + n + " is not properly formatted");
			return null;
		} finally {
		    if(scanner!=null)
		        scanner.close();
		}
		
		return friends;
	}
	
	/**
	 * Check if given string is numeric
	 * @param string	Contains expression to check
	 * @return			true if string contains only [0-9] or - or .
	 */
	public static boolean isNumeric (String string) {
		 return string.matches("-?\\d+(\\.\\d+)?");
	}
	
	/**
	 * Check the values in JSON objects read from given file
	 * 
	 * @param friends		Arraylist of friends read from file
	 * @return				True if arraylist is valid
	 */
	public static boolean checkJSONObjects(ArrayList<JSONObject> friends) {
		JSONObject obj;
		for(int i = 0; i < friends.size(); i++) {
			obj = friends.get(i);
			if(!obj.containsKey("latitude")) {
				System.out.println("Object " + (i+1) + " doesn't contain latitude");
				return false;
			}
			if (!obj.containsKey("longitude"))  {
				System.out.println("Object " + (i+1) + " doesn't contain longitude"); 
				return false;
			}
			if (!obj.containsKey("user_id"))  {
				System.out.println("Object " + (i+1) + " doesn't contain user_id"); 
				return false;
			}
			if(!obj.containsKey("name")) {
				System.out.println("Object " + (i+1) + " doesn't contain name"); 
				return false;
			}
			if(!isNumeric(obj.get("latitude").toString())) {
				System.out.println("Object " + (i+1) + " latitude is not numeric"); 
				return false;
			}
			if(!isNumeric(obj.get("longitude").toString())) {
				System.out.println("Object " + (i+1) + " longitude is not numeric"); 
				return false;
			}
			if(!isNumeric(obj.get("user_id").toString())) {
				System.out.println("Object " + (i+1) + " user_id is not numeric"); 
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Calculates distance between Bangalore office and friend
	 * 
	 * @param lat2		latitude of friend
	 * @param long2		longitude of friend
	 * @return 			distance 
	 */
	public static double getDistance(double lat2, double long2) {
		double radiusOfEarth = 6371;
		
		//home or Bangalore office coordinates
		double lat1 = 12.9611159;
		double long1 = 77.6362214;
		
		double centralAngle = 2 * Math.asin(Math.sqrt(
					Math.pow(Math.sin((lat1 - lat2) / 2), 2) + 
					Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((long1 - long2) / 2), 2)
				));
	
		return radiusOfEarth * Math.toRadians(centralAngle);
	}
	
	
	/**
	 * Finds friends within a 100km radius
	 * 
	 * @param friends	arraylist of friends from given file
	 * @return 			arraylist of friends within a 100km radius
	 */
	public static ArrayList<JSONObject> findClosebyFriends(ArrayList<JSONObject> friends) {
		ArrayList<JSONObject> closebyFriends = new ArrayList<>();
		JSONObject obj;
		double distance;
		
		for(int i = 0; i < friends.size(); i++) {
			obj = friends.get(i);
			distance = getDistance(Double.parseDouble((String)obj.get("latitude")), 
					Double.parseDouble((String)obj.get("longitude"))); 
			if(distance <= 100) {
				closebyFriends.add(obj);
			}
		}
		return closebyFriends;
	}
	
	
	/**
	 * Sorts given array of JSON objects using user_id in ascending order
	 * 
	 * @param closebyFriends	arraylist of friends within 100km distance
	 * @return 					sorted closebyFriends 
	 */
	public static ArrayList<JSONObject> sortClosebyFriends(ArrayList<JSONObject> closebyFriends) {
		if(closebyFriends.size() > 1) {
			Collections.sort(closebyFriends, new MyComparator());
		}
		return closebyFriends;
	}
	
	
	/**
	 * prints out the user_id and name of the friends
	 * 
	 * @param closebyFriends	sorted arraylist of friends within 100km distance
	 */
	public static void printClosebyFriendList(ArrayList<JSONObject> closebyFriends) {
		JSONObject obj;
		for(int i = 0; i < closebyFriends.size(); i++) {
			obj = closebyFriends.get(i);
			System.out.println(obj.get("user_id") + " " + obj.get("name"));
		}
	}
	
	
	/**
	 * calls above functions to solve problem 2
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<JSONObject> friends = readFromFile("src/customers.json");
		if(friends == null) {
			return;
		}
		if(friends.size() == 0) {
			System.out.println("Given file is empty");
		}
		if(!checkJSONObjects(friends)) {
			return;
		}
		
		printClosebyFriendList(sortClosebyFriends(findClosebyFriends(friends)));
	}
}
