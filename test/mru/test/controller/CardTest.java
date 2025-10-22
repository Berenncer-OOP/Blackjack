package mru.test.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.game.controller.Card;

class CardTest {
	
	@Test
    void testCardSuitAndRank() {
        Card c = new Card(1, "Hearts");
        assertEquals("Hearts", c.getSuit());
        assertEquals(1, c.getRank());
    }

    @Test
    void testFaceCardValues() {
    	Card ace = new Card(1, "Hearts");
        Card jack = new Card(11, "Spades");
        Card queen = new Card(12, "Clubs");
        Card king = new Card(13, "Diamonds");

        assertEquals(1, ace.getRank());
        assertEquals(11, jack.getRank());
        assertEquals(12, queen.getRank());
        assertEquals(13, king.getRank());
    }

    @Test
    void testToString() {
        Card ace = new Card(1, "Hearts");
        assertTrue(ace.toString().contains("Ace") || ace.toString().contains("1"));
    }

}
