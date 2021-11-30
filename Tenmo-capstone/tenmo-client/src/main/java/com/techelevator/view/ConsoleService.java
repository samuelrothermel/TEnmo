package com.techelevator.view;


import com.techelevator.tenmo.model.Transfer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConsoleService {

	private PrintWriter out;
	private Scanner in;

	public ConsoleService(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	public String getUserInput(String prompt) {
		out.print(prompt+": ");
		out.flush();
		return in.nextLine();
	}

	public Integer getUserInputInteger(String prompt) {
		Integer result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not valid ***" + System.lineSeparator());
			}
		} while(result == null);
		return result;
	}

//	public Transfer promptForTransferData() {
//		return promptForTransferData(null);
//	}
//
//	public Transfer promptForTransferData(Transfer existingTransfer) {
//		Transfer newTransfer = null;
//		while (newTransfer == null) {
//			System.out.println("--------------------------------------------");
//			System.out.println("Enter transfer data as a comma separated list containing:");
//			System.out.println("Hotel ID, Full Name, Checkin Date, Checkout Date, Number of Guests");
//			if (existingReservation != null) {
//				System.out.println(existingReservation);
//			} else {
//				System.out.println("Example: 1, John Smith, 10/10/2020, 10/14/2020, 2");
//			}
//			System.out.println("--------------------------------------------");
//			System.out.println();
//			newReservation = makeReservation(scanner.nextLine());
//			if (newReservation == null) {
//				System.out.println("Invalid entry. Please try again.");
//			}
//		}
//		if (existingReservation != null) {
//			newReservation.setId(existingReservation.getId());
//		}
//		return newReservation;
//	}
//
//	private Transfer makeTransfer(String csv) {
//		Transfer transfer = null;
//		String[] parsed = csv.split(",");
//		if (parsed.length == 5) {
//			try {
//				transfer = new Transfer();
//				transfer.setTransferTypeId(2);
//				transfer.setTransferStatusId(2);
//				transfer.setAccountFrom(userService.get);
//				reservation.setCheckoutDate(parsed[3].trim());
//				reservation.setGuests(Integer.parseInt(parsed[4].trim()));
//			} catch (NumberFormatException e) {
//				reservation = null;
//			}
//		}
//		return reservation;
//	}


}
