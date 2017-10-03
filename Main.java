import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static String input = "n/a";
	static int usersign = 0;
	static int counter;
	static 
	ArrayList<Node> boards = new ArrayList<Node>(); //for 9x9
	static int counter2=0;

	
	
//........................................Some Crucial Functions:.....................................................................
	

//.......................................Checks For Terminal States AND returns Utility Value.........................................
		//true translates to following: 0 if draw, terminal.  and 1 if x wins and -1 if o wins. 
	    //false translates to 2, it is NOT terminal and NOT goal state
	static boolean terminal(Node n){
			
			int[] cases = new int[8];
			cases[0] = n.pos[0] + n.pos[1] + n.pos[2]; //Case1, positions 0,1,2
			cases[1] = n.pos[3] + n.pos[4] + n.pos[5]; //Case2, positions 3,4,5
			cases[2] = n.pos[6] + n.pos[7] + n.pos[8]; //Case3, positions 6,7,8
			cases[3] = n.pos[0] + n.pos[3] + n.pos[6]; //Case4, positions 0,3,6
			cases[4] = n.pos[1] + n.pos[4] + n.pos[7]; //Case5, positions 1,4,7
			cases[5] = n.pos[2] + n.pos[5] + n.pos[8]; //Case6, positions 2,5,8
			cases[6] = n.pos[0] + n.pos[4] + n.pos[8]; //Case7, positions 0,4,8
			cases[7] = n.pos[2] + n.pos[4] + n.pos[6]; //case8, positions 2,4,6
			
			for(int i = 0; i<8; i++){
				if(cases[i]>= 3){
					//System.err.println("Goal State reached, X wins.");
					//return 1;
					return true;
				}
				else
					if(cases[i]<=-3){
						//System.err.println("Goal State reached, O wins.");
						//return -1;
						return true;
					}
			}
			
			//if no availaible spots
			if((n.pos[0]!=0 && n.pos[1]!=0 && n.pos[2]!=0 && n.pos[3]!=0 && n.pos[4]!=0 && n.pos[5]!=0 && n.pos[6]!=0 && n.pos[7]!=0 && n.pos[8]!=0)){
				//System.err.println("Terminal State reached, Draw");
				//return 0;
				return true;
			}
			else
			return false;	
		}
			

//.......................................Utility Function.....................................................
	
	static double Utility(Node n){
		
		int[] cases = new int[8];
		cases[0] = n.pos[0] + n.pos[1] + n.pos[2]; //Case1, positions 0,1,2
		cases[1] = n.pos[3] + n.pos[4] + n.pos[5]; //Case2, positions 3,4,5
		cases[2] = n.pos[6] + n.pos[7] + n.pos[8]; //Case3, positions 6,7,8
		cases[3] = n.pos[0] + n.pos[3] + n.pos[6]; //Case4, positions 0,3,6
		cases[4] = n.pos[1] + n.pos[4] + n.pos[7]; //Case5, positions 1,4,7
		cases[5] = n.pos[2] + n.pos[5] + n.pos[8]; //Case6, positions 2,5,8
		cases[6] = n.pos[0] + n.pos[4] + n.pos[8]; //Case7, positions 0,4,8
		cases[7] = n.pos[2] + n.pos[4] + n.pos[6]; //case8, positions 2,4,6
		
		for(int i = 0; i<8; i++){
			if(cases[i]>= 3){
				//System.err.println("Goal State reached, X wins.");
				return 1.00;
			}
			else
				if(cases[i]<=-3){
					//System.err.println("Goal State reached, O wins.");
					return -1.00;
				}
		}	
		
		return 0;	
	}

	
//.......................................Print Nodes (States)...............................................................................
		static Node printNode(Node n, boolean nine){
			//Prints the current State
			String xo = "J";
			for(int i = 0; i<9; i++){
				
				System.err.print((i+1) + ":" + n.pos[i] + " |");
			}
			
			if(nine == false){
			    System.err.print(" Turn : " + n.turn + "   ");
			    System.err.print("| PathCost : " + n.pathCost + "   ");
			    System.err.println();
			}
			    
			 return n;   
			}
		
		
