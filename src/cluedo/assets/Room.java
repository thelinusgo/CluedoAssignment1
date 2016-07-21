package cluedo.assets;
/**
 * Room Class. Stores the name of the room, and the weapon it is currently holding.
 * @author linus
 *
 */
public class Room {
	
	/**
	 * The name of the Room.
	 */
	private String name;
	
	/**
	 * The weapon that the room is in.
	 */
	private Weapon weapon = null;
	
	/*
	 * Construct a Room.
	 */
	public Room(String n){
		this.name = n;
	}
	
	/**
	 * Add weapon to room
	 * @param w
	 */
	public void addWeapon(Weapon w){
		this.weapon = w;
	}
	
	/**
	 * Returns a toString representation of this Room.
	 */
	@Override
	public String toString(){
		if(this.weapon != null){
			return "[Room: " + name + " | Weapon: " + weapon.weaponName() + "]";
		}
		return "[Room: " + name + " | Weapon: null]";
	}
	
	/**
	 * Gets the weapon being held in this current room.
	 * @return
	 */
	public Weapon getWeapon(){
		return this.weapon;
	}
}
