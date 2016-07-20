package cluedo.assets;
/**
 * Class that holds three Cards. They are stored in the fields of this class.
 * @param A - playerCard
 * @param B - roomCard
 * @param C - weaponCard 
 * 
 */
public class Envelope <A extends PlayerCard,B extends RoomCard,C extends WeaponCard> {
	private A playerCard;
	private B roomCard;
	private C weaponCard;

	public Envelope(A p, B rm, C wp){
		this.playerCard = p;
		this.roomCard = rm;
		this.weaponCard = wp;
	}
	
	public A getPlayerCard(){
		return this.playerCard;
	}
	
	public B getRoomCard(){
		return this.roomCard;
	}
	
	public C getWeaponCard(){
		return this.weaponCard;
	}
}
