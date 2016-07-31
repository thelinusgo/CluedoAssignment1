package cluedo.main;
import java.io.*;
import java.util.*;
import cluedo.assets.*;

public class Board {
	/**
	 * Represents the board in 2d array form.
	 */
	private String[][] board = new String[25][25];

	/**
	 * Stores the list of doors on the board.
	 */
	private List<Door> doors = new ArrayList<Door>();

	/**
	 * Stores the starting positions of each player.
	 */
	private List<Position> startPos = new ArrayList<Position>();

	/** This helps generating a random shuffle for the lists */
	private long seed = System.nanoTime();

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
		drawDoors();
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

		doors.add(new Door(false, 4, 6));
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

		doors.add(new Door(true, width-1, 12));
		doors.add(new Door(true, width-2, y+height-1));
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

		board[x][y] = "|S|";
		board[x+1][y+1] = "3 ";

		doors.add(new Door(false, x+width-1, y));
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

		board[x+1][y+1] = "4 ";

		doors.add(new Door(true, x+width-1, y+2));
		doors.add(new Door(true, x+3, y));
		doors.add(new Door(true, x+2, y));
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

		board[x+width-1][y] = "S|";
		board[x+1][y+1] = "5 ";

		doors.add(new Door(false, x, y));
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
		board[x+1][y+1] = "6 ";

		doors.add(new Door(true, x, y+2));
		doors.add(new Door(true, x+3, y));
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
		board[x+1][y+1] = "7 ";

		doors.add(new Door(true, x, y+1));
		doors.add(new Door(false, x+width-2, y+height-1));
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
		board[x+width-2][y+height-1] = "S|";
		board[x+1][y+1] = "8 ";
		doors.add(new Door(false, x, y+height-2));
	}

	/**
	 * Draws the ball room.
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

		doors.add(new Door(true, x, 5));
		doors.add(new Door(true, x+width-1, 5));
		doors.add(new Door(false, x+1, y+height-1));
		doors.add(new Door(false, x+width-2, y+height-1));

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
	 * Draws the doors.
	 */
	private void drawDoors(){
		for(int i = 0; i < doors.size(); i++){
			Door d = doors.get(i);
			int x = d.getX();
			int y = d.getY();
			board[x][y] = "D|";
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
		List<Room> rooms = Game.initializer.getRooms();
		int x = p.getX() + directionX;
		int y = p.getY() + directionY;
		if(isValidMove(x, y, directionX, directionY, p)){
			p.setPos(x, y);
			p.moveAStep();
			board[p.getX()][p.getY()] = p.getCharacterName() + "|";
			for(int i = 0; i < rooms.size(); i++){
				if(rooms.get(i).contains(p.getX(), p.getY())){
					p.setIsInRoom(true);
				}else{
					p.setIsInRoom(false);
				}
			}
		}
		drawBoard();
	}
	
	/**
	 * Checks if player is doing a valid move. If they are not, then it returns false, else it returns true.
	 * 
	 * @param x
	 * @param y
	 * @param directionX
	 * @param directionY
	 * @param p
	 * @return
	 */
	public boolean isValidMove(int x, int y, int directionX, int directionY, Player p){
		if(x > 24 && x < 0 && y > 24 && y < 0){
			System.out.println("Cannot go out of bounds!");
			return false;
		}else if(board[x][y].equals("|#|") || board[x][y].equals("#|")){
			System.out.println("Cannot move into wall.");
			return false;
		}else if(board[x][y].equals("|X|") || board[x][y].equals("X|")){
			System.out.println("Cannot move into wall.");
			return false;
		}else if((board[x][y].equals("S|") || board[x][y].equals("|S|")) && !p.isInRoom()){
			System.out.println("Player is not in room to take the stairs.");
			return false;
		}else{
			for(int i = 0; i < doors.size(); i++){
				Door d = doors.get(i);
				if(!d.isHorizontal() && x == d.getX() && y == d.getY() && directionY == 0 && directionX > 1){
					System.out.println("Going through door the wrong way!");
					return false;
				}else if(d.isHorizontal() && x == d.getX() && y == d.getY() && directionY > 1 && directionX == 0){
					System.out.println("Going through door the wrong way!");
					return false;
				}
			}
		}
		return true;
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

	/**
	 * Initializes each player's position.
	 * @param currentPlayers
	 */
	public void setPlayerPosition(List<Player> currentPlayers){
		Collections.shuffle(startPos, new Random(seed)); 
		for(int i = 0; i < currentPlayers.size(); i++){
			int x = startPos.get(i).getX();
			int y = startPos.get(i).getY();
			board[x][y] = currentPlayers.get(i).getCharacterName() + "|";
			currentPlayers.get(i).setPos(x, y);
			if(x == 0 && y == 17){
				board[x][y] = "|" +currentPlayers.get(i).getCharacterName() + "|";
			}
		}
	}

	public static void main(String[] args) {
		new Board();
	}

}
