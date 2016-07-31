package tests;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import swen221.monopoly.*;
import swen221.monopoly.locations.Location;
import swen221.monopoly.locations.Street;

public class MonopolyTests {
	// this is where you must write your tests; do not alter the package, or the
    // name of this file.  An example test is provided for you.
	
	@Test
	public void testValidBuyProperty_1() {
		// Construct a "mini-game" of Monopoly and with a single player. The
		// player attempts to buy a property. We check that the right amount has
		// been deducted from his/her balance, and that he/she now owns the
		// property and vice-versa.
		GameOfMonopoly game = new GameOfMonopoly();
		try {
			Player player = setupMockPlayer(game,"Park Lane",1500);
			game.buyProperty(player);
			assertEquals(1150,player.getBalance());
			assertEquals("Park Lane",player.iterator().next().getName());
			Street street = (Street) game.getBoard().findLocation("Park Lane"); 
			assertEquals(player,street.getOwner());
		} catch (GameOfMonopoly.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Testing for valid move.
	 */
	@Test
	public void testValidMove_1() {
		// This test if the player moved
		GameOfMonopoly game = new GameOfMonopoly();
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1500);
			game.movePlayer(player, 2);
			assertEquals("Mayfair", player.getLocation().getName());
		} catch (GameOfMonopoly.InvalidMove e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Shouldn't be able to buy non property.
	 */
	@Test
	public void testInvalidBuyProperty_2() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1500);
			game.movePlayer(player, 1);
			game.buyProperty(player);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}

