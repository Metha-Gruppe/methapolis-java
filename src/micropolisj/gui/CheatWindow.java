package micropolisj.gui;

import java.awt.FlowLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import micropolisj.engine.Micropolis;

public class CheatWindow extends JFrame implements KeyEventDispatcher{
    
    private static final String MORE_MONEY_CHEAT = "gimme";
    private static final int AMOUNT_GIVEN = 5000;
    
    
    private static String lastInput;
    
    private Micropolis engine;
    
    private KeyboardFocusManager manager;
    
    private JTextField textField;
    
    public CheatWindow(Micropolis engine) {
        super("Let's cheat!");
        System.out.println("new CheatWindow created");
        this.engine = engine;
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
                    engine.playerInfo.budget.totalFunds += AMOUNT_GIVEN;
                    engine.fireFundsChanged();
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
