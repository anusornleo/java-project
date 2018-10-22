import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

public class PlayFrame extends JFrame {

	private Container canvas;
	private PlayPanel pp;

	public PlayFrame(GetNote[] list) {
		canvas = getContentPane();
		canvas.setLayout(new BorderLayout());
		
		pp = new PlayPanel(this, list);
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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setSize(1920, 1080); // Have Top Bar [CLOSE]
		
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set Fullscreen
//		this.setUndecorated(true);
		//this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}
	
	private static int index = 0;
	public static List<Double> list = new ArrayList<Double>();
	public static GetNote []notes;
	public static int listSize = 0;

	public static void main(String[] args) throws Exception {
		//Scanner sc = new Scanner(new File("song1.txt"));
		Scanner[] sc = new Scanner[2];
		sc[0] = new Scanner(new File("song1.txt"));
		sc[1] = new Scanner(new File("song2.txt"));
		double num;
		while(sc[1].hasNextLine()) {
				num = sc[1].nextDouble();
				list.add(num);
				index += 1;
				//System.out.println(num);
		}
		listSize = list.size();
		notes = new GetNote[listSize / 3];

		Note();
		
//		for(int i = 0; i < notes.length; i++) {
//			System.out.println(notes[i].slot + " " + notes[i].delay + " " + notes[i].length);
//		}
		
		//TryNote();
		new PlayFrame(notes);
	}
	

	public static void Note(){
		//System.out.println(list.toString());
		int noteIndex = index/3;
		int indexTemp = 0;
		//GetNote []notes = new GetNote[mini_index];
		int slot = 0, length=0;
		double delay = 0;
		//System.out.println(index+" "+mini_index);
		for(int i=0; i<index; i += 3) {
			double slot_b = list.get(i);
			slot = (int) slot_b;
			delay = list.get(i + 1);
			double length_b = list.get(i + 2);
			length = (int) length_b;
			notes[indexTemp++] = new GetNote(slot, delay, length);
		}
		
	}
//	public static void TryNote() {
//		for (int i = 0; i < notes.length; i++) {
//            System.out.println(notes[i].slot+"/"+notes[i].delay+"/"+notes[i].length);
//        }
//	}

}
