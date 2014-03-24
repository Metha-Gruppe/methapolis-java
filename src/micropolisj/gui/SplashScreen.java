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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import sun.net.www.content.audio.x_aiff;
import micropolisj.network.ServerMicropolis;

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
    private static final String START_NEW_SERVER_COMMAND = "newServer";
    private static final String JOIN_COMMAND = "join Server";
    
    
    public SplashScreen(){
        super("Welcome To Methapolis");
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
        add(imgPanel);
        //TODO replace Strings with Resourcebundle-Strings 
        newGameBtn = new JButton("New Game");
        loadGameBtn = new JButton("Load Game");
        createServerBtn = new JButton("Create Server");
        loginBtn = new JButton("Login");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newGameBtn);
        buttonPanel.add(loadGameBtn);
        buttonPanel.add(createServerBtn);
        buttonPanel.add(loginBtn);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        add(buttonPanel);
        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        newGameBtn.addActionListener(this);
        newGameBtn.setActionCommand(NEW_GAME_COMMAND);
        loadGameBtn.addActionListener(this);
        loadGameBtn.setActionCommand(LOAD_GAME_COMMAND);
        createServerBtn.addActionListener(this);
        createServerBtn.setActionCommand(START_NEW_SERVER_COMMAND);
        loginBtn.addActionListener(this);
        loginBtn.setActionCommand(JOIN_COMMAND);
        setSize(img.getWidth(), img.getHeight() + buttonPanel.getHeight());
        //center window
        setLocationRelativeTo(null);
        setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        case START_NEW_SERVER_COMMAND:
            startNewServer();
            break;
        case JOIN_COMMAND:
            startJoinGameScreen();
            break;
        default:
            return;
        }
    }
    
    private void startJoinGameScreen() {
        JoinGameScreen joinScr = new JoinGameScreen();
        closeThis();
    }

    private void startNewGame() {
        MainWindow win = new MainWindow();
        win.doNewCity(true);
        this.setVisible(false);
        win.setVisible(false);
        win.setVisible(true);
        closeThis();
    }
    
    private void startNewServer() {
        this.setVisible(false);
        MainWindow win = new MainWindow(new ServerMicropolis());
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
