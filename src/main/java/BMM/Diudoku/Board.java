package BMM.Diudoku;

/**
 * This class represent the board of the game
 * @author Boaglio Agustin
 * @author Menendez Josue
 * @author Magnoli Roman
 *
 */
public class Board {

    /**
     * Represent the number of the board rows
     */
    private static final Integer ROWS = 9;

    /**
     * Represent the number of the board columns
     */
    private static final Integer COLUMNS = 9;

    /**
     * Matrix to represent the board
     */
    private Integer [][] board;

    /**
     * Number represent value of the board
     */
    private Integer value = 0;
    /**
     * Constructor of the Board class that make a board of 9x9
     */
    public Board () {
        this.board = new Integer[ROWS][COLUMNS];
        for (Integer row = 0; row < ROWS; row++) {
            for (Integer col = 0; col < COLUMNS; col++) {
                this.board[row][col] = 0;
            }
        }
    }

    /**
     * Set a value in a specific cell of the board
     * @param value is the number to set in the specific position
     * @param row is the index of the row of the board to set the value
     * @param col is the index of the column of the board to set the value
     */
    public void setCell(Integer value, Integer row, Integer col) {
        if ((this.isValidPos(row, col) == false )) 
          throw new IllegalArgumentException("Invalid Position");
        if ((this.isValidNumber(value, row, col) == false)) 
          throw new IllegalArgumentException("This value can't be located here, because it is already in the same row, column or region");
        this.board[row][col] = value;
    }

    /**
     * Determine if a specific position is valid to be occupied
     * @param row is the index of the row to verify the position
     * @param col is the index of the column to verify the position
     * @return true if position is a valid position
     */
    private boolean isValidPos(Integer row, Integer col) {
        if ((row > ROWS) || (col > COLUMNS) || (this.board[row][col] != 0)) return false;
        return true;
    }

    /**
     * Determine if a value can be located on the position, according to the
     * sudoku's rulers
     * @param value is the number to establish
     * @param row is the index of the row to verify the position
     * @param col is the index of the column to verify the position
     * @return true if the value can be located on the position
     */
    public boolean isValidNumber(Integer value, Integer row, Integer col) {
        if (((value <= 9) || (value >= 1)) && (this.isValidRow(row, value)) && (this.isValidColumns(col, value)) && (this.isValidRegion(row, col, value))) 
          return true;
        return false;
    }

    /**
     * Determine if a value can be located in a row, according to the sudoku's rulers
     * @param row is the index to verify the row
     * @param value is the number to set on the board
     * @return true if the number can be located
     */
    private boolean isValidRow(Integer row, Integer value) {
        for (Integer colIndex = 0; colIndex < COLUMNS; colIndex++) {
            if (this.board[row][colIndex] == value) return false;
        }
        return true;
    }

    /**
     * Determine if a value can be located in a column, according to the sudoku's rulers
     * @param col is the index to verify the column
     * @param value is the number to set on the board
     * @return true if the number can be located
     */
    private boolean isValidColumns(Integer col, Integer value) {
        for (Integer rowIndex = 0; rowIndex < ROWS; rowIndex++) {
            if (this.board[rowIndex][col] == value) return false;
        }
        return true;
    }

    /**
     * Determine if a value can be located in a 3x3 region, according to the sudoku's rulers
     * @param row is the index of row to verify the region
     * @param col is the index of column to verify the region
     * @param value is the number to set in a region
     * @return true if the value can be located
     */
    private boolean isValidRegion(Integer row, Integer col, Integer value) {
        Integer region = this.determineRegion(row, col);
        switch (region) {
            case 1 :
                for(Integer rowIndex = 0; rowIndex < 3; rowIndex++) {
                    for (Integer colIndex = 0; colIndex < 3; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;

            case 2 :
                for(Integer rowIndex = 0; rowIndex < 3; rowIndex++) {
                    for (Integer colIndex = 3; colIndex < 6; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;

            case 3 :
                for(Integer rowIndex = 0; rowIndex < 3; rowIndex++) {
                    for (Integer colIndex = 6; colIndex < 9; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;

            case 4 :
                for(Integer rowIndex = 3; rowIndex < 6; rowIndex++) {
                    for (Integer colIndex = 0; colIndex < 3; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;

            case 5 :
                for(Integer rowIndex = 3; rowIndex < 6; rowIndex++) {
                    for (Integer colIndex = 3; colIndex < 6; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;

            case 6 :
                for(Integer rowIndex = 3; rowIndex < 6; rowIndex++) {
                    for (Integer colIndex = 6; colIndex < 9; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;

            case 7 :
                for(Integer rowIndex = 6; rowIndex < 9; rowIndex++) {
                    for (Integer colIndex = 0; colIndex < 3; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;

            case 8 :
                for(Integer rowIndex = 6; rowIndex < 9; rowIndex++) {
                    for (Integer colIndex = 3; colIndex < 6; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;

            case 9 :
                for(Integer rowIndex = 6; rowIndex < 9; rowIndex++) {
                    for (Integer colIndex = 6; colIndex < 9; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;
        }
        return true;
    }

    /**
     * Determine the region on the board for a specify position
     * @param row is the index of the row on the board
     * @param col is the index of the column on the board
     * @return the number of the region to a specify position
     */
    private Integer determineRegion(Integer row, Integer col) {
        Integer region = 0;
        if (row <= 2) {
            if (col <= 2) region = 1;
            if ((col >= 3) && (col <= 5)) region = 2;
            if (col >= 6) region = 3;
        }
        if ((row >= 3) && (row <= 5)) {
            if (col <= 2) region = 4;
            if ((col >= 3) && (col <= 5)) region = 5;
            if (col >= 6) region = 6;
        }
        if (row >= 6) {
            if (col <= 2) region = 7;
            if ((col >= 3) && (col <= 5)) region = 8;
            if (col >= 6) region = 9;
        }
        return region;
    }

    /**
     * Mathod to set a valor for a board based on the minimax algorithm whit alpha-beta pruning
     * @param board is the board to value
     * @param alfa
     * @param beta
     * @return the value of a board
     */
    public Integer minimaxAlfaBeta(Board board, Integer alfa, Integer beta, Boolean player, Integer deep) {
        bestPlay(board);
    	if (board.value == 81||deep == 3)
        	return board.value;

    	Board finalBoard = null;
    	for (Board b : generateSon(board)) {
    	  System.out.println(b.toString());
    		if (player) {
    			int v = minimaxAlfaBeta(b, alfa, beta, false, deep++);
    			if (alfa < v);
    				alfa = v;
    			finalBoard = b;
    		}else
    			beta = Math.min(beta, minimaxAlfaBeta(b, alfa, beta, true, deep++));
    	}
    	if (finalBoard != null)
    		this.board = finalBoard.board;
        if (player)
        	return alfa;
        else
        	return beta;
    }

    /**
     * Method to build the sons of the board
     * @param board
     * @return son of the board
     */
    private Board[] generateSon(Board board) {
    	Board[] sons = new Board[10];
    	Integer sonsCount = 0;
    		for (Integer row = 0; row < ROWS; row++) {
    			for (Integer column = 0; column < COLUMNS; column++) {
    				for (Integer num = 1; num <= 9; num++) {
    				  
    					if ((board.isValidNumber(num, row, column)) && (board.board[row][column] == 0) && (sonsCount != 10)) {
    						Board newboard = board.cloneBoard();
    						newboard.setCell(num, row, column);
    						System.out.println(newboard.toString());
    						sons[sonsCount] = newboard;
    						sonsCount++;
    					} 
    				}
    			}
    		}
    	return sons;
    }

    private Board cloneBoard(){
        Board clone = new Board();
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
              if (this.board[i][j] != 0)
                clone.setCell(this.board[i][j], i, j);
            }
        }
        return clone;
    }


    /**
     * Method to set value for the best play
     * @param board
     */
    private void bestPlay(Board board) {
    	for (Integer i = 0; i < ROWS; i++)
    		for (Integer j = 0; j < COLUMNS; j++) {
    			if (!board.isValidPos(i, j)) {
    				board.value++;
    			}
    		}
    }
    /**
     * See if a borad is complete.
     * @return true is a board is complete.
     */
    public Boolean completeBoard() {
    	for (int i = 0; i < ROWS; i++) {
    		for (int j = 0; j < COLUMNS; j++) {
    			if (this.isValidPos(i, j)) {
    				for (int k = 1;k <=9; k++) {
    					if (this.isValidNumber(k, i, j))
    						return false;
    				}
    			}
    		}
    	}
    	return true;
    }

    /**
     * provides a string representation of the board current content
     * @return a string that show the board
     */
	@Override
	public String toString() {
		String s = "";
		String contentCell = "";
		for (Integer row = 0; row < ROWS; row++) {
			s = s + "|";
			for (Integer col = 0; col < COLUMNS; col++) {
				contentCell = this.board[row][col].toString();
				for (Integer b = contentCell.length(); b < 4; b++) {
					s = s + " ";
				}
				s = s + contentCell + "|";
			}
			s = s + "\n";
		}
		return s;
	}
}
