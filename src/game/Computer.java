/**
 * @file Computer.java
 * @author Zongbo Xu
 * @date 5 December 2015
 *
 * A class for computer players.
 */ 

package game;

import java.util.Random;

public class Computer extends Player implements Runnable {
	private boolean m_aiToggled = false;
	private Board m_board;
	private GameController m_gameController;
	private int m_difficulty;
	private boolean m_test = true;
	
	private final int SLEEP_TIME = 2000;
	private final int MAXIMUM = 100;
	private final int MINIMUM = 1;
	
	// Common AI Difficulty Probabilities
	public static final int PERFECT_PROBABILITY = 100; // i.e. will always play perfectly
	public static final int NORMAL_PROBABILITY = 90; // i.e. 1/10 moves won't be perfect
	public static final int EASY_PROBABILITY = 50; // i.e. only 50% chance to make a perfect move
	
	
	public boolean toggleAi() {
		m_aiToggled = !m_aiToggled;
		return m_aiToggled;
	}
	
	public Computer(String name, Board board, GameController gc, int difficulty) {
		super(name);
		m_difficulty = difficulty;
		m_board = board;
		m_gameController = gc;
		makeStupidMove(); //Make first move
		m_gameController.repaintAll();
	}

	public void run() {
		while (m_aiToggled) {
			//Sleep here as computer will always have just made a move
			try {
				Thread.sleep(SLEEP_TIME); //Wait 3 seconds
			} catch (InterruptedException e) {
				System.err.println("Failed to put thread to sleep.");
			}
			
			boolean foundValidMove = false;
			
			do {
				if (makeMove()) foundValidMove = true;
				m_gameController.repaintAll();	
				checkGameOver();
			} while (!foundValidMove);
		}
	}
	
	private boolean checkGameOver() {
		if (m_board.getm_GameLost()) {
			m_gameController.setGameLost();
			m_aiToggled = false;
			return true;
		} else if (m_board.getm_GameWon()) {
			m_gameController.setGameWin();
			m_aiToggled = false;
			return true;
		} else {
			return false;
		}
	}
		
	private boolean makePerfectMove() {
				Random rnd = new Random();
				int row = rnd.nextInt(m_board.getm_Board().size());
				int column = rnd.nextInt(m_board.getm_Board().get(row).size());
				Tile randomTile = m_board.getm_Board().get(row).get(column);
				if (!randomTile.isMine() && randomTile.isHidden()) {
					m_board.revealTile(column, row);
					
					if (m_test) {
						System.out.println("Revealed tile: (" + row + "," + column + ")");
					}
					
					return true;
				} else if (randomTile.isMine() && !randomTile.isDefused()) {
					m_board.defusedTile(column, row);
					m_gameController.repaintAll();
					
					if (m_test) {
						System.out.println("Mine tile found. Looping.");
					}
					
					return true;
				} else if (randomTile.isMine() && randomTile.isDefused()) {
					if (m_test) {
						System.out.println("Mine already defused. Looping.");
					}
					
					return false;
				} else {
					if (m_test) {
						System.out.println("Revealed tile found. Looping.");
					}
					
					return false;
				}
	}
	
	private boolean makeMove() {
		double randomInt = new Random().nextInt(MAXIMUM - MINIMUM + 1);
		
		if (randomInt <= (m_difficulty)) {
			if (m_test) {
				System.out.println("Prob val: " + m_difficulty);
				System.out.println("RNG produced: " + randomInt);
				System.out.println("Less than probability, SUCCESS");
				System.out.println("(Make smart move)");
			}
			
			return makeSmartMove();
		} else {
			if (m_test) {
				System.out.println("Prob val: " + m_difficulty);
				System.out.println("RNG produced: " + randomInt);
				System.out.println("More than probability, FAIL");
				System.out.println("(Make stupid move)");
			}
			
			return makeStupidMove();
		}
	}
	
	private boolean makeSmartMove() {
		return makePerfectMove(); //Temporary, should be more intelligent than this
	}
	
	private boolean makeStupidMove() {
		boolean findingHiddenTile = true;
		
		while (findingHiddenTile) {
			Random rnd = new Random();
			int row = rnd.nextInt(m_board.getm_Board().size());
			int column = rnd.nextInt(m_board.getm_Board().get(row).size());
			Tile randomTile = m_board.getm_Board().get(row).get(column);
			
			if (randomTile.isHidden()) {
				m_board.revealTile(column, row);
				System.out.println("Revealed tile: (" + row + "," + column + ")");
				findingHiddenTile = false;
			}
		}
			
		return true;
	}
}