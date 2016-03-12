package testing;

import static org.junit.Assert.*;

import java.awt.Window;

import game.*;
import org.junit.Test;

public class ComputerUnitTests {
	IntegrationTests interactingClass = new IntegrationTests();
	
	@Test
	public void testLowIntelligencePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(), interactingClass.createGameController(), Computer.EASY_PROBABILITY);
		
		assertEquals("Test if the correct intelligence is set by clicking the Low-Intelligence preset",
				Computer.EASY_PROBABILITY, tester.getIntelligence());
	}
	
	@Test
	public void testNormalIntelligencePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(), interactingClass.createGameController(), Computer.NORMAL_PROBABILITY);
		
		assertEquals("Test if the correct intelligence is set by clicking the Low-Intelligence preset",
				Computer.NORMAL_PROBABILITY, tester.getIntelligence());
	}
	
	@Test
	public void testHighIntelligencePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(), interactingClass.createGameController(), Computer.PERFECT_PROBABILITY);
		
		assertEquals("Test if the correct intelligence is set by clicking the Low-Intelligence preset",
				Computer.PERFECT_PROBABILITY, tester.getIntelligence());
	}
	
	@Test
	public void testCantLosePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(), interactingClass.createGameController(), Computer.CANNOT_LOSE);
		
		assertEquals("Test if the correct intelligence is set by clicking the Low-Intelligence preset",
				Computer.CANNOT_LOSE, tester.getIntelligence());
	}
	
	@Test
	public void testAiOnLargeBoard() {
		Computer tester = new Computer("AI", new Board(
				Board.MAX_SIZE, Board.MAX_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(), Computer.PERFECT_PROBABILITY);
		
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
		
		assertEquals("Test if the computer player can always play to "
				+ "completion on a large board", true, tester.checkGameOver());
	}
	
	@Test
	public void testAiOnDefaultBoard() {
		Computer tester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(), Computer.PERFECT_PROBABILITY);
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: testAiOnDefaultBoard() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("Test if the computer player can always play to "
				+ "completion on a default sized board", true, tester.checkGameOver());
	}
	
	@Test
	public void testAiOnSmallBoard() {
		Computer tester = new Computer("AI", new Board(
				Board.MIN_SIZE, Board.MIN_SIZE, Board.MIN_SIZE), 
				interactingClass.createGameController(), Computer.PERFECT_PROBABILITY);
		
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
		
		assertEquals("Test if the computer player can always play to "
				+ "completion on a small sized board", true, tester.checkGameOver());
	}
	
	@Test
	public void testAiWithMaxMines() {
		Computer tester = new Computer("AI", new Board(
				Board.MAX_SIZE, Board.MAX_SIZE, Board.MAX_MINES), 
				interactingClass.createGameController(), Computer.PERFECT_PROBABILITY);
		
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
		
		assertEquals("Test if the computer player can always play to "
				+ "completion with the max number of mines", 
				true, tester.checkGameOver());
	}
	
	@Test
	public void testAiWithDefaultMines() {
		Computer tester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(), Computer.PERFECT_PROBABILITY);
		
		Thread testThread = new Thread(tester);
		testThread.start();
		tester.setTime(Computer.TEST_SLEEP_TIME);
		tester.toggleAi();
		
		try {
			testThread.join();
		} catch (InterruptedException e) {
			System.err.println("ComputerUnitTests :: testAiWithDefaultMines() -->"
					+ " Error interrupting thread.");
		}
		
		assertEquals("Test if the computer player can always play to "
				+ "completion with the default number of mines", 
				true, tester.checkGameOver());
	}
	
	@Test
	public void testAiWithMinMines() {
		Computer tester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.MIN_MINES), 
				interactingClass.createGameController(), Computer.PERFECT_PROBABILITY);
		
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
		
		assertEquals("Test if the computer player can always play to "
				+ "completion with the min number of mines", 
				true, tester.checkGameOver());
	}
	
	@Test
	public void testMinIntelligence() {
		Computer tester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(), Computer.MINIMUM_INTELLIGENCE);
		
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
		
		assertEquals("Test if the computer player can always play to "
				+ "completion with the min intelligence", 
				true, tester.checkGameOver());
	}
	
	@Test
	public void testMaxIntelligence() {
		Computer tester = new Computer("AI", new Board(
				Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES), 
				interactingClass.createGameController(), Computer.MAXIMUM_INTELLIGENCE);
		
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
		
		assertEquals("Test if the computer player can always play to "
				+ "completion with the max intelligence", 
				true, tester.checkGameOver());
	}
}
