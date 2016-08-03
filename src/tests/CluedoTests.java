package tests;
import org.junit.*;

import cluedo.assets.*;
import cluedo.assets.Character;
import cluedo.main.*;

import static org.junit.Assert.*;
import java.util.*;


public class CluedoTests {
	/**
	 * Tests that it can move one position.
	 */
	@Test
	public void testValidMove_1() {
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
	 * Tests that you can move multiple steps.
	 */
	@Test
	public void testValidMove_2() {
		CluedoGame game = new CluedoGame();
		try {
			Player player = setupMockPlayer("Rodolfo", "Miss Scarlett", new Position(23, 13));
			player.setLookBack("_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			game.board.move(-4, 0, player);
			assertEquals(new Position(19, 13), player.position());
		} catch (CluedoGame.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Ensures that you cannot move backwards from your position.
	 */
	@Test
	public void testInvalidMove_1(){
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Samuel", "Professor Plum", new Position(0,17));
			player.setLookBack("|_");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			game.board.move(-1, 0, player);
			assertEquals(new Position(0, 17), player.position());
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Ensures that you cannot move into a wall. If you move 10 spaces, and 10th square is a wall, it will move
	 * 9 spaces, 1 before the wall.
	 */
	@Test
	public void testInvalidMove_2(){
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Namuel", "Professor Plum", new Position(7,24));
			player.setLookBack("__");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			for(int i = 0; i < 10; i++){
			game.board.move(0, -1, player);	
			}
			assertEquals(new Position(7, 16), player.position()); //assert that it moved it one square behind the wall.
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test that it utilizes the diceRoll correctly.
	 */
	@Test
	public void testValidMove_3(){
		CluedoGame game = new CluedoGame();
		int diceRoll = game.diceRoll();
		try{
			Player player = setupMockPlayer("Xamuel", "Professor Plum", new Position(0,17));
			player.setLookBack("_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			for(int i = 0; i < diceRoll; i++){
				game.board.move(1, 0, player);
			}
			assertEquals(new Position(diceRoll, player.position().getY()), player.position());
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test that I can go inside of a room.
	 */
	@Test
	public void testGoingInRoom(){
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Lamuel", "Professor Plum", new Position(24,6));
			player.setLookBack("_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
		
		
		
		
		
		}catch(CluedoGame.InvalidMove e){
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