//......................................Generate Children States....................................................
		
	static ArrayList<Node> makeChildren(Node n){
		counter++;
		ArrayList<Node> children = new ArrayList<Node>();
		if(terminal(n)==true){
			return null;
		}
	
		for(int i = 0; i<9; i++){
			if(n.pos[i] == 0){
				//counter++;
				Node child = new Node();
				child.pos = n.pos.clone();
				child.pos[i] = n.turn;
				child.pathCost = n.pathCost+1;
				child.turn = -1* n.turn;
				child.parentNode = n;
				children.add(child);
			}
		}
		
		return children;
		
	}
	

	
			
//.....................................Implementation of the MiniMax algorithm.......................................

	public static double MiniMax(Node n){
		counter++;
		ArrayList<Node> children = makeChildren(n);
		
		if(terminal(n) == true){
			return Utility(n);
		}
		
		if(n.turn==1){//Maximizing
				double bestVal = -1000;
				for(int i = 0; i<children.size(); i++){
					double val = MiniMax(children.get(i));
					bestVal = max(bestVal, val);
				}
				
				return bestVal;
			}
		
		else 
			if(n.turn==-1){//Minimizing
				double bestVal = 1000;
				for(int i = 0; i<children.size(); i++){
					double val = MiniMax(children.get(i));
					bestVal = min(bestVal, val);
				}
				
				return bestVal;
			}
		return 0;
	}
	
	
	
	public static double max(double bestVal, double val) {
		if(val>bestVal){
			return val;
		}
		else
			return bestVal;
	}
	public static double min(double bestVal, double val) {
		if(val < bestVal){
			return val;
		}
		else
			return bestVal;
	}
	
	
	

//.............................................Implementing alpha beta pruning for 3x3 to see if it works................................
	
	
	public static double alphabeta3(Node n, double alpha, double beta){
		counter++;
		if(terminal(n)==true ){
			return Utility(n);
		}
		
		ArrayList<Node> children = new ArrayList<Node>();
		children = makeChildren(n);
		
		if(n.turn==1){//Maximizing
			double v = -1000;
			for(int i = 0; i<children.size(); i++){
				double val = alphabeta3(children.get(i), alpha, beta);
				v = max(v, val);
				
				alpha = max(alpha, v);
				
				if(beta <= alpha ){
					break;
				}

			}
			
			return v;
		}
		else 
			if(n.turn==-1){//Minimizing
				double v = 1000;
				for(int i = 0; i<children.size(); i++){
					double val = alphabeta3(children.get(i), alpha, beta);
					v = min(v, val);
					
					beta = min(beta, v);
					
					if(beta <= alpha){
						break;
					}
				}
			return v;
		}
		return 0;

	}
		
	
	
	
//.................................................AI RESPONSE...............................................
	public static Node aiResponse(Node n){
		ArrayList<Node> childr = makeChildren(n);
		Node result = new Node();

		if(n.turn==1) {//maximizing
			result.utility = -1000;
			for(int i = 0; i<childr.size(); i++){
					//childr.get(i).utility = MiniMax(childr.get(i));
					childr.get(i).utility = alphabeta3(childr.get(i), -1000, 1000);
			}
			
			for(int i = 0; i<childr.size(); i++){
					if(childr.get(i).utility>result.utility){
						result = childr.get(i);
					}
			}
			
		}
			else if(n.turn==-1) {//minimizing
				result.utility = 1000;
				for(int i = 0; i<childr.size(); i++){
					//childr.get(i).utility = MiniMax(childr.get(i));
					childr.get(i).utility = alphabeta3(childr.get(i), -1000, 1000);
			}
			
			for(int i = 0; i<childr.size(); i++){
					if(childr.get(i).utility<result.utility){
						result = childr.get(i);
					}
			}
		}
		
		
		return result;
	}

//.......................................Compares children node with parent node and returns a number..............................................................................................
	
	public static int getMove(Node n){
		
		int diff = 0;
		for(int i = 0; i<9; i++){
			
			if(n.pos[i]!=n.parentNode.pos[i]){
				diff = i+1;
			}
		}
		return diff;
	}
	
	
	
	
