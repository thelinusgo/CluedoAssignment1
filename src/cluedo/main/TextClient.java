package cluedo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

import cluedo.assets.Player;


public class TextClient {

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
		Scanner sc = new Scanner(System.in);
		String dir = sc.next();
		switch(dir){
		case "W": p.moveAStep();
		case "S": p.moveAStep();
		case "A": p.moveAStep();
		case "D": p.moveAStep();
		default:
			System.out.println("That is not a valid direction!");
			break;
		}
		sc.close();

	}
	
	
	
	/**
	 * Ask for initial players, and their names.
	 * Returns a list of players. Sequence begins and ends with a backslash "/".
	 * @return String of players.
	 */
	public static void askPlayers(){
		/*Local variables */
		int amount = 0;
		String singleName = "";
		//String appendedNames = "/\n";
		boolean right = false;
		
		/*Use a scanner. */
		Scanner sc = new Scanner(System.in);
		/*Now, ask for user input. */
		while(!right){
			System.out.println("How many players would you like?");
			amount = sc.nextInt();
			
			if(amount > 6 || amount < 3){
				System.out.println("Cluedo needs 3-6 players.");
			}else{
				right = true;
			}
		}
			for(int i = 0 ; i != amount; ++i){
				System.out.println("Please enter Player "  + String.valueOf(i+1) + "'s name");
				singleName = sc.next();
				Game.addPlayer(singleName);
			}
			System.out.println("Please note that every player will be assigned a random character.");
			Game.askSuccess = true;
			sc.close();
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

}