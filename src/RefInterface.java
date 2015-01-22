import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class RefInterface 
{
	// ToDo: Add Timer
	String name = "name";
	Board boardstate;
	int playerNumber;
	int boardHeight;
	int boardWidth;
	int piecesToWin;
	int timeLimit;
	int turn = 0;
	
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
		boardstate = new Board(boardHeight, boardWidth);
		
		piecesToWin = s.nextInt();
		int firstPlayer = s.nextInt();
		timeLimit = s.nextInt();
		s.close();
		
		//If we're not the first player, wait for the board to be updated.
		if(firstPlayer != playerNumber){
			updateBoard();
		};
		return boardstate;
	}
	
	public void makeMove(int column, int movetype)
	{
		// Sends our move to the referee in the form "column movetype"
		System.out.println(column + " " + movetype);
	}
	
	public void updateBoard() throws IOException
	{
		int column;
		int movetype;
		int player;
		turn++;
		Scanner s = new Scanner(in.readLine());
		column = s.nextInt();
		if (column == -1)
		{
			//We won the game
			System.out.println("Good Game!");
		}
		else if(column == -2)
		{
			// We lost the game
			System.out.println("Congradulations");
		}
		else if(column == -3)
		{
			// We tied the game
			System.out.println("Well Played");
		}
		movetype = s.nextInt();
		s.close();
		if(((turn & 1) == 0 && playerNumber == 1) || ((turn & 1) == 1 && playerNumber == 2))
		{
			player = 1;
		}
		else
		{
			player = 2;
		}
		boardstate.makeMove(player, column, movetype);
	}	
}
