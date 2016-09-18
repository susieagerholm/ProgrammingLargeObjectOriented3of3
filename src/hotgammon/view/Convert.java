package hotgammon.view;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import hotgammon.common.Location;

/** Mappings between graphical coordinate (x,y) and locations;
 * and vice versa.
 * 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
public class Convert {
  /** Given coordinate (x,y) on the graphical backgammon board
   * return the location it is witin, or null if not on any
   * location.
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public static Location xy2Location(int x, int y) {
    Set<Location> s = mapLocation2Rectangle.keySet();
    for( Location l : s ) {
      Rectangle r = mapLocation2Rectangle.get(l);
      if (r.contains(x,y)) {
        return l;
      }
    }
    return null;
  }

  /** Given a  location and a count of checkers on this location,
   * compute the (x,y) position of a properly positioned checker
   * (i.e. properly aligned).
   * @param location the location on the board
   * @param count the number of checkers already on this location
   */
  public static Point locationAndCount2xy(Location location, int count) {
    Rectangle box = mapLocation2Rectangle.get(location);

    int size = 27; // magic constant: a checker is 27 pixels wide and high...
    // calculate x
    int newx = box.x + (box.width - size) / 2;

    // calculate y
    int newy;
    // stack in the y-directio if on the bear off locations
    if ( location == Location.B_BEAR_OFF 
         || 
         location == Location.R_BEAR_OFF ) { 
      size /= 2; 
    }
    // if on the black side of the board
    if ( location == Location.B1 ||
         location == Location.B2 ||
         location == Location.B3 ||
         location == Location.B4 ||
         location == Location.B5 ||
         location == Location.B6 ||
         location == Location.B7 ||
         location == Location.B8 ||
         location == Location.B9 ||
         location == Location.B10 ||
         location == Location.B11 ||
         location == Location.B12 ||
         location == Location.B12 ||
         location == Location.B_BEAR_OFF ||
         location == Location.R_BAR ) {
      newy = size * count + box.y;
    } else if ( location == Location.R_BEAR_OFF ) {
      newy =
        (box.y + box.height)
        - (size * (count + 2));
    } else {
      newy =
        (box.y + box.height)
        - (size * (count + 1));
    }
    return new Point(newx, newy);
  }

  private static Map<Location,Rectangle> mapLocation2Rectangle;
  private static void defineLocation2RectangleMap() {
    mapLocation2Rectangle = new HashMap<Location,Rectangle>();
     // Black normal points
    processARectangleCreationForLocation(Location.B12, new Rectangle(15 + 0 * 40, 20, 40, 200));
    processARectangleCreationForLocation(Location.B11, new Rectangle(15 + 1 * 40, 20, 40, 200));
    processARectangleCreationForLocation(Location.B10, new Rectangle(15 + 2 * 40, 20, 40, 200));
    processARectangleCreationForLocation(Location.B9, new Rectangle(15 + 3 * 40, 20, 40, 200));
    processARectangleCreationForLocation(Location.B8, new Rectangle(15 + 4 * 40, 20, 40, 200));
    processARectangleCreationForLocation(Location.B7, new Rectangle(15 + 5 * 40, 20, 40, 200));
    
    processARectangleCreationForLocation(Location.B6, new Rectangle(300 + 0 * 40, 20, 40, 200));
    processARectangleCreationForLocation(Location.B5, new Rectangle(300 + 1 * 40, 20, 40, 200));
    processARectangleCreationForLocation(Location.B4, new Rectangle(300 + 2 * 40, 20, 40, 200));
    processARectangleCreationForLocation(Location.B3, new Rectangle(300 + 3 * 40, 20, 40, 200));
    processARectangleCreationForLocation(Location.B2, new Rectangle(300 + 4 * 40, 20, 40, 200));
    processARectangleCreationForLocation(Location.B1, new Rectangle(300 + 5 * 40, 20, 40, 200));
    
    // red normal points
    processARectangleCreationForLocation(Location.R12, new Rectangle(15 + 0 * 40, 217, 40, 200));
    processARectangleCreationForLocation(Location.R11, new Rectangle(15 + 1 * 40, 217, 40, 200));
    processARectangleCreationForLocation(Location.R10, new Rectangle(15 + 2 * 40, 217, 40, 200));
    processARectangleCreationForLocation(Location.R9, new Rectangle(15 + 3 * 40, 217, 40, 200));
    processARectangleCreationForLocation(Location.R8, new Rectangle(15 + 4 * 40, 217, 40, 200));
    processARectangleCreationForLocation(Location.R7, new Rectangle(15 + 5 * 40, 217, 40, 200));
    
    processARectangleCreationForLocation(Location.R6, new Rectangle(300 + 0 * 40, 217, 40, 200));
    processARectangleCreationForLocation(Location.R5, new Rectangle(300 + 1 * 40, 217, 40, 200));
    processARectangleCreationForLocation(Location.R4, new Rectangle(300 + 2 * 40, 217, 40, 200));
    processARectangleCreationForLocation(Location.R3, new Rectangle(300 + 3 * 40, 217, 40, 200));
    processARectangleCreationForLocation(Location.R2, new Rectangle(300 + 4 * 40, 217, 40, 200));
    processARectangleCreationForLocation(Location.R1, new Rectangle(300 + 5 * 40, 217, 40, 200));
    
    // black special points
    processARectangleCreationForLocation(Location.B_BAR, new Rectangle(260, 220, 36, 200));
    processARectangleCreationForLocation(Location.B_BEAR_OFF, new Rectangle(545, 12, 40, 200));
    
    // red special points
    processARectangleCreationForLocation(Location.R_BAR, new Rectangle(260, 12, 36, 200));
    processARectangleCreationForLocation(Location.R_BEAR_OFF, new Rectangle(545, 220, 40, 200));
  }
   
  private static void processARectangleCreationForLocation(Location l, Rectangle r) {
    mapLocation2Rectangle.put(l,r);
  }

  // I need to initialize the map 
  static {
    defineLocation2RectangleMap();
  }
}