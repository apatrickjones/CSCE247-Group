/*
 *
 */
import java.util.HashMap;
import java.util.Map;
public class AdminUser extends EmployeeUser {

    public boolean isAdmin;
    public String reward;
    //private Map<String, String> adminMap = new HashMap<>();



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
        isLoggedIn = true;
        isAdmin = true;
        System.out.println("Login Successful");

    }


    public void setRewards(String reward) {    // Setter for reward
        if(isAdmin != true)
            return;
        this.reward = reward;
    }

    public String getRewards() {
        return this.reward;
    }
    public void deleteReview() {
        if(isAdmin != true)
            return;
        //TODO
    }

    public void editReview() {
        if(isLoggedIn != true)
            return;
        //TODO
    }


}
