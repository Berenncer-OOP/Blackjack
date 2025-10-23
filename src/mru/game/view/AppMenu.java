package mru.game.view;

import java.util.*;

/**
 * This class shows the menus and sub menus to the user.
 * It also prompts the user for the inputs and validates them.
 */
public class AppMenu {
	Scanner kInput = new Scanner(System.in);

	/**
	 * displays Main Menu options and accepts and validates user input to pass to Game Manager
	 * @return 'P' for Play Game, 'S' for Search, 'E' for Exit Game. 
	 */
	public char mainMenu() {
		char option;
		//print mainMainDisplay, perform input validation. if option == 0, then input was invalid; repeat input.
		mainMenuDisplay();
		do {
			option = mainMenuValidation(kInput.nextLine().charAt(0));
		} while (option == '0');
		return option;
	}
	
	/**
	 * Prints Main Menu to display user options to play game, search, or exit.
	 */
	public void mainMenuDisplay() {
		System.out.println("Select one of these options:");
		System.out.println();
		System.out.println("\t(P) Play Game");
		System.out.println("\t(S) Search");
		System.out.println("\t(E) Exit");
		System.out.println();
		System.out.println("Enter a choice:");
	}
	
	/**
	 * Performs validation of user input for Main Menu options
	 * @param option character accepted as input
	 * @return char validatedInput: uppercase characters 'P', 'S', or 'E' for valid selection options; else returns '0' 
	 */
	public char mainMenuValidation(char option) {
		char validatedOption = '0';
		
		switch (option) {
		case 'P', 'p':
			validatedOption = 'P';
			break;
		case 'S', 's':
			validatedOption = 'S';
			break;
		case 'E', 'e':
			validatedOption = 'E';
			break;
		default:
			System.out.println("That was not a valid option! Please try again: ");
		}
		return validatedOption;
	}
	
	/**
	 * displays Search Menu options and accepts and validates user input to pass to Game Manager
	 * @return 'T' for Top Player search, 'N' for name search, 'B' for back to Main Menu. 
	 */
	public char searchMenu() {
		char option;
		searchMenuDisplay();
		do {
			option = searchMenuValidation(kInput.nextLine().charAt(0));
		} while (option == '0');
		return option;	
	}
	
	/**
	 * Prints Search Menu to display user options to look up top players (T), a specific player by name (N), or go back to main menu (M).
	 * See PlayerReports class for views used to display reports when T or N is selected.
	 */
	public void searchMenuDisplay() {
		System.out.println("Select one of these options:");
		System.out.println("\t(T) Top Player (Most number of wins)"); 
		System.out.println("\t(N) Looking for name");
		System.out.println("\t(B) Back to Main Menu");
		System.out.println();
		System.out.println("Enter a choice: ");
	}
	
	/**
	 * Performs validation of user input for Search Menu options
	 * @param option character accepted as input
	 * @return char validatedInput: uppercase characters 'T', 'N', or 'B' for valid selection options; else returns '0' 
	 */
	public char searchMenuValidation(char option) {
		char validatedOption = '0';
		
		switch (option) {
		case 'T', 't':
			validatedOption = 'T';
			break;
		case 'N', 'n':
			validatedOption = 'N';
			break;
		case 'B', 'b':
			validatedOption = 'B';
			break;
		default:
			System.out.println("That was not a valid option! Please try again: ");
		}
		return validatedOption;
	}
	
	/**
	 * Prints "Saving..." message to console.
	 */
	public void fileSaving() {
		System.out.println("Saving...");
	}
	
	/**
	 * Prints message telling user file has saved.
	 */
	public void fileSaved() {
		System.out.println("Done! Please visit us again!");
	}

}
