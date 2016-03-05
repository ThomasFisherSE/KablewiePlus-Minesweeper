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
	private final int SLEEP_TIME = 2000;
	
	// AI Difficulty Probabilities
	private final double PERFECT_PROBABILITY = 1; // i.e. will always play perfectly
	private final double NORMAL_PROBABILITY = 0.9; // i.e. 1/10 moves won't be perfect
	private final double EASY_PROBABILITY = 0.5; // i.e. only 50% chance to make a perfect move
	
	
	public boolean toggleAi() {
		m_aiToggled = !m_aiToggled;
		return m_aiToggled;
	}
	
	public Computer(String name, Board board, GameController gc) {
		super(name);
		m_board = board;
		m_gameController = gc;
	}

	public void run() {
		while (true) {
			while (m_aiToggled) {
				boolean foundValidMove = false;
				do {
					if (makeMove()) foundValidMove = true;
				} while (!foundValidMove);
				
				try {
					Thread.sleep(SLEEP_TIME); //Wait 3 seconds
				} catch (InterruptedException e) {
					System.err.println("Failed to put thread to sleep.");
				}
			}
		}
	}
	
	private boolean makeMove() {
		Random rnd = new Random();
		int row = rnd.nextInt(m_board.getm_Board().size());
		int column = rnd.nextInt(m_board.getm_Board().get(row).size());
		Tile randomTile = m_board.getm_Board().get(row).get(column);
		
		if (!randomTile.isMine() && randomTile.isHidden()) {
			m_board.revealTile(column, row);
			m_gameController.repaintAll();
			System.out.println("Revealed tile: (" + row + "," + column + ")");
			return true;
		} else {
			System.out.println("Mine or already revealed tile found. Looping.");
			return false;
		}
	}
		
		return true;
	}
}