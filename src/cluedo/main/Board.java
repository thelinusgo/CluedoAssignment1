package cluedo.main;
import java.io.*;
import java.util.*;
import cluedo.assets.*;

public class Board {
	/**
	 * Represents the board in 2d array form.
	 */
	String[][] board = new String[25][25];
	
	List<Position> startPos = new ArrayList<Position>();
	
	/** This helps generating a random shuffle for the lists */
	private long seed = System.nanoTime();
	//aa
	public Board(){
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

		initialiseBoard();
		drawBoard();
	}

	/**
	 * Fill board with rooms
	 */
	public void initialiseBoard(){
		drawBoarder();
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
		drawStart();
	}

	/**
	 * Draws the boarder.
	 */
	private void drawBoarder(){
		for(int i = 0; i < this.board.length; i++){
			board[i][0] = "#|";
			board[i][board.length-1] = "#|";
		}
		for(int i = 0; i < this.board.length; i++){
			board[0][i] = "|#|";
			board[board.length-1][i] = "#|";
		}
		board[6][1] = "#|";
		board[17][1] = "#|";
	}

	/**
	 * Draws the kitchen.
	 */
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

	/**
	 * Draws the dining room.
	 */
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

	/**
	 * Draws the lounge.
	 */
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

	/**
	 * Draws the hall.
	 */
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

	/**
	 * Draws the study room.
	 */
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

	/**
	 * Draws the library.
	 */
	private void drawLibrary() {
		int x = 18;
		int y = 14;
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

	/**
	 * Draws the billiard room.
	 */
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

	/**
	 * Draws the conservatory.
	 */
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

	/**
	 * Draws the ballroom.
	 */
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

	/**
	 * Draws the solution room.
	 */
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

	/**
	 * Draws the start spaces.
	 */
	private void drawStart(){
		startPos.add(new Position(9, 0));
		startPos.add(new Position(14, 0));
		startPos.add(new Position(0, 17));
		startPos.add(new Position(7, board.length-1));
		startPos.add(new Position(board.length-1, board.length-6));
		startPos.add(new Position(board.length-1, 6));
		
		for(int i = 0; i < startPos.size(); i++){
			int x = startPos.get(i).getX();
			int y = startPos.get(i).getY();
			board[x][y] = "/|";
			if(x == 0 && y == 17){
				board[x][y] = "|/|";
			}
		}
	}

	/**
	 * Moves player.
	 * @param x
	 * @param y
	 * @param p
	 */
	public void move(int directionX, int directionY, Player p){
		p.setPos(directionX, directionY);
		board[p.getX()][p.getY()] = p.getCharacterName() + "|";
	}

	/**
	 * Prints out the board.
	 */
	public void drawBoard(){
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < board.length; y++){
				System.out.print(board[y][x]);
			}
			System.out.println();
		}
	}

	public void setPlayerPosition(List<Player> currentPlayers){
		Collections.shuffle(startPos, new Random(seed)); 
		for(int i = 0; i < currentPlayers.size(); i++){
			int x = startPos.get(i).getX();
			int y = startPos.get(i).getY();
			board[x][y] = currentPlayers.get(i).getCharacterName();
		}
	}

	public static void main(String[] args) {
		new Board();
	}


}
