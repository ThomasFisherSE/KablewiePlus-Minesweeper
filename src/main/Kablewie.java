/**
 * @file Kablewie.java
 * @author A4 Thomas Phelps A5 Victoria Charvis
 * @date 4 December 2015
 * @see MainMenu.java
 * @brief Starts the game
 *
 * Starts the game then creates and
 * calls the other components.
 */

package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import game.Board;
import game.GameController;
import game.Player;

/**
 * 
 * @class Kablewie
 * @brief Starts the game
 *
 * Starts the game then creates and
 * calls the other components.
 */
public class Kablewie {

	/**
	 * Method for creating a JFrame for the mainMenu
	 * 
	 * @see MainMenu.java
	 */
	public Kablewie() {
		// Create the frame.
		m_frame = new JFrame("Kablewie");
		m_frame.setIconImage(new ImageIcon("images/Kablewie.png").getImage());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		// Set frame boundaries.
		m_frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		m_frame.setLocation(dim.width / 2 - m_frame.getSize().width / 2,
				dim.height / 2 - m_frame.getSize().height / 2);

		// Set window to close when exited.
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Show the frame.
		m_frame.setVisible(true);
		new MainMenu(m_frame, this);
	}
	
	/**
	 * Initialisation method.
	 * 
	 * @param args a String Array of arguments passed from the command line.
	 */
	public static void main(String[] args) {
		// Create a Kablewie instance to escape static scope.
		new Kablewie();
	}

	/**
	 * Simple method for passing board and player information to the game
	 * controller
	 * 
	 * @param board
	 * @param player
	 * 
	 * @see GameController.java
	 * @return if the game started successfully
	 */
	public boolean startGame(Board board, Player player, MainMenu menu) {
		try {
			m_frame.getContentPane().removeAll();
			new GameController(board, player, m_frame, menu);
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}
	
	/**
	 * 
	 * @param board the loaded board
	 * @param player the loaded player
	 * @param time the loaded time
	 * @return if the loaded game started successfully
	 */
	public boolean startLoadedGame(Board board, Player player, String time){
		try {
			m_frame.getContentPane().removeAll();
			new GameController(board, player, time, m_frame);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
	
	private JFrame m_frame;
	
	private final int FRAME_WIDTH = 640;
	private final int FRAME_HEIGHT = 480;
}
