
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

import javax.imageio.ImageIO;
import javax.swing.*;


public class NeedForSpeed extends JFrame {
	public NeedForSpeed() {
		add(new RacaCar());
	}
	private static BufferedImage image;

	public static void main(String[] args) {
		
		NeedForSpeed frame = new NeedForSpeed();
		frame.setTitle("GUI test");
		frame.setLocation(1920/2-500, 1080/2-500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,1000);
		frame.setVisible(true);
		try {                
	          image = ImageIO.read(new File("note.png"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
		
	}

	class RacaCar extends JPanel implements ActionListener {
		private int y1 = 0;
		public RacaCar() {
			Timer timer = new Timer(10, this);
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}

		@Override
		public void paintComponent(Graphics a) {
			super.paintComponent(a);
			int n1=0; // note 1
			if (n1==0) {
				n1=1;
				int x1 = 90;
				if (y1 > getHeight()-200)
					y1 = 0;
				else
					y1 += 2;
				a.drawImage(image, x1, y1, this);
			}
			
			
		}
		
	}
}