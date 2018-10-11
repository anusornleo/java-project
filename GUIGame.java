import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class GUIGame implements ActionListener,KeyListener{

	private JFrame fr;
	private JTextField tf,tf1;
	private JButton btn;
	private int point = 0;

	public void GameGUI() {
		MainClass set = new MainClass();
		set.setLet('a');
		fr = new JFrame("Test Game");
		tf = new JTextField(3);
		tf1 = new JTextField(3);
		tf1.setBounds ( 0, 0, 20, 100 );
		tf.setBounds ( 0, 100, 400, 100 );
		
		tf.setEditable(false);
		tf1.setEditable(false);
		tf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font font1 = new Font("SansSerif", Font.BOLD, 120);
		tf.setFont(font1);
		tf.requestFocus();
		btn = new JButton("Exit");
		btn.setBounds ( 0, 200, 400, 100 );
		btn.setFont(font1);
		//fr.setLayout(new FlowLayout());
		//fr.setExtendedState(JFrame.MAXIMIZED_BOTH); //set full screen
		//fr.setUndecorated(true); //set full screen
		fr.setSize(400,400);
		fr.setVisible(true);
		fr.setLayout(null);
		fr.add(tf1);
		fr.add(tf);
		fr.add(btn);
		tf.addKeyListener(this);
		btn.addActionListener(this);
		tf.setText(String.valueOf(set.getLet()));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		MainClass set = new MainClass();
			if(e.getKeyCode() == KeyEvent.VK_A && set.getLet() == 'A') {
				point += 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_S && set.getLet() == 'S') {
				point += 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_D && set.getLet() == 'D') {
				point += 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_F && set.getLet() == 'F') {
				point += 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_J && set.getLet() == 'J') {
				point += 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_K && set.getLet() == 'K') {
				point += 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_L && set.getLet() == 'L') {
				point += 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_SEMICOLON && set.getLet() == ';') {
				point += 1;
			}
			set.setLet('a');
			tf.setText(String.valueOf(set.getLet()));
			tf1.setText(String.valueOf(point));
			tf1.setBounds ( 0, 0, point*3+10, 100 );
		
		//tf.setText(String.valueOf(point));
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Exit")) {
			System.exit(0);
		}
		
	}
}
	


