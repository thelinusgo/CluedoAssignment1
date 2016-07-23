package cluedo.main;
import java.util.*;
import cluedo.assets.*;
/**
 * Creates a new instance of a Board, and runs the textClient.
 * @author linus
 *
 */
public class Game {
	private Initializer initializer;
	private Board board;
	private int numPlayers = 0;
	private static List<Player> currentPlayers;
	private TextClient textClient;
	private boolean success;
	
	/** This helps generating a random shuffle for the lists */
	private long seed = System.nanoTime();

	public Game(){
		board = new Board();
		currentPlayers = new ArrayList<Player>();
		initialSetup();
		initializer = new Initializer();
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
		System.out.println("Players: ");
		textClient.askPlayers();
		for(int i = 0; i < currentPlayers.size(); i++){
			System.out.println(currentPlayers.get(i));
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
	
	public void runGame(){
		if(success){
			
		}
	}

	public static void main(String[] args){
		new Game();
	}
}