/*
 * This class is running all of the event and showing menus for the user to choose options from
 * @Author: Team Me
 *
 */
import java.util.Scanner;

public class EventDriver {

		private static final String WELCOME_STRING = "Welcome to our event application!";
		private String[] mainMenuOptions = {"View Events","Payment Options","Account Options", "Exit Program"};
		private Scanner scanner;

		EventDriver() {
			scanner = new Scanner(System.in);
		}

		/*
		* This method is the start of the program, prompting the user to input a command
		* @returns if the command is valid
		*/
		public void run() {
			System.out.println(WELCOME_STRING);

			//Will loop as long as we want to keep interacting with the application
			while(true) {
				displayMainMenu();
				int userCommand = getUserCommand(mainMenuOptions.length);

				//getUserCommand will return a -1 if input is invalid
				if(userCommand == -1) {
					System.out.println("Not a valid command");
					continue;
				}

				//if they picked the final command they will be logged out
				if(userCommand == mainMenuOptions.length -1) break;

				switch(userCommand) {
					case(0):
						System.out.println("ENTERING EVENTS SUBMENU...\n\n");
						displayEvents();
						break;
					case(1):
						//call command2 here
						System.out.println("ENTERING PAYMENT SUBMENU...\n\n");
						Payment();
						break;
					case(2):
						//call command3 here -- NOTE: selecting the last command exits the program
						System.out.println("ENTERING ACCOUNT SUBMENU...\n\n");
						accountOptions();
						break;
					case(3):
						//Empty, exits program
						break;
				}
			}

			System.out.println("Goodbye!");
		}

		/*
		* This method is in charge of displaying the main menu
		* @returns the options for the user to choose from
		*/
		private void displayMainMenu() {
			System.out.println("\n************ Main Menu *************");
			for(int i=0; i< mainMenuOptions.length; i++) {
				System.out.println((i+1) + ". " + mainMenuOptions[i]);
			}
			//System.out.println("\n");
		}

		/*
		* This method takes in the users commands
		* @param numCommands seeing which command that the user has inputted.
		*/
		public int getUserCommand(int numCommands) {
			System.out.print("What would you like to do?: ");

			String input = scanner.nextLine();
			int command = Integer.parseInt(input) - 1;

			if(command >= 0 && command <= numCommands -1) return command;

			return -1;
		}

		/*
		* This method is in charge of running the payment command if the user chooses to do so
		* @returns option for the user to enter payment information
		*/
		private void Payment(){
			EventHandler p1 = new EventHandler();
			p1.run("payment");
		}

		/*
		* This method is in charge of displaying the event and the event's properties
		* @returns the event and the properties of the event
		*/
		private void displayEvents() {
			EventHandler p1 = new EventHandler();
			p1.run("displayEvents");
		}

		/*
		* This method is in charge of accessing Account Options such as creating a new Event
		* @returns the event and the properties of the event
		*/
		private void accountOptions() {
			EventHandler p1 = new EventHandler();
			p1.run("account");
		}
		/*
		* This method is in charge of running the user interface
		* @returns the execution of the user interface
		*/
		public static void main(String[] args) {
			EventDriver UserInterface = new EventDriver();
			UserInterface.run();
		}
}
