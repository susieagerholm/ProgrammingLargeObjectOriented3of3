package hotgammon.network.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hotgammon.common.Color;
import hotgammon.common.Location;
import hotgammon.network.dtos.LocationRepresentation;
import hotgammon.view.CheckerMoveCommand;
import hotgammon.view.DiceRollCommand;
import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.FigureFactory;

public class NetworkGameFigureFactory implements FigureFactory<Location> {

	private GameProxy proxy;

	public NetworkGameFigureFactory(GameProxy proxy) {
		// TODO Auto-generated constructor stub
		this.proxy = proxy;
	}

	@Override
	public Map<Location, List<BoardFigure>> generatePieceMultiMap() {
		Map<Location, List<BoardFigure>> m = new HashMap<Location, List<BoardFigure>>();
		
		//for each location in game - create figurines on location...
		for( LocationRepresentation loc : proxy.checkerLocations ) {
			List<BoardFigure> list = new ArrayList<BoardFigure>();
			//GETTING VALUES FROM LOCATION
			//System.out.println("GETTING LOCATION? " + loc.name());
			int no_checkers = loc.count;
			
			//DOES LOCATION CONTAIN ANY CHECKERS?*??
			if (no_checkers > 0) {
				Color col_checkers = loc.color;
				String col_string = col_checkers.name() + "checker";
				String col_string_lower = col_string.toLowerCase();
				
				for(int i = 0; i < no_checkers; i++) {
	        	
		        	System.out.println("CREATING FIGURE FOR " + loc + "with color " + col_string_lower);
		        	list.add(new BoardFigure(col_string_lower, true, new CheckerMoveCommand(proxy))); // TODO
		        	//System.out.println("NEW POINT CREATED AT: 0, " + offset);
		        
		        	//offset = offset + 0.5;
		        }
		    }
			//DO FAKE LIST IF LOCATION HAS NO CHECKERS
			else {
				//BLABLA
			}
			m.put( loc.location, list);
	    }
		System.out.println(Arrays.toString(m.entrySet().toArray()));
		return m;
	}

	@Override
	public Map<String, BoardFigure> generatePropMap() {
		Map<String, BoardFigure> m = new HashMap<String, BoardFigure>();

		for (int dieNo = 1; dieNo <= 2; dieNo++) { // We have two dice
			String dieKey = "dieNo" + dieNo;
			m.put( dieKey, new BoardFigure("die0", false, new DiceRollCommand(proxy)));
		}
		
		return m;
	}

}
