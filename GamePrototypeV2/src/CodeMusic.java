
public class CodeMusic {
	private int col, row;

	public void setLet(int row, int col) {
		int[][] music = {{1,0},
				          {0,1},
				          {1,0},
				          {0,1},
				          {1,0},
				          {0,1},
				          {1,0},
				          {0,1},
				          {1,0},
		                  {0,1}};
		for(row=0;row<10;row++) {
			this.row = row;
			for(col=0;col<2;col++) {
				this.col = col;
				
			}
		}
	}
}
