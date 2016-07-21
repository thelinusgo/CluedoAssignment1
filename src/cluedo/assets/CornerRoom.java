package cluedo.assets;
/**
 * Class that represents the Corner Room. It is the same as a regular room but contains a reference to another room.
 * @author linus
 *
 */
public class CornerRoom extends Room{
	/**
	 * A reference to the other corner Room.
	 */
	private Room other;
	
	/**
	 * Constructor for a Corner room.
	 * @param String name
	 * @param Weapon weapon
	 * @param Room other
	 */
	public CornerRoom(String n, Weapon w, Room other){
		super(n);
		super.addWeapon(w);
		if(other != null){
			this.other = other;
		}
	}
	
	
}