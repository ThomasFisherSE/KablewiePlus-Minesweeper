/**
 * @file Revealed.java
 * @author Anshul Kumar
 * @date 5 December 2015
 *
 * A Revealed Tile
 */ 

package game;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Revealed extends Tile {

	private final ImageIcon m_revealedImage;
	
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
	 * draw the number of mines near by the tile which is clicked
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
			int spacingX = 10;
			int spacingY = 19;
			int fontSize = 15;
			g.setFont(new Font("Time new roman", Font.BOLD, fontSize));
			g.drawString(Integer.toString(getNearbyMines()),
											x * Tile.WIDTH + spacingX,
											y * Tile.HEIGHT + spacingY);
		}
	}

	/**
	 * A recursive to reveal empty tiles
	 * 
	 * @param board a Board object that contains all the tiles
	 * @param i the row to get around
	 * @param j the column to get around
	 */
	public void revealPosition(ArrayList<ArrayList<Tile>> board,
								int i, int j) {
		
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
			
			revealPosition(board, i - 1, j - 1);
			revealPosition(board, i - 1, j);
			revealPosition(board, i - 1, j + 1);
			revealPosition(board, i, j - 1);
			revealPosition(board, i, j + 1);
			revealPosition(board, i + 1, j - 1);
			revealPosition(board, i + 1, j);
			revealPosition(board, i + 1, j + 1);
			
		} else {
			
			board.get(i).remove(j);
			board.get(i).add(j, new Revealed(false, false, false));
			Revealed r = (Revealed) board.get(i).get(j);
			r.calculateNearbyMines(r, getTileArround(board, i, j));
			
		}
	}

}
