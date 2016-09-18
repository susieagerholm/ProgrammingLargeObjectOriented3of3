package hotgammon.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hotgammon.common.Color;
import hotgammon.common.Game;
import hotgammon.common.Location;
import minidraw.boardgame.BoardFigure;


public class GameFigureFactory implements minidraw.boardgame.FigureFactory<Location> {
	private Game game;

	public GameFigureFactory(Game myGame) {
		this.game = myGame;
	}

	@Override
	public Map<Location, List<minidraw.boardgame.BoardFigure>> generatePieceMultiMap() {
		Map<Location, List<BoardFigure>> m = new HashMap<Location, List<BoardFigure>>();
		
		//for each location in game - create figures on location...
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
		        	list.add(new BoardFigure(col_string_lower, true, new CheckerMoveCommand(game)));
		        }
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
