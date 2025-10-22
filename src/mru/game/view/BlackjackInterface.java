package mru.game.view;

import java.util.Scanner;

import mru.game.model.Player;

public class BlackjackInterface extends InterfaceComponents {
	InterfaceComponents ic = new InterfaceComponents();
    Scanner kInput = new Scanner(System.in);
    
    private int specialRowLength = 75;
    private int welcomeMessageLength = 23;
    private int nameFieldLength = 11;
    private int balanceMessageLength = 33;
    
    private String welcomeReturningPlayer = "Welcome back ";
    private String balanceReturningPlayer = "Your balance is: $";
    private String welcomeNewPlayer = "Welcome ";
    private String balanceNewPlayer = "Your initial balance is: $";

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
    
    public void blackJackStart(String playerName) {
        System.out.println(" " + ic.rowSection(" ", 9) +"- BlackJack -"+ ic.rowSection(" ", 9) + " ");
        System.out.println("+" + ic.rowSection("=", 15) +"+"+ ic.rowSection("=", 15) + "+");
        System.out.println("||" + playerName +ic.rowSection(" ", 14-playerName.length()) + "|" +  "Dealer"+ ic.rowSection(" ",8 )+ "||" );
        System.out.println("+" + ic.rowSection("=", 15) +"+"+ ic.rowSection("=", 15) + "+");
    }
    
    public void showHands(String pHand, String dHand) {
        System.out.println("||" + pHand +ic.rowSection(" ", 14-pHand.length()) + "|" + dHand+ ic.rowSection(" ",15-dHand.length())+ "||" );
        System.out.println("+" + ic.rowSection("-", 15) +"+"+ ic.rowSection("-", 16) + "+");
    }

    
}
