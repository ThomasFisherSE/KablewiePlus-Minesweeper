package testing;

import static org.junit.Assert.*;
import game.*;
import org.junit.Test;

public class ComputerUnitTests {
	IntegrationTests interactingClass = new IntegrationTests();
	
	@Test
	public void testLowIntelligencePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(), interactingClass.createGameController(), Computer.EASY_PROBABILITY);
		
		assertEquals("Test if the correct intelligence is set by clicking the Low-Intelligence preset",
				Computer.EASY_PROBABILITY, tester.getIntelligence());
	}
	
	@Test
	public void testNormalIntelligencePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(), interactingClass.createGameController(), Computer.NORMAL_PROBABILITY);
		
		assertEquals("Test if the correct intelligence is set by clicking the Low-Intelligence preset",
				Computer.NORMAL_PROBABILITY, tester.getIntelligence());
	}
	
	@Test
	public void testHighIntelligencePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(), interactingClass.createGameController(), Computer.PERFECT_PROBABILITY);
		
		assertEquals("Test if the correct intelligence is set by clicking the Low-Intelligence preset",
				Computer.PERFECT_PROBABILITY, tester.getIntelligence());
	}
	
	@Test
	public void testCantLosePreset() {
		Computer tester = new Computer("AI", interactingClass.createBoard(), interactingClass.createGameController(), Computer.CANNOT_LOSE);
		
		assertEquals("Test if the correct intelligence is set by clicking the Low-Intelligence preset",
				Computer.CANNOT_LOSE, tester.getIntelligence());
	}
	

}
