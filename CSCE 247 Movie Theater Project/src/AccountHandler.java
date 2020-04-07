/*
 * This class deals with all accounts, removal of accounts, seeing if the username is taken, and if the login is correct
 * @Author: Team ME
 *
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class AccountHandler {

    // Level 0 = Guest User, Level 1 = General User, Level 2 = Employee User, Level 4 = Admin User
    public int level = 0;
    public String username;
    public String password;

    EmployeeUser eu = new EmployeeUser();
    GeneralUser gu = new GeneralUser();
    GuestUser guest = new GuestUser();

    // HashMaps for username and password storage
    public Map<String, String> employeeMap = new HashMap<>();
    public Map<String, String> adminMap = new HashMap<>();
    public Map<String, String> generalMap = new HashMap<>();

    Scanner in = new Scanner(System.in);

    /*
    * Removes the username and password from the hashmap if a user wishes to delete his/her account
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




    /*
    * Helper method to ensure the username isn't taken
    * @pararm username is being checked that it's not used already
    */
    public boolean isUsernameTaken(String username) {
        if(level == 3)
            return eu.adminMap.containsKey(username);
        else if(level == 2)
            return eu.employeeMap.containsKey(username);
        else if(level == 1)
            return gu.adminMap.containsKey(username);
        else
            return false;
    }

    /*
    * Helper method to ensure the username and password are correct
    * @pararm username and password are being entered and checked to see if their correct
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


}
