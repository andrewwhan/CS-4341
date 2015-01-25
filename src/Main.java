import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;


public class Main 
{
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		RefInterface refInt = new RefInterface();
		refInt.announce();
		Board board = refInt.gameStart();
		Minimax minimax = new Minimax(board);
		Heuristic.adjacencyMultiplier = new Random().nextInt(10);
		Heuristic.winningSpaceMultiplier = new Random().nextInt(10);
		Heuristic.centralMultiplier = new Random().nextInt(10);
		Heuristic.heightMultiplier = new Random().nextInt(10);
		Heuristic.bottomMultiplier = new Random().nextInt(10);
		Heuristic.popoutMultiplier = new Random().nextInt(10);
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("weights2.csv", true)))) {
		    out.print(Heuristic.adjacencyMultiplier + "," + Heuristic.winningSpaceMultiplier + "," +
		    		Heuristic.centralMultiplier + "," + Heuristic.heightMultiplier + "," +
		    		Heuristic.bottomMultiplier + "," + Heuristic.popoutMultiplier + ",");
		}catch (IOException e) {}
		
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
