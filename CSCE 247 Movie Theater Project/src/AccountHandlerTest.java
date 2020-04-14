import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountHandlerTest {

    AccountHandler ah = AccountHandler.getInstance();

    @Test
    void testHashAccountNormal() {
        ah.hashAccount("employee", "username", "password");
        boolean test = ah.checkMap("username", "password");
        assertTrue(test);
    }
    @Test
    void testHashAccountNull() {
        ah.hashAccount(null, null, null);
        boolean test = ah.checkMap(null, null);
        assertFalse(test);
    }
    @Test
    void testHashAccountEmpty() {
        ah.hashAccount("", "", "");
        boolean test = ah.checkMap("", "");
        assertFalse(test);
    }


    @Test
    void testCheckInfoNormal() {
        ah.hashAccount("general", "username", "password");
        boolean test = ah.checkInfo("username", "password");
        assertTrue(test);
    }
    @Test
    void testCheckInfoNull() {
        boolean test = ah.checkInfo(null, null);
        assertFalse(test);
    }
    @Test
    void testCheckInfoEmpty() {
        boolean test = ah.checkInfo("", "");
        assertFalse(test);
    }


    @Test
    void testDeleteNormal() {
        ah.hashAccount("general", "toberemoved", "toberemoved");
        ah.delete("toberemoved", "toberemoved");
        boolean test = ah.checkMap("toberemoved", "toberemoved");
        assertFalse(test);
    }
    @Test
    void testDeleteEmployee() {
        ah.hashAccount("employee", "toberemoved", "toberemoved");
        ah.delete("toberemoved", "toberemoved");
        boolean test = ah.checkMap("toberemoved", "toberemoved");
        assertFalse(test);
    }
    @Test
    void testDeleteAdmin() {
        ah.hashAccount("admin", "toberemoved", "toberemoved");
        ah.delete("toberemoved", "toberemoved");
        boolean test = ah.checkMap("toberemoved", "toberemoved");
        assertFalse(test);
    }
    @Test
    void testDeleteNonexistent() {
        ah.delete("", "");
        boolean test = ah.checkMap("", "");
        assertFalse(test);
    }
    @Test
    void testDeleteAccountNull() {
        ah.delete(null, null);
        boolean test = ah.checkMap(null, null);
        assertFalse(test);
    }


    @Test
    void testIsUsernameTakenNotTaken() {
        boolean test = ah.isUsernameTaken("user44321");
        assertFalse(test);
    }
    @Test
    void testIsUsernameTakenTaken() {
        ah.hashAccount("general", "nottaken", "password");
        boolean test = ah.isUsernameTaken("nottaken");
        assertTrue(test);
    }
    @Test
    void testIsUsernameTakenNull() {
        boolean test = ah.isUsernameTaken(null);
        assertFalse(test);
    }


    @Test
    void testIsLoginCorrectNormal() {
        ah.hashAccount("admin", "username", "password");
        boolean test = ah.isLoginCorrect("username", "password");
        assertTrue(test);
    }
    @Test
    void testIsLoginCorrectWrong() {
        ah.hashAccount("employee", "username", "password");
        boolean test = ah.isLoginCorrect("uernme", "pswrd");
        assertFalse(test);
    }
    /*
    @Test
    void testIsLoginCorrect() {
    }
    */

}