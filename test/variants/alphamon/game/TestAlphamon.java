package variants.alphamon.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import hotgammon.common.Color;
import hotgammon.common.GameImpl;
//import hotgammon.common.GameFactory;
import hotgammon.common.Location;
//import hotgammon.common.LogDecorator;
import hotgammon.variants.factory.AlphamonFactory;

public class TestAlphamon {
  private GameImpl game;
//  private LogDecorator game;
//  private GameFactory myGameFactory;
  
/*  public TestAlphamon() {
	  this(new AlphamonFactory());
  }*/
/*  
  public TestAlphamon(GameFactory myGameFactory) {
	  this.myGameFactory = myGameFactory;
  }*/
  
  @Before public void setup() {
//    game = new GameImpl(myGameFactory);
	game = new GameImpl(new AlphamonFactory());	  
//	game = new LogDecorator(new GameImpl(new AlphamonFactory()));
  }
  
  @Test public void shouldHaveNoPlayerInTurnAfterNewGame() {
    game.newGame();
    assertEquals("Shouldn't have a color", Color.NONE, game.getPlayerInTurn() );
  }

  @Test public void shouldHaveBlackPlayerInTurnAfterNewGame() {
    game.newGame();
    game.nextTurn(); // will throw [1,2] and thus black starts
    assertEquals("Should be black", Color.BLACK, game.getPlayerInTurn() );
  }

  @Test public void initiallyTwoBlackCheckersOnR1() {
      game.newGame();
      assertEquals("Should be two", 2, game.getCount(Location.R1));
      assertEquals("Should be black", Color.BLACK, game.getColor(Location.R1));
  }

  @Test public void initiallyTwoRedCheckersOnB1() {
      game.newGame();
      assertEquals("Should be two", 2, game.getCount(Location.B1));
      assertEquals("Should be red", Color.RED, game.getColor(Location.B1));
  }
  
  @Test public void initiallyFiveBlackCheckersOnB6() {
      game.newGame();
      assertEquals("Should be five", 5, game.getCount(Location.B6));
      assertEquals("Should be black", Color.BLACK, game.getColor(Location.B6));
  }
  
  @Test public void initiallyFiveRedCheckersOnR6() {
      game.newGame();
      assertEquals("Should be five", 5, game.getCount(Location.R6));
      assertEquals("Should be red", Color.RED, game.getColor(Location.R6));
  }
  
  @Test public void initiallyFiveRedCheckersOnB12() {
        game.newGame();
        assertEquals("Should be five", 5, game.getCount(Location.B12));
        assertEquals("Should be red", Color.RED, game.getColor(Location.B12));
  }

  @Test public void initiallyFiveRedCheckersOnR12() {
        game.newGame();
        assertEquals("Should be five", 5, game.getCount(Location.R12));
        assertEquals("Should be black", Color.BLACK, game.getColor(Location.R12));
  }
  
  @Test public void initially3RedCheckersOnR8() {
        game.newGame();
        assertEquals("Should be three", 3, game.getCount(Location.R8));
        assertEquals("Should be red", Color.RED, game.getColor(Location.R8));
  }
  
  @Test public void initially3BlackCheckersOnB8() {
          game.newGame();
          assertEquals("Should be three", 3, game.getCount(Location.B8));
          assertEquals("Should be black", Color.BLACK, game.getColor(Location.B8));
  }
  
  @Test public void firstRollGives1and2() {
      game.newGame();
      game.nextTurn();
      int[] diceThrown = game.diceThrown();
      assertEquals("Should be one", 1, diceThrown[0]);
      assertEquals("Should be two", 2, diceThrown[1]);
  }
  
  @Test public void secondTurnIsRedPlayerAndRollIs3and4() {
      game.newGame();
      game.nextTurn();
      game.nextTurn();
      assertEquals( Color.RED, game.getPlayerInTurn() );
      int[] diceThrown = game.diceThrown();
      assertEquals("Should be three", 3, diceThrown[0]);
      assertEquals("Should be four", 4, diceThrown[1]);
  }
  
  @Test public void moveFromR1toR2isValid() {
      game.newGame();
      game.nextTurn();
      assertTrue(game.move(Location.R1, Location.R2));
      assertEquals("Should be one", 1, game.getCount(Location.R1));
      assertEquals("Should be black", Color.BLACK, game.getColor(Location.R1));
      assertEquals("Should be one", 1, game.getCount(Location.R2));
      assertEquals("Shoule be black", Color.BLACK, game.getColor(Location.R2));
      assertEquals("Should be one", 1, game.getNumberOfMovesLeft());
  }
  
  @Test public void moveFromR1ToB1isInvalid() {
      game.newGame();
      game.nextTurn();
      assertFalse("Should not be possible", game.move(Location.R1, Location.B1));
  }
  
  @Test public void twoBlackMovesMeansNoMovesLeft() {
      game.newGame();
      game.nextTurn();
      assertTrue("Should be possible", game.move(Location.R1, Location.R2));
      assertTrue("Should be possible", game.move(Location.R1, Location.R2));
      assertEquals("Should be zero", 0, game.getNumberOfMovesLeft());
  }
  
  @Test public void gameEndsAfterSixRollsWinnerIsRed() {
      game.newGame();
      game.nextTurn();
      assertEquals("Should have no winner", Color.NONE, game.winner());
      game.nextTurn();
      assertEquals("Should have no winner", Color.NONE, game.winner());
      game.nextTurn();
      assertEquals("Should have no winner", Color.NONE, game.winner());
      game.nextTurn();
      assertEquals("Should have no winner", Color.NONE, game.winner());
      game.nextTurn();
      assertEquals("Should have no winner", Color.NONE, game.winner());
      game.nextTurn();
      assertEquals("Winner should be red", Color.RED, game.winner());
  }
  
  @Test public void movingRedPieceInFirstRoundIsIllegal() {
      game.newGame();
      game.nextTurn();
      assertFalse("Should not be possible", game.move(Location.B1, Location.B10));
  }
  
  @Test public void movingBlackPieceToEmptyLocationTurnsItBlack() {
      game.newGame();
      game.nextTurn();
      assertEquals("Should have no color", Color.NONE, game.getColor(Location.B7));
      assertTrue("Should be possible", game.move(Location.R1, Location.B7));
      assertEquals("Should now be black", Color.BLACK, game.getColor(Location.B7));
      assertEquals("Should now be one", 1, game.getCount(Location.B7));
  }

  @Test public void cannotMoveFromEmptyLocation() {
      game.newGame();
      game.nextTurn();
      assertFalse("Should not be possible", game.move(Location.B7, Location.B10));
  }
  
  @Test public void movingAllPiecesAwayFromLocationResultsInNoColor() {
      game.newGame();
      game.nextTurn();
      assertTrue("Should be possible", game.move(Location.R1, Location.R2));
      assertTrue("Should be possible", game.move(Location.R1, Location.R2));
      assertEquals("Should be zero", 0, game.getCount(Location.R1));
      assertEquals("Should be none", Color.NONE, game.getColor(Location.R1));
  }
  
  @Test public void barsAndBearoffsCannotBeUsed() {
      game.newGame();
      game.nextTurn();
      assertFalse("Should not be possible", game.move(Location.R1, Location.R_BAR));
      assertFalse("Should not be possible", game.move(Location.R1, Location.B_BAR));
      assertFalse("Should not be possible", game.move(Location.R1, Location.B_BEAR_OFF));
      assertFalse("Should not be possible", game.move(Location.R1, Location.R_BEAR_OFF));
  }
  
  @Test public void aThirdMoveIsIllegal() {
      game.newGame();
      game.nextTurn();
      assertTrue("Should be possible", game.move(Location.R1, Location.R2));
      assertTrue("Should be possible", game.move(Location.R1, Location.R2));
      assertFalse("Should not be possible",game.move(Location.R12, Location.R2));
  }
  
  @Test public void correctSequenceOfDiceRolls() {
      game.newGame();
      game.nextTurn();
      assertEquals("Should be one", 1, game.diceThrown()[0]);
      assertEquals("Should be two", 2, game.diceThrown()[1]);
      game.nextTurn();
      assertEquals("Should be three", 3, game.diceThrown()[0]);
      assertEquals("Should be four", 4, game.diceThrown()[1]);
      game.nextTurn();
      assertEquals("Should be five", 5, game.diceThrown()[0]);
      assertEquals("Should be six", 6, game.diceThrown()[1]);
      game.nextTurn();
      assertEquals("Should be one", 1, game.diceThrown()[0]);
      assertEquals("Should be two", 2, game.diceThrown()[1]);
      game.nextTurn();
      assertEquals("Should be three", 3, game.diceThrown()[0]);
      assertEquals("Should be four", 4, game.diceThrown()[1]);
      game.nextTurn();
      assertEquals("Should be five", 5, game.diceThrown()[0]);
      assertEquals("Should be six", 6, game.diceThrown()[1]);
  }
  
  @Test public void afterAMoveLengthOfDiceValuesLeftAre1() {
      game.newGame();
      game.nextTurn();
      game.diceThrown();
      assertTrue("Should be possible", game.move(Location.R1, Location.R2));
      assertEquals("Length should be one", game.diceValuesLeft().length, 1);
  }

  @Test public void afterTwoMovesLengthOfDiceValuesLeftAre1() {
        game.newGame();
        game.nextTurn();
        assertTrue("Should be possible", game.move(Location.R1, Location.R2));
        assertTrue("Should be possible", game.move(Location.R1, Location.R2));
        assertEquals("Should be zero", game.diceValuesLeft().length, 0);
  }
  
  @Test public void afterThreeRollsDiceValuesLeftEqualsDice() {
      game.newGame();
      game.nextTurn();
      game.nextTurn();
      game.nextTurn();
      assertEquals("Should be equal", game.diceThrown(), game.diceValuesLeft());
  }
  
  @Test public void valueOfDiceIsAlwaysReturnedWithGreatestValueLast() {
	  game.newGame();
      game.nextTurn();
      game.diceThrown();
      int[] myRes = game.diceValuesLeft();
      assertEquals("Value should be 2", myRes[0], 1);
      assertEquals("Value should be 2", myRes[1], 2);
      game.move(Location.R1, Location.R3);
      int[] newRes = game.diceValuesLeft();
      assertEquals("Value should be 2", myRes[0], 1);
      
  }

}
