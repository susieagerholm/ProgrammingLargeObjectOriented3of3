package hotgammon.network.server;

import java.io.PrintWriter;

import com.google.gson.Gson;

import hotgammon.common.GameObserver;
import hotgammon.common.Location;
import hotgammon.network.dtos.MoveRequestArgument;
import hotgammon.network.dtos.ObserverMove;
import hotgammon.network.dtos.ObserverRollDice;

public class NetworkObserver implements GameObserver{

	private PrintWriter outputStream;
	private Gson gson;

	public NetworkObserver(PrintWriter out){
		this.outputStream = out;
	    gson = new Gson();
	}
	@Override
	public void checkerMove(Location from, Location to) {
		ObserverMove m = new ObserverMove();
		m.payload = new MoveRequestArgument();
		m.payload.from = from;
		m.payload.to = to;
		String json = gson.toJson(m);
		outputStream.println(json);
	}

	@Override
	public void diceRolled(int[] values) {
		ObserverRollDice drr = new ObserverRollDice();
		drr.payload = values;
		String json = gson.toJson(drr);
		outputStream.println(json);
	}
}