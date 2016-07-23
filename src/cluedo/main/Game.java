package cluedo.main;
import java.util.*;
import cluedo.assets.*;
/**
 * Creates a new instance of a Board, and runs the textClient.
 * @author linus
 *
 */
public class Game {
 //aaa
	private Initializer initializer;
	private Board board;
	private int numPlayers = 0;
	private List<Player> currentPlayers;
	private TextClient textClient;
	private boolean success;
	
	/** This helps generating a random shuffle for the lists */
	private long seed = System.nanoTime();

	public Game(){
		initializer = new Initializer();
		board = new Board();
		currentPlayers = new ArrayList<Player>();
		initialSetup();
	}
	/**
	 * Sets up the board and an instance of the textClient.
	 */
	public void initialSetup(){
		drawAsciiArt();
		String playerSequence = textClient.askPlayers();
		System.out.println(playerSequence);
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