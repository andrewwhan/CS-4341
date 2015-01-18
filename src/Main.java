import java.io.IOException;


public class Main 
{
	
	public static void main(String[] args) throws IOException 
	{
		Board boardstate;
		
		RefInterface refInt = new RefInterface();
		refInt.announce();
		boardstate = refInt.gameStart();
	}
	
	public void minimax()
	{
		// uses minimax to create a search tree
	}
	
	private boolean alphaBeta()
	{
		// This should use alpha beta pruning to determine if we should continue searching down a branch
		// returning true means continue searching
		// returning false means stop searching
		return true;
	}
}
