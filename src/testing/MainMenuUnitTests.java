package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Assert.*;

import main.*;

import org.junit.Test;

public class MainMenuUnitTests {
	IntegrationTests interactingClass = new IntegrationTests();

	@Test
	public void startGameTest1() {
		MainMenu test = new MainMenu(interactingClass.createFrame(), new Kablewie());
		
		assertEquals("Test if the inputs are valid to start Game", true, 
				test.startGame());
	}

	@Test
	public void startGameTest2() {
		MainMenu test = new MainMenu(interactingClass.createFrame(), new Kablewie());
		
		assertEquals("Test if the inputs are not valid to start Game",
				false, test.startGame());
	}
}
