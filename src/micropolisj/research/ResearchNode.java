package micropolisj.research;

public interface ResearchNode {
	
	public String getName(); //changeswp

	public String getDesc();

	public String getIcon();

	public int getCost();

	// public int getId();

	// public boolean isReachable(ResearchState state);
	
	public void makeChanges(ResearchState state);

}
