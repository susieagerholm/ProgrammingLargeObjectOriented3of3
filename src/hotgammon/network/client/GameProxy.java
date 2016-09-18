package hotgammon.network.client;

import java.io.IOException;

import hotgammon.common.Color;
import hotgammon.common.Game;
import hotgammon.common.GameObserver;
import hotgammon.common.Location;
import hotgammon.network.dtos.LocationRepresentation;
import minidraw.boardgame.BoardGameObserver;

public class GameProxy implements Game {
	private ServerCommunication server;
	public int[] dicesRolled;
	public LocationRepresentation[] checkerLocations;
	public int numberOfMovesLeft;

	public GameProxy(ServerCommunication server, BoardGameObserver<Location> observer){
		this.server = server;
	}
	
	public GameProxy(){
		
	}
	public boolean move(Location from, Location to){		
		try {
			return server.move(from, to);
		} catch (IOException e) {
			return false;
		}
	}

	public LocationRepresentation[] checkerLocations() throws IOException, InterruptedException {
		LocationRepresentation[] locations = server.checkerLocations();
		checkerLocations = locations;
		return locations;
	}
	
	public void setObserver(BoardGameObserver<Location> observer){
	}
	
	public void setServer(ServerCommunication server){
		this.server = server;
	}

	public void nextTurn() {
		server.nextTurn();
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getPlayerInTurn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfMovesLeft() {
		return this.numberOfMovesLeft;
	}

	@Override
	public int[] diceThrown() {
		try{
		dicesRolled = server.rollDice();;
		return dicesRolled;
		} catch (IOException e){
			return new int[0];
		}
	}

	@Override
	public int[] diceValuesLeft() {
		return dicesRolled;
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
		return this.numberOfMovesLeft;
	}

	@Override
	public void addObserver(GameObserver observer) {
		// TODO Auto-generated method stub
		
	}


}
