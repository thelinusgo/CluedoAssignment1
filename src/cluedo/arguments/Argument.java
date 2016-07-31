package cluedo.arguments;

import cluedo.cards.*;

/**
 * Class that represents arguments - that is, either a suggestion or an accusation in Cluedo.
 * 
 * @author linus + casey
 *
 */
public class Argument {
	private WeaponCard weapon;
	private RoomCard room;
	private CharacterCard character;
	private boolean isArgumentCorrect = false;
	
	/**
	 * Constructs an argument object, consisting of a weapon, a room and a character
	 * @param w
	 * @param r
	 * @param c
	 */
	public Argument(WeaponCard w, RoomCard r, CharacterCard c){
		this.weapon = w;
		this.room = r;
		this.character = c;
	}
	
	/**
	 * Returns the status of the argument.
	 * @return
	 */
	public boolean argumentStatus(){
		return this.isArgumentCorrect;
	}
	
	/**
	* Sets the status of this argument.
	 * @return
	 */
	public void setArgumentStatus(boolean val){
		this.isArgumentCorrect = val;
	}
	
	
	/**
	 * gets the WeaponCard object.
	 * @return
	 */
	public WeaponCard getWeaponCard() {
		return weapon;
	}

	/**
	 * gets the roomCard object.
	 * @return
	 */
	public RoomCard getRoomCard() {
		return room;
	}
	
	/**
	 * gets the characterCard object.
	 * @return
	 */
	public CharacterCard getCharacterCard() {
		return character;
	}





	
	
	
	
}
