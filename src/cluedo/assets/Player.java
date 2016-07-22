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
	private String characterName;
	private int x;
	private int y;

	/**
	 * Create a Player with a given name, a hand, a current character, and their x and y position.
	 * 
	 * @param name
	 * @param c
	 * @param h
	 * @param x
	 * @param y
	 */
	public Player(String name, Character c, List<Card> h, int x, int y){
		this.name = name;
		this.hand = h;
		this.characterName = c.getName();
		this.x = x;
		this.y = y;
	}

	/**
	 * Set player's X and Y position.
	 * @param x
	 */
	public void setPos(int x, int y){
		this.x = this.x + x;
		this.y = this.y + y;
	}

	/**
	 * Get player's x position.
	 * @return
	 */
	public int getX(){
		return this.x;
	}

	/**
	 * Get player's y position.
	 * @return
	 */
	public int getY(){
		return this.y;
	}

	/**
	 * Return name of player.
	 * @return
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Return name of character name.
	 * @return
	 */
	public String getCharacterName(){
		return this.characterName;
	}

	/**
	 * Return list of cards of player.
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
