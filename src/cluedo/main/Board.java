package cluedo.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import cluedo.assets.Room;
import cluedo.assets.Weapon;
/**
 * Class that represents the Board. Contains fields and methods regarding setting up the board.
 * @author linus & casey
 *
 */
public class Board {

	/*List of Rooms and their Weapons. */
	
	private static List<Room> rooms = new ArrayList<>();
	private static List<Weapon> weapons = new ArrayList<>();
	
	/**
	 * Construct a new Board
	 */
	public Board(){
		/*Fill the arraylist with weapons*/
		weapons.add(new Weapon("Candlestick"));
		weapons.add(new Weapon("Dagger"));
		weapons.add(new Weapon("Lead Pipe"));
		weapons.add(new Weapon("Revolver"));
		weapons.add(new Weapon("Rope"));
		weapons.add(new Weapon("Spanner"));
		weapons.add(new Weapon(null)); //7
		weapons.add(new Weapon(null)); //8
		weapons.add(new Weapon(null)); //9

		/*Shuffle it so that a weapon will be in a random room each time. */
		long seed = System.nanoTime();
		Collections.shuffle(weapons, new Random(seed)); //shuffle it
		
		/*Fill the arraylist with rooms */
		/*NB: not all rooms have weapons.  */
		rooms.add(new Room("Kitchen",weapons.get(0)));
		rooms.add(new Room("Ball Room",weapons.get(1)));
		rooms.add(new Room("Conservatory",weapons.get(2)));
		rooms.add(new Room("Billiard Room", weapons.get(3)));
		rooms.add(new Room("Library", weapons.get(4)));
		rooms.add(new Room("Study", weapons.get(5)));
		rooms.add(new Room("Hall", weapons.get(6)));
		rooms.add(new Room("Lounge", weapons.get(7)));
	}
	
	
	
	
	/*Very small test class */
	public static void main(String[] argv){
		new Board();

		System.out.println("ROOM ARRAYLIST: ");
		int index = 0;
		for(Room r : rooms){
			System.out.println(index + ": " + r.toString());
			index++;
		}
		System.out.println("----------------");
		System.out.println("WEAPON ARRAYLIST: ");
		index =0;
		for(Weapon w : weapons){
			System.out.println(index + ": " +w.toString());
			index++;
		}
	}
	
	
	
	
	
}
