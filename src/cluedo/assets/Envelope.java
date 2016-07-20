package cluedo.assets;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds three Cards. They are stored in the fields of this class.
 * @param A - playerCard
 * @param B - roomCard
 * @param C - weaponCard 
 * 
 */
public class Envelope <A extends PlayerCard,B extends RoomCard,C extends WeaponCard>{
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
	
	public Card[] toArray(){
		Card[] c = new Card[3];
		c[0] = playerCard;
		c[1] = roomCard;
		c[2] = weaponCard;
		return c;
	}
}
