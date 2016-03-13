package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import game.*;

public class BoardUnitTests {
	IntegrationTests interactingClass = new IntegrationTests();
	
	@Test
	public void testBoardComplete() {
		Board tester = interactingClass.createBoard();
		Computer testComputer = new Computer("AI", tester, interactingClass.createGameController(), Computer.CANNOT_LOSE);
		Thread testThread = new Thread(testComputer);
		testThread.start();
		testComputer.setTime(Computer.TEST_SLEEP_TIME);
		testComputer.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("BoardUnitTests :: testBoardComplete() --> Error interrupting thread.");
		}
		
		assertEquals("Test if the board has been completed and the game is won",
				true, tester.getGameWon()); 
	}
	
	@Test
	public void testBoardIncomplete() {
		Board tester = interactingClass.createBoard();
		Computer testComputer = new Computer("AI", tester, interactingClass.createGameController(), Computer.LOW_PROBABILITY);
		testComputer.run();
		assertEquals("Test if the board has been completed and the game is won",
				false, tester.getGameWon()); 
	}
	
	@Test
	public void testBoardInitialization() {
		assertEquals("Test if the boards columns are initialized correclty",
				10, interactingClass.createBoard().getColumns());
		assertEquals("Test if the boards rows are initialized correclty",
				10, interactingClass.createBoard().getRows());
		assertEquals("Test if the boards mines are initialized correclty",
				10, interactingClass.createBoard().getMineCount());
	}
	
	@Test
	public void testBoardBounds() {
		assertEquals("Test to ensure the board does not accept invalid bounds",
				false, interactingClass.createBoard().inLimit(-1,-1));
		assertEquals("Test to ensure the board accepts valid board bounds",
				true, interactingClass.createBoard().inLimit(0,0));
	}
}
