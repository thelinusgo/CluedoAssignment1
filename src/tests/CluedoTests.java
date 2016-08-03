package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import cluedo.assets.Player;
import cluedo.main.Board;
import cluedo.main.Game;
import cluedo.main.Initializer;

public class CluedoTests {

	/**
	 * Test that it can add the correct amount of players
	 * Test
	 */
	@Test
	public void testCluedo_CorrectInput_1(){
		Game cluedo = new Game(true);
		int value = cluedo.numPlayers();
		List<Player> players = cluedo.currentPlayers();
		assertTrue(value < 6 || value > 3);
	}
	
	/**
	 * Test that the user can place input correctly.
	 */
	@Test
	public void testCluedo_CorrectInput_2(){
	Game cluedo = new Game(true);
	assertTrue(cluedo != null);
	}
	
	/**
	 * SIMULATED INPUT: Tests correct input.
	 */
	@Test
	public void testCluedo_CorrectInput_3(){
	String amount = "3";
	
	String n1 = "a";
	String n2 = "b";
	String n3 = "c";
	
	Game cluedo = new Game(true);	
	ByteArrayInputStream in = new ByteArrayInputStream(amount.getBytes());
	Scanner sc = new Scanner(System.in);
	System.setIn(in);
	System.out.println(n1);
	ByteArrayInputStream in1 = new ByteArrayInputStream(n1.getBytes());
	System.setIn(in1);
	ByteArrayInputStream in2 = new ByteArrayInputStream(n2.getBytes());
	System.setIn(in2);
	ByteArrayInputStream in3 = new ByteArrayInputStream(n3.getBytes());
	System.setIn(in3);
	}
}
