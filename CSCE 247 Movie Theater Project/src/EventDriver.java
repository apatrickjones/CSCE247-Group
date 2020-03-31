/*
 *
 */
import java.util.Scanner;

public class EventDriver {
		
		private static final String WELCOME_STRING = "Welcome to our event application!";
		private String[] mainMenuOptions = {"Payment Options","Command2","Command3"};
		private Scanner scanner;
		
	
	
		EventDriver(){
			scanner = new Scanner(System.in);
			
		}
		
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
						System.out.println("ENTERING PAYMENT SUBMENU...");
						Payment();
						break;
					case(1):
						//call command2 here
						System.out.println("ENTERED COMMAND 2");
						break;
					case(2):
						//call command3 here -- NOTE: selecting the last command exits the program
						System.out.println("ENTERED COMMAND 3");
						break;
				}
			}
			
			System.out.println("Goodbye!");
		}
		
		private void displayMainMenu() {
			System.out.println("\n************ Main Menu *************");
			for(int i=0; i< mainMenuOptions.length; i++) {
				System.out.println((i+1) + ". " + mainMenuOptions[i]);
			}
			//System.out.println("\n");
		}
		
		
		private int getUserCommand(int numCommands) {
			System.out.print("What would you like to do?: ");
			
			String input = scanner.nextLine();
			int command = Integer.parseInt(input) - 1;
			
			if(command >= 0 && command <= numCommands -1) return command;
			
			return -1;
		}
		
		private void Payment(){
			EventHandler p1 = new EventHandler();
			p1.run("payment");
		}
		
		public static void main(String[] args) {
			EventDriver UserInterface = new EventDriver();
			UserInterface.run();
		}
}

