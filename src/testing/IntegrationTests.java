package testing;

import javax.swing.JFrame;

import game.Board;
import game.Computer;
import game.GameController;
import game.Human;
import main.Kablewie;
import main.MainMenu;

public class IntegrationTests {
	
	public static void main(String[] args) {
		BoardUnitTests boardTests = new BoardUnitTests();
		boardTests.testBoardBounds();
		boardTests.testBoardComplete();
		boardTests.testBoardIncomplete();
		boardTests.testBoardInitialization();
		
		ComputerUnitTests computerTests = new ComputerUnitTests();
		computerTests.testCantLosePreset();
		computerTests.testHighIntelligencePreset();
		computerTests.testLowIntelligencePreset();
		computerTests.testNormalIntelligencePreset();
		
	}
	
	public Board createBoard() {
		return new Board(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES);
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