package cluedo.main;
import java.util.*;
import cluedo.assets.*;
/**
 * Creates a new instance of a Board, and runs the textClient.
 * @author linus
 *
 */
public class Game {
	
	private Board board;
	private TextClient text;
	private int numPlayers = 0;
	private List<Player> currentPlayers;
	
	public Game(){
		board = new Board();
		text = new TextClient();
		currentPlayers = new ArrayList<Player>();
		initialSetup();
	}
	/**
	 * Sets up the board and an instance of the textClient.
	 */
	public void initialSetup(){
		boolean fail = false;
		if(!fail){
		System.out.println("Please enter the amount of players: ");
		Scanner scan = new Scanner(System.in);
		numPlayers = scan.nextInt();
		if(numPlayers > 6 || numPlayers < 3){
			System.out.println("ERROR: you must have at least 3 players and at most 6 players.");
			fail = true;
		}
		}
	}
	
	
	
	public static void main(String[] args){
		new Game();
	}
	

}
