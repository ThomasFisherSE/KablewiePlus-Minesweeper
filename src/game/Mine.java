/**
 * @file Mine.java
 * @author Josh Townsend
 * @date 7 December 2015
 * @see Tile.java
 * @brief A Mine tile
 *
 * A Mine tile, inherits from Tile
 */

package game;

import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * 
 * @class Mine
 * @brief A Mine tile
 *
 * A Mine tile, inherits from Tile
 * /
public class Mine extends Tile {
	
	/**
	 * Mine Constructor
	 * 
	 * @param isMine a boolean is mine or not
	 * @param isHidden a boolean is hidden or not
	 * @param isDefused a boolean is defused or not
	*/
	public Mine(boolean isMine, 
				boolean isHidden, 
				boolean isDefused,
				String path) {
		
		/**
		 *  Always set hidden to false as Mine is a visible tile.
		 *  Always set mine to true as Mine is a mine.
		 */ 
		super(true, true, false);
		URL url = getClass().getResource(path);
		m_mineImage = new ImageIcon(url);
	}
	
	/**
	 *  render the tile
	 *  
	 * @param g a Graphics object used to render
	 * @param x the X coordinate to render at
	 * @param y the Y coordinate to render at
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(m_mineImage.getImage(), 
					x * Tile.WIDTH,
					y * Tile.HEIGHT,
					null);
	}

	private ImageIcon m_mineImage;

}
