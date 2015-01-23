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
//			int column = new Random().nextInt(board.boardstate.length);
//			board.makeMove(1, column, 1);
//			refInt.makeMove(column, 1);
			int[] bestMove = minimax(board, 1, true, 2);
			board.makeMove(1, bestMove[0], bestMove[1]);
			refInt.makeMove(bestMove[0], bestMove[1]);
			refInt.updateBoard();
		}
	}
	
	public static int[] minimax(Board board, int player, boolean root, int depth)
	{
		//Check for termination
		if(depth == 0){
			int[] returnValue = new int[1];
			returnValue[0] = new Heuristic().getValue(board);
			return returnValue;
		}
		
		int value;
		//If we're player 1 and maxing start value off low, if we're minning start value high
		value = (player == 1 ? -10000 : 10000);
		int column=0;
		int movetype=1;

		//Branch, checking each column a move can be made in
		for(int col=0; col<board.boardstate[0].length; col++){
			for(int mov=0; mov<2; mov++){ //And the movetypes that can be made in that column
				if(board.validMove(player, col, mov)){ //Don't waste our time with an invalid move
					//Recursive call to minimax to perform our depth first search
					//Pass it the board that would result if we were to try this move, the opposite player number from the current call,
					//The fact that it is not the root call, and to only proceed for depth-1 more calls.
					int miniReturn = minimax(board.tryMove(player, col, mov), (player % 2) + 1, false, depth-1)[0];
					//If this call is player one we're trying to max.
					if(player == 1){
						//If we have the best move record the value and the move
						if(miniReturn > value){
							value = miniReturn;
							column = col;
							movetype = mov;
						}
					}
					//Otherwise we're trying to min.
					else{
						//If we have the opponent's best move record the value and the move
						if(miniReturn < value){
							value = miniReturn;
							column = col;
							movetype = mov;
						}
					}
				}
			}
		}
		
		//If root call return the move
		if(root){
			int[] bestMove = new int[2];
			bestMove[0] = column;
			bestMove[1] = movetype;
			return bestMove;
		}
		//Otherwise return the min/max value
		else{
			int[] returnValue = new int[1];
			returnValue[0] = value;
			return returnValue;
		}

	}
	
	private boolean alphaBeta()
	{
		// This should use alpha beta pruning to determine if we should continue searching down a branch
		// returning true means continue searching
		// returning false means stop searching
		return true;
	}
}
