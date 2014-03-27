package micropolisj.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import micropolisj.engine.Micropolis;
import micropolisj.network.ServerMicropolis;

public class SplashScreen extends JFrame {

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

	public SplashScreen() {
		super("Welcome To Methapolis");
		try {
			image = ImageIO.read(new File("graphics/splash.png"));
			jlStartNewGame = new JLabel(new ImageIcon(ImageIO.read(new File("graphics/splash_1.png"))));
			jlHostGame = new JLabel(new ImageIcon(ImageIO.read(new File("graphics/splash_2.png"))));
			jlLoginGame = new JLabel(new ImageIcon(ImageIO.read(new File("graphics/splash_3.png"))));
			jlLoadGame = new JLabel(new ImageIcon(ImageIO.read(new File("graphics/splash_4.png"))));
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		jlSplashImage = new JLabel(new ImageIcon(image));
		jlSplashImage.setBounds(0, 0, 1024, 576);
		jlStartNewGame.setBounds(0, 0, 1024, 576);
		jlHostGame.setBounds(0, 0, 1024, 576);
		jlLoginGame.setBounds(0, 0, 1024, 576);
		jlLoadGame.setBounds(0, 0, 1024, 576);

		panel = new JPanel(null, true);
		panel.setSize(1024, 576);
		getContentPane().add(panel);
		panel.add(jlSplashImage);

		panelNewGame = new JPanel(null, true);
		panelNewGame.setBounds(775, 40, 225, 55);
		HandlerNewGame handlernew = new HandlerNewGame();
		panelNewGame.addMouseListener(handlernew);
		panel.add(panelNewGame);

		panelHost = new JPanel(null, true);
		panelHost.setBounds(895, 175, 125, 40);
		HandlerHost handlerhost = new HandlerHost();
		panelHost.addMouseListener(handlerhost);
		panel.add(panelHost);

		panelLogin = new JPanel(null, true);
		panelLogin.setBounds(875, 320, 110, 45);
		HandlerLogin handlerlog = new HandlerLogin();
		panelLogin.addMouseListener(handlerlog);
		panel.add(panelLogin);

		panelLoad = new JPanel(null, true);
		panelLoad.setBounds(755, 500, 230, 55);
		HandlerLoad handlerload = new HandlerLoad();
		panelLoad.addMouseListener(handlerload);
		panel.add(panelLoad);

		final Dimension d = this.getToolkit().getScreenSize();
		// center the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 576);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);

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

	private class HandlerNewGame implements MouseListener {
		public void mouseClicked(MouseEvent event) {
			startNewGame();
		}

		public void mousePressed(MouseEvent event) {

		}

		public void mouseReleased(MouseEvent event) {

		}

		public void mouseEntered(MouseEvent event) {
			panel.remove(jlSplashImage);
			removeBackgroundPanels();
			panel.add(jlStartNewGame);
			addBackgroundPanels();
			panel.repaint();
		}

		public void mouseExited(MouseEvent event) {
			panel.remove(jlStartNewGame);
			removeBackgroundPanels();
			panel.add(jlSplashImage);
			addBackgroundPanels();
			panel.repaint();
		}
	}

	private class HandlerHost implements MouseListener {
		public void mouseClicked(MouseEvent event) {
			startNewServer();
		}

		public void mousePressed(MouseEvent event) {

		}

		public void mouseReleased(MouseEvent event) {

		}

		public void mouseEntered(MouseEvent event) {
			panel.remove(jlSplashImage);
			removeBackgroundPanels();
			panel.add(jlHostGame);
			addBackgroundPanels();
			panel.repaint();
		}

		public void mouseExited(MouseEvent event) {
			panel.remove(jlHostGame);
			removeBackgroundPanels();
			panel.add(jlSplashImage);
			addBackgroundPanels();
			panel.repaint();
		}
	}

	private class HandlerLogin implements MouseListener {
		public void mouseClicked(MouseEvent event) {
			startJoinGameScreen();
		}

		public void mousePressed(MouseEvent event) {

		}

		public void mouseReleased(MouseEvent event) {

		}

		public void mouseEntered(MouseEvent event) {
			panel.remove(jlSplashImage);
			removeBackgroundPanels();
			panel.add(jlLoginGame);
			addBackgroundPanels();
			panel.repaint();
		}

		public void mouseExited(MouseEvent event) {
			panel.remove(jlLoginGame);
			removeBackgroundPanels();
			panel.add(jlSplashImage);
			addBackgroundPanels();
			panel.repaint();
		}
	}

	private class HandlerLoad implements MouseListener {
		public void mouseClicked(MouseEvent event) {
			loadGame();
		}

		public void mousePressed(MouseEvent event) {

		}

		public void mouseReleased(MouseEvent event) {

		}

		public void mouseEntered(MouseEvent event) {
			panel.remove(jlSplashImage);
			removeBackgroundPanels();
			panel.add(jlLoadGame);
			addBackgroundPanels();
			panel.repaint();
		}

		public void mouseExited(MouseEvent event) {
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

	void startPlaying(Micropolis newEngine, File file) {

		MainWindow win = new MainWindow();
		win.setVisible(true);
		win.setEngine(newEngine);
		win.currentFile = file;
		win.makeClean();
		dispose();
	}

	private void removeBackgroundPanels() {
		panel.remove(panelNewGame);
		panel.remove(panelHost);
		panel.remove(panelLogin);
		panel.remove(panelLoad);

	}

	private void addBackgroundPanels() {
		panel.add(panelNewGame);
		panel.add(panelHost);
		panel.add(panelLogin);
		panel.add(panelLoad);
	}

}