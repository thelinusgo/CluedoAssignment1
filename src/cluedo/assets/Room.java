package cluedo.assets;

import java.util.*;

/**
 * Room Class. Stores the name of the room, and the weapon it is currently holding.
 * @author linus
 *
 */
public class Room {
	
	/**
	 * The name of the Room.
	 */
	private String name;
	
	/**
	 * The weapon that the room is in.
	 */
	private Weapon weapon = null;
	
	/**
	 * The x position of this room.
	 */
	private int x;
	
	/**
	 * The y position of this room.
	 */
	private int y;
	
	/**
	 * The width of this room.
	 */
	private int width;
	
	/**
	 * The height of this room.
	 */
	private int height;

	/**
	 * Stores the doors that belong to this room.
	 */
	private List<Door> doors;
	
	/**
	 * Coordinates that players will be placed in if they have entered the room
	 */
	private Position[] playerCoords;
	
	/**
	 * Maps players to coordinates.
	 */
	private Map<Player, Position> playerMap;
	
	/**
	 * Construct a Room.
	 */
	public Room(String n){
		this.name = n;
	}
	
	
	
	/**
	 * Construct a Room with x, y coordinates, its width and height and whether it has stairs or not.
	 */
	public Room(String n, int x, int y, int width, int height){
		this.name = n;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		playerCoords = new Position[6];
		doors = new ArrayList<Door>();
		playerMap = new HashMap<>();
		addPlayerCoords();
	}
	
	private void addPlayerCoords(){
		int j = 0;
		int k = 0;
		for(int i = 0; i < playerCoords.length; i++){
			playerCoords[i] = new Position(this.width/2+k, this.height/2+j);
			j++;
			if(j == 1){
				j = 0;
				k++;
			}
		}
	}
	
	/**
	 * Add player and position to Map.
	 */
	public void addMap(Player p){
		for(Map.Entry<Player, Position> e : playerMap.entrySet()){
			for(Position pos : playerCoords){
				if(e.getValue().getX() != pos.getX() && e.getValue().getY() != pos.getY()){
					playerMap.put(p, pos);
				}
			}
			
		}
	}
	
	/**
	 * Returns the map.
	 */
	public Map<Player, Position> getMap(){
		return playerMap;
	}
	
	/**
	 * Add weapon to room
	 * @param w
	 */
	public void addWeapon(Weapon w){
		this.weapon = w;
	}
	
	/**
	 * Adds a door object to the doors ArrayList.
	 * @param d
	 */
	public void addDoors(Door d){
		this.doors.add(d);
	}
	
	/**
	 * Returns the doors ArrayList.
	 * @return
	 */
	public List<Door> getDoors(){
		return this.doors;
	}
	
	/**
	 * Returns whether a co-ordinate in the room is contained in this room.
	 * Returns false otherwise. 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean contains(int x, int y){
		if(this.x <= x && this.x + width > x && this.y <= y && this.y + height > y){
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the weapon being held in this current room.
	 * @return
	 */
	public Weapon getWeapon(){
		return this.weapon;
	}
	
	/**
	 * Returns a toString representation of this Room.
	 */
	@Override
	public String toString(){
		if(this.weapon != null){
			return "[Room: " + name + " | Weapon: " + weapon.weaponName() + "]";
		}
		return "[Room: " + name + " | Weapon: null]";
	}
	
	public String stringName(){
		return "[Room: " + name + "]";
	}
}
