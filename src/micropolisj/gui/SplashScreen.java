package micropolisj.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * screen to appear before game starts allowing user to choose several starting options
 * @author nikolaibobenko
 * TODO rearrange buttons - LAYOUTMANAGER - buttons below picture
 * TODO fix pictureLoadingIssue
 * TODO add button functionalities
 */
public class SplashScreen extends JFrame implements ActionListener{
    
    private JButton newGameBtn;
    private JButton loadGameBtn;
    private JButton createServerBtn;
    private JButton loginBtn;
    private JPanel imgPanel;
    
    private static final String NEW_GAME_COMMAND = "newGame";
    private static final String LOAD_GAME_COMMAND = "loadGame";
    
    
    public SplashScreen(){
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            final BufferedImage img = ImageIO.read(new File("graphics/Metha-Science.png"));
        imgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        };
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(imgPanel);
        //TODO replace Strings with Resourcebundle-Strings 
        newGameBtn = new JButton("New Game");
        loadGameBtn = new JButton("Load Game");
        createServerBtn = new JButton("Create Server");
        loginBtn = new JButton("Login");
        add(newGameBtn);
        add(loadGameBtn);
        add(createServerBtn);
        add(loginBtn);
        
        setLayout(new FlowLayout());
        
        newGameBtn.addActionListener(this);
        newGameBtn.setActionCommand(NEW_GAME_COMMAND);
        loadGameBtn.addActionListener(this);
        loadGameBtn.setActionCommand(LOAD_GAME_COMMAND);
        pack();
        //center window
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    //TODO add other button functionalities
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
        case NEW_GAME_COMMAND:
            startNewGame();
            break;
        case LOAD_GAME_COMMAND:
            loadGame();
            break;
        default:
            return;
        }
    }
    
    private void startNewGame() {
        this.setVisible(false);
        MainWindow win = new MainWindow();
        win.setVisible(false);
        win.doNewCity(true);
        win.setVisible(true);
        closeThis();
    }
    
    private void loadGame() {
        this.setVisible(false);
        MainWindow win = new MainWindow();
        win.setVisible(false);
        win.onLoadGameClicked();
        win.setVisible(true);
        closeThis();
    }
    
    private void closeThis() {
        this.setVisible(false);
        this.dispose();
    }
    
}
