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
	
	public String toString(){return weapon.toString();}
	
	@Override
	public String getName(){ return weapon.weaponName();}

	@Override
	public Weapon getObject() {
		return this.weapon;
	}
}
