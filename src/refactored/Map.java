package refactored;


public class Map {
	private Tile[] map;
	private int width;
	private int height;
	
	
	// CONSTRUCTOR
	public Map()	{
		
	}
	
	// METHODS
	public Tile getTileAt(int x, int y)	{
		int index = width*y + x;
		if(index >= 0 && index < map.length)	{
			return map[index];			
		}
		else	{
			System.out.println("Map.java@getTileAt(int, int): invalid index");
			return null;
		}
	}

	// GETTERS & SETTERS
	public Tile[] getMap() {
		return map;
	}

	public void setMap(Tile[] map) {
		this.map = map;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
