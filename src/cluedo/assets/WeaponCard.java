package cluedo.assets;
/**
 * This class represents a card holding a Weapon object.
 * @author linus
 *
 */
public class WeaponCard implements Card{
	private Weapon weapon;
	
	public WeaponCard(Weapon w){
		this.weapon = w;
	}
	
	public String toString(){return this.weapon.toString();}
	
}
