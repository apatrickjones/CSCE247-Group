/**
 * @Author: Andrew Jones, Jake Powers, Team ME
 *
 * This class deals with all accounts, removal of accounts, seeing if the username is taken, and if the login is correct
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AccountHandler {

    Scanner in = new Scanner(System.in);

    /**
     *  Level 0 = Guest User, Level 1 = General User, Level 2 = Employee User, Level 4 = Admin User
     *
     * Guest can: input payment information and checkout    // Default, set to 0 by default and only changes if you log into a new account
     * General can: do everything a guest can, create a new general account, login to general account, get suggested movies, save payment information, and check viewing history
     * Employee can: create new employee account, login to employee account, edit movies, create a new movie, and view events
     * Admin can: do everything an employee can, create new admin account, login to admin account, change rewards, and change/delete reviews/ratings
     *
     */
    public int level = 0;
    public String reward = "";
    private static AccountHandler accountHandler;

    /**
     * HashMaps for username and password storage:
     */
    public Map<String, String> employeeMap = new HashMap<>();
    public Map<String, String> adminMap = new HashMap<>();
    public Map<String, String> generalMap = new HashMap<>();


    /**
     * Instance class for AccountHandler, Singleton design pattern
     *
     * @return the current instance of AccountHandler if one is open,
     * @return a new instance of AccountHandler if one is not open
     */
    public static AccountHandler getInstance() {
        if (accountHandler == null) {
            accountHandler = new AccountHandler();
        }
        return accountHandler;
    }

    /**
     * Creates a new user account of the type specified and enters the username and password
     * into the specific hashmap for that type of account
     */
    public void createNewAccount() {
        System.out.println("Are you making a General, Employee, or Admin account?");
        String answer = in.nextLine();
        System.out.println("Please create your username:");
        String username = in.nextLine();
        if(isUsernameTaken(username) == true) {
            System.out.println("That username is already taken. Please enter another username:");
            return;
        }
        System.out.println("Please create your password:");
        String password = in.nextLine();
        if(answer.equalsIgnoreCase("general")) {
            generalMap.put(username, password);
        }
        else if(answer.equalsIgnoreCase("employee")) {
            employeeMap.put(username, password);
        }
        else if(answer.equalsIgnoreCase("admin")) {
            adminMap.put(username, password);
        }
        else {
            System.out.println("You have to choose something!");
            return;
        }
    }

    /**
     * Logs user into their specified account
     *
     * @return whether or not the login was successful
     */
    public void login() {
        System.out.println("What kind of account are you logging into? General, Employee or Admin");
        String answer = in.nextLine();
        if(answer.equalsIgnoreCase("general")) {
            level = 1;
        }
        else if(answer.equalsIgnoreCase("employee")) {
            level = 2;
        }
        else if(answer.equalsIgnoreCase("admin")) {
            level = 3;
        }
        else {
            System.out.println("Invalid input");
            return;
        }
        System.out.println("Please enter your username and password:");
        String username = in.nextLine();
        String password = in.nextLine();
        if(isLoginCorrect(username, password) != true) {
            System.out.println("The username or password is incorrect");
            level = 0;
            return;
        }
        System.out.println("Login Successful");
    }

    /**
     * Removes the username and password from the hashmap if a user wishes to delete his/her account
     *
     * @returns which account has been successfully deleted
     */
    public void removeAccount() {
        if(level == 0) {
            System.out.println("Sorry, you must be logged in to delete an account");
            return;
        }
        System.out.println("Please enter the username and password of the account you wish to delete");
        String username = in.nextLine();
        String password = in.nextLine();
        if(isLoginCorrect(username, password) != true) {
            System.out.println("Incorrect Username or Password");
            return;
        }
        System.out.println("Are you sure you wish to delete this account?");
        String answer = in.nextLine();
        if(answer == "yes" && level == 3) {
            adminMap.remove(username);
            adminMap.remove(password);
            System.out.println("Admin Account successfully deleted");
        }
        else if(answer == "yes" && level == 2) {
            employeeMap.remove(username);
            employeeMap.remove(password);
            System.out.println("Employee Account successfully deleted");
            return;
        }
        else if(answer == "yes" && level == 1) {
            generalMap.remove(username);
            generalMap.remove(password);
            System.out.println("General Account successfully deleted");
            return;
        }
        else
            System.out.println("Account was not deleted.");
    }

    /**
     * Helper method to ensure the username isn't taken
     *
     * @pararm username is being checked that it's not used already
     * @return true if the username key has been taken by another account
     * @return false if the username key has not been taken
     */
    public boolean isUsernameTaken(String username) {
        if(adminMap.containsKey(username) == true)
            return adminMap.containsKey(username);
        else if(employeeMap.containsKey(username) == true)
            return employeeMap.containsKey(username);
        else if(generalMap.containsKey(username) == true)
            return generalMap.containsKey(username);
        else
            return false;
    }

    /**
     * Helper method to ensure the username and password are correct
     *
     * @pararm username and password are being entered and checked to see if their correct
     * @return true if the username and password's respective hashmap includes their key and value properly
     * @return false if the username and password are incorrect
     */
    public boolean isLoginCorrect(String username, String password) {
        if(level == 3) {
            for(Map.Entry<String, String> entry : adminMap.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                if(k.equalsIgnoreCase(username) || v.equals(password))
                    return true;
            }
        }
        else if(level == 2) {
            for(Map.Entry<String, String> entry : employeeMap.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                if (k.equalsIgnoreCase(username) || v.equals(password))
                    return true;
            }
        }
        else if(level == 1) {
            for(Map.Entry<String, String> entry : generalMap.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                if (k.equalsIgnoreCase(username) || v.equals(password))
                    return true;
            }
        }
        return false;
    }




    

    public String getRewards() {
        return reward;
    }

    public void setRewards(String reward) {
        this.reward = reward;
    }








    //Object obj = jsonParser.parse(new FileReader("Events.json"));
    //private static FileWriter file;


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
    	System.out.println("Please enter your username and password to continue.");
    	String username = in.nextLine();
    	String password = in.nextLine();
    	if (!isLoginCorrect(username,password)) {
    		System.out.println("Incorrect Password. Returning to Menu.");
    		return;
    	}
    	if (level <= 2) {
    		System.out.println("Sorry, you do not have access to this command.");
    		return;
    	}
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


    public void editEvent() {
        System.out.println("Enter the index of the event you wish to edit");
        int index = in.nextInt();


    }

}
