/**
 * @file Revealed.java
 * @author A5 Thomas Fisher, A4 Anshul Kumar
 * @date 5 December 2015
 * @see Tile.java
 * @brief A Revealed tile
 *
 * A Revealed Tile, inherits from Tile
 */ 

package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Revealed extends Tile {
	
	/**
	 * Constructor
	 * 
	 * @param isMine a boolean is mine or not
	 * @param isHidden a boolean is hidden or not
	 * @param isDefused a boolean is defused or not
	 */
	public Revealed(boolean isMine, boolean isHidden, boolean isDefused) {
		// Always set hidden to false as its the revealed tile.
		super(isMine, false, isDefused);
		m_revealedImage = new ImageIcon("images/revealed.png");
	}
	
	/**
	 * Draw the number of mines near by the tile which is clicked
	 * 
	 * @param g a Graphics object used to render
	 * @param x the x position of the tile
	 * @param y the y position of the tile
	 */
	public void render(Graphics g, int x, int y) {

		g.drawImage(m_revealedImage.getImage(),
					x * Tile.WIDTH,
					y * Tile.HEIGHT,
					null);
		
		if (getNearbyMines() > 0) {
			/*
			 * using Spacing so that the number is drawn in the center
			 */
			
			g.setFont(new Font("Time new roman", Font.BOLD, FONT_SIZE));
			g.drawString(Integer.toString(getNearbyMines()),
											x * Tile.WIDTH + X_SPACING,
											y * Tile.HEIGHT + Y_SPACING);
		}
	}

	/**
	 * A recursive to reveal empty tiles
	 * 
	 * @param board a Board object that contains all the tiles
	 * @param i the row to get around
	 * @param j the column to get around
	 * @param gc the GameController for the current game
	 */
	public void revealPosition(ArrayList<ArrayList<Tile>> board,
								int i, int j, GameController gc) {
		if (	i < 0 
				|| j < 0 
				|| i >= board.size() 
				|| j >= board.get(0).size() 
				|| !(board.get(i).get(j).isHidden())) {
			// Escape if true
			return;
		}
		
		ArrayList<Tile> tileArround = getTileArround(board, i, j);
		
		if (calculateNearbyMines(board.get(i).get(j), tileArround) == 0) {
			
			board.get(i).remove(j);
			board.get(i).add(j, new Revealed(false, false, false));
			Revealed r = (Revealed) board.get(i).get(j);
			
			r.calculateNearbyMines(r, getTileArround(board, i, j));
			
			revealPosition(board, i - 1, j - 1, gc);
			revealPosition(board, i - 1, j, gc);
			revealPosition(board, i - 1, j + 1, gc);
			revealPosition(board, i, j - 1, gc);
			revealPosition(board, i, j + 1, gc);
			revealPosition(board, i + 1, j - 1, gc);
			revealPosition(board, i + 1, j, gc);
			revealPosition(board, i + 1, j + 1, gc);
			
		} else {
			
			board.get(i).remove(j);
			board.get(i).add(j, new Revealed(false, false, false));
			Revealed r = (Revealed) board.get(i).get(j);
			r.calculateNearbyMines(r, getTileArround(board, i, j));
			
		}
		
		gc.repaintAll();
	}
	
	
	/**
	 * An animated reveal of tiles
	 * 
	 * @param board a Board object that contains all the tiles
	 * @param i the row to get around
	 * @param j the column to get around
	 * @param gc the GameController for the current game
	 */
	public void animatedReveal(ArrayList<ArrayList<Tile>> board,
			int i, int j, GameController gc) {
		int delay = REVEAL_RATE;
    	Timer timer = new Timer( delay, new ActionListener(){
    		public void actionPerformed( ActionEvent e ){
    			if (	i < 0 
    					|| j < 0 
    					|| i >= board.size() 
    					|| j >= board.get(0).size() 
    					|| !(board.get(i).get(j).isHidden())) {
    				// Escape if true
    				return;
    			}
    			
    			ArrayList<Tile> tileArround = getTileArround(board, i, j);
    			
    			if (calculateNearbyMines(board.get(i).get(j), tileArround) == 0) {
    				
    				board.get(i).remove(j);
    				board.get(i).add(j, new Revealed(false, false, false));
    				Revealed r = (Revealed) board.get(i).get(j);
    				
    				r.calculateNearbyMines(r, getTileArround(board, i, j));
    				
    				animatedReveal(board, i - 1, j - 1, gc);
    				animatedReveal(board, i - 1, j, gc);
    				animatedReveal(board, i - 1, j + 1, gc);
    				animatedReveal(board, i, j - 1, gc);
    				animatedReveal(board, i, j + 1, gc);
    				animatedReveal(board, i + 1, j - 1, gc);
    				animatedReveal(board, i + 1, j, gc);
    				animatedReveal(board, i + 1, j + 1, gc);
    				
    			} else {
    				
    				board.get(i).remove(j);
    				board.get(i).add(j, new Revealed(false, false, false));
    				Revealed r = (Revealed) board.get(i).get(j);
    				r.calculateNearbyMines(r, getTileArround(board, i, j));
    				
    			}
    			
    			gc.repaintAll();
    		}
    	} );
    	timer.setRepeats( false );
    	timer.start();
    	
    	gc.repaintAll();
	}

	private final ImageIcon m_revealedImage;
	private final int REVEAL_RATE = 50;
	private final int X_SPACING = 10;
	private final int Y_SPACING = 19;
	private final int FONT_SIZE = 15;
}
