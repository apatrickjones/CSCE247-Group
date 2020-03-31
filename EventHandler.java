import java.util.Scanner;

public class EventHandler {
	//I decided to design the EventHandler similar to the MainDriver, kind of "sub-menu". Using the same structure as the main driver to increase efficiency and call methods effectively
	private Scanner scanner;
	
	EventHandler() {
		scanner = new Scanner(System.in);
	}
	
	public void run(String menuType) {
		
		while(true) {
			printMenuArray(getTargetMenu(menuType));
			
			int userCommand = getUserCommand(getTargetMenu(menuType).length);
			
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
			}
			
			//if they picked the last option then log them out
			if(userCommand == getTargetMenu(menuType).length -1) break;
		
			switch(userCommand) {
				case(0):
					//Insert a command for the menu being display EX: purchaseTickets();
					break;
				case(1):
					//checkoutItem();
					break;
				case(2):
					//rateItem();
					break;
				case(3):
					//payFine();
					break;
			}
		}
	}
	
	public String[] getTargetMenu(String menuType) {
		
		String[] menuArray = null;
		
		if(menuType.equalsIgnoreCase("payment")) {
			
			String[] paymentOptions = {"Buy Ticket(s)", "Request Refund", "Exit to main menu"};
			menuArray = paymentOptions;
		} 
		// ^ use this format to create diffrent sub-menues to be selected
		
		return menuArray;
	}
	
	public void printMenuArray(String[] menuOptions) {
		for(int i=0; i< menuOptions.length; i++) {
			System.out.println((i+1) + ". " + menuOptions[i]);
		}
		System.out.println("\n");
	}
	
	private int getUserCommand(int numCommands) {
		System.out.print("What would you like to do?: ");
		
		String input = scanner.nextLine();
		int command = Integer.parseInt(input) - 1;
		
		if(command >= 0 && command <= numCommands -1) return command;
		
		return -1;
	}
	

}
