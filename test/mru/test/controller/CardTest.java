package mru.test.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.game.controller.Card;

class CardTest {

	@Test
	void testSuitAndRank() {
		Card c = new Card(1, "Hearts");
        assertEquals("Hearts", c.getSuit());
        assertEquals(1, c.getRank());
	}

}
