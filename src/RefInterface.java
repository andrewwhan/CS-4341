
public class RefInterface 
{
	// ToDo: Add Timer
	
	int playerNumber;
	int boardHeight;
	int boardWidth;
	int piecesToWin;
	int timeLimit;
	
	public void announce()
	{
		// This should send the Ref an identity and should update which player number we are
	}
	public void gameStart()
	{
		/* This should get the game information from the Ref
		 * Game information consists of 5 numbers [in this order]: 
		 * board height (#rows), 
		 * board width (#columns), 
		 * number of pieces to win (the N in Connect-N), 
		 * turn of the player (1 indicating 1st player, and 2 indicating 2nd player), 
		 * and the time limit to make a move in seconds. 
		 * 
		 * Once the players receive these information, the game starts immediately. 
		 * These are sent as a one line separated with spaces.
		 */
	}
	
	public void makeMove(int column, int movetype)
	{
		// This should tell the Ref which column to put the piece into and if it is a popout or a normal move 
	}
	
	public void updateBoard()
	{
		// This should read the Ref's output as to where the other player went
	}

}
