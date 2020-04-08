/*
 * @Author Team ME
 * This class is handling all of the event properties
 *
 */
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileReader;
import java.math.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class EventHandler {
	//I decided to design the EventHandler similar to the MainDriver, kind of "sub-menu". Using the same structure as the main driver to increase efficiency and call methods effectively
	private Scanner scanner;

	EventHandler() {
		scanner = new Scanner(System.in);
	}

	/*
	* This method is allowing the user to choose different commands
	* @param menuType can be multiple different options for the user to choose from
	*/
	public void run(String menuType) {
		JSONParser parser = new JSONParser();
		try (FileReader file = new FileReader("Events.json")) {
			
			Object obj = parser.parse(file);
			JSONArray JArr = (JSONArray) obj;
			
			while(true) {
				printMenuArray(getTargetMenu(menuType));
	
				int userCommand = getUserCommand(getTargetMenu(menuType).length);
	
				if(userCommand == -1) {
					System.out.println("Not a valid command");
					continue;
				}
	
				if (menuType.equalsIgnoreCase("payment")) {
					switch(userCommand) {
						case(0):
							System.out.println("Proceding to Ticket Selection");
							//Insert a command for the menu being display EX: purchaseTickets();
							break;
						case(1):
							System.out.println("Call our toll free number for refund support! +1 (800) 123-4567");
							//checkoutItem();
							break;
						case(2):
							System.out.println("Returning to Main Menu");
							//rateItem();
							break;
						case(3):
							//payFine();
							break;
						case(4):
							//Back to Main Menu
							break;
					}
				} else if (menuType.equalsIgnoreCase("displayEvents")) {
					Iterator<JSONObject> iterator = JArr.iterator();
					switch(userCommand) {
					case(0):
						System.out.println("Displaying All Events\n\n");
						System.out.println("-------------------");
						//View All Events
						while (iterator.hasNext()) {
							parseJSON(iterator.next());
							System.out.println("-------------------");
						}
						break;
					case(1):
						System.out.println("Displaying All Movies\n\n");
						System.out.println("-------------------");
						//View Movies
						while(iterator.hasNext()) {
							JSONObject buffer = iterator.next();
							if (buffer.containsValue("Movie")) {
								parseJSON(buffer);
								System.out.println("-------------------");
							}
						}
						break;
					case(2):
						System.out.println("Displaying All Plays\n\n");
						System.out.println("-------------------");
						//View Plays
						while(iterator.hasNext()) {
							JSONObject buffer = iterator.next();
							if (buffer.containsValue("Play")) {
								parseJSON(buffer);
								System.out.println("-------------------");
							}
						}
						break;
					case(3):
						System.out.println("Displaying All Concerts\n\n");
						System.out.println("-------------------");
						//View Concerts
						while(iterator.hasNext()) {
							JSONObject buffer = iterator.next();
							if (buffer.containsValue("Concert")) {
								parseJSON(buffer);
								System.out.println("-------------------");
							}
						}
						break;
					case(4):
						System.out.println("Please type the exact name of the Event you want more info about.\n\n");
						String selection = scanner.next();
						if (findEvent(selection, JArr) != null) {
							JSONObject O = findEvent(selection, JArr);
							viewDetails(O);
						}
						scanner.nextLine();
						break;
					case(5):
						System.out.println("Returning to Main Menu\n\n");
						//Back to Main Menu
						break;
					}
				} //If you want to add a new sub-menu, add the if and switch statements here
	
				//if they picked the last option then log them out
				if(userCommand == getTargetMenu(menuType).length -1) break;
			}
		} catch(Exception e) {
			System.out.println(e);
			System.out.println("We are sorry, an error has occured.\nReturning to Main Menu.");
		}
	}

	/*
	* This method is controlling the payment command
	* @param menuType can be multiple differnt options for the user to choose from
	*/
	public String[] getTargetMenu(String menuType) {

		String[] menuArray = null;

		if (menuType.equalsIgnoreCase("payment")) {

			String[] paymentOptions = {"Buy Ticket(s)", "Request Refund", "Exit to Main Menu"};
			menuArray = paymentOptions;
		} else if(menuType.equalsIgnoreCase("displayEvents")) {

			String[] eventType = {"View All Events", "View Movies", "View Plays", "View Concerts", "View Details about an Event","Exit to Main Menu"};
			menuArray = eventType;
		}
		// ^ use this format to create diffrent sub-menues to be selected

		return menuArray;
	}

	/*
	* This method is printing out all of the commands that the user has to choose from
	* @param menuOptions is showing the user all the options
	*/
	public void printMenuArray(String[] menuOptions) {
		for(int i=0; i< menuOptions.length; i++) {
			System.out.println((i+1) + ". " + menuOptions[i]);
		}
		System.out.println("\n");
	}

	/*
	* This method gets the users command that they have inputted
	* @pararm numCommands is the command that the user has inputted
	*/
	private int getUserCommand(int numCommands) {
		System.out.print("What would you like to do?: ");

		String input = scanner.nextLine();
		int command = Integer.parseInt(input) - 1;

		if(command >= 0 && command <= numCommands -1) return command;

		return -1;
	}
	
	private JSONObject findEvent(String selection, JSONArray JArr) {
		Iterator<JSONObject> iterator = JArr.iterator();
			while(iterator.hasNext()) {
				JSONObject buffer = iterator.next();
				if (buffer.containsValue(selection)) {
					return buffer;
				}
			}
		return null;
	}
	
	private void parseJSON(JSONObject a) {
		System.out.println(a.get("Title"));
		System.out.println(a.get("Description"));
		JSONArray o = (JSONArray) a.get("Rating");
		Long[] ratings = new Long[o.size()];
	     for (int i = 0; i < o.size(); i++) {
	        ratings[i] = (Long)o.get(i);
	     }
	     double ratingAVG = 0;
	     for (int i = 0; i < ratings.length; i++) {
	    	 ratingAVG += ratings[i];
	     }
	     ratingAVG /= ratings.length;
	     System.out.println(ratingAVG + " Stars");
	}
	
	private void viewDetails(JSONObject a) {
		;
		System.out.println("Title: " + a.get("Title"));
		
		System.out.println("Genre: " + a.get("Genre"));
		
		System.out.println("Description : " + a.get("Description"));
		
		JSONArray seating = (JSONArray) a.get("Seating");
		System.out.println(seating.toJSONString());
		
		JSONArray showings = (JSONArray) a.get("Showings");
		for (Object s:showings) {
			JSONObject show = (JSONObject) s;
			System.out.println("Showing at "+ show.get("Theatre") + " on " + show.get("Month") + " " + show.get("Day") + "," + show.get("Hour") +":"+ show.get("Minute"));
		}
		
		JSONArray o = (JSONArray) a.get("Rating");
		Long[] ratings = new Long[o.size()];
	     for (int i = 0; i < o.size(); i++) {
	        ratings[i] = (Long)o.get(i);
	     }
	     double ratingAVG = 0;
	     for (int i = 0; i < ratings.length; i++) {
	    	 ratingAVG += ratings[i];
	     }
	     ratingAVG /= ratings.length;
	     System.out.println(ratingAVG + " Stars");
	     
	     System.out.println("Price: $" + a.get("Price"));
	     
	     JSONArray reviews = (JSONArray) a.get("Comments");
	     System.out.println("Reviews: "+ reviews.toJSONString());
	     System.out.println("-------------------\n\n");
	}

}
