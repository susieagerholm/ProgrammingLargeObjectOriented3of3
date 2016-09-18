package guidiceroll.visual;

import javax.swing.JTextField;

import guidiceroll.stub.StubDiceRollGame;
import guidiceroll.stub.StubGuiDiceRoll;
import hotgammon.common.Location;
import minidraw.boardgame.BoardActionTool;
import minidraw.boardgame.BoardDrawing;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.StdViewWithBackground;

/** Show and roll the dice on the backgammon board. */
public class ShowDice {
  
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {

    StubDiceRollGame my_game = new StubGuiDiceRoll();
    
    DrawingEditor my_board = new MiniDrawApplication( "HotGammon Dice Visual Test ...",  
                               						  new HotGammonFactory(my_game) );
    my_board.open();
    my_game.addObserver( (BoardDrawing<Location>) my_board.drawing() );
    my_board.setTool(new BoardActionTool(my_board));
  }
}

class HotGammonFactory implements Factory {

  private StubDiceRollGame game;
	  
  public HotGammonFactory(StubDiceRollGame game) {
		super();
		this.game = game;
	}

  public DrawingView createDrawingView( DrawingEditor editor ) {
	DrawingView view = 
	new StdViewWithBackground(editor, "board");
	return view;
  }

  
  public Drawing createDrawing( DrawingEditor editor ) {
    return new BoardDrawing<Location>(new HotGammonPieceFactory(game),
                                      new HotGammonStartPositionStrategyImpl( ),
                                      new HotGammonPropAppearanceStrategy(game) );
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    JTextField statusField = new JTextField( "Hello HotGammon..." );
    statusField.setEditable(false);
    return statusField;
  }
}


