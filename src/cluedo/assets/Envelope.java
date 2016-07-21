package cluedo.assets;

/**
 * Class that holds three Cards. They are stored in the fields of this class.
 * @param A - playerCard
 * @param B - roomCard
 * @param C - weaponCard 
 * 
 */
public class Envelope <A extends PlayerCard,B extends RoomCard,C extends WeaponCard>{
	/*These store the three types of Cards required for this envelope */
	
	private A playerCard;
	private B roomCard;
	private C weaponCard;

	public Envelope(A p, B rm, C wp){
		this.playerCard = p;
		this.roomCard = rm;
		this.weaponCard = wp;
	}
	/**
	 * Gets the player from the Envelope.
	 * @return
	 */
	public A getPlayerCard(){
		return this.playerCard;
	}
	
	/**
	 * Gets the Room Card from this envelope.
	 * @return
	 */
	public B getRoomCard(){
		return this.roomCard;
	}
	/**
	 * Gets the weapon card from this envelope.
	 * @return
	 */
	public C getWeaponCard(){
		return this.weaponCard;
	}
	
	/**
	 * Returns the envelope in Array form.
	 * @return
	 */@SuppressWarnings("rawtypes")
	public Card[] toArray(){
		Card[] c = new Card[3];
		c[0] = playerCard;
		c[1] = roomCard;
		c[2] = weaponCard;
		return c;
	}
}
