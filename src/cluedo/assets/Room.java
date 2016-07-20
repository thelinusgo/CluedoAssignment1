package cluedo.assets;

public class Room {
	private String name;
	private Weapon weapon;
	
	/*
	 * Construct a Room.
	 */
	public Room(String n, Weapon w){
		this.name = n;
		this.weapon = w;
	}
	
	/**
	 * Returns a toString representation of this Room.
	 */
	@Override
	public String toString(){
		return "[Room: " + name + " | Weapon: " + weapon.weaponName() + "]";
	}
	
	/**
	 * Gets the weapon being held in this current room.
	 * @return
	 */
	public Weapon getWeapon(){
		return this.weapon;
	}
}
