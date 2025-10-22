package mru.game.view;

import java.util.ArrayList;

import mru.game.model.Player;

public class PlayerReports extends InterfaceComponents{
	
	public boolean returnToMainMenu() {
		System.out.println("Press \"Enter\" to continue...");
		String input = kInput.nextLine();
		if (input == "" ) {
			return true;
		} else {
			return false;
		}
	}
	
	//playerSearch
	public void playerSearchDisplay(Player p) {
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
			System.out.println(intersection + rowSection(headerRow, nameFieldLength) + intersection + rowSection(headerRow, winsFieldLength) + intersection + rowSection(headerRow, balanceFieldLength)+ intersection); //pieces together header row separators
			System.out.println(column + "NAME" + addSpacing(nameFieldLength, 4) + column + "# WINS" + addSpacing(winsFieldLength, 6)  + column + "BALANCE" + addSpacing(balanceFieldLength, 7)+ column);
			
			System.out.println(intersection + rowSection(headerRow, nameFieldLength) + intersection + rowSection(headerRow, winsFieldLength) + intersection + rowSection(headerRow, balanceFieldLength)+ intersection); //pieces together header row separators
			
			System.out.println(column + playerName + nameSpacing + column + playerWins + winsSpacing + column + playerBalance + balanceSpacing + column);
			
			System.out.println(intersection + rowSection(row, nameFieldLength) + intersection + rowSection(row, winsFieldLength) + intersection + rowSection(row, balanceFieldLength)+ intersection); //pieces together header row separators
			
		 }
		
		 else {
			 System.out.println("Player could not be found");
		 }
		
	}
	
	public void topPlayersSearchDisplay(Player[] topPlayers) {
		int nameFieldLength = 18; //length of report fields
		int winsFieldLength = 22; //length of report fields

		if (topPlayers[0] == null) {
			System.out.println("There are no player records to report. Try again later!");
		} else {
			System.out.println("                 - TOP PLAYERS - ");
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
				
				System.out.println(column + playerName + addSpacing(nameFieldLength, playerName.length()) + column + playerWins + addSpacing(winsFieldLength, playerWins.length()) + column);
				System.out.println(intersection + rowSection(row, nameFieldLength) 
				+ intersection + rowSection(row, winsFieldLength) + intersection);
			}

		
		}
		
		
	}
	

}
