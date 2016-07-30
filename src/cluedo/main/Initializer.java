package cluedo.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import cluedo.assets.*;
import cluedo.assets.Character;
import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.cards.Envelope;
import cluedo.cards.RoomCard;
import cluedo.cards.WeaponCard;
/**
 * Class that represents the Board. Contains fields and methods regarding setting up the board.
 * @author linus & casey
 *
 */
public class Initializer {
	/**Lists that hold components of the board */
	private static List<Room> rooms = new ArrayList<>();
	private static List<Weapon> weapons = new ArrayList<>();
	private static List<Card> cards = new ArrayList<>();
	private static List<Character> characters = new ArrayList<>();
	private static Envelope envelope = new Envelope();
	private static List<RoomCard> roomCards = new ArrayList<>();
	private static List<WeaponCard> weaponCards = new ArrayList<>();
	private static List<CharacterCard> characterCards = new ArrayList<>();

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
		rooms.add(new Room("Kitchen", 0, 1, 6, 6, true));
		rooms.add(new Room("Dining Room", 0, 9, 8, 7, false));
		rooms.add(new Room("Ball Room", 8, 1, 8, 7, false));
		rooms.add(new Room("Conservatory", 18, 1, 7, 5, true));
		rooms.add(new Room("Billiard Room", 19, 8, 6, 5, false));
		rooms.add(new Room("Library", 18, 14, 7, 5, false));
		rooms.add(new Room("Study", 17, 21, 8, 4, true));
		rooms.add(new Room("Hall", 9, 18, 6, 7, false));
		rooms.add(new Room("Lounge", 0, 19, 7, 6, true));
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
		
		for(Card c : cards){
			if(c instanceof RoomCard){
				roomCards.add((RoomCard) c);
			}else if(c instanceof WeaponCard){
				weaponCards.add((WeaponCard) c);
			}else if(c instanceof CharacterCard){
				characterCards.add((CharacterCard) c);
			}
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

		for(Card c : cards){
			if(roomCard == null){
				if(c instanceof RoomCard){
					roomCard = (RoomCard) c;
				}
			}else if(weaponCard == null){
				if(c instanceof WeaponCard){
					weaponCard = (WeaponCard) c;
				}
			}else if(characterCard == null){
				if(c instanceof CharacterCard){
					characterCard = (CharacterCard) c;
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
		cards.remove(roomCard);
		cards.remove(weaponCard);
		cards.remove(characterCard);
	}

	public void distributeCharacters(){
		Collections.shuffle(characters, new Random(seed)); 
		for(int i = 0; i < Game.getCurrentPlayers().size(); i++){	
			Player p = Game.getCurrentPlayers().get(i);
			p.setCharacter(characters.get(i));
		}
	}

	public void distributeCards(){
		Collections.shuffle(roomCards, new Random(seed)); 
		Collections.shuffle(weaponCards, new Random(seed)); 
		Collections.shuffle(characterCards, new Random(seed)); 
		for(int i = 0, j = 0; j < roomCards.size(); i++, j++){
			if(i == Game.getCurrentPlayers().size()){
				i = 0;
			}
			Player currentPlayer = Game.getCurrentPlayers().get(i);
			currentPlayer.addCard(roomCards.get(j));
		}
		for(int i = 0, j = 0; j < weaponCards.size(); i++, j++){
			if(i == Game.getCurrentPlayers().size()){
				i = 0;
			}
			Player currentPlayer = Game.getCurrentPlayers().get(i);
			currentPlayer.addCard(weaponCards.get(j));
		}
		for(int i = 0, j = 0; j < characterCards.size(); i++, j++){
			if(i == Game.getCurrentPlayers().size()){
				i = 0;
			}
			Player currentPlayer = Game.getCurrentPlayers().get(i);
			currentPlayer.addCard(characterCards.get(j));
		}
		
		for(Player p : Game.getCurrentPlayers()){
			for(Card c : p.getCards()){
				System.out.println(c);
			}
		}
	}
	
	public List<Room> getRooms(){
		return Initializer.rooms;
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
