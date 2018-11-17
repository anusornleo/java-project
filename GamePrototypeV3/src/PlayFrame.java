import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PlayFrame extends JFrame {

	private Container canvas;
	private PlayPanel pp;
	public boolean startgame;
	public static boolean finishgame;
	public static boolean selectMusic;
	private static int numberMusic = 0;
	
	public PlayFrame(GetNote[] list) {
		canvas = getContentPane();
		canvas.setLayout(new BorderLayout());
		
		pp = new PlayPanel(this, list);
		//gt = new GroupThread(pp, list);
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
				if(e.getKeyCode() == KeyEvent.VK_ENTER && startgame==false && selectMusic==false && finishgame==false){
					//System.out.println("1startgame:"+startgame+"| selectMusic:"+selectMusic+"| finishgame:"+finishgame);
					selectMusic = true;
					pp.select();
					return;
					}
				else if(e.getKeyCode() == KeyEvent.VK_ENTER && startgame==false && selectMusic==true &&  finishgame==false){
					//System.out.println("2startgame:"+startgame+"| selectMusic:"+selectMusic+"| finishgame:"+finishgame);
					pp.startGame();
					selectMusic = false;
					startgame = true;
					System.out.println("startgame"+startgame+" | selectgame"+selectMusic+" | finishgame"+finishgame);
//					try {
//						File soundFile = new File("Song0.wav");
//						AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
//						Clip clip = AudioSystem.getClip();
//						clip.open(audioIn);
//						clip.start();
//					} catch (UnsupportedAudioFileException evt) {
//						evt.printStackTrace();
//						System.out.println("No support");
//					} catch (IOException evt) {
//						evt.printStackTrace();
//					} catch (LineUnavailableException evt) {
//						evt.printStackTrace();
//					}
					return;
					}
				else if(e.getKeyCode() == KeyEvent.VK_ENTER && startgame==true && selectMusic==false &&  finishgame==false){
					pp.pause();
				}
				else if(e.getKeyCode() == KeyEvent.VK_ENTER && startgame==true && selectMusic==false &&  finishgame==true){
					//System.out.println("3startgame:"+startgame+"| selectMusic:"+selectMusic+"| finishgame:"+finishgame);
					selectMusic = true;
					startgame = false;
					finishgame = false;
					pp.playAgain();
					return;
					}
				else if(e.getKeyCode() == KeyEvent.VK_LEFT && selectMusic==true){
					//System.out.println("Lstartgame:"+startgame+"| selectMusic:"+selectMusic+"| finishgame:"+finishgame);
					numberMusic -= 1;
					if (numberMusic < 0) {numberMusic = 3;}
					pp.numberMusic(numberMusic);
					return;
					}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT && selectMusic==true){
					//System.out.println("Rstartgame:"+startgame+"| selectMusic:"+selectMusic+"| finishgame:"+finishgame);
					numberMusic += 1;
					if (numberMusic > 3) {numberMusic = 0;}
					pp.numberMusic(numberMusic);
					return;
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
		
		this.setSize(900, 1000); // Have Top Bar [CLOSE]
		
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
		Scanner[] sc = new Scanner[4];
		sc[0] = new Scanner(new File("song0.txt"));
		sc[1] = new Scanner(new File("song1.txt"));
		sc[2] = new Scanner(new File("song2.txt"));
		sc[3] = new Scanner(new File("song3.txt"));
		double num;
		while(sc[numberMusic].hasNextLine()) {
				num = sc[numberMusic].nextDouble();
				list.add(num);
				index += 1;
		}
		listSize = list.size();
		notes = new GetNote[listSize / 3];
		Note();
		new PlayFrame(notes);
	}
	

	public static void Note(){
		int indexTemp = 0;
		int slot = 0, length=0;
		double delay = 0;
		for(int i=0; i<index; i += 3) {
			double slot_b = list.get(i);
			slot = (int) slot_b;
			delay = list.get(i + 1);
			double length_b = list.get(i + 2);
			length = (int) length_b;
			notes[indexTemp++] = new GetNote(slot, delay, length);
		}
		
	}

	public static void finishValue(boolean finishgame2) {
		// TODO Auto-generated method stub
		finishgame = finishgame2;
	}

}
