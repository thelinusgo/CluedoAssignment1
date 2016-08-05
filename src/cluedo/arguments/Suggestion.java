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
		boolean correct = false;
		RoomCard roomCard = super.getRoomCard();
		CharacterCard cc = super.getCharacterCard();
		WeaponCard wp = super.getWeaponCard();

		for(Player p : players){
			playerHand = p.getCards();
			for(Card card : playerHand){
				if(card.equals(super.getCharacterCard())){
					Room r1 = roomCard.getObject();
					Room r2 = cc.getObject().getRoom();
					Character c1 = cc.getObject();
					Character c2 = r1.getCharacter();
					c1.addRoom(r1);
					if(c2 != null){
						c2.addRoom(r2);
					}else if(r2 != null){
						r2.addCharacter(c2);
					}
					r1.addCharacter(c1);
					for(Player player : CluedoGame.getCurrentPlayers()){
						if(!player.equals(p)){
							if(player.getCharacter().equals(cc.getObject())){
								CluedoGame.board.moveToRoom(p, roomCard.getObject());
							}
						}
					}
					correct = true;
				}else if(card.equals(super.getWeaponCard())){
					Room r1 = roomCard.getObject();
					Room r2 = wp.getObject().getRoom();
					Weapon w1 = wp.getObject();
					Weapon w2 = r1.getWeapon();
					w1.addRoom(r1);
					if(w2 !=  null){
						w2.addRoom(r2);
					}
					r1.addWeapon(w1);
					r2.addWeapon(w2);
					correct = true;
				}else if(card.equals(super.getRoomCard())){
					correct = true;
				}
			}
		}
		return correct;
	}

}
