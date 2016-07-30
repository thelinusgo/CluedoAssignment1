package cluedo.main;
import java.util.*;
import cluedo.assets.*;
import cluedo.cards.Card;
/**
 * Creates a new instance of a Board, and runs the textClient.
 * @author linus
 *
 */
public class Game {
	private Initializer initializer; //initializes all of the data
	private Board board; //an instance of the board.
	
	private int numPlayers = 0; //stores the amount of players.
	private static List<Player> currentPlayers; //a list of the current players.
	
	private TextClient textClient; //an instance of the textClient.
	public static boolean askSuccess; //TODO? WHAT IS THIS??
	private static boolean hasAsked = false; //if a player has asked or not.
	
	private static Dice dice = new Dice(); //make a new instance of the dice.
	private Player currentPlayer; //the current player of the round.
	
	
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
		for(int i = 0; i < currentPlayers.size(); i++){
			System.out.println(currentPlayers.get(i));
			System.out.println("-------------------------------------------");
			System.out.println("Name: " +currentPlayers.get(i).getName());
			for(Card c : currentPlayers.get(i).getCards()){
				System.out.println("card: " + c.toString());
			}
			System.out.println("-------------------------------------------");
		}
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
			currentPlayer = currentPlayers.get(0);
			System.out.println("Asking players successful.");
			System.out.println("Player : " + currentPlayer.getName() + " to start.");
			System.out.println(currentPlayer.getName() + " rolls a " + dice.getDice() + ".");
			currentPlayer.setNumberofMoves(dice.getDice());
			System.out.println(currentPlayer.getName() + "  has " + currentPlayer.numberofMoves() + "moves.");

			while(currentPlayer.numberofMoves() > 0){
				System.out.println(currentPlayer.getName() + " currently has " + currentPlayer.numberofMoves() + "moves left.");
				TextClient.movementListener(currentPlayer);
			}
			System.out.println(currentPlayer.getName() + " has run out of moves.");
		}
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