package mru.game.controller;
import java.util.ArrayList;
import java.util.Scanner;
import mru.game.model.Player;
import mru.game.view.BlackjackInterface;
/**
 * Runs the actual Blackjack game.
 * Handles all the game logic. Handles current player money/wins updates during game play using a local Player object; field data is retrieved after game play to update Player in playerRecords in GameManager.
 */
public class BlackjackGame {
	ArrayList<Card> playerHand = new ArrayList<>();
    ArrayList<Card> dealerHand = new ArrayList<>();
	
   @SuppressWarnings("unused")
   private Player currentPlayer;
   private String playerName;
   private double playerBalance;
   private int playerWins;
   private boolean returningPlayer;
   
   private int playerAces;
   private int dealerAces;
   private boolean hitting = false;  
  
   private CardDeck deck;
   private Scanner input;
   private BlackjackInterface gameDisplay = new BlackjackInterface();
  
   /**
	 * Default constructor, calls no methods.
	 */
   public BlackjackGame() {
   	
   }
   
   /**
    * Initializes game with current player Player object fields, returningPlayer flag, CardDeck, and input scanner.
    * @param player Player object
    * @param returningPlayer boolean, true if returning player, false if new player
    */
   public void initializeGame(Player player, boolean returningPlayer) {
   	this.currentPlayer = player;
   	this.playerName = player.getName();
   	this.playerBalance = player.getBalance();
   	this.returningPlayer = returningPlayer;
   	
    this.deck = new CardDeck();
    this.input = new Scanner(System.in);
   }
   /**
    * Gets balance of current player local Player object in BlackjackGame
    * @return this.playerBalance double
    */
   public double getPlayerBalance() {
   	return this.playerBalance;
   }
   /**
    * Gets wins from current instance of game play for current player local Player object in BlackjackGame
    * @return playerWins int representing wins accrued during current session
    */
   public int getPlayerWins() {
   	return this.playerWins;
   }

   /**
    * Handles the game play loop, loops playing blackjack until player decides they are done
    */
   public void play() {
	   /**
	    * A welcome display for the player
	    */
       gameDisplay.welcomePlayer(this.playerName, this.playerBalance, this.returningPlayer);
       boolean keepPlaying = true;
       
       while (keepPlaying) {
           if (this.playerBalance < 2) {
               System.out.println("You don’t have enough money to play.");
               break;
           }
           double bet = getValidBet();
           playRound(bet);
           gameDisplay.playAgain();
           char again = input.next().toUpperCase().charAt(0);
           keepPlaying = (again == 'Y');
       }
       System.out.println();
       System.out.println("Leaving the Blackjack table...");
   }
   