	/**
	 * Shouldn't be able to buy property that has already been bought.
	 */
	@Test
	public void testInvalidBuyProperty_3() {
		// should not be able to buy a property thats already brought
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player1 = setupMockPlayer(game, "Park Lane", 1500);
			Player player2 = setupMockPlayer(game, "Park Lane", 1500);
			game.movePlayer(player1, 2);
			game.buyProperty(player1);
			game.movePlayer(player2, 2);
			game.buyProperty(player2);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to buy property with insufficient money.
	 */
	@Test
	public void testInvalidBuyProperty_4() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 100);
			game.movePlayer(player, 2);
			game.buyProperty(player);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Testing for valid selling property.
	 */
	@Test
	public void testValidSellProperty_1() {
		GameOfMonopoly game = new GameOfMonopoly();
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1500);
			game.buyProperty(player);
			game.sellProperty(player, player.getLocation());
		} catch (GameOfMonopoly.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Shouldn't be able to sell non property.
	 */
	@Test
	public void testInValidSellProperty_1() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1500);
			game.movePlayer(player, 1);
			Location loc = game.getBoard().findLocation("Super Tax");
			game.sellProperty(player, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to sell property that is not their's.
	 */
	@Test
	public void testInValidSellProperty_2() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player1 = setupMockPlayer(game, "Park Lane", 1500);
			Player player2 = setupMockPlayer(game, "Park Lane", 1500);
			game.movePlayer(player1, 2);
			game.buyProperty(player1);
			game.movePlayer(player2, 2);
			Location loc = game.getBoard().findLocation("Mayfair");
			game.sellProperty(player2, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to sell mortgaged property.
	 */
	@Test
	public void testInValidSellProperty_3() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1500);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.buyProperty(player);
			game.mortgageProperty(player, loc);
			game.sellProperty(player, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Testing valid mortgage property.
	 */
	@Test
	public void testValidMortgageProperty_1() {
		GameOfMonopoly game = new GameOfMonopoly();
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1500);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.buyProperty(player);
			game.mortgageProperty(player, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Shouldn't be able to mortgage property that has already been mortgaged.
	 */
	@Test
	public void testInValidMortgageProperty_1() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1500);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.buyProperty(player);
			game.mortgageProperty(player, loc);
			game.mortgageProperty(player, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to mortgage non property.
	 */
	@Test
	public void testInValidMortgageProperty_2() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1500);
			game.movePlayer(player, 1);
			Location loc = game.getBoard().findLocation("Super Tax");
			game.mortgageProperty(player, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to mortgage property that is not their's.
	 */
	@Test
	public void testInValidMortgageProperty_3() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player1 = setupMockPlayer(game, "Park Lane", 1500);
			Player player2 = setupMockPlayer(game, "Park Lane", 1500);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.buyProperty(player1);
			game.mortgageProperty(player2, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Testing if player has collected correct amount for passing go.
	 */
	@Test
	public void testPassGo() {
		GameOfMonopoly game = new GameOfMonopoly();
		try {
			Player player = setupMockPlayer(game, "Mayfair", 1500);
			game.movePlayer(player, 2);
			assertEquals(1700, player.getBalance());
		} catch (GameOfMonopoly.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	/**
	 * Testing if player1 has received the correct amount from rent.
	 */
	@Test
	public void testCollectRent() {
		GameOfMonopoly game = new GameOfMonopoly();
		try {
			Player player1 = setupMockPlayer(game, "Park Lane", 1500);
			Player player2 = setupMockPlayer(game, "Park Lane", 1500);
			game.movePlayer(player1, 2);
			game.buyProperty(player1);
			game.movePlayer(player2, 2);
			assertEquals(1150, player1.getBalance());
		} catch (GameOfMonopoly.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Testing valid unmortgage property.
	 */
	@Test
	public void testUnmortgageProperty() {
		GameOfMonopoly game = new GameOfMonopoly();
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1500);
			game.buyProperty(player);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.mortgageProperty(player, loc);
			game.unmortgageProperty(player, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Shouldn't be able to unmortgage property that has not been mortgaged.
	 */
	@Test
	public void testInvalidUnmortgageProperty_1() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1500);
			game.buyProperty(player);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.unmortgageProperty(player, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to unmortgage non property.
	 */
	@Test
	public void testInvalidUnmortgageProperty_2() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Super Tax", 1500);
			Location loc = game.getBoard().findLocation("Super Tax");
			game.unmortgageProperty(player, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to unmortgage property that is not their's.
	 */
	@Test
	public void testInvalidUnmortgageProperty_3() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player1 = setupMockPlayer(game, "Park Lane", 1500);
			Player player2 = setupMockPlayer(game, "Park Lane", 1500);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.buyProperty(player1);
			game.mortgageProperty(player1, loc);
			game.unmortgageProperty(player2, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to unmortgage property with insufficient money.
	 */
	@Test
	public void testInvalidUnmortgageProperty_4() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player1 = setupMockPlayer(game, "Park Lane", 350);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.buyProperty(player1);
			game.mortgageProperty(player1, loc);
			game.unmortgageProperty(player1, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Testing the correct about of money credited and deducted from buying, mortgaging, and unmortgaging
	 * a property.
	 */
	@Test
	public void testCostOnUnmortgageProperty() {
		GameOfMonopoly game = new GameOfMonopoly();
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1000);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.buyProperty(player);
			assertEquals(650, player.getBalance());
			game.mortgageProperty(player, loc);
			assertEquals(825, player.getBalance());
			game.unmortgageProperty(player, loc);
			assertEquals(440, player.getBalance());
		} catch (GameOfMonopoly.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Testing for valid build house.
	 */
	@Test
	public void testValidBuildHouse() {
		GameOfMonopoly game = new GameOfMonopoly();
		try {
			Player player = setupMockPlayer(game, "Park Lane", 2000);
			game.buyProperty(player);
			game.movePlayer(player, 2);
			game.buyProperty(player);
			game.buildHouses(player, player.getLocation(), 1);
		} catch (GameOfMonopoly.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Shouldn't be able to build house on a non property.
	 */
	@Test
	public void testInvalidBuildHouse_1() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Super Tax", 2000);
			Location loc = game.getBoard().findLocation("Super Tax");
			game.buildHouses(player, loc, 1);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to build house on a mortgaged property.
	 */
	@Test
	public void testInvalidBuildHouse_2() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 2000);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.buyProperty(player);
			game.mortgageProperty(player, loc);
			game.buildHouses(player, loc, 1);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to build a house if not all of the properties in the same colour group is their's.
	 */
	@Test
	public void testInvalidBuildHouse_3() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player1 = setupMockPlayer(game, "Park Lane", 2000);
			Player player2 = setupMockPlayer(game, "Mayfair", 2000);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.buyProperty(player1);
			game.buyProperty(player2);
			game.buildHouses(player1, loc, 1);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to build more than 5 houses.
	 */
	@Test
	public void testInvalidBuildHouse_4() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 2000);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.buyProperty(player);
			game.buildHouses(player, loc, 6);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to build houses if there is already a hotel present on that street.
	 */
	@Test
	public void testInvalidBuildHouse_5() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 2500);
			game.buyProperty(player);
			game.movePlayer(player, 2);
			game.buyProperty(player);
			game.buildHouses(player, player.getLocation(), 5);
			game.buildHotel(player, player.getLocation());
			game.buildHouses(player, player.getLocation(), 1);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to build more than 5 houses.
	 */
	@Test
	public void testInvalidBuildHouse_6() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 2500);
			game.buyProperty(player);
			game.movePlayer(player, 2);
			game.buyProperty(player);
			game.buildHouses(player, player.getLocation(), 5);
			game.buildHouses(player, player.getLocation(), 1);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to build house with insufficient money.
	 */
	@Test
	public void testInvalidBuildHouse_7() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 850);
			game.buyProperty(player);
			game.movePlayer(player, 2);
			game.buyProperty(player);
			game.buildHouses(player, player.getLocation(), 2);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Testing valid build hotel.
	 */
	@Test
	public void testValidBuildHotel() {
		GameOfMonopoly game = new GameOfMonopoly();
		try {
			Player player = setupMockPlayer(game, "Park Lane", 2500);
			game.buyProperty(player);
			game.movePlayer(player, 2);
			game.buyProperty(player);
			game.buildHouses(player, player.getLocation(), 5);
			game.buildHotel(player, player.getLocation());
		} catch (GameOfMonopoly.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Shouldn't be able to build a hotel without building up to 5 houses first.
	 */
	@Test
	public void testInvalidBuildHotel_1() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Electric Company", 2500);
			game.buyProperty(player);
			game.buildHotel(player, player.getLocation());
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to build a hotel on a mortgaged property.
	 */
	@Test
	public void testInvalidBuildHotel_2() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 2000);
			Location loc = game.getBoard().findLocation("Park Lane");
			game.buyProperty(player);
			game.mortgageProperty(player, loc);
			game.buildHotel(player, loc);
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to build a hotel on a property that is not their's.
	 */
	@Test
	public void testInvalidBuildHotel_3() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player1 = setupMockPlayer(game, "Park Lane", 2000);
			Player player2 = setupMockPlayer(game, "Mayfair", 2000);
			game.buyProperty(player1);
			game.movePlayer(player1, 2);
			game.buyProperty(player2);
			game.buildHotel(player1, player1.getLocation());
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to build a hotel without building up to 5 houses first.
	 */
	@Test
	public void testInvalidBuildHotel_4() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 2000);
			game.buyProperty(player);
			game.movePlayer(player, 2);
			game.buyProperty(player);
			game.buildHouses(player, player.getLocation(), 3);
			game.buildHotel(player, player.getLocation());
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to build a hotel if there's a hotel already present on that property.
	 */
	@Test
	public void testInvalidBuildHotel_5() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 2000);
			game.buyProperty(player);
			game.movePlayer(player, 2);
			game.buyProperty(player);
			game.buildHouses(player, player.getLocation(), 5);
			game.buildHotel(player, player.getLocation());
			game.buildHotel(player, player.getLocation());
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Shouldn't be able to build a hotel with insufficient money
	 */
	@Test
	public void testInvalidBuildHotel_6() {
		GameOfMonopoly game = new GameOfMonopoly();
		boolean invalid = false;
		try {
			Player player = setupMockPlayer(game, "Park Lane", 1800);
			game.buyProperty(player);
			game.movePlayer(player, 2);
			game.buyProperty(player);
			game.buildHouses(player, player.getLocation(), 5);
			game.buildHotel(player, player.getLocation());
		} catch (GameOfMonopoly.InvalidMove e) {
			invalid = true;
		}
		assertTrue(invalid);
	}
	
	/**
	 * Testing if correct amount is deducted off player2 for landing in player1's station.
	 */
	@Test
	public void testStationRent() {
		GameOfMonopoly game = new GameOfMonopoly();
		try {
			Player player1 = setupMockPlayer(game, "Kings Cross Station", 1800);
			Player player2 = setupMockPlayer(game, "Income Tax", 1800);
			game.buyProperty(player1);
			game.movePlayer(player2, 1);
			assertEquals(1775, player2.getBalance());
		} catch (GameOfMonopoly.InvalidMove e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Setup a mock game of monopoly with a player located at a given location.
	 */
	private Player setupMockPlayer(GameOfMonopoly game, String locationName, int balance)
			throws GameOfMonopoly.InvalidMove {
		Board board = game.getBoard();
		Location location = board.findLocation(locationName);
		return new Player("Dave", Player.Token.ScottishTerrier, balance, location);
	}
}
