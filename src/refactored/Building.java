package refactored;


public abstract class Building extends MapObject {
	protected int playerID;
	protected int pollution;
	protected int population;
	protected int level;
	protected Tile tile;
	
	// CONSTRUCTORS
	public Building()	{
		// short globalPlayerID = 2;
		this(2);
	}
	
	public Building(int playerID)	{
		this.playerID = playerID;
		this.level = 0;
		this.population = 0;
	}
	
	
	// METHODS
	
	// GETTERS & SETTERS
	public int getPlayerID() {
		return playerID;
	}
	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getPollution() {
		return pollution;
	}
	
	public void setPollution(int pollution) {
		this.pollution = pollution;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}
	
}
