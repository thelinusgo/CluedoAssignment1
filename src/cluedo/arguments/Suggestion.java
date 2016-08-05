package cluedo.arguments;

import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.cards.RoomCard;
import cluedo.cards.WeaponCard;
import cluedo.main.CluedoGame;
import cluedo.assets.*;
import cluedo.assets.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a suggestion. It is a subtype of an argument.
 * @author linus
 *
 */
public class Suggestion extends Argument {
	
	/**
	 * Construct a new suggestion.
	 * @param w
	 * @param r
	 * @param c
	 */
	public Suggestion(WeaponCard w, RoomCard r, CharacterCard c, Player p) {
		super(w, r, c, p);
	}
	
	/**
	 * This checks if the suggestion is Correct by iterating over the list of players, and their hand.
	 * @param players
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean checkSuggestion(List<Player> players){
		if(players == null) throw new IllegalArgumentException("List of players cannot be null");
		List<Card> playerHand;
		RoomCard roomCard = super.getRoomCard();
		Room room = roomCard.getObject();
		CharacterCard cc = super.getCharacterCard();
		Character c = cc.getObject();
		for(Player p : players){
			playerHand = p.getCards();
			for(Card card : playerHand){
				if(card.equals(super.getCharacterCard())){
					room.getWeapon().addRoom(cc.getObject().getRoom());
					cc.getObject().getRoom().addCharacter(room.getCharacter());
					room.addCharacter(cc.getObject());
					cc.getObject().addRoom(room);
					for(Player player : CluedoGame.getCurrentPlayers()){
						if(!player.equals(p)){
							if(player.getCharacter().equals(cc.getObject())){
								CluedoGame.board.moveToRoom(p, roomCard.getObject());
							}
						}
					}
					return true;
				}else if(card.equals(super.getWeaponCard())){
					WeaponCard wp = super.getWeaponCard();
					roomCard.getObject().getWeapon().addRoom(wp.getObject().getRoom());
					wp.getObject().getRoom().addWeapon(roomCard.getObject().getWeapon());
					roomCard.getObject().addWeapon(wp.getObject());
					wp.getObject().addRoom(roomCard.getObject());
					return true;
				}else if(card.equals(super.getRoomCard())){
					return true;
				}
			}
		}
		return false;
	}
	
}
