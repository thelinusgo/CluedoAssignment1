package cluedo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import cluedo.arguments.Accusation;
import cluedo.arguments.Suggestion;
import cluedo.assets.Player;
import cluedo.cards.*;


public class TextClient {
	private static Board board = Game.board;

	private static String MOVES = "w|a|s|d"; //pattern describing the possible moves.
	private static String OPTIONS = "m|s|a|c"; //patterns describing the possible options.
	private static String CHOICE = "y|n";
	
	private static boolean correctInput = false; //set to true if the correct input has been provided.

	private static Scanner sc = new Scanner(System.in); //field containing the scanner.
	/**
	 * Get string from System.in
	 */
	public static String inputString() {
		String choice = "z";
		while(!choice.matches(CHOICE)){
			choice = sc.next();
			if(!choice.matches(CHOICE)){
				System.out.println("Not a choice. Use lower case letters.");
			}
		}
		return choice;
	}

	/**
	 * Listen for direction movement. W, S, A, D are valid directions.
	 * 
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
					correctInput = true;
				}else{
					System.out.println("Please try again.");
				}
			}else{
				System.out.println("That is not a valid direction!");
			}
		}
	}
	
	/**
	 * Asks the user input about creating an accusation.
	 * @param p
	 * @return
	 */
	public static Accusation askAccusation(Player p){
		System.out.println("Player: " + p.getName() + "Wishes to make an accusation.");
		Envelope env = Initializer.getEnvelope();
		CharacterCard character = null;
		WeaponCard weapon = null;
		RoomCard room = null;
		
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	/**
	 * Gets user input about creating the suggestion.
	 * This iterates over the players hand, and allows them to create a suggestion, based off their data.
	 * @param p
	 */
	public static Suggestion askSuggestion(Player p){
		System.out.println("What cards do you want to nominate?");
		System.out.println("----------------------------------");
		System.out.println("AVAILABLE CARDS:");
		
		//the objects for creating a suggestion.
		//TODO: needs to be based on room he's in..
		WeaponCard weapon = null;
		CharacterCard character = null;
		RoomCard room = null;
		int indexChoice;
		
		//sublists containing cards of a certain category.
		List<Card> cardsList = p.getCards();
		List<WeaponCard> weapons = new ArrayList<>();
		List<RoomCard> rooms = new ArrayList<>();
		List<CharacterCard> suspects = new ArrayList<>();
		
		//seperating the hand into their respective sublists..
		for(Card cards : cardsList){
			if(cards instanceof WeaponCard){
				weapons.add((WeaponCard) cards);
			}else if(cards instanceof RoomCard){
				rooms.add((RoomCard) cards);
			}else if(cards instanceof CharacterCard){
				suspects.add((CharacterCard) cards);
			}	
		}
		
		System.out.println("Instructions: Enter index of the item you want to nominate.");
		for(int i = 0; i < 3;){
			if(i == 0){
				System.out.println("Step 1) Choose from available weapons: ");
				int index = 0;
				for(WeaponCard ww : weapons){
				System.out.println(index + " "  + ww.toString());				
				index++;
				}
				indexChoice = sc.nextInt();
				if(indexChoice < 0 || indexChoice > weapons.size()-1){
					System.out.println("Incorrect index. Please try again.");
				
				}else{
					weapon = (WeaponCard) weapons.get(indexChoice);
					i++;
				}
			}else if(i == 1){
				System.out.println("Step 2) Choose from available Rooms: ");
				int index = 0;
				for(RoomCard rr : rooms){
				System.out.println(index + " "  + rr.toString());
				index++;
				}
				indexChoice = sc.nextInt();
				if(indexChoice < 0 || indexChoice > weapons.size()-1){
					System.out.println("Incorrect index. Please try again.");
					}else{
						room = (RoomCard) rooms.get(indexChoice);
						i++;
					}
			}else{
				System.out.println("Step 3) Choose from available Suspects: ");
				int index = 0;
				for(CharacterCard cc : suspects){
				System.out.println(index + " "  + cc.toString());
				index++;
				}
				indexChoice = sc.nextInt();
				if(indexChoice < 0 || indexChoice > weapons.size()-1){
					System.out.println("Incorrect index. Please try again.");
					}else{
						character = (CharacterCard) suspects.get(indexChoice);
						i++;
					}
			}
		}
		System.out.println("----------------------------------");
		System.out.println(" CONFIRMED Suggestion Pieces:     ");
		System.out.println(" weapon: " + weapon);
		System.out.println(" character: " + character);
		System.out.println(" room: " + room);
		System.out.println("----------------------------------");

		return new Suggestion(weapon, room, character, p);
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