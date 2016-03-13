/**
 * @file TileUnitTests.java
 * @author William Hughes
 * @Date 13/03/2016
 * @see Tile.java
 * @brief Tests Tile class
 * 
 * Tests methods in Computer, and whether the computer AI works correctly
 */

package testing;

import static org.junit.Assert.*;


import org.junit.Test;


import game.*;

public class TileUnitTests {
	/**
	 * Hidden class referenced because Tile is abstract
	 */
	
	@Test
	public void testSurroundingTiles() {
		Tile tester = new Hidden(false, false, false); 
		Board testBoard = m_interactingClass.createBoard();
		
		/**
		 * Testing the number of adjacent tiles a tile should have
		 */

		assertEquals("test if the length of the array list created is always"
				+ " 9 for a tile in the middle of the board", TILES_ARRAY_SIZE,
				(tester.getTileArround(testBoard.getBoard(),
						testBoard.getColumns() / 2,testBoard.getRows() / 2)
						).size());
		
		
		
		assertEquals("test if the length of the array list created is always"
				+ " 4 for a tile in the corner of the board",
				CORNER_ARRAY_SIZE,
				(tester.getTileArround(testBoard.getBoard(),0,0)).size());
	}
	
	IntegrationTests m_interactingClass = new IntegrationTests();
	
	private final int TILES_ARRAY_SIZE = 9;
	private final int CORNER_ARRAY_SIZE = 4;
}
