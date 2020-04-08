/*
 * @author: Andrew Jones
 * Need to go through and clean up this
 */

public class EmployeeUser extends AccountHandler  {

        /*
        * Creates a new employee account and store the username/password in a hashmap
        * @returns if the username is taken or avaliable
        */
    public void createNewEmployeeAccount() {
        System.out.println("Please create your username:");
        String username = in.nextLine();
        if(isUsernameTaken(username) == true) {
            System.out.println("That username is already taken. Please enter another username:");
            return;
        }
        System.out.println("Please create your password:");
        String password = in.nextLine();
        registerUserEmployee(username, password);
    }

    /*
    * Compares the user's username and password to the hashmap, and logs them into the system if its correct
    * @returns if the username and password are correct or incorrect
    */
    public void employeeLogin() {
        level = 2;
        System.out.println("Please enter your username and password:");
        String username = in.nextLine();
        String password = in.nextLine();
        if(isLoginCorrect(username, password) != true) {
            System.out.println("The username or password is incorrect");
            return;
        }
        level = 2;
        System.out.println("Login Successful");
    }

        /*
        * Helper methods to enter the user's username and password into the hashmap
        * @param username and password are being saved into a hashmap
        */
    public void registerUserEmployee(String username, String password) {
        employeeMap.put(username, password);
    }


    public void editRewards(String reward) {
        if(level < 2) {
            System.out.println("You must be an employee or higher to edit the reward");
            return;
        }
        getRewards();
        System.out.println("Would you like to change the current rewards?");
        String answer = in.nextLine();
        if(answer.equalsIgnoreCase("no")) {
            System.out.println("Thank you");
            return;
        }
        else if(answer != "yes") {
            System.out.println("Please input either yes or no");
            return;
        }
        System.out.println("Please enter the new reward");
        reward = in.nextLine();
        setRewards(reward);
        System.out.println("The reward has been updated to " + getRewards() + ". Thank you");
    }











        /*
        * Overwrites an inappropriate or unwanted rating from a movie's comments
        * @param rating gets set by the user
        *

    public void setRating(int rating) { // Setter for rating
        if(rating >= 0 && rating <= 5)
            this.rating = rating;
        else
            System.out.println("Please input a rating of between 0 and 5");
    }

        *
        * Adds an event and it's properties to the Event class
        * @returns that the event and the properties of the event have been stored
        *
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

     */


}
