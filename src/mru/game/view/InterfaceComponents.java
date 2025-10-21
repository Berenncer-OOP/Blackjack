package mru.game.view;

import java.util.Scanner;

/**
 * This is a superclass providing interface component fields and methods to create display rows
 */
public class InterfaceComponents {
	Scanner kInput = new Scanner(System.in);
	
	//display characters: 
		String intersection = "+";
		String column = "|";
		String headerRow = "=";
		String row = "-";
		String specialRow = "*";
		
		/**
		 * Prompts player for their name. Used to pass a name to GameManager to perform findPlayer()
		 * @return playerName as a String
		 */
		public String promptName() {
			System.out.print("What is your name: ");
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
