import java.io.IOException;


public class Main 
{
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		RefInterface refInt = new RefInterface();
		refInt.announce();
		Board board = refInt.gameStart();
		Minimax minimax = new Minimax(board);
		while(true){
			Thread minThread = new Thread(minimax);
			minThread.start();
			Thread.sleep(refInt.timeLimit * 1000 - 500);
			int[] bestMove = minimax.bestMove.clone();
			minThread.interrupt();
			board.makeMove(1, bestMove[0], bestMove[1]);
			refInt.makeMove(bestMove[0], bestMove[1]);
			refInt.updateBoard();
		}
	}
}