//.......................................USER TURN.................................................................................................................................................
	public static Node userResponse(Node n){
		
		boolean invalid = true;
		
		while(invalid==true){
			System.err.println();
			System.err.println("Enter an integer 1-9" );
			int integer = scanner.nextInt();
				
			if(integer>=1 && integer<=9 && n.pos[integer-1]==0){
				n.pos[integer-1]=usersign;
				n.turn = -1*usersign;
				invalid = false;
			}
			else{
				System.err.println("Invalid. Try again.");}
			}
	
		return n;
	}

	
//......................................3X3 tic tac toe...........................................................
	
	public static void threebthree(){
		//initial greetings
		while ((input.equals("x")== false && input.equals("X")==false)  && (input.equals("o")==false && input.equals("O")==false)){
				System.err.println("Welcome to tic tac toe. Enter to play as x or o.");
				input = scanner.next();
			}
			if(input.equals("x") || input.equals("X")){
				usersign = 1; //x is 1 and o is -1
			}
			else
				usersign = -1;
			
			
			
		//Starts the new game, fills up the positions as "0", meaning empty.
			Node currentNode = new Node();
			for(int i = 0; i<9; i++){
				currentNode.pos[i] = 0;
			}
			
			//x goes first
			if(usersign==1){ //user is x
				
				while(terminal(currentNode)==false){
					Node userchoice = new Node();
					userchoice = userResponse(currentNode);
					printNode(userchoice, false);
					
					if(terminal(userchoice)==true && Utility(userchoice)==1){
						System.err.println("X wins");
						break;
					}
					else
						if(terminal(userchoice)==true && Utility(userchoice)==-1){
							System.err.println("O wins");
							break;
						}
						else 
							if(terminal(userchoice)==true && Utility(userchoice)==0){
								System.err.println("It's a Draw!");
								break;
							}
					
					currentNode = aiResponse(userchoice);
					printNode(currentNode, false);
					System.err.println("Numbers of nodes expanded : " + counter);
					System.out.println(getMove(currentNode));
					
					
									
					if(terminal(currentNode)==true && Utility(currentNode)==1){
						System.err.println("X wins");
						break;
					}
					else
						if(terminal(currentNode)==true && Utility(currentNode)==-1){
							System.err.println("O wins");
							break;
						}
						else 
							if(terminal(currentNode)==true && Utility(currentNode)==0){
								System.err.println("It's a Draw!");
								break;
							}
				}
				
			}
			else
				if(usersign==-1){ //ai is x
					currentNode.turn = 1;
					while(terminal(currentNode)==false){
						
						Node aichoice = new Node();
						aichoice = aiResponse(currentNode);
						printNode(aichoice, false);
						System.err.println("Numbers of nodes expanded : " + counter);
						System.out.println(getMove(aichoice));
						
						
						if(terminal(aichoice)==true && Utility(aichoice)==1){
							System.err.println("X wins");
							break;
						}
						else
							if(terminal(aichoice)==true && Utility(aichoice)==-1){
								System.err.println("O wins");
								break;
							}
							else 
								if(terminal(aichoice)==true && Utility(aichoice)==0){
									System.err.println("It's a Draw!");
									break;
								}
						
						currentNode = userResponse(aichoice);
						printNode(currentNode, false);
						
				
						if(terminal(currentNode)==true && Utility(currentNode)==1){
							System.err.println("X wins");
							break;
						}
						else
							if(terminal(currentNode)==true && Utility(currentNode)==-1){
								System.err.println("O wins");
								break;
							}
							else 
								if(terminal(currentNode)==true && Utility(currentNode)==0){
									System.err.println("It's a Draw!");
									break;
								}
					}
					
				}
	}
	

					
	
//.....................................Main Method....................................................................................................
	public static void main(String[] args) {
		int v = 3;
		
		while(v!=1 || v!=2){
		System.err.println("Welcome to tic tac toe. Enter 1 to play tic tac toe. Enter 2 to play 9-board Tic tac toe.");
		v = scanner.nextInt();
		if(v==1){
			threebthree();
		}
		else
			if(v==2){
				ai9b9.ninebnine();
				//ai9b9.testTerminal();
				//ai9b9.testChildren();
			}
		}	
		
	}
		
 }
	
