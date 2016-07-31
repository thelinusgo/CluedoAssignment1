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
import cluedo.cards.Card;


public class TextClient {
	private static Board board = Game.board;

	private static String MOVES = "w|a|s|d";
	private static String OPTIONS = "m|s|a|c";

	private static boolean correctInput = false;

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
	public static void movementListener(Player p){
		String dir = "z";
		System.out.println("Please enter the letters W, A, S or D to move.");
		System.out.println("W - Up");
		System.out.println("A - Left");
		System.out.println("S - Down");
		System.out.println("D - Right\n");
		boolean isValidMove = false;
		correctInput = false;

		while(!correctInput && !isValidMove){
			dir = sc.next();
			if(dir.matches(MOVES)){
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
				}
				if(board.isValidMove()){
					isValidMove = true;
				}else{
					System.out.println("Please try again.");
				}
				correctInput = true;
			}else{
				System.out.println("That is not a valid direction!");
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

		boolean correctInput = false;
		boolean isInteger = false;
		/*Now, ask for user input. */
		while(!correctInput && !isInteger){
			System.out.println("How many players would you like?");
			amount = sc.next();
			if(isInteger(amount)){
				if(Integer.parseInt(amount) > 6 || Integer.parseInt(amount) < 3){
					System.out.println("Cluedo needs 3-6 players.");
				}else{
					correctInput = true;
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
	 * Ask player what they want to do, i.e. show cards, move, make a suggestion or make an accusation.
	 * @param p
	 */
	public static String askOption(){
		String option = "z";
		System.out.println("What do you want to do?");
		System.out.println("Press M to make a move.");
		System.out.println("Press S to make a suggestion.");
		System.out.println("Press A to make an accusation.");
		System.out.println("Press C to show current cards.");
		while(!option.matches(OPTIONS)){
			option = sc.next();
			if(!option.matches(OPTIONS)){
				System.out.println("Not an option. Use lower case letters.");
			}
		}
		return option;
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