package guidiceroll.visual;

import java.awt.Point;

import hotgammon.common.Location;
import minidraw.boardgame.PositioningStrategy;

public class HotGammonStartPositionStrategyImpl implements PositioningStrategy<Location> {

	@Override
	public Point calculateFigureCoordinatesForProps(String arg0) {
      	if (arg0.matches("dieNo1")) {
    		return new Point(198, 200);
    	}
    	else {
    		return new Point(323, 200);
    	}
	}

	@Override
    public Point calculateFigureCoordinatesIndexedForLocation(Location arg0, int arg1) {
		// TODO Auto-generated method stub
//		return null;
		return new Point(100, 100);
	}

}
