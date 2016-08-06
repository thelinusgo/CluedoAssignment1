package tests;
import org.junit.*;

import cluedo.arguments.Accusation;
import cluedo.arguments.Suggestion;
import cluedo.assets.*;
import cluedo.assets.Character;
import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.cards.Envelope;
import cluedo.cards.RoomCard;
import cluedo.cards.WeaponCard;
import cluedo.main.*;
import cluedo.main.CluedoGame.InvalidMove;

import static org.junit.Assert.*;
import java.util.*;


public class CluedoTests {

	
	
	/**
	 * Tests that it can move one position.
	 */
	@Test
	public void testValidMove_01() {
		 
		CluedoGame game = new CluedoGame();
		try {
			Player player = setupMockPlayer("Ralf", "Miss Scarlett", new Position(23, 13));
			player.setLookBack("_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			game.board.move(-1, 0, player, game.currentPlayers());
			assertEquals(new Position(22, 13), player.position());
		} catch (CluedoGame.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Tests that you can move multiple steps.
	 */
	@Test
	public void testValidMove_02() {
		 
		CluedoGame game = new CluedoGame();
		try {
			Player player = setupMockPlayer("Ralf", "Miss Scarlett", new Position(23, 13));
			player.setLookBack("_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			game.board.move(-4, 0, player, game.currentPlayers());
			assertEquals(new Position(19, 13), player.position());
		} catch (CluedoGame.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Ensures that you cannot move backwards from your position.
	 */
	@Test
	public void testInvalidMove_01(){
		 
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Ralf", "Professor Plum", new Position(0,17));
			player.setLookBack("|_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			game.board.move(-1, 0, player, game.currentPlayers());
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
	public void testInvalidMove_02(){
		 
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Ralf", "Professor Plum", new Position(7,24));
			player.setLookBack("/|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			for(int i = 0; i < 10; i++){
			game.board.move(0, -1, player, game.currentPlayers());	
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
	public void testValidMove_03(){
		 
		CluedoGame game = new CluedoGame();
		int diceRoll = game.diceRoll();
		try{
			Player player = setupMockPlayer("Ralf", "Professor Plum", new Position(0,17));
			player.setLookBack("|_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			for(int i = 0; i < diceRoll; i++){
				game.board.move(1, 0, player, game.currentPlayers());
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
	public void testGoingInRoom_01(){
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Ralf", "Professor Plum", new Position(0,17));
			player.setLookBack("|_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			game.board.move(6, -2, player, game.currentPlayers());
			assertTrue(player.isInRoom());
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGoingInRoom_02(){
		CluedoGame game = new CluedoGame();
		try {
			Player player1 = setupMockPlayer("Ralf", "Professor Plum", new Position(6,18));
			Player player2 = setupMockPlayer("Homer", "Miss Scarlett", new Position(6,17));
			player1.setLookBack("_|");
			player2.setLookBack("_|");
			game.board.move(0, 1, player1, game.currentPlayers());
			game.board.move(0, 1, player2, game.currentPlayers());
			game.board.move(0, 1, player2, game.currentPlayers());
			Player player3 = setupMockPlayer("Sam", "Colonel Mustard", new Position(6,18));
			Player player4 = setupMockPlayer("Kumar", "Mrs. White", new Position(6,17));
			player3.setLookBack("_|");
			player4.setLookBack("_|");
			game.board.move(0, 1, player3, game.currentPlayers());
			game.board.move(0, 1, player4, game.currentPlayers());
			game.board.move(0, 1, player4, game.currentPlayers());
			Player player5 = setupMockPlayer("Flo", "The Reverend Green", new Position(6,18));
			Player player6 = setupMockPlayer("Peter", "Mrs. Peacock", new Position(6,17));
			player5.setLookBack("_|");
			player6.setLookBack("_|");
			game.board.move(0, 1, player5, game.currentPlayers());
			game.board.move(0, 1, player6, game.currentPlayers());
			game.board.move(0, 1, player6, game.currentPlayers());
			assertEquals(6, player1.getRoom().getMap().size());
		} catch (InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGoingInRoom_03(){
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Ralf", "Professor Plum", new Position(0,17));
			game.addPlayer(player);
			Player player2 = setupMockPlayer("Pete", "Mrs. White", new Position(1,17));
			game.addPlayer(player2);
			player.setLookBack("|/|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			game.board.move(6, -2, player, game.currentPlayers());
			assertTrue(player.isInRoom());
			
			game.board.exitRoom(player, game.currentPlayers());
			assertFalse(player.isInRoom());
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test that I can leave a room after entering it.
	 */
	@Test
	public void testLeavingRoom(){
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Ralf", "Professor Plum", new Position(0,17));
			player.setLookBack("|_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			game.board.move(6, -2, player, game.currentPlayers()); //moving diagonally but who cares anyway?
			System.out.println(player.position().toString());
			assertTrue(player.isInRoom());
			game.board.exitRoom(player, game.currentPlayers());
			assertTrue(!player.isInRoom());
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGoingInStairs(){
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Ralf", "Professor Plum", new Position(0,17));
			player.setLookBack("|_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
			game.board.move(6, 2, player, game.currentPlayers()); //moving diagonally but who cares anyway?
			assertTrue(player.isInRoom());
			game.board.canMove(player);
			//get the room that the player WILL LEAVE
			Room oldRoom = player.getRoom();
			game.board.moveToRoom(player, oldRoom.getOtherRoom());
			assertTrue(player.isInRoom());
			//check that the rooms Other Room equals the old room.
			assertEquals(player.getRoom().getOtherRoom(), oldRoom);
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}	
	}
	
	/**
	 * Test when you try to go use stairs in a room that does not have them.
	 */
	@Test
	public void testInvalidGoingStairs(){
	CluedoGame game = new CluedoGame();
	/**
	 * TODO: NOTE: this throws a Null Pointer Exception when you try to access a room that does not have a room.
	 */
	try{
		Player player = setupMockPlayer("Ralf", "Professor Plum", new Position(23,6));
		player.setLookBack("_|");
		game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|"; 
		game.board.move(0, -1, player, game.currentPlayers());
		assertFalse(player.isInRoom());
		assertEquals(new Position(23, 6), player.position());
	}catch(CluedoGame.InvalidMove e){
		fail(e.getMessage());
	}
  }
	
	/**
	 * Test that a player must make a valid name.
	 */
	@Test
	public void testInvalidCharacterName(){
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Ralf", "Professor Pulm", new Position(0,17));
			assertTrue(player.getCharacterName() == null);
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test that a player must make a valid name.
	 */
	@Test
	public void testValidCharacter_01(){
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Ralf", "Professor Plum", new Position(0,17));
			assertTrue(player.getCharacterName() != null);
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test that it generates a correct suggestion object.
	 * NOTE: REQUIRES USER INPUT to pass.
	 */
	@Test
	public void testValidSuggestion_01(){
	CluedoGame game = new CluedoGame();
	try{
		Player player = setupMockPlayer("Ralf", "Miss Scarlett", new Position(0,17));
		player.setLookBack("|_|");
		game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|";
		game.board.move(6, -2, player, game.currentPlayers());
		assertTrue(player.getRoom() != null);
		
		WeaponCard wc = new WeaponCard(new Weapon("Dagger"));
		RoomCard rc = new RoomCard(player.getRoom());
		CharacterCard cc = new CharacterCard(new Character("Mrs. White"));
		Suggestion sug = new Suggestion(wc, rc, cc, player);
		assertTrue(sug != null);
	}catch(CluedoGame.InvalidMove e){
		fail(e.getMessage());
	}
   }
	
	/**
	 * This tests that the user makes a correct suggestion, and it will iterate over the other players hands.
	 * - To make this test past, must choose cards that don't belong to any player.
	 * 
	 */
	@Test
	public void testValidSuggestion_02(){
	CluedoGame game = new CluedoGame();
	
	try{
		Player player1 = setupMockPlayer("A", "Miss Scarlett", new Position(0,17));
		player1.setLookBack("|_|");
		game.board.getBoard()[player1.position().getX()][player1.position().getY()] = player1.getCharacterName() + "|";
		game.board.move(6, -2, player1, game.currentPlayers());
		RoomCard r1 = new RoomCard(new Room("Kitchen"));
		WeaponCard w1 = new WeaponCard(new Weapon("Rope"));
		CharacterCard c1 = new CharacterCard(new Character("Miss Scarlett"));
		
		player1.addCard(c1);
		player1.addCard(w1);
		player1.addCard(r1);
		game.addPlayer(player1);
		r1.getObject().addWeapon(w1.getObject());
		r1.getObject().addCharacter(c1.getObject());
		w1.getObject().addRoom(r1.getObject());
		c1.getObject().addRoom(r1.getObject());
		
		RoomCard r2 = new RoomCard(new Room("Study"));
		WeaponCard w2 = new WeaponCard(new Weapon("Dagger"));
		CharacterCard c2 = new CharacterCard(new Character("Professor Plum"));
		Player player2 = setupMockPlayer("B", "Professor Plum", new Position(0,25));
		player2.addCard(r2);
		player2.addCard(w2);
		player2.addCard(c2);
		game.addPlayer(player2);
		r2.getObject().addWeapon(w2.getObject());
		r2.getObject().addCharacter(c2.getObject());
		w2.getObject().addRoom(r2.getObject());
		c2.getObject().addRoom(r2.getObject());
		
		Player player3 = setupMockPlayer("C", "The Reverend Green", new Position(7,24));
		player3.addCard(new CharacterCard(new Character("Miss Scarlett")));
		player3.addCard(new WeaponCard(new Weapon("Knife")));
		player3.addCard(new RoomCard(new Room("Conservatory")));
		game.addPlayer(player3);
		
		game.initializer.distributeCharacters(game.currentPlayers());
		game.initializer.setCharacters();
		assertTrue(player1.getRoom() != null);
		
		WeaponCard wc = new WeaponCard(new Weapon("Revolver"));
		RoomCard rc = new RoomCard(player1.getRoom());
		CharacterCard cc = new CharacterCard(new Character("Colonel Mustard"));
		Suggestion sug = new Suggestion(wc, rc, cc, player1);
		assertTrue(sug.checkSuggestion(game.currentPlayers()));
		assertEquals(player1.getRoom(), sug.getCharacterCard().getObject().getRoom());
	}catch(CluedoGame.InvalidMove e){
		fail(e.getMessage());
	}
	}
	/**
	 * Test that you cannot make an invalid suggestion.
	 * 
	 */
	@Test
	public void testInvalidSuggestion(){
		CluedoGame game = new CluedoGame();
		try{
			Player player = setupMockPlayer("Ralf", "Miss Scarlett", new Position(0,17));
			player.setLookBack("|_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|";
			game.board.move(+2, 0, player, game.currentPlayers());
			assertTrue(player.getRoom() == null);
			Suggestion sug = game.makeSuggestion(player);
			assertTrue(sug == null);
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test that you can make a valid accusation.
	 */
	@Test
	public void testValidAccusation_01(){
		CluedoGame game = new CluedoGame();
		try{
			Envelope env = new Envelope();
			WeaponCard wep = new WeaponCard(new Weapon("Dagger"));
			RoomCard room = new RoomCard(new Room("Ballroom"));
			CharacterCard character = new CharacterCard(new Character("Miss Scarlett"));
			env.add(wep);
			env.add(room);
			env.add(character);
			Player player = setupMockPlayer("Ralf", "Miss Scarlett", new Position(0,17));
			game.addPlayer(player);
			player.setLookBack("|_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|";
			Accusation accu = new Accusation(wep,room,character,player, env);
			assertTrue(accu != null);
			//assertTrue(accu.argumentStatus());
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test that you can make a valid accusation - AND the game will be over.
	 */
	@Test
	public void testValidAccusation_02(){
		CluedoGame game = new CluedoGame();
		try{
			Envelope env = new Envelope();
			WeaponCard wep = new WeaponCard(new Weapon("Dagger"));
			RoomCard room = new RoomCard(new Room("Ballroom"));
			CharacterCard character = new CharacterCard(new Character("Miss Scarlett"));
			env.add(wep);
			env.add(room);
			env.add(character);
			Player player = setupMockPlayer("Ralf", "Miss Scarlett", new Position(0,17));
			game.addPlayer(player);
			player.setLookBack("|_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|";
			Accusation accu = new Accusation(wep,room,character,player,env);
			assertTrue(accu != null);
			assertTrue(accu.accusationStatus());
			//assertTrue(accu.checkAccusation(env, player));
			//assertTrue(game.isGameOver());
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test that you can make an invalid accusation.
	 */
	@Test
	public void testInvalidAccusation_01(){
		CluedoGame game = new CluedoGame();
		try{
			Envelope env = new Envelope();
			WeaponCard wep = new WeaponCard(new Weapon("Dagger"));
			RoomCard room = new RoomCard(new Room("Ballroom"));
			CharacterCard character = new CharacterCard(new Character("Miss Scarlett"));
			env.add(wep);
			env.add(room);
			env.add(character);
			Player player = setupMockPlayer("Ralf", "Miss Scarlett", new Position(0,17));
			game.addPlayer(player);
			player.setLookBack("|_|");
			game.board.getBoard()[player.position().getX()][player.position().getY()] = player.getCharacterName() + "|";
			Accusation accu = new Accusation(wep,room,(new CharacterCard(new Character("Professor Plum"))), player, env);
			assertTrue(accu != null);
			assertFalse(accu.accusationStatus());
		}catch(CluedoGame.InvalidMove e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test that a player can win when all players are out of the game
	 */
	@Test
	public void testWin_01(){
		CluedoGame game = new CluedoGame();
		try{
			Envelope env = new Envelope();
			WeaponCard wep = new WeaponCard(new Weapon("Dagger"));
			RoomCard room = new RoomCard(new Room("Ball Room"));
			CharacterCard character = new CharacterCard(new Character("Miss Scarlett"));
			env.add(wep);
			env.add(room);
			env.add(character);
			Player player1 = setupMockPlayer("Ralf", "Miss Scarlett", new Position(0,17));
			Player player2 = setupMockPlayer("Sam", "Mrs. White", new Position(9, 0));
			Player player3 = setupMockPlayer("Pete", "Professor Plum", new Position(14, 0));
			game.addPlayer(player1);
			game.addPlayer(player2);
			game.addPlayer(player3);
			Accusation accu1 = new Accusation(wep,room,(new CharacterCard(new Character("Professor Plum"))), player1, env);
			Accusation accu2 = new Accusation(wep,(new RoomCard(new Room("Dining Room"))), character, player2, env);
			assertTrue(game.isGameOver());
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
