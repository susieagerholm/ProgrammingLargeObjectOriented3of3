package hotgammon.view;

import hotgammon.common.Game;
import minidraw.boardgame.PropAppearanceStrategy;

public class GamePropAppearanceStrategy implements PropAppearanceStrategy {

	private Game game;
	
	public GamePropAppearanceStrategy(Game game) {
		this.game = game;
	}


	@Override
	public String calculateImageNameForPropWithKey(String keyOfProp) {
		// Must ask game for new die value
		// keyOfProp tells which die to update
		// Notifier must notify us twice to update both dice
		System.out.println("Dice rolled. Update this die: " + keyOfProp);
		int diceValues[] = new int[4];
		diceValues = game.diceValuesLeft();  // Get the dice values
		if (keyOfProp == "dieNo1") {
			return "die" + diceValues[0];
		}
		else {
			return "die" + diceValues[1];
		}
	}

}
