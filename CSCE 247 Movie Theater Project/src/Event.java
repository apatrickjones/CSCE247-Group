import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Event {

    Scanner in = new Scanner(System.in);
    AccountHandler ah = AccountHandler.getInstance();

    /**
     * Temporary variables for entering their values into Events.json
     */
    public String title, type, genre, description, theatre, month;
    public double day, hour, minute, price;

    /*
     *  JSON file writer class
     */
    // Needs to be able to edit JSON,
    // Opens the index of deleted event to be overwritten
    public void removeEvent() {
        JSONArray jsonArray = new JSONArray();
        System.out.println("Enter index of object to delete");
        int object = in.nextInt();
        System.out.println(jsonArray);
        jsonArray.remove(object);
        System.out.println("After deleting ::"+jsonArray);
    }

    public void addEvent () {
        AccountHandler ah = AccountHandler.getInstance();
        /*
        System.out.println("Please enter your username and password to continue.");
        String username = in.nextLine();
        String password = in.nextLine();
        if (!ah.isLoginCorrect(username,password)) {
            System.out.println("Incorrect Password. Returning to Menu.");
            return;
        }
        if (ah.getLevel() <= 2) {
            System.out.println("Sorry, you do not have access to this command.");
            return;
        }*/
        try {
            JSONObject event = new JSONObject();
            JSONObject showing = new JSONObject();

            System.out.println("Enter the title:");
            title = in.nextLine();
            event.put("Title", title);

            System.out.println("Enter the type of event:");
            type = in.nextLine();
            event.put("Type", type);

            System.out.println("Enter the genre:");
            genre = in.nextLine();
            event.put("Genre", genre);

            System.out.println("Enter the description:");
            description = in.nextLine();
            event.put("Description", description);

            System.out.println("Enter the number of rows in the theatre");
            int numRow = in.nextInt();
            JSONArray seats = new JSONArray();
            System.out.println("Do the rows have the same amount of seats in them?");
            in.next();
            if (in.nextLine().equalsIgnoreCase("no")) {
                for(int i = 0; i < numRow; i++) {
                    System.out.println("Enter the number of seats in row" + i);
                    int seatsOnRow = in.nextInt();
                    JSONArray row = new JSONArray();
                    for (int j = 0; j < seatsOnRow; j++) {
                        row.add(0);
                    }
                    seats.add(row);
                }
            } else {
                System.out.println("How many seats are in a row?");
                int seatsPerRow = in.nextInt();
                for (int i = 0; i < numRow; i++) {
                    JSONArray row = new JSONArray();
                    for (int j = 0; j < seatsPerRow; j++) {
                        row.add(0);
                    }
                    seats.add(row);
                }

            }
            seats.toJSONString();
            event.put("seating", seats);

            System.out.println("Enter number of showings to add");
            int counter = in.nextInt();
            JSONArray showings = new JSONArray();

            for (int i = 0; i < counter; i++) {
                System.out.println("Enter the theatre:");
                in.nextLine();
                theatre = in.nextLine();
                showing.put("Theatre", theatre);

                System.out.println("Enter the month:");
                //in.nextLine();
                month = in.nextLine();
                showing.put("Month", month);

                System.out.println("Enter the day:");
                day = in.nextInt();
                showing.put("Day", day);

                System.out.println("Enter the hour:");
                hour = in.nextInt();
                showing.put("Hour", hour);

                System.out.println("Enter the minute:");
                minute = in.nextInt();
                showing.put("Minute", minute);
                in.nextLine();

                showings.add(showing);
            }
            event.put("Showings", showings);

            System.out.println("Enter the number of Ratings to submit");
            int countR = in.nextInt();
            JSONArray ratings = new JSONArray();

            for (int i = 1; i < countR+1; i++) {
                System.out.println("Enter rating " + i);
                ratings.add(in.nextInt());
            }
            event.put("Rating", ratings);

            System.out.println("Enter the ticket price");
            price = in.nextDouble();
            event.put("Price", price);

            System.out.println("Enter the number of Comments to submit");
            int countC = in.nextInt();
            JSONArray comments = new JSONArray();
            in.nextLine();
            for (int i = 1; i < countR+1; i++) {
                System.out.println("Enter Comment " + i);
                comments.add(in.nextLine());
            }
            event.put("comments", comments);

            FileReader fileR = new FileReader("Events.json");
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(fileR);
            JSONArray JArr = (JSONArray) obj;

            JArr.add(event);
            FileWriter fileW = new FileWriter("Events.json");
            fileW.write(JArr.toJSONString());
            fileR.close();
            fileW.flush();
            fileW.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    public void editComment() {
    	System.out.println("Enter the name of the event you wish to comment on.");
    	String eventName = in.nextLine();
    	
    	try {
			FileReader fileR = new FileReader("Events.json");
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(fileR);
			JSONArray JArr = (JSONArray) obj;
			JSONObject currObj = findEvent(eventName,JArr);
			if (!currObj.equals(null)) {
				System.out.println("Enter your comment.");
				String comment = in.nextLine();
				JSONArray commentObject = (JSONArray) currObj.get("Comments");
				commentObject.add(comment);
				
				JArr.remove(currObj);
				
				currObj.replace("Comments", commentObject);
				
				JArr.add(currObj);
				
				FileWriter fileW = new FileWriter("Events.json");
				fileW.write(JArr.toJSONString());
				fileW.flush();
				fileW.close();
				System.out.println("Comment added!");
			} else {
				System.out.println("That event is not in the database. Contact an Employee to have it added.");
			}
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
    
    public void editRatings() {
    	System.out.println("Enter the name of the event you wish to add a rating to.");
    	String eventName = in.nextLine();
    	
    	try {
			FileReader fileR = new FileReader("Events.json");
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(fileR);
			JSONArray JArr = (JSONArray) obj;
			JSONObject currObj = findEvent(eventName,JArr);
			if (!currObj.equals(null)) {
				System.out.println("Enter your Rating (1-5).");
				int review = in.nextInt();
				if (review < 1 || review > 5) {
					System.out.println("Invalid value for reviews, try again.");
					return;
				}
				JSONArray reviewObject = (JSONArray) currObj.get("Rating");
				reviewObject.add(review);
				
				JArr.remove(currObj);
				
				currObj.replace("Rating", reviewObject);
				JArr.remove(eventName);
				JArr.add(currObj);
				
				FileWriter fileW = new FileWriter("Events.json");
				fileW.write(JArr.toJSONString());
				fileW.flush();
				fileW.close();
				System.out.println("Rating added!");
			} else {
				System.out.println("That event is not in the database. Contact an Employee to have it added.");
			}
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
    
    
    /*public void editEvent() {
    	System.out.println("Enter the name of the event you wish to edit.");
    	String eventName = in.nextLine();
        System.out.println("Enter the key of the event you wish to edit (Remember to capitalize the first letter)");
        int index = in.nextInt();
		try {
			FileReader fileR = new FileReader("Events.json");
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(fileR);
			JSONArray JArr = (JSONArray) obj;
			Iterator iterator = JArr.iterator();
			
			JSONObject editing = findEvent(eventName, JArr);
			if (editing.equals(null)) {
				System.out.println("This Event does not exist. Try another event.");
				return;
			}
			JSONObject key = (JSONObject) editing.get(index);
			
			System.out.println("Enter 1 to replace the data, Enter 2 to add to the data.");
			switch(in.nextInt()) {
			case(1):
				JSONObject 
			}
				
			
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
        
        
    }*/
    
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

}
