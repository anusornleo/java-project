import java.util.Random;
import java.util.Scanner;

public class MainClass {
	
	private  int letNum;
    private static  int secret;
    private static  int preNum = 10;
    private static char let='a';
 	public int SampleGame(int upperbound) {
		long seed = System.nanoTime(); 
		Random rand = new Random(seed);
		this.secret = rand.nextInt(upperbound);
		while(preNum == secret) {
			this.secret = rand.nextInt(upperbound);
		}
		this.preNum = secret;
		return secret;
	}
 	
	public char getLet() {
		return let;
	}

	public void setLet(char let) {
		char[] letAll = { 'A', 'S', 'D', 'F', 'J', 'K', 'L', ';' };
		MainClass run = new MainClass();
		run.SampleGame(7);
		let = letAll[secret];
		this.let = let;
	}


	

	

}
