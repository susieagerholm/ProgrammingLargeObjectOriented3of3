package hotgammon.variants.factory;

import hotgammon.common.GameFactory;
import hotgammon.variants.strategy.DiceRollStrategy;
import hotgammon.variants.strategy.MoveValidatorStrategy;
import hotgammon.variants.strategy.StartPositionStrategy;
import hotgammon.variants.strategy.WinnerStrategy;
import hotgammon.variants.strategyimpl.AlphamonMoveValidatorStrategyImpl;
import hotgammon.variants.strategyimpl.AlphamonStartPositionStrategyImpl;
import hotgammon.variants.strategyimpl.AlphamonWinnerStrategyImpl;
import hotgammon.variants.strategyimpl.EpsilonmonDiceRollStrategyImpl;

public class EpsilonmonFactory implements GameFactory {

	@Override
	public DiceRollStrategy createDiceRollStrategy() {
		return new EpsilonmonDiceRollStrategyImpl();
	}

	@Override
	public MoveValidatorStrategy createMoveValidatorStrategy() {
		return new AlphamonMoveValidatorStrategyImpl();
	}

	@Override
	public StartPositionStrategy createStartPositionStrategy() {
		return new AlphamonStartPositionStrategyImpl();
	}

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new AlphamonWinnerStrategyImpl();
	}

}
