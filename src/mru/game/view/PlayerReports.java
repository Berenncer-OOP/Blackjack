package mru.game.view;

import java.util.ArrayList;

import mru.game.model.Player;

public class PlayerReports extends InterfaceComponents{
	
	/**
	 * Checks for and validates user input for option to return to main menu after viewing a report.
	 * @return true If User Presses Enter, false if user presses another character
	 */
	public boolean returnToMainMenu() {
		System.out.println("Press \"Enter\" to continue...");
		String input = kInput.nextLine();
		if (input == "" ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Displays a table with a specific player's statistics. Displays a message if the player cannot be found. 
	 * @param p a player object
	 */
	public void playerSearchReport(Player p) {
		int nameFieldLength = 18; //length of report fields
		int winsFieldLength = 15; 
		int balanceFieldLength = 22; 
		
		if(p != null){
			//player object fields to strings
			String playerName = p.getName();
			String playerWins = String.valueOf(p.getNumberOfWins());
			String playerBalance = String.valueOf(p.getBalance());
			 
			//calculating spaces to add in order to print display correctly
			String nameSpacing = addSpacing(nameFieldLength, playerName.length());
			String winsSpacing = addSpacing(winsFieldLength, playerWins.length());
			String balanceSpacing = addSpacing(balanceFieldLength, playerBalance.length());
			
			//would like to put the row printouts in their own methods but not a priority:
			System.out.println("                 - PLAYER INFO - ");
			
			// header row separator:
			System.out.println(intersection + rowSection(headerRow, nameFieldLength) 
			+ intersection + rowSection(headerRow, winsFieldLength) 
			+ intersection + rowSection(headerRow, balanceFieldLength)+ intersection); 
			
			// column Headings:
			System.out.println(column + "NAME" + addSpacing(nameFieldLength, 4) + column + "# WINS" + addSpacing(winsFieldLength, 6)  + column + "BALANCE" + addSpacing(balanceFieldLength, 7)+ column);
			// header row separator:
			System.out.println(intersection + rowSection(headerRow, nameFieldLength) 
			+ intersection + rowSection(headerRow, winsFieldLength) 
			+ intersection + rowSection(headerRow, balanceFieldLength)+ intersection); 
			
			//Player Information row:
			System.out.println(column + playerName + nameSpacing + column + playerWins + winsSpacing + column + playerBalance + balanceSpacing + column);
			
			// footer Rows Separator:
			System.out.println(intersection + rowSection(row, nameFieldLength) 
			+ intersection + rowSection(row, winsFieldLength) 
			+ intersection + rowSection(row, balanceFieldLength)+ intersection); 
			
		 }
		
		 else {
			 System.out.println("Player could not be found");
		 }
		
	}
	
	/**
	 * Displays a table of top players' names and number of wins.
	 * @param topPlayers performs display operations on an ArrayList of Player objects; use GameManager.findTopPlayers(playerRecords) to create an ArrayList sorted by player wins
	 */
	public void topPlayersReport(ArrayList<Player> topPlayers) {
		int nameFieldLength = 18; //length of report fields
		int winsFieldLength = 22; //length of report fields

		if (topPlayers.get(0) == null) {
			System.out.println("There are no player records to report. Try again later!");
		} else {
			System.out.println("            - TOP PLAYERS - ");
			//upper header row
			System.out.println(intersection + rowSection(headerRow, nameFieldLength) 
			+ intersection + rowSection(headerRow, winsFieldLength) + intersection); 
			//header row text
			System.out.println(column + "NAME" + addSpacing(nameFieldLength, 4) 
			+ column + "# WINS" + addSpacing(winsFieldLength, 6)  + column);
			//bottom header row
			System.out.println(intersection + rowSection(headerRow, nameFieldLength) 
			+ intersection + rowSection(headerRow, winsFieldLength) + intersection); 
			
			for (Player topPlayer : topPlayers) {
				String playerName = topPlayer.getName();
				String playerWins = String.valueOf(topPlayer.getNumberOfWins());
				//GameManager.findTopPlayers() uses players with blank names to initialize the topPlayers AL;
				//So this if statement prints only the rankings that have valid players
				if (playerName != "") {
					System.out.println(column + playerName + addSpacing(nameFieldLength, playerName.length()) 
					+ column + playerWins + addSpacing(winsFieldLength, playerWins.length()) + column);
					System.out.println(intersection + rowSection(row, nameFieldLength) 
					+ intersection + rowSection(row, winsFieldLength) + intersection);
				}
			}
		}
	}
}
