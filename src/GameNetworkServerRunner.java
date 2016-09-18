import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import hotgammon.common.Game;
import hotgammon.common.GameImpl;
import hotgammon.network.server.ClientCommunication;
import hotgammon.variants.factory.AlphamonFactory;

public class GameNetworkServerRunner {
	private static int port = 5000, maxConnections = 0;

	public static void main(String[] args) throws IOException {
		System.out.println("Starting server");
		Game game = new GameImpl(new AlphamonFactory());
		game.newGame();
		game.nextTurn();
		game.diceThrown();
		int i = 0;

		try {
			ServerSocket listener = new ServerSocket(port);
			Socket server;

			while ((i++ < maxConnections) || (maxConnections == 0)) {
				server = listener.accept();
				BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
				PrintWriter writer = new PrintWriter(server.getOutputStream(), true);
				ClientCommunication clientComm = new ClientCommunication(reader, writer, game);
				Thread t = new Thread(clientComm);
				t.start();
			}
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}

}
