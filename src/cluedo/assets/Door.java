package cluedo.assets;

/**
 * Door object for determining which way a door is facing.
 * 
 * @author oznaprazzi
 *
 */
public class Door {
	private boolean isHorizontal;
	private int x;
	private int y;
	
	public Door(boolean direction, int x, int y){
		this.isHorizontal = direction;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns which direction the door is facing.
	 * @return
	 */
	public boolean isHorizontal(){
		return this.isHorizontal;
	}
	
	/**
	 * Returns the x position of the door.
	 * @return
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Returns the y position of the door.
	 * @return
	 */
	public int getY(){
		return this.y;
	}
}
