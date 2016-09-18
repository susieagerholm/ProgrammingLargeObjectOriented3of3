package hotgammon.view;

import hotgammon.common.Game;
import hotgammon.common.Location;
import minidraw.boardgame.Command;

public class CheckerMoveCommand implements Command {

	private Game game;
	private Location fromLocation, toLocation;
	
	public CheckerMoveCommand(Game game){
		this.game = game;
	}
	
	@Override
	public boolean execute() {
		boolean isMoveValid = game.move(fromLocation, toLocation);
		System.out.println("Move is: "+isMoveValid);
		return isMoveValid;
	}

	@Override
	public void setFromCoordinates(int x, int y) {
		fromLocation = Convert.xy2Location(x, y);
	}

	@Override
	public void setToCoordinates(int x, int y) {
		toLocation = Convert.xy2Location(x, y);
	}

}