
public class Board {
	// I was thinking of having a 0 represent an open space, 1 represent our piece, and a 2 represent an opponents piece
	
	int[][] boardstate;
	int height;
	
	Board(int height, int width){
		boardstate = new int[height][width];
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				System.out.print(boardstate[i][j]);
			}
			System.out.println();
		}
	}
	
	public void makeMove(int player, int column, int movetype)
	{
		if(movetype == 1)
		{
			int newheight = 0;
			for(int i=0; i<boardstate[newheight][column]; i=0)
			{
				newheight ++;
			}
			boardstate[newheight][column] = player;
		}
		
		if(movetype == 0)
		{
			for( int i=0; i < height; i++)
			{
				boardstate[i][column] = boardstate[i+1][column];
			}
			boardstate[height-1][column] = 0;
		}
	}
}
