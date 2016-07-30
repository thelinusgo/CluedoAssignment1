
package cluedo.assets;

/**
 * Class that represents the dice on the board.
 * @author linus
 *
 */
public class Dice {
	
	private int dice;
	
	/**
	 * Roll a dice.
	 */
	public Dice(){
		rollDice();
	}
	
	/**
	 * Roll the dice a random number between 2-12.
	 */
	public void rollDice(){
	dice = 0;
	int die1 = (int)(Math.random()*6)+1;	
	int die2 = (int)(Math.random()*6)+1;
	dice = die1 + die2;
	}
	
	/**
	 * Gets the value of the die.
	 * @return
	 */
	public int getDice(){
		return this.dice;
	}
	
	public static void main(String[] argc){
		int val = 0; int avg = 0;
		for(int i = 0; i <= 1000; i++){
			Dice d = new Dice();
			val += d.getDice();
			System.out.println("i: " + i + "value: "+ d.getDice());
		}
		avg = val/100;
		System.out.printf("Average value: %d", avg);
	}
	
}
