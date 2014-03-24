package micropolisj.util;

import java.awt.Rectangle;

import micropolisj.engine.TileConstants;

public class Utilities {
    
    
    //first 4 bits contain playerID (1st is always 0), last 8 bits tileValue
    //allows 8 players (including neutral)
    public static short codePlayerID(int tileValue, int playerID) {
        int id = playerID << 12;
        id = (tileValue & TileConstants.LOMASK) | id;
        return (short) id;
    }
    
    public static int getPlayerID(char encodedTile) {
        return getPlayerID((short) encodedTile);
    }

    public static int getPlayerID(short encodedTile) {
        return encodedTile >> 12;
    }
    
    
    public static Rectangle moveRectangle(Rectangle rec, int dx, int dy) {
        rec.setLocation(rec.x + dx, rec.y + dy);
        return rec;
    }
    
    
//        public static void main(String[] args) {
//        System.out.println((short)codePlayerID((char)130, 2));
//        System.out.println(getPlayerID(codePlayerID(130, 7)));
//    }

        
        
      
}
