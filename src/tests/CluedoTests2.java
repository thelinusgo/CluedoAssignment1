package tests;
import org.junit.*;

import cluedo.assets.*;
import cluedo.assets.Character;
import cluedo.main.*;

import static org.junit.Assert.*;
import java.util.*;


public class CluedoTests2 {
	// this is where you must write your tests; do not alter the package, or the
    // name of this file.  An example test is provided for you.
	
	@Test
	public void testValidMove_1() {
		// Construct a "mini-game" of Monopoly and with a single player. The
		// player attempts to buy a property. We check that the right amount has
		// been deducted from his/her balance, and that he/she now owns the
		// property and vice-versa.
		CluedoGame game = new CluedoGame();
		try {
			Player player = setupMockPlayer("Ralf", "Miss Scarlett", new Position(23, 13));
			player.setLookBack("_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			game.board.move(-1, 0, player);
			assertEquals(new Position(22, 13), player.position());
		} catch (CluedoGame.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Setup a mock game of monopoly with a player located at a given location.
	 */
	private Player setupMockPlayer(String name, String charName, Position pos)
			throws CluedoGame.InvalidMove {
		
		Player p = new Player(name);
		p.setCharacter(new Character(charName));
		p.setPos(pos.getX(), pos.getY());
		return p;
	}
}
