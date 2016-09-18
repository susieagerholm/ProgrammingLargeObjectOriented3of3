package guidiceroll.visual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import guidiceroll.stub.StubDiceRollGame;
import hotgammon.common.Color;
//import hotgammon.common.StubDiceRollGame;
import hotgammon.common.Location;
import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.FigureFactory;
import minidraw.boardgame.NullCommand;

public class HotGammonPieceFactory implements FigureFactory<Location> {

	private StubDiceRollGame game;

	public HotGammonPieceFactory(StubDiceRollGame game) {
		super();
		this.game = game;
	}

	@Override
	public Map<Location, List<BoardFigure>> generatePieceMultiMap() {
		Map<Location, List<BoardFigure>> m = new HashMap<Location, List<BoardFigure>>();
		
		//for each location in game - create figurines on location...
		for( Location loc : Location.values() ) {
			List<BoardFigure> list = new ArrayList<BoardFigure>();
			//GETTING VALUES FROM LOCATION
			System.out.println("GETTING LOCATION? " + loc.name());
			int no_checkers = game.getCount(loc);
			
			//DOES LOCATION CONTAIN ANY CHECKERS?*??
			if (no_checkers > 0) {
				Color col_checkers = game.getColor(loc);
				String col_string = col_checkers.name() + "checker";
				String col_string_lower = col_string.toLowerCase();
				
				for(int i = 0; i < no_checkers; i++) {
	        	
		        	System.out.println("CREATING FIGURE FOR " + loc + "with color " + col_string_lower);
					// TODO 20151127 Erik: NullCommand() must be replaced by CheckerMoveCommand()
		        	list.add(new BoardFigure(col_string_lower, true, new NullCommand()));
		        }
		    }
			//DO FAKE LIST IF LOCATION HAS NO CHECKERS
			else {
				// TODO 20151127 Erik: Something is missing here or remove else-statement
				//BLABLA
			}
			m.put( loc, list);
	    }
		
		System.out.println(Arrays.toString(m.entrySet().toArray()));
		return m;
	}
	
	@Override
	public Map<String, BoardFigure> generatePropMap() {
		Map<String, BoardFigure> m = new HashMap<String, BoardFigure>();

		for (int dieNo = 1; dieNo <= 2; dieNo++) { // We have two dice
			String dieKey = "dieNo" + dieNo;
			m.put( dieKey, new BoardFigure("die0", false, new DiceRollCommand(game)));
		}
		
		return m;
	}

}
