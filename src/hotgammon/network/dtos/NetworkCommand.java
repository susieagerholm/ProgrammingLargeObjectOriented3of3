package hotgammon.network.dtos;

import hotgammon.common.Game;

public abstract class NetworkCommand<T> {
	String command;
	public abstract void execute(Game game);
	T arguments;
}
