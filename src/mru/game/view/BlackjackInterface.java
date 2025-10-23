package mru.game.view;

import java.util.Scanner;

import mru.game.model.Player;

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
        System.out.println(" " + rowSection(" ", 9) +"- BlackJack -"+ rowSection(" ", 9) + " ");
        System.out.println("+" + rowSection("=", 15) +"+"+ rowSection("=", 15) + "+");
        System.out.println("||" + playerName + rowSection(" ", 14-playerName.length()) + "|" +  "Dealer"+ rowSection(" ",8 )+ "||" );
        System.out.println(intersection + rowSection(headerRow, cardFieldLength) + intersection + rowSection(headerRow, cardFieldLength) + intersection);
    }
    
    /**
     * Display to show the dealer and player's hands
     * @param pHand String naming card in player's hand
     * @param dHand String naming card in dealer's hand
     */
    public void showHands(String pHand, String dHand) {
        System.out.println(column+column 
        + pHand + rowSection(" ", cardFieldLength - pHand.length()) 
        + column
        + dHand+ rowSection(" ", cardFieldLength - dHand.length())
        + column+column );
        System.out.println(intersection + rowSection(row, cardFieldLength) 
        + intersection + rowSection(row, cardFieldLength) + intersection);
    }

    
}
