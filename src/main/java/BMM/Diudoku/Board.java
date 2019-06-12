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
    private static final int ROWS = 9;
    
    /**
     * Represent the number of the board columns
     */
    private static final int COLUMNS = 9;
    
    /**
     * Matrix to represent the board
     */
    private int [][] board; 
    
    /**
     * Constructor of the Board class that make a board of 9x9
     */
    public Board () {
        this.board = new int[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
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
    public void setCell(int value, int row, int col) {
        if (!(this.isValidPos(row, col))) throw new IllegalArgumentException("Invalid Position");
        if (!(this.isValidNumber(value, row, col))) throw new IllegalArgumentException("This value can't be located here, because it is already in the same row, column or region");
        this.board[row][col] = value;
    }
    
    /**
     * Determine if a specific position is valid to be occupied
     * @param row is the index of the row to verify the position
     * @param col is the index of the column to verify the position
     * @return true if position is a valid position
     */
    private boolean isValidPos(int row, int col) {
        if ((row >= 9) || (col >= 9) || (this.board[row][col] != 0)) return false;
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
    private boolean isValidNumber(int value, int row, int col) {
        if ((this.isValidRow(row, value)) && (this.isValidColumns(col, value)) && (this.isValidRegion(row, col, value))) return true;
        return false;
    }
    
    /**
     * Determine if a value can be located in a row, according to the sudoku's rulers
     * @param row is the index to verify the row
     * @param value is the number to set on the board
     * @return true if the number can be located
     */
    private boolean isValidRow(int row, int value) {
        for (int colIndex = 0; colIndex < COLUMNS; colIndex++) {
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
    private boolean isValidColumns(int col, int value) {
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex++) {
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
    private boolean isValidRegion(int row, int col, int value) {
        int region = this.determineRegion(row, col);
        switch (region) {
            case 1 : 
                for(int rowIndex = 0; rowIndex < 3; rowIndex++) {
                    for (int colIndex = 0; colIndex < 3; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;
            
            case 2 :
                for(int rowIndex = 0; rowIndex < 3; rowIndex++) {
                    for (int colIndex = 3; colIndex < 6; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;
            
            case 3 :
                for(int rowIndex = 0; rowIndex < 3; rowIndex++) {
                    for (int colIndex = 6; colIndex < 9; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;
            
            case 4 :
                for(int rowIndex = 3; rowIndex < 6; rowIndex++) {
                    for (int colIndex = 0; colIndex < 3; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;
            
            case 5 :
                for(int rowIndex = 3; rowIndex < 6; rowIndex++) {
                    for (int colIndex = 3; colIndex < 6; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;
            
            case 6 :
                for(int rowIndex = 3; rowIndex < 6; rowIndex++) {
                    for (int colIndex = 6; colIndex < 9; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;
            
            case 7 :
                for(int rowIndex = 6; rowIndex < 9; rowIndex++) {
                    for (int colIndex = 0; colIndex < 3; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;
            
            case 8 :
                for(int rowIndex = 6; rowIndex < 9; rowIndex++) {
                    for (int colIndex = 3; colIndex < 6; colIndex++) {
                        if (this.board[rowIndex][colIndex] == value) return false;
                    }
                }
                break;
            
            case 9 :
                for(int rowIndex = 6; rowIndex < 9; rowIndex++) {
                    for (int colIndex = 6; colIndex < 9; colIndex++) {
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
    private int determineRegion(int row, int col) {
        int region = 0;
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
    public int minimaxAlfaBeta(Board board, int alfa, int beta) {
        //TODO Implement this method
        return 0;
    }
    
    /**
     * provides a string representation of the board current content
     * @return a string that show the board
     */
    public String toString() {
        //TODO Implement this method
        return "";
    }
}
