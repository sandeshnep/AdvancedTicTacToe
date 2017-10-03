import java.util.ArrayList;
import java.util.Random;

public class ai9b9 extends Main{
	
//.......................................Checks if the given board is a Terminal........................

	static int terminal9(nBoard n){ //this is essentially a boolean. 1 is x wins, -1 is 0 wins and 0 is draw and 5 is FALSE
		
		//Checks if anyone wins
		for(int i = 0; i<9 ; i++){
			if(terminal(n.board[i])==true && Utility(n.board[i])==1){ // X wins
				return 1;
			}
			else
				if(terminal(n.board[i])==true && Utility(n.board[i])==-1){//O wins
					return -1;
				}		
		}
		
		//checks if the entire game is a draw
		int counter2 = 0;
		for(int i = 0; i<9; i++){
			if(terminal(n.board[i])==true && Utility(n.board[i])==0){
				counter2++;
			}
		}
		
		if(counter2>=9){
			return 0; //Entire game is a draw
		}
		
		//if neither a draw, nor a win, terminal is false. 5 for false
		return 5;
	}

	
//...........................................A simple Utility function for 9b9..........................
	
	static double Utility9(nBoard n){
		if(terminal9(n)==1){ //X win
			return 1.00; 
		}
		else
			if(terminal9(n)==-1){ //O win
				return -1.00;
			}
		
		return 0; //Draw Full. Gotta make sure that terminal!=5 
	}
	
	
//.....................................Returns if a specified Node is full............................
	
static boolean isFull(Node n){
		
		if(terminal(n)==true && Utility(n)==0){
			return true;
		}
		else
			return false;
}


//.........................................Heuristic Function......................................................................

/**Here is how I propose to apply the Heuristic function:
 * 
 * 1) It takes a big 9-board as an input
 * 2) It iterates through each of the smaller 9 boards
 * 3) for each smaller board (Node):
 * 		* there are 7 cases, for each Cases:
 * 		* If there is 1 X with 2 0's then H = H+0.01
 * 		* If there are 2 x's with 1 0, then H = H+0.10
 * 		* If there are 3 x's, then H = H +1
 * 		* The values are negative for O's
 * 4) The final H value of the board is the average of all the values for the smaller boards
 */


static double H(nBoard boards){
	double avg;
	ArrayList<Double> values = new ArrayList<Double>();
	for(int i = 0; i<9; i++){
		Node n = boards.board[i];
		
		double value = 0;
		
		ArrayList<Integer> case0 = new ArrayList<Integer>();
		ArrayList<Integer> case1 = new ArrayList<Integer>();
		ArrayList<Integer> case2 = new ArrayList<Integer>();
		ArrayList<Integer> case3 = new ArrayList<Integer>();
		ArrayList<Integer> case4 = new ArrayList<Integer>();
		ArrayList<Integer> case5 = new ArrayList<Integer>();
		ArrayList<Integer> case6 = new ArrayList<Integer>();
		ArrayList<Integer> case7 = new ArrayList<Integer>();
	
		
		case0.add(n.pos[0]); case0.add(n.pos[1]); case0.add(n.pos[2]);
		case1.add(n.pos[3]); case1.add(n.pos[4]); case1.add(n.pos[5]);
		case2.add(n.pos[6]); case2.add(n.pos[7]); case2.add(n.pos[8]);
		case3.add(n.pos[0]); case3.add(n.pos[3]); case3.add(n.pos[6]);
		case4.add(n.pos[1]); case4.add(n.pos[4]); case4.add(n.pos[7]);
		case5.add(n.pos[2]); case5.add(n.pos[5]); case5.add(n.pos[8]);
		case6.add(n.pos[0]); case6.add(n.pos[4]); case6.add(n.pos[8]);
		case7.add(n.pos[2]); case7.add(n.pos[4]); case7.add(n.pos[6]);
		
		//for case 0
		if(case0.contains(-1)==false){ // no O's
			if(case0.get(0) + case0.get(1) + case0.get(2) == 1){ // 1 X
				value = value + 0.01;
			}
			else
				if(case0.get(0) + case0.get(1) + case0.get(2) == 2){ // 2 X
					value = value + 0.10;
				}
				else
					if(case0.get(0) + case0.get(1) + case0.get(2) == 3){ // 3 X
						value = value + 1.00;
					}	
		}
		else
			if(case0.contains(1)==false){ // no X's
				if(case0.get(0) + case0.get(1) + case0.get(2) == -1){ // 1 O
					value = value - 0.01;
				}
				else
					if(case0.get(0) + case0.get(1) + case0.get(2) == -2){ // 2 O
						value = value - 0.10;
					}
					else
						if(case0.get(0) + case0.get(1) + case0.get(2) == -3){ // 3 O
							value = value - 1.00;
						}	
			}
		
		
		 //for case1
				if(case1.contains(-1)==false){ // no O's
					if(case1.get(0) + case1.get(1) + case1.get(2) == 1){ // 1 X
						value = value + 0.01;
					}
					else
						if(case1.get(0) + case1.get(1) + case1.get(2) == 2){ // 2 X
							value = value + 0.10;
						}
						else
							if(case1.get(0) + case1.get(1) + case1.get(2) == 3){ // 3 X
								value = value + 1.00;
							}	
				}
				else
					if(case1.contains(1)==false){ // no X's
						if(case1.get(0) + case1.get(1) + case1.get(2) == -1){ // 1 O
							value = value - 0.01;
						}
						else
							if(case1.get(0) + case1.get(1) + case1.get(2) == -2){ // 2 O
								value = value - 0.10;
							}
							else
								if(case1.get(0) + case1.get(1) + case1.get(2) == -3){ // 3 O
									value = value - 1.00;
								}	
					}
				
				//for case2
				if(case2.contains(-1)==false){ // no O's
					if(case2.get(0) + case2.get(1) + case2.get(2) == 1){ // 1 X
						value = value + 0.01;
					}
					else
						if(case2.get(0) + case2.get(1) + case2.get(2) == 2){ // 2 X
							value = value + 0.10;
						}
						else
							if(case2.get(0) + case2.get(1) + case2.get(2) == 3){ // 3 X
								value = value + 1.00;
							}	
				}
				else
					if(case2.contains(1)==false){ // no X's
						if(case2.get(0) + case2.get(1) + case2.get(2) == -1){ // 1 O
							value = value - 0.01;
						}
						else
							if(case2.get(0) + case2.get(1) + case2.get(2) == -2){ // 2 O
								value = value - 0.10;
							}
							else
								if(case2.get(0) + case2.get(1) + case2.get(2) == -3){ // 3 O
									value = value - 1.00;
								}	
					}
				
				
				//for case3
				if(case3.contains(-1)==false){ // no O's
					if(case3.get(0) + case3.get(1) + case3.get(2) == 1){ // 1 X
						value = value + 0.01;
					}
					else
						if(case3.get(0) + case3.get(1) + case3.get(2) == 2){ // 2 X
							value = value + 0.10;
						}
						else
							if(case3.get(0) + case3.get(1) + case3.get(2) == 3){ // 3 X
								value = value + 1.00;
							}	
				}
				else
					if(case3.contains(1)==false){ // no X's
						if(case3.get(0) + case3.get(1) + case3.get(2) == -1){ // 1 O
							value = value - 0.01;
						}
						else
							if(case3.get(0) + case3.get(1) + case3.get(2) == -2){ // 2 O
								value = value - 0.10;
							}
							else
								if(case3.get(0) + case3.get(1) + case3.get(2) == -3){ // 3 O
									value = value - 1.00;
								}	
					}
				
				
				//for case4
				if(case4.contains(-1)==false){ // no O's
					if(case4.get(0) + case4.get(1) + case4.get(2) == 1){ // 1 X
						value = value + 0.01;
					}
					else
						if(case4.get(0) + case4.get(1) + case4.get(2) == 2){ // 2 X
							value = value + 0.10;
						}
						else
							if(case4.get(0) + case4.get(1) + case4.get(2) == 3){ // 3 X
								value = value + 1.00;
							}	
				}
				else
					if(case4.contains(1)==false){ // no X's
						if(case4.get(0) + case4.get(1) + case4.get(2) == -1){ // 1 O
							value = value - 0.01;
						}
						else
							if(case4.get(0) + case4.get(1) + case4.get(2) == -2){ // 2 O
								value = value - 0.10;
							}
							else
								if(case4.get(0) + case4.get(1) + case4.get(2) == -3){ // 3 O
									value = value - 1.00;
								}	
					}
				
				
				//for case5
				if(case5.contains(-1)==false){ // no O's
					if(case5.get(0) + case5.get(1) + case5.get(2) == 1){ // 1 X
						value = value + 0.01;
					}
					else
						if(case5.get(0) + case5.get(1) + case5.get(2) == 2){ // 2 X
							value = value + 0.10;
						}
						else
							if(case5.get(0) + case5.get(1) + case5.get(2) == 3){ // 3 X
								value = value + 1.00;
							}	
				}
				else
					if(case5.contains(1)==false){ // no X's
						if(case5.get(0) + case5.get(1) + case5.get(2) == -1){ // 1 O
							value = value - 0.01;
						}
						else
							if(case5.get(0) + case5.get(1) + case5.get(2) == -2){ // 2 O
								value = value - 0.10;
							}
							else
								if(case5.get(0) + case5.get(1) + case5.get(2) == -3){ // 3 O
									value = value - 1.00;
								}	
					}
				
				
				
				//for case6
				if(case6.contains(-1)==false){ // no O's
					if(case6.get(0) + case6.get(1) + case6.get(2) == 1){ // 1 X
						value = value + 0.01;
					}
					else
						if(case6.get(0) + case6.get(1) + case6.get(2) == 2){ // 2 X
							value = value + 0.10;
						}
						else
							if(case6.get(0) + case6.get(1) + case6.get(2) == 3){ // 3 X
								value = value + 1.00;
							}	
				}
				else
					if(case6.contains(1)==false){ // no X's
						if(case6.get(0) + case6.get(1) + case6.get(2) == -1){ // 1 O
							value = value - 0.01;
						}
						else
							if(case6.get(0) + case6.get(1) + case6.get(2) == -2){ // 2 O
								value = value - 0.10;
							}
							else
								if(case6.get(0) + case6.get(1) + case6.get(2) == -3){ // 3 O
									value = value - 1.00;
								}	
					}
				
				
				//for case7
				if(case7.contains(-1)==false){ // no O's
					if(case7.get(0) + case7.get(1) + case7.get(2) == 1){ // 1 X
						value = value + 0.01;
					}
					else
						if(case7.get(0) + case7.get(1) + case7.get(2) == 2){ // 2 X
							value = value + 0.10;
						}
						else
							if(case7.get(0) + case7.get(1) + case7.get(2) == 3){ // 3 X
								value = value + 1.00;
							}	
				}
				else
					if(case7.contains(1)==false){ // no X's
						if(case7.get(0) + case7.get(1) + case7.get(2) == -1){ // 1 O
							value = value - 0.01;
						}
						else
							if(case7.get(0) + case7.get(1) + case7.get(2) == -2){ // 2 O
								value = value - 0.10;
							}
							else
								if(case7.get(0) + case7.get(1) + case7.get(2) == -3){ // 3 O
									value = value - 1.00;
								}	
					}
				
				
				values.add(value);
	
			
	}
	
	double sum = 0;
	for(int i = 0; i<values.size(); i++){
		sum +=values.get(i);
	}
	
	avg = sum/9.00;
	
	return avg;
}




	
//.......................................Print Boards (States)...............................................................................
	static void printBoard(nBoard n){
		//Prints the current State
		for(int i = 0; i<9; i++){
			printNode(n.board[i], true);
			System.err.print("Board: " + i );
			System.err.println();
		}
				
		    System.err.print("-- Turn : " + n.turn + "   ");
		    System.err.print("| PathCost : " + n.pathCost + "   ");

		    System.err.println("Board: " + n.currentBoard + " Pos: " + n.currentPos);
				     
	}



//.......................................USER TURN.................................................................................................................................................
	public static nBoard userResponse9(nBoard n){
		
		 n.pathCost = 0;
		//N's position will become the new board
		int boardpos = n.currentPos;
		boolean invalid = true;
		while(invalid==true){
			System.err.println();
			System.err.println("Enter two digits. The first digit specifies the board, and the second digit specifies the position within that board." );
			String digits = scanner.next();
			String b = digits.substring(0, 1);
			String p = digits.substring(1,2);
			
			int board = Integer.parseInt(b);
			int pos = Integer.parseInt(p);
			
			if(board>=1 && board<=9 && pos>=1 && pos<=9 && n.board[board-1].pos[pos-1]==0){
				n.board[board-1].pos[pos-1]=usersign;
				n.turn = -1*usersign;
				n.currentBoard = board;
				n.currentPos = pos;

				invalid = false;
			}
			else{
				System.err.println("Invalid. Try again.");}
							
		}
		return n;
	}


	
//.......................................Generate Children success boards..............................
	
