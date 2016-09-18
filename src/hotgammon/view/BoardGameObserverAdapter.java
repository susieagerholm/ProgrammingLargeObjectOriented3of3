package hotgammon.view;

import hotgammon.common.GameObserver;
import hotgammon.common.Location;
import minidraw.boardgame.BoardGameObserver;

public class BoardGameObserverAdapter implements GameObserver {

	private BoardGameObserver<Location> boardGameObserver;

	public BoardGameObserverAdapter(BoardGameObserver<Location> boardGameObserver){
		this.boardGameObserver = boardGameObserver;
		
	}
	@Override
	public void checkerMove(Location from, Location to) {
		boardGameObserver.pieceMovedEvent(from, to);
	}

	@Override
	public void diceRolled(int[] values) {
		boardGameObserver.propChangeEvent("dieNo1");
		boardGameObserver.propChangeEvent("dieNo2");
	}

}
