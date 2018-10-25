import java.awt.Color;
import java.awt.event.KeyEvent;

public class ColorKey {

	public static Color[] colorNoteDefault = { Color.orange, Color.orange,Color.orange, Color.orange };

	public static Color[] colorNotePress = { new Color(255, 153, 51),
		new Color(255, 153, 51), new Color(255, 153, 51),
		new Color(255, 153, 51) };

	public static Color[] colorNoteDisable = { new Color(255, 0, 102,128),
		new Color(255, 153, 51,128), new Color(255, 153, 51,128),
		new Color(255, 0, 102,128) };

	public static int []keyNoteCode = {
			KeyEvent.VK_D,
			KeyEvent.VK_F,
			KeyEvent.VK_J,
			KeyEvent.VK_K
	};

	public static String []path = {"bg1.png","bg2.png"};

	public static int []suffleMusic = {KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT};
}