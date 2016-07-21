package cluedo.assets;
/**
 * Class that represents a Weapon.
 * A weapon belongs to a room.
 * @author linus
 *
 */
public class Weapon{
	/**
	 * the name of the weapon.
	 */
	private String weaponName;
	/**
	 * Create a new weapon. The string name cannot be null.
	 * @param w
	 */
	public Weapon(String w){
		assert w!=null: "weapon name cannot be null";
		this.weaponName = w;
	}
	
	@Override
	public String toString(){
		return "Weapon: " + this.weaponName;
	}
	/**
	 * Returns the weaponName of this weapon.
	 * @return
	 */
	public String weaponName(){return this.weaponName;}
}
