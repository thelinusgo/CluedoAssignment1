package cluedo.main;
import java.io.IOException;
import java.util.*;

import cluedo.arguments.Suggestion;
import cluedo.assets.*;
import cluedo.cards.Card;
/**
 * Creates a new instance of a Board, and runs the textClient.
 * @author Casey & Linus
 *
 */
public class Game {
	public static Initializer initializer; //initializes all of the data
	public static Board board; //an instance of the board.

	private int numPlayers = 0; //stores the amount of players.
	private static List<Player> currentPlayers; //a list of the current players.

	private TextClient textClient; //an instance of the textClient.
	public static boolean askSuccess; //TODO? WHAT IS THIS??
	private static boolean hasAsked = false; //if a player has asked or not.

	private static Dice dice = new Dice(); //make a new instance of the dice.
	private Player currentPlayer; //the current player of the round.

	private boolean moveMade = false;

	/** This helps generate a random shuffle for the lists */
	private long seed = System.nanoTime();

	public Game(){
		board = new Board();
		currentPlayers = new ArrayList<Player>();
		initializer = new Initializer();
		initialSetup();
		runGame();
	}

	/**
	 * Add new player to current players.
	 * @param name
	 */
	public static void addPlayer(String name){
		currentPlayers.add(new Player(name));
	}

	/**
	 * Returns currentPlayers list.
	 * @param name
	 */
	public static List<Player> getCurrentPlayers(){
		return currentPlayers;
	}

	/**
	 * Sets up the board and an instance of the textClient.
	 */
	public void initialSetup(){
		drawAsciiArt();
		TextClient.askPlayers();
		initializer.distributeCharacters();
		initializer.distributeCards();
		board.setPlayerPosition(currentPlayers);
		board.drawBoard();
	}

	/**
	 * Fun little thing I tried doing. It works!
	 * This method draws "CLUEDO GAME" in ascii representative form.
	 * @author Linus Go
	 */
	public void drawAsciiArt(){
		String art = "";
		art+="+===========================================================================+\n";
		art+="|| #####          by CASEY & LINUS              #####                       ||\n";
		art+="||#     # #      #    # ###### #####   ####    #     #   ##   #    # ###### ||\n";
		art+="||#       #      #    # #      #    # #    #   #        #  #  ##  ## #      ||\n";
		art+="||#       #      #    # #####  #    # #    #   #  #### #    # # ## # #####  ||\n";
		art+="||#       #      #    # #      #    # #    #   #     # ###### #    # #      ||\n";
		art+="||#     # #      #    # #      #    # #    #   #     # #    # #    # #      ||\n";
		art+="|| #####  ######  ####  ###### #####   ####     #####  #    # #    # ###### ||\n";
		art+="+===========================================================================+\n";
		System.out.println(art);
		System.out.println("Welcome to the Cluedo Game.");
	}

	public void setPlayerPosition(){
		Collections.shuffle(currentPlayers, new Random(seed)); 
		board.setPlayerPosition(currentPlayers);
	}

	/**
	 * Runs the game - only if asking players was successful.
	 */
	public void runGame(){
		if(askSuccess){
			while(!isGameOver()){
				for(int i = 0; i < currentPlayers.size(); i++){
					moveMade = false;
					currentPlayer = currentPlayers.get(i);
					System.out.println("Player " + currentPlayer.getName() + " starts.");
					System.out.println(currentPlayer.getName() + "'s character piece is " + currentPlayer.getCharacterName() + ".");
					while(!moveMade){
						String option = TextClient.askOption();
						doOption(option, currentPlayer);
					}
				}
			}
		}
	}

	public void doOption(String option, Player p){
		switch(option){
		case "m":
			System.out.println(currentPlayer.getName() + " rolls a " + dice.getDice() + ".");
			currentPlayer.setNumberofMoves(dice.getDice());
			System.out.println(currentPlayer.getName() + "  has " + currentPlayer.numberofMoves() + " moves.");

			while(currentPlayer.numberofMoves() > 0){
				System.out.println(currentPlayer.getName() + " currently has " + currentPlayer.numberofMoves() + " moves left.");
				System.out.println("current location: " + currentPlayer.position().getX() + ", " + currentPlayer.position().getY());
				TextClient.movementListener(currentPlayer);
			}
			System.out.println(currentPlayer.getName() + " has run out of moves.");
			moveMade = true;
			break;
		case "c":
			for(Card c : p.getCards()){
				System.out.println(c.toString());
			}
			break;
		case "a":
			//TODO: need to finish this part.
			moveMade = true;
			break;
		case "s":
			//TODO: ALSO NEED TO FINISH THIS PART.
			System.out.println("Player " + currentPlayer.getName() + " wishes to make an suggestion.");
			Suggestion sug = TextClient.askSuggestion(p);
			moveMade = true;
			break;
		}
	}
	
	

	public boolean isGameOver(){
		return false;
	}



	/**
	 * TODO: Casey, you need to decide how moving is implemented.
	 * Pathfinding, or basic WSAD movement?
	 * This displays the instructions to move
	 */
	public void displayInstructions(){
		System.out.println("Type W to move UP.");
		System.out.println("Type S to move DOWN.");
		System.out.println("Type A to move LEFT.");
		System.out.println("Type D to move RIGHT.");
	}

	public static void main(String[] args){
		new Game();
	}
}