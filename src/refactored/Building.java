package refactored;


public class Building extends MapObject {
	private short playerID;
	private int pollution;
	private int population;
	private short level;
	
	// CONSTRUCTORS
	public Building()	{
		// short globalPlayerID = 2;
		this((short) 2);
	}
	
	public Building(short playerID)	{
		this.playerID = playerID;
		this.level = 0;
		this.population = 0;
	}
	
	
	// METHODS
	
	// GETTERS & SETTERS
	public short getPlayerID() {
		return playerID;
	}
	
	public void setPlayerID(short playerID) {
		this.playerID = playerID;
	}
	
	public short getLevel() {
		return level;
	}
	
	public void setLevel(short level) {
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
