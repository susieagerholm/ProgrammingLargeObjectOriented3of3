package guidiceroll.stub;

import java.util.ArrayList;
import java.util.List;

import hotgammon.common.Color;
import hotgammon.common.Location;
import minidraw.boardgame.BoardDrawing;

/** A testing stub for visual testing of
 * the hotgammon graphical user interface.
 */
public class StubGuiDiceRoll implements StubDiceRollGame {

  private List<BoardDrawing<Location>> gameObserverList = new ArrayList<BoardDrawing<Location>>();
  private int movesLeft; // moves left to make for a player
  private int turn; // count turns, used to simulate dice rolling
  private int diceValues[] = new int[2];

	
  public StubGuiDiceRoll() {
    newGame();
  }

  public void newGame() {
    movesLeft = 0;
    turn = 0;
  }
  
  @Override  
  public void nextTurn() {
    turn++;
    movesLeft = 2;
	diceValues = diceThrown();
    notifyObservers("dieNo1");
    notifyObservers("dieNo2");
    System.out.println("nextTurn: " + turn);
  }

  @Override
  public int getNumberOfMovesLeft() {
    return movesLeft;
  }

  @Override  
  public int[] diceThrown() {
	movesLeft = 0;						// Fake it so GUI is able to roll the dice without moving any checkers!
    switch (turn % 6) {
      case 1 :
        return new int[] { 1, 2 };
      case 2 :
        return new int[] { 3, 4 };
      case 3 :
        return new int[] { 5, 6 };
      case 4 :
        return new int[] { 2, 1 };
      case 5 :
        return new int[] { 4, 3 };
      case 0 :
        return new int[] { 6, 5 };
      default :
        return new int[] { 0, 0 };
    }
  }

  @Override
  public int[] diceValuesLeft() {
	  if (movesLeft == 2) {
		  return  diceValues;
	  }
	  else if (movesLeft == 1) {
		  return  diceValues;
	  }
	  else {
		  return  diceValues;
	  }
  }


  @Override
  public Color getColor(Location location) { 
	  return Color.NONE;
  }

  @Override
  public int getCount(Location location) {
	  return 0;
  }
  
  
  @Override  
  public void addObserver(BoardDrawing<Location> observer) {
  	gameObserverList.add(observer);
  }
  
  private void notifyObservers(String event) {
  	 for (BoardDrawing<Location> go : gameObserverList) {
  		 go.propChangeEvent(event);
  	 }
  }
  
}
