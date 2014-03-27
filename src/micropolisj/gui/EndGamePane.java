package micropolisj.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import micropolisj.network.ServerMicropolis;

public class EndGamePane extends JFrame {
	
	MainWindow mainWindow;
	JLabel screen;


	public static void main(String[] args) {
        try {
            new EndGamePane(null, false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	
	public EndGamePane(MainWindow mainWindow, boolean gameWon) throws IOException{
		this.mainWindow = mainWindow;
		
		String img = gameWon ? "graphics/win.png" : "graphics/lose.png";
		BufferedImage image = ImageIO.read(new File(img));
        
		screen = new JLabel(new ImageIcon(image));

		screen.setSize(image.getWidth(), image.getHeight());
        add(screen);
        
//		setBounds(200, 200, 600, 600);
		
		setLayout(new BorderLayout());
		
//		JLabel endMessageLabel = new JLabel("big win!");
//		add(endMessageLabel, BorderLayout.CENTER);
		
		JPanel menu = new JPanel();
		menu.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton newGameButton = new JButton("new game");
		newGameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startNewGame();
			}
		});
		menu.add(newGameButton);
		
		JButton joinGameButton = new JButton("join game");
		joinGameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startJoinGameScreen();
			}
		});
		menu.add(joinGameButton);
		
		JButton loadGameButton = new JButton("load game");
		loadGameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loadGame();
			}
		});
		menu.add(loadGameButton);

		JButton createServerButton = new JButton("create server");
		createServerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startNewServer();
			}
		});
		menu.add(createServerButton);

		JButton continueGameButton = new JButton("continue");
		continueGameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				continueGame();
			}
		});
		menu.add(continueGameButton);
		
		add(menu, BorderLayout.SOUTH);
		
		setSize(image.getWidth(), image.getHeight() + 60);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	void closeMainWindow(){
		mainWindow.closeWindow();
		mainWindow.makeClean();
	}

	private void startJoinGameScreen() {
		closeMainWindow();
		
		new JoinGameScreen();
		closeThis();
	}

	private void startNewGame() {
		closeMainWindow();
		
		MainWindow win = new MainWindow();
		win.doNewCity(true);
		this.setVisible(false);
		win.setVisible(false);
		win.setVisible(true);
		closeThis();
	}

	private void startNewServer() {
		closeMainWindow();
		
		this.setVisible(false);
		MainWindow win = new MainWindow(new ServerMicropolis());
		win.setVisible(false);
		win.doNewCity(true);
		win.setVisible(true);
		closeThis();
	}

	private void loadGame() {
		closeMainWindow();
		
		this.setVisible(false);
		MainWindow win = new MainWindow();
		win.setVisible(false);
		win.onLoadGameClicked();
		win.setVisible(true);
		closeThis();
	}
	
	private void continueGame(){
		closeThis();
	}

	private void closeThis() {
		this.setVisible(false);
		this.dispose();
	}

}
