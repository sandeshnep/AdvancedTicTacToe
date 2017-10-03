import java.util.ArrayList;
// This is the giant board with 9 smaller nodes inside of it
public class nBoard {
	
	Node[] board = new Node[9];
	
	int turn;
	nBoard parentBoard;
	int pathCost;
	double utility;

	int currentBoard;
	int currentPos;
	int boardVal;

}
