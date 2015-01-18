
public class Board {
	int[][] boardstate;
	
	Board(int height, int width){
		boardstate = new int[height][width];
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				System.out.print(boardstate[i][j]);
			}
			System.out.println();
		}
	}
}
