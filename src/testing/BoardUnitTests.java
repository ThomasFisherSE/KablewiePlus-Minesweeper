package testing;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;

import game.Board;
import game.Computer;
import game.GameController;
import game.Human;
import main.Kablewie;
import main.MainMenu;

public class BoardUnitTests {
	IntegrationTests interactingClass = new IntegrationTests();
	
	@Test
	public void testBoardComplete() {
		Board tester = interactingClass.createBoard();
		GameController gc = new GameController(tester, interactingClass.createHuman(), interactingClass.createFrame(), interactingClass.createMainMenu());
		Computer testComputer = new Computer("AI", tester, gc, Computer.CANNOT_LOSE);
		testComputer.run();
		assertEquals("Test if the board has been completed and the game is won",
				true, tester.getGameWon()); 
		//Currently not testing correctly, not returning a completed board.
	}
	
	@Test
	public void testBoardIncomplete() {
		Board tester = interactingClass.createBoard();
		GameController gc = new GameController(tester, interactingClass.createHuman(), interactingClass.createFrame(), interactingClass.createMainMenu());
		Computer testComputer = new Computer("AI", tester, gc, Computer.EASY_PROBABILITY);
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
