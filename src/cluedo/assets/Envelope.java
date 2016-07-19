package cluedo.assets;
/**
 * Class that holds three Cards. They are stored in the fields of this class and 
 * filled out when this class is instantiated (someone makes a new instance of this class.)
 * @author linus 
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
