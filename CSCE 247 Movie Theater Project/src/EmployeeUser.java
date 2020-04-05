/*
 * @author: Andrew Jones
 * Need to go through and clean up this
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class EmployeeUser extends Event {


    public boolean isLoggedIn = false;
    public String username;
    public String password;
    public int rating;

    Scanner in = new Scanner(System.in);
        // HashMap for username and password storage
    private Map<String, String> employeeMap = new HashMap<>();
    private Map<String, String> adminMap = new HashMap<>();
    AdminUser au = new AdminUser();

        // Creates a new employee account and store the username/password in a hashmap
    public void createNewEmployeeAccount() {
        System.out.println("Please create your username:");
        String username = in.nextLine();
        if(isUsernameTaken(username) == true){
            System.out.println("That username is already taken. Please enter another username:");
            return;
        }
        System.out.println("Please create your password:");
        String password = in.nextLine();
        registerUser(username, password);
    }


        // Helper method to ensure the username isn't taken
    public boolean isUsernameTaken(String username){
        if(au.isAdmin == true)
            return adminMap.containsKey(username);
        return employeeMap.containsKey(username);
    }

        // Helper methods to enter the user's username and password into the hashmap
    public void registerUser(String username, String password) {
        employeeMap.put(username, password);
    }
    public void registerUserAdmin(String username, String password) {
        adminMap.put(username, password);
    }




        // Compares the user's username and password to the hashmap, and logs them into the system if its correct
    public void employeeLogin() {
        System.out.println("Please enter your username and password:");
        String username = in.nextLine();
        String password = in.nextLine();
        if(isLoginCorrect(username, password) != true) {
            System.out.println("The username or password is incorrect");
            return;
        }
        isLoggedIn = true;
        System.out.println("Login Successful");
    }

        // Helper method to ensure the username and password are correct
    public boolean isLoginCorrect(String username, String password) {
        if(au.isAdmin == true) {
            for(Map.Entry<String, String> entry : adminMap.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                if(k.equalsIgnoreCase(username) || v.equals(password))
                    return true;
            }
        }
        for(Map.Entry<String, String> entry : employeeMap.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if(k.equalsIgnoreCase(username) || v.equals(password))
                return true;
        }
        return false;
    }




        // Removes the username and password from the hashmap if a user wishes to delete his/her account
    public void removeAccount() {
        if(isLoggedIn != true) {
            System.out.println("Sorrry, you must be logged in to delete an account");
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
        if(answer == "yes" && au.isAdmin != true) {
            employeeMap.remove(username);
            employeeMap.remove(password);
            System.out.println("Employee Account successfully deleted");
            return;
        }
        else if(answer == "yes" && au.isAdmin == true) {
            adminMap.remove(username);
            adminMap.remove(password);
            System.out.println("Admin Account successfully deleted");
        }
        else
            System.out.println("Account was not deleted.");
    }



        // Overwrites an inappropriate or unwanted rating from a movie's comments
    public void setRating(int rating) { // Setter for rating
        if(rating >= 0 && rating <= 5)
            this.rating = rating;
        else
            System.out.println("Please input a rating of between 0 and 5");
    }


        // Adds an event to the Event class
    public void addEvent() {
        System.out.println("Please enter the title, description, director, runtime, and price");
        String title = in.nextLine();
        String description = in.nextLine();
        String director = in.nextLine();
        double runTime = in.nextDouble();
        double price = in.nextDouble();
        //Event.addEvent(title, description, director, runTime, price);
        System.out.println("Successfully added event");
    }

        // toString method for an event
    public String viewEventInformation() {  // Not sure if I should use super.title or just title, or if this should just go in Event class
        return "Title: " + title + "\nDescription: " + super.description + "\nDirector: " + director + "\nRuntime: " + runTime + " minutes\nTicket Price: $" + price;
    }

        // Allows the employee to edit an event's elements
    public void editEvent() {
        //Scanner in = new Scanner(System.in);
        System.out.println("Which part of the event would you like to change? (1) Title  (2) Description  (3) Director  (4) Runtime  (5) Ticket Price");
        int num = in.nextInt();
        switch (num) {
            case 1:
                System.out.println("Please enter the new title:");
                String title = in.nextLine();
                super.setTitle(title);          // Not sure if I should use super.setTitle or just setTitle
                break;
            case 2:
                System.out.println("Please enter the new description:");
                String description = in.nextLine();
                setDescription(description);
                break;
            case 3:
                System.out.println("Please enter the new director:");
                String director = in.nextLine();
                setDirector(director);
                break;
            case 4:
                System.out.println("Please enter the new runtime:");
                double runtime = in.nextDouble();
                setRunTime(runtime);
                break;
            case 5:
                System.out.println("Please enter the new ticket price:");
                double price = in.nextDouble();
                setPrice(price);
                break;
            default:
                System.out.println("Please choose a number between 1 - 5");
                return;
        }
        System.out.println("Event changed successfully");
    }

}