  /**
   * checks to see if player got blackjack
   * @param playerScore
   * @param dealerScore
   * @return
   */
   private boolean playerGotBlackjack(int playerScore, int dealerScore) {
 	  if (playerScore == 21 && dealerScore == 21) {
           System.out.println("Both got Blackjack. It’s a tie.");
           showDisplay(playerHand, dealerHand);
           return false;
           
       } else if (playerScore == 21) {
    	   showDisplay(playerHand, dealerHand);
           System.out.println("Blackjack! You win 1.5x your bet.");
           return true;
          
           
       } else if (dealerScore == 21) {
    	   showDisplay(playerHand, dealerHand);
           System.out.println("Dealer has Blackjack. You lose.");
           return false;
           
       }
 	  return false;
   }
/**
 * A round of blackjack, either until dealer/player gets 21, a bust or a tie happens
 * @param bet
 */
   private void playRound(double bet) {
	   dealerAces = 0;
	   playerAces = 0;
	   clearHand(playerHand);
	   clearHand(dealerHand);
       // deal cards
       playerHand.add(drawCard());
       dealerHand.add(drawCard());
       playerHand.add(drawCard());
       dealerHand.add(drawCard());

       // check for instant Blackjacks
      if( getHandValuePlayer() == 21) {
    	  System.out.println("You won! $" + bet*1.5);
    	  this.playerBalance += bet * 1.5;
    	  this.playerWins++;
    	  return;
      }

       // player turn
       boolean turn = true;
       while (turn) {
           System.out.println();
           if(playerHand.size() == 2) {
        	   gameDisplay.blackJackStart(playerName);
        	   gameDisplay.showHands(playerHand.get(0).toString(), dealerHand.get(0).toString());
        	   gameDisplay.showHands(playerHand.get(1).toString(), "");
           }   
           System.out.print("Hit (H) or Stand (S)? ");
           char choice = input.next().toUpperCase().charAt(0);
           
           if (choice == 'H') {
        	   hitting = true;
               playerHand.add(drawCard());
               showDisplay(playerHand, dealerHand);
               if( playerGotBlackjack(getHandValuePlayer(), getHandValueDealer())) {
             	  this.playerBalance += bet * 1.5;
             	  this.playerWins++;
             	  return;
               }
    
               if (getHandValuePlayer() > 21) {
            	   
            	   showDisplay(playerHand, dealerHand);
            	   hitting = false;
                   System.out.println("You busted. Dealer wins.");
                   this.playerBalance -= bet;
                   return;
               }
               
           } else if (choice == 'S') {
        	   hitting = false;
               turn = false;

           } else {
               System.out.println("Invalid input.");
           }
       }
       
       // dealer’s turn
       System.out.println();
       System.out.println("Dealer’s turn...");

       while (getHandValueDealer() < 17) {
           dealerHand.add(drawCard());
           if(getHandValueDealer() == 17) {
        	   showDisplay(playerHand, dealerHand);
        	   System.out.println("Dealer stands");
        	   break;
           }
           if (getHandValueDealer() > 21) {
        	   showDisplay(playerHand, dealerHand);
        	   
               System.out.println("Dealer busts! ");
               
               break;
           }
           if (getHandValueDealer() == 21) {
        	   showDisplay(playerHand, dealerHand);
        	   
               System.out.println("Dealer got BlackJack! ");
               this.playerBalance -= bet;
               break;
           }
          
       }
           System.out.println();

           if (getHandValuePlayer() > getHandValueDealer() ) {
        	   showDisplay(playerHand, dealerHand);
               System.out.println("You win! $"+ bet);
               this.playerBalance += bet;
               this.playerWins += 1;
           } else if (getHandValuePlayer() < getHandValueDealer() || getHandValueDealer() == 21) {
        	   showDisplay(playerHand, dealerHand);
               System.out.println("Dealer wins. -$"+ bet);
               this.playerBalance -= bet;
           } else if(getHandValuePlayer() == getHandValueDealer()) {
        	   showDisplay(playerHand, dealerHand); 
               System.out.println("It’s a tie.");
           }
       }
   /**
    * Will notify if bet isnt valid and return if it is valid
    * @return bet amount
    */
   private double getValidBet() {
       double bet = 0;
       boolean valid = false;
       while (!valid) {
           System.out.println();
           System.out.print("Place your bet (min $2, max $" + playerBalance + "): ");
           if (input.hasNextDouble()) {
               bet = input.nextDouble();
               if (bet >= 2 && bet <= playerBalance) {
                   valid = true;
               } else {
                   System.out.println("Invalid bet amount.");
               }
           } else {
               System.out.println("Enter a number, please.");
               input.next();
           }
       }
       return bet;
   }
   /**
    * draws a card from the cardDeck class
    * @return Card object
    */
   private Card drawCard() {
       if (deck.getDeck().isEmpty()) {
           System.out.println();
           System.out.println("Deck’s empty, reshuffling...");
           deck = new CardDeck();
       }
       return deck.getDeck().remove(0);
   }
   /**
    * clears the "hand" of whichever card arraylist specified in param
    * @param hand
    */
   private void clearHand(ArrayList<Card> hand) {
	   while(hand.size()!= 0) {
		   hand.remove(0);
	   }
   }

   
   /**
    * calculates total hand value for player
    * @return total hand value for player
    */
   private int getHandValuePlayer() {
	   int total = 0;
	   
	   	for(Card c: playerHand) {
	   		if(c.getRank() > 10) {
	   			if( c.toString().contains("King") || c.toString().contains("Queen") ||  c.toString().contains("Jack")) {
	   				total +=10;
	   				
		   		}
	   			if(c.getRank() == 1) {
	   				playerAces++;
	   				total +=11;
	   			}
	   		}else {
	   		
	   			total += c.getRank();
	   		}
	   		if(total == 21 && playerAces == 1) {
		   		return total;
		   	}
	   		else {
			   	while (total > 21 && playerAces > 0) {
			   		total -= 10;
			   		playerAces--;
			   	}
	   		}
	   	}
	   	
	   	return total;
   }
   
   /**
    * calculates dealers current hand value 
    * @return total hand value for dealer
    */
   private int getHandValueDealer() {
	   int total = 0;
	   
	   	for(Card c: dealerHand) {
	   		if(c.getRank() > 10) {
	   			if( c.toString().contains("King") || c.toString().contains("Queen") ||  c.toString().contains("Jack")) {
	   				total +=10;
	   				
		   		}
	   			if(c.getRank() == 1) {
	   				dealerAces++;
	   				total +=11;
	   			}
	   		}else {
	   		
	   			total += c.getRank();
	   		}
	   		if(total == 21 && dealerAces == 1) {
		   		return total;
		   	}
	   		else {
			   	while (total > 21 && dealerAces > 0) {
			   		total -= 10;
			   		dealerAces--;
			   	}
	   		}
	   	}
	   	
	   	return total;
   }
   
   /**
    * takes in the current hands notices if player card or dealer cards should be shown and passes string values to blackjackinterface class to display on console with correct values
    * @param playerHand
    * @param dealerHand
    */
   public void showDisplay(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
	   gameDisplay.blackJackStart(playerName);
	   
	   if(hitting) {
		   for(Card p: playerHand) {
			   if(playerHand.indexOf(p)== 0) {
				   gameDisplay.showHands(p.toString(), dealerHand.get(0).toString());
			   }
			   else {
				   gameDisplay.showHands(p.toString(), "");
			   }
		   }
	   }
	   else {
		   if(playerHand.size() < dealerHand.size()) {
			   for(Card d : dealerHand) {
				   if(dealerHand.indexOf(d) < playerHand.size()) { 
					   gameDisplay.showHands(playerHand.get(dealerHand.indexOf(d)).toString(), d.toString());
				   }
				   else {
					   gameDisplay.showHands("", d.toString());
				   }
			   }
			   
		   }else  if(playerHand.size() > dealerHand.size()) {
			   for(Card p : playerHand) {
				   if(playerHand.indexOf(p) < dealerHand.size()) { 
					   gameDisplay.showHands(p.toString(), dealerHand.get(playerHand.indexOf(p)).toString());
				   }
				   else {
					   gameDisplay.showHands(p.toString(),"");
				   }
			   }
		   }else {
			   for(Card p : playerHand) {
				   
					   gameDisplay.showHands(p.toString(), dealerHand.get(playerHand.indexOf(p)).toString());
			   }
		   }
	   }   
   }
  }

