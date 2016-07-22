package cluedo.assets;
/**
 * Abstract class that contains all of the Cards.
 * @author linus
 * @param <E>
 *
 */
public abstract class Card<E> {
	
	private E item;
	
	public Card(E itm){
		this.item = itm;
	}
	
	/**
	 * Returns a toString representation of this card.
	 * @return
	 */
	public String toString(){
		return "Card: " + item.toString();
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
