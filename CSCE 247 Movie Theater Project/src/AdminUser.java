/*
 * @Author Team ME
 *This class allows the Admin user to log in and modify rewards and reviews
 */
import java.util.HashMap;
import java.util.Map;
public class AdminUser extends EmployeeUser {

    public String reward;

   /*
   * This method is allowing a user to create their admin account
   * @returns if the username is avalible for use
   */
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

    /*
    * This method is allowing the admin user to log in using their credentials
    * @returns if the user has entered the correct user and password to log in
    */
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

    /*
    * Helper methods to enter the user's username and password into the hashmap
    * @param username and password are being changed
    */
    public void registerUserAdmin(String username, String password) {
        adminMap.put(username, password);
    }












    /*
    * This method is setting the reward that is set by the admin for that specific user
    * @param setting the reward for the user
    */
    public void setRewards(String reward) {    // Setter for reward
        if(level != 3)
            return;
        this.reward = reward;
    }

    /*
    * This method gets and returns the reward for the specific user
    * @returns the reward for the specific user
    */
    public String getRewards() {
        return this.reward;
    }

    /*
    * This method allows the admin user to delete a review left by a user
    * @returns that the user's review has been deleted by the admin
    */
    public void deleteReview() {
        if(level != 3)
            return;
        //TODO
    }

    /*
    * This method allows the admin to edit a review left by a user
    * @returns that the comment has been edited and saved by the admin
    */
    public void editReview() {
        if(level != 3)
            return;
        //TODO
    }


}
