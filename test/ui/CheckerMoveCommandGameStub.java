package ui;

import hotgammon.common.Color;
import hotgammon.common.Game;
import hotgammon.common.GameObserver;
import hotgammon.common.Location;

public class CheckerMoveCommandGameStub implements Game {
	public Boolean moveIsCalled = false;
	@Override
	public void newGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void nextTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean move(Location from, Location to) {
		moveIsCalled = true;
		return false;
	}

	@Override
	public Color getPlayerInTurn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfMovesLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] diceThrown() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] diceValuesLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color winner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putColor(Location location, Color color) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCount(Location location) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void putCount(Location location, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNumberOfTurn() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addObserver(GameObserver observer) {
		// TODO Auto-generated method stub

	}

}
