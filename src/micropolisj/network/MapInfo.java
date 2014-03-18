package micropolisj.network;

import java.util.List;

import micropolisj.engine.Sprite;

public class MapInfo {
    
    public char[][] map;
    public List<Sprite> sprites;
    
    public MapInfo(char[][] map, List<Sprite> sprites) {
        this.map = map;
        this.sprites = sprites;
    }

}
