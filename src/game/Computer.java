/**
 * @file Computer.java
 * @author A5 Thomas Fisher, Ethan Davies  A3 Zongbo Xu
 * @date 5 December 2015
 *
 * A class for computer players.
 */ 

package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Computer extends Player implements Runnable {
	
	private final int MAXIMUM = 100;
	private final int MINIMUM = 1;
	private final int MILLISEC_IN_SEC = 1000;
	
	private boolean m_aiToggled = false;
	private Board m_board;
	private GameController m_gameController;
	private int m_intelligence;
	private boolean m_test = false;
	private int m_sleepTime = (int) (DEFAULT_SLEEP_TIME * 100);
	private ArrayList<Tile> m_knownSmartMoves = new ArrayList<Tile>();
	private ArrayList<Tile> m_knownBombs = new ArrayList<Tile>();
	
	
	// Common AI Probability Presets
	// Cheating AI, knows where each mine is
	public static final int CANNOT_LOSE = -1;
	
	// Will always make smart moves where possible
	public static final int PERFECT_PROBABILITY = 100; 
	
	// 1/10 moves won't be perfect
	public static final int NORMAL_PROBABILITY = 90; 
	
	// Only 50% chance to make a perfect move
	public static final int EASY_PROBABILITY = 50; 
	
	// The default time in seconds between turns
	public static final double DEFAULT_SLEEP_TIME = 2;
	
	//The time to wait between turns when testing
	public static final double TEST_SLEEP_TIME = 0.01;
	/**
	 * Accessor method for the current intelligence of the ai
	 * 
	 * @return int, the intelligence of the ai
	 */
	public int getIntelligence() {
		return m_intelligence;
	}
	
	/**
	 *  Accessor method for if the computer ai is running currently
	 *  
	 *  @return boolean, true if ai is toggled, false if not
	 */
	public boolean isRunning() {
		return m_aiToggled;
	}
	
	/**
	 *  Mutator method for toggling the ai on or off
	 *  
	 *  @return boolean, true if the ai is now toggled on, false if it's off
	 */
	public boolean toggleAi() {
		m_aiToggled = !m_aiToggled;
		return m_aiToggled;
	}
	
	/**
	 *  Mutator method for time between turns
	 *  
	 *  @param time a double, the time in seconds between turns
	 */
	public void setTime(double time) {
		m_sleepTime = (int) (time*MILLISEC_IN_SEC);
	}
	
	/**
	 * Constructor
	 * 
	 * @param name a String, the name given to the computer player
	 * @param board a Board object, the board the computer player will make moves on
	 * @param gc a GameController object, the class in charge of setting up games
	 * @param intelligence an integer, the chance out of 100 a smart move will be made
	 */
	public Computer(String name, Board board, GameController gc, int intelligence) {
		super(name);
		m_intelligence = intelligence;
		m_board = board;
		m_gameController = gc;
	}
	
	/**
	 *  Run the ai
	 */
	public void run() {
		makeMove(); //Make first move
		m_gameController.repaintAll();
		checkGameOver();
		
		while (m_aiToggled) {
			//Sleep here as computer will always have just made a move
			try {
				Thread.sleep(m_sleepTime); //Wait 3 seconds
			} catch (InterruptedException e) {
				System.err.println("Failed to put thread to sleep.");
			}
			
			boolean foundValidMove = false;
			
			do {
				if (makeMove()) foundValidMove = true;
				m_gameController.repaintAll();	
				checkGameOver();
			} while (!foundValidMove && m_aiToggled);
		}
	}
	
	/**
	 *  Check if the computer has put the game in a state where it is over
	 *  
	 *  @return boolean, true if game is over, false if not
	 */
	private boolean checkGameOver() {
		if (m_board.getGameLost()) {
			m_gameController.setGameLost();
			m_aiToggled = false;
			return true;
		} else if (m_board.getGameWon()) {
			m_gameController.setGameWin();
			m_aiToggled = false;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 *  Make a perfect move where the AI cheats (knows where mines are)
	 *  
	 *  @return boolean, true if a move has been made, false if not
	 */
	private boolean makePerfectMove() {
				Random rnd = new Random();
				int row = rnd.nextInt(m_board.getBoard().size());
				int column = rnd.nextInt(m_board.getBoard().get(row).size());
				Tile randomTile = m_board.getBoard().get(row).get(column);
				if (!randomTile.isMine() && randomTile.isHidden()) {
					m_board.revealTile(column, row);
					
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
	
	/**
	 * Decide what type of move is most appropriate and make it based
	 * 
	 * @return boolean, true if move has been made, false if not
	 */
	private boolean makeMove() {
		double randomInt = new Random().nextInt(MAXIMUM - MINIMUM + 1);
		
		if (randomInt <= (m_intelligence)) {
			if (m_test) {
				System.out.println("Prob val: " + m_intelligence);
				System.out.println("RNG produced: " + randomInt);
				System.out.println("Less than probability, SUCCESS");
				System.out.println("(Make smart move)");
			}
			
			return makeSmartMove();
		} else if (randomInt > m_intelligence && m_intelligence > 0){
			if (m_test) {
				System.out.println("Prob val: " + m_intelligence);
				System.out.println("RNG produced: " + randomInt);
				System.out.println("More than probability, FAIL");
				System.out.println("(Make stupid move)");
			}
			
			return makeStupidMove();
		} else if (m_intelligence == CANNOT_LOSE) {
			if (m_test) {
				System.out.println("Can't lose; make perfect move");
			}
			
			return makePerfectMove();
		}
		
		return false;
	}
	
	/**
	 *  Generate a list of safe, smart moves
	 */
	private void generateMoveLists() {
		m_knownSmartMoves.clear();
		m_knownBombs.clear();
		
		for (int i = 0; i < m_board.getBoard().size(); i++) {
			for (int j = 0; j < m_board.getBoard().size(); j++) {
				Tile tile = m_board.getBoard().get(i).get(j);
				
				ArrayList<Tile> surroundingTiles = tile.getTileArround(
						m_board.getBoard(), i, j);
				int flagged = 0;
				int hidden = 0;
				
				for(Iterator<Tile> iter = surroundingTiles.iterator(); 
						iter.hasNext(); ) {
				    Tile outerTile = iter.next();
				    
				    if (outerTile.isDefused()) {
						flagged++;
					}
					
					if (outerTile.isHidden()) {
						hidden++;
					} else {
						//If an outer tile is already revealed, 
						// it can't be used, so remove it
						iter.remove();
					}
				}
				
				if ((tile.calculateNearbyMines(tile, surroundingTiles) 
						== flagged) && !tile.isHidden()) {
					//All bombs have been found
					for (Tile outerTile: surroundingTiles) {
						if (outerTile.isHidden() && 
								!m_knownSmartMoves.contains(outerTile) && 
								!outerTile.isMine()) {
							m_knownSmartMoves.add(outerTile);
						}
					}
				}
				
				if ((tile.calculateNearbyMines(tile, surroundingTiles) == 
						(flagged + hidden)) && !tile.isHidden()) {
					//All surrounding tiles must be bombs, so flag them
					for (Tile outerTile: surroundingTiles) {
						if (outerTile.isHidden() && !outerTile.isDefused() &&
								!m_knownBombs.contains(outerTile)) {
							m_knownBombs.add(outerTile);
						}
					}
				}
				
				if (surroundingTiles.size() == 0 &&
						!m_knownBombs.contains(tile) && tile.isHidden()) {
					//No hidden surrounding tiles, centre tile must be a bomb
					m_knownBombs.add(tile);
				}
			}
		}
	}
	
	/**
	 *  Make a smart move based on a generated list of smart moves
	 *  
	 *  @return boolean, true if move has been made, false if not
	 */
	private boolean makeSmartMove() {
		generateMoveLists();
		
		if (!m_knownBombs.isEmpty()) {
			Tile tileToDefuse = m_knownBombs.get(0);
			
			for (int i = 0; i < m_board.getBoard().size(); i++) {
				for (int j = 0; j < m_board.getBoard().size(); j++) {
					if (m_board.getBoard().get(i).get(j).equals(tileToDefuse)){
						m_board.defusedTile(j, i);
						
						m_knownBombs.remove(0);
						
						
					}
				}
			}
		} else if (!m_knownSmartMoves.isEmpty()) {
			Tile tileToReveal = m_knownSmartMoves.get(0);
			
			for (int i = 0; i < m_board.getBoard().size(); i++) {
				for (int j = 0; j < m_board.getBoard().size(); j++) {
					if (m_board.getBoard().get(i).get(j).equals(tileToReveal)){
						m_board.revealTile(j, i);
						
						m_knownSmartMoves.remove(0);
					}
				}
			}
		} else {
			if (m_test) {
				System.out.println("No smart moves available, "
						+ "make stupid move.");
			}
			return makeStupidMove();
		}
		
		return true;
	}
	
	/**
	 *  Make a stupid move, where it is possible that a mine can be revealed
	 *  
	 *  @return boolean, true if move has been made, false if not
	 */
	private boolean makeStupidMove() {
		boolean findingHiddenTile = true;
		
		while (findingHiddenTile) {
			Random rnd = new Random();
			int row = rnd.nextInt(m_board.getBoard().size());
			int column = rnd.nextInt(m_board.getBoard().size());
			Tile randomTile = m_board.getBoard().get(column).get(row);
			
			if (randomTile.isHidden() && !randomTile.isDefused()) {
				m_board.revealTile(row, column);
				findingHiddenTile = false;
			}
		}
			
		return true;
	}
}