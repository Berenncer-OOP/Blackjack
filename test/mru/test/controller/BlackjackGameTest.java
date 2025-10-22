package mru.test.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mru.game.controller.BlackjackGame;
import mru.game.controller.Card;

class BlackjackGameTest {

    @Test
    void testHandValueWithoutAce() {
        BlackjackGame game = new BlackjackGame();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(10, "Hearts"));
        hand.add(new Card(5, "Spades"));
        assertEquals(15, game.getHandValue(hand));
    }

    @Test
    void testHandValueWithAceAs11() {
        BlackjackGame game = new BlackjackGame();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(1, "Hearts"));
        hand.add(new Card(9, "Spades"));
        assertEquals(20, game.getHandValue(hand));
    }

    @Test
    void testHandValueWithAceAdjustedTo1() {
        BlackjackGame game = new BlackjackGame();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(1, "Hearts"));
        hand.add(new Card(9, "Spades"));
        hand.add(new Card(5, "Clubs")); // Would bust if Ace stays 11
        assertEquals(15, game.getHandValue(hand));
    }

   
}
