package cluedo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
	 * Ask for initial players, and their names.
	 * Returns a list of players. Sequence begins and ends with a backslash "/".
	 * @return String of players.
	 */
	public static String askPlayers(){
		
		/*Local variables */
		int amount = 0;
		String singleName = "";
		String appendedNames = "/\n";
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
			
			for(int i = 0 ; i != amount; ++i){
				System.out.println("Please enter Player "  + i+1 + "'s name.");
				singleName = sc.next();
				appendedNames += singleName + "\n";
			}
		}
		appendedNames += "/";
		return appendedNames;
		
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
