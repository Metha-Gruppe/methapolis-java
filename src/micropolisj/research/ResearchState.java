package micropolisj.research;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import micropolisj.engine.Micropolis;
import micropolisj.engine.MicropolisTool;
import micropolisj.gui.MainWindow;
import micropolisj.network.ClientMicropolis;
import micropolisj.network.PlayerInput;

public class ResearchState extends JFrame implements ActionListener, Serializable {
    private static final long serialVersionUID = 1L;
    
	static ResourceBundle strings = ResourceBundle.getBundle("micropolisj.GuiStrings"); //changeswp
	
    public int policeResearch = 0;

    public int getPoliceResearchState() {
        return policeResearch;
    }

    public double getPoliceStationRange() {
        return 1000.0 + policeResearch * 500.0;
    }

    public int fireResearch = 0;

    public int getFiremanResearchState() {
        return fireResearch;
    }

    public double getFireStationRange() {
        return 1000.0 + fireResearch * 500.0;
    }

    public int environmentResearch = 0;

    public int getEnvironmentResearchState() {
        return environmentResearch;
    }

    public int rocketResearch = 0;

    public int getRocketResearchState() {
        return rocketResearch;
    }

	int buttonWidth = 250;
	int buttonHeight = 100;
	int maxWidth = 0;
	int maxHeight = 0;

    public ResearchTree tree;// = new ResearchTree();

    HashSet<Integer> reached_nodes;// = new HashSet<Integer>();

    public int researchPoints = 0;
    
    private Micropolis city;
    
    public Map<MicropolisTool, JToggleButton> toolBtns;
	public void setToolBtns(Map<MicropolisTool, JToggleButton> toolBtns){
		if(toolBtns == null)
			System.out.println("no but");
		this.toolBtns = toolBtns;
	}
	
	JScrollPane scrollPane;
    JPanel insidePane;
    JPanel subMenu;
    JLabel researchPointsLabel;
    JButton closeButton;
    JButton[] buttons;

    // SINGLETON PATTERN
    private static ResearchState instance = null;

//    public static ResearchState getInstance() {
//        if (instance == null) {
//            return new ResearchState();
//        } else {
//            return instance;
//        }
//    }
    
    // when loading a *.cty file this method will be called
    public static ResearchState createFromResearchData(Micropolis engine, ResearchData data, Map<MicropolisTool, JToggleButton> toolBtns)	{
    	ResearchState state = new ResearchState(engine);
    	state.setToolBtns(toolBtns);
    	state.environmentResearch = data.environmentResearch;
    	state.fireResearch = data.fireResearch;
    	state.policeResearch = data.policeResearch;
    	state.rocketResearch = data.rocketResearch;
    	state.researchPoints = data.researchPoints;
    	return state;
    }
    
    public static ResearchState createFromResearchData(Micropolis engine, ResearchData data)	{
    	ResearchState state = new ResearchState(engine);
    	state.environmentResearch = data.environmentResearch;
    	state.fireResearch = data.fireResearch;
    	state.policeResearch = data.policeResearch;
    	state.rocketResearch = data.rocketResearch;
    	state.researchPoints = data.researchPoints;
    	return state;
    }


	public ResearchData getResearchData() {
        ResearchData data = new ResearchData();
        data.environmentResearch = environmentResearch;
        data.fireResearch = fireResearch;
        data.policeResearch = policeResearch;
        data.rocketResearch = rocketResearch;
        data.researchPoints = researchPoints;
        return data;
    }

    // TODO Separate Tree from Frame!!!
    public ResearchState(Micropolis city) {
        this(city, new ResearchTree());
    }
	
	// CONSTRUCTOR
	public ResearchState(Micropolis engine, ResearchTree tree) {
		city = engine;
        this.tree = tree;
		
		reached_nodes = new HashSet<Integer>();
		
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

		researchPointsLabel = new JLabel(Integer.toString(researchPoints) + " " + strings.getString("research.POINTS_NAME") + " ");
		subMenu.add(researchPointsLabel, BorderLayout.EAST);
		
		JPanel ppanel = new JPanel();
		ppanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		subMenu.add(ppanel, BorderLayout.CENTER);

		closeButton = new JButton(strings.getString("research.CLOSE_BUTTON"));
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		ppanel.add(closeButton);
		
		int n = ResearchTree.possible_nodes.length;
		buttons = new JButton[n];

		for(int node_id = 0; node_id < n; node_id++) {
			ResearchNode node = ResearchTree.possible_nodes[node_id];

			buttons[node_id] = new JButton();
			buttons[node_id].setBounds(new Rectangle(ResearchTree.positionsX[node_id], ResearchTree.positionsY[node_id],
					buttonWidth, buttonHeight));
			
			buttons[node_id].setActionCommand(Integer.toString(node_id));
			buttons[node_id].addActionListener(this);
			

			maxWidth = Math.max(maxWidth, ResearchTree.positionsX[node_id] + buttonWidth);
			maxHeight = Math.max(maxHeight, ResearchTree.positionsY[node_id] + buttonHeight);

			insidePane.add(buttons[node_id]);
		}

		insidePane.setPreferredSize(new Dimension(maxWidth, maxHeight));

		refreshPanel();

		setVisible(false);
	}
	
