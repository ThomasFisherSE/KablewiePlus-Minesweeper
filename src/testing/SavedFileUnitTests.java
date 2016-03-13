/**
 * @file SavedFileUnitTests.java
 * @author Victoria Charvis
 * @Date 11/03/2016
 * @see SavedFile.java
 * @brief Tests Saved File
 * 
 * Tests all methods in Saved File with valid and invalid input
 */

package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import game.*;
import org.junit.Test;

public class SavedFileUnitTests {

	/**
	 * Tests if the file saves correctly
	 */
	@Test 
	public void FileShouldSaveCorrectly() {
		SavedFile tester = createFile();
		
		//valid input 
		assertEquals("Should accept reading from valid file",tester.saveFile(1,createBoard(),createPlayer()),true);
		
		//invalid input is not possible to be passed into saveFile
		//Can't have empty username/board/slot parameter passed in 
	}	
	
	/**
	 * Tests if the file loads correctly
	 */
	@Test
	public void FileShouldLoadCorrectly() {
		SavedFile tester = createFile();
		
		//valid input
		assertEquals("Should accept reading from valid file",tester.loadFile(1),true);
		assertEquals("Should accept valid file", tester.validLoadFile("SaveFile1.csv") , true);
		
		//invalid input
		assertEquals("Shouldn't accept reading from valid but non existent file",tester.loadFile(3),false);
		assertEquals("Shouldn't accept non existent file", tester.validLoadFile("SaveFile3.csv") , false);
		//invalid input (not possible in running program)
		assertEquals("Shouldn't accept reading from wrong file format", tester.validLoadFile("SaveFile1.txt") , false);
		//invalid input (not possible in running program)
		assertEquals("Shouldn't accept reading from 'non-slot' file", tester.validLoadFile("SaveFile5.csv") ,false);
	}
	
	/**
	 * Tests if a new game starts correctly
	 */
	@Test
	public void GameShouldStart(){
		SavedFile tester = createFile();
		
		//valid input
		assertEquals("Created board tile values should be equal to the default amount",
				tester.startup(createArrayList(),createBoard(),createPlayer()),true);
		//invalid input
		assertEquals("Created board tile values shouldn't be equal to the default amount",
				tester.startup(createBadArrayList(),createBoard(),createPlayer()),false);
	}
	  
	/**
	 * Creates a new instance os SavedFile
	 * 
	 * @return a new SavedFile object
	 */
	public SavedFile createFile() {
		return new SavedFile();
	}
	
	/**
	 * Creates a new board
	 * 
	 * @return a board of 10x10 with 10 mines
	 */
	public Board createBoard() {
		return new Board(10,10,10);
	}

	/**
	 * Creates an ArrayList of example saved game data
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
		for (int i=0; i<90;i++) {
			saved.add("FFT");
		}
		//as default there are 10 mines
		for (int i=0; i<10;i++) {
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
		for (int i=0; i<90;i++) {
			saved.add("FFT");
		}
		//as default there are 10 mines
		for (int i=0; i<10;i++) {
			saved.add("FTT");
		}
		return saved;
	}
	
	/**
	 * Creates a new player
	 * 
	 * @return the created player
	 */
	public Player createPlayer() {
		return new Player("PlayerName");
	}
}
