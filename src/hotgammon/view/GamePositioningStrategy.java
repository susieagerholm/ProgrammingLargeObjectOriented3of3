package hotgammon.view;

import java.awt.Point;

import hotgammon.common.Location;
import minidraw.boardgame.PositioningStrategy;

public class GamePositioningStrategy implements PositioningStrategy<Location> {
	

	@Override
	public Point calculateFigureCoordinatesIndexedForLocation(Location location, int index) {
		int x, y;
		String loc = location.toString();
		int loc_int = Integer.parseInt(loc.replaceAll("[\\D]", ""));
		x = getXPosition(loc_int);
		y = getYPosition(loc, index);
		System.out.println("FOR LOCATION: " + location.name() + " CREATING NEW POINT WITH COORDINATES" + x + "," + y);
		return new Point(x,y);
	}

	@Override
	public Point calculateFigureCoordinatesForProps(String arg0) {
      	if (arg0.matches("dieNo1")) {
    		return new Point(198, 200);
    	}
    	else {
    		return new Point(323, 200);
    	}
	}
	
	private int getXPosition(int loc_by_int) {
		//position all checkers by location - locations by 40, add 510 offset (populating from right side of board) 
		int x = ((loc_by_int - 1) * -40) + 510;
		if(loc_by_int < 7) {
			return x;
		}
		//To leave extra space for Bar location: Deduct extra 50 for positions to the left of the board 
		else {
			return x - 50;
		}		
	}
	 
		private int getYPosition(String loc, int index) {
		//is location of current checker on the upper half of the Board ("B*"
		if (loc.startsWith("B")) {
			//set initial offset for checkers and indent by index
			return 20  + (30 * index);
		}	
		//else offset for checkers in buttom half of board and negate indent by index
		else {
			return 390 + (-30 * index);
		}
	}

}
