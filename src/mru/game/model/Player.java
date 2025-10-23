package mru.game.model;

/**
 * This Model class represents each player record in the .txt file Database
 */
public class Player {
	//related to issue #12, design
	private String name;
	private double balance;
	private int numberOfWins;
	
	/**
	 * Constructor to make Player objects with all fields initialized.
	 * @param name String of player name
	 * @param balance double of starting player balance
	 * @param wins int of starting player wins
	 */
	public Player(String name, double balance, int wins) {
		this.name = name;
		this.balance = balance;
		this.numberOfWins = wins;
	}
	
	/**
	 * Set Player name individually
	 * @param name String player name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set Player balance individually
	 * @param balance double player balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * Set Player wins individually
	 * @param wins int player wins
	 */
	public void setNumberOfWins(int wins) {
		this.numberOfWins = wins;
	}
	
	/**
	 * Player object name getter
	 * @return object's name String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Player object balance getter
	 * @return object's balance double
	 */
	public double getBalance() {
		return this.balance;
	}
	
	/**
	 * Player object wins getter
	 * @return object's numberOfWins int
	 */
	public int getNumberOfWins() {
		return this.numberOfWins;
	}
	
	/**
	 * Formats fields of Player object into a String to be used in the casinoInfo data file
	 * @return "name,balance,numberOfWins"
	 */
	public String format() {
		return name +","+balance+","+numberOfWins;
	}
	
	/**
	 * Adds new wins to existing wins total
	 * @param wins number of new wins to be added to existing wins total
	 */
	public void addWins(int wins) {
		this.numberOfWins += wins;
	}
	
	
}
