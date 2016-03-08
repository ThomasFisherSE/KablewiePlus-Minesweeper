/**
 * @file Tile.java
 * @author Ethan Davies
 * @date 7 December 2015
 *
 * A class for a generic tile
 */

package game;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tile {
	// Static values for WIDTH and HEIGHT
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;

	private boolean m_isMine = false;
	private boolean m_isHidden = false;
	private boolean m_isDefused = false;
	private int m_nearbyMines;
	
	/**
	 * Tile Constructor
	 * @param isMine a boolean is mine or not
	 * @param isHidden a boolean is hidden or not
	 * @param isDefused a boolean is defused or not
	 */
	public Tile(boolean isMine, boolean isHidden, boolean isDefused){
		this.m_isMine = isMine;
		this.m_isHidden = isHidden;
		this.m_isDefused=isDefused;
	}
	
	/**
	 * @return isMine which is true if the tile is a mine 
	 */
	public boolean isMine() {
		return m_isMine;
	}
	
	/**
	 * @return isMine which is true if the tile is a mine 
	 */
	public boolean isHidden() {
		return m_isHidden;
	}
	
	/**
	 * @return isMine which is true if the tile is a mine 
	 */
	public boolean isDefused() {
		return m_isDefused;
	}
	
	/**
	 * @return the mines near by the tile calls it
	 */
	public int getm_NearByMines() {
		return m_nearbyMines;
	}
	
	/**
	 * Change the Tile type
	 * 
	 * @param isMine a boolean for if its a mine
	 * @param isHidden a boolean for if it is hidden
	 */
	public void setTileType(boolean isMine, boolean isHidden) {
		this.m_isMine = isMine;
		this.m_isHidden = isHidden;
	}
	
	/**
	 * Gets the tiles that are around a set position
	 * 
	 * @param board a Board object that contains all the tiles
	 * @param i the row to get around
	 * @param j the column to get around
	 * @return
	 */
	public ArrayList<Tile> getTileArround(ArrayList<ArrayList<Tile>> board,
											int i, int j) {

		int prevrow = i - 1;
		int prevrcol = j - 1;
		int nextrow = i + 1;
		int nextcol = j + 1;
		
		// it run a for loop all around the tile i,j
		ArrayList<Tile> t2 = new ArrayList<Tile>();
		
		for (int k = prevrow; k <= nextrow; ++k) {
			for (int m = prevrcol; m <= nextcol; ++m) {
				
				if (!(	k < 0 ||
						m < 0 || 
						k >= board.size() 
						|| m >= board.get(0).size())) {
					/*
					 * before adding it the condition makes sure that it is not
					 * out of bound of the board
					 */
					t2.add(board.get(k).get(m));
				}
			}
		}
		
		return t2;
	}
	
	/**
	 * Calculates the number of mines around a tile
	 * 
	 * @param tile a Tile thats being checked
	 * @param tileArround an ArrayList of tiles that are around the tile
	 */
	public int calculateNearbyMines(Tile tile, ArrayList<Tile> tileArround) {
		if (tile.isMine()) {
			m_nearbyMines = -1;
		} else {
			int nearbyMine = 0;
			for (int i = 0; i < tileArround.size(); i++) {
				if (tileArround.get(i).isMine()) {
					nearbyMine++;
				}
			}
			m_nearbyMines = nearbyMine;
		}
		return m_nearbyMines;
	}
	
	/**
	 *  render the tile
	 *  
	 * @param g a Graphics object used to render
	 * @param x the X coordinate to render at
	 * @param y the Y coordinate to render at
	 */
	public abstract void render(Graphics g, int x, int y);

}
