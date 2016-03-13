/**
 * @file BoardUnitTests.java

 * @author Ethan Davies
 * @Date 13/03/2016
 * @see Board.java
 * @brief Tests board initialization 
 * 
 * Tests methods in Board that return values and initialize board states
 */

package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Board;
import game.Computer;

/**
 * 
 * @class BoardUnitTests
 * @brief Tests board initialization 
 * 
 * Tests methods in Board that return values and initialize board states
 */
public class BoardUnitTests {
	/**
	 * Testing the return of the correct column value
	 */
	@Test
	public void testColumnReturn() {
		Board tester = m_interactingClass.createBoard();
		int col = tester.getColumns();
		assertEquals("Test if correct columns are returned",
				col,tester.getColumns());
	}
	/**
	 * Testing the return of the correct row value
	 */
	@Test
	public void testRowReturn() {
		Board tester = m_interactingClass.createBoard();
		int row = tester.getRows();
		assertEquals("Test if the correct row count is returned",
				row,tester.getRows());
		
	}
	/**
	 * Testing if the correct number of mines is being returned
	 */
	@Test
	public void testMineReturn() {
		Board tester = m_interactingClass.createBoard();
		int mine = tester.getMineCount();
		assertEquals("Test if the correct mine count is returned",
				mine,tester.getMineCount());
	}
	/**
	 * Testing if the correct time value is being returned
	 */
	@Test
	public void testTimeReturn () {
		Board tester = m_interactingClass.createBoard();
		String time = tester.getTimePassed();
		assertEquals("Test correct time return",time,tester.getTimePassed());
	}
	/**
	 * Testing if the board can be played to completion or not
	 */
	@Test
	public void testBoardComplete() {
		Board tester = m_interactingClass.createBoard();
		Computer testComputer = new Computer("AI", tester,
				m_interactingClass.createGameController(),
				Computer.CANNOT_LOSE);
		Thread testThread = new Thread(testComputer);
		testThread.start();
		testComputer.setTime(Computer.TEST_SLEEP_TIME);
		testComputer.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("BoardUnitTests :: testBoardComplete() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("Test if the board has been completed and the game "
				+ "is won",
				true, tester.getGameWon()); 
	}
	/**
	 * Testing for when the board is not played to completion
	 */
	@Test
	public void testBoardIncomplete() {
		Board tester = m_interactingClass.createBoard();
		Computer testComputer = new Computer("AI", tester,
				m_interactingClass.createGameController(),
				Computer.LOW_PROBABILITY);
		Thread testThread = new Thread(testComputer);
		testThread.start();
		testComputer.setTime(Computer.TEST_SLEEP_TIME);
		testComputer.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("BoardUnitTests :: testBoardComplete() -->"
					+ " Error interrupting thread.");
		}
		assertEquals("Test if the board has not been completed",
				false, tester.getGameWon()); 
	}
	/**
	 * Testing the default board initialization
	 */
	@Test
	public void testBoardDefaultInitialization() {
		assertEquals("Test if the boards columns are initialized correclty",
				10, m_interactingClass.createBoard().getColumns());
		assertEquals("Test if the boards rows are initialized correclty",
				10, m_interactingClass.createBoard().getRows());
		assertEquals("Test if the boards mines are initialized correclty",
				10, m_interactingClass.createBoard().getMineCount());
	}
	/**
	 * Testing a custom board initialization
	 */
	@Test
	public void testBoardCustomInitialization() {
		int col,row,mines;
		col = 15;
		row = 15;
		mines = 30;
		Board tester = new Board(row,col,mines);
		assertEquals("Test if the boards custom column size is initialized"
				+ " correctly",
				col,tester.getColumns());
		assertEquals("Test if the boards custom column size is initialized"
				+ " correctly",
				row,tester.getRows());
		assertEquals("Test if the boards custom column size is initialized"
				+ " correctly",
				mines,tester.getMineCount());
	}
	/**
	 * Testing the bounds of the board in which a turn can be made
	 */
	@Test
	public void testBoardBounds() {
		assertEquals("Test to ensure the board does not accept invalid bounds",
				false, m_interactingClass.createBoard().inLimit(-1,-1));
		assertEquals("Test to ensure the board accepts valid board bounds",
				true, m_interactingClass.createBoard().inLimit(0,0));
	}
	
	IntegrationTests m_interactingClass = new IntegrationTests();
}
