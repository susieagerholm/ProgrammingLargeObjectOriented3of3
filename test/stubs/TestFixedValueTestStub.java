package stubs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import hotgammon.common.Game;
import hotgammon.common.GameImpl;


public class TestFixedValueTestStub {
	
	private Game myGame;
  
	//diceValuesLeft performs according to spec with input from new diceThrown - decrement array 
	@Test public void fixedValueStrategySetsDefaultResultArrayAsExpected() {
		TestStubFixedValueFactory myTestStubFactory = new TestStubFixedValueFactory(4, 3);
		assertNotNull("test stub factory is NULL", myTestStubFactory);
		myGame = new GameImpl(myTestStubFactory);
		assertNotNull("My game is NULL", myGame);

		int[] result = myGame.diceThrown();
		assertEquals("Den f�rste terningev�rdi fra test stub default b�r v�re 4", 4, result[0]);
		assertEquals("Den anden terningev�rdi fra test stub default b�r v�re 3", 3, result[1]);
		assertEquals("Test stub returnerer array med pr�cis to v�rdier", 2, result.length);
	}	 	 
		 
  //diceValuesLeft performs according to spec with input from new diceThrown - decrement array 
	@Test public void fixedValueStrategySetsResultArrayAsExpected() {
      myGame = new GameImpl(new TestStubFixedValueFactory(1, 5));
      myGame.newGame();
	    myGame.nextTurn();

      int[] result = myGame.diceThrown();
      assertEquals("Den f�rste terningev�rdi fra test stub efter setter b�r v�re 5", 5, result[0]);
      assertEquals("Den anden terningev�rdi fra test stub efter setter v�re 1", 1, result[1]);
      assertEquals("Test stub returnerer array med pr�cis to v�rdier", 2, result.length);
	}	 	 

}
