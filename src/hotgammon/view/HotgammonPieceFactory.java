package hotgammon.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hotgammon.common.Color;
import hotgammon.common.Game;
import hotgammon.common.Location;
import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.FigureFactory;

public class HotgammonPieceFactory implements FigureFactory<Location>{

	private Game game;

	public HotgammonPieceFactory(Game game){
		this.game = game;
		
		
	}
	@Override
	public Map<Location, List<BoardFigure>> generatePieceMultiMap() {
		Map<Location, List<BoardFigure>> m = new HashMap<Location,List<BoardFigure>>();
		for(Location l : Location.values()){
			Color colorInLocation = game.getColor(l);
			int countInLocation = game.getCount(l);
			List<BoardFigure> boardFiguresInLocation = new ArrayList<BoardFigure>();
			for(int i = 0; i < countInLocation; i++){
				String checkerResource = getCheckerResource(colorInLocation);
				boardFiguresInLocation.add(new BoardFigure(checkerResource,true, null));
			}
		}
		return m;
	}

	private String getCheckerResource(Color colorInLocation) {
		if(colorInLocation == Color.BLACK)
			return "blackchecker";
		return "redchecker";
	}
	@Override
	public Map<String, BoardFigure> generatePropMap() {
		// TODO Auto-generated method stub
		return null;
	}
	
}