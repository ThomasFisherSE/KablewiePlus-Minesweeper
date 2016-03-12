/**
 * @file Human.java
 * @author Zongbo Xu
 * @date 5 December 2015
 * @see Player.java
 * @brief A class for Human players.
 *
 * A class for Human players, inherits from Player
 */ 

package game;

public class Human extends Player  {
	
	/**
	 * Gets if it's the human players turn
	 * 
	 * @return boolean if it's the human players turn
	 */
	public boolean getPlayersTurn() {
		return m_playersTurn;
	}
	
	/**
	 * Constructor
	 * 
	 * @param name the players name
	 */
	public Human(String name) {
		super(name);
	}
	
	/**
	 * Sets if it's the human player turn
	 */
	public void takeTurn() {
		m_playersTurn = true;
	}

	private boolean m_playersTurn = true;

}
