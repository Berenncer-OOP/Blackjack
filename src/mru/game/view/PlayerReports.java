package mru.game.view;

import java.util.ArrayList;

import mru.game.model.Player;

public class PlayerReports extends InterfaceComponents{
	
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
			String balanceSpacing = addSpacing(balanceFieldLength, playerName.length());
			
			
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
	
	public void searchTopPlayersMenu(ArrayList<Player> topPlayers) {
		int nameFieldLength = 18; //length of report fields
		int winsFieldLength = 22; //length of report fields
		
		//
		String topName =  topPlayers.get(0).getName();
		int topWins = topPlayers.get(0).getNumberOfWins();
		int spacing = topName.length();
		String.format(topName);
		//end of messing
		
		System.out.println();
		System.out.println("|"+ topPlayers.get(2).getName()+" " +"|"+topPlayers.get(3).getNumberOfWins()+" " + "+" );
		System.out.println("|"+ topPlayers.get(4).getName()+" " +"|"+topPlayers.get(5).getNumberOfWins()+" " + "+" );
		System.out.println();
		System.out.println("Press "+"Enter"+" to continue");
		
		
	}
	

}
