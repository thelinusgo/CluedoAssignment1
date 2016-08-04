package cluedo.assets;
/**
 * Class that represents a player in the game. Contains a name.
 * @author linus
 *
 */
public class Character {
	
	private String name;
	
	private Room room;
	
	public Character(String name){
		this.name = name;
	}
	
	/**
	 * Returns the name of the character.
	 * @return
	 */
	public String getName(){
		switch(this.name){
			case "Miss Scarlett": return "M";
			case "Colonel Mustard": return "C";
			case "Mrs. White": return "W";
			case "The Reverend Green": return "G";
			case "Mrs. Peacock": return "P";
			case "Professor Plum": return "R";
		}
		return null;
	}
	
	/**
	 * Returns the full String name of this character.
	 * @return
	 */
	public String name(){
		return this.name;
	}
	
	/**
	 * Stores the room that this player is in.
	 */
	public void addRoom(Room rm){
		this.room = rm;
	}
	
	/**
	 * Returns the room that this player belongs to.
	 * @return
	 */
	public Room getRoom(){
		return this.room;
	}
	
	@Override
	public String toString(){
		return "Character: " + name;
	}
	
}
