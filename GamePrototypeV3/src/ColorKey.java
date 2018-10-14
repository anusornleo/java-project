import java.awt.Color;
import java.awt.event.KeyEvent;


public class ColorKey {

	public static Color[] colorNoteDefault = { Color.pink, Color.orange,
			Color.orange, Color.pink };
	public static Color[] colorNotePress = { new Color(255, 0, 102),
		new Color(255, 153, 51), new Color(255, 153, 51),
		new Color(255, 0, 102) };
	
	public static Color[] colorNoteDisable = { new Color(255, 0, 102,128),
		new Color(255, 153, 51,128), new Color(255, 153, 51,128),
		new Color(255, 0, 102,128) };
	
	public static int []keyNoteCode = {
			KeyEvent.VK_D,
			KeyEvent.VK_F,
			KeyEvent.VK_J,
			KeyEvent.VK_K
	};
}
