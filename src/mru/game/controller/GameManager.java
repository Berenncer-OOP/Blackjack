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
	
	private final String FILE_PATH = "res/CasinoInfo.txt";
	private File casinoInfo;
	
	//maybe move these objects?
	private AppMenu appMenu = new AppMenu();
	private PlayerReports playerReports = new PlayerReports();
	

	/**
	 * Constructor for class calls file set up and main menu controller methods
	 * @throws IOException
	 */
	public GameManager() throws IOException {
		fileSetup();
		mainMenuController();
	}
		
		
	/**
	 * Checks if casinoInfo file exists; loads existing file if yes, creates new file if not.
	 * @throws IOException
	 */
	public void fileSetup() throws IOException {
		casinoInfo = new File(FILE_PATH);
		if (casinoInfo.exists()) {
			loadFileData(casinoInfo);
		} else {
			casinoInfo.createNewFile();
		}
	}
	
	/**
	 * Loads lines of file to array list of player records.
	 * @param inputFile .txt file with one record per line in the form "Name,balance,wins".
	 * @throws IOException
	 */
	public void loadFileData(File inputFile) throws IOException {
		//load 
		String currentLine;
		String[] splittedLine;
		
		Scanner fileReader = new Scanner(inputFile);
		while(fileReader.hasNextLine()) {
			currentLine = fileReader.nextLine();
			splittedLine = currentLine.split(",");
			Player p = new Player(splittedLine[0], Double.parseDouble(splittedLine[1]), Integer.parseInt(splittedLine[2]));
			playerRecords.add(p);
		}
		fileReader.close();
	}
	
	/**
	 * Controls program flow from main Menu using validated output of appMenu.mainMenu() user input. 
	 * @throws IOException
	 */
	public void mainMenuController() throws IOException {
		char option;
		
		option = appMenu.mainMenu();
		
		switch(option) {
		case 'P':
			playGame();
			break;
		case 'S':
			searchMenuController();
			break;
		case 'E':
			saveAndExit();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + option);
		}
	}

//Main Menu Options:
	/**
	 * Controls program flow from search Menu using validated output of appMenu.searchMenu() user input. 
	 * @throws IOException
	 */
	private void searchMenuController() throws IOException {
		char option = appMenu.searchMenu();
		
		switch (option) {
		case 'T': {
			ArrayList<Player> topPlayers = findTopPlayers(this.playerRecords);
			System.out.println(playerRecords.get(0).getName());
			playerReports.topPlayersReport(topPlayers);
			boolean returnToMain = false;
			do {
				returnToMain = playerReports.returnToMainMenu();
			} while (returnToMain == false);
			if (returnToMain == true) {
				mainMenuController();
			}
		break;
		}
		case 'N':{
			Player player = findPlayer(playerReports.promptName());
			playerReports.playerSearchReport(player);
			boolean returnToMain = false;
			do {
				returnToMain = playerReports.returnToMainMenu();
			} while (returnToMain == false);
			if (returnToMain == true) {
				mainMenuController();
			}
			break;
		}
		case 'B':{
			mainMenuController();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + option);
		}
	}
	
	/**
	 * Instantiates BlackjackGame and related view and Player objects, passes Player data between BlackjackGame and GameManager at game start and end.
	 * @throws IOException
	 */
	public void playGame() throws IOException {
		BlackjackGame game = new BlackjackGame();
		BlackjackInterface gameDisplay = new BlackjackInterface();
		
		String playerName = gameDisplay.promptName();
		boolean returningPlayer = returningPlayer(findPlayer(playerName));
    	Player currentPlayer = setUpPlayer(playerName);

		game.initializeGame(currentPlayer, returningPlayer);
		game.play();
		currentPlayer.setBalance(game.getPlayerBalance());
		currentPlayer.setNumberOfWins(game.getPlayerWins());
		mainMenuController();
	}
	
	/**
	 * Saves playerRecords back to casinoInfo file and calls AppMenu methods to display visual saving/saved cues to user.
	 * @throws IOException
	 */
	private void saveAndExit() throws IOException {
		appMenu.fileSaving();
		PrintWriter saveFile = new PrintWriter(casinoInfo);
		for(Player p: playerRecords) {
			saveFile.println(p.format());
		}
		saveFile.close();
		appMenu.fileSaved();
	}
	

	
//playerRecords management	

	// find player by name in players AL; return index location of player in AL
	// use for player set up at game start
	// also use for search submenu
	// if return value == -1, player was not found
	/**
	 * Searches playerRecords for a specified player by name
	 * @param name a String specifying the target player's name
	 * @return the relevant Player object if found, a null Player object if not found
	 */
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

	/**
	 * Use before calling createPlayerRecord() to indicate to BlackjackInterface if a Player object is returning or new
	 * @param p a Player object
	 * @return true if p contains existing player data (existing player), false if p is null (new player)
	 */
  	public boolean returningPlayer(Player p) {
  		boolean returningPlayer;
  		if (p != null) {
  			returningPlayer = true;
  		} else {
  			returningPlayer = false;
  		}
  		return returningPlayer;
  	}
	
  	/**
  	 * Create a new player record and add Player to playerRecord ArrayList
  	 * @param name String of player name
  	 * @param balance double of balance to instantiate Player with
  	 * @param wins int of wins to instantiate Player with
  	 * @return Player object of created player
  	 */
	public Player createPlayerRecord(String name, double balance, int wins) {
		Player currentPlayer = new Player(name, balance, wins);
		this.playerRecords.add(currentPlayer);
		return currentPlayer;
	}
	
	
	// setting up current player when BlackjackGame starts, passing player info back to BlackjackGame.
	/**
	 * Calls findPlayer(name) and createPlayerRecord(name,balance,wins) to set up existing and new players when BlackjackGame game starts.
	 * 
	 * @param name accepts a String of the user's name, can be the name of a new OR returning player
	 * @return currentPlayer as Player object, pass this to BlackjackGame.initializeGame()
	 */
	public Player setUpPlayer(String name) {
		Player currentPlayer = findPlayer(name);
		if (currentPlayer != null) {
			return currentPlayer;
		}  else {
			currentPlayer = createPlayerRecord(name, STARTING_BALANCE, STARTING_WINS);
		}
		return currentPlayer;
	}
	
	/**
	 * Iterates over an ArrayList of Player objects to sort them into a new list ranked by wins.
	 * @param playerRecords the ArrayList of all playerRecords stored in GameManager; could be a subset ArrayList of Player objects if desired
	 * @return ArrayList of Player objects sorted by number of wins
	 */
	public ArrayList<Player> findTopPlayers(ArrayList<Player> playerRecords) {
		ArrayList<Player> topPlayers = new ArrayList<Player>();
		int listLength = 5;
		
		for (int i = 0; i < listLength; i++) {
			Player topPlayer = new Player("",0,-1);
			
			topPlayers.add(topPlayer);
		}
		
		//for each player in playerRecords
			//check each spot in topPlayers
			//if player's wins greater than person in spot, insert player in that spot
		for (Player player : playerRecords) {
			for (int i = 0; i < topPlayers.size(); i++) {
				int topWins = topPlayers.get(i).getNumberOfWins();
				if (player.getNumberOfWins() > topWins){
					topPlayers.add(i, player);
					System.out.println(player.getName());
					break;
				}
			}
		}
		
		return topPlayers;
	}

}