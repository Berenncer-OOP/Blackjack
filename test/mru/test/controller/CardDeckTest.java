package mru.test.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import mru.game.controller.CardDeck;
import mru.game.controller.Card;

class CardDeckTest {

	    @Test
	    void testDeckHas52CardsAtStart() {
	        CardDeck deck = new CardDeck();
	        assertEquals(52, deck.getDeck().size());
	    }

	    @Test
	    void testShuffleChangesOrder() {
	        CardDeck deck = new CardDeck();
	        List<Card> before = List.copyOf(deck.getDeck());
	        deck.shuffleDeck();
	        List<Card> after = deck.getDeck();
	        assertNotEquals(before, after);
	    }

	    @Test
	    void testDealingReducesDeckSize() {
	        CardDeck deck = new CardDeck();
	        deck.getDeck().remove(0);
	        assertEquals(51, deck.getDeck().size());
	    }
}
