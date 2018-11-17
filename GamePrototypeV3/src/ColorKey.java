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

	public static int []keyNoteCode = {KeyEvent.VK_D,KeyEvent.VK_F,KeyEvent.VK_J,KeyEvent.VK_K};

	public static String[] Bg = {"bg_song0.png","bg_song1.png","bg_song2.png","bg_song3.png"};

	public static String[] cover = {"song0.png","song1.png","song2.png","song3.png"};

	//public static int []suffleMusic = {KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT};
}