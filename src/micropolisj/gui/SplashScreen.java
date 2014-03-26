
package micropolisj.gui;

import static micropolisj.gui.MainWindow.EXTENSION;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

import micropolisj.Main;
import micropolisj.gui.*;
import micropolisj.network.ServerMicropolis;
import micropolisj.engine.Micropolis;

import javax.swing.JDialog;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Dimension;

public class SplashScreen extends JFrame{
	
	JPanel panel;
	JPanel panelNewGame;
	JPanel panelHost;
	JPanel panelLogin;
	JPanel panelLoad;
	BufferedImage image;
	JLabel jlSplashImage;
	JLabel jlStartNewGame;
	JLabel jlHostGame;
	JLabel jlLoginGame;
	JLabel jlLoadGame;
	
	
	static final ResourceBundle strings = MainWindow.strings;
	
	public SplashScreen(){
		super("Welcome To Methapolis");
		try{
			image = ImageIO.read(new File("graphics/splash.png"));
			jlStartNewGame = new JLabel(new ImageIcon(ImageIO.read(new File("graphics/splash_1.png"))));
			jlHostGame = new JLabel(new ImageIcon(ImageIO.read(new File("graphics/splash_2.png"))));
			jlLoginGame = new JLabel(new ImageIcon(ImageIO.read(new File("graphics/splash_3.png"))));
			jlLoadGame = new JLabel(new ImageIcon(ImageIO.read(new File("graphics/splash_4.png"))));
		} catch(IOException e){
			e.printStackTrace();
		}

		jlSplashImage = new JLabel(new ImageIcon(image));
		jlSplashImage.setBounds(0,0,1024,576);
		jlStartNewGame.setBounds(0,0,1024,576);
		jlHostGame.setBounds(0,0,1024,576);
		jlLoginGame.setBounds(0,0,1024,576);
		jlLoadGame.setBounds(0,0,1024,576);
				
				
		panel  = new JPanel(null,true);
		panel.setSize(1024,576);
		getContentPane().add(panel);
		panel.add(jlSplashImage);
		
		panelNewGame = new JPanel(null,true);
		panelNewGame.setBounds(775,40,225,55);
		HandlerNewGame handlernew = new HandlerNewGame();
		panelNewGame.addMouseListener(handlernew);
		panel.add(panelNewGame);
		
		panelHost = new JPanel(null,true);
		panelHost.setBounds(895,175,125,40);
		HandlerHost handlerhost = new HandlerHost();
		panelHost.addMouseListener(handlerhost);
		panel.add(panelHost);
		
		panelLogin = new JPanel(null,true);
		panelLogin.setBounds(875,320,110,45);
		HandlerLogin handlerlog = new HandlerLogin();
		panelLogin.addMouseListener(handlerlog);
		panel.add(panelLogin);
		
		panelLoad = new JPanel(null,true);
		panelLoad.setBounds(755,500,230,55);
		HandlerLoad handlerload = new HandlerLoad();
		panelLoad.addMouseListener(handlerload);
		panel.add(panelLoad);

		final Dimension d = this.getToolkit().getScreenSize(); 
		this.setLocation((int) (((d.getWidth() - this.getWidth()) / 2)-512), (int) (((d.getHeight() - this.getHeight()) / 2)-300));

	}
	
	private void startJoinGameScreen() {
	  new JoinGameScreen();
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
	
	private class HandlerNewGame implements MouseListener{
		public void mouseClicked(MouseEvent event){
			startNewGame();
		}
		public void mousePressed(MouseEvent event){
			
		}
		public void mouseReleased(MouseEvent event){
			
		}
		public void mouseEntered(MouseEvent event){
			panel.remove(jlSplashImage);
			removeBackgroundPanels();
			panel.add(jlStartNewGame);
			addBackgroundPanels();
			panel.repaint();
		}
		public void mouseExited(MouseEvent event){
			panel.remove(jlStartNewGame);
			removeBackgroundPanels();
			panel.add(jlSplashImage);
			addBackgroundPanels();
			panel.repaint();
		}
	}
	
	private class HandlerHost implements MouseListener{
		public void mouseClicked(MouseEvent event){
			startNewServer();
		}
		public void mousePressed(MouseEvent event){
			
		}
		public void mouseReleased(MouseEvent event){
			
		}
		public void mouseEntered(MouseEvent event){
			panel.remove(jlSplashImage);
			removeBackgroundPanels();
			panel.add(jlHostGame);
			addBackgroundPanels();
			panel.repaint();
		}
		public void mouseExited(MouseEvent event){
			panel.remove(jlHostGame);
			removeBackgroundPanels();
			panel.add(jlSplashImage);
			addBackgroundPanels();
			panel.repaint();
		}
	}
	
	private class HandlerLogin implements MouseListener{
		public void mouseClicked(MouseEvent event){
			startJoinGameScreen();
		}
		public void mousePressed(MouseEvent event){
			
		}
		public void mouseReleased(MouseEvent event){
			
		}
		public void mouseEntered(MouseEvent event){
			panel.remove(jlSplashImage);
			removeBackgroundPanels();
			panel.add(jlLoginGame);
			addBackgroundPanels();
			panel.repaint();
		}
		public void mouseExited(MouseEvent event){
			panel.remove(jlLoginGame);
			removeBackgroundPanels();
			panel.add(jlSplashImage);
			addBackgroundPanels();
			panel.repaint();
		}
	}
	
	private class HandlerLoad implements MouseListener{
		public void mouseClicked(MouseEvent event){
			loadGame();
		}
		public void mousePressed(MouseEvent event){
			
		}
		public void mouseReleased(MouseEvent event){
			
		}
		public void mouseEntered(MouseEvent event){
			panel.remove(jlSplashImage);
			removeBackgroundPanels();
			panel.add(jlLoadGame);
			addBackgroundPanels();
			panel.repaint();
		}
		public void mouseExited(MouseEvent event){
			panel.remove(jlLoadGame);
			removeBackgroundPanels();
			panel.add(jlSplashImage);
			addBackgroundPanels();
			panel.repaint();
		}
	}
	

	
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(image, 0, 0, null);           
    }
	
	
	void startPlaying(Micropolis newEngine, File file)
	{
		
		MainWindow win = new MainWindow();
		win.setVisible(true);
		win.setEngine(newEngine);
		win.currentFile = file;
		win.makeClean();
		dispose();
	}
	private void removeBackgroundPanels(){
		panel.remove(panelNewGame);
		panel.remove(panelHost);
		panel.remove(panelLogin);
		panel.remove(panelLoad);
		
	}
	private void addBackgroundPanels(){
		panel.add(panelNewGame);
		panel.add(panelHost);
		panel.add(panelLogin);
		panel.add(panelLoad);
	}


}

