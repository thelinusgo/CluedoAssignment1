package cluedo.assets;

import java.util.*;

import cluedo.cards.Card;

/**
 * Class that represents a player in Cluedo.
 * This consists of a hand, and a players name, and if they're out of the game or not.
 * @author linus
 *
 */
public class Player {

	private List<Card> hand; //Holds the hand 
	private boolean isOut = false;
	private String name;
	private String characterName;
	private int x;
	private int y;
	private int numberofMoves;
	
	/**
	 * Create a Player with a given name, a hand, a current character, and their x and y position.
	 * 
	 * @param name
	 * @param c
	 * @param h
	 * @param x
	 * @param y
	 */
	public Player(String name){
		this.name = name;
		hand = new ArrayList<>();
	}
	
	/**
	 * Sets the amount of moves a player may have.
	 */
	public void setNumberofMoves(int amount){
		if(amount > 12 || amount < 2) throw new IllegalArgumentException("player can have only 2-12 moves.");
		this.numberofMoves = amount;
	}
	
	/**
	 * This allows the player to move a step.
	 */
	
	public void moveAStep(){
		this.numberofMoves--;
	}
	/**
	 * Returns the amount of moves a player currently has.
	 * 
	 */
	public int numberofMoves(){
		return this.numberofMoves;
	}
	
	/**
	 * Assigns a character to a player.
	 * E.g. Linus gets Professor Plum.
	 * @param c
	 */
	public void setCharacter(Character c){
		this.characterName = c.getName();
	}
	
	/**
	 * Adds a card to the a players hand.
	 * @param c
	 */
	public void addCard(Card c){
		this.hand.add(c);
	}
	
	/**
	 * Set player's X and Y position.
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
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
	
	public String toString(){
		return "Name: " + this.name + ", Character Piece: " + this.characterName;
	}
}
