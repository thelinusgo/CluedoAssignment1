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
public class Initializer {

	/**Lists that hold components of the board */
	private static List<Room> rooms = new ArrayList<>();
	private static List<Weapon> weapons = new ArrayList<>();
	//private static List<Card> cards = new ArrayList<>();
	private static List<Card> cards = new ArrayList<>();
	private static List<Character> characters = new ArrayList<>();
	private static Envelope envelope = new Envelope();

	/** This helps generating a random shuffle for the lists */
	private long seed = System.nanoTime();

	/**
	 * Construct a new Board
	 */
	public Initializer(){
		initializeWeapons();
		initializeRooms();
		initializeCharacters();
		fillList();
		initializeEnvelope();
	}

	/**
	 * Initializes the weapons list.
	 */
	public void initializeWeapons(){
		/*Fill the arraylist with weapons*/
		weapons.add(new Weapon("Candlestick"));
		weapons.add(new Weapon("Dagger"));
		weapons.add(new Weapon("Lead Pipe"));
		weapons.add(new Weapon("Revolver"));
		weapons.add(new Weapon("Rope"));
		weapons.add(new Weapon("Spanner"));

		/*Shuffle it so that a weapon will be in a random room each time. */
		Collections.shuffle(weapons, new Random(seed)); //shuffle it
	}

	/**
	 * Initializes the rooms list.
	 */
	public void initializeRooms(){
		/*Fill the arraylist with rooms */
		/*NB: not all rooms have weapons.  */
		rooms.add(new Room("Kitchen"));
		rooms.add(new Room("Ball Room"));
		rooms.add(new Room("Conservatory"));
		rooms.add(new Room("Billiard Room"));
		rooms.add(new Room("Library"));
		rooms.add(new Room("Study"));
		rooms.add(new Room("Hall"));
		rooms.add(new Room("Lounge"));
		Collections.shuffle(rooms); //shuffle it

		for(int i = 0; i < weapons.size(); i++){
			rooms.get(i).addWeapon(weapons.get(i));
		}
		
		Collections.shuffle(weapons, new Random(seed));
	}

	/**
	 * Initializes the characters list.
	 */
	public void initializeCharacters(){
		/*Fill the ArrayList with people.. */
		characters.add(new Character("Miss Scarlett"));
		characters.add(new Character("Colonel Mustard"));
		characters.add(new Character("Mrs. White"));
		characters.add(new Character("The Reverend Green"));
		characters.add(new Character("Mrs. Peacock"));
		characters.add(new Character("Professor Plum"));
		Collections.shuffle(characters, new Random(seed)); //shuffle it
	}
	
	/**
	 * Put all cards in cards list.
	 */
	public void fillList(){
		/*Fill the cards arrayList with Room Cards */
		for(Room r : rooms){
			cards.add(new RoomCard(r));
		}
		
		/*Fill the cards arrayList with Weapon Cards */
		for(Weapon w : weapons){
			cards.add(new WeaponCard(w));
		}

		/*Fill the cards ArrayList with Player Cards */
		for(Character p : characters){
			cards.add(new CharacterCard(p));
		}
		
		Collections.shuffle(cards, new Random(seed)); 
	}

	/**
	 * Initializes the envelope list.
	 */
	public void initializeEnvelope(){

		RoomCard roomCard = null;
		CharacterCard characterCard = null;
		WeaponCard weaponCard = null;

		for(int i = 0 ; i != cards.size(); i++){
			Card currentCard = cards.get(i);
			if(roomCard == null){
				if(currentCard instanceof RoomCard){
					roomCard = (RoomCard) currentCard;
				}
			}else if(weaponCard == null){
				if(currentCard instanceof WeaponCard){
					weaponCard = (WeaponCard) currentCard;
				}
			}else if(characterCard == null){
				if(currentCard instanceof CharacterCard){
					characterCard = (CharacterCard) currentCard;
				}
			}

			if(roomCard != null && characterCard != null && weaponCard != null){
				break;
			}
		}
		envelope.add(weaponCard);
		envelope.add(characterCard);
		envelope.add(roomCard);

		/*Finally, remove these cards from their arrayList */
		rooms.remove(roomCard);
		weapons.remove(weaponCard);
		characters.remove(characterCard);

	}

	/*Very small test class. TODO need to remove in the final release. */
	public static void main(String[] argv){
		new Initializer();
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
