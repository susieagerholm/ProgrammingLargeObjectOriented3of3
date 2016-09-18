package guidiceroll.visual;

import guidiceroll.stub.StubDiceRollGame;
import minidraw.boardgame.PropAppearanceStrategy;

public class HotGammonPropAppearanceStrategy implements PropAppearanceStrategy {

	private StubDiceRollGame game;
	private int rollTheDie = -1;
	
	public HotGammonPropAppearanceStrategy(StubDiceRollGame game) {
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
