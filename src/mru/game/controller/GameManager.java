package mru.game.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.model.Player;
import mru.game.view.*;

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
	
	private final String FILE_PATH = "res/casinoInfo.txt";
	File casinoInfo;
	
	//maybe move these objects?
	private PlayerReports playerReports = new PlayerReports();
	

	
	public GameManager() throws IOException {
		//commenting out for now because not working and need to troubleshoot with Spencer:
		//fileSetup();
		//loadFileData();
		
		AppMenu appMenu = new AppMenu();

		
		//testing playerRecords ArrayList:
		createPlayerRecord("Bernard", 100, 0);
		createPlayerRecord("Fenna", 100, 0);
		createPlayerRecord("Spencer", 100, 0);
		System.out.println(findPlayer("fenna"));
		playerReports.playerSearchDisplay(findPlayer("Fenna"));
		appMenu.mainMenu();
		//BlackjackGame game = new BlackjackGame(setUpPlayer("fenna"));
	}
		
		
	//On startup check if file exists: 
	public void fileSetup() throws IOException {
		File casinoInfo = new File(FILE_PATH);
		if (casinoInfo.exists()) {
			loadFileData();
		} else {
			casinoInfo.createNewFile();
		}
	}
	
	//load from file to playerRecords
	public void loadFileData() throws IOException {
		//load 
		String currentLine;
		String[] splittedLine;
		
		Scanner fileReader = new Scanner(casinoInfo);
		while(fileReader.hasNextLine()) {
			currentLine = fileReader.nextLine();
			splittedLine = currentLine.split(",");
			Player p = new Player(splittedLine[0], Double.parseDouble(splittedLine[1]), Integer.parseInt(splittedLine[2]));
			playerRecords.add(p);
		}
		fileReader.close();
	}
	
	//add player to playerRecords
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
	
	public ArrayList<Player> findTopPlayers() {
		ArrayList<Player> topPlayers = new ArrayList<Player>();
		
		
		
		for(int pr = playerRecords.size(); pr >=0; pr--) {
			
			for(Player tp: topPlayers) {
				
				//initalize topPlayers array so it is not empty
				if(topPlayers.size() == 0 ||topPlayers.size() == 1 || topPlayers.size() == 2) {
					topPlayers.add(playerRecords.get(pr));
					if(topPlayers.size() == 2) {
						if( topPlayers.get(0).getNumberOfWins() > topPlayers.get(1).getNumberOfWins() && topPlayers.get(0).getNumberOfWins() > topPlayers.get(2).getNumberOfWins()) {
								
								//arraylist is in order
								
							}
						//2 & 3rd position > 1
						else if (topPlayers.get(1).getNumberOfWins() > topPlayers.get(0).getNumberOfWins() && topPlayers.get(2).getNumberOfWins() > topPlayers.get(0).getNumberOfWins()) {
							//2>3
							if(topPlayers.get(1).getNumberOfWins() > topPlayers.get(2).getNumberOfWins()) {
								topPlayers.addLast(topPlayers.get(0));
								topPlayers.remove(1);
							}
							//3>2
							else {
								topPlayers.addFirst(topPlayers.get(2));
								topPlayers.addLast(topPlayers.get(1));
								topPlayers.remove(1);
								
							}
						}
						else if(topPlayers.get(1).getNumberOfWins() > topPlayers.get(0).getNumberOfWins() || topPlayers.get(2).getNumberOfWins() > topPlayers.get(0).getNumberOfWins()) {
							if(topPlayers.get(1).getNumberOfWins() > topPlayers.get(2).getNumberOfWins() ) {
								if(topPlayers.get(1).getNumberOfWins() > topPlayers.get(0).getNumberOfWins()) {
									topPlayers.addFirst(topPlayers.get(1));
									topPlayers.remove(2);
								}
							}
							else if(topPlayers.get(1).getNumberOfWins() < topPlayers.get(2).getNumberOfWins() ) {
								if(topPlayers.get(2).getNumberOfWins() > topPlayers.get(0).getNumberOfWins()) {
									topPlayers.addFirst(topPlayers.get(2));
									topPlayers.remove(3);
								}
							}
						}
					}
					break;
				}
				if(topPlayers.get(2).getNumberOfWins() < playerRecords.get(pr).getNumberOfWins()) {
					if(topPlayers.get(1).getNumberOfWins() < playerRecords.get(pr).getNumberOfWins()) {
						if(topPlayers.get(0).getNumberOfWins() < playerRecords.get(pr).getNumberOfWins()) {
							
						}
					}
				}

			}
		}
	}
	

}