import java.util.Random;


public class Heuristic 
{
	int value = 0;
	Board board;
	
	public int getValue( Board boardstate)
	{
		board = boardstate;
		value = 0;
		// gets the current board states then uses the helper methods to return a value of a move
		checkAdjacent();
		central();
		win();
		loss();
		value += new Random().nextInt(100);
		System.err.println(value);
		return value;
	}
	
	private void checkAdjacent()
	{
		for (int i = 2; i < board.piecesToWin; i++)
		{
			for(int j = 0; j < board.boardstate.length; j++)
			{
				for(int k = 0; k <board.boardstate[j].length; k++)
				{
					if (nInARow(board.boardstate, j,k) >= i)
					{
						value += 1;
					}
				}
			}
		}
	}
	
	private void central()
	{
		int center = Math.abs(board.boardstate[1].length/2);
		for(int i = 0; i < board.boardstate.length; i++)
		{
			for(int j=0; j < board.boardstate[i].length; j++)
			{
				if(board.boardstate[i][j] == 1)
				{
					value += center - Math.abs(j-center);
					value += board.boardstate.length - i;
				}
				if(board.boardstate[i][j] == 2)
				{
					value -= center - Math.abs(j-center);
					value -= board.boardstate.length - i;
				}
			}
		}
	}
	
	private void win()
	{
		for(int i=0; i<board.boardstate.length; i++){
			for(int j=0; j<board.boardstate[i].length; j++){
				if(board.boardstate[i][j] == 1){
					if(nInARow(board.boardstate, i, j) >= board.piecesToWin){
						value += 9001;
						return;
					}
				}
			}
		}
	}
	
	private void loss()
	{
		for(int i=0; i<board.boardstate.length; i++){
			for(int j=0; j<board.boardstate[i].length; j++){
				if(board.boardstate[i][j] == 2){
					if(nInARow(board.boardstate, i, j) >= board.piecesToWin){
						value += -9001;
						return;
					}
				}
			}
		}
	}
	
	// Add Heuristic helper methods
	// Todo: Add multiple Heuristic levels based on time remaining
	
	//Given the location of a piece, returns the length of the longest chain of pieces that particular piece is part of.
	public int nInARow(int[][] boardstate, int row, int column){
		int player = boardstate[row][column];
		if(player == 0){
			System.err.println("Null player");
			return 0;
		}
		
		int maxChain = 1;
		int currentChain = 1;
		
		
		//Left-Right Chain
		//Check left
		int x = column-1;
		int y = row;
		while(x > -1 && boardstate[y][x] == player){
			currentChain++;
			x--;
		}
		//Upon reaching terminating piece, space, or edge of the board, check to the right
		x = column+1;
		while(x < boardstate[y].length && boardstate[y][x] == player){
			currentChain++;
			x++;
		}
		//Record left-right chain as the max chain
		maxChain = currentChain;
		
		
		//Up-Down Chain
		//Check up
		currentChain = 1;
		x = column;
		y = row + 1;
		while(y < boardstate.length && boardstate[y][x] == player){
			currentChain++;
			y++;
		}
		//Upon reaching terminating piece, space, or edge of the board, check down
		y = row - 1;
		while(y > -1 && boardstate[y][x] == player){
			currentChain++;
			y--;
		}
		//If we have a new maxChain record it
		if(currentChain > maxChain){
			maxChain = currentChain;
		}
		
		
		//UpLeft-DownRight Chain
		//Check up-left
		currentChain = 1;
		x = column - 1;
		y = row + 1;
		while(y < boardstate.length && x > -1 && boardstate[y][x] == player){
			currentChain++;
			x--;
			y++;
		}
		//Upon reaching terminating piece, space, or edge of the board, check down-right
		x = column + 1;
		y = row - 1;
		while(y > -1 && x < boardstate[y].length && boardstate[y][x] == player){
			currentChain++;
			x++;
			y--;
		}
		//If we have a new maxChain record it
		if(currentChain > maxChain){
			maxChain = currentChain;
		}
		
		
		//UpRight-DownLeft Chain
		//Check up-right
		currentChain = 1;
		x = column + 1;
		y = row + 1;
		while(y < boardstate.length && x < boardstate[y].length && boardstate[y][x] == player){
			currentChain++;
			x++;
			y++;
		}
		//Upon reaching terminating piece, space, or edge of the board, check down-left
		x = column - 1;
		y = row - 1;
		while(y > -1 && x > -1 && boardstate[y][x] == player){
			currentChain++;
			x--;
			y--;
		}
		//If we have a new maxChain record it
		if(currentChain > maxChain){
			maxChain = currentChain;
		}
		
		return maxChain;
	}
}