	static ArrayList<nBoard> makeChildren9(nBoard n){
		counter++;
		
		if(terminal9(n)!=5){ //terminal is not false, meaning it is true
			return null;
		}
		//System.err.println(counter);

		ArrayList<nBoard> children = new ArrayList<nBoard>();
	
		//Now if the given node is not full, all the moves will have to be in that board
		//System.err.println("Testing, current Board : " + n.currentPos);
		int board = n.currentPos;
		
		
		if(isFull(n.board[board-1])==false){
			
			for(int i = 0; i<9; i++){
				if(n.board[board-1].pos[i] == 0){
					nBoard child = new nBoard();
					child.board[0] = new Node();child.board[1] = new Node();child.board[2] = new Node();child.board[3] = new Node();child.board[4] = new Node();child.board[5] = new Node();child.board[6] = new Node();child.board[7] = new Node();child.board[8] = new Node();
					for(int j = 0; j<9; j++){
						child.board[j].pos = n.board[j].pos.clone();
					}
					
					child.board[board-1].pos[i] = n.turn; //makes the move
					child.pathCost = n.pathCost+1;
					child.turn = -1*n.turn;
					child.parentBoard = n;
					
					child.currentBoard = board;
					child.currentPos = i+1;
					//printBoard(child);
					children.add(child);
				}
			}
			return children;
		}
		else{//If the given board IS full, you can move anywhere. A huge number of children can be created

			for(int i = 0; i<9; i++){
				
				for(int j = 0; j<9; j++){
					
					if(n.board[i].pos[j] == 0){
						nBoard child = new nBoard();
						child.board[0] = new Node();child.board[1] = new Node();child.board[2] = new Node();child.board[3] = new Node();child.board[4] = new Node();child.board[5] = new Node();child.board[6] = new Node();child.board[7] = new Node();child.board[8] = new Node();
						for(int k = 0; k<9; k++){
							child.board[k].pos = n.board[k].pos.clone();
						}
						
						child.board[i].pos[j] = n.turn; //makes the move
						child.pathCost = n.pathCost+1;
						child.turn = -1* n.turn;
						child.parentBoard = n;
						child.currentBoard = i+1;
						child.currentPos = j+1;
						
					//	printBoard(child);
						children.add(child);
					}
				}	
				
			}
			return children;
		}
}

	
	
//.....................................Implementation of the MiniMax algorithm 9 version...........................................................................................................

