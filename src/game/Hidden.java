/**
 * @file Hidden.java
 * @author Thomas Williams
 * @date 7 December 2015
 * @see Tile.java
 * @brief A hidden Tile
 *
 * A hidden Tile, inherits from Tile
 */ 

package game;

import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * 
 * @class Hidden
 * @brief A hidden Tile
 *
 * A hidden Tile, inherits from Tile
 * /
public class Hidden extends Tile {
	
	/**
	 * Hidden Constructor
	 * 
	 * @param isMine a boolean is mine or not
	 * @param isHidden a boolean is hidden or not
	 * @param isDefused a boolean is defused or not
	*/
	public Hidden(boolean isMine, boolean isHidden,boolean isDefused) {
		// Always set hidden to false as its the hidden tile.
		super(isMine, true,false);
		URL url = getClass().getResource("/images/hidden.png");
		m_hidden=new ImageIcon(url);
	}
	
	/**
	 * Render the tile
	 *  
	 * @param g a Graphics object used to render
	 * @param x the X coordinate to render at
	 * @param y the Y coordinate to render at
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(m_hidden.getImage(), 
					x * Tile.WIDTH,
					y * Tile.HEIGHT,
					null);
	}

	private ImageIcon m_hidden;
}
