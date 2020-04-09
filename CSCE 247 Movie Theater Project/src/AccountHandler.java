/**
 * @Author: Andrew Jones, Team ME
 *
 * This class deals with all accounts, removal of accounts, seeing if the username is taken, and if the login is correct
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountHandler {

    /*
     * We couldn't figure out how to get the hashmaps to work with seperated employee,
     * admin, and general user classes, and the singleton pattern on the account class
     * wasn't properly creating an instance, so we had to combine all the code into
     * this one class. So anywhere you see multiple if, else if statements, they were
     * originally 3 seperated classes.
     */

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
     * Getter for level of priority
     *
     * @return level of priority
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Setter for level of priority
     *
     * @param level is changed to param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

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
        System.out.println("You successfully created a new account");
    }

    /**
     * Logs user into their specified account
     *
     * @return whether or not the login was successful
     */
    public boolean login() {
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
            return false;
        }
        System.out.println("Please enter your username and password:");
        String username = in.nextLine();
        String password = in.nextLine();
        if(isLoginCorrect(username, password) != true) {
            System.out.println("The username or password is incorrect");
            level = 0;
            return false;
        }
        System.out.println("Login Successful");
        return true;
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

}
