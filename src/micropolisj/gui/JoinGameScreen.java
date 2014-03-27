package micropolisj.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import micropolisj.network.ClientMicropolis;

public class JoinGameScreen extends JFrame implements ActionListener {
	private JButton loginBtn;
	private JTextField textfield;

	public JoinGameScreen() {
		super("Join game");

		textfield = new JTextField("IPv4 address");
		loginBtn = new JButton("Login");

		add(textfield);
		add(loginBtn);

		setLayout(new FlowLayout());

		textfield.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					joinGame();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					joinGame();
				}
			}
		});
		loginBtn.addActionListener(this);
		pack();

		// center window
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		joinGame();
	}

	private void joinGame() {
		setVisible(false);
		try {
			MainWindow win = new MainWindow(new ClientMicropolis(textfield.getText()));
			win.setVisible(true);
		}
		catch(RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeThis();
	}

	private void closeThis() {
		setVisible(false);
		dispose();
	}
}
