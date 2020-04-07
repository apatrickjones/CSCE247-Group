/*
 * @Author Team ME
 * This class is handling all of the event properties
 *
 */
import java.util.Scanner;

public class EventHandler {
	//I decided to design the EventHandler similar to the MainDriver, kind of "sub-menu". Using the same structure as the main driver to increase efficiency and call methods effectively
	private Scanner scanner;

	EventHandler() {
		scanner = new Scanner(System.in);
	}

	/*
	* This method is allowing the user to choose different commands
	* @param menuType can be multiple different options for the user to choose from
	*/
	public void run(String menuType) {

		while(true) {
			printMenuArray(getTargetMenu(menuType));

			int userCommand = getUserCommand(getTargetMenu(menuType).length);

			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
			}

			if (menuType.equalsIgnoreCase("payment")) {
				switch(userCommand) {
					case(0):
						System.out.println("Proceding to Ticket Selection");
						//Insert a command for the menu being display EX: purchaseTickets();
						break;
					case(1):
						System.out.println("Call our toll free number for refund support! +1 (800) 123-4567");
						//checkoutItem();
						break;
					case(2):
						System.out.println("Returning to Main Menu");
						//rateItem();
						break;
					case(3):
						//payFine();
						break;
					case(4):
						//Back to Main Menu
						break;
				}
			} else if (menuType.equalsIgnoreCase("displayEvents")) {
				switch(userCommand) {
				case(0):
					System.out.println("Displaying All Events");
					//View All Events
					break;
				case(1):
					System.out.println("Displaying All Movies");
					//View Movies
					break;
				case(2):
					System.out.println("Displaying All Plays");
					//View Plays
					break;
				case(3):
					System.out.println("Displaying All Concerts");
					//View Concerts
					break;
				case(4):
					System.out.println("Returning to Main Menu");
					//Back to Main Menu
					break;
				}
			} //If you want to add a new sub-menu, add the if and switch statements here

			//if they picked the last option then log them out
			if(userCommand == getTargetMenu(menuType).length -1) break;
		}
	}

	/*
	* This method is controlling the payment command
	* @param menuType can be multiple differnt options for the user to choose from
	*/
	public String[] getTargetMenu(String menuType) {

		String[] menuArray = null;

		if (menuType.equalsIgnoreCase("payment")) {

			String[] paymentOptions = {"Buy Ticket(s)", "Request Refund", "Exit to Main Menu"};
			menuArray = paymentOptions;
		} else if(menuType.equalsIgnoreCase("displayEvents")) {

			String[] eventType = {"View All Events", "View Movies", "View Plays", "View Concerts", "Exit to Main Menu"};
			menuArray = eventType;
		}
		// ^ use this format to create diffrent sub-menues to be selected

		return menuArray;
	}

	/*
	* This method is printing out all of the commands that the user has to choose from
	* @param menuOptions is showing the user all the options
	*/
	public void printMenuArray(String[] menuOptions) {
		for(int i=0; i< menuOptions.length; i++) {
			System.out.println((i+1) + ". " + menuOptions[i]);
		}
		System.out.println("\n");
	}

	/*
	* This method gets the users command that they have inputted
	* @pararm numCommands is the command that the user has inputted
	*/
	private int getUserCommand(int numCommands) {
		System.out.print("What would you like to do?: ");

		String input = scanner.nextLine();
		int command = Integer.parseInt(input) - 1;

		if(command >= 0 && command <= numCommands -1) return command;

		return -1;
	}


}
