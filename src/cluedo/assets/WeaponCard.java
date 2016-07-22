package cluedo.assets;
/**
 * This class represents a card holding a Weapon object.
 * @author linus
 *
 */
public class WeaponCard extends Card<Weapon>{
	//private Weapon weapon;
	/**
	 * Construct a new weapon card, with a given weapon
	 * @param Weapon w
	 */
	public WeaponCard(Weapon w){
		super(w);
	}
	
//	@Override
//	public String toString(){return weapon.toString();}
//	
//	@Override
//	public String getName(){ return weapon.weaponName();}
//
//	@Override
//	public Weapon getObject() {
//		return this.weapon;
//	}
}
