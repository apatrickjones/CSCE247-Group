/*
 * @Author: Team ME
 * This class is allowing for a user to create an account and login
 *
 */
public class GeneralUser extends AccountHandler {

    /*
    * This method allows the user to create a new user account
    * @returns if the username is already taken or is available
    */
    public void createNewGeneralAccount() {
        System.out.println("Please create your username:");
        String username = in.nextLine();
        if(isUsernameTaken(username) == true) {
            System.out.println("That username is already taken. Please enter another username:");
            return;
        }
        System.out.println("Please create your password:");
        String password = in.nextLine();
        registerUserGeneral(username, password);
    }

    /*
    * Compares the user's username and password to the hashmap, and logs them into the system if its correct
    * @returns if the username and password is correct or incorrect
    */
    public void generalLogin() {
        System.out.println("Please enter your username and password:");
        String username = in.nextLine();
        String password = in.nextLine();
        if(isLoginCorrect(username, password) != true) {
            System.out.println("The username or password is incorrect");
            return;
        }
        level = 1;
        System.out.println("Login Successful");
    }
    /*
    * Helper methods to enter the user's username and password into the hashmap
    * @param username and password are being stored in a hasmap for accessability
    */
    public void registerUserGeneral(String username, String password) {
        generalMap.put(username, password);
    }









/*
    public String getSuggestedMovies() {    // Getter for suggestedMovies
        return this.suggestedMovies;
    }

    public String getViewingHistory() { //Getter for viewingHistory
        return this.viewingHistory;
    }


    public void checkout(String paymentInformation, String personalInformation) {

    }
*/

}
