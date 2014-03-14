package refactored;

import java.util.List;
import java.util.Map;

import micropolisj.engine.TileSpec;
import micropolisj.engine.TileSpec.BuildingInfo;

public class Tile extends MapObject {
	
	// ATTRIBUTES
	
	public TileSpec owner;
	public int ownerOffsetX;
	public int ownerOffsetY;
	
	public BuildingInfo buildingInfo;
	public TileSpec animNext;
	public TileSpec onPower;
	public TileSpec onShutdown;
	public String name;
	public Map<String, String> attributes;
	public List<String> images;
	
	// TODO: reference to building
//	Building building;
	
	// CONSTRUCTORS
	public Tile()	{
		
	}
	
	public static Tile newTileFromID(int id)	{
		// TODO: to be implemented...
		return null;
	}
	
	// METHODS
	
	
	// GETTERS & SETTERS
}
