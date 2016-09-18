package hotgammon.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

import hotgammon.common.Location;
import hotgammon.network.dtos.CheckerLocations;
import hotgammon.network.dtos.CheckerLocationsResponse;
import hotgammon.network.dtos.DicesLeftRequest;
import hotgammon.network.dtos.DicesLeftResponse;
import hotgammon.network.dtos.LocationRepresentation;
import hotgammon.network.dtos.MoveRequest;
import hotgammon.network.dtos.MoveResponse;
import hotgammon.network.dtos.NextTurnRequest;
import hotgammon.network.dtos.ObserverMove;
import hotgammon.network.dtos.ObserverRollDice;
import hotgammon.network.dtos.RollDiceRequest;
import hotgammon.network.dtos.RollDiceResponse;
import minidraw.boardgame.BoardGameObserver;

public class ServerCommunication implements Runnable {

	private Socket socket;
	private BoardGameObserver<Location> observer;
    private String line;
	private Gson gson;
	private boolean waitForResponse;
	private boolean running = true;
	private PrintWriter out;
	private BufferedReader in;
	private GameProxy proxy;

	public ServerCommunication(PrintWriter out, BufferedReader in, GameProxy proxy) {
		this.out = out;
		this.in = in;
		this.proxy = proxy;
	    gson = new Gson();
	    
	}

	public void run() {
		try {
	        proxy.checkerLocations = this.checkerLocations();
	        proxy.dicesRolled = this.dicesLeft();
			while(running ){
				while (in.ready() && !waitForResponse) {
					line = in.readLine();
					System.out.println("Input i whileløkke - "+line);
		        	if(line.contains("observer-move")){
		        		ObserverMove omr = gson.fromJson(line, ObserverMove.class);
		        		observer.pieceMovedEvent(omr.payload.from, omr.payload.to);
		        	} else if(line.contains("observer-roll-dice")){
		        		ObserverRollDice ord = gson.fromJson(line, ObserverRollDice.class);
		        		proxy.dicesRolled = ord.payload;
		        		observer.propChangeEvent("dieNo1");
		        		observer.propChangeEvent("dieNo2");
		        	}
				}
			}
			socket.close();
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}
	
	public void observer(String json){
		System.out.println("Input i whileløkke - "+json);
    	if(json.contains("observer-move")){
    		observerMove(json);
    	} else if(json.contains("observer-roll-dice")){
    		observerDiceRolled(json);
    	}
	}

	public void observerDiceRolled(String json) {
		ObserverRollDice ord = gson.fromJson(json, ObserverRollDice.class);
		proxy.dicesRolled = ord.payload;
		observer.propChangeEvent("dieNo1");
		observer.propChangeEvent("dieNo2");
	}

	public void observerMove(String json) {
		ObserverMove omr = gson.fromJson(json, ObserverMove.class);
		observer.pieceMovedEvent(omr.payload.from, omr.payload.to);
	}

	public Boolean move(Location from, Location to) throws IOException {
		waitForResponse = true;
		sendMoveRequest(from, to);
		
		MoveResponse mr = readMoveResponse();
		return mr.payload;
	}

	public MoveResponse readMoveResponse() throws IOException {
		String response = in.readLine();
		if(response.contains("observer")){
			observer(response);
			response = in.readLine();
		}
		MoveResponse mr = gson.fromJson(response, MoveResponse.class);
		waitForResponse = false;
		return mr;
	}

	public void sendMoveRequest(Location from, Location to) {
		MoveRequest m = new MoveRequest();
		m.arguments.from = from;
		m.arguments.to = to;
		String json = gson.toJson(m);
		out.println(json);
	}

	public int[] rollDice() throws IOException {
		waitForResponse = true;
		sendRollDiceRequest();
		RollDiceResponse rdr = readRollDiceResponse();
		waitForResponse = false;
		return rdr.payload;
	}

	private RollDiceResponse readRollDiceResponse() throws IOException {
		String response = in.readLine();
		RollDiceResponse rdr = gson.fromJson(response, RollDiceResponse.class);
		return rdr;
	}

	private void sendRollDiceRequest() {
		RollDiceRequest rd = new RollDiceRequest();
		String json = gson.toJson(rd);
		out.println(json);
	}

	public LocationRepresentation[] checkerLocations() throws IOException {
		waitForResponse = true;
		sendCheckerLocationRequest();

		CheckerLocationsResponse clr = readCheckerLocationResponse();
		waitForResponse = false;
		return clr.payload;
	}

	private CheckerLocationsResponse readCheckerLocationResponse() throws IOException {
		String response = in.readLine();
		System.out.println(response);
		CheckerLocationsResponse clr = gson.fromJson(response, CheckerLocationsResponse.class);
		return clr;
	}

	private void sendCheckerLocationRequest() {
		CheckerLocations cl = new CheckerLocations();
		String json = gson.toJson(cl);
		out.println(json);
	}
	
	public void setObserver(BoardGameObserver<Location> observer){
		this.observer = observer;
		
	}

	public void nextTurn() {
		NextTurnRequest ntr = new NextTurnRequest();
		String json = gson.toJson(ntr);
		out.println(json);
	}
	
	public int[] dicesLeft() throws IOException{
		waitForResponse = true;
		sendDicesLeftRequest();

		DicesLeftResponse clr = readDicesLeftResponse();
		waitForResponse = false;
		return clr.payload;
	}

	private DicesLeftResponse readDicesLeftResponse() throws IOException {
		String response = in.readLine();
		System.out.println(response);
		DicesLeftResponse clr = gson.fromJson(response, DicesLeftResponse.class);
		return clr;
	}

	private void sendDicesLeftRequest() {
		DicesLeftRequest dlr = new DicesLeftRequest();
		String json = gson.toJson(dlr);
		out.println(json);
	}
}