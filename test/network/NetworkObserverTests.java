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
import hotgammon.network.dtos.ObserverMove;
import hotgammon.network.dtos.ObserverRollDice;
import hotgammon.network.server.NetworkObserver;

public class NetworkObserverTests {
	private BufferedReader reader;
	private NetworkObserver networkObserver;

	@Before
	public void init() throws IOException {
	    PipedInputStream pipeInput = new PipedInputStream();
	    reader = new BufferedReader(
	            new InputStreamReader(pipeInput));
	    PrintWriter out = new PrintWriter(new BufferedOutputStream(
	            new PipedOutputStream(pipeInput)), true);
	    networkObserver = new NetworkObserver(out);
	}
	
	@Test
	public void TestCheckerMoveIsSendingCorrectObjectToStream() throws IOException {
	    networkObserver.checkerMove(Location.B1, Location.B10);
		ObserverMove m = new ObserverMove();
		m.payload.from = Location.B1;
		m.payload.to = Location.B10;
		Gson gson = new Gson();
		String json = gson.toJson(m);
	    String outputFromReader = reader.readLine();
	    Assert.assertEquals(json, outputFromReader);
	}
	
	@Test
	public void TestDiceRollIsSendingCorrectObjectToStream() throws IOException {
		int[] dicesRolled = new int[]{1,5};
	    networkObserver.diceRolled(dicesRolled);
		ObserverRollDice drr = new ObserverRollDice();
		drr.payload = dicesRolled;
		Gson gson = new Gson();
		String json = gson.toJson(drr);
	    String outputFromReader = reader.readLine();
	    Assert.assertEquals(json, outputFromReader);
	}
	
}
