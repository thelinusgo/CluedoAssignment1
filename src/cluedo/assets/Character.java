package cluedo.assets;
/**
 * Class that represents a player in the game. Contains a name.
 * @author linus
 *
 */
public class Character {
	
	private String name;
	
	public Character(String name){
		this.name = name;
	}
	/**
	 * Returns the name of the character.
	 * @return
	 */
	public String getName(){
		switch(this.name){
			case "Miss Scarlett": return "S";
			case "Colonel Mustard": return "C";
			case "Mrs. White": return "W";
			case "The Reverend Green": return "G";
			case "Mrs. Peacock": return "P";
			case "Professor Plum": return "R";
		}
		return null;
	}
	
	@Override
	public String toString(){
		return "Player: " + name;
	}
	
}
