package testing;

import javax.swing.JFrame;
import game.*;
import main.*;

public class IntegrationTests {
	public static void main(String[] args) {
		
		try {
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
			computerTests.testAiOnDefaultBoard();
			computerTests.testAiOnLargeBoard();
			computerTests.testAiWithDefaultMines();
			computerTests.testAiWithMaxMines();
			computerTests.testAiWithMinMines();
			computerTests.testMaxIntelligence();
			computerTests.testMinIntelligence();
			
			SavedFileUnitTests savedFileTests = new SavedFileUnitTests();
			savedFileTests.FileShouldLoadCorrectly();
			savedFileTests.FileShouldSaveCorrectly();
			savedFileTests.GameShouldStart();
			
			System.out.println("Tests were successful.");
		} catch (Exception e) {
			System.err.println("A test failed: \n" + e);
		}
		
		System.exit(0);
	}
	
	public Board createBoard() {
		return new Board(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE, Board.DEFAULT_MINES);
	}
	
	public GameController createGameController() {
		GameController gc = new GameController(createBoard(), createHuman(), createFrame(), createMainMenu());
		gc.asTest();
		return gc;
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
    
    public Player createPlayer() {
    	return new Player("testPlayer");
    }
    
    public SavedFile createSavedFile() {
    	return new SavedFile();
    }
}
