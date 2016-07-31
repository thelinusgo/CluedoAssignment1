package cluedo.arguments;

import java.util.List;

import cluedo.assets.Player;
import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.cards.Envelope;
import cluedo.cards.RoomCard;
import cluedo.cards.WeaponCard;

public class Accusation extends Argument{

	public Accusation(WeaponCard weapon, RoomCard room, CharacterCard character) {
		super(weapon, room, character);
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

		for(Card cards: env.getEnvelope()){
			for(Card playersCard : p.getCards()){
				if(playersCard instanceof RoomCard){
					if(playersCard.equals(room)){
						valid = true;
					}else{
						valid = false;
					}
				}else if(playersCard instanceof WeaponCard){
					if(playersCard.equals(weapon)){
						valid = true;
					}else{
						valid = false;
					}
				}else if(playersCard instanceof CharacterCard){
					if(playersCard.equals(character)){
						valid = true;
					}else{
						valid = false;
					}
				}
			}
		}
		return valid;
	}
}