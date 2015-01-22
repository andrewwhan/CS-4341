
public class Board {
	
	int[][] boardstate;
	
	Board(int height, int width){
		boardstate = new int[height][width];
		printBoard();
	}
	
	public void makeMove(int player, int column, int movetype)
	{
		if(movetype == 1)
		{
			int newheight = 0;
			while(boardstate[newheight][column] > 0)
			{
				newheight ++;
			}
			boardstate[newheight][column] = player;
		}
		
		if(movetype == 0)
		{
			for( int i=0; i < boardstate.length-1; i++)
			{
				boardstate[i][column] = boardstate[i+1][column];
			}
			boardstate[boardstate.length-1][column] = 0;
		}
		printBoard();
	}
	
	public void printBoard(){
		for(int i=boardstate.length-1; i>=0; i--){
			for(int j=0; j<boardstate[i].length; j++){
				System.err.print(boardstate[i][j]);
			}
			System.err.println();
		}
		System.err.println();
	}
}
