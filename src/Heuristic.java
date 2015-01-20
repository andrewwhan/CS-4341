
public class Heuristic 
{
	int value;
	int[][] boardstate;
	
	public int getValue( int[][] boardState)
	{
		boardstate = boardState;
		// gets the current board states then uses the helper methods to return a value of a move
		return value;
	}
	
	private void checkAdjacent()
	{
		// This should modify the value based on number of connected tiles 
	}
	
	private void central()
	{
		// This should modify the value based on how central the move is
	}
	
	private void win()
	{
		// This should set value to a large number
	}
	
	private void loss()
	{
		// This should set value to -1
	}
	
	// Add Heuristic helper methods
	// Todo: Add multiple Heuristic levels based on time remaining
}