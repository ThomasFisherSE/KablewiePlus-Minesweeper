/**
 * @file IntegrationTests.java
 * @author Scott-Cheg
 * @date 11/03/2015
 * @see All files in the package testing
 * @brief integration testing
 * 
 * Integration testing for the classes
 */
package testing;

import java.util.ArrayList;

import javax.swing.JFrame;
import game.*;
import main.*;

public class IntegrationTests {
	/**
	 * Tests all unit tests
	 * @param args a String Array of arguments passed from the command line.
	 */
	public static void main(String[] args) {
		
		try {
			BoardUnitTests boardTests = new BoardUnitTests();
			boardTests.testBoardBounds();
			boardTests.testBoardComplete();
			boardTests.testBoardIncomplete();
			boardTests.testBoardDefaultInitialization();
			boardTests.testBoardCustomInitialization();
			boardTests.testBoardReturn();
			boardTests.testTimeReturn();
			boardTests.testMineReturn();
			boardTests.testRowReturn();
			boardTests.testColumnReturn();
			
			ComputerUnitTests computerTests = new ComputerUnitTests();
			computerTests.testCantLosePreset();
			computerTests.testHighIntelligencePreset();
			computerTests.testLowIntelligencePreset();
			computerTests.testNormalIntelligencePreset();
			computerTests.testAiOnDefaultBoard();
			computerTests.testAiOnLargeBoard();
			computerTests.testAiWithDefaultMines();
			computerTests.testAiWithMaxMines();
			computerTests.testAiWithMinMines();
			computerTests.testMaxIntelligence();
			computerTests.testMinIntelligence();
			computerTests.testAiWhenMinesRevealed();
			computerTests.testRunningAiConsecutively();
			computerTests.testGeneratingMoves();
			computerTests.testMakeMove();
			computerTests.testMakePerfectMove();
			computerTests.testMakeStupidMove();
			computerTests.testMakeSmartMove();
			
			SavedFileUnitTests savedFileTests = new SavedFileUnitTests();
			savedFileTests.FileShouldLoadCorrectly();
			savedFileTests.FileShouldSaveCorrectly();
			savedFileTests.GameShouldStart();
			savedFileTests.TestValidData();
			
			System.out.println("Tests were successful.");
		} catch (Exception e) {
			System.err.println("A test failed: \n" + e);
		}
		
		System.exit(0);
	}
	
	/**
	 * Creates an ArrayList of valid saved game data
	 * 
	 * @return ArrayList of saved game data
	 */
	public ArrayList<String> createArrayList() {
		ArrayList<String> saved = new ArrayList<String>();
		saved.add("PLayer Name");//example name
		saved.add("00:00:01");//example time
		saved.add("0");//example diffused amount
		saved.add("10");//example mine amount
		saved.add("100");//example hidden amount
		saved.add("0");//example revealed amount
		//as default all tiles are hidden(T) and diffused(F)
		int min = 0;
		int max = 90;
		int mineAmount = 10;
		for (int i=min; i<max;i++) {
			saved.add("FFT");
		}
		//as default there are 10 mines
		for (int i=min; i<mineAmount;i++) {
			saved.add("FTT");
		}
		return saved;
	}
	
	/**
	 * Creates an ArrayList of faulty saved game data
	 * 
	 * @return ArrayList of saved game data
	 */
	public ArrayList<String> createBadArrayList() {
		ArrayList<String> saved = new ArrayList<String>();
		saved.add("PLayer Name");//example name
		saved.add("00:00:01");//example time
		saved.add("0");//example diffused amount
		saved.add("0");//example mismatched mine amount
		saved.add("0");//example mismatched hidden amount
		saved.add("0");//example revealed amount
		//as default all tiles are hidden(T) and diffused(F)
		int min = 0;
		int max = 90;
		int mineAmount = 10;
		for (int i=min; i<max;i++) {
			saved.add("FFT");
		}
		//There are 10 mines
		for (int i=min; i<mineAmount;i++) {
			saved.add("FTT");
		}
		return saved;
	}
	
	/**
	 * Creates a new player with invalid username
	 * 
	 * @return the created player
	 */
	public Player createBadPlayer() {
		return new Player("");
	}
	
	/**
	 * Creates a new board
	 * 
	 * @return a board of 0x0 with 0 mines
	 */
	public Board createBadBoard() {
		return new Board(0,0,0);
	}
	
	/**
	 * Creates a board
	 */
	public Board createBoard() {
		return new Board(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES);
	}
	
	/**
	 * Creates a Computer object
	 * 
	 * @return the Computer object
	 */
    public Computer createComputer() {
        return new Computer("AI", createBoard(), createGameController(), 0);
    }
    
	/**
	 * Creates a JFrame object
	 * 
	 * @return the JFrame object
	 */
	public JFrame createFrame() {
		return new JFrame("testFrame");
	}
	
	/**
	 * Creates a GameController object
	 * 
	 * @return the GameController object
	 */
	public GameController createGameController() {
		GameController gc = new GameController(createBoard(), createHuman(), createFrame(), createMainMenu());
		gc.asTest();
		return gc;
	}
	
	/**
	 * Creates a Human object
	 * 
	 * @return the Human object
	 */
    public Human createHuman() {
    	return new Human("testPlayer");
    }
    
	/**
	 * Creates a MainMenu object
	 * 
	 * @return the MainMenu object
	 */
	public MainMenu createMainMenu() {
		return new MainMenu(createFrame(), new Kablewie());
	}
	
	/**
  	 * Creates a Player object
  	 * 
  	 * @return the Player object
  	 */
    public Player createPlayer() {
    	return new Player("testPlayer");
    }
	
	/**
  	 * Creates a SavedFile object
  	 * 
  	 * @return the SavedFile object
  	 */
    public SavedFile createSavedFile() {
    	return new SavedFile(createGameController());
    }
	
}
