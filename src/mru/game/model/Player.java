package mru.game.model;

public class Player {
	
	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */
	//related to issue #12, design
	private String name;
	private double balance;
	private int numberOfWins;
	
	public Player(String name, double balance, int wins) {
		this.name = name;
		this.balance = balance;
		this.numberOfWins = wins;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setNumberOfWins(int wins) {
		this.numberOfWins += wins;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public int getNumberOfWins() {
		return this.numberOfWins;
	}
	
	public String format() {
		return name +","+balance+","+numberOfWins;
	}
}
