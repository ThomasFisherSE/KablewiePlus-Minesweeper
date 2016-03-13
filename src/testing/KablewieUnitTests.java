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

public class KablewieUnitTests {
	IntegrationTests interactingClass = new IntegrationTests();

	@Test
	public void testStartGame() {
		Kablewie tester = new Kablewie();
		//valid input
		assertEquals("Test if input is valid",true,tester.startGame(interactingClass.createBoard(), interactingClass.createPlayer(), interactingClass.createMainMenu()));	
		

	}
	@Test
	public void testStartGame2() {
		Kablewie tester = new Kablewie();		
		//invalid input
		assertEquals("Test if input is not valid",false,tester.startGame(interactingClass.createBoard(), interactingClass.createPlayer(), interactingClass.createMainMenu()));	

	}	
	
	
	@Test
	public void testStartLoadedGame() {
		Kablewie tester = new Kablewie();	
		String time = "00:00:07";
		//valid input
		assertEquals("Test if input is valid",true,tester.startLoadedGame(interactingClass.createBoard(), interactingClass.createPlayer(),time));	
		

	}
	@Test
	public void testStartLoadedGame2() {
		Kablewie tester = new Kablewie();	
		String time = "00:00:07";
		
		//invalid input
		assertEquals("Test if input is not valid",false,tester.startLoadedGame(interactingClass.createBoard(), interactingClass.createPlayer(),time));	

	}
	
		
}
