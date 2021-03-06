package BMM.Diudoku;

import java.util.LinkedList;
import java.util.Random;

/**
 * This class represent the board of the game
 *
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
    private Integer[][] board;

    /**
     * Number represent value of the board
     */
    private Integer value;

    /**
     * Constructor of the Board class that make a board of 9x9
     */
    public Board() {
        this.board = new Integer[ROWS][COLUMNS];
        for (Integer row = 0; row < ROWS; row++) {
            for (Integer col = 0; col < COLUMNS; col++) {
                this.board[row][col] = 0;
            }
        }
        this.value = 0;
    }

    /**
     * Set a value in a specific cell of the board
     *
     * @param value is the number to set in the specific position
     * @param row is the index of the row of the board to set the value
     * @param col is the index of the column of the board to set the value
     */
    public void setCell(Integer value, Integer row, Integer col) {
        if ((this.isValidPos(row, col) == false)) {
            throw new IllegalArgumentException("Invalid Position");
        }
        if ((this.isValidNumber(value, row, col) == false)) {
            throw new IllegalArgumentException("This value can't be located here, because it is already in the same row, column, region or the value is out of range");
        }
        this.board[row][col] = value;
    }

    /**
     * Determine if a specific position is valid to be occupied
     *
     * @param row is the index of the row to verify the position
     * @param col is the index of the column to verify the position
     * @return true if position is a valid position
     */
    public boolean isValidPos(Integer row, Integer col) {
        return !((row > ROWS) || (col > COLUMNS) || (this.board[row][col] != 0));
    }

    /**
     * Determine if a value can be located on the position, according to the
     * sudoku's rulers
     *
     * @param value is the number to establish
     * @param row is the index of the row to verify the position
     * @param col is the index of the column to verify the position
     * @return true if the value can be located on the position
     */
    public boolean isValidNumber(Integer value, Integer row, Integer col) {
      return (((value <= 9) && (value >= 1)) && (this.isValidRow(row, value)) && (this.isValidColumns(col, value)) && (this.isValidRegion(row, col, value)));
    }

    /**
     * Determine if a value can be located in a row, according to the sudoku's
     * rulers
     *
     * @param row is the index to verify the row
     * @param value is the number to set on the board
     * @return true if the number can be located
     */
    private boolean isValidRow(Integer row, Integer value) {
        if (row >= 9 || row < 0)
            return false;
        for (Integer colIndex = 0; colIndex < COLUMNS; colIndex++) {
            if (this.board[row][colIndex].equals(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determine if a value can be located in a column, according to the
     * sudoku's rulers
     *
     * @param col is the index to verify the column
     * @param value is the number to set on the board
     * @return true if the number can be located
     */
    private boolean isValidColumns(Integer col, Integer value) {
        if (col >= 9 || col < 0)
            return false;
        for (Integer rowIndex = 0; rowIndex < ROWS; rowIndex++) {
            if (this.board[rowIndex][col].equals(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determine if a value can be located in a 3x3 region, according to the
     * sudoku's rulers
     *
     * @param row is the index of row to verify the region
     * @param col is the index of column to verify the region
     * @param value is the number to set in a region
     * @return true if the value can be located
     */
    private boolean isValidRegion(Integer row, Integer col, Integer value) {
        Integer region = this.determineRegion(row, col);
        switch (region) {
            case 1:
                for (Integer rowIndex = 0; rowIndex < 3; rowIndex++) {
                    for (Integer colIndex = 0; colIndex < 3; colIndex++) {
                        if (this.board[rowIndex][colIndex].equals(value)) {
                            return false;
                        }
                    }
                }
                break;

            case 2:
                for (Integer rowIndex = 0; rowIndex < 3; rowIndex++) {
                    for (Integer colIndex = 3; colIndex < 6; colIndex++) {
                        if (this.board[rowIndex][colIndex].equals(value)) {
                            return false;
                        }
                    }
                }
                break;

            case 3:
                for (Integer rowIndex = 0; rowIndex < 3; rowIndex++) {
                    for (Integer colIndex = 6; colIndex < 9; colIndex++) {
                        if (this.board[rowIndex][colIndex].equals(value)) {
                            return false;
                        }
                    }
                }
                break;

            case 4:
                for (Integer rowIndex = 3; rowIndex < 6; rowIndex++) {
                    for (Integer colIndex = 0; colIndex < 3; colIndex++) {
                        if (this.board[rowIndex][colIndex].equals(value)) {
                            return false;
                        }
                    }
                }
                break;

            case 5:
                for (Integer rowIndex = 3; rowIndex < 6; rowIndex++) {
                    for (Integer colIndex = 3; colIndex < 6; colIndex++) {
                        if (this.board[rowIndex][colIndex].equals(value)) {
                            return false;
                        }
                    }
                }
                break;

            case 6:
                for (Integer rowIndex = 3; rowIndex < 6; rowIndex++) {
                    for (Integer colIndex = 6; colIndex < 9; colIndex++) {
                        if (this.board[rowIndex][colIndex].equals(value)) {
                            return false;
                        }
                    }
                }
                break;

            case 7:
                for (Integer rowIndex = 6; rowIndex < 9; rowIndex++) {
                    for (Integer colIndex = 0; colIndex < 3; colIndex++) {
                        if (this.board[rowIndex][colIndex].equals(value)) {
                            return false;
                        }
                    }
                }
                break;

            case 8:
                for (Integer rowIndex = 6; rowIndex < 9; rowIndex++) {
                    for (Integer colIndex = 3; colIndex < 6; colIndex++) {
                        if (this.board[rowIndex][colIndex].equals(value)) {
                            return false;
                        }
                    }
                }
                break;

            case 9:
                for (Integer rowIndex = 6; rowIndex < 9; rowIndex++) {
                    for (Integer colIndex = 6; colIndex < 9; colIndex++) {
                        if (this.board[rowIndex][colIndex].equals(value)) {
                            return false;
                        }
                    }
                }
                break;
        }
        return true;
    }

    /**
     * Determine the region on the board for a specify position
     *
     * @param row is the index of the row on the board
     * @param col is the index of the column on the board
     * @return the number of the region to a specify position
     */
    private Integer determineRegion(Integer row, Integer col) {
        Integer region = 0;
        if (row <= 2) {
            if (col <= 2) {
                region = 1;
            }
            if ((col >= 3) && (col <= 5)) {
                region = 2;
            }
            if (col >= 6) {
                region = 3;
            }
        }
        if ((row >= 3) && (row <= 5)) {
            if (col <= 2) {
                region = 4;
            }
            if ((col >= 3) && (col <= 5)) {
                region = 5;
            }
            if (col >= 6) {
                region = 6;
            }
        }
        if (row >= 6) {
            if (col <= 2) {
                region = 7;
            }
            if ((col >= 3) && (col <= 5)) {
                region = 8;
            }
            if (col >= 6) {
                region = 9;
            }
        }
        return region;
    }

    /**
     * Mathod to set a valor for a board based on the minimax algorithm whit
     * alpha-beta pruning
     *
     * @param board is the board to value
     * @param alfa
     * @param beta
     * @return the value of a board
     */
    public Integer minimaxAlfaBeta(Board board, Integer alfa, Integer beta, Boolean player, Integer deep, Boolean hardDeep) {
    	bestPlay(board);
        if ((board.value.equals((board.board.length*board.board.length)+1)) || (deep > 3 )) {
            return board.value;
        }
        deep++;
        Board finalBoard = null;
        LinkedList<Board> allBoards = generateSon(board);
        if (allBoards.size() > 1)
        	dicotomicSort(allBoards);
        for (Board b : allBoards) {
            if (player) {
                int v = minimaxAlfaBeta(b, alfa, beta, false, deep, hardDeep);
                if (alfa < v) {
                  alfa = v;
                  if (!hardDeep) {
                	  int theChosenOne;
                	  int top = allBoards.size();
                	  Random r = new Random();
                	  theChosenOne = r.ints(0, top).findFirst().getAsInt();
                	  finalBoard = allBoards.get(theChosenOne);
                  } else {
                	  finalBoard = b;
                  }
                }
            } else {
                beta = Math.min(beta, minimaxAlfaBeta(b, alfa, beta, true, deep, hardDeep));
            }
        }
        if (finalBoard != null) {
            this.board = finalBoard.board;
        }
        if (player) {
            return alfa;
        } else {
            return beta;
        }
    }

    /**
     * Method to build the sons of the board
     *
     * @param board
     * @return son of the board
     */
    private LinkedList<Board> generateSon(Board board) {
    	LinkedList<Board> sons = new LinkedList<Board>();
        for (Integer row = 0; row < ROWS; row++) {
            for (Integer column = 0; column < COLUMNS; column++) {
                for (Integer num = 1; num <= 9; num++) {
                    if ((board.isValidNumber(num, row, column)) && (board.board[row][column].equals(0) && (sons.size() < 100))) {
                        Board newboard = board.cloneBoard();
                        newboard.setCell(num, row, column);
                        sons.add(newboard);
                    }
                }
            }
        }
        return sons;
    }

    private Board cloneBoard() {
        Board clone = new Board();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (this.board[i][j] != 0) {
                    clone.setCell(this.board[i][j], i, j);
                }
            }
        }
        return clone;
    }

    /**
     * Method to set value for the best play
     *
     * @param board
     */
    private void bestPlay(Board board) {
        for (Integer i = 0; i < ROWS; i++) {
            for (Integer j = 0; j < COLUMNS; j++) {
                if (!board.isValidPos(i, j)) {
                    board.value++;
                } else {
                    int k = 1;
                    while ((k <= 9) && (!board.isValidNumber(k, i, j))) {
                        if (k == 9) {
                            board.value++;
                        }
                        k++;
                    }
                }
            }
        }
        if ((board.value % 2 == 0)) {
        	board.value++;
        }
    }

    /**
     * See if a borad is complete.
     *
     * @return true is a board is complete.
     */
    public Boolean completeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (this.isValidPos(i, j)) {
                    for (int k = 1; k <= 9; k++) {
                        if (this.isValidNumber(k, i, j)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public Integer[][] getBoard() {
    	return this.board;
    }

    /**
     * provides a string representation of the board current content
     *
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
                s = s + contentCell + "";
                if (col == 2 || col == 5) s = s + "|";
            }
            s = s + "|\n";
            if (row == 2 || row == 5) s = s + "----------------------------------------\n";
        }
        return s;
    }
    /**
     * This method sort the linkedlist boards, seeing the board.value from maximun value to minimun value.
     * 
     * @param LinkedList<Board> 
     */
    public static void dicotomicSort(LinkedList<Board> a ) {
		Integer min = 0;
		Integer max = 1;
		Boolean put = false;
		Integer cen;
		
		if (a.get(min).value < a.get(max).value) {
			a.addFirst(a.get(max));
			a.remove(max+1);
		}	
		while(min+max<a.size()-1) {
			Board aux = a.get(max+1);
			if (aux.value > a.get(min).value) {
				a.remove(max+1);
				a.addFirst(aux);
				max++;
			} else if (aux.value > a.get(max).value) {
				int lastMax = max;
				while (min<max) {
					cen = ((min+max)/2);
					if (a.get(cen).value == aux.value) {
						a.add(cen+1, aux);
						a.remove(lastMax+2);
						put = true;
						max = lastMax+1;
						min = 0;
						break;
					} else if (a.get(cen).value < aux.value)
							max = cen-1;
					else
						min = cen+1;
				}
				if (!put) {
					
					a.remove(lastMax+1);
					a.add(min, aux);
					max = lastMax+1;
					min = 0;
				} else
					put = false;
			} else {
				max++;
			}	
		}
		
	}
}
