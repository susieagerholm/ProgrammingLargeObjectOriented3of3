package hotgammon.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import hotgammon.common.Game;
import hotgammon.common.Location;
import hotgammon.network.dtos.CheckerLocations;
import hotgammon.network.dtos.CheckerLocationsResponse;
import hotgammon.network.dtos.DicesLeftResponse;
import hotgammon.network.dtos.LocationRepresentation;
import hotgammon.network.dtos.MoveRequest;
import hotgammon.network.dtos.MoveResponse;
import hotgammon.network.dtos.MovesLeft;
import hotgammon.network.dtos.MovesLeftResponse;
import hotgammon.network.dtos.RollDiceRequest;
import hotgammon.network.dtos.RollDiceResponse;

public class ClientCommunication implements Runnable {
	private String line;
	private Game game;
	private Gson gson;
	private BufferedReader in;
	private PrintWriter out;

	public ClientCommunication(BufferedReader in, PrintWriter out, Game game) {
		this.in = in;
		this.out = out;
		this.game = game;
		gson = new Gson();
	}

	public void run() {
		try {
			System.out.println("Client connected");
			
			game.addObserver(new NetworkObserver(out));
			while ((line = in.readLine()) != null && line != "hest") {
				System.out.println(line);
				if (line.contains("\"move\"")) {
					move(out, line);
				} else if (line.contains("roll-dice")) {
					rollDice(out, line);
				} else if (line.contains("moves-left")) {
					movesLeft(out, line);
				} else if (line.contains("checker-locations")) {
					checkerLocaitons(out, line);
				} else if (line.contains("next-turn")){
					nextTurn();
				} else if (line.contains("dices-left")) {
					dicesLeft(out, line);
				} else {
				
					out.println("I don't understand the command");
					out.println(line);
				}
			}

		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}

	private void dicesLeft(PrintWriter out, String json) {
		DicesLeftResponse dlr = new DicesLeftResponse();
		dlr.payload = game.diceValuesLeft();
		String responseJson = gson.toJson(dlr);
		out.println(responseJson);
	}

	private void nextTurn() {
		game.nextTurn();
		game.diceThrown();
	}

	public void checkerLocaitons(PrintWriter out, String json) {
		gson.fromJson(json, CheckerLocations.class);
		LocationRepresentation[] lrArray = new LocationRepresentation[28];
		int i = 0;
		for (Location l : Location.values()) {
			LocationRepresentation lr = new LocationRepresentation();
			lr.location = l;
			lr.color = game.getColor(l);
			lr.count = game.getCount(l);
			lrArray[i++] = lr;
		}
		CheckerLocationsResponse clr = new CheckerLocationsResponse();
		clr.payload = lrArray;
		String responseJson = gson.toJson(clr);
		System.out.println("Skriver CheckerLocationResponse til client");
		out.println(responseJson);
	}

	public void movesLeft(PrintWriter out, String json) {
		gson.fromJson(json, MovesLeft.class);
		int movesLeft = game.getNumberOfMovesLeft();
		MovesLeftResponse mlr = new MovesLeftResponse();
		mlr.payload = movesLeft;
		String responseJson = gson.toJson(mlr);
		out.println(responseJson);
	}

	public void rollDice(PrintWriter out, String json) {
		gson.fromJson(json, RollDiceRequest.class);
		int[] dices = game.diceThrown();
		RollDiceResponse rdr = new RollDiceResponse();
		rdr.payload = dices;
		String responseJson = gson.toJson(rdr);
		out.println(responseJson);
	}

	public void move(PrintWriter out, String json) {
		MoveRequest m = gson.fromJson(json, MoveRequest.class);
		
		Boolean isMoveValid = game.move(m.arguments.from, m.arguments.to);
		MoveResponse mr = new MoveResponse();
		mr.payload = isMoveValid;
		String responseJson = gson.toJson(mr);
		out.println(responseJson);
	}
}