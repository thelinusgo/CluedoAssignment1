package cluedo.assets;
/**
 * Abstract class that contains all of the Cards.
 * @author linus
 * @param <E>
 *
 */
public abstract class Card<E> {
	
	private E item; //This represents the Item being held in the card.
	
	/**
	 * Construct a new Card with the given item inside.
	 * @param itm
	 */
	public Card(E itm){
		assert itm != null : "Item in card CANNOT be null!";
		this.item = itm;
	}
	
	/**
	 * Returns a toString representation of this card.
	 * @return
	 */
	public String toString(){
		return "Card: [" + item.toString() + "]";
	}
	
	/**
	 * Returns the name of this card.
	 * @return
	 */
	public String getName(){
		return item.toString();
	}
	
	/**
	 * Gets the item being currently held by this card. For example, a RoomCard's getObject() call would return a Room object.
	 * @return
	 */
	public E getObject(){
	return this.item;	
	}
}
