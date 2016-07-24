package cluedo.assets;
/**
 * This class represents a Card holding a Room object.
 * @author linus
 *
 */
public class RoomCard extends Card<Room>{
	public RoomCard(Room r){
		super(r);
	}
	
	@Override
	public String toString(){
		return "Card: " + super.getName();
	}
}
