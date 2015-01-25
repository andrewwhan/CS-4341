
public class Minimax implements Runnable {

	int[] bestMove;
	Board board;
	boolean interrupted = false;

	Minimax(Board boardstate){
		board = boardstate;
	}

	@Override
	public void run() {
		//Iterative deepening
		int depth = 1;
		while(true){
			bestMove = minimax(board, 1, true, depth, -10000, 10000);
			//System.err.println("Completed depth " + depth + " " + bestMove[0] + " " + bestMove[1]);
			depth++;
			if(interrupted){
				interrupted = false;
				return;
			}
		}
	}

	public int[] minimax(Board board, int player, boolean root, int depth, int alpha, int beta)
	{
		
		//Check for interrupt
		if(Thread.interrupted()){
			interrupted = true;
			return new int[1];
		}

		if(interrupted){
			return new int[1];
		}
		
		//Check for termination based on depth
		if(depth == 0){
			int[] returnValue = new int[1];
			returnValue[0] = new Heuristic().getValue(board);
			return returnValue;
		}
		
		//Terminal test for if game is won/lost
		if(new Heuristic().terminalTest(board)){
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
					int miniReturn = minimax(board.tryMove(player, col, mov), (player % 2) + 1, false, depth-1, alpha, beta)[0];
					//If this call is player one we're trying to max.
					if(player == 1){
						//If we have the best move record the value and the move
						if(miniReturn > value){
							value = miniReturn;
							column = col;
							movetype = mov;
							//If this branch is better for us than our opponent's worst move, the opponent won't pick it; prune.
							if(value > beta){
								int[] returnValue = new int[1];
								returnValue[0] = value;
								//System.err.println("Prune " + value + " > " + beta);
								return returnValue;
							}
							//If we have a better move than alpha record it
							if(value > alpha){
								//System.err.println("Set alpha to " + value);
								alpha = value;
							}
						}
					}
					//Otherwise we're trying to min.
					else{
						//If we have the opponent's best move record the value and the move
						if(miniReturn < value){
							value = miniReturn;
							column = col;
							movetype = mov;
							//If this branch is worse than our best move we won't pick it; prune.
							if(value < alpha){
								int[] returnValue = new int[1];
								returnValue[0] = value;
								//System.err.println("Prune " + value + " < " + alpha);
								return returnValue;
							}
							//If the opponent has a better move than beta record it
							if(value < beta){
								//System.err.println("Set beta to " + value);
								beta = value;
							}
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

}
