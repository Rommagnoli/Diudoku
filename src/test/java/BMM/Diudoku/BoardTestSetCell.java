package BMM.Diudoku;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import junit.framework.TestCase;


/**
 * The method under tests is {@link BMM.Diudoku.Board.#setCell()}
 */
public class BoardTestSetCell extends TestCase {
	
	private Board boardDiudoku;
	private Board expectedBoard;
	private Boolean expected;
	
	

	/**
	 * This method converts an integer matrix into a Board object.
	 * @param an integer matrix.
	 * @return a Board object representing the integer matrix.
	 */
	private Board matrixToBoard(Integer[][] matrix) {
		Board board = new Board();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if (matrix[i][j]==0) {
					continue;
				}else { 
					board.setCell(matrix[i][j],i,j);
				}
			}
		}
		return board;
	} 
	
	/**
	 * This method test:
	 * Given a valid position and a valid value, setCell() should set the value in the position.
	 */
	@Test
	public void testSetCell1() {
		boardDiudoku = new Board();
		expectedBoard = new Board();
		Integer[][] matriz = new Integer[][] {	
			{0,2,3,4,5,6,7,8,9},
			{9,0,0,0,0,0,0,0,0}, 
			{8,0,0,0,0,0,0,0,0},
			{7,0,0,0,0,0,0,0,0},
			{6,0,0,0,0,0,0,0,0},
			{5,0,0,0,0,0,0,0,0},
			{4,0,0,0,0,0,0,0,0},
			{3,0,0,0,0,0,0,0,0},
			{2,0,0,0,0,0,0,0,0}
		};
		boardDiudoku = matrixToBoard(matriz);
		boardDiudoku.setCell(1,0,0);
		
		
		Integer[][] matriz2 = new Integer[][] {	
			{1,2,3,4,5,6,7,8,9},
			{9,0,0,0,0,0,0,0,0}, 
			{8,0,0,0,0,0,0,0,0},
			{7,0,0,0,0,0,0,0,0},
			{6,0,0,0,0,0,0,0,0},
			{5,0,0,0,0,0,0,0,0},
			{4,0,0,0,0,0,0,0,0},
			{3,0,0,0,0,0,0,0,0},
			{2,0,0,0,0,0,0,0,0}
		};
		expectedBoard = matrixToBoard(matriz2);
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				expected = expectedBoard.getBoard()[i][j].equals(boardDiudoku.getBoard()[i][j]);
			}
		}	
		assertTrue(expected);	
	}
	
	/**
	 * This method test:
	 * Given an invalid position (Cell not empty) and a valid value setCell() should throw an exception.
	 */
	@Test
	public void testSetCell2() {
		boardDiudoku = new Board();
		Integer[][] matriz = new Integer[][] {	
			{1,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}, 
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		};
		boardDiudoku = matrixToBoard(matriz);
		 Throwable exception = assertThrows(IllegalArgumentException.class,
		            ()->{boardDiudoku.setCell(1,0,0);});
		 assertEquals(exception.getMessage(),"Invalid Position");
		
	}
	
	/**
	 * This method test:
	 * Given a valid position and a invalid value for the row setCell() should throw an exception.
	 */
	@Test
	public void testSetCell3() {
		boardDiudoku = new Board();
		Integer[][] matriz = new Integer[][] {	
			{0,1,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}, 
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		};
		boardDiudoku = matrixToBoard(matriz);
		 Throwable exception = assertThrows(IllegalArgumentException.class,
		            ()->{boardDiudoku.setCell(1,0,0);});
		 assertEquals(exception.getMessage(),"This value can't be located here, because it is already in the same row, column, region or the value is out of range");
		
	}
	
	/**
	 * This method test:
	 * Given a valid position and a invalid value for the column setCell() should throw an exception.
	 */
	@Test
	public void testSetCell4() {
		boardDiudoku = new Board();
		Integer[][] matriz = new Integer[][] {	
			{0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0}, 
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		};
		boardDiudoku = matrixToBoard(matriz);
		 Throwable exception = assertThrows(IllegalArgumentException.class,
		            ()->{boardDiudoku.setCell(1,0,0);});
		 assertEquals(exception.getMessage(),"This value can't be located here, because it is already in the same row, column, region or the value is out of range");
		
	}
	
	/**
	 * This method test:
	 * Given a valid position and a invalid value for the area setCell() should throw an exception.
	 */
	@Test
	public void testSetCell5() {
		boardDiudoku = new Board();
		Integer[][] matriz = new Integer[][] {	
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}, 
			{0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		};
		boardDiudoku = matrixToBoard(matriz);
		 Throwable exception = assertThrows(IllegalArgumentException.class,
		            ()->{boardDiudoku.setCell(1,0,0);});
		 assertEquals(exception.getMessage(),"This value can't be located here, because it is already in the same row, column, region or the value is out of range");
		
	}
	/**
	 * This method test:
	 * Given a valid position and invalid value setCell() should throw an exception.
	 */
	@Test
	public void testSetCell6() {
		boardDiudoku = new Board();
		Integer[][] matriz = new Integer[][] {	
			{0,1,2,3,4,5,6,7,8},
			{9,0,0,0,0,0,0,0,0}, 
			{8,0,0,0,0,0,0,0,0},
			{7,0,0,0,0,0,0,0,0},
			{6,0,0,0,0,0,0,0,0},
			{5,0,0,0,0,0,0,0,0},
			{4,0,0,0,0,0,0,0,0},
			{3,0,0,0,0,0,0,0,0},
			{2,0,0,0,0,0,0,0,0}
		};
		boardDiudoku = matrixToBoard(matriz);
		 Throwable exception = assertThrows(IllegalArgumentException.class,
		            ()->{boardDiudoku.setCell(1,0,0);});
		 assertEquals(exception.getMessage(),"This value can't be located here, because it is already in the same row, column, region or the value is out of range");
		
	}
	
	/**
	 * This method test:
	 * Given a valid position and a invalid value setCell() should throw an exception.
	 */
	@Test
	public void testSetCell7() {
		boardDiudoku = new Board();
		Integer[][] matriz = new Integer[][] {	
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}, 
			{0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		};
		boardDiudoku = matrixToBoard(matriz);
		 Throwable exception = assertThrows(IllegalArgumentException.class,
		            ()->{boardDiudoku.setCell(10,0,0);});
		 assertEquals(exception.getMessage(),"This value can't be located here, because it is already in the same row, column, region or the value is out of range");
		
	}
	
	/**
	 * This method test:
	 * Given an invalid position (out of range) and a valid value setCell() should throw an exception.
	 */
	@Test
	public void testSetCell8() {
		boardDiudoku = new Board();
		Integer[][] matriz = new Integer[][] {	
			{1,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}, 
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		};
		boardDiudoku = matrixToBoard(matriz);
		 Throwable exception = assertThrows(IllegalArgumentException.class,
		            ()->{boardDiudoku.setCell(1,10,10);});
		 assertEquals(exception.getMessage(),"Invalid Position");
		
	}
	
}
