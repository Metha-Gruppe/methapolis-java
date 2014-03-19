package micropolisj.research;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class ResearchState extends JFrame implements ActionListener {
	
	int rocket_speed = 1;
	
	ResearchTree tree = new ResearchTree();
	
	HashSet<Integer> reached_nodes = new HashSet<Integer>();
	
	int researchPoints = 0;
	
	int buttonWidth = 150;
	int buttonHeight = 75;
	int maxWidth = 0;
	int maxHeight = 0;
	JButton [] buttons;

	public ResearchState(){
		setLayout(null);
		
		int n = tree.possible_nodes.length;
		buttons = new JButton[n];
		
		for(int node_id=0;node_id < n;node_id++){
			ResearchNode node = tree.possible_nodes[node_id];
			
			buttons[node_id] = new JButton("<html>"+node.getDesc()+"</html>");
			buttons[node_id].setBounds(new Rectangle(tree.positionsX[node_id], tree.positionsY[node_id], 100, 50));

			maxWidth = Math.max(maxWidth, tree.positionsX[node_id] + buttonWidth);
			maxHeight = Math.max(maxHeight, tree.positionsY[node_id] + buttonHeight);
			
			add(buttons[node_id]);
		}
		
		refreshPanel();
		
		setVisible(false);
	}
	
	public void showResearchPanel(){
		setBounds(200, 200, maxWidth, maxHeight + 200);
		
		setVisible(true);
	}
	
	private void refreshPanel(){
		
		for(int node_id=0;node_id < tree.possible_nodes.length;node_id++){
			ResearchNode node = tree.possible_nodes[node_id];
			
			if(reached_nodes.contains(node_id)){
				buttons[node_id].setEnabled(false);
				buttons[node_id].setBackground(Color.red);
			}else if(!tree.isReachable(reached_nodes, node_id)){
				buttons[node_id].setEnabled(false);
			}else{
				System.out.println(node_id);
				
				buttons[node_id].setActionCommand(Integer.toString(node_id));
				buttons[node_id].setEnabled(true);
				buttons[node_id].addActionListener(this);
			}
		}
		
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		int node_id = Integer.parseInt(ev.getActionCommand());
		
		System.out.println(ev.getActionCommand());
		
		reached_nodes.add(node_id);
		
		refreshPanel();
	}
}
