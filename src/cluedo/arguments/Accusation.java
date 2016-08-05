package cluedo.arguments;

import cluedo.assets.Player;
import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.cards.Envelope;
import cluedo.cards.RoomCard;
import cluedo.cards.WeaponCard;

/**
 * Class that represents an Accusation. It extends an Argument, and has a checkAccusation method.
 * @author linus
 *
 */
public class Accusation extends Argument{

	private boolean validAccusation = false;
	private Envelope env;

	public Accusation(WeaponCard weapon, RoomCard room, CharacterCard character, Player p, Envelope env) {
		super(weapon, room, character, p);
		this.env = env;
		validAccusation = checkAccusation(env, p);
	}

	/**
	 * This checks if the current accusation was correct.
	 * @return
	 */
	public boolean checkAccusation(Envelope env, Player p){
		WeaponCard weapon = super.getWeaponCard();
		RoomCard room = super.getRoomCard();
		CharacterCard character = super.getCharacterCard();
		boolean valid = false;
		for(Card card: env.getEnvelope()){
			if(card instanceof RoomCard){
				if(card.equals(room)){
					valid = true;
				}else{
					valid = false;
				}
			}else if(card instanceof WeaponCard){
				if(card.equals(weapon)){
					valid = true;
				}else{
					valid = false;
				}
			}else if(card instanceof CharacterCard){
				if(card.equals(character)){
					valid = true;
				}else{
					valid = false;
				}
			}
		}
		return valid;
	}

	/**
	 * Returns the status of this accusation.
	 * @return
	 */
	public boolean accusationStatus(){
		return this.validAccusation;
	}



	@Override
	public String toString(){
		return "Player: " + super.getCurrentPlayer().getName() + " accuses " + super.getCharacterCard().getName() + " of murder; using a " + super.getWeaponCard().getName() +
				" in the " + super.getRoomCard().getName();
	}
}