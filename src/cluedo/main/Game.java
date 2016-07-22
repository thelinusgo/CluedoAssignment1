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
		success = false;

		while(!success){
			numPlayers = textClient.inputNumber("How many players are playing?: ");
			if(numPlayers > 6 || numPlayers < 3){
				System.out.println("ERROR: Cluedo must have 3-6 players.");
			}else{ 
				success = true;
			}
		}
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
