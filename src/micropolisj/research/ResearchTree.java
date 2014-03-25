package micropolisj.research;

import java.util.HashSet;

import micropolisj.engine.MicropolisTool;

public class ResearchTree {

	static ResearchNode[] possible_nodes;
	static int[][] needed = {
			{}, {0}, {1}, 
			{}, {3}, {4}, 
			{}, {6}, {7}, 
			{}, {9}, {10}
	};
	int height = 2, width = 2;
	static int[] positionsX = {
			0, 0, 0
		  , 200, 200, 200
		  , 400, 400, 400
		  , 600, 600, 600
	};
	static int[] positionsY = {
			0, 100, 200
		  , 0, 100, 200
		  , 0, 100, 200
		  , 0, 100, 200
	};

	public ResearchTree() {
		possible_nodes = new ResearchNode[] {
				new RocketNode(1, 150), new RocketNode(2, 200), new RocketNode(3, 250)
			  , new PoliceNode(1, 150), new PoliceNode(2, 200), new PoliceNode(3, 250)
			  , new FiremanNode(1, 150), new FiremanNode(2, 200), new FiremanNode(3, 250)
			  , new EnvironmentNode(1, 150), new EnvironmentNode(2, 200), new EnvironmentNode(3, 250)
		};
	}

	boolean isReachable(HashSet<Integer> reached_nodes, int node_id) {
		for(int nid : needed[node_id]) {
			if(!reached_nodes.contains(nid))
				return false;
		}
		return true;

	}

	// INNER CLASSES FOR UPGRADE FEATURES
	class RocketNode implements ResearchNode {

		int id=0;
		int cost=150;
		
		public RocketNode(int st, int cost){
			id = st;
			this.cost = cost;
		}
		
		@Override
		public String getDesc() {
			return "rocket research";
		}

		@Override
		public String getIcon() {
			return "/firestation.png";
		}

		@Override
		public int getCost() {
			return cost;
		}

		// @Override
		// public int getId(){return 0;}

		@Override
		public void makeChanges(ResearchState state) {
			state.rocketResearch = id;
			state.toolBtns.get(MicropolisTool.ROCKET).setVisible(true);
		}
	}
	// INNER CLASSES FOR UPGRADE FEATURES
	class PoliceNode implements ResearchNode {

		int id=0;
		int cost=150;
		
		public PoliceNode(int st, int cost){
			id = st;
			this.cost = cost;
		}
		
		@Override
		public String getDesc() {
			return "larger police station range";
		}

		@Override
		public String getIcon() {
			return "/firestation.png";
		}

		@Override
		public int getCost() {
			return cost;
		}
		@Override
		public void makeChanges(ResearchState state) {
			state.policeResearch = id;
		}
	}
	// INNER CLASSES FOR UPGRADE FEATURES
	class FiremanNode implements ResearchNode {

		int id=0;
		int cost=150;
		
		public FiremanNode(int st, int cost){
			id = st;
			this.cost = cost;
		}
		
		@Override
		public String getDesc() {
			return "larger firestation range";
		}

		@Override
		public String getIcon() {
			return "/firestation.png";
		}

		@Override
		public int getCost() {
			return cost;
		}
		@Override
		public void makeChanges(ResearchState state) {
			state.firemanResearch = id;
		}
	}
	// INNER CLASSES FOR UPGRADE FEATURES
	class EnvironmentNode implements ResearchNode {

		int id=0;
		int cost=150;
		
		public EnvironmentNode(int st, int cost){
			id = st;
			this.cost = cost;
		}
		
		@Override
		public String getDesc() {
			return "smaller polution range";
		}

		@Override
		public String getIcon() {
			return "/firestation.png";
		}

		@Override
		public int getCost() {
			return cost;
		}
		@Override
		public void makeChanges(ResearchState state) {
			state.environmentResearch = id;
		}
	}
}
