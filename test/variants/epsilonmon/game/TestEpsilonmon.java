package variants.epsilonmon.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import hotgammon.common.Game;
import hotgammon.common.GameImpl;
import hotgammon.common.Location;
import hotgammon.variants.factory.EpsilonmonFactory;


public class TestEpsilonmon {
	private Game myGame;
	  
	@Before public void setup() {
		myGame = new GameImpl(new EpsilonmonFactory());
		myGame.newGame();
	    myGame.nextTurn();
	}

	// Testing that result array from diceThrown is initialized properly 
	@Test public void returnedArrayOfIntegersConsistsOfTwoInteger() {
		int[] result = myGame.diceThrown();
	    assertEquals("Kald til aktuelle diceThrown impl. returnerede et array, der best�r af 2 tal", 2, result.length);
	}
	
	//Testing validity of random implementation
	@Test public void returnedArrayConsistsOfIntegersWithinValidSpace() {
		int[] result = myGame.diceThrown();
	    assertTrue("diceThrown returnerer et f�rste terningekast der er lig med eller st�rre end 1", result[0] >= 1);
	    assertTrue("diceThrown returnerer et f�rste terningekast der er lig med eller mindre end 6", result[0] <= 6);
	    assertTrue("diceThrown returnerer et f�rste terningekast der er lig med eller st�rre end 1", result[1] >= 1);
	    assertTrue("diceThrown returnerer et f�rste terningekast der er lig med eller mindre end 6", result[1] <= 6);
	}
	
	//diceValuesLeft performs according to spec with input from new diceThrown - decrement array 
		 @Test public void valueOfArrayIsDecrementedFollowingAMove() {
		 myGame.newGame();
		 myGame.nextTurn();
		 myGame.diceThrown();
		 int[] result = myGame.diceValuesLeft();
		 assertEquals("2MovesLeft: L�ngden af returnerede array fra diceValuesLeft b�r v�re 2", result.length, 2);
		 myGame.move(Location.R1, Location.R2);
		 int[] new_result = myGame.diceValuesLeft(); 	 
		 assertEquals("1MoveLeft: L�ngden af returnerede array fra diceValuesLeft b�r v�re 1", new_result.length, 1);
		      
	}
		 	 	
	
	//Testing diceValuesLeft performs according to specification - largest integer always returned with one move left	
	 @Test public void valueOfDiceIsAlwaysReturnedWithGreatestValueLast() {
		  myGame.newGame();
	      myGame.nextTurn();
	      int[] result = myGame.diceThrown();
	      assertTrue("Array med terningev�rdier returneres med det st�rste tal f�rst", result[1] <= result[0]);
	      
	 }
	
}
