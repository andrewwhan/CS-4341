import java.io.IOException;
import java.util.Random;


public class Main 
{
	
	public static void main(String[] args) throws IOException 
	{
		Board board;
		Heuristic heuristic = new Heuristic();
		
		RefInterface refInt = new RefInterface();
		refInt.announce();
		board = refInt.gameStart();
		while(true){
			int column = new Random().nextInt(board.boardstate.length);
			board.makeMove(1, column, 1);
			refInt.makeMove(column, 1);
			refInt.updateBoard();
			System.err.println(heuristic.getValue(board));
		}
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
