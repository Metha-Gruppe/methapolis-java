package refactored;

import java.util.List;
import java.util.Map;

import micropolisj.engine.TileBehavior;
import micropolisj.engine.TileSpec;
import micropolisj.engine.TileSpec.BuildingInfo;

public class MapObject {
	// attributes
	protected boolean bulldozable;
	protected boolean burnable;
	protected boolean conducting;
	protected boolean overWater;
	
	protected int id;
	protected int imageIndex;
	
	protected List<MapImage> images;
	protected String name;
	protected String description;
	protected TileBehavior behavior;
	protected Zone zone;
	

	public TileSpec owner;
	public int ownerOffsetX;
	public int ownerOffsetY;
	public BuildingInfo buildingInfo;
	public TileSpec animNext;
	public TileSpec onPower;
	public TileSpec onShutdown;
	public Map<String, String> attributes;

	// CONSTRUCTOR
	public MapObject() {
		// TODO: init stuff here
	}

	// GETTERS & SETTERS
	public boolean isBulldozable() {
		return this.bulldozable;
	}

	public void setBulldozable(boolean bulldozable) {
		this.bulldozable = bulldozable;
	}

	public boolean isBurnable() {
		return this.burnable;
	}

	public void setBurnable(boolean burnable) {
		this.burnable = burnable;
	}

	public boolean isConducting() {
		return this.conducting;
	}

	public void setConducting(boolean conducting) {
		this.conducting = conducting;
	}

	public boolean isOverWater() {
		return this.overWater;
	}

	public void setOverWater(boolean overWater) {
		this.overWater = overWater;
	}

	public Zone getZone() {
		return this.zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MapImage> getImages() {
		return this.images;
	}

	public void setImages(List<MapImage> images) {
		this.images = images;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImageIndex() {
		return imageIndex;
	}

	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}

	public TileBehavior getBehavior() {
		return behavior;
	}

	public void setBehavior(TileBehavior behavior) {
		this.behavior = behavior;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
