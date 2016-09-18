package ui;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class MoveCheckerCommandTests {
	private CheckerMoveCommandGameStub game;
	
	@Before
	public void setup(){
		game = new CheckerMoveCommandGameStub();
	}
	
	@Test
	public void TestThatMoveIsCalled(){
		Assert.assertTrue(false);
	}
}
