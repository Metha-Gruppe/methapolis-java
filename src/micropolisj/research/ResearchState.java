package micropolisj.research;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class ResearchState extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	int policeResearch = 0;
	int getPoliceResearchState(){return policeResearch;}
	double getPoliceStationRange(){
		return 1000.0 + policeResearch* 75.0;
	}

	int firemanResearch = 0;
	int getFiremanResearchState(){return firemanResearch;}

	int environmentResearch = 0;
	int getEnvironmentResearchState(){return environmentResearch;}

	int rocketResearch = 0;
	int getRocketResearchState(){return rocketResearch;}

	ResearchTree tree = new ResearchTree();

	HashSet<Integer> reached_nodes = new HashSet<Integer>();

	int researchPoints = 300;

	int buttonWidth = 150;
	int buttonHeight = 75;
	int maxWidth = 0;
	int maxHeight = 0;

	JScrollPane scrollPane;
	JPanel insidePane;
	JPanel subMenu;
	JLabel researchPointsLabel;
	JButton closeButton;
	JButton[] buttons;
	
	// SINGLETON PATTERN
	private static ResearchState instance = null;
	public ResearchState getInstance()	{
		if(instance == null)	{
			return new ResearchState();
		}
		else	{
			return instance;
		}
	}

	// CONSTRUCTOR
	public ResearchState() {
		setName("Research Tree");
		setLayout(new BorderLayout());

		insidePane = new JPanel();
		insidePane.setLayout(null);

		scrollPane = new JScrollPane(insidePane);
		// scrollPane.setLayout(null);
		add(scrollPane, BorderLayout.CENTER);

		subMenu = new JPanel();
		subMenu.setLayout(new BorderLayout());
		add(subMenu, BorderLayout.SOUTH);

		researchPointsLabel = new JLabel(Integer.toString(researchPoints) + " research points");
		subMenu.add(researchPointsLabel, BorderLayout.EAST);

		closeButton = new JButton("close");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		subMenu.add(closeButton);

		int n = ResearchTree.possible_nodes.length;
		buttons = new JButton[n];

		for(int node_id = 0; node_id < n; node_id++) {
			ResearchNode node = ResearchTree.possible_nodes[node_id];

			buttons[node_id] = new JButton("<html>" + node.getDesc() + "</html>");
			buttons[node_id].setBounds(new Rectangle(ResearchTree.positionsX[node_id], ResearchTree.positionsY[node_id],
					buttonWidth, buttonHeight));

			maxWidth = Math.max(maxWidth, ResearchTree.positionsX[node_id] + buttonWidth);
			maxHeight = Math.max(maxHeight, ResearchTree.positionsY[node_id] + buttonHeight);

			insidePane.add(buttons[node_id]);
		}

		insidePane.setPreferredSize(new Dimension(maxWidth, maxHeight));

		refreshPanel();

		setVisible(false);
	}

	public void showResearchPanel() {
		setBounds(200, 200, maxWidth + 10, maxHeight + 100);

		setVisible(true);
	}

	private void refreshPanel() {

		researchPointsLabel.setText(Integer.toString(researchPoints) + " research points");

		for(int node_id = 0; node_id < ResearchTree.possible_nodes.length; node_id++) {
			ResearchNode node = ResearchTree.possible_nodes[node_id];

			if(reached_nodes.contains(node_id)) {
				buttons[node_id].setEnabled(false);
				buttons[node_id].setText("<html>"+node.getDesc()+"</html>");
				buttons[node_id].setBackground(Color.red);
			}
			else if(!tree.isReachable(reached_nodes, node_id)) {
				buttons[node_id].setEnabled(false);
				buttons[node_id].setText("<html>"+node.getDesc()+"</html>");
			}
			else {
				System.out.println(node_id);

				buttons[node_id].setActionCommand(Integer.toString(node_id));
				buttons[node_id].setEnabled(true);
				buttons[node_id].setText("<html>"+node.getDesc() + " (" + Integer.toString(node.getCost()) + "pts)</html>");
				buttons[node_id].addActionListener(this);
			}
		}

		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		int node_id = Integer.parseInt(ev.getActionCommand());
		ResearchNode node = ResearchTree.possible_nodes[node_id];
		int cost = node.getCost();

		if(researchPoints >= cost) {

			reached_nodes.add(node_id);
			node.makeChanges(this);

			researchPoints -= cost;

			refreshPanel();
		}
		else {
			JOptionPane.showMessageDialog(this, "You don't have enough research points for this upgrade.");
		}
	}
}
