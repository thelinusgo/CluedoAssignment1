package cluedo.assets;

import java.util.List;

/**
 * Class that represents a player in Cluedo.
 * This consists of a hand, and a players name, and if they're out of the game or not.
 * @author linus
 *
 */
public class Player {
	
	private List<Card> hand;
	private Character currentCharacter;
	private boolean isOut = false;
	private String name;
	
	/**
	 * Create a Player with a given name, a hand and a current character.
	 * @param n
	 * @param hand
	 */
	public Player(String n, List<Card> h, Character c){
		this.name = n;
		this.hand = h;
		this.currentCharacter = c;
	}
	
	/**
	 * Create a Player with just a given name and a current Character.
	 */
	public Player(String n, Character ch){
		this.name = n;
		this.currentCharacter = ch;
	}
	
	/**
	 * This sets the current player to be out.
	 */
	public void setOut(){
		this.isOut = true;
	}

	
	
	
	
}
