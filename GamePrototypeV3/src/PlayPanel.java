import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	private Color textColor = Color.white;
	private int perfectRange = 15;
	private int goodRange = 30;
	private int noteWidth = 150;
	private int rangeClick = 100;
	

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public PlayPanel(JFrame fr) {
		this.fr = fr;
		setLayout(null);

		// Copy default color to note color.
		colorNote = new Color[ColorKey.colorNoteDefault.length];
		for (int i = 0; i < colorNote.length; i++) {
			colorNote[i] = ColorKey.colorNoteDefault[i];
		}

		setBackground(Color.black);
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
			
		}

		// key note on display
		for (int i = 0; i < 4; i++) {

			g.setColor(Color.white);
			g.fillRect(adjX + i * noteWidth, adjY, noteWidth, heightKey);

			g.setColor(colorNote[i]);
			g.fillRect(adjX + i * noteWidth + 2, adjY + 2, noteWidth - 4, heightKey - 4);

			g.setColor(Color.white);
			g.setFont(keyNoteFont);

			String[] keyNote = { "D", "F", "J", "K" };

			g.drawString(keyNote[i], adjX+(noteWidth/2) + i * noteWidth,adjY + heightKey / 2);
		}

		// Text show on center Display
		g.setColor(textColor);
		g.drawString(accurateText, (fr.getWidth() / 2 - fontMetrics.stringWidth(accurateText)) - 15, 200);
		g.setColor(Color.white);
		g.drawString(comboText, (fr.getWidth() / 2 - fontMetrics.stringWidth(comboText)) - 15, 260);
		g.drawString(scoreText,  (fr.getWidth() / 2 - fontMetrics.stringWidth(comboText)) - 15, 360);
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
						scoreText = String.valueOf(count++);
						accurateText = "Perfect";
						textColor = Color.cyan;
					} else if (diff < goodRange) { // good
						comboText = String.valueOf(++combo);
						scoreText = String.valueOf(count++);
						accurateText = "Good";
						textColor = Color.green;
					} else if (diff < rangeClick) { // bad
						combo = 0;
						scoreText = String.valueOf(count);
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
						scoreText = String.valueOf(count++);
						accurateText = "Perfect";
						textColor = Color.cyan;
					} else if (diff < goodRange) { // good
						comboText = String.valueOf(++combo);
						scoreText = String.valueOf(count++);
						accurateText = "Good";
						textColor = Color.green;
					} else if (diff < rangeClick) { // bad
						combo = 0;
						scoreText = String.valueOf(count);
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
			scoreText = String.valueOf(count);
			new GroupThread(this);
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
						scoreText = String.valueOf(count);
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
		// TODO Auto-generated method stub

		return noteList.isEmpty();
	}

	public void finish() {
		startgame = false;
		comboText = " ";
		accurateText = " ";
		scoreText = String.valueOf(count);
		repaint();
	}
}
