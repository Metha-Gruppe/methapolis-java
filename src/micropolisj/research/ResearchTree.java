package micropolisj.research;

import java.util.HashSet;

public class ResearchTree {

	static ResearchNode[] possible_nodes;
	static int[][] needed = {
			{}, {
				0
			}
	};
	int height = 2, width = 2;
	static int[] positionsX = {
			0, 120
	};
	static int[] positionsY = {
			0, 70
	};

	public ResearchTree() {
		possible_nodes = new ResearchNode[] {
				new RocketRangeNode(), new RocketDamageNode()
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
	class RocketRangeNode implements ResearchNode {

		@Override
		public String getDesc() {
			return "increase rockets range";
		}

		@Override
		public String getIcon() {
			return "/firestation.png";
		}

		@Override
		public int getCost() {
			return 15;
		}

		// @Override
		// public int getId(){return 0;}

		@Override
		public void makeChanges(ResearchState state) {
			state.rocket_speed = 2;
		}
	}

	class RocketDamageNode implements ResearchNode {

		@Override
		public String getDesc() {
			return "increase rockets damage";
		}

		@Override
		public String getIcon() {
			return "/coal.png";
		}

		@Override
		public int getCost() {
			return 20;
		}

		// @Override
		// public int getId(){return 0;}

		@Override
		public void makeChanges(ResearchState state) {
			state.rocket_speed = 3;
		}
	}
}
