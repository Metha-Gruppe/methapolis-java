package refactored;

import java.util.List;

/**
 * This class wraps one or multiple buildings
 */
public class Zone {
	public static final short RESIDENTIAL = 1;
	public static final short INDUSTRIAL = 2;
	public static final short COMMERCIAL = 3;
	public static final short POLICE = 4;
	public static final short FIRESTATION = 5;
	// TODO: to be continued...
	
	
	// ATTRIBUTES
	private short type;
	private short width;
	private short height;
	private short x;
	private short y;
	private List<Building> buildings;
	
	// CONSTRUCTORS
	public Zone(short type, short x, short y)	{
		this(type, x, y, (short) 3, (short) 3);
	}
	
	public Zone(short type, short x, short y, short width, short height)	{
		this.type = type;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// GETTERS & SETTERS
	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getWidth() {
		return width;
	}

	public void setWidth(short width) {
		this.width = width;
	}

	public short getHeight() {
		return height;
	}

	public void setHeight(short height) {
		this.height = height;
	}

	public short getX() {
		return x;
	}

	public void setX(short x) {
		this.x = x;
	}

	public short getY() {
		return y;
	}

	public void setY(short y) {
		this.y = y;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}
	
}
