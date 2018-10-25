import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PlayPanel extends JPanel {

	private Color[] colorNote;
	private final JFrame fr;
	private boolean startgame = false;
	private boolean finishgame = false;
	private ArrayList<KeyNoteGraphic> noteList = new ArrayList<>();
	private int noteSpeed = 10;
	private int yBound = 750;
	private String accurateText = "";
	private String comboText = "";
	private String scoreText = " ";
	private int combo;
	private int count;
	private Color textColor = Color.black;
	private int perfectRange = 30;
	private int goodRange = 50;
	private int noteWidth = 100;
	private int rangeClick = 100;
	private int BTWkey = noteWidth+40;
	GetNote[] notes;
	int adjX = 690, adjY = 750;
	int heightKey = 100;
	Font keyNoteFont = new Font("Venus Rising", Font.BOLD, 40);
	BufferedImage img[] = new BufferedImage[2];
	private boolean selectMusic = false;
	
	public void img_load() {  // Load image form same Image
		//String path[] = {"bg1.png","bg2.png"};
		for (int i = 0; i < 2; i++) {
			try {
				img[i] = ImageIO.read(new File(ColorKey.path[i]));
			} catch (IOException e) {
			}
		}
	}

	
	public PlayPanel(JFrame fr, GetNote[] list) {
		img_load();
		notes = list;
		this.fr = fr;
		setLayout(null);
		colorNote = new Color[ColorKey.colorNoteDefault.length];
		for (int i = 0; i < colorNote.length; i++) {
			colorNote[i] = ColorKey.colorNoteDefault[i];
		}
		
		
	}

	public void paint(Graphics g) {
		
		super.paint(g);
		FontMetrics fontMetrics = g.getFontMetrics();

		// check if game is started or not
		// if game is start, draw note
		// otherwise, display text to start a game.
		//g.drawImage(background_a, 0, 0, null); //background image each Music
		
		if (startgame==false && finishgame == false) {
			g.drawImage(img[0], 0, 0,1920,1080, this);
			g.setColor(Color.white);
			g.setFont(keyNoteFont);
			g.drawString("Enter to...", fr.getWidth() / 2 - 200, 400);
			g.drawString("START!!", fr.getWidth() / 2 - 150, 500);
		}
		else if (selectMusic==true) {
			g.setColor(Color.blue);
			g.fillRect(640, 400, 640, 400);
			//selectMusic(g);
		}
		else if (startgame==true) {
			selectMusic=false;
			runningNote(g);
			
		}
		else if (finishgame == true) {
			System.out.println("Finish Game");
			g.setColor(Color.blue);
			g.fillRect(0, 0, 200, 200);
		}

		
		g.setColor(textColor);
		g.drawString(accurateText, (fr.getWidth() / 2 - fontMetrics.stringWidth(accurateText)) - 15, 200);
		g.drawString(comboText, (fr.getWidth() / 2 - fontMetrics.stringWidth(comboText)) - 15, 260);
		g.setColor(Color.black);
		g.drawString(scoreText,50, 50);
	}

	public void keyPress(int j) {
		colorNote[j] = ColorKey.colorNotePress[j];
		for (KeyNoteGraphic note : noteList) {
			if (note.second.slot == j) {
				if (!note.isClick) {
					int y = note.first + note.height;
					int diff = Math.abs(y - yBound);
					note.isClick = true;
					if (diff < perfectRange) { // perfect
						comboText = String.valueOf(++combo);
						count+=3;
						scoreText = "Score : "+String.valueOf(count);
						accurateText = "Perfect";
						textColor = Color.cyan;
					} else if (diff < goodRange) { // good
						comboText = String.valueOf(++combo);
						count+=1;
						scoreText = "Score : "+String.valueOf(count);
						accurateText = "Good";
						textColor = Color.green;
					} else if (diff < rangeClick) { // bad
						combo = 0;
						scoreText = "Score : "+String.valueOf(count);
						comboText = "";
						accurateText = "Bad";
						textColor = Color.red;
						note.isBad = true;

					} else {
						note.isClick = false;
					}

				}
				break;
			}
		}
		repaint();
	}

	public void keyRelease(int j) {
		colorNote[j] = ColorKey.colorNoteDefault[j];
		for (KeyNoteGraphic note : noteList) {
			if (note.second.slot == j) {
				if (note.second.length > 1) {
					int y = note.first;
					int diff = Math.abs(y - yBound);
					if (diff < perfectRange) { // perfect
						comboText = String.valueOf(++combo);
						count+=3;
						scoreText = "Score : "+String.valueOf(count);
						accurateText = "Perfect";
						textColor = Color.cyan;
					} else if (diff < goodRange) { // good
						comboText = String.valueOf(++combo);
						count+=1;
						scoreText = "Score : "+String.valueOf(count);
						accurateText = "Good";
						textColor = Color.green;
					} else if (diff < rangeClick) { // bad
						combo = 0;
						scoreText = "Score : "+String.valueOf(count);
						comboText = "";
						accurateText = "Bad";
						textColor = Color.red;

						note.isBad = true;
					}
				}
				break;
			}
		}
		repaint();
	}

	public void startGame() {
		if (!startgame) {
			startgame = true;
			noteList.clear();
			combo = 0;
			comboText = "";
			accurateText = "";
			scoreText = "Score : "+String.valueOf(count);
			new GroupThread(this, notes);
			repaint();
		}
	}

	public void addNote(GetNote note) {
		// TODO Auto-generated method stub
		noteList.add(new KeyNoteGraphic(note, noteWidth));
	}


	public void updateNotes() {
		// TODO Auto-generated method stub
		try {
			int i = 0;
			for (KeyNoteGraphic note : noteList) {
				note.first += noteSpeed;
				if (note.first - note.height > yBound) {
					if (!note.isClick) { // miss
						combo = 0;
						scoreText = "Score : "+String.valueOf(count);
						comboText = "";
						accurateText = "Miss";
						textColor = Color.white;
					}
					noteList.remove(i);

				}

				i++;

			}
			if (!noteList.isEmpty()) {

			}

			repaint();
		} catch (Exception e) {

		}
	}

	public boolean isFinish() {
		return noteList.isEmpty();
	}

	public void finish() {
		//startgame = false;
		finishgame = true;
		comboText = "";
		accurateText = "";
		//scoreText = "Score : "+String.valueOf(count);
//		Graphics g = null;
//		for(int i = 0;i<500;i++) {
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		repaint();
	}
	public void coundown(int x,Graphics g) {
	}
	public void runningNote(Graphics g) {
		
		g.drawImage(img[1], 0, 0, 1920, 1080, null);
		g.setColor(new Color(0, 0, 0, 50) );
		g.fillRect(640, 0, 640, fr.getHeight());
		for (int i = 0; i < 4; i++) {
			g.setColor(new Color(255, 255, 153));
			g.fillRoundRect(adjX + i * (BTWkey)+45, 0, 10, 800, 0, 0);
			
			g.setColor(Color.white);
			g.fillRoundRect(adjX + i * (BTWkey), adjY, noteWidth, heightKey,15,15); // border
			
			g.setColor(colorNote[i]);
			g.fillRoundRect(adjX + i * (BTWkey)+5, adjY+5, noteWidth-10, heightKey-10, 10, 10);

			g.setColor(Color.white);
			g.setFont(keyNoteFont);
			String[] keyNote = { "D", "F", "J", "K" };
			g.drawString(keyNote[i], adjX+(noteWidth/2) + i * BTWkey-20,adjY + heightKey / 2 +15);
			
		}
		g.drawString(GroupThread.c,980,500);
		for (KeyNoteGraphic pair : noteList) {
			g.setColor(new Color(255, 255, 153));
			pair.draw(g, colorNote);
			
			for (int i = 0; i < 4; i++) {
				
				g.setColor(Color.white);
				g.fillRoundRect(adjX + i * (BTWkey), adjY, noteWidth, heightKey,15,15); // border
				
				g.setColor(colorNote[i]);
				g.fillRoundRect(adjX + i * (BTWkey)+5, adjY+5, noteWidth-10, heightKey-10, 10, 10);

				g.setColor(Color.white);
				g.setFont(keyNoteFont);
				String[] keyNote = { "D", "F", "J", "K" };
				g.drawString(keyNote[i], adjX+(noteWidth/2) + i * BTWkey-20,adjY + heightKey / 2 +15);
				
			}
		}
		if ( finishgame==true ) {
			g.setColor(Color.green);
			g.fillRoundRect(500, 500, 500, 500, 10, 10);
			g.drawString("SCORE : "+String.valueOf(count),980,500);
		}
	}
	
	public void select() {
		selectMusic = true;
	}
	
	public void selectMusic(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(640, 400, 640, 400);
	}
}
