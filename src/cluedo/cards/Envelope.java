package cluedo.cards;

/**
 * Class that holds three Cards. It is backed by an array.
 * 
 * 
 */
public class Envelope{
	private Card[] envelope;
	private int count = 0;
	
	/**
	 * Creates a new envelope
	 */
	public Envelope(){
		this.envelope = new Card[3];	
	}
	
	
	
	public Card get(int i){
		if(i > 0 || i < 3){
			return envelope[i]; 
		}else throw new ArrayIndexOutOfBoundsException("envelope is out of bounds.");
	}
	
	public void add(Card card){
		if(count >= 3){
			return;
		}
		envelope[count] = card;
		count++;	
	}
	
	public Card[] getEnvelope(){
		Card[] temp = this.envelope;
		return temp;
	}
	
}


