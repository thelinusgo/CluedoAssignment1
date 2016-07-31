package cluedo.arguments;

import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.cards.RoomCard;
import cluedo.cards.WeaponCard;
import cluedo.assets.*;
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
		for(Player player : players){
			playerHand = player.getCards();
			for(Card card : playerHand){
				if(card.equals(super.getCharacterCard()) || card.equals((super.getRoomCard())) || card.equals(((super.getWeaponCard())))){
					return true;
				}
			}
		}
	return false;
	}
	
}
