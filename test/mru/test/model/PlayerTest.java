package mru.test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.game.model.Player;

class PlayerTest {

	 @Test
	    void testAdjustBalance() {
	        Player p = new Player("Alice", 100, 0);
	        double balance = p.getBalance();
	        balance += 50;
	        p.setBalance(balance);
	        assertEquals(150, p.getBalance());
	        balance -= 20;
	        p.setBalance(balance);
	        assertEquals(130, p.getBalance());
	    }

	    @Test
	    void testAddWins() {
	        Player p = new Player("Bob", 100, 0);
	        p.addWins(1);
	        assertEquals(1, p.getNumberOfWins());
	    }

}
