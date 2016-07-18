package cluedo.assets;
/**
 * This class represents a Card containing a player object.
 * @author linus
 *
 */
public class PlayerCard implements Card{
	private Player player;
	
	public PlayerCard(Player player){
		this.player = player;
	}
	public String toString(){return "Card: " + this.player.toString();}
}
