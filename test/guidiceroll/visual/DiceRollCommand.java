package guidiceroll.visual;

import guidiceroll.stub.StubDiceRollGame;
import minidraw.boardgame.Command;

public class DiceRollCommand implements Command {

	private StubDiceRollGame game;
	
	public DiceRollCommand(StubDiceRollGame game) {
		this.game = game;
	}

	@Override
	public void setFromCoordinates(int fromX, int fromY) {
		// Die clicked
		System.out.println("Die clicked!");
	}

	@Override
	public void setToCoordinates(int toX, int toY) {
		// Die does not move
		System.out.println("Die does not move!");		
	}

	@Override
	public boolean execute() {
		System.out.println("Dice roll command execute!");
		if (game.getNumberOfMovesLeft() == 0) {
			game.nextTurn();		
			return true;
		}
		return false;
	}
}
