package micropolisj.network;

import java.io.Serializable;
import java.util.List;

import micropolisj.engine.CityBudget;
import micropolisj.engine.Sprite;

public class MapInfo implements Serializable{
    
    public char[][] map;
    public int cityTime;
    
    public List<Sprite> sprites;
//    public CityBudget cityBudget;
    
    public MapInfo(char[][] map, List<Sprite> sprites, int cityTime) {
        this.map = map;
        this.sprites = sprites;
        this.cityTime = cityTime;
    }
    
}
