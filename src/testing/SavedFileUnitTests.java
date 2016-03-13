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

import java.io.File;
import game.*;
import org.junit.Test;

/**
 * 
 * @class SavedFileUnitTests
 * @brief Tests Saved File
 * 
 * Tests all methods in Saved File with valid and invalid input
 */
public class SavedFileUnitTests {
	/**
	 * Tests if the file saves correctly
	 */
	@Test 
	public void FileShouldSaveCorrectly() {
		SavedFile tester = m_interactingClass.createSavedFile();
		
		//valid input 
		assertEquals("Should accept saving",tester.saveFile(1,
				m_interactingClass.createBoard(),
				m_interactingClass.createPlayer()),true);
		
		/**
		 * Invalid input is not possible to be passed into saveFile
		 * Can't have empty username/board/slot parameter passed in but test 
		 * included anyway
		 */
		assertEquals("Shouldn't accept saving, empty board",tester.saveFile(1,
				m_interactingClass.createBadBoard(),
				m_interactingClass.createPlayer()),false);
		assertEquals("Shouldn't accept saving, empty username",tester.saveFile(
				1,m_interactingClass.createBoard(),
				m_interactingClass.createBadPlayer()),false);
	}	
	
	/**
	 * Tests if data passed in is valid 
	 */
	@Test
	public void TestValidData() {
		SavedFile tester = m_interactingClass.createSavedFile();
		//valid input
		assertEquals("Shouldn accept as vaid data",tester.validateData(
				m_interactingClass.createBoard(),
				m_interactingClass.createPlayer()),true);
		
		//invalid input
		assertEquals("Shouldn't accept empty board",tester.validateData(
				m_interactingClass.createBadBoard(),
				m_interactingClass.createPlayer()),false);
		assertEquals("Shouldn't accept empty username",tester.validateData(
				m_interactingClass.createBoard(),
				m_interactingClass.createBadPlayer()),false);
		
	}
	
	/**
	 * Tests if the file loads correctly
	 */
	@Test
	public void FileShouldLoadCorrectly() {
		SavedFile tester = m_interactingClass.createSavedFile();
		
		//valid input
		assertEquals("Should accept reading from valid file",tester.loadFile(1)
				,true);
		assertEquals("Should accept valid file", tester.validateLoadFile(
				"SaveFile1.csv") , true);
		
		//if loadFile 3 hasn't been created 
		if (!new File("SaveFile3.csv").isFile()) {
			//invalid input
			assertEquals("Shouldn't accept reading from valid but non"
					+ " existent file",
				tester.loadFile(3),false);
		}
		assertEquals("Shouldn't accept non existent file", 
				tester.validateLoadFile("SaveFile3.csv"), false);
		//invalid input (not possible in running program)
		assertEquals("Shouldn't accept reading from wrong file format",
				tester.validateLoadFile("SaveFile1.txt"), false);
		//invalid input (not possible in running program)
		assertEquals("Shouldn't accept reading from 'non-slot' file", 
				tester.validateLoadFile("SaveFile5.csv"),false);
	}
	
	/**
	 * Tests if a new game starts correctly
	 */
	@Test
	public void GameShouldStart(){
		SavedFile tester = m_interactingClass.createSavedFile();
		
		//valid input
		assertEquals("Created board tile values should be equal "
				+ "to the default amount",
				tester.startup(m_interactingClass.createArrayList(),
						m_interactingClass.createBoard(),
						m_interactingClass.createPlayer()), true);
		
		//invalid input
		assertEquals("Created board tile values shouldn't be "
				+ "equal to the default amount",
				tester.startup(m_interactingClass.createBadArrayList(),
						m_interactingClass.createBoard(),
						m_interactingClass.createPlayer()), false);
	}
	
	IntegrationTests m_interactingClass = new IntegrationTests();
}
