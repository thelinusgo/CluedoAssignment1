package cluedo.assets;
/**
 * Interface to Manage all of the 
 * @author linus
 * @param <E>
 *
 */
public interface Card<E> {
	/**
	 * Returns a toString representation of this card.
	 * @return
	 */
	public String toString();
	
	/**
	 * Returns the name of this card.
	 * @return
	 */
	public String getName();
	
	/**
	 * Gets the item being currently held by this card.
	 * @return
	 */
	public E getObject();
}