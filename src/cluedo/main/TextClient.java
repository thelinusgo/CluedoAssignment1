package cluedo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSpinnerUI;

import cluedo.assets.Player;


public class TextClient {
	private static Board board = new Board();

	private static String MOVES = "w|a|s|d";

	private static Scanner sc = new Scanner(System.in);
	/**
	 * Get string from System.in
	 */
	public static String inputString(String msg) {
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
	 * Listen for direction movement. W, S, A, D are valid directions.
	 * Currently unfinished!
	 * TODO: NEED TO LOOK AT THIS!!
	 * @param Player to move.
	 */
	public static void movementListener(Player p) throws IOException{
		String dir = "z";
		System.out.println("Please enter the letters W, A, S or D to move.");
		System.out.println("W - Up");
		System.out.println("A - Left");
		System.out.println("S - Down");
		System.out.println("D - Right\n");
		while(!dir.matches(MOVES)){
			dir = sc.next();
			switch(dir){
			case "w":
				board.move(0, -1, p);
				break;
			case "s": 
				board.move(0, 1, p);
				break;
			case "a": 
				board.move(-1, 0, p);
				break;
			case "d": 
				board.move(1, 0, p);
				break;
			default:
				System.out.println("That is not a valid direction!");
				break;
			}
		}
	}



	/**
	 * Ask for initial players, and their names.
	 * Returns a list of players. Sequence begins and ends with a backslash "/".
	 * @return String of players.
	 */
	public static void askPlayers(){
		/*Local variables */
		String amount = "z";
		String singleName = "";
		//String appendedNames = "/\n";
		boolean right = false;
		boolean isInteger = false;
		/*Now, ask for user input. */
		while(!right && !isInteger){
			System.out.println("How many players would you like?");
			amount = sc.next();
			if(isInteger(amount)){
				if(Integer.parseInt(amount) > 6 || Integer.parseInt(amount) < 3){
					System.out.println("Cluedo needs 3-6 players.");
				}else{
					right = true;
					isInteger = true;
				}
			}
			
		}
		for(int i = 0 ; i != Integer.parseInt(amount); ++i){
			System.out.println("Please enter Player "  + String.valueOf(i+1) + "'s name");
			singleName = sc.next();
			Game.addPlayer(singleName);
		}
		System.out.println("Please note that every player will be assigned a random character.");
		Game.askSuccess = true;
	}

	/**
	 * Get integer from System.in
	 */
	public static int inputNumber(String msg) {
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

	public static boolean isInteger(String input){
		try{
			Integer.parseInt( input );
			return true;
		}catch(Exception e){
			return false;
		}
	}

}