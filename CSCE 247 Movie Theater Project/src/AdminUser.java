/*
 *
 */
public class AdminUser extends EmployeeUser {

    public boolean isLoggedIn;
    public String reward;


    public void setRewards(String reward) {    // Setter for reward
        this.reward = reward;
    }

    public String getRewards() {
        return this.reward;
    }


    private void adminLogin() {
        System.out.println("Please enter your username and password:");
        String username = in.nextLine();
        String password = in.nextLine();
        if(false /*isLoginCorrect(username, password)*/) {
            System.out.println("The username or password is incorrect");
        }
        isLoggedIn = true;
        System.out.println("Login Successful");

    }

    public void deleteReview() {
        //TODO
    }

    public void editReview() {
        //TODO
    }


}
