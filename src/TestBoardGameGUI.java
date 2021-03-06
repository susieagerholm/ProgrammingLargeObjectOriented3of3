

import javax.swing.JTextField;

import hotgammon.common.Game;
import hotgammon.common.GameImpl;
import hotgammon.common.Location;
import hotgammon.variants.factory.AlphamonFactory;
import hotgammon.view.BoardGameObserverAdapter;
import hotgammon.view.GameFigureFactory;
import hotgammon.view.GamePositioningStrategy;
import hotgammon.view.GamePropAppearanceStrategy;
import minidraw.boardgame.BoardActionTool;
import minidraw.boardgame.BoardDrawing;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.StdViewWithBackground;

public class TestBoardGameGUI {
	
	 @SuppressWarnings("unchecked")
	public static void main(String[] args) {
		    Game game = new GameImpl(new AlphamonFactory());
		    game.newGame();

		    DrawingEditor window = 
		      new MiniDrawApplication( "Backgammon Demo with BoardGame GUI",
		          new HotgammonFactory(game) );
		    window.open();

		    ((GameImpl) game).addObserver( new BoardGameObserverAdapter((BoardDrawing<Location>) window.drawing()) );
		   
		    window.setTool( new BoardActionTool(window) );
		    game.nextTurn();
		    game.diceThrown();
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
		return new BoardDrawing<Location>(new GameFigureFactory(game), new GamePositioningStrategy(), new GamePropAppearanceStrategy(game));
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