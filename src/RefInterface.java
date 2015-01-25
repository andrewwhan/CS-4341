import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;


public class RefInterface 
{
	// ToDo: Add Timer
	String name = "AlexAndrew";
	Board board;
	int playerNumber;
	int boardHeight;
	int boardWidth;
	int piecesToWin;
	int timeLimit;
	
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public void announce() throws IOException
	{
		//Announce our name to the referee
		System.out.println(name);
		
		//Identify whether we are the first or second player
		Scanner s = new Scanner(in.readLine());
		s.next(); //Skip text "player1:"
		if(s.hasNext(name)){ //If our name is the next token we're player 1, otherwise we're player 2
			playerNumber = 1;
		}
		else{
			playerNumber = 2;
		}
		System.err.println("We're player " + playerNumber);
		s.close();
	}

	public Board gameStart() throws IOException
	{
		/* Retrieves game information from the referee.
		 * Returns the empty board if we are playing first, or the board with the opponent's move made if we are playing second
		 */
		Scanner s = new Scanner(in.readLine());
		boardHeight = s.nextInt();
		boardWidth = s.nextInt();
		piecesToWin = s.nextInt();
		board = new Board(boardHeight, boardWidth, piecesToWin);
		
		int firstPlayer = s.nextInt();
		timeLimit = s.nextInt();
		s.close();
		
		//If we're not the first player, wait for the board to be updated.
		if(firstPlayer != playerNumber){
			updateBoard();
		};
		return board;
	}
	
	public void makeMove(int column, int movetype)
	{
		// Sends our move to the referee in the form "column movetype"
		System.out.println(column + " " + movetype);
	}
	
	public void updateBoard() throws IOException
	{
		String column;
		int movetype;
		Scanner s = new Scanner(in.readLine());
		column = s.next();
		if (column.equals("win"))
		{
			// We won the game
			//System.err.println("Good Game!");
		}
		else if(column.equals("lose"))
		{
			// We lost the game
			//System.err.println("Congratulations");
		}
		else if(column.equals("draw"))
		{
			// We tied the game
			//System.err.println("Well Played");
		}
		movetype = s.nextInt();
		s.close();
		// For our internal representation of the boardstate our opponent's pieces are 2's
		board.makeMove(2, Integer.parseInt(column), movetype);
	}	
}
