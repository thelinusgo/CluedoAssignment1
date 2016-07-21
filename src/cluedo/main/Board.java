package cluedo.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import cluedo.assets.Card;
import cluedo.assets.Envelope;
import cluedo.assets.Character;
import cluedo.assets.CharacterCard;
import cluedo.assets.Room;
import cluedo.assets.RoomCard;
import cluedo.assets.Weapon;
import cluedo.assets.WeaponCard;
/**
 * Class that represents the Board. Contains fields and methods regarding setting up the board.
 * @author linus & casey
 *
 */
public class Board {

	/*Lists that hold components of the board.. */

	private static List<Room> rooms = new ArrayList<>();
	private static List<Weapon> weapons = new ArrayList<>();
	private static List<Card> cards = new ArrayList<>();
	private static List<Character> players = new ArrayList<>();
	
	private static Envelope envelope = new Envelope();
	
	/**
	 * An Array representing an envelope of cards.
	 * The first element will always be a Room, second will be a weapon, and third will always be a card.
	 */
	/**
	 * Construct a new Board
	 */
	public Board(){

		initializeData();

		RoomCard roomCard = null;
		CharacterCard characterCard = null;
		WeaponCard weaponCard = null;

		for(int i = 0 ; i != cards.size(); i++){
			Card currentCard = cards.get(i);
			if(currentCard instanceof RoomCard){
				roomCard = (RoomCard) currentCard;
//				if(roomCard.getObject().getWeapon() == null){
//					
//				}
				if(roomCard.getObject().getWeapon() != null){
					weaponCard = new WeaponCard(roomCard.getObject().getWeapon());
				}
			}else if(currentCard instanceof CharacterCard){
				characterCard = (CharacterCard) currentCard;
			}
		}
		envelope.add(weaponCard);
		envelope.add(characterCard);
		envelope.add(roomCard);
	}
	/**
	 * Initializes all of the data in the arraylists.
	 */
	public void initializeData(){
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
		Collections.shuffle(rooms, new Random(seed)); //shuffle it

		/*Fill the ArrayList with people.. */
		players.add(new Character("Miss Scarlett"));
		players.add(new Character("Colonel Mustard"));
		players.add(new Character("Mrs. White"));
		players.add(new Character("The Reverend Green"));
		players.add(new Character("Mrs. Peacock"));
		players.add(new Character("Professor Plum"));
		Collections.shuffle(players, new Random(seed)); //shuffle it


		/*Fill the cards arrayList with Room Cards */
		for(Room r : rooms){
			cards.add(new RoomCard(r));
		}
		/*Fill the cards arrayList with Weapon Cards */
		for(Weapon w : weapons){
			cards.add(new WeaponCard(w));
		}

		/*Fill the cards ArrayList with Player Cards */
		for(Character p : players){
			cards.add(new CharacterCard(p));
		}
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
		System.out.println("----------------");
		for(int i = 0 ; i < 3; i++){
			System.out.println("[ENVELOPE] : " + envelope.get(i).toString() + " ");
		}




	}





}
