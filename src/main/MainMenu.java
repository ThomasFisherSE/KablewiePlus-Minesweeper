/**
 * @file MainMenu.java
 * @author A3 Eromosele Gideon A5 Thomas Fisher
 * @date 7 December 2015
 * @see Kablewie.java
 * 
 * @brief creates main menu
 *
 * Handles creation of the main menu
 * and user input on the menu.
 */

package main;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import game.Board;
import game.Human;
import game.Player;

/* 
 * Suppress serial ID warning as ID would not
 * match coding conventions.
 * @class MainMenu
 * @brief creates main menu
 *
 * Handles creation of the main menu
 * and user input on the menu.
 */
@SuppressWarnings("serial")
public class MainMenu extends JPanel implements MouseListener, KeyListener {

	/**
	 * Constructor that sets variable values
	 * and starts display of the menu.
	 * 
	 * @param frame a JFrame that the menu can be attached to.
	 * @param kablewie the instance of Kablewie which started this Menu.
	 */
	public MainMenu(JFrame frame, Kablewie kablewie) {
		// Set Class variables
		this.m_frame = frame;
		this.m_kablewie = kablewie;
		
		// Display the menu
		display();
		
		// Repaint the frame so it displays the menu
		frame.validate();
		frame.repaint();
	}

	/**
	 * Create the board size input.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 * @return boolean showing if it was executed correctly
	 */
	public boolean createBoardSize(JPanel gamePanel) {
		try {
			JLabel boardSize = new JLabel("Board Size:");
			boardSize.setBounds(LBL_COLUMN_X, BOARDSIZE_ROW_Y,
					LBL_WIDTH, LBL_HEIGHT);
			gamePanel.add(boardSize);

			m_boardSizeText = new JTextField();
			m_boardSizeText.addKeyListener(this);
			m_boardSizeText.setText("10");
			m_boardSizeText.setBorder(null);
			m_boardSizeText.setBounds(TF_COLUMN_X, BOARDSIZE_ROW_Y,
					TF_WIDTH, TF_HEIGHT);
			m_boardSizeText.setForeground(Color.RED);
			gamePanel.add(m_boardSizeText);
			m_boardSizeText.setColumns(COLUMNS);
			
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
		
	}
	
	/**
	 * Add everything that needs to be asked
	 *  before starting the game in gamePanel
	 * 
	 * @param gamePanel the panel to be added to
	 */
	public void createPanel(JPanel gamePanel) {

		gamePanel.setBackground(Color.GRAY);
		gamePanel.setBorder(
				new TitledBorder(null, "Kablewie Status", TitledBorder.LEADING,
						TitledBorder.TOP, null, null));
		gamePanel.setBounds(GAME_PANEL_X, GAME_PANEL_Y, GAME_PANEL_WIDTH,
				GAME_PANEL_HEIGHT);
		m_frame.getContentPane().add(gamePanel);
		gamePanel.setLayout(null);
		createUserName(gamePanel);
		createBoardSize(gamePanel);
		createTotalMines(gamePanel);
		createStartGameBtn(gamePanel);
	}
	
	/**
	 * Create the start game button.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 */
	public void createStartGameBtn(JPanel gamePanel) {
		JButton startGame = new JButton("Start Game");
		startGame.setIcon(null);
		startGame.setFont(new Font("Copperplate Gothic Bold",
				Font.PLAIN, FONT_SIZE));
		startGame.setForeground(Color.BLACK);
		startGame.setBackground(Color.DARK_GRAY);
		startGame.setBounds(START_BUTTON_X, START_BUTTON_Y,
				BUTTON_WIDTH, BUTTON_HEIGHT);
		gamePanel.add(startGame);

		startGame.addMouseListener(this);		
	}
	
	/**
	 * Create the mine input.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 */
	public void createTotalMines(JPanel gamePanel) {
		JLabel totalMines = new JLabel("Total Mines:");
		totalMines.setBounds(LBL_COLUMN_X, TOTAL_MINES_ROW_Y,
				LBL_WIDTH, LBL_HEIGHT);
		gamePanel.add(totalMines);

		m_totalMinesText = new JTextField();
		m_totalMinesText.addKeyListener(this);
		m_totalMinesText.setText("10");
		m_totalMinesText.setBorder(null);
		m_totalMinesText.setBounds(TF_COLUMN_X, TOTAL_MINES_ROW_Y,
				TF_WIDTH, TF_HEIGHT);
		m_totalMinesText.setForeground(Color.RED);
		gamePanel.add(m_totalMinesText);
		m_totalMinesText.setColumns(COLUMNS);
	}
	
	/**
	 * Create the username input.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 * @return boolean showing if it was executed correctly
	 */
	public boolean  createUserName(JPanel gamePanel) {
		try {
			JLabel userName = new JLabel("User Name:");
			userName.setBounds(LBL_COLUMN_X, USERNAME_ROW_Y,
					LBL_WIDTH, LBL_HEIGHT);
			gamePanel.add(userName);

			m_userNameText = new JTextField();
			m_userNameText.addKeyListener(this);
			m_userNameText.setBorder(null);
			m_userNameText.setBounds(TF_COLUMN_X, USERNAME_ROW_Y,
					TF_WIDTH, TF_HEIGHT);
			m_userNameText.setForeground(Color.RED);
			gamePanel.add(m_userNameText);
			m_userNameText.setColumns(COLUMNS);
			
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
		
	}

	/**
	 * Create a panel and add this to the main frame.
	 */
	public void display() {
		// Frame options for menu.
		m_frame.setResizable(false);
		m_frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		m_frame.setTitle("Kablewie");
		m_frame.setForeground(Color.RED);
		m_frame.setBackground(Color.RED);
		m_frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		m_frame.getContentPane().setLayout(null);
		
		// Create and add the panel.
		JPanel gamePanel = new JPanel();
		m_frame.addKeyListener(this);
		createPanel(gamePanel);
	}

	/**
	 * Called on KeyRelease, start the game if
	 * its the enter key.
	 * @param e the KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			startGame();
		}
	}
	
	/**
	 * Unused but included due to implements.
	 * @param e Unused KeyEvent
	 */
	public void keyPressed(KeyEvent e) {

	}
	
	/**
	 * Unused but included due to implements.
	 * @param e Unused KeyEvent
	 */
	public void keyTyped(KeyEvent e) {

	}
	
	/**
	 * Called if the user clicks on the start game button.
	 * @param arg0 the MouseEvent
	 */
	public void mouseClicked(MouseEvent arg0) {
		startGame();
	}
	
	/**
	 * Unused but included due to implements.
	 * @param arg0 Unused MouseEvent
	 */
	public void mouseEntered(MouseEvent arg0) {

	}
	
	/**
	 * Unused but included due to implements.
	 * @param arg0 Unused MouseEvent
	 */
	public void mouseExited(MouseEvent arg0) {

	}
	
	/**
	 * Unused but included due to implements.
	 * @param arg0 Unused MouseEvent
	 */
	public void mousePressed(MouseEvent arg0) {

	}
	
	/**
	 * Unused but included due to implements.
	 * @param arg0 Unused MouseEvent
	 */
	public void mouseReleased(MouseEvent arg0) {

	}
	
	/**
	 * Starts the game
	 * 
	 * @return if the game was started successfully
	 */
	public boolean startGame() {
		try {
			String username = m_userNameText.getText();
			int boardSize;
			int numMines;

			boardSize = Integer.parseInt(m_boardSizeText.getText());
			numMines = Integer.parseInt(m_totalMinesText.getText());
			
			if (!(boardSize > MIN_BOARD_SIZE && boardSize <= MAX_BOARD_SIZE)
					|| !(numMines < boardSize * boardSize &&
							numMines <= MAX_MINES && numMines > MIN_MINES)) {
				m_totalMinesText.setText(m_boardSizeText.getText());
				JOptionPane.showMessageDialog(null, "Please enter valid values"
						+ " for Board Size i.e Max Board size 30 x 30");
				return false;
			}
			if (username.length() <=0 || username.length() >12) {
				JOptionPane.showMessageDialog(null, "Please enter Username i.e"
						+ " Letters or Numbers not more than 12 chracters");
				return false;
			}
			Board board = new Board(boardSize, boardSize, numMines);
			Player player = new Human(username);
			m_frame.setSize((boardSize * TILE_SIZE) + SPACING,
					boardSize * TILE_SIZE + INFO_HEIGHT);
			m_kablewie.startGame(board, player, this);
			return true;
		} catch (Exception e) {
		//	System.out.println(e);
			JOptionPane.showMessageDialog(null, "Please enter values for"
					+ " Board Size and Total Mines");
			return false;
		}
		
	}
	
	private final int GAME_PANEL_X = 95;
	private final int GAME_PANEL_Y = 27;
	private final int GAME_PANEL_WIDTH = 274;
	private final int GAME_PANEL_HEIGHT = 210;
	private final int LBL_COLUMN_X = 6;
	private final int USERNAME_ROW_Y = 26;
	
	private final int LBL_WIDTH = 82;
	private final int LBL_HEIGHT = 26;
	
	private final int TF_HEIGHT = 26;
	private final int TF_WIDTH = 110;
	
	private final int TF_COLUMN_X = 98;
	
	private final int TOTAL_MINES_ROW_Y = 116;
	private final int COLUMNS = 10;
	
	private final int TILE_SIZE = 30;
	private final int INFO_HEIGHT = 105;
	private final int BOARDSIZE_ROW_Y = 69;
	private final int START_BUTTON_X = 110;
	private final int START_BUTTON_Y = 160;
	private final int BUTTON_WIDTH = 100;
	private final int BUTTON_HEIGHT = 32;
	private final int FONT_SIZE = 10;
	private final int FRAME_WIDTH = 440;
	private final int FRAME_HEIGHT = 320;
	private final int MIN_BOARD_SIZE = 0;
	private final int MAX_BOARD_SIZE = 30;
	private final int MIN_MINES = 0;
	private final int MAX_MINES = MAX_BOARD_SIZE * MAX_BOARD_SIZE;
	private final int SPACING = 8;
	
	private JTextField m_userNameText;
	private JTextField m_boardSizeText;
	private JTextField m_totalMinesText;
	private JFrame m_frame;
	private Kablewie m_kablewie;
	
}
