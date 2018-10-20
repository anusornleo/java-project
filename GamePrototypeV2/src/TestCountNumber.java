

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TestCountNumber {

	public static void main(String[] args) {
		Timer myTimer;
		myTimer = new Timer();
		myTimer.schedule(new TimerTask() {
			private int i;
			public void run() {
				System.out.println(i);
				this.i = i+1;
			}
		}, 0, 1000);
	}
	
//	private static void timerTick() {
//		System.out.println(i);
//	}

}