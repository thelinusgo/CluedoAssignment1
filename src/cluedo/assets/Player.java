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
	private boolean isOut = false;
	private String name;
	
	/**
	 * Create a Player with a given name, a hand and a current character.
	 * @param n
	 * @param hand
	 */
	public Player(Character ch, List<Card> h){
		this.name = ch.getName();
		this.hand = h;
	}
	
	/**
	 * Return name of player
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Return list of cards of player
	 * @return
	 */
	public List<Card> getCards(){
		return this.hand;
	}
	
	/**
	 * This sets the current player to be out.
	 */
	public void setOut(){
		this.isOut = true;
	}
}
