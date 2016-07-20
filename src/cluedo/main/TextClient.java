package cluedo.main;
import java.io.*;
import java.util.*;

public class TextClient {
	/**
	 * Get string from System.in
	 */
	
	private static String inputString(String msg) {
		System.out.print(msg + " ");
		while (true) {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				return input.readLine();
			} catch (IOException e) {
				System.out.println("I/O Error ... please try again!");
			}
		}
	}

	/**
	 * Get integer from System.in
	 */
	private static int inputNumber(String msg) {
		System.out.print(msg + " ");
		while (true) {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				String v = input.readLine();
				return Integer.parseInt(v);
			} catch (IOException e) {
				System.out.println("Please enter a number!");
			}
		}
	}
	
	/**
	 * Input player details from System.in
	 */
	private static ArrayList<Player> inputPlayers(int nplayers, Location go) {
		// set up the tokens
		ArrayList<Player.Token> tokens = new ArrayList<Player.Token>();
		for(Player.Token t : Player.Token.values()) {
			tokens.add(t);
		}
		
		// now, input data
		ArrayList<Player> players = new ArrayList<Player>();

		for (int i = 0; i != nplayers; ++i) {
			String name = inputString("Player #" + i + " name?");
			String tokenName = inputString("Player #" + i + " token?");
			Player.Token token = Player.Token.valueOf(tokenName);
			while(!tokens.contains(token)) {
				System.out.print("Invalid token!  Must be one of: ");
				boolean firstTime = true;
				for (Player.Token t : Player.Token.values()) {
					if (!firstTime) {
						System.out.print(", ");
					}
					firstTime = false;
					System.out.print("\"" + t + "\"");
				}
				System.out.println();
				tokenName = inputString("Player #" + i + " token?");
				token = Player.Token.valueOf(tokenName);
			}
			tokens.remove(token);
			players.add(new Player(name, token, 1500, go));
		}
		return players;
	}
}
