/**
 * @file KablewieUnitTests.java
 * @author Ibrahim Shehu
 * @Date 11/03/2016
 * @see Kablewie.java
 * @brief Tests Kablewie class
 * 
 * Tests all methods in Kablewie class with valid input
 */
package testing;

import static org.junit.Assert.*;
import main.Kablewie;

import org.junit.Test;

/**
 * 
 * @class KablewieUnitTests
 * @brief Tests Kablewie class
 * 
 * Tests all methods in Kablewie class with valid input
 * /
public class KablewieUnitTests {

	/** 
	 * Testing to the testStartGame with valid input 
	 */
	@Test
	public void testStartGame() {
		Kablewie tester = new Kablewie();
		//valid input
		assertEquals("Test if input is valid",true,tester.startGame(
				m_interactingClass.createBoard(), 
				m_interactingClass.createPlayer(),
				m_interactingClass.createMainMenu()));	
		

	}

	/** 
	 * Testing to the testStartGame2 with invalid input 
	 */
	@Test
	public void testStartGame2() {
		Kablewie tester = new Kablewie();		
		assertEquals("Test if input is not valid",false,tester.startGame(
				m_interactingClass.createBoard(),
				m_interactingClass.createPlayer(),
				m_interactingClass.createMainMenu()));	

	}	
	/** 
	 * Testing to the testStartLoadedGame with valid input 
	 */
	@Test
	public void testStartLoadedGame() {
		Kablewie tester = new Kablewie();	
		String time = "00:00:07";
		assertEquals("Test if input is valid",true,tester.startLoadedGame(
				m_interactingClass.createBoard(),
				m_interactingClass.createPlayer(),time));	
		

	}
	/** 
	 * Testing to the testStartLoadedGame2 with invalid input 
	 */
	@Test
	public void testStartLoadedGame2() {
		Kablewie tester = new Kablewie();	
		String time = "00:00:07";
		assertEquals("Test if input is not valid",false,tester.startLoadedGame(
				m_interactingClass.createBoard(),
				m_interactingClass.createPlayer(),time));	

	}
	
	IntegrationTests m_interactingClass = new IntegrationTests();
}
