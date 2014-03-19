package micropolisj.research;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.HashSet;

import javax.swing.*;

import micropolisj.research.ResearchNode;

public class ResearchTree {

	static ResearchNode[] possible_nodes;// = {new RocketRangeNode()};
	static int [][] needed = {{}, {0}};
	int height = 2, width = 2;
	static int [] positionsX = {0, 120};
	static int [] positionsY = {0, 70};
	
	ResearchTree(){
		possible_nodes = new ResearchNode[]{new RocketRangeNode(), new RocketDamageNode()};
	}
	
	boolean isReachable(HashSet<Integer> reached_nodes, int node_id){
		for(int nid : needed[node_id]){
			if(!reached_nodes.contains(nid))
				return false;
		}
		return true;
			
	}

	class RocketRangeNode implements ResearchNode {

		@Override
		public String getDesc() {return "increase rockets range";}

		@Override
		public String getIcon() {return "/firestation.png";}
		//@Override
		//public int getId(){return 0;}

		@Override
		public void makeChanges(ResearchState state) {
			state.rocket_speed = 2;
		}
	}

	class RocketDamageNode implements ResearchNode {

		@Override
		public String getDesc() {return "increase rockets damage";}

		@Override
		public String getIcon() {return "/coal.png";}
		//@Override
		//public int getId(){return 0;}

		@Override
		public void makeChanges(ResearchState state) {
			state.rocket_speed = 3;
		}
	}
}