	public static double MiniMax9(nBoard n){
			
		counter++;
			if(terminal9(n)!=5 ){
				return Utility9(n);
			}
			
			ArrayList<nBoard> children = new ArrayList<nBoard>();
			children = makeChildren9(n);
	
			
			if(n.turn==1){//Maximizing
					double bestVal = -1000;
					for(int i = 0; i<children.size(); i++){
						double val = MiniMax9(children.get(i));
						bestVal = max(bestVal, val);
					}
					
					return bestVal;
				}
			else 
				if(n.turn==-1){//Minimizing
					double bestVal = 1000;
					for(int i = 0; i<children.size(); i++){
						double val = MiniMax9(children.get(i));
						bestVal = min(bestVal, val);
					}
					return bestVal;
				}
			return 0;
		}
		

//................................Here is an attempt at Alpha beta pruning................................................
	
	//I modified the alpha beta to include Heuristic cut offs
	public static double alphabeta(nBoard n, double alpha, double beta){
		counter++;
		/*if(terminal9(n)!=5 ){
			return Utility9(n);
		}
		else*/
		if(n.pathCost>=7 || terminal9(n)!=5){
				return H(n);
		}
		
		//Heuristic function not working properly
		ArrayList<nBoard> children = new ArrayList<nBoard>();
		children = makeChildren9(n);
		
		if(n.turn==1){//Maximizing
			double v = -1000;
			for(int i = 0; i<children.size(); i++){
				double val = alphabeta(children.get(i), alpha, beta);
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
					double val = alphabeta(children.get(i), alpha, beta);
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
		
//.................................................AI RESPONSE............................................................................
	public static nBoard aiResponse9(nBoard n){
		ArrayList<nBoard> childr = makeChildren9(n);
		nBoard result = new nBoard();

			if(n.turn==1) {//maximizing
				result.utility = -1000;
				for(int i = 0; i<childr.size(); i++){
						//childr.get(i).utility = MiniMax9(childr.get(i));
						childr.get(i).utility = alphabeta(childr.get(i), -1000, 1000);
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
						//childr.get(i).utility = MiniMax9(childr.get(i));
						childr.get(i).utility = alphabeta(childr.get(i), -1000, 1000);
				}
				
				for(int i = 0; i<childr.size(); i++){
						if(childr.get(i).utility<result.utility){
							result = childr.get(i);
						}
				}
			}
			
			
			return result;
		}
	
	
	
	
	

	public static void ninebnine() {
				
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
					
					
					
				//Starts the new game, initial settings of a nbn board
					nBoard currentBoard = new nBoard();
					currentBoard.board[0] = new Node(); currentBoard.board[1] = new Node();currentBoard.board[2] = new Node();currentBoard.board[3] = new Node();currentBoard.board[4] = new Node();currentBoard.board[5] = new Node();currentBoard.board[6] = new Node();currentBoard.board[7] = new Node();currentBoard.board[8] = new Node();
					for(int i = 0; i<9; i++){ //board
						for(int j = 0; j<9; j++){ //position
							currentBoard.board[i].pos[j] = 0;
						}	
					}
					
					//x goes first
					if(usersign==1){ //user is x
						
						while(terminal9(currentBoard)==5){
							nBoard userchoice = new nBoard();
							userchoice = userResponse9(currentBoard);
							printBoard(userchoice);
							System.err.println("Heuristic : " + H(userchoice));
							
							if(terminal9(userchoice)==1){
								System.err.println("X wins");
								break;
							}
							else
								if(terminal9(userchoice)==-1){
									System.err.println("O wins");
									break;
								}
								else 
									if(terminal9(userchoice)==0){
										System.err.println("It's a Draw!");
										break;
									}
							
							
							//AI turn
							currentBoard = aiResponse9(userchoice);
							printBoard(currentBoard);
							System.err.println("Heuristic : " + H(currentBoard));
							
							System.err.println("Numbers of nodes expanded : " + counter);

							System.out.println(Integer.toString(currentBoard.currentBoard) + Integer.toString(currentBoard.currentPos));
	
							
											
							if(terminal9(currentBoard)==1){
								System.err.println("X wins");
								break;
							}
							else
								if(terminal9(currentBoard)==-1){
									System.err.println("O wins");
									break;
								}
								else 
									if(terminal9(currentBoard)==0){
										System.err.println("It's a Draw!");
										break;
									}
						}
						
					}
					else
						if(usersign==-1){ //ai is x
							currentBoard.turn = 1;
							currentBoard.currentPos = 5;
							while(terminal9(currentBoard)==5){
								
								nBoard aichoice = new nBoard();
								aichoice = aiResponse9(currentBoard);
								printBoard(aichoice);
								
								System.err.println("Utility : " + aichoice.utility);
								System.err.println("Numbers of nodes expanded : " + counter);
								
								
								if(terminal9(aichoice)==1){
									System.err.println("X wins");
									break;
								}
								else
									if(terminal9(aichoice)==-1){
										System.err.println("O wins");
										break;
									}
									else 
										if(terminal9(aichoice)==0){
											System.err.println("It's a Draw!");
											break;
										}
								
								currentBoard = userResponse9(aichoice);
								printBoard(currentBoard);
								
						
								if(terminal9(currentBoard)==1){
									System.err.println("X wins");
									break;
								}
								else
									if(terminal9(currentBoard)==-1){
										System.err.println("O wins");
										break;
									}
									else 
										if(terminal9(currentBoard)==0){
											System.err.println("It's a Draw!");
											break;
										}
							}
							
						}
		
	}
	
	
//.......................................Debugging...........................................

//.......................................Testing Terminal State.............................
//works
	public static nBoard debug = new nBoard();
	
	static void testTerminal(){
		debug.board[0] = new Node(); debug.board[1] = new Node();debug.board[2] = new Node();debug.board[3] = new Node();debug.board[4] = new Node();debug.board[5] = new Node();debug.board[6] = new Node();debug.board[7] = new Node();debug.board[8] = new Node();
		Node test = debug.board[0];
		test.pos[0] = 1;test.pos[1] = -1;test.pos[2] = 1;
		test.pos[3] = -1;test.pos[4] = 1;test.pos[5] = -1;test.pos[6] = -1;test.pos[7] = 1;test.pos[8] = -1;
		
		Node test2 = debug.board[1];
		test2.pos[0] = 1;test2.pos[1] = -1;test2.pos[2] = 1;
		test2.pos[3] = -1;test2.pos[4] = 1;test2.pos[5] = -1;test2.pos[6] = -1;test2.pos[7] = 1;test2.pos[8] = -1;

		Node test3 = debug.board[2];
		test3.pos[0] = 1;test3.pos[1] = -1;test3.pos[2] = 1;
		test3.pos[3] = -1;test3.pos[4] = 1;test3.pos[5] = -1;test3.pos[6] = -1;test3.pos[7] = 1;test3.pos[8] = -1;

		Node test4 = debug.board[3];
		test4.pos[0] = 1;test4.pos[1] = -1;test4.pos[2] = 1;
		test4.pos[3] = -1;test4.pos[4] = 1;test4.pos[5] = -1;test4.pos[6] = -1;test4.pos[7] = 1;test4.pos[8] = -1;
		

		Node test5 = debug.board[4];
		test5.pos[0] = 1;test5.pos[1] = -1;test5.pos[2] = 1;
		test5.pos[3] = -1;test5.pos[4] = 1;test5.pos[5] = -1;test5.pos[6] = -1;test5.pos[7] = 1;test5.pos[8] = -1;

		Node test6 = debug.board[5];
		test6.pos[0] = 1;test6.pos[1] = -1;test6.pos[2] = 1;
		test6.pos[3] = -1;test6.pos[4] = 1;test6.pos[5] = -1;test6.pos[6] = -1;test6.pos[7] = 1;test6.pos[8] = -1;

		Node test7 = debug.board[6];
		test7.pos[0] = 1;test7.pos[1] = -1;test7.pos[2] = 1;
		test7.pos[3] = -1;test7.pos[4] = 1;test7.pos[5] = -1;test7.pos[6] = -1;test7.pos[7] = 1;test7.pos[8] = -1;

		Node test8 = debug.board[7];
		test8.pos[0] = 1;test8.pos[1] = -1;test8.pos[2] = 1;
		test8.pos[3] = -1;test8.pos[4] = 1;test8.pos[5] = -1;test8.pos[6] = -1;test8.pos[7] = 1;test8.pos[8] = -1;
		
		Node test9 = debug.board[8];
		test9.pos[0] = 1;test9.pos[1] = -1;test9.pos[2] = 1;
		test9.pos[3] = -1;test9.pos[4] = 1;test9.pos[5] = -1;test9.pos[6] = -1;test9.pos[7] = 1;test9.pos[8] = -1;
		
		System.err.println(terminal9(debug));
		
	}
	
//.......................................Testing Make Children..................................................................................................................................
	
	//make children is messed up? Seems to work for now.
	
	static void testChildren(){
		debug.board[0] = new Node(); debug.board[1] = new Node();debug.board[2] = new Node();debug.board[3] = new Node();debug.board[4] = new Node();debug.board[5] = new Node();debug.board[6] = new Node();debug.board[7] = new Node();debug.board[8] = new Node();
		Node test = debug.board[0];
		test.pos[0] = 1;test.pos[1] = -1;test.pos[2] = 1;
		test.pos[3] = -1;test.pos[4] = 1;test.pos[5] = -1;test.pos[6] = -1;test.pos[7] = 1;test.pos[8] = -1;
		
		usersign = 1;
		debug.turn = -1;
		debug.currentBoard = 1; debug.currentPos = 1;
		
		
		for(int i = 1; i<9; i++){
			for(int j = 0; j<9; j++){
				debug.board[i].pos[j]=0;
			}
		}
		
		ArrayList<nBoard> childr = makeChildren9(debug);
	
		
	}
	
	
	
}
