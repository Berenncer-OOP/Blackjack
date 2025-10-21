package mru.game.controller;

import java.util.ArrayList;
import java.util.Scanner;
import mru.game.model.Player;
import mru.game.view.BlackjackInterface;

/**
 * Runs the actual Blackjack game.
 * Handles all the game logic and money updates.
 */
public class BlackjackGame {
	/**
	 * In this class you implement the game
	 * You should use CardDeck class here
	 * See the instructions for the game rules
	 */
	//related to issue #14, design
	
    private Player player;
    private CardDeck deck;
    private Scanner input;

    public BlackjackGame(Player player) {
        this.player = player;
        this.deck = new CardDeck();
        this.input = new Scanner(System.in);
        play();
    }
    
    public BlackjackGame() {
    	//call bjinterf to display and ask for user
    	//assign to obj
    	//call play
    	
    }
    
    public void initializeGame(Player player) {
    	this.player = player;
        this.deck = new CardDeck();
        this.input = new Scanner(System.in);
    }

    public void play() {
        System.out.println();
        System.out.println("Welcome to Blackjack, " + player.getName() + "!");
        boolean keepPlaying = true;

        while (keepPlaying) {
            if (player.getBalance() < 2) {
                System.out.println("You don’t have enough money to play.");
                break;
            }

            double bet = getValidBet();
            playRound(bet);

            System.out.println();
            System.out.print("Play another round? (Y/N): ");
            char again = input.next().toUpperCase().charAt(0);
            keepPlaying = (again == 'Y');
        }

        System.out.println();
        System.out.println("Leaving the Blackjack table...");
    }

    private void playRound(double bet) {
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();

        // deal cards
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
        playerHand.add(drawCard());
        dealerHand.add(drawCard());

        System.out.println();
        System.out.println("Your cards: " + playerHand);
        System.out.println("Dealer shows: " + dealerHand.get(0));

        int playerScore = getHandValue(playerHand);
        int dealerScore = getHandValue(dealerHand);

        // check for instant Blackjacks
        if (playerScore == 21 && dealerScore == 21) {
            System.out.println("Both got Blackjack. It’s a tie.");
            return;
        } else if (playerScore == 21) {
            System.out.println("Blackjack! You win 1.5x your bet.");
            player.setBalance(bet * 1.5);
            player.setNumberOfWins(1);
            return;
        } else if (dealerScore == 21) {
            System.out.println("Dealer has Blackjack. You lose.");
            player.setBalance(-bet);
            return;
        }

        // player turn
        boolean turn = true;
        while (turn) {
            System.out.println();
            System.out.println("Your hand: " + playerHand + " (Total: " + getHandValue(playerHand) + ")");
            System.out.print("Hit (H) or Stand (S)? ");
            char choice = input.next().toUpperCase().charAt(0);

            if (choice == 'H') {
                playerHand.add(drawCard());
                playerScore = getHandValue(playerHand);
                if (playerScore > 21) {
                    System.out.println("You busted. Dealer wins.");
                    player.setBalance(-bet);
                    return;
                }
            } else if (choice == 'S') {
                turn = false;
            } else {
                System.out.println("Invalid input.");
            }
        }

        // dealer’s turn
        System.out.println();
        System.out.println("Dealer’s turn...");
        System.out.println("Dealer’s hand: " + dealerHand + " (Total: " + dealerScore + ")");
        while (dealerScore < 17) {
            dealerHand.add(drawCard());
            dealerScore = getHandValue(dealerHand);
            System.out.println("Dealer draws. Total: " + dealerScore);
        }

        if (dealerScore > 21) {
            System.out.println("Dealer busts! You win.");
            player.setBalance(bet);
            player.setNumberOfWins(1);
        } else {
            System.out.println();
            System.out.println("Final hands:");
            System.out.println("You: " + playerHand + " (" + playerScore + ")");
            System.out.println("Dealer: " + dealerHand + " (" + dealerScore + ")");

            if (playerScore > dealerScore) {
                System.out.println("You win!");
                player.setBalance(bet);
                player.setNumberOfWins(1);
            } else if (playerScore < dealerScore) {
                System.out.println("Dealer wins.");
                player.setBalance(-bet);
            } else {
                System.out.println("It’s a tie.");
            }
        }
    }

    private double getValidBet() {
        double bet = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println();
            System.out.print("Place your bet (min $2, max $" + player.getBalance() + "): ");
            if (input.hasNextDouble()) {
                bet = input.nextDouble();
                if (bet >= 2 && bet <= player.getBalance()) {
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

    private Card drawCard() {
        if (deck.getDeck().isEmpty()) {
            System.out.println();
            System.out.println("Deck’s empty, reshuffling...");
            deck = new CardDeck();
        }
        return deck.getDeck().remove(0);
    }

    private int getHandValue(ArrayList<Card> hand) {
        int total = 0;
        int aces = 0;

        for (Card c : hand) {
            int rank = c.getRank();
            if (rank > 10) total += 10;
            else if (rank == 1) {
                total += 11;
                aces++;
            } else total += rank;
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }
}