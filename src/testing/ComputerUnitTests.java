package testing;

import static org.junit.Assert.*;

import game.*;
import org.junit.Test;

public class ComputerUnitTests {
	IntegrationTests interactingClass = new IntegrationTests();
	
	/**
	 * Testing the low intelligence preset in the JMenu
	 */
	@Test
	public void testLowIntelligencePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(),
				interactingClass.createGameController(),
				Computer.LOW_PROBABILITY);
		
		assertEquals("Test if the correct intelligence is set by clicking"
				+ " the Low-Intelligence preset",
				Computer.LOW_PROBABILITY, tester.getIntelligence());
	}
	
	/**
	 * Testing the normal intelligence preset in the JMenu
	 */
	@Test
	public void testNormalIntelligencePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(),
				interactingClass.createGameController(),
				Computer.NORMAL_PROBABILITY);
		
		assertEquals("Test if the correct intelligence is set by clicking"
				+ " the Normal-Intelligence preset",
				Computer.NORMAL_PROBABILITY, tester.getIntelligence());
	}
	
	/**
	 * Testing the high intelligence preset in the JMenu
	 */
	@Test
	public void testHighIntelligencePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(),
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		assertEquals("Test if the correct intelligence is set by clicking"
				+ " the High-Intelligence preset",
				Computer.PERFECT_PROBABILITY, tester.getIntelligence());
	}
	
	/**
	 * Testing the cannot lose intelligence preset in the JMenu
	 */
	@Test
	public void testCantLosePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(),
				interactingClass.createGameController(), Computer.CANNOT_LOSE);
		
		assertEquals("Test if the correct intelligence is set by using"
				+ " the cannot lose preset",
				Computer.CANNOT_LOSE, tester.getIntelligence());
	}
	
	/**
	 * Testing the computer player playing on a large board
	 */
	@Test
	public void testAiOnLargeBoard() {
		Computer tester = new Computer("AI", new Board(
				Board.MAX_SIZE, Board.MAX_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: testAiOnLargeBoard() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("The computer player should be able to play to completion"
				+ " on a large board", true, tester.checkGameOver());
	}
	
	/**
	 * Testing the computer player playing on a default board
	 */
	@Test
	public void testAiOnDefaultBoard() {
		Computer tester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: testAiOnDefaultBoard()"
					+ " --> Error interrupting thread.");
		}
		
		assertEquals("The computer player should be able to play to completion"
				+ " on a default board", true, tester.checkGameOver());
	}
	
	/**
	 * Testing the computer player playing on a small board
	 */
	@Test
	public void testAiOnSmallBoard() {
		Computer tester = new Computer("AI", new Board(
				Board.MIN_SIZE, Board.MIN_SIZE, Board.MIN_SIZE), 
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: testAiOnSmallBoard() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("The computer player should be able to play to completion"
				+ " on a small board", true, tester.checkGameOver());
	}
	
	/**
	 * Testing the computer player playing with the max number of mines
	 */
	@Test
	public void testAiWithMaxMines() {
		Computer tester = new Computer("AI", new Board(
				Board.MAX_SIZE, Board.MAX_SIZE, Board.MAX_MINES), 
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: testAiWithMaxMines() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("The computer player should be able to play to completion"
				+ " with the max number of mines", 
				true, tester.checkGameOver());
	}
	
	/**
	 * Testing the computer player playing with the default number of mines
	 */
	@Test
	public void testAiWithDefaultMines() {
		Computer tester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: testAiWithDefaultMines()"
					+ " --> Error interrupting thread.");
		}
		
		assertEquals("The computer player should be able to play to completion"
				+ " with the default number of mines", 
				true, tester.checkGameOver());
	}
	
	/**
	 * Testing the computer player playing with the min number of mines
	 */
	@Test
	public void testAiWithMinMines() {
		Computer tester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.MIN_MINES), 
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: testAiWithMinMines() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("The computer player should be able to play to completion"
				+ " with the min number of mines", 
				true, tester.checkGameOver());
	}
	
	/**
	 * Testing the computer player playing with the min possible intelligence
	 */
	@Test
	public void testMinIntelligence() {
		Computer tester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(), 
				Computer.MINIMUM_INTELLIGENCE);
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: testMinIntelligence() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("The computer player should be able to play to completion"
				+ " with min intelligence", true, tester.checkGameOver());
	}
	
	/**
	 * Testing the computer player playing with the max possible intelligence
	 */
	@Test
	public void testMaxIntelligence() {
		Computer tester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(), 
				Computer.MAXIMUM_INTELLIGENCE);
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: testMaxIntelligence() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("The computer player should be able to play to completion"
				+ " with max intelligence", true, tester.checkGameOver());
	}
	
	/**
	 * Testing the computer player playing when mines are revealed
	 */
	@Test
	public void testAiWhenMinesRevealed() {
		Board testBoard = new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES);
		
		Computer tester = new Computer("AI", testBoard, 
				interactingClass.createGameController(), 
				Computer.PERFECT_PROBABILITY);
		
		testBoard.showBombTile();
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: testAiWhenMinesRevealed()"
					+ " --> Error interrupting thread.");
		}
		
		assertEquals("The computer player should be able to play to completion"
				+ " when mines are revealed", true, tester.checkGameOver());
	}
	
	/**
	 * Testing running multiple computer players after each other
	 */
	@Test
	public void testRunningAiConsecutively() {
		Board testBoard = new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES);
		
		GameController testGameController = 
				interactingClass.createGameController();
		
		Computer tester = new Computer("AI", testBoard, testGameController, 
				Computer.MAXIMUM_INTELLIGENCE);
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: "
					+ "testRunningAiConsecutively() -->"
					+ " Error interrupting thread.");
		}
		
		tester = new Computer("AI", testBoard, testGameController, 
				Computer.MAXIMUM_INTELLIGENCE);
		
		testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: "
					+ "testRunningAiConsecutively() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("The computer player should be able to play to completion"
				+ " when running several computer players consecutively", 
				true, tester.checkGameOver());
	}
	
	/**
	 * Testing the generateMoveLists() method
	 */
	@Test
	public void testGeneratingMoves() {
		Computer newBoardTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		Computer midGameTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES),
				interactingClass.createGameController(),
				Computer.CANNOT_LOSE);
		
		
		/**
		 * Make 9 perfect moves; since there are 10 mines, 
		 * after 9 perfect moves, there should always be a smart move possible
		 */
		for (int i = 0; i<10; i++) {
			midGameTester.makePerfectMove();
		}
		
		assertEquals("Move lists should be able to be generated for a new "
				+ "board", 
				false, newBoardTester.generateMoveLists());
		
		assertEquals("Move lists should be able to be generated mid-game", 
				true, midGameTester.generateMoveLists());
	}
	
	/**
	 * Testing the makeStupidMove() method
	 */
	@Test
	public void testMakeStupidMove() {
		Computer newBoardTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		Computer midGameTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES),
				interactingClass.createGameController(),
				Computer.CANNOT_LOSE);
		
		for (int i = 0; i<10; i++) {
			midGameTester.makePerfectMove();
		}
		
		Computer gameOverTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES),
				interactingClass.createGameController(),
				Computer.CANNOT_LOSE);
		
		Thread testThread = new Thread(gameOverTester);
		testThread.start();
		gameOverTester.setTime(Computer.TEST_SLEEP_TIME);
		gameOverTester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: "
					+ "testMakeStupidMove() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("A stupid move should be able to be made on a new board", 
				true, newBoardTester.makeStupidMove());
		
		assertEquals("A stupid move should be able to be made mid-game", 
				true, midGameTester.makeStupidMove());
		
		assertEquals("A stupid move should be able to be made when "
				+ "the game is over", false, gameOverTester.makeStupidMove());
	}
	
	/**
	 * Testing the makePerfectMove() method
	 */
	@Test
	public void testMakePerfectMove() {
		Computer newBoardTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		Computer midGameTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES),
				interactingClass.createGameController(),
				Computer.CANNOT_LOSE);
		
		for (int i = 0; i<10; i++) {
			midGameTester.makePerfectMove();
		}
		
		Computer gameOverTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES),
				interactingClass.createGameController(),
				Computer.CANNOT_LOSE);
		
		Thread testThread = new Thread(gameOverTester);
		testThread.start();
		gameOverTester.setTime(Computer.TEST_SLEEP_TIME);
		gameOverTester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: "
					+ "testMakePerfectMove() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("A perfect move should be able to be made on a new board", 
				true, newBoardTester.makePerfectMove());
		
		assertEquals("A perfect move should be able to be made mid-game", 
				true, midGameTester.makePerfectMove());
		
		assertEquals("A perfect move should be able to be made when "
				+ "the game is over", false, gameOverTester.makePerfectMove());
	}
	
	/**
	 * Testing the makeMove() method
	 */
	@Test
	public void testMakeMove() {
		Computer newBoardTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		Computer midGameTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES),
				interactingClass.createGameController(),
				Computer.CANNOT_LOSE);
		
		for (int i = 0; i<10; i++) {
			midGameTester.makePerfectMove();
		}
		
		Computer gameOverTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES),
				interactingClass.createGameController(),
				Computer.CANNOT_LOSE);
		
		Thread testThread = new Thread(gameOverTester);
		testThread.start();
		gameOverTester.setTime(Computer.TEST_SLEEP_TIME);
		gameOverTester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: "
					+ "testMakeMove() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("A move should be able to be made on a new board", 
				true, newBoardTester.makeMove());
		
		assertEquals("A move should be able to be made mid-game", 
				true, midGameTester.makeMove());
		
		assertEquals("A move should be able to be made when the game is over", 
				false, gameOverTester.makeMove());
	}
	
	/**
	 * Testing the makeSmartMove() method
	 */
	@Test
	public void testMakeSmartMove() {
		Computer newBoardTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(),
				Computer.PERFECT_PROBABILITY);
		
		Computer midGameTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES),
				interactingClass.createGameController(),
				Computer.CANNOT_LOSE);
		
		for (int i = 0; i<10; i++) {
			midGameTester.makePerfectMove();
		}
		
		Computer gameOverTester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES),
				interactingClass.createGameController(),
				Computer.CANNOT_LOSE);
		
		Thread testThread = new Thread(gameOverTester);
		testThread.start();
		gameOverTester.setTime(Computer.TEST_SLEEP_TIME);
		gameOverTester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: "
					+ "testMakeSmartMove() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("A smart move should be able to be made on a new board", 
				true, newBoardTester.makeSmartMove());
		
		assertEquals("A smart move should be able to be made mid-game", 
				true, midGameTester.makeSmartMove());
		
		assertEquals("A smart move should not be made when the game is over", 
				false, gameOverTester.makeSmartMove());
	}
}
