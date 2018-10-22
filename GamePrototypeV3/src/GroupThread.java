import java.util.List;
import java.util.ArrayList;

public class GroupThread {

	private PlayPanel pp;
	private Thread noteThread;
	private Thread gameThread;
	
	public GetNote[] notes;
	
	private int fps = 60;
	private long spf = 1000 / fps;

	private boolean hasNext = true;

	public GroupThread(PlayPanel pp, GetNote[] list) {
		this.pp = pp;
		notes = list;

		noteThread();
		gameThread();
	}

	/**
	 * thread for add new note in note list
	 */
	
	
	private void noteThread() {
		noteThread = new Thread() {

			public void run() {

				try {
					for (int i = 0; i < notes.length; i++) {
						hasNext = true;
						System.out.println(notes[i].delay);
						int timer = (int) (notes[i].delay * 1000);
						
						if (timer > 0)
							sleep(timer);
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
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				pp.finish();
			}

		};

		gameThread.start();
	}

}
