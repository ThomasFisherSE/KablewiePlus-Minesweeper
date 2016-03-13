/**
 * @file MainMenuUnitTests.java
 * @author Gideon Eromosele
 * @Date 12/03/2016
 * @see MainMenu.java
 * @brief Tests MainMenu class
 * 
 * Tests all method in MainMenu class with valid and invalid input
 */
package testing;

import static org.junit.Assert.assertEquals;
import main.*;
import org.junit.Test;

public class MainMenuUnitTests {
	IntegrationTests interactingClass = new IntegrationTests();
	
	/** 
	 * Testing to the testStartGame with valid input 
	 */
	@Test
	public void startGameTest1() {
		MainMenu test = new MainMenu(interactingClass.createFrame(), new Kablewie());
		
		//The statement is set to false, if and only if inputs to start game is true
		assertEquals("Test if the inputs are valid to start Game", false, 
				test.startGame());
	}
	
	/** 
	 * Testing to the testStartGame with valid input 
	 */
	@Test
	public void startGameTest2() {
		MainMenu test = new MainMenu(interactingClass.createFrame(), new Kablewie());
		
		//The statement is set to true, if and only if inputs to start game is false
		assertEquals("Test if the inputs are not valid to start Game",
				true, test.startGame());
	}
}
