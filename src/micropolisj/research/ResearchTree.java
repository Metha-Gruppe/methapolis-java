package micropolisj.research;

import java.io.Serializable;
import java.util.HashSet;
import java.util.ResourceBundle;

import micropolisj.engine.MapState;
import micropolisj.engine.MicropolisTool;

public class ResearchTree implements Serializable {

	int rocketNameDecider = 0;
	int policeNameDecider = 0;
	int fireNameDecider = 0;
	int pollNameDecider = 0;
	int rocketDescDecider = 0;
	int policeDescDecider = 0;
	int fireDescDecider = 0;
	int pollDescDecider = 0;
	
	static ResourceBundle strings = ResourceBundle.getBundle("micropolisj.GuiStrings");
	
	static ResearchNode[] possible_nodes;
	static int[][] needed = {
			{}, {0}, {1}, 
			{}, {3}, {4}, 
			{}, {6}, {7}, 
			{}, {9}, {10}
	};
	int height = 2, width = 2;
	static int[] positionsX = {
			25, 25, 25
		  , 290, 290, 290
		  , 555, 555, 555
		  , 820, 820, 820
	};

	static int[] positionsY = {
			25, 130, 235
		  , 25, 130, 235
		  , 25, 130, 235
		  , 25, 130, 235
	};

	//Set costs here
	public ResearchTree() {
		possible_nodes = new ResearchNode[] {
				new RocketNode(1, 200), new RocketNode(2, 350), new RocketNode(3, 500)
			  , new PoliceNode(1, 50), new PoliceNode(2, 100), new PoliceNode(3, 150)
			  , new FiremanNode(1, 50), new FiremanNode(2, 100), new FiremanNode(3, 150)
			  , new EnvironmentNode(1, 50), new EnvironmentNode(2, 100), new EnvironmentNode(3, 150)
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
		public String getName() {
			
			switch(rocketNameDecider) {
				case 0:
					rocketNameDecider ++;
					return strings.getString("research.ROCKET_T1_NAME");
	
				case 1:
					rocketNameDecider ++;
					return strings.getString("research.ROCKET_T2_NAME");
	
				case 2:
					rocketNameDecider = 0;
					return strings.getString("research.ROCKET_T3_NAME");

				default:
					throw new Error("unreachable Research String");
			}
		}
		
		@Override
		public String getDesc() {
			
			switch(rocketDescDecider) {
				case 0:
					rocketDescDecider ++;
					return strings.getString("research.ROCKET_T1_DESCRIPTION");
	
				case 1:
					rocketDescDecider ++;
					return strings.getString("research.ROCKET_T2_DESCRIPTION");
	
				case 2:
					rocketDescDecider = 0;
					return strings.getString("research.ROCKET_T3_DESCRIPTION");

				default:
					throw new Error("unreachable Research String");
			}
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
		public String getName() {
			
			switch(policeNameDecider) {
				case 0:
					policeNameDecider ++;
					return strings.getString("research.POLICE_T1_NAME");
	
				case 1:
					policeNameDecider ++;
					return strings.getString("research.POLICE_T2_NAME");
	
				case 2:
					policeNameDecider = 0;
					return strings.getString("research.POLICE_T3_NAME");

				default:
					throw new Error("unreachable Research String");
			}
		}
		
		@Override
		public String getDesc() {
			
			switch(policeDescDecider) {
				case 0:
					policeDescDecider ++;
					return strings.getString("research.POLICE_T1_DESCRIPTION");
	
				case 1:
					policeDescDecider ++;
					return strings.getString("research.POLICE_T2_DESCRIPTION");
	
				case 2:
					policeDescDecider = 0;
					return strings.getString("research.POLICE_T3_DESCRIPTION");

				default:
					throw new Error("unreachable Research String");
			}
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
		public String getName() {
			
			switch(fireNameDecider) {
				case 0:
					fireNameDecider ++;
					return strings.getString("research.FIRE_T1_NAME");
	
				case 1:
					fireNameDecider ++;
					return strings.getString("research.FIRE_T2_NAME");
	
				case 2:
					fireNameDecider = 0;
					return strings.getString("research.FIRE_T3_NAME");

				default:
					throw new Error("unreachable Research String");
			}
		}
		
		@Override
		public String getDesc() {
			
			switch(fireDescDecider) {
				case 0:
					fireDescDecider ++;
					return strings.getString("research.FIRE_T1_DESCRIPTION");
	
				case 1:
					fireDescDecider ++;
					return strings.getString("research.FIRE_T2_DESCRIPTION");
	
				case 2:
					fireDescDecider = 0;
					return strings.getString("research.FIRE_T3_DESCRIPTION");

				default:
					throw new Error("unreachable Research String");
			}
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
		public String getName() {
			
			switch(pollNameDecider) {
				case 0:
					pollNameDecider ++;
					return strings.getString("research.POLL_T1_NAME");
	
				case 1:
					pollNameDecider ++;
					return strings.getString("research.POLL_T2_NAME");
	
				case 2:
					pollNameDecider = 0;
					return strings.getString("research.POLL_T3_NAME");

				default:
					throw new Error("unreachable Research String");
			}
		}
		
		@Override
		public String getDesc() {
			
			switch(pollDescDecider) {
				case 0:
					pollDescDecider ++;
					return strings.getString("research.POLL_T1_DESCRIPTION");
	
				case 1:
					pollDescDecider ++;
					return strings.getString("research.POLL_T2_DESCRIPTION");
	
				case 2:
					pollDescDecider = 0;
					return strings.getString("research.POLL_T3_DESCRIPTION");

				default:
					throw new Error("unreachable Research String");
			}
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
