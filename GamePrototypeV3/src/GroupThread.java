import java.util.List;
import java.util.ArrayList;

public class GroupThread {

	private PlayPanel pp;
	private Thread menu;
	private Thread noteThread;
	private Thread gameThread;
	public static int num = 0;
	public GetNote[] notes;
	private long spf = 1;
	private boolean hasNext = true;
	public static boolean pause = false;
	//public static int count;

	public GroupThread(PlayPanel pp, GetNote[] list) {
		this.pp = pp;
		notes = list;

		if (num == 0) {
			menu();
			num += 1;
		} else if (num == 1) {
			
			noteThread();
			System.out.println("add");
			gameThread();
		}

	}

	/**
	 * thread for add new note in note list
	 */
	private void menu() {
		menu = new Thread() {

			public void run() {

				try {
					while (!pp.isFinish() || hasNext) {
						sleep(200);
						pp.updateNotes2();
//						if (count == 40) {
//							System.out.println("000");
//							res();
//						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};

		menu.start();
	}

	private void noteThread() {
		noteThread = new Thread() {

			public void run() {

				try {
					
					for (int i = 0; i < notes.length; i++) {
						if (pause == true) {
							p();
//							System.out.println("1. " + count);
//							pause();
						}

						hasNext = true;
						// System.out.println(notes[i].slot+" "+notes[i].delay+" "+notes[i].length);
						int timer = (int) (notes[i].delay * 1000);
						if (timer > 0) {
							sleep(timer);
						}
						pp.addNote(notes[i]);
						

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				hasNext = false;
			}

		};

		noteThread.start();
	}

	/**
	 * Thread for update note position
	 */
	private void gameThread() {
		gameThread = new Thread() {

			public void run() {

				try {

					while (!pp.isFinish() || hasNext) {
						sleep(spf);
						pp.updateNotes();
						//System.out.println(pause);
						if (pause == true) {
							p();
							//pause = false;
//							System.out.println("2. " + count);
//							pause();
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				;
				try {
					for (int i = 0; i < 3; i++) {
						sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				pp.finish();

			}

		};

		gameThread.start();

	}

	public void pause() { // test pause
		pause = true;
		System.out.println("pause");
	}

	public synchronized void p() { // test pause
		try {
			wait(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
