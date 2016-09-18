import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextField;

import hotgammon.common.Location;
import hotgammon.network.client.GameProxy;
import hotgammon.network.client.NetworkGameFigureFactory;
import hotgammon.network.client.ServerCommunication;
import hotgammon.view.GamePositioningStrategy;
import hotgammon.view.GamePropAppearanceStrategy;
import minidraw.boardgame.BoardActionTool;
import minidraw.boardgame.BoardDrawing;
import minidraw.boardgame.BoardGameObserver;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.StdViewWithBackground;

public class GameNetworkClientRunner {
	private static int port = 5000;
	private static String ip = "localhost";

	public static void main(String[] args) throws IOException, InterruptedException {
		try {
			Socket socket = new Socket(ip, port);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			GameProxy proxy = new GameProxy();
			DrawingEditor window = new MiniDrawApplication("Network Demo with BoardGame GUI",
					new HotgammonNetworkFactory(proxy));

			
			ServerCommunication serverComm = new ServerCommunication(writer, reader, proxy);
			proxy.setServer(serverComm);
			Thread t = new Thread(serverComm);
	        t.start();
	        
	        //state.checkerLocations = serverComm.checkerLocations();
	        while(proxy.checkerLocations == null || proxy.dicesRolled == null){
	        	Thread.sleep(500);
	        }

			window.open();
			BoardGameObserver<Location> observer = (BoardDrawing<Location>) window.drawing();
			proxy.setObserver(observer);
			observer.propChangeEvent("dieNo1");
			observer.propChangeEvent("dieNo2");
			serverComm.setObserver(observer);
			window.setTool(new BoardActionTool(window));

		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}

}

class HotgammonNetworkFactory implements Factory{

	private GameProxy proxy;

	public HotgammonNetworkFactory(GameProxy proxy){
		this.proxy = proxy;
	}
	
	@Override
	public Drawing createDrawing(DrawingEditor arg0) {
		return new BoardDrawing<Location>(new NetworkGameFigureFactory(proxy), new GamePositioningStrategy(), new GamePropAppearanceStrategy(proxy));
	}

	@Override
	public DrawingView createDrawingView(DrawingEditor arg0) {
		return new StdViewWithBackground(arg0, "board");
	}

	@Override
	public JTextField createStatusField(DrawingEditor arg0) {
		return new JTextField("Hotgammon awesomeness");
	}
}
