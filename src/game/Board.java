/**
 * @file Board.java
 * @author A4 Ethan Davies A5 Victoria Charvis
 * @date 7 December 2015
 * @see Tile.java
 * 
 * Contain the Information of the current Board
 * and various helper methods for manipulating
 * the board.
 */ 

package game;

import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Board {
	
	/**
	 * Constructor
	 * 
	 * @param bRows an int for the number of rows
	 * @param bColumns an int for the number of columns
	 * @param numMines an int for number of mines
	 */
	public Board(int bRows, int bColumns, int numMines) {
		// Set Class variables
		this.m_mineCount = numMines;
		this.m_rows = bRows;
		this.m_columns = bColumns;
		
		// Create a revealed tile for the sake of method calls.
		m_reveal = new Revealed(false, false, false);
		
		// Setup the board.
		setBoardDimensions();
		placeMines();
	}

	/**
	 * Constructor
	 * 
	 * @param boardSize the size of the board
	 * @param numMines an int for number of mines
	 */
	public Board(int boardSize, int numMines) {
		// Set Class variables
		this.m_mineCount = numMines;
		this.m_rows = boardSize;
		this.m_columns = boardSize;
		
		// Create a revealed tile for the sake of method calls.
		m_reveal = new Revealed(false, false, false);
		
		// Setup the board.
		setBoardDimensions();
	}
	
	/**
	 * Returns the board
	 * 
	 * @return the board which has all the tiles
	 */
	public ArrayList<ArrayList<Tile>> getBoard() {
		return m_board;
	}
	
	/**
	 * Gets the column count
	 * 
	 * @return the column count
 	 */
 	public int getColumns() {
	 	return m_columns;
	 }
 	
 	/**
	 * @return an int with the number of defused tiles
	 */
	public int getDefusedTile() {
		int defusedTile = 0;
		
		for (int i = 0; i < m_board.size(); ++i) {
			for (int j = 0; j < m_board.size(); ++j) {
				try {
					if (m_board.get(i).get(j).isDefused() && m_board.get(i).get(j).isMine()) {
						++defusedTile;
					}
				} catch (IndexOutOfBoundsException e) {
					System.err.println("Board.java :: getDefusedTile() --> "
							+ "Index Out of Bounds \n"
							+ "i:" + i + " j:" + j + " size: " + m_board.size());
				}
			}
		}
		
		return defusedTile;
	}
 	
	/**
	 * Returns if game is lost
	 * 
	 * @return the value of m_gameLost which is true if the game is lost
	 */
	public boolean getGameLost() {
		return m_gameLost;
	}

	/**
	 * Returns if the game is won
	 * 
	 * @return the value of m_gameWon which is true if the game is won
	 */
	public boolean getGameWon() {
		return m_gameWon;
	}
	
	/**
	 * @return an int with the number of hidden tiles
	 */
	public int getHiddenTile() {
		int hiddenTile = 0;
		
		for (int i = 0; i < m_board.size(); ++i) {
			for (int j = 0; j < m_board.size(); ++j) {
				try {
					if (m_board.get(i).get(j).isHidden()) {
						++hiddenTile;
					}
				} catch (IndexOutOfBoundsException e) {
					System.err.println("Board.java :: getHiddenTile() --> "
							+ "Index Out of Bounds \n"
							+ "i:" + i + " j:" + j + " size: " + m_board.size());
				}
			}
		}
		
		return hiddenTile;
	}

	/**
	 * Gets the mine count
	 * 
	 * @return the mine count
	 */
	public int getMineCount() {
		return m_mineCount;
	}
	
	/**
	 * @return an int with the number of revealed tiles
	 */
	public int getRevealedTile() {
		int revealedTile = 0;
		
		for (int i = 0; i < m_board.size(); ++i) {
			for (int j = 0; j < m_board.size(); ++j) {
				try {
					if (!(m_board.get(i).get(j).isHidden())) {
						++revealedTile;
					}
				} catch (IndexOutOfBoundsException e) {
					System.err.println("Board.java :: getHiddenTile() --> "
							+ "Index Out of Bounds \n"
							+ "i:" + i + " j:" + j + " size: " + m_board.size());
				}
			}
		}
		
		return revealedTile;
	}
	
	/**
	  * Gets the row count
	  * 
	  * @return the row count
	  */
	 public int getRows() {
	 	return m_rows;
	 }

	/**
	 * Gets the time passed
	 * 
	 * @return value of the time passed
	 */
	public String getTimePassed() {
		return m_timePassed;
	}
	
	/**
	 * Sets the dimensions of the board
	 */
	private void setBoardDimensions() {
		// Create board
		m_board = new ArrayList<ArrayList<Tile>>();
		
		// Sets the dimensions HxW of the board that is to be created
		for (int i = 0; i < m_rows; i++) {
			m_board.add(new ArrayList<Tile>());

			for (int q = 0; q < m_columns; q++) {
				m_board.get(i).add(new Hidden(false, true, false));
			}
		}
	}

	/**
	 * Sets the states of the loaded tiles
	 * 
	 * @param column the column the tile is in
	 * @param row the row the tile is in
	 * @param state the state the tile is in(Diffused,Hidden,Mine)
	 */
	public void setState(int column, int row, String state, 
			boolean isProperty) {
		if (state.equalsIgnoreCase("D")) {
			if (isProperty){
				m_board.get(row).get(column).setDefused(true);
			} else {
				m_board.get(row).get(column).setDefused(false);
			}
		} else if (state.equalsIgnoreCase("M")) {
			if (isProperty){
				m_board.get(row).get(column).setMine(true);
			} else {
				m_board.get(row).get(column).setMine(false);
			}
		} else if (state.equalsIgnoreCase("H")) {
			if (isProperty){
				m_board.get(row).get(column).setHidden(true);
			} else {
				m_board.get(row).get(column).setHidden(false);
			}
		}
	}

	/**
	 * This method is used to defuse tiles on the board
	 * after a users turn, storing whether the tile was
	 * a mine or not.
	 * 
	 * @param x an int which is the X position of the click
	 * @param y an int which is the Y position of the click
	 */
	public void defusedTile(int x, int y) {		
		if (inLimit(y, x)) {
			
			boolean isMine = m_board.get(y).get(x).isMine();
			boolean isDefused = m_board.get(y).get(x).isDefused();
			boolean isHidden = m_board.get(y).get(x).isHidden();
			
			if (isHidden) {
				if (!(isDefused)) {
					m_board.get(y).remove(x);
					m_board.get(y).add(x, new Defused(isMine, true, true));
					haveWon();
				} else {
					m_board.get(y).remove(x);
					m_board.get(y).add(x, new Hidden(isMine, true, false));
				} 
			}
		}
	}
	
	/**
	 * checks if the game have been won or not
	 */
	public void haveWon() {
		if (getRevealedTile() + getDefusedTile() == m_board.size() *
				m_board.size()) {
			m_gameLost = false;
			m_gameWon = true;
		}
	}
	
	/**
	 * Hides all bomb tiles on the board
	 */
	 public void hideBombTile() {
		 for (int y=0; y < m_columns; y++ ){
			 for (int x=0; x < m_rows; x++){
				 if (m_board.get(y).get(x).isMine()) {                    
					 m_board.get(y).remove(x);
	                 m_board.get(y).add(x, new Hidden(true, true, false));
	              }
	         }		
	     }
	 }
	 
	/**
	 * it check if x,y are in the range of the board or not
	 * 
	 * @param x an int for the row
	 * @param y an int for the column
	 * 
	 * @return true if x, y are in range
	 */
	public boolean inLimit(int x, int y) {
		return !(x >= m_board.size()
				|| y >= m_board.get(0).size()
				|| x < 0
				|| y < 0);
	}
	
	/**
	 * Loads the graphics
	 */
	public void loadGraphics(GameController gc) {
		//for every tile in the game set the graphics
		for (int j=0;j<m_board.size();j++) {
			for (int i=0;i<m_board.size();i++) {
				
				boolean isMine = m_board.get(j).get(i).isMine();
				boolean isDefused = m_board.get(j).get(i).isDefused();
				boolean isHidden = m_board.get(j).get(i).isHidden();
				
				//Setting correct images
				if (m_board.get(j).get(i).isDefused()) {
					m_board.get(j).remove(i);
					m_board.get(j).add(i, new Defused(isMine, isHidden, 
							isDefused));
				} else if (m_board.get(j).get(i).isHidden()==false) {
					if (!(isDefused)) {
						
						m_board.get(j).remove(i);
						m_board.get(j).add(i, new Revealed(isMine, isHidden, 
								isDefused));
						
						m_board.get(j).get(i).setHidden(true);
						m_reveal.revealPosition(m_board, j, i, gc);
						haveWon();
					}
				}
			}
		}
	}

	/**
	 * Calls render on each tile on the board.
	 * 
	 * @param g a Graphics object for rendering the board.
	 */
	public void render(Graphics g) {
		// This will be responsible for creating the graphics of the board
		for (int y = 0; y < m_board.size(); y++) {
			for (int x = 0; x < m_board.get(y).size(); x++) {
				m_board.get(y).get(x).render(g, x, y);
			}
		}
	}
	
	/**
	 * Renders the games UI
	 * 
	 * @param g a Graphics object for rendering the UI
	 * @param player a Player object with the info to be displayed
	 * @param timePassed a String with the time that has passed.
	 */
	public void renderInfo(Graphics g, Player player, String timePassed) {
		Font timeNewRoman = new Font("Time new roman", Font.BOLD, FONT_SIZE);
		
		// Positioning Values.
		int x = 1;
		int y = 10;
		
		g.setFont(timeNewRoman);
		g.setColor(Color.RED);
		g.drawString("Name : " + player.getUsername(), x, y);
		
		x = x + 180;
		
		if (timePassed == null) {
			g.drawString("Time: 00:00:00", x, y);
		} else {
			g.drawString("Time: " + timePassed, x, y);
			m_timePassed = timePassed;
		}
		
		x = 1;
		y = 27;
		g.setColor(Color.BLUE);
		g.drawString("Defused Mine : " + getDefusedTile(), x, y);
		
		x = x + 180;
		g.drawString("Mines Present : " + m_mineCount, x, y);
		
		y = 48;
		x = 1;
		g.drawString("Hidden Square : " + getHiddenTile(), x, y);
		
		x = x + 180;
		g.drawString("Revealed Square : " + getRevealedTile(), x, y);
	}
	
	/**
	 * Resets the game so that it can be played again
	 */
	public void reset() {
		m_board.clear();
		setBoardDimensions();
		placeMines();
		m_gameLost = false;
		m_gameWon = false;
	}
	
	/**
	 * This method is used to reveal tiles on the board
	 * after a users turn, and then takes action based
	 * on what tile is revealed.
	 * 
	 * @param x an int which is the X position of the click
	 * @param y an int which is the Y position of the click
	 */
	public void revealTile(int x, int y, GameController gc) {
		
		
		if (inLimit(y, x) && !(m_board.get(y).get(x).isDefused())) {
			
			if (m_board.get(y).get(x).isHidden() && 
					!(m_board.get(y).get(x).isMine())) {
				
				m_reveal.animatedReveal(m_board, y, x, gc);
				haveWon();
				
			} else if (m_board.get(y).get(x).isMine()) {
				this.m_gameWon = false;
				this.m_gameLost = true;
				m_board.get(y).remove(x); // create a mine tile
				m_board.get(y).add(x, new Mine(true, true, false, 
						"images/mineX.jpg"));
				for (int i = 0; i < m_board.size(); ++i) {
					for (int j = 0; j < m_board.get(0).size(); ++j) {
						if (m_board.get(i).get(j).isMine() && !(i == y && j == x)) {
							m_board.get(i).remove(j); // create a mine tile
							m_board.get(i).add(j, new Mine(true, true, false, 
									"images/mine.png"));
						}
					}
				}	
			} else if (!m_board.get(y).get(x).isHidden()) {
				JOptionPane.showMessageDialog(null, "You can't reveal a revealed tile", "Invalid Move", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**
	 * Shows all bomb tiles on the board
	 */
	public void showBombTile() {
		
		for (int y=0; y < m_columns - 1; y++ ){
	            for (int x=0; x < m_rows - 1; x++){
			
	                if (m_board.get(y).get(x).isMine()) {

	                    m_board.get(y).remove(x); // create a mine tile
	                    m_board.get(y).add(x, new Mine(true, true, false, 
	                    		"images/mine.png"));
	                    for (int i = 0; i < m_board.size(); ++i) {
	                        for (int j = 0; j < m_board.get(0).size(); ++j) {
	                            if (m_board.get(i).get(j).isMine() && 
	                            		!(i == y && j == x)) {
	                                m_board.get(i).remove(j);
	                                m_board.get(i).add(j, new Mine(true, true,
	                                		false, "images/mine.png"));
	                            }
	                        }
	                    }	
	                }
	            }		
		}
	 }

	/**
	 * Place the mines onto the board.
	 */
	private void placeMines() {
		// This places the mines in random areas on the board(In the array)
		Random rnd = new Random();
		int mineCount = this.m_mineCount;
		while (mineCount > 0) {
			int row = rnd.nextInt(m_board.size());
			int column = rnd.nextInt(m_board.get(row).size());

			if (m_board.get(row).get(column).isMine()) {

			} else {
				m_board.get(row).get(column).setTileType(true, true);
				mineCount--;
			}
		}
	}
	
	private int m_rows;
	private int m_columns;
	private int m_mineCount;
	private boolean m_gameWon = false;
	private boolean m_gameLost = false;
	private ArrayList<ArrayList<Tile>> m_board;
	private Revealed m_reveal;
	private String m_timePassed;
	
	public static final int DEFAULT_SIZE = 10;
	public static final int DEFAULT_MINES = DEFAULT_SIZE;
	public static final int MAX_SIZE = 30;
	public static final int MAX_MINES = MAX_SIZE - 1;
	public static final int MIN_SIZE = 2;
	public static final int MIN_MINES = 1;
	
	private final int FONT_SIZE = 12;
}
