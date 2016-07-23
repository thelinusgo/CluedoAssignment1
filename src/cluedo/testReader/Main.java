package cluedo.testReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/**
 * Test class please ignore. This is what I used to test that user input is being inserted correctly.
 * Will implement this in the TextClient.
 * @author linus
 *
 */
public class Main {

	public static String test(){
		int number = 0;
		String name = "";
		boolean right = false;
		String blah = "/\n";
		
		Scanner sc = new Scanner(System.in);

		while(!right){
		System.out.println("amount of people: ");
		number = sc.nextInt();
		
		if(number > 6 || number < 3){
			System.out.println("Incorrect amount of people.");
		}else{right = true;}
		}
		for(int i = 0 ; i < number ; i++){
		System.out.println("Please enter a name:");
		name = sc.next();
		blah += name + "\n";
	}
		blah += "/";
		return blah;
	}
	
	
	
	public static void main(String[] args){
		//List<Widget> test = new LinkedList<>();
		String s = Main.test();
		System.out.println(s);
		//test.add(new Widget(name));
		}
		
		
		
//		System.out.println("WIDGET LIST: ");
//		for(Widget w : test){
//			System.out.println(w.toString());
//		}
		
		
		
	
	
	
	
	
	
	
}
