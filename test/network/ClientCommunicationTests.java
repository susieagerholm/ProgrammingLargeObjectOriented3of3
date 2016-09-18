package network;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import hotgammon.common.Location;
import hotgammon.network.dtos.MoveRequest;
import hotgammon.network.dtos.MoveResponse;
import hotgammon.network.dtos.RollDiceRequest;
import hotgammon.network.dtos.RollDiceResponse;
import hotgammon.network.server.ClientCommunication;

public class ClientCommunicationTests {
	private BufferedReader reader;
	private ClientCommunication clientComm;
	private PrintWriter out;
	private Gson gson;
	private NetworkMoveGameStub game;
	@Before
	public void init() throws IOException {
	    PipedInputStream pipeInput = new PipedInputStream();
	    reader = new BufferedReader(
	            new InputStreamReader(pipeInput));
	    out = new PrintWriter(new BufferedOutputStream(
	            new PipedOutputStream(pipeInput)), true);
	    game = new NetworkMoveGameStub();
	    clientComm = new ClientCommunication(reader, out,game);
	    gson = new Gson();
	    
	}
	
	@Test
	public void TestMoveHasBeenCalled(){
		MoveRequest mr = new MoveRequest(Location.B1, Location.B2);
		String json = gson.toJson(mr);
		clientComm.move(out, json);
		Assert.assertTrue(game.hasMoveBeenCalled);
	}
	
	@Test
	public void TestThatMoveResponseIsAddedToStream() throws IOException{
		MoveRequest mr = new MoveRequest(Location.B1, Location.B2);
		String json = gson.toJson(mr);
		clientComm.move(out, json);
		String jsonResponse = reader.readLine();
		MoveResponse moveResponse = gson.fromJson(jsonResponse, MoveResponse.class);
		Assert.assertTrue(moveResponse.payload);
	}
	
	@Test
	public void TestDiceThrownHasBeenCalled(){
		Assert.assertFalse(game.hasDiceThrownBeenCalled);
		RollDiceRequest rdr = new RollDiceRequest();
		String json = gson.toJson(rdr);
		clientComm.rollDice(out, json);
		Assert.assertTrue(game.hasDiceThrownBeenCalled);
	}
	
	@Test
	public void TestThatDiceThrownResponseIsAddedToStream() throws IOException{
		int[] dicesRolled = new int[]{5,1};
		game.diceThrownResponse = dicesRolled;
		RollDiceRequest rdr = new RollDiceRequest();
		String json = gson.toJson(rdr);
		clientComm.rollDice(out, json);
		String jsonResponse = reader.readLine();
		RollDiceResponse rolldiceResponse = gson.fromJson(jsonResponse, RollDiceResponse.class);
		Assert.assertArrayEquals(dicesRolled, rolldiceResponse.payload);
	}
	
}
