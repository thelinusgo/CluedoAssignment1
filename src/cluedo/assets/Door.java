package cluedo.assets;

/**
 * Door object for determining which way a door is facing.
 * 
 * @author oznaprazzi
 *
 */
public class Door {
	private boolean isHorizontal;
	private Position position;
	private String s;
	
	/**
	 * Stores the room that this door belongs to.
	 */
	private Room room;
	
	/**
	 * Stores where the front of the door is.
	 */
	private Position inFront;
	
	public Door(boolean direction, int x, int y, Room rm, String s){
		this.isHorizontal = direction;
		this.position = new Position(x, y);
		this.room = rm;
		this.s = s;
	}
	
	/**
	 * Returns which direction the door is facing.
	 * @return
	 */
	public boolean isHorizontal(){
		return this.isHorizontal;
	}
	
	/**
	 * Sets the door's front.
	 * @param p
	 */
	public void setInFront(Position p){
		this.inFront = p;
	}
	
	/**
	 * Returns the door's front.
	 * @return
	 */
	public Position getInFront(){
		return this.inFront;
	}
	
	/**
	 * Returns the position of this door.
	 * @return
	 */
	public Position getPosition(){
		return this.position;
	}
	
	/**
	 * Returns the room that this door belongs to.
	 */
	public Room getRoom(){
		return this.room;
	}
	
	/**
	 * Returns the string of the door.
	 */
	public String getString(){
		return this.s;
	}
}
