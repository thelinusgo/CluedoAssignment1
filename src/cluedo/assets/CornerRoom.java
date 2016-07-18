package cluedo.assets;

public class CornerRoom extends Room{
	
	private Room other; //a reference to the other corner room.
	
	public CornerRoom(String n, Weapon w, Room other){
	super(n,w);
	this.other = other;
	}
	
	
}