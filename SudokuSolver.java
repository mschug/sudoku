public class SudokuSolver {

	private final int EMPTY = 0;
	/* Default constructor. */
	public SudokuSolver(){}
	
 	/*
 	 * Solves a 9x9 Sudoku grid and returns a filled-in array.
 	 */
 	public boolean solveBoard( int[][] board ) {
 		// Board dimensions are wrong, so the puzzle cannot possibly be solved.
 		if( board.length != 9 || board[0].length != 9 ){
 			return false;
 		}
 		
 		// find and set the square to solve
 		int[] location = new int[2];
 		if( !getNextLocation( location, board ) ){
 			return true;
 		}
 		
 		int x = location[0];
 		int y = location[1];
 		
 		for( int num = 1; num <= 9; num++ ){
 			
 			if( isValid( board, x, y, num ) ){
 				board[x][y] = num;
 				
 				// recursively solve board
 				if( solveBoard(board) ){
 					return true;
 				}
 				
 				// invalid solution, retry
 				board[x][y] = EMPTY; 
 			}
 			
 		}
 		
 		// square is invalid, backtrack
 		return false;
 	}
 	
 	/*
 	 * Finds and sets the next empty square in the Sudoku grid. 
 	 */
 	private boolean getNextLocation( int[] current, int[][] board ){
 		
 		for( int x = 0; x < board.length; x++ ){
 			for( int y = 0; y < board[0].length; y++ ){
 				if( board[x][y] == EMPTY ){
 					// set the location for the next square to solve
 					current[0] = x;
 					current[1] = y;
 					return true;
 				}
 			}
 		}
 		return false;
 		
 	}
 	
 	/*
 	 * Checks if a row does not contain more than one copy of a number.
 	 */
 	private boolean validRow( int[][] board, int x, int y, int num ){
 		if( board[x][y] != EMPTY )
 			return false;
 		
 		for( int i = 0; i < board.length; i++ ){
 			if( i != y && board[x][i] == num ){
 				return false;
 			}
 		}
 		return true;
 	}
 	
 	/*
 	 * Checks if a column does not contain more than one copy of a number.
 	 */
 	private boolean validColumn( int[][] board, int x, int y, int num ){
 		if( board[x][y] != EMPTY )
 			return false;
 		
 		for( int i = 0; i < board.length; i++ ){
 			if( i != x && board[i][y] == num ){
 				return false;
 			}
 		}
 		return true;
 	}
 	
 	/*
 	 * Checks if a 3x3 box does not contain more than one copy of a number.
 	 */
 	private boolean validBox( int[][] board, int x, int y, int num ){
 		if( board[x][y] != EMPTY )
 			return false;
 		
 		// get corner of the 3x3 box
 		int boxLeft = x - x % 3;
 		int boxTop = y - y % 3;
 		
 		for( int tempX = boxLeft; tempX < boxLeft + 3; tempX++ ){
 	 		for( int tempY = boxTop; tempY < boxTop + 3; tempY++ ){
 	 			if( tempX != x && tempY != y && board[tempX][tempY] == num ){
 	 				return false;
 	 			}
 	 		}
 		}
 		return true;
 	}
 	
 	/*
 	 * Checks whether the current cell is valid.
 	 */
 	private boolean isValid( int[][] board, int x, int y, int num ){
 		return validRow( board, x, y, num ) && validColumn( board, x, y, num ) && validBox( board, x, y, num );
 	}
 	
 	/*
 	 * Prints a 9x9 Sudoku board, with box boundaries, to the console.
 	 */
 	public void printBoard( int[][] board ){
 		// Board needs to be 9 by 9 in order to print correctly. 
 		if( board.length != 9 || board[0].length != 9 ){
 			return;
 		}
 		
 		for( int x = 0; x < 9; x++ ){
 			for( int y = 0; y < 9; y++ ){
 				System.out.print( board[x][y] );
 				if( y % 3 == 2 && y != 8 ){
 					System.out.print("|");
 				}
 			}
 			System.out.println();
 			if( x % 3 == 2 && x != 8 ){
 				System.out.println( "---+---+---" );
 			}
 		}
 		
 		System.out.println();
 	}

	
}
