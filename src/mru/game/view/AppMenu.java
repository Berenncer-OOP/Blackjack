package mru.game.view;

import java.util.*;

import mru.game.controller.*;
import mru.game.model.Player;

public class AppMenu {
	Scanner kInput = new Scanner(System.in);

	//display characters: 
	String intersection = "+";
	String column = "|";
	String headerRow = "=";
	String row = "-";
	String specialRow = "*";
	

	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */
	
	/**
	 * Showcases the default menu on console for the player
	 */
	
	//launch appmenu with scanner
//	public AppMenu() {
//		Scanner kInput = new Scanner(System.in);
//		
//	}
	
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
		System.out.println("(P) Play Game");
		System.out.println("(S) Search");
		System.out.println("(E) Exit");
		System.out.println();
		System.out.println("Enter a Choice:");
	}
	
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
	
	//searchMenu
	public char searchMenu() {
		System.out.println("Select one of these options:");
		System.out.println("(T) Top Player (Most number of wins)");
		System.out.println("(N) Looking for name");
		System.out.println("(B) Back to main Menu");
		System.out.println();
		System.out.println("Enter a choice: ");
		char option = kInput.nextLine().charAt(0);
		
		return option;
		
	}
	
	public String promptName() {
		System.out.print("What is your Name: ");
		String playerName = kInput.nextLine().trim();
		
		return playerName;
	}
	
	/**
	 * Makes sections of row separators using a given separator character. Can be used to make sections of row separators, or entire rows if row display uses single character. 
	 * @param rowCharacter the character to use for the row section as a String
	 * @param fieldLength the length of the row section as an int
	 * @return row String of specified length using specified character
	 */
	public String rowSection(String rowCharacter, int fieldLength) {
		String row = new String();
		while (fieldLength > 0) {
			row += rowCharacter;
			fieldLength -= 1;
		}
		return row;
	}
	
	/**
	 * Subtracts length of field content from total field length and returns a spacing String with that number of spaces to create proper field display length.
	 * @param fieldLength total characters in field
	 * @param fieldContentLength characters in field content
	 * @return String with correct amount of spaces
	 */
	public String addSpacing(int fieldLength, int fieldContentLength) {
		String spacing = new String();
		int spacesNeeded = fieldLength - fieldContentLength;
		while (spacesNeeded > 0) {
			spacing+=" ";
			spacesNeeded -= 1;
		}
		return spacing;
	}
	
}
