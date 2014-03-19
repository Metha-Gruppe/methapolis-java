package micropolisj.research;


interface ResearchNode {
	public String getDesc();
	public String getIcon();
	//public int getId();

	//public boolean isReachable(ResearchState state);
	public void makeChanges(ResearchState state);
}
