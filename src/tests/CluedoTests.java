package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import cluedo.main.Board;
import cluedo.main.Game;
import cluedo.main.Initializer;

public class CluedoTests {

	/**
	 * Test that the gameBoard can be made.
	 * Test
	 */
	@Test
	public void testCluedo_1(){
		Game cluedo = new Game(true);
		int numPlayers = 3;

		for(int i = 0; i < numPlayers; ++i){
			Game.addPlayer("Player : " + i);
			Initializer.distributeCharacters();
			Initializer.distributeCards();
			Board.setPlayerPosition(currentPlayers);

		}
		
		
//		Game cluedoGame = new Game();
//	
//		String data = "3\n";
//		InputStream stdin = System.in;
//		
//		try{
//			System.setIn(new ByteArrayInputStream(data.getBytes()));
//			Scanner scanner = new Scanner(System.in);
//			System.out.println(scanner.nextLine());
//		}finally{
//			System.setIn(stdin);
//		}
//		
		
	}
}
