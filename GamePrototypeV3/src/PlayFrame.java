import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class PlayFrame extends JFrame {


	private Container canvas;
	private PlayPanel pp;

	public PlayFrame() {
		canvas = getContentPane();
		canvas.setLayout(new BorderLayout());
		
		pp = new PlayPanel(this);
		canvas.add(pp, BorderLayout.CENTER);
		
		pp.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				for (int j = 0; j < ColorKey.keyNoteCode.length; j++) {
					if(e.getKeyCode() == ColorKey.keyNoteCode[j]){	
						pp.keyRelease(j);
						return;
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					pp.startGame();
					
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				for (int j = 0; j < ColorKey.keyNoteCode.length; j++) {
					if(e.getKeyCode() == ColorKey.keyNoteCode[j]){	
						pp.keyPress(j);
						return;
					}
				}
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		pp.setFocusable(true);

		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new PlayFrame().setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
