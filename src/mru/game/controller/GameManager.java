package mru.game.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import mru.game.model.Player;

public class GameManager {
	
	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */
	//related to: issue #13, design
	
	private ArrayList<Player> playerRecords = new ArrayList<Player>();
	private final double STARTING_BALANCE = 100.00;
	private final int STARTING_WINS = 0;

	
	public GameManager() throws IOException {
		//fileSetup();
		
		//testing playerRecords ArrayList:
		createPlayerRecord("Bernard", 100, 0);
		createPlayerRecord("Fenna", 100, 0);
		createPlayerRecord("Spencer", 100, 0);
		System.out.println(findPlayer("fenna"));
		BlackjackGame game = new BlackjackGame(setUpPlayer("fenna"));
		}
		
		
	
	public void fileSetup() throws IOException {
		final String FILE_PATH = "res/CasinoInfo.txt";
		File casinoInfo = new File(FILE_PATH);
		if (casinoInfo.exists()) {
			loadFileData();
		} else {
			casinoInfo.createNewFile();
		}
	}
	
	public void loadFileData() {
		//load 
	}
	
	public void createPlayerRecord(String name, double balance, int wins) {
		Player currentPlayer = new Player(name, balance, wins);
		playerRecords.add(currentPlayer);
}
	
	// setting up current player when BlackjackGame starts, passing player info back to BlackjackGame.
	// only passes back balance because balance can go up or down, numOfWins will only be added to after gameplay ends
	public Player setUpPlayer(String name) {
		Player currentPlayer = findPlayer(name);
		if (currentPlayer != null) {
			return currentPlayer;
		}  else {
			createPlayerRecord(name, STARTING_BALANCE, STARTING_WINS);
		}
		return currentPlayer;
	}
	
	
	// find player by name in players AL; return index location of player in AL
	// use for player set up at game start
	// also use for search submenu
	// if return value == -1, player was not found
	public Player findPlayer(String name) {
		String targetPlayer = new String(name);
		
		Player searchResult = null;
		
		for (Player player : playerRecords) {
			String playerName = new String(player.getName());
			if (playerName.equalsIgnoreCase(targetPlayer)) {
				searchResult = player;
			} 
		}
		return searchResult;
	}
	

}