//package micropolisj.gui;
//
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Graphics;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.WindowConstants;
//
//import sun.net.www.content.audio.x_aiff;
//import micropolisj.network.ServerMicropolis;
//
///**
// * screen to appear before game starts allowing user to choose several starting options
// * @author nikolaibobenko
// * TODO rearrange buttons - LAYOUTMANAGER - buttons below picture
// * TODO fix pictureLoadingIssue
// * TODO add button functionalities
// */
//public class SplashScreen extends JFrame implements ActionListener{
//    
//    private JButton newGameBtn;
//    private JButton loadGameBtn;
//    private JButton createServerBtn;
//    private JButton loginBtn;
//    private JPanel imgPanel;
//    
//    private static final String NEW_GAME_COMMAND = "newGame";
//    private static final String LOAD_GAME_COMMAND = "loadGame";
//    private static final String START_NEW_SERVER_COMMAND = "newServer";
//    private static final String JOIN_COMMAND = "join Server";
//    
//    
//    public SplashScreen(){
//        super("Welcome To Methapolis");
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        try {
//            final BufferedImage img = ImageIO.read(new File("graphics/Metha-Science.png"));
//        imgPanel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(img, 0, 0, null);
//            }
//        };
//        add(imgPanel);
//        //TODO replace Strings with Resourcebundle-Strings 
//        newGameBtn = new JButton("New Game");
//        loadGameBtn = new JButton("Load Game");
//        createServerBtn = new JButton("Create Server");
//        loginBtn = new JButton("Login");
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(newGameBtn);
//        buttonPanel.add(loadGameBtn);
//        buttonPanel.add(createServerBtn);
//        buttonPanel.add(loginBtn);
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
//        add(buttonPanel);
//        
//        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
//        
//        newGameBtn.addActionListener(this);
//        newGameBtn.setActionCommand(NEW_GAME_COMMAND);
//        loadGameBtn.addActionListener(this);
//        loadGameBtn.setActionCommand(LOAD_GAME_COMMAND);
//        createServerBtn.addActionListener(this);
//        createServerBtn.setActionCommand(START_NEW_SERVER_COMMAND);
//        loginBtn.addActionListener(this);
//        loginBtn.setActionCommand(JOIN_COMMAND);
//        setSize(img.getWidth(), img.getHeight() + buttonPanel.getHeight());
//        //center window
//        setLocationRelativeTo(null);
//        setVisible(true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //TODO add other button functionalities
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        switch(e.getActionCommand()) {
//        case NEW_GAME_COMMAND:
//            startNewGame();
//            break;
//        case LOAD_GAME_COMMAND:
//            loadGame();
//            break;
//        case START_NEW_SERVER_COMMAND:
//            startNewServer();
//            break;
//        case JOIN_COMMAND:
//            startJoinGameScreen();
//            break;
//        default:
//            return;
//        }
//    }
//    
//    private void startJoinGameScreen() {
//        JoinGameScreen joinScr = new JoinGameScreen();
//        closeThis();
//    }
//
//    private void startNewGame() {
//        MainWindow win = new MainWindow();
//        win.doNewCity(true);
//        this.setVisible(false);
//        win.setVisible(false);
//        win.setVisible(true);
//        closeThis();
//    }
//    
//    private void startNewServer() {
//        this.setVisible(false);
//        MainWindow win = new MainWindow(new ServerMicropolis());
//        win.setVisible(false);
//        win.doNewCity(true);
//        win.setVisible(true);
//        closeThis();
//    }
//    
//    private void loadGame() {
//        this.setVisible(false);
//        MainWindow win = new MainWindow();
//        win.setVisible(false);
//        win.onLoadGameClicked();
//        win.setVisible(true);
//        closeThis();
//    }
//    
//    private void closeThis() {
//        this.setVisible(false);
//        this.dispose();
//    }
//    
//}
