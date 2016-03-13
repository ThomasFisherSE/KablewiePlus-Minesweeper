/**
 * @file Defused.java
 * @author Josh Townsend
 * @date 7 December 2015
 * @see Tile.java
 * @brief A Defused Tile
 *
 * A Defused Tile, inherits from Tile
 */

package game;

import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * 
 * @class Defused
 * @brief A Defused Tile
 *
 * A Defused Tile, inherits from Tile
 */
public class Defused extends Tile {

	/**
	 * Constructor
	 * 
	 * @param isMine a boolean is mine or not
	 * @param isHidden a boolean is hidden or not
	 * @param isDefused a boolean is defused or not
	 */
	public Defused(boolean isMine, boolean isHidden, boolean isDefused) {
		super(isMine, isHidden, isDefused);
		URL url = getClass().getResource("/images/defused.png");
		m_defused = new ImageIcon(url);
	}

	/**
	 * Render the tile
	 *  
	 * @param g a Graphics object used to render
	 * @param x the X coordinate to render at
	 * @param y the Y coordinate to render at
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(m_defused.getImage(),
					x * Tile.WIDTH,
					y * Tile.HEIGHT,
					null);
	}
	
	private ImageIcon m_defused;
	
}
