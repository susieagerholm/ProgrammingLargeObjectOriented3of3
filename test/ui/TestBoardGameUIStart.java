package ui;

import javax.swing.JTextField;

import org.junit.Test;

import hotgammon.common.Game;
import hotgammon.common.GameImpl;
import hotgammon.common.Location;
import hotgammon.variants.factory.AlphamonFactory;
import hotgammon.view.GameFigureFactory;
import hotgammon.view.GamePositioningStrategy;
import minidraw.boardgame.BoardDrawing;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.StdViewWithBackground;


public class TestBoardGameUIStart {
	@Test
	public void runBoard(){
		Game game = new GameImpl(new AlphamonFactory());
		game.newGame();
		DrawingEditor editor = new MiniDrawApplication("Hotgammon", new HotgammonFactory(game));
		editor.open();
		
		
	}
	
}

class HotgammonFactory implements Factory
{
	private Game game;

	public HotgammonFactory(Game game)
	{
		this.game = game;
		
	}

	@Override
	public Drawing createDrawing(DrawingEditor arg0) {
		return new BoardDrawing<Location>(new GameFigureFactory(game), new GamePositioningStrategy(), null);
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