	public Micropolis getCity() {
		return city;
	}
	
/*
    // CONSTRUCTOR
    public ResearchState(Micropolis engine, ResearchTree tree) {
        city = engine;
        this.tree = tree;
        reached_nodes = new HashSet<Integer>();

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

        JPanel ppanel = new JPanel();
        ppanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        subMenu.add(ppanel, BorderLayout.CENTER);

        closeButton = new JButton("close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });
        ppanel.add(closeButton);

        int n = ResearchTree.possible_nodes.length;
        buttons = new JButton[n];

        for (int node_id = 0; node_id < n; node_id++) {
            ResearchNode node = ResearchTree.possible_nodes[node_id];

            buttons[node_id] = new JButton("<html>" + node.getDesc() + "</html>");
            buttons[node_id].setBounds(new Rectangle(ResearchTree.positionsX[node_id], ResearchTree.positionsY[node_id], buttonWidth, buttonHeight));

            buttons[node_id].setActionCommand(Integer.toString(node_id));
            buttons[node_id].addActionListener(this);

            maxWidth = Math.max(maxWidth, ResearchTree.positionsX[node_id] + buttonWidth);
            maxHeight = Math.max(maxHeight, ResearchTree.positionsY[node_id] + buttonHeight);

            insidePane.add(buttons[node_id]);
        }

        insidePane.setPreferredSize(new Dimension(maxWidth, maxHeight));

        refreshPanel();

        setVisible(false);
    }
*/
    public void showResearchPanel() {
		setBounds(200, 200, maxWidth + 40, maxHeight + 100); //changeswp

        refreshPanel();

        setVisible(true);
    }
    
    private void refreshNodesFromResearchData() {
    	ResearchData researchData = getResearchData();

    	// the order has to match the order in ResearchTree.nodesByType
    	int[] progress = { researchData.rocketResearch, researchData.policeResearch, researchData.fireResearch, researchData.environmentResearch };
    	
    	// reset progress
    	reached_nodes.clear();
    	
    	// go through research types
    	for(int type = 0; type < progress.length; type++) {
    		// add progress to reached_nodes
    		for(int prog = 0; prog < progress[type]; prog++) {
    			reached_nodes.add(ResearchTree.indicesByType[type][prog]);
    		}
    	}
    }
    
    public void printReached() {
    	for(Integer i : reached_nodes) {
    		System.out.println(i);
    	}
    }

    public void refreshPanel() {    	
    	refreshNodesFromResearchData();
    	
    	printReached();
    	
    	getResearchData().print();

		researchPointsLabel.setText(Integer.toString(researchPoints) + " " + strings.getString("research.POINTS_NAME") + " ");

		for(int node_id = 0; node_id < ResearchTree.possible_nodes.length; node_id++) {
			ResearchNode node = ResearchTree.possible_nodes[node_id];

			// reached
			if(reached_nodes.contains(node_id)) {
				buttons[node_id].setEnabled(false);
				buttons[node_id].setText("<html>" + node.getName() + node.getDesc() +"</html>");
				buttons[node_id].setBackground(Color.blue);
			}
			// not reachable
			else if(!tree.isReachable(reached_nodes, node_id)) {
				buttons[node_id].setEnabled(false);
				buttons[node_id].setText("<html>"+node.getName() + node.getDesc() + "</html>");
			}
			// reachable
			else {
				buttons[node_id].setEnabled(true);
				buttons[node_id].setText("<html>" + node.getName() + " (" + Integer.toString(node.getCost()) + "pts)" + node.getDesc() +"</html>");
			}
		}
		
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        int node_id = Integer.parseInt(ev.getActionCommand());
        ResearchNode node = ResearchTree.possible_nodes[node_id];
        int cost = node.getCost();

        if (researchPoints >= cost) {

            reached_nodes.add(node_id);
            node.makeChanges(this);

            researchPoints -= cost;

            ResearchData researchData = getResearchData();

            refreshPanel();
            
            // update player info
            city.getPlayerInfo().researchData = researchData;
            
//            ResearchData researchData = engine.getPlayerInfo().researchData;
//        	researchData.researchPoints += AMOUNT_RESEARCH;
        	
            if(city instanceof ClientMicropolis) {
            	PlayerInput input = new PlayerInput(null);
            	input.setResearchData(researchData);
            	((ClientMicropolis) city).getRemote().sendInput(input);
            }
            
            
        } else {
			JOptionPane.showMessageDialog(this, strings.getString("research.POINTS_MISSING"));
        }
	}
}
