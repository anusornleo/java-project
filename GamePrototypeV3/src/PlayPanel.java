import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.List;


public class PlayPanel extends JPanel {

	private Color[] colorNote;
	private final JFrame fr;
	private boolean startgame = false;
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
	Image background;
	
	GetNote[] notes;
	

//	private void sleep(int i) {
//		try {
//			Thread.sleep(i);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	public PlayPanel(JFrame fr, GetNote[] list) {
		notes = list;
		this.fr = fr;
		setLayout(null);

		// Copy default color to note color.
		colorNote = new Color[ColorKey.colorNoteDefault.length];
		for (int i = 0; i < colorNote.length; i++) {
			colorNote[i] = ColorKey.colorNoteDefault[i];
		}
		background = Toolkit.getDefaultToolkit().createImage("bg.png");
		setBackground(Color.DARK_GRAY);
	}

	public void paint(Graphics g) {
		super.paint(g);
		int adjX = 200, adjY = 750;

		int heightKey = 100;

		Font keyNoteFont = new Font("Arial", Font.BOLD, 40);
		FontMetrics fontMetrics = g.getFontMetrics();

		// check if game is started or not
		// if game is start, draw note
		// otherwise, display text to start a game.
		g.drawImage(background, 0, 0, null);
		if (startgame) {
			for (KeyNoteGraphic pair : noteList) {
				pair.draw(g, colorNote);
			}
		}
		else {

			//g.setColor(Color.white);
			//g.setFont(keyNoteFont);
			accurateText = "Press ENTER to Start";
			comboText = "START!!";
			g.drawImage(background, 0, 0, null);
			
		}

		// key note on display
		for (int i = 0; i < 4; i++) {
			
			g.setColor(Color.white);
			g.fillRoundRect(adjX + i * (BTWkey), adjY, noteWidth, heightKey,15,15); // border
			
			g.setColor(colorNote[i]);
			g.fillRoundRect(adjX + i * (BTWkey)+5, adjY+5, noteWidth-10, heightKey-10, 10, 10);

			g.setColor(Color.white);
			g.setFont(keyNoteFont);

			String[] keyNote = { "D", "F", "J", "K" };
			g.drawString(keyNote[i], adjX+(noteWidth/2) + i * BTWkey-14,adjY + heightKey / 2 +15);
		}

		// Text show on center Display
		g.setColor(textColor);
		g.drawString(accurateText, (fr.getWidth() / 2 - fontMetrics.stringWidth(accurateText)) - 15, 200);
		g.setColor(Color.black);
		g.drawString(comboText, (fr.getWidth() / 2 - fontMetrics.stringWidth(comboText)) - 15, 260);
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
//			for (count = 0; count < 3; count++) {
//				comboText = String.valueOf(count);
//				accurateText = "222";
//				this.count = count;
//				//System.out.println(count);
//				sleep(1000);
//			}
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
		startgame = false;
		comboText = " ";
		accurateText = " ";
		scoreText = "Score : "+String.valueOf(count);
		repaint();
	}
}
