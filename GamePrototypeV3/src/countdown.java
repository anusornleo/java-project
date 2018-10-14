
public class countdown extends GetNote {
	int count = 0;
	public countdown(int slot, double delay, int length) {
		super(slot, delay, length);
		for (count = 0; count < 3; count++) {
			//comboText = String.valueOf(count);
			System.out.println(count);
			sleep(1000);
		}
		// TODO Auto-generated constructor stub
	}
	private void sleep(int i) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
