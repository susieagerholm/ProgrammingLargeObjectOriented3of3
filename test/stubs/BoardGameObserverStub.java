package stubs;

import hotgammon.common.Location;
import minidraw.boardgame.BoardGameObserver;

public class BoardGameObserverStub implements BoardGameObserver<Location> {
	public Boolean hasPieceMovedEventBeenCalled = false;
	public int noOfTimesPropChangeEventHasBeenCalled = 0;
	@Override
	public void pieceMovedEvent(Location arg0, Location arg1) {
		hasPieceMovedEventBeenCalled = true;
	}

	@Override
	public void propChangeEvent(String arg0) {
		noOfTimesPropChangeEventHasBeenCalled++;
		
	}

}
