import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class KeyNoteGraphic {

	public int first;
	public boolean isClick = false;
	public boolean isBad = false;
	public GetNote second;
	public int height;
	public int width;
	private int noteHeight = 20;
	private int noteWidth = 100;
	private int BTWkey = noteWidth+40;

	public KeyNoteGraphic(GetNote second, int width) {
		// TODO Auto-generated constructor stub
		this.height = second.length * noteHeight;
		this.width = width;
		this.first = -height;
		this.second = second;
	}



	/**
	 * draw note
	 * @param g
	 * @param color
	 */
	public void draw(Graphics g, Color[] color) {
		int y = this.first;
		GetNote note = this.second;
		int slot = note.slot;

		if (isBad)
			g.setColor(ColorKey.colorNoteDisable[slot]);
		else
			g.setColor(color[slot]);
		((Graphics2D) g).setStroke(new BasicStroke(5.0f));
		g.fillRoundRect(slot * BTWkey + 690, y, width, height, 20, 20); // color in note

		g.setColor(Color.white);
		g.drawRoundRect(slot * BTWkey + 690, y, width, height, 20, 20); // border
	}

}
