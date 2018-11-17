import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PlayPanel extends JPanel {

	private Color[] colorNote;
	private final JFrame fr;
	public static boolean startgame = false;
	public static boolean selectMusic = false;
	public static boolean finishgame = false;
	private ArrayList<KeyNoteGraphic> noteList = new ArrayList<>();
	private int noteSpeed = 1;
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
	int adjX = 700, adjY = 750;
	int heightKey = 100;
	Font keyNoteFont = new Font("WaffleRegular", Font.BOLD, 40);
	BufferedImage imgHome,imgKeyBG,imgKey;
	BufferedImage imgCover[] = new BufferedImage[4];
	BufferedImage imgBg[] = new BufferedImage[4];
	public static int numberMusic = 0;
	public static boolean pause = false;
	
	
	
	public void img_load() { // Load image form same Image
		for (int i = 0; i < 4; i++) {
			try {
				imgKey = ImageIO.read(new File("pic\\keyBack.png"));
				imgKeyBG = ImageIO.read(new File("pic\\keyBG.png"));
				imgHome = ImageIO.read(new File("bg_home.png"));
				imgBg[i] = ImageIO.read(new File(ColorKey.Bg[i]));
				imgCover[i] = ImageIO.read(new File(ColorKey.cover[i]));
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
		
		if (startgame==false && finishgame == false && selectMusic==false) {
			g.drawImage(imgHome, 0, 0,1920,1080, this);
			g.setColor(Color.white);
			g.setFont(keyNoteFont);
			g.drawString("Enter to...", fr.getWidth() / 2 - 200, 400);
			g.drawString("START!!", fr.getWidth() / 2 - 150, 500);
			
		}
		if (startgame==false && selectMusic==true && finishgame==false) {
			count = 0;
			selectMusic(g);
		}
		else if (startgame==true) {
			runningNote(g);
			
		}
		else if (finishgame == true) {
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
		//repaint();
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
		//repaint();
	}

	public void updateNotes2() {
		// TODO Auto-generated method stub
		try {
			repaint();
		} catch (Exception e) {

		}
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
		System.out.println("note");
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
		PlayFrame.finishValue(finishgame);
		comboText = "";
		accurateText = "";
		scoreText = "";
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
	public void runningNote(Graphics g) {
		
		g.drawImage(imgBg[numberMusic], 0, 0, 1920, 1080, null);
		g.setColor(new Color(0, 0, 0, 50) );
		g.fillRect(640, 0, 640, fr.getHeight());
		g.drawImage(imgKeyBG, 640, adjY+5, 640, 302, null);
		for (int i = 0; i < 4; i++) {
			g.setColor(new Color(255, 255, 153));
			g.fillRoundRect(adjX + i * (BTWkey)+45, 0, 10, 800, 0, 0); // Line under KeyNote
			
			
			g.setColor(colorNote[i]);
			g.drawImage(imgKey,adjX + i * (BTWkey)+5, adjY+5, noteWidth-10, 192,null);

			g.setColor(Color.white);
			g.setFont(keyNoteFont);
			String[] keyNote = { "D", "F", "J", "K" };
			g.drawString(keyNote[i], adjX+(noteWidth/2) + i * BTWkey-20,adjY + heightKey / 2 +15);
			
		}
		//g.drawString(GroupThread.c,980,500);
		for (KeyNoteGraphic pair : noteList) {
			g.setColor(new Color(255, 255, 153));
			pair.draw(g, colorNote);
			
//			for (int i = 0; i < 4; i++) {
//				
//				g.setColor(Color.white);
//				g.fillRoundRect(adjX + i * (BTWkey), adjY, noteWidth, heightKey,15,15); // border
//				
//				g.setColor(colorNote[i]);
//				g.fillRoundRect(adjX + i * (BTWkey)+5, adjY+5, noteWidth-10, heightKey-10, 10, 10);
//
//				g.setColor(Color.white);
//				g.setFont(keyNoteFont);
//				String[] keyNote = { "D", "F", "J", "K" };
//				g.drawString(keyNote[i], adjX+(noteWidth/2) + i * BTWkey-20,adjY + heightKey / 2 +15);
//				
//			}
		}
		if ( finishgame==true ) {
			g.setColor(new Color(255,182,50));
			g.fillRoundRect(640, 200, 640, 400, 10, 10);
			g.setColor(Color.white);
			g.drawString("SCORE : "+String.valueOf(count),800,320);
		}
	}
	
	public void select() {
		selectMusic = true;
		new GroupThread(this, notes);
		//System.out.println("startgame:"+startgame+"| selectMusic:"+selectMusic+"| finishgame:"+finishgame);
	}
	
	public void selectMusic(Graphics g) {
		g.drawImage(imgCover[numberMusic], 640, 200, 640, 512, null);
		count = 0;
//		g.setColor(Color.blue);
//		g.fillRect(640, 400, 640, 400);
	}
	
	public void numberMusic(int number) {
		System.out.println("Number Music "+number);
		numberMusic = number;
	}


	public void playAgain() {
		// TODO Auto-generated method stub
		startgame=false; selectMusic=true;finishgame=false;
	}


	public void pause() {
		// TODO Auto-generated method stub
		GroupThread gt = new GroupThread(null, notes);
		gt.pause();
	}
}
