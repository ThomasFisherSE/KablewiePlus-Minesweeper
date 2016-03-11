/**
 * @file SavedFile.java
 * @author Victoria Charvis
 * @date 29th February 2016
 * @brief Saves current game
 * 
 * Saves the current game into the selected CSV file
 * Loads a selected game from a selected CSV file
 * 
 * @see Tony Gaddis and Godfrey Muganda, chapter 4.10
 * from "Starting out with Java from control structures through data structures
 * 1st edition
 */

package game;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import main.Kablewie;

public class SavedFile {
	
	final static int USERNAME = 0;
	final static int TIME = 1;
	final static int DIFFUSED_TILE = 2;
	final static int MINE_COUNT = 3;
	final static int HIDDEN_TILE = 4;
	final static int REVEALED_TILE = 5;
	final static int BOARDSIZE = 6;
		
	/**
	 *  
	 * @param slot the number slot for the file to be loaded from
	 */
	public void loadFile(int slot) {
		/*Getting the file name from the slot chosen*/
		String fileName = ("SaveFile" + slot + ".csv");
		
		/*Checking if the file exists*/
		if (!new File(fileName).isFile() || new File(fileName).length()==0) {
			return;
		}
		
		try{
			
			Scanner	in = new Scanner(new File(fileName));
			
			/*setting up array list to store lines in*/
			ArrayList<String> line = new ArrayList<String>();
			
			/*read in each field of the file*/
			in.useDelimiter(",");
			while (in.hasNext()){
				String readFile = in.next();
				/*store data in the array list*/
				line.add(readFile);	
			}
			in.close();
			
			/*Setup new objects using saved game data*/
			Player loadedPlayer = new Player(line.get(USERNAME));
			int boardSize = Integer.parseInt(line.get(BOARDSIZE));
			int mineAmount = Integer.parseInt(line.get(MINE_COUNT)); 
			Board loadedBoard = new Board(boardSize, mineAmount);
			/*Continuing to read the file after the current data*/
			int arrayIndex = 7;
			
			/*Setting up the tiles*/
			for (int i=0; i<boardSize; i++) {
				for (int j=0; j<boardSize; j++) {
					/*For each line in the array list*/
					String data = line.get(arrayIndex);
					/*Setup scanner to detect character change*/
					Scanner s = new Scanner(data);
					s.useDelimiter("");  
					
					/*
					 * Read the tile values(T or F) 
					 * and apply these values to tiles
					 */
					String value = s.next();
					/*Diffused value*/
					if (value.equalsIgnoreCase("T")) {
						loadedBoard.setState(j, i, "D", true);
					} else {
						loadedBoard.setState(j, i, "D", false);
					}
					/*Mine value*/
					value = s.next();
					if (value.equalsIgnoreCase("T")) {
						loadedBoard.setState(j, i, "M", true);
					} else {
						loadedBoard.setState(j, i, "M", false);
					}
					/*Hidden value*/
					value = s.next();
					if (value.equalsIgnoreCase("T")) {
						loadedBoard.setState(j, i, "H", true);
					} else {
						loadedBoard.setState(j, i, "H", false);
					}
					/*Increase index to access next element in array*/
					arrayIndex++;
					s.close();
				}
			}
			startup(line, loadedBoard, loadedPlayer);
		}
		catch (Exception e) {}
	}
	
	/**
	 * 
	 * @param slot the number slot for the game to be saved into
	 * @param board the board of the game to be saved
	 * @param player the player of the game to be saved
	 */
	public void saveFile(int slot, Board board, Player player) {
		String fileName = ("SaveFile" + slot + ".csv");
		try {
			
			/*Set up connection to the file*/
			FileWriter writer = new FileWriter(fileName);
			PrintWriter output = new PrintWriter(writer);
			
			/**
			 * Saving player name, time passed, diffused tile amount, 
			 * total mines, hidden tile amount, revealed tiles amount
			 */
			output.print(player.getUsername()+",");
			output.print(board.getTimePassed()+",");
			output.print(board.getDefusedTile()+",");
			output.print(board.getMineCount()+",");
			output.print(board.getHiddenTile()+",");
			output.print(board.getRevealedTile()+",");
			
			/*Saving tile data*/
			
			/*Creating a copy of the tiles in the board*/
			ArrayList<ArrayList<Tile>> boardData;
			boardData = new ArrayList<ArrayList<Tile>>();
			boardData = board.getm_Board();
			
			/*Getting and saving the board size*/
			int tileAmount = boardData.size();
			output.print(tileAmount+",");
			
			/**
			 * For every tile record if it's revealed, hidden, 
			 * diffused and mined by True/False
			 */
			for (int i=0; i<tileAmount; i++) {
				for (int j=0; j<tileAmount; j++) {
					if (boardData.get(i).get(j).isDefused()) {
						output.print("T");
					} else {
						output.print("F");
					}
					if (boardData.get(i).get(j).isMine()) {
						output.print("T");
					} else {
						output.print("F");
					}
					if (boardData.get(i).get(j).isHidden()) {
						output.print("T,");
					} else {
						output.print("F,");
					}
				}
			}
			
			/*closing the resource*/
			output.close();
		}
		
		catch (Exception e) {}
	}
	
	/**
	 * 
	 * @param line ArrayList containing the saved data
	 * @param loadedBoard the board loaded from file
	 * @param loadedPlayer the player loaded from file
	 */
	private void startup(ArrayList<String> line, Board loadedBoard,
			Player loadedPlayer) {
		/*Used to check file read in correctly*/
		if (Integer.parseInt(line.get(DIFFUSED_TILE)) 
				!= loadedBoard.getDefusedTile()) {
			/*error reading file*/
			return;
		}
		if (Integer.parseInt(line.get(HIDDEN_TILE)) 
				!= loadedBoard.getHiddenTile()) {
			/*error reading file*/
			return;
		}
		if (Integer.parseInt(line.get(REVEALED_TILE)) 
				!= loadedBoard.getRevealedTile()) {
			/*error reading file*/
			return;
		}
		
		/*Enables player to continue playing game*/
		Kablewie loadedGame = new Kablewie();
		String loadedTime = line.get(TIME);
		loadedBoard.loadGraphics();
		loadedGame.startLoadedGame(loadedBoard, loadedPlayer, loadedTime);
	}
	
	/**
	 * Used for unit testing
	 * @param args a String Array of arguments passed from the command line.
	 */
	public static void main(String[] args) {
	//unit testing
		
	//integration testing
		
	}
}
