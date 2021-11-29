package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;
import com.techelevator.view.ConsoleService;

import java.sql.SQLOutput;

public class App {

	private static final String API_BASE_URL = "http://localhost:8080/";
    
    private static final String MENU_OPTION_EXIT = "Exit";
    private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
	private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
	private static final String[] LOGIN_MENU_OPTIONS = { LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
	private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
	private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
	private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS, MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS, MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };

	public static String AUTH_TOKEN = "";
	public static String USERNAME = "";
	public static int ID = 0;
	public static int AcctId = 0;

	private AuthenticatedUser currentUser;
	private ConsoleService console;
	public AuthenticationService authenticationService;

	public UserService userService = new UserService(API_BASE_URL, currentUser);
	public TransferService transferService = new TransferService(API_BASE_URL, currentUser);

	public static void main(String[] args) {
		App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL));
		app.run();
	}

	public App(ConsoleService console, AuthenticationService authenticationService) {
		this.console = console;
		this.authenticationService = authenticationService;
	}

	public void run() {
		System.out.println("*********************");
		System.out.println("* Welcome to TEnmo! *");
		System.out.println("*********************");
		
		registerAndLogin();
		mainMenu();
	}

	private void mainMenu() {
		while(true) {
			String choice = (String)console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
				viewCurrentBalance();
			} else if(MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
				viewTransferHistory();
			} else if(MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
				viewPendingRequests();
			} else if(MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
				sendBucks();
			} else if(MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
				requestBucks();
			} else if(MAIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else {
				// the only other option on the main menu is to exit
				exitProgram();
			}
		}
	}

	private void viewCurrentBalance() {
		System.out.println("Your current account balance is: $" + userService.getBalance(ID));
	}

	private void viewTransferHistory() {
		// TODO Auto-generated method stub
		Transfer[] transfers = transferService.getAllTransfers(ID);
		// Print Transfers
		System.out.println("------------------------------------------");
		System.out.println("Transfers");
		System.out.println("ID				From/To				Amount");
		System.out.println("------------------------------------------");

		String currentTo = "";
		String currentFrom = "";

		for (Transfer a : transfers) {
			System.out.print(a.getTransferId() + "            ");

			if(AcctId == a.getAccountFrom()) {

				currentTo = userService.getUsernameFromAccountId(a.getAccountTo());
				System.out.print("To: " + currentTo);
			} else {
				currentFrom = userService.getUsernameFromAccountId(a.getAccountFrom());
				System.out.print("From: " + currentFrom);
			}
			System.out.println("             $" + a.getAmount());
		}
		System.out.println("-----------");

		//Print details of transfer
		Integer transferDetailsId = console.getUserInputInteger("Enter transfer ID to view details (0 to cancel): ");
		Transfer newTransfer = transferService.getTransferDetails(transferDetailsId);
		System.out.println("-------------------------------------------");
		System.out.println("Transfer Details");
		System.out.println("-------------------------------------------");
		System.out.println("Id: " + newTransfer.getTransferId());
		System.out.println("From: " + userService.getUsernameFromAccountId(newTransfer.getAccountFrom()));
		System.out.println("To: " + userService.getUsernameFromAccountId(newTransfer.getAccountTo()));

		int transferType = newTransfer.getTransferTypeId();
		if(transferType == 1){
			System.out.println("Type: Request");
		}
		else{
			System.out.println("Type: Send");
		}

		int statusType = newTransfer.getTransferStatusId();
		if(statusType == 1){
			System.out.println("Status: Pending");
		}
		else if(statusType == 2){
			System.out.println("Status: Approved");
		}
		else{
			System.out.println("Status: Rejected");
		}

		System.out.println("Amount: $" + newTransfer.getAmount());


	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
		// TODO Auto-generated method stub
		// Select a user to send bucks to
		System.out.println("------------------------------");
		System.out.println("Users");
		System.out.println("ID     Name");
		System.out.println("------------------------------");
		// Gets an array of users
		User[] users = userService.getUsers();
		// Print Names and ID's of users
		for (User a : users) {
			System.out.println(a);
		}
		// console input will retrieve user ID
		// prompt user for amount of $$ they want to send
		Integer typeId = 2;
		Integer statusId = 2;
		Integer tempToId = console.getUserInputInteger("Enter ID of user you are sending to (0 to cancel)");
		Integer ToId = userService.getAccountIdByUserID(tempToId);
		Integer tempUserId = ID;
		Integer FromId = userService.getAccountIdByUserID(tempUserId);
		Double transferAmount = 0.00;
		boolean isValid = false;
		while (!isValid) {
			Integer tempAmount = console.getUserInputInteger("Enter amount");
			if (userService.getBalance(ID) < tempAmount) {
				System.out.println("You do not have enough funds for this transfer.");
				System.out.println("Please enter a new amount.");
			} else {
				transferAmount = tempAmount.doubleValue();
				isValid = true;
			}
		}
		// makes a Transfer object and sends it.
		Transfer newTransfer = new Transfer();
		newTransfer.setTransferTypeId(typeId);
		newTransfer.setTransferStatusId(statusId);
		newTransfer.setAccountFrom(FromId);
		newTransfer.setAccountTo(ToId);
		newTransfer.setAmount(transferAmount);
		transferService.sendBucks(newTransfer);
	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}
	
	private void exitProgram() {
		System.exit(0);
	}

	private void registerAndLogin() {
		while(!isAuthenticated()) {
			String choice = (String)console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
			if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
				register();
			} else {
				// the only other option on the login menu is to exit
				exitProgram();
			}
		}
	}

	private boolean isAuthenticated() {
		return currentUser != null;
	}

	private void register() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
        while (!isRegistered) //will keep looping until user is registered
        {
            UserCredentials credentials = collectUserCredentials();
            try {
            	authenticationService.register(credentials);
            	isRegistered = true;
            	System.out.println("Registration successful. You can now login.");
            } catch(AuthenticationServiceException e) {
            	System.out.println("REGISTRATION ERROR: "+e.getMessage());
				System.out.println("Please attempt to register again.");
            }
        }
	}

	private void login() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) //will keep looping until user is logged in
		{
			UserCredentials credentials = collectUserCredentials();
		    try {
				currentUser = authenticationService.login(credentials);

				// gets new token, username, and id when there's a new login.
				App.AUTH_TOKEN = currentUser.getToken();
				App.USERNAME = currentUser.getUser().getUsername();
				App.ID = currentUser.getUser().getId();
				App.AcctId = userService.getAccountIdByUserID(ID);
			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: "+e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
	}
	
	private UserCredentials collectUserCredentials() {
		String username = console.getUserInput("Username");
		String password = console.getUserInput("Password");
		return new UserCredentials(username, password);
	}
}
