package micropolisj.gui;

import java.awt.FlowLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

import micropolisj.engine.CityBudget;
import micropolisj.engine.Micropolis;
import micropolisj.engine.MicropolisTool;
import micropolisj.network.ClientMicropolis;
import micropolisj.network.PlayerInput;
import micropolisj.research.ResearchData;
import micropolisj.research.ResearchState;

public class CheatWindow extends JFrame implements KeyEventDispatcher{
    
    private static final String MORE_MONEY_CHEAT = "gimme";
    private static final int AMOUNT_GIVEN = 100000;
    
    private static final String MORE_RESEARCH_CHEAT = "research";
    private static final int AMOUNT_RESEARCH = 100000;
    
    
    private static String lastInput;
    
    private Micropolis engine;
    
    private KeyboardFocusManager manager;
    
    private JTextField textField;
    
    private Map<MicropolisTool, JToggleButton> toolBtns;
    
    public CheatWindow(Micropolis engine, Map<MicropolisTool, JToggleButton> toolBtns) {
        super("Let's cheat!");
        this.engine = engine;
        this.toolBtns = toolBtns;
        textField = new JTextField(10);
        textField.setText(lastInput);
        add(textField);
        setLayout(new FlowLayout());
        pack();
        setLocationRelativeTo(null);
        manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_PRESSED) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                if(textField.getText().equals(MORE_MONEY_CHEAT)) {
                	CityBudget budget = engine.getPlayerInfo().budget;
                    
                	budget.totalFunds += AMOUNT_GIVEN;
                    
                    if(engine instanceof ClientMicropolis) {
                    	PlayerInput input = new PlayerInput(null);
                    	input.setTotalBudget(budget.totalFunds);
                    	((ClientMicropolis) engine).getRemote().sendInput(input);
                    }
                    
                    engine.fireFundsChanged();
                }
                if(textField.getText().equals(MORE_RESEARCH_CHEAT)) {
                	ResearchData researchData = engine.getPlayerInfo().researchData;
                	researchData.researchPoints += AMOUNT_RESEARCH;
                	
                    if(engine instanceof ClientMicropolis) {
                    	PlayerInput input = new PlayerInput(null);
                    	input.setResearchData(researchData);
                    	((ClientMicropolis) engine).getRemote().sendInput(input);
                    }
                    engine.getPlayerInfo().researchState = ResearchState.createFromResearchData(engine, researchData, toolBtns);
                }
                lastInput = textField.getText();
                manager.removeKeyEventDispatcher(this);
                this.dispose();
                return true;
            }
        }
        return false;
    }
    
}
