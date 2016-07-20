package cluedo.assets;
/**
 * Class that represents a Weapon.
 * A weapon belongs to a room.
 * @author linus
 *
 */
public class Weapon{
	
	private String weaponName;
	
	public Weapon(String w){
		this.weaponName = w;
	}
	@Override
	public String toString(){
		return "Weapon: " + this.weaponName;
	}
	
	public String weaponName(){return this.weaponName;}
}
