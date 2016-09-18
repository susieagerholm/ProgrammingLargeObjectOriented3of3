package network;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import hotgammon.common.Location;
import hotgammon.network.client.GameProxy;
import hotgammon.network.client.ServerCommunication;
import hotgammon.network.dtos.MoveRequest;
import hotgammon.network.dtos.MoveRequestArgument;
import hotgammon.network.dtos.MoveResponse;
import hotgammon.network.dtos.ObserverMove;
import hotgammon.network.dtos.ObserverRollDice;
import junit.framework.Assert;
import stubs.BoardGameObserverStub;

public class ServerCommunicationTests {
	private BufferedReader reader;
	private ServerCommunication serverComm;
	private PrintWriter out;
	private Gson gson;
	private NetworkMoveGameStub game;
	private GameProxy proxy;
	
	@Before
	public void init() throws IOException {
	    PipedInputStream pipeInput = new PipedInputStream();
	    reader = new BufferedReader(
	            new InputStreamReader(pipeInput));
	    out = new PrintWriter(new BufferedOutputStream(
	            new PipedOutputStream(pipeInput)), true);
	    game = new NetworkMoveGameStub();
	    proxy = new GameProxy();
	    serverComm = new ServerCommunication(out, reader, proxy);
	    gson = new Gson();
	}
	
	@Test
	public void TestThatCorrectRequestIsWrittenToStream() throws IOException{
		Location fromLocation = Location.B1, toLocation = Location.B3;
		serverComm.sendMoveRequest(fromLocation, toLocation);
		MoveRequest expected = new MoveRequest(fromLocation, toLocation);
		String jsonrequest = reader.readLine();
		MoveRequest actual = gson.fromJson(jsonrequest, MoveRequest.class);
		Assert.assertEquals(expected.arguments.from, actual.arguments.from);
		Assert.assertEquals(expected.arguments.to, actual.arguments.to);
	}
	
	@Test
	public void TestThatCorrectResponseIsReadFromStream() throws IOException{
		Location fromLocation = Location.B1, toLocation = Location.B3;
		
		MoveResponse expected = new MoveResponse();
		expected.payload = true;
		String jsonResponse = gson.toJson(expected);
		out.println(jsonResponse);
		MoveResponse actual = serverComm.readMoveResponse();
		Assert.assertEquals(expected.payload, actual.payload);
		
	}
	
	@Test
	public void TestThatObserverMoveCallObserver() throws IOException{
		BoardGameObserverStub observer = new BoardGameObserverStub();
		serverComm.setObserver(observer);
		ObserverMove om = new ObserverMove();
		om.payload = new MoveRequestArgument();
		om.payload.from = Location.B1;
		om.payload.to = Location.B2;
		String json = gson.toJson(om);
		serverComm.observerMove(json);
		Assert.assertTrue(observer.hasPieceMovedEventBeenCalled);
	}
	
	@Test
	public void TestThatObserverDiceRollCallObserver(){
		BoardGameObserverStub observer = new BoardGameObserverStub();
		serverComm.setObserver(observer);
		ObserverRollDice ord = new ObserverRollDice();
		ord.payload = new int[]{1,3};
		serverComm.observerDiceRolled(gson.toJson(ord));
		Assert.assertEquals(2,observer.noOfTimesPropChangeEventHasBeenCalled);
	}
}
