package micropolisj.network;

import java.io.Serializable;
import java.util.List;

import micropolisj.engine.Sprite;

public class MapInfo implements Serializable{
    
    public char[][] map;
    public int cityTime;
    
    public int gameWonID;
    
    public List<Sprite> sprites;
//    public CityBudget cityBudget;
    
    public MapInfo(char[][] map, List<Sprite> sprites, int cityTime, int gameWonID) {
        this.map = map;
        this.sprites = sprites;
        this.cityTime = cityTime;
        this.gameWonID = gameWonID;
    }
    
}
