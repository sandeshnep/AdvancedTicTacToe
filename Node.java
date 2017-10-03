import java.util.ArrayList;
import java.util.List;

//Structure of a Node
public class Node {
	//the following describes the State
	 int[] pos = new int[9];
	 int turn;
	 
	 Node parentNode; //not really used
	 
	 int pathCost;
	 double utility;
	 double boardVal;

}
