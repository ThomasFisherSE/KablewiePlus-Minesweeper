package testing;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import game.*;
import main.*;

import org.junit.Test;

public class ComputerUnitTests {
	@Test
	public void testLowIntelligencePreset() {
		Computer tester = new Computer("AI", createBoard(), createGameController(), Computer.EASY_PROBABILITY);
		
		assertEquals("Test if the correct intelligence is set by clicking the Low-Intelligence preset",
				Computer.EASY_PROBABILITY, tester.getIntelligence());
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
