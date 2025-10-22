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
	

	
	public GameManager() throws IOException {
		//commenting out for now because not working and need to troubleshoot with Spencer:
		fileSetup();
		mainMenuController();
		
		
		
//		setUpPlayer("Bernard");
//		createPlayerRecord("Bernard", 100, 0);
//		createPlayerRecord("Fenna", 100, 0);
//		createPlayerRecord("Spencer", 100, 0);
//		System.out.println(findPlayer("fenna"));
//
//
//		playerReports.playerSearchDisplay(findPlayer("Fenna"));
//		playerReports.playerSearchDisplay(playerRecords.get(3));
		mainMenuController();
	}
		
		
	//On startup check if file exists: 
	public void fileSetup() throws IOException {
		casinoInfo = new File(FILE_PATH);
		if (casinoInfo.exists()) {
			loadFileData(casinoInfo);
		} else {
			casinoInfo.createNewFile();
		}
	}
	
	//load from file to playerRecords
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
	 * Controls program flow using validated output of appMenu.mainMenu() user input. 
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
			search();
			break;
		case 'E':
			saveAndExit();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + option);
		}
		
	}
	
	private void search() throws IOException {
		char option = appMenu.searchMenu();
		
		switch (option) {
		
		case 'T': {
			ArrayList<Player> topPlayers = findTopPlayers();
			playerReports.topPlayersSearchDisplay(topPlayers);
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
			playerReports.playerSearchDisplay(player);
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
	
	public void playGame() throws IOException {
		BlackjackGame game = new BlackjackGame();
		BlackjackInterface gameDisplay = new BlackjackInterface();
		String playerName = gameDisplay.promptName();
    	Player currentPlayer = setUpPlayer(playerName);
		game.initializeGame(currentPlayer);
		game.play();
		currentPlayer.setBalance(game.getPlayerBalance());
		currentPlayer.setNumberOfWins(game.getPlayerWins());
		mainMenuController();
	}
	
	private void saveAndExit() throws IOException {
		PrintWriter saveFile = new PrintWriter(casinoInfo);
		for(Player p: playerRecords) {
			saveFile.println(p.format());
		}
		saveFile.close();
	}
	
	
	

	
	
//playerRecords management	

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
	
	//add player to playerRecords
	public Player createPlayerRecord(String name, double balance, int wins) {
		Player currentPlayer = new Player(name, balance, wins);
		playerRecords.add(currentPlayer);
		return currentPlayer;
	}
	
	
	// setting up current player when BlackjackGame starts, passing player info back to BlackjackGame.
	// only passes back balance because balance can go up or down, numOfWins will only be added to after gameplay ends
	public Player setUpPlayer(String name) {
		Player currentPlayer = findPlayer(name);
		if (currentPlayer != null) {
			return currentPlayer;
		}  else {
			currentPlayer = createPlayerRecord(name, STARTING_BALANCE, STARTING_WINS);
		}
		return currentPlayer;
	}
	
	

	
	public ArrayList<Player> findTopPlayers() {
		ArrayList<Player> topPlayers = new ArrayList<Player>(3);
		int listLength = 3;
		
		for (int i = 0; i < listLength; i++) {
			Player topPlayer = playerRecords.get(i);
			topPlayers.add(topPlayer);
		}
		
		for (Player player : playerRecords) {
			for (int i = 0; i < topPlayers.size(); i++) {
				int topWins = topPlayers.get(i).getNumberOfWins();
				if (player.getNumberOfWins() > topWins){
					topPlayers.add(i, player);
				}
			}
		}
		
		return topPlayers;
	}

}