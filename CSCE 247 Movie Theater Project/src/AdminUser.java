/*
 *
 */
import java.util.HashMap;
import java.util.Map;
public class AdminUser extends EmployeeUser {

    public String reward;




    public void createNewAdminAccount() {
        System.out.println("Please create your username:");
        String username = in.nextLine();
        if(isUsernameTaken(username) == true) {
            System.out.println("That username is already taken. Please enter another username:");
            return;
        }
        System.out.println("Please create your password:");
        String password = in.nextLine();
        registerUserAdmin(username, password);
    }

    private void adminLogin() { // May have a code system where you must enter a specific code to be able to become an admin
        System.out.println("Please enter your username and password:");
        String username = in.nextLine();
        String password = in.nextLine();
        if(isLoginCorrect(username, password)) {
            System.out.println("The username or password is incorrect");
        }
        level = 3;
        System.out.println("Login Successful");

    }

    // Helper methods to enter the user's username and password into the hashmap
    public void registerUserAdmin(String username, String password) {
        adminMap.put(username, password);
    }












    public void setRewards(String reward) {    // Setter for reward
        if(level != 3)
            return;
        this.reward = reward;
    }

    public String getRewards() {
        return this.reward;
    }
    public void deleteReview() {
        if(level != 3)
            return;
        //TODO
    }

    public void editReview() {
        if(level != 3)
            return;
        //TODO
    }


}
