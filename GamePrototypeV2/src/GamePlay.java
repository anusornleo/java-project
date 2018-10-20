
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePlay extends JFrame {
	public GamePlay() {
		add(new RacaCar());
	}

	private static BufferedImage image;
	private int c;

	public static void main(String[] args) {

		GamePlay frame = new GamePlay();
		frame.setTitle("GUI test");
		frame.setLocation(1920 / 2 - 500, 1080 / 2 - 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
		try {
			image = ImageIO.read(new File("note.png"));
		} catch (IOException ex) {
			// handle exception...
		}
		Timer myTimer;
		myTimer = new Timer();
		myTimer.schedule(new TimerTask() {
			private int i;

			public void run() {
				System.out.println(i);
				this.i = i + 1;
			}
		}, 0, 1000);
	}

	class RacaCar extends JPanel implements ActionListener {
		int i = c;
		private int[] y = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		private int[] n = new int[10];

		public RacaCar() {
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}

		@Override
		public void paintComponent(Graphics a) {
			super.paintComponent(a); // note 1
			int[] x = { 100, 200, 300, 400, 500, 600, 700, 800 };
			if (n[i] == 0) {
				if (y[i] > getHeight() - 200) {
					y[i] = 0;
					this.n[i] = 1;
				} else {
					y[i] += 2;
				}
				// System.out.println(c);
				a.drawImage(image, x[i], y[i], this);
			}
		}

	}
	private static void timerTick() {
  		System.out.println(i);
  	}
}