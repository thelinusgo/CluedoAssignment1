package cluedo.assets;
/**
 * This class represents a Card containing a player object.
 * @author linus
 *
 */
public class CharacterCard implements Card{
	private Character player;
	
	public CharacterCard(Character player){
		this.player = player;
	}
	
	public String toString(){return "Card: " + this.player.toString();}
	
	@Override
	public String getName() {
		return player.toString();
	}
	@Override
	public Character getObject() {
		return this.player;
	}
	
	
}
