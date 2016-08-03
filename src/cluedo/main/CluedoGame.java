package cluedo.main;
import java.io.IOException;
import java.util.*;

import cluedo.arguments.Accusation;
import cluedo.arguments.Suggestion;
import cluedo.assets.*;
import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.cards.RoomCard;
import cluedo.cards.WeaponCard;
/**
 * Creates a new instance of a Board, and runs the textClient.
 * @author Casey & Linus
 *
 */
public class CluedoGame {
	public static Initializer initializer; //initializes all of the data
	public static Board board; //an instance of the board.

	private int numPlayers = 0; //stores the amount of players.
	private static List<Player> currentPlayers; //a list of the current players.

	public TextClient textClient; //an instance of the textClient.
	public static boolean askSuccess; //TODO? WHAT IS THIS??
	private static boolean hasAsked = false; //if a player has asked or not.

	private List<Integer> diceList = new ArrayList<Integer>(Arrays.asList(2,3,4,5,6,7,8,9,10,11,12));
	private int currentRoll = diceList.get(0);
	private Player currentPlayer; //the current player of the round.

	/** If player has made a move*/
	private boolean moveMade = false;
	
	/** This helps generate a random shuffle for the lists */
	private long seed = System.nanoTime();

	/**Stores player's previous option*/
	private String prevOption = "";

	public CluedoGame(){
		currentPlayers = new ArrayList<Player>();
		initializer = new Initializer();
		board = new Board();
	}

	/**
	 * Alternative constructor to allow testing.
	 */
	public CluedoGame(boolean test){
		if(test){
			board = new Board();
			currentPlayers = new ArrayList<Player>();
			initializer = new Initializer();
			initialSetup();
			//runGame();
			
		}else{
			System.out.println("Game is not in test mode! Test mode must be set to true to run.");
			System.exit(0);
		}
	}
	
	/**
	 * Gets the list of current Players
	 * @return
	 */
	public static List<Player> currentPlayers(){
		return currentPlayers;
	}
	
	public int numPlayers(){
		return this.numPlayers;
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
	 * Returns a random value between 2-12.
	 * @return
	 */
	public int diceRoll(){
		Collections.shuffle(diceList);
		currentRoll = diceList.get(0);
		return currentRoll;
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
	 * Test class for the initial setup.
	 */
	public void testInitialSetup(){
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
	 * @throws InvalidMove 
	 */
	public void runGame() throws InvalidMove{
		if(askSuccess){
			while(!isGameOver()){
				for(int i = 0; i < currentPlayers.size(); i++){
					moveMade = false;
					currentPlayer = currentPlayers.get(i);
					System.out.println("Player " + currentPlayer.getName() + " starts.");
					System.out.println(currentPlayer.getName() + "'s character piece is " + currentPlayer.getCharacterName() + ".");
					loop: while(!moveMade){
						String option;
						if(prevOption.equals("s")){
							System.out.println("Do you want to end your turn or make an accusation? (Press Y for ending your turn or N for making an accusation)");
							option = TextClient.inputString();
							switch(option){
							case "y":
								System.out.println("You have ended your turn.");
								moveMade = true;
								prevOption = "";
								break loop;
							case "n":
								option = "a";
								break;
							}
						}else{
							option = TextClient.askOption();
						}
						doOption(option, currentPlayer);
					}
				}
			}
		}
	}

	public void doOption(String option, Player p) throws InvalidMove{
		switch(option){
		case "m":
			currentPlayer.setNumberofMoves(diceRoll());
			System.out.println(currentPlayer.getName() + " rolls a " + currentPlayer.numberofMoves() + ".");
			System.out.println(currentPlayer.getName() + "  has " + currentPlayer.numberofMoves() + " moves.");
			if(currentPlayer.isInRoom()){
				System.out.println("Do you want to take the stairs or do you want to get out of the room?");
				System.out.println("Press Y for stairs and N for exiting the room");
				String choice = TextClient.inputString();
				switch(choice){
				case "y":
					board.moveToRoom(p);
					break;
				case "n":
					board.exitRoom(p);
					break;
				}
			}else{
				while(currentPlayer.numberofMoves() > 0){
					System.out.println(currentPlayer.getName() + " (" + currentPlayer.getCharacterName() + ")" + " currently has " + currentPlayer.numberofMoves() + " moves left.");
					System.out.println("current location: " + currentPlayer.position().getX() + ", " + currentPlayer.position().getY());
					TextClient.movementListener(currentPlayer);
					if(currentPlayer.isInRoom()){
						System.out.println("You have entered a room. You will need to wait for your next turn to be able to");
						System.out.println("take the stairs or exit the room.");
						break;
					}
					if(!board.canMove(p) && !p.coordinatesTaken().isEmpty()){
						System.out.println("Sorry you do not have anywhere to move now.");
						break;
					}
				}
				if(currentPlayer.numberofMoves() <= 0){
					System.out.println(currentPlayer.getName() + " has run out of moves.");
				}
			}
			moveMade = true;
			break;
		case "c":
			for(Card c : p.getCards()){
				System.out.println(c.toString());
			}
			break;
		case "a":
			System.out.println("Player " + currentPlayer.getName() + " wishes to make an accusation.");
			makeAccusation(currentPlayer);
			moveMade = true;
			break;
		case "s":
			//TODO: ALSO NEED TO FINISH THIS PART.
			System.out.println("Player " + currentPlayer.getName() + " wishes to make an suggestion.");
			Suggestion sugg = makeSuggestion(currentPlayer);
			
			if(sugg == null){
				System.out.println("You are not in a room to make a suggestion.");
				moveMade = true;
				break;
			}
			/**
			 * TODO: casey, I feel like this is broken.
			 */
			CharacterCard cc = sugg.getCharacterCard();
			WeaponCard wp = sugg.getWeaponCard();
			RoomCard room = sugg.getRoomCard();
			int count = 1;
			//iterate over the players list backwards
			
			for(Player pl : currentPlayers){
				for(Card currentCard : pl.getCards()){
					if(currentCard instanceof CharacterCard){
						if(currentCard.equals(cc)){
							System.out.println("Card matches!");
							count++;
						}
					}else if(currentCard instanceof WeaponCard){
						if(currentCard.equals(wp)){
							System.out.println("Card matches!");
							count++;
						}
					}
				}
			}
//			for(int i = currentPlayers.size()-1; i > 0; --i){
//				for(int j = 0; j < currentPlayers.get(i).getCards().size(); ++j){
//					Card currentCard = currentPlayers.get(i).getCards().get(j);
//					if(currentCard instanceof CharacterCard){
//						if(currentCard.equals(cc)){
//							count++;
//						}
//					}else if(currentCard instanceof WeaponCard){
//						if(currentCard.equals(wp)){
//							count++;
//						}
//					}
//				}				
//			}
			System.out.println("count:" + count);
			prevOption = "s";
			break;
		}
	}

	/**
	 * This makes an accusation.
	 * @param current Player
	 */
	public Suggestion makeSuggestion(Player p){
		return TextClient.askSuggestion(p);
	}
	
	/**
	 * This makes a suggestion.
	 * @param p
	 */
	public Accusation makeAccusation(Player p){
		return TextClient.askAccusation(p);
	}


	public boolean isGameOver(){
		return false;
	}
	
	/**
	 * Indicates an attempt to make an invalid move.
	 *
	 */
	public static class InvalidMove extends Exception {
		public InvalidMove(String msg) {
			super(msg);
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

	public static void main(String[] args) throws InvalidMove{
		CluedoGame game = new CluedoGame();
		game.initialSetup();
		game.runGame();
	}
}