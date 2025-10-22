package mru.game.view;

import java.util.Scanner;

public class BlackjackInterface extends InterfaceComponents {
	InterfaceComponents ic = new InterfaceComponents();
    Scanner kInput =new Scanner(System.in);
    
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
    public String promptName() {
        System.out.println("Please put in your name");
        return kInput.nextLine().trim();
    }}
