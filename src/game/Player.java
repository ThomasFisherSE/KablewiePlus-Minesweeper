/**
 * @file Player.java
 * @author Ceri Westcott
 * @date 5 December 2015
 * @see Human.java, Computer.java
 * @brief A class for a generic player
 *
 * A class for a generic player, inherited by Human and Computer players
 */ 

package game;

public class Player {
	
	/**
	 * Constructor
	 * 
	 * @param username a String for the players username
	 */
	public Player(String username) {
		this.m_username = username;
	}
	
	/**
	 * Accessor method for username
	 * @return the username of the player
	 */
	public String getUsername() {
		return m_username;
	}

	private String m_username;
	
}
