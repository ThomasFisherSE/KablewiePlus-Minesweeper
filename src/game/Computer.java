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
	private boolean easyFlag;
	private boolean normalFlag;
	private boolean perfectFlag;

	
	// AI Difficulty Probabilities
	private final double PERFECT_PROBABILITY = 1; // i.e. will always play perfectly
	private final double NORMAL_PROBABILITY = 0.9; // i.e. 1/10 moves won't be perfect
	private final double EASY_PROBABILITY = 50.0; // i.e. only 50% chance to make a perfect move
	private final double MAXIMUM = 100;
	private final double MINIMUM = 1;
	
	private void easyDifficulty() {
		double randomDouble = new Random().nextInt((int)MAXIMUM - (int)MINIMUM + 1);
		if (randomDouble <= (EASY_PROBABILITY)) {
			easyFlag = true;
			System.out.println("Prob val: " + EASY_PROBABILITY);
			System.out.println("Easy flag: " + easyFlag);
			System.out.println(randomDouble);
			System.out.println("Less than probability");
		} else if (randomDouble > (EASY_PROBABILITY)) {
			easyFlag = false;
			System.out.println("Prob val: " + EASY_PROBABILITY*2);
			System.out.println("Easy flag: " + easyFlag);
			System.out.println(randomDouble);
			System.out.println("More than probability");
		}
	}
	
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
		while (m_aiToggled) {
			boolean foundValidMove = false;
			
			do {
				if (makeMove()) foundValidMove = true;
					
				if (m_board.getm_GameLost()) {
					m_gameController.setGameLost();
					m_aiToggled = false;
				}
				
				if (m_board.getm_GameWon()) {
					m_gameController.setGameWin();
					m_aiToggled = false;
				}
			} while (!foundValidMove);
			
			try {
				Thread.sleep(SLEEP_TIME); //Wait 3 seconds
			} catch (InterruptedException e) {
				System.err.println("Failed to put thread to sleep.");
			}
		}
	}
	
	private boolean makeMove() {
		//easyDifficulty();
		Random rnd = new Random();
		int row = rnd.nextInt(m_board.getm_Board().size());
		int column = rnd.nextInt(m_board.getm_Board().get(row).size());
		Tile randomTile = m_board.getm_Board().get(row).get(column);
		if (!randomTile.isMine() && randomTile.isHidden()) {
			m_board.revealTile(column, row);
			m_gameController.repaintAll();
			System.out.println("Revealed tile: (" + row + "," + column + ")");
			return true;
		} else if (randomTile.isMine() && !randomTile.isDefused()) {
			/* if (easyFlag = true) {
				m_board.revealTile(column,row);
				m_gameController.repaintAll();
				System.out.println("Mine tile exploded.");
				easyFlag = false;
				return false;
			} else {*/
				m_board.defusedTile(column, row);
				m_gameController.repaintAll();
				System.out.println("Mine tile found. Looping.");
				return true;
			//}
		} else if (randomTile.isMine() && randomTile.isDefused()) {
			System.out.println("Mine already defused. Looping.");
			return false;
		} else {
			System.out.println("Revealed tile found. Looping.");
			return false;
		}
	}
}