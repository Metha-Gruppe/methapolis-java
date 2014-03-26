package micropolisj.util;

import java.awt.Rectangle;

import micropolisj.engine.TileConstants;

public class Utilities {
    
    
    //bits 14, 13, 12 contain playerID (16 is always 0), 15 is power, first 8 bits tileValue
    //allows 8 players (including neutral)
    public static short codePlayerID(int tileValue, int playerID) {
        int id = playerID << 11;
        id = (tileValue & TileConstants.LOMASK) | id;
        return (short) id;
    }
    
    public static int getPlayerID(char encodedTile) {
        return getPlayerID((short) encodedTile);
    }
    
    public static int getPlayerID(int encodedTile) {
        return getPlayerID((char)encodedTile);
    }

    public static int getPlayerID(short encodedTile) {
        // set powerbit to 0
        encodedTile &= (TileConstants.PWRBIT - 1);
        return encodedTile >> 11;
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
