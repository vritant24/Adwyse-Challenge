import java.util.Comparator;

import org.json.simple.JSONObject;

/**
 * 
 * @author Vritant Bhardwaj
 *
 *This class is used to replace the default comparator in Collection Sort
 */
class MyComparator implements Comparator<JSONObject> {
		
		/**
		 * compare()
		 * 
		 * Compares user_id of two objects
		 */
		@Override
		public int compare(JSONObject o1, JSONObject o2) {
			long userID1 = (long) o1.get("user_id");
			long userID2 = (long) o2.get("user_id");
			
			if(userID1 > userID2) {
				return 1;
			}
			else if(userID1 < userID2) {
				return -1;
			}
			return 0;
		}
	}