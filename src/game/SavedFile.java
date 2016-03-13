/**
 * @file SavedFile.java
 * @author Victoria Charvis
 * @date 29th February 2016
 * @see Tony Gaddis and Godfrey Muganda, chapter 4.10
 * from "Starting out with Java from control structures through data structures
 * 1st edition
 * @brief Saves and loads selected game
 * 
 * Saves the current game into the selected CSV file
 * Loads a selected game from a selected CSV file
 */

package game;

import java.util.*;

import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import main.Kablewie;

public class SavedFile {
	
	/**
	 * Constructor
	 * 
	 * @param gc, the GameController for the current game
	 */
	public SavedFile(GameController gc) {
		m_gameController = gc;
	}
	
	/**
	 * Loads the game data from file
	 *  
	 * @param slot the number slot for the file to be loaded from
	 * @return if loading was successful
	 */
	public boolean loadFile(int slot) {
		/*Getting the file name from the slot chosen*/
		String fileName = ("SaveFile" + slot + ".csv");
		
		if (!validateLoadFile(fileName)) {
			return false;
		}
		
		try {
			
			Scanner	in = new Scanner(new File(fileName));
			
			/*setting up array list to store lines in*/
			ArrayList<String> line = new ArrayList<String>();
			
			/*read in each field of the file*/
			in.useDelimiter(",");
			while (in.hasNext()) {
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
		
		return true;
	}
	
	/**
	 * Saves the game data to file
	 * 
	 * @param slot the number slot for the game to be saved into
	 * @param board the board of the game to be saved
	 * @param player the player of the game to be saved
	 * @return if saving was successful
	 */
	public boolean saveFile(int slot, Board board, Player player) {
		if (!validateData(board, player)) {
			return false;
		}
		
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
			boardData = board.getBoard();
			
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
		return true;
	}
	
	/**
	 * Starts a loaded game so the player can resume playing
	 * 
	 * @param line ArrayList containing the saved data
	 * @param loadedBoard the board loaded from file
	 * @param loadedPlayer the player loaded from file
	 * @return if the game was started successfully
	 */
	public boolean startup(ArrayList<String> line, Board loadedBoard,
			Player loadedPlayer) {
		/*Used to check file read in correctly*/
		if (Integer.parseInt(line.get(DIFFUSED_TILE)) 
				!= loadedBoard.getDefusedTile()) {
			/*error reading file*/
			JOptionPane.showMessageDialog(null, "Input file corrupted", "Loading error", 
					JOptionPane.INFORMATION_MESSAGE);
			new Kablewie();
			return false;
		}
		if (Integer.parseInt(line.get(HIDDEN_TILE)) 
				!= loadedBoard.getHiddenTile()) {
			/*error reading file*/
			JOptionPane.showMessageDialog(null, "Input file corrupted", "Loading error", 
					JOptionPane.INFORMATION_MESSAGE);
			new Kablewie();
			return false;
		}
		if (Integer.parseInt(line.get(REVEALED_TILE)) 
				!= loadedBoard.getRevealedTile()) {
			/*error reading file*/
			JOptionPane.showMessageDialog(null, "Input file corrupted", "Loading error", 
					JOptionPane.INFORMATION_MESSAGE);
			new Kablewie();
			return false;
		}
		
		/*Enables player to continue playing game*/
		Kablewie loadedGame = new Kablewie();
		String loadedTime = line.get(TIME);
		loadedBoard.loadGraphics(m_gameController);
		loadedGame.startLoadedGame(loadedBoard, loadedPlayer, loadedTime);
		return true;
	}
	
	/**
	 * Tests if input passed is valid
	 * 
	 * @param board the board to be saved
	 * @param player the player to be saved
	 * @return if the data is valid
	 */
	public boolean validateData(Board board, Player player) {
		if (board.getBoard().size()==0 || 
				player.getUsername().equalsIgnoreCase("")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Used to see if the file being accessed is allowed
	 * 
	 * @param fileName the name of the file
	 * @return if the file is valid for the game
	 */
	public boolean validateLoadFile(String fileName) {
		/*if file is one of the accepted ones*/
		if (fileName.equalsIgnoreCase("SaveFile1.csv")|| 
				fileName.equalsIgnoreCase("SaveFile2.csv")
				||fileName.equalsIgnoreCase("SaveFile3.csv")) {
			/*Checking if the file exists*/
			if (!new File(fileName).isFile() ||
					new File(fileName).length()==0) {
				return false;
			} else return true;
		} 
			return false;
	}

	private GameController m_gameController;
	
	final static int USERNAME = 0;
	final static int TIME = 1;
	final static int DIFFUSED_TILE = 2;
	final static int MINE_COUNT = 3;
	final static int HIDDEN_TILE = 4;
	final static int REVEALED_TILE = 5;
	final static int BOARDSIZE = 6;

}
