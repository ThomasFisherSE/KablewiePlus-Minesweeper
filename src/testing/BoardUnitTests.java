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

	@Test
	public void testBoardComplete() {
		Board tester = createBoard();
		GameController gc = new GameController(tester, createHuman(), createFrame(), createMainMenu());
		Computer testComputer = new Computer("AI", tester, gc, Computer.CANNOT_LOSE);
		testComputer.run();
		assertEquals("Test if the board has been completed and the game is won",
				true, tester.getGameWon()); 
		//Currently not testing correctly, not returning a completed board.
	}
	
	@Test
	public void testBoardIncomplete() {
		Board tester = createBoard();
		GameController gc = new GameController(tester, createHuman(), createFrame(), createMainMenu());
		Computer testComputer = new Computer("AI", tester, gc, Computer.EASY_PROBABILITY);
		testComputer.run();
		assertEquals("Test if the board has been completed and the game is won",
				false, tester.getGameWon()); 
	}
	
	@Test
	public void testBoardInitialization() {
		assertEquals("Test if the boards columns are initialized correclty",
				10, createBoard().getColumns());
		assertEquals("Test if the boards rows are initialized correclty",
				10, createBoard().getRows());
		assertEquals("Test if the boards mines are initialized correclty",
				10, createBoard().getMineCount());
	}
	
	@Test
	public void testBoardBounds() {
		assertEquals("Test to ensure the board does not accept invalid bounds",
				false, createBoard().inLimit(-1,-1));
		assertEquals("Test to ensure the board accepts valid board bounds",
				true, createBoard().inLimit(0,0));
	}
	
	public Board createBoard() {
		return new Board(10,10,10);
	}
	
	public GameController createGameController() {
		return new GameController(createBoard(), createHuman(), createFrame(), createMainMenu());
	}
	
	public MainMenu createMainMenu() {
		return new MainMenu(createFrame(), new Kablewie());
	}
	
	
	public JFrame createFrame() {
		return new JFrame("testFrame");
	}
	
    public Computer createComputer() {
        return new Computer("AI", createBoard(), createGameController(), 0);
    }
    
    public Human createHuman() {
    	return new Human("testPlayer");
    }

}
