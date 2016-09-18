package hotgammon.network.dtos;

import hotgammon.common.Game;

public class MoveNetworkCommand extends NetworkCommand<MoveRequestArgument> {

	
	@Override
	public void execute(Game game) {
		game.move(this.arguments.from, this.arguments.to);
	}

}
