package mru.game.view;

import java.util.Scanner;

/**
 * View class to display Blackjack gameplay.
 */
public class BlackjackInterface extends InterfaceComponents {
	InterfaceComponents ic = new InterfaceComponents();
    Scanner kInput = new Scanner(System.in);
    
    private int specialRowLength = 75;
    private int welcomeMessageLength = 23;
    private int balanceMessageLength = 33;
    
    private int cardFieldLength = 16;
    
    private String welcomeReturningPlayer = "Welcome back ";
    private String balanceReturningPlayer = "Your balance is: $";
    private String welcomeNewPlayer = "Welcome ";
    private String balanceNewPlayer = "Your initial balance is: $";

    /**
     * Displays a welcome message for new or returning players.
     * @param playerName String of player's name
     * @param playerBalance double of player's balance
     * @param returningPlayer true if returning, false if new player
     */
    public void welcomePlayer(String playerName, double playerBalance, boolean returningPlayer) {
    	System.out.println();
    	System.out.println(rowSection(specialRow, specialRowLength));
    	
    	if (returningPlayer) {
    		String playerSpacing =  addSpacing(welcomeMessageLength, welcomeReturningPlayer.length() + playerName.length());
    		String balanceSpacing = addSpacing(balanceMessageLength, balanceReturningPlayer.length() + Double.toString(playerBalance).length());
    		
    		System.out.println(rowSection(specialRow, 3) + rowSection(" ", 3)
    		+ welcomeReturningPlayer
    		+ playerName.toUpperCase() + playerSpacing
    		+ rowSection(row, 3) + rowSection(" ", 4)
    		+ balanceReturningPlayer
    		+ playerBalance + balanceSpacing
    		
    		+ rowSection(" ", 3) + rowSection(specialRow, 3)
    		);
    	} else {
    		String playerSpacing =  addSpacing(welcomeMessageLength, welcomeNewPlayer.length() + playerName.length());
    		String balanceSpacing = addSpacing(balanceMessageLength, balanceNewPlayer.length() + Double.toString(playerBalance).length());
    		
    		System.out.println(rowSection(specialRow, 3) + rowSection(" ", 3)
    		+ welcomeNewPlayer
    		+ playerName.toUpperCase() + playerSpacing
    		+ rowSection(row, 3) + rowSection(" ", 4)
    		+ balanceNewPlayer
    		+ playerBalance +  balanceSpacing
    		+ rowSection(" ", 3) + rowSection(specialRow, 3)
    		);
    	}
    	System.out.println(rowSection(specialRow, specialRowLength));
    }
    
    /**
     * Display for when a round of Blackjack starts
     * @param playerName String of player's name
     */
    public void blackJackStart(String playerName) {
        System.out.println(" " + rowSection(" ", 10) +"- BlackJack -"+ rowSection(" ", 10) + " ");
        System.out.println("+" + rowSection("=", cardFieldLength) +"+"+ rowSection("=", cardFieldLength) + "+");
        System.out.println("||" + playerName + rowSection(" ", cardFieldLength-1-playerName.length()) + "|" +  "Dealer"+ rowSection(" ",cardFieldLength-7 )+ "||" );
        System.out.println(intersection + rowSection(headerRow, cardFieldLength) + intersection + rowSection(headerRow, cardFieldLength) + intersection);
    }
    
    /**
     * Display to show the dealer and player's hands
     * @param pHand String naming card in player's hand
     * @param dHand String naming card in dealer's hand
     */
    public void showHands(String pHand, String dHand) {
        System.out.println(column+column 
        + pHand + rowSection(" ", cardFieldLength - 1 - pHand.length()) 
        + column
        + dHand+ rowSection(" ", cardFieldLength - 1 - dHand.length())
        + column+column );
        System.out.println(intersection + rowSection(row, cardFieldLength) 
        + intersection + rowSection(row, cardFieldLength) + intersection);
    }
    
    /**
     * Insufficient funds to play message
     */
    public void insufficientFunds() {
    	System.out.println("You don’t have enough money to play.");
    }

    /**
     * Play another round prompt
     */
	public void playAgain() {
		System.out.println();
        System.out.print("Play another round? (Y/N): ");		
	}

	/**
	 * Leaving game message
	 */
	public void leavingTable() {
		System.out.println();
        System.out.println("Leaving the Blackjack table...");		
	}
	
	/**
	 * Tie message
	 */
	public void bothBlackjack() {
		System.out.println("Both got Blackjack. It’s a tie.");
	}

	/**
	 * Player instant Blackjack win message
	 */
	public void playerInstantBlackjack() {
		System.out.println("Blackjack! You win 1.5x your bet.");		
	}
	

	/**
	 * Dealer Blackjack win message
	 */
	public void dealerBlackjack() {
		System.out.println("Dealer has Blackjack. You lose.");		
	}
	/**
	 * Player regular Blackjack win message
	 */
	public void playerBlackjack() {
		System.out.println("Blackjack! You win!");		
	}

	/**
	 * Player regular win message
	 */
	public void playerWin() {
        System.out.println("You win!");
		
	}
	/**
	 * Dealer regular win message
	 */
	public void dealerWin() {
		System.out.println("Dealer wins.");		
	}

	/**
	 * Regular tie message
	 */
	public void tie() {
        System.out.println("It’s a tie.");
		
	}

    
    
}
