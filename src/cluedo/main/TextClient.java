package cluedo.main;
import java.io.*;
import java.util.*;
import cluedo.assets.*;

public class TextClient {
	/**
	 * Represents the board in 2d array form.
	 */
	String[][] board = new String[25][25];

	public TextClient(){
		//filling up the board so it does not contain any null values
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < board.length; y++){
				board[x][y] = "_|";
			}
		}
		
		//fills up the side of the board
		for(int i = 0; i < board.length; i++){
			board[0][i] = "|_|";
		}
		
		//fill board with rooms
		drawKitchen();
		drawDiningRoom();
		drawLounge();
		drawHall();
		drawStudy();
		drawLibrary();
		drawBilliard();
		drawConservatory();
		drawBallroom();
		drawCluedo();
		
		//doMove
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < board.length; y++){
				System.out.print(board[y][x]);
			}
			System.out.println();
		}

	}

	private void drawKitchen(){
		int size = 6;
		int x = 0;
		int y = 1;
		/*Boarder*/
		//top
		for(int i = 0; i < size; i++){
			board[i][y] = "X|";
		}
		//bottom
		for(int i = 0; i < size; i++){
			board[i][y+size-1] = "X|";
		}
		//left
		for(int i = y; i < size+y; i++){
			board[x][i] = "|X|";
		}
		//right
		for(int i = y; i < size; i++){
			board[x+size-1][i] = "X|";
		}
		//area
		for(int i = x+1; i < size-1; i++){
			for(int j = y+1; j < size; j++){
				board[i][j] = "  ";
			}
		}

		for(int i = y+1; i < size; i++){
			board[size-2][i] = " |";
		}

		board[1][2] = "1 ";
		board[5][1] = "S|";
		board[4][6] = "D|";
	}

	private void drawDiningRoom(){
		int width = 8;
		int height = 7;
		int x = 0;
		int y = 9;

		/*Boarder*/
		//top
		for(int i = 0; i < 5; i++){
			board[i][y] = "X|";
		}
		for(int i = 4; i < width; i++){
			board[i][y+1] = "X|";
		}
		board[x+4][y+1] = " |";

		//bottom
		for(int i = 0; i < width; i++){
			board[i][y+height-1] = "X|";
		}
		//left
		for(int i = y; i < height + y; i++){
			board[x][i] = "|X|";
		}
		//right
		for(int i = y+2; i < height + y; i++){
			board[x+width-1][i] = "X|";
		}

		//area
		for(int i = x+1; i < 4; i++){
			for(int j = y+1; j < y+height-1; j++){
				board[i][j] = "  ";

			}
		}

		for(int i = 4; i < width-1; i++){
			for(int j = y+2; j < y+height-1; j++){
				board[i][j] = "  ";

			}
		}

		for(int i = y+2; i < height + y; i++){
			board[x+width-2][i] = " |";
		}

		board[1][10] = "2 ";
		board[width-1][12] = "D|";
		board[width-2][y+height-1] = "D|";
	}

	private void drawLounge(){
		int x = 0;
		int y = 19;
		int width = 7;
		int height = 6;
		/*Boarder*/
		//top
		for(int i = 0; i < width; i++){
			board[i][y] = "X|";
		}

		//bottom
		for(int i = 0; i < width; i++){
			board[i][y+height-1] = "X|";
		}

		//left
		for(int i = y; i < y+height; i++){
			board[x][i] = "|X|";
		}

		//right
		for(int i = y+1; i < y+height; i++){
			board[x+width-1][i] = "X|";
		}

		//area
		for(int i = x+1; i < width-1; i++){
			for(int j = y+1; j < y+height-1; j++){
				board[i][j] = "  ";
			}
		}

		for(int i = y+1; i < y+height-1; i++){
			board[x+width-2][i] = " |";
		}

		board[x+width-1][y] = "D|";
		board[x][y] = "|S|";
		board[x+1][y+1] = "3 ";
	}

	private void drawHall() {
		int x = 9;
		int y = 18;
		int width = 6;
		int height = 7;

		/*Boarder*/
		//top
		for(int i = x; i < width+x; i++){
			board[i][y] = "X|";
		}

		//bottom
		for(int i = x; i < width+x; i++){
			board[i][y+height-1] = "X|";
		}

		//left
		for(int i = y; i < height+y; i++){
			board[x][i] = "X|";
		}

		//right
		for(int i = y+1; i < height+y-1; i++){
			board[x+width-1][i] = "X|";
		}

		//area
		for(int i = x+1; i < width+x-1; i++){
			for(int j = y+1; j < height+y-1; j++){
				board[i][j] = "  ";
			}
		}

		for(int i = y+1; i < y+height-1; i++){
			board[x+width-2][i] = " |";
		}

		board[x+2][y] = "D|";
		board[x+3][y] = "D|";
		board[x+width-1][y+2] = "D|";
		board[x+1][y+1] = "4 ";
	}

	private void drawStudy() {
		int x = 17;
		int y = 21;
		int width = 8;
		int height = 4;
		
		/*Boarder*/
		//top
		for(int i = x; i < width+x; i++){
			board[i][y] = "X|";
		}

		//bottom
		for(int i = x; i < width+x; i++){
			board[i][y+height-1] = "X|";
		}

		//left
		for(int i = y; i < height+y; i++){
			board[x][i] = "X|";
		}

		//right
		for(int i = y; i < height+y; i++){
			board[x+width-1][i] = "X|";
		}

		//area
		for(int i = x+1; i < width+x-1; i++){
			for(int j = y+1; j < height+y-1; j++){
				board[i][j] = "  ";
			}
		}

		for(int i = y+1; i < y+height-1; i++){
			board[x+width-2][i] = " |";
		}

		board[x][y] = "D|";
		board[x+width-1][y] = "S|";
		board[x+1][y+1] = "5 ";
	}

	private void drawLibrary() {
		int x = 18;
		int y = 15;
		int width = 7;
		int height = 5;

		/*Boarder*/
		//top
		for(int i = x+1; i < width+x-1; i++){
			board[i][y] = "X|";
		}

		//bottom
		for(int i = x+1; i < width+x-1; i++){
			board[i][y+height-1] = "X|";
		}

		//left
		for(int i = y+1; i < height+y-1; i++){
			board[x][i] = "X|";
		}

		//right
		for(int i = y+1; i < height+y-1; i++){
			board[x+width-1][i] = "X|";
		}

		//area
		for(int i = x+1; i < width+x-1; i++){
			for(int j = y+1; j < height+y-1; j++){
				board[i][j] = "  ";
			}
		}

		for(int i = y+1; i < y+height-1; i++){
			board[x+width-2][i] = " |";
		}
		
		board[x][y+2] = "D|";
		board[x+3][y] = "D|";
		board[x+1][y+1] = "6 ";
	}

	private void drawBilliard() {
		int x = 19;
		int y = 8;
		int width = 6;
		int height = 5;
		
		/*Boarder*/
		//top
		for(int i = x; i < width+x; i++){
			board[i][y] = "X|";
		}

		//bottom
		for(int i = x; i < width+x; i++){
			board[i][y+height-1] = "X|";
		}

		//left
		for(int i = y; i < height+y; i++){
			board[x][i] = "X|";
		}

		//right
		for(int i = y; i < height+y; i++){
			board[x+width-1][i] = "X|";
		}

		//area
		for(int i = x+1; i < width+x-1; i++){
			for(int j = y+1; j < height+y-1; j++){
				board[i][j] = "  ";
			}
		}

		for(int i = y+1; i < y+height-1; i++){
			board[x+width-2][i] = " |";
		}
		
		board[x][y+1] = "D|";
		board[x+width-2][y+height-1] = "D|";
		board[x+1][y+1] = "7 ";
	}

	private void drawConservatory() {
		int x = 18;
		int y = 1;
		int width = 7;
		int height = 5;
		
		/*Boarder*/
		//top
		for(int i = x; i < width+x; i++){
			board[i][y] = "X|";
		}

		//bottom
		for(int i = x+1; i < width+x-1; i++){
			board[i][y+height-1] = "X|";
		}

		//left
		for(int i = y; i < height+y-1; i++){
			board[x][i] = "X|";
		}

		//right
		for(int i = y; i < height+y-1; i++){
			board[x+width-1][i] = "X|";
		}

		//area
		for(int i = x+1; i < width+x-1; i++){
			for(int j = y+1; j < height+y-1; j++){
				board[i][j] = "  ";
			}
		}

		for(int i = y+1; i < y+height-1; i++){
			board[x+width-2][i] = " |";
		}
		
		board[x][y+height-2] = "D|";
		board[x+width-2][y+height-1] = "S|";
		board[x+1][y+1] = "8 ";
	}

	private void drawBallroom() {
		int x = 8;
		int y = 1;
		int width = 8;
		int height = 7;
		
		/*Boarder*/
		//top
		for(int i = x+2; i < width+x-2; i++){
			board[i][y] = "X|";
		}
		
		//bottom
		for(int i = x; i < width+x; i++){
			board[i][y+height-1] = "X|";
		}

		//left
		for(int i = y+1; i < height+y; i++){
			board[x][i] = "X|";
		}

		//right
		for(int i = y+1; i < height+y; i++){
			board[x+width-1][i] = "X|";
		}

		//area
		for(int i = x+1; i < width+x-1; i++){
			for(int j = y+1; j < height+y-1; j++){
				board[i][j] = "  ";
			}
		}

		for(int i = y+1; i < y+height-1; i++){
			board[x+width-2][i] = " |";
		}
		
		board[x][y+height-2] = "D|";
		board[x+width-2][y+height-1] = "S|";
		board[x+1][y+1] = "X|";
		board[x+width-2][y+1] = "X|";
		board[x+width-3][y+1] = " |";
		board[x+2][y+1] = "9 ";
	}

	private void drawCluedo() {
		int x = 10;
		int y = 10;
		int width = 6;
		int height = 7;
		
		//area
		for(int i = x; i < width+x; i++){
			for(int j = y; j < height+y; j++){
				board[i][j] = "*|";
			}
		}
	}
	
	public void move(int x, int y, Character p){
		board[x][y] = p.getName();
	}
	

	/**
	 * Get string from System.in
	 */
	
	private static String inputString(String msg) {
		System.out.print(msg + " ");
		while (true) {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				return input.readLine();
			} catch (IOException e) {
				System.out.println("I/O Error ... please try again!");
			}
		}
	}

	/**
	 * Get integer from System.in
	 */
	private static int inputNumber(String msg) {
		System.out.print(msg + " ");
		while (true) {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				String v = input.readLine();
				return Integer.parseInt(v);
			} catch (IOException e) {
				System.out.println("Please enter a number!");
			}
		}
	}

	public static void main(String[] args) {
		new TextClient();
	}


}
