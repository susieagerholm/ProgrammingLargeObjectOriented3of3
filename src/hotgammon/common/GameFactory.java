package hotgammon.common;

import hotgammon.variants.strategy.DiceRollStrategy;
import hotgammon.variants.strategy.MoveValidatorStrategy;
import hotgammon.variants.strategy.StartPositionStrategy;
import hotgammon.variants.strategy.WinnerStrategy;
 
public interface GameFactory {
	public DiceRollStrategy createDiceRollStrategy();
	public MoveValidatorStrategy createMoveValidatorStrategy();
	public StartPositionStrategy createStartPositionStrategy();
	public WinnerStrategy createWinnerStrategy();
}
