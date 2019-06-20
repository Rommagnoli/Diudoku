package BMM.Diudoku;

import java.util.*;
import java.util.stream.IntStream;
import java.math.*;

/**
 * Hello world!
 *
 */
public class App {

    public static void printMenu() {
        System.out.println("       BIENVENIDOS A DIUDOKU       ");
        System.out.println(" _________________________________");
        System.out.println("|                                 |");
        System.out.println("| 1     PLAY DIUDOKU VS IA        |");
        System.out.println("| 2     SALIR DE LA APP           |");
        System.out.println("| 3     IA VS IA |EXPERIMENTAL|   |");
        System.out.println("|_________________________________|");
    }
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private static final int MIN_VALUE = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        int opcion = 1;
        while (opcion != 2) {
            printMenu();
            System.out.println("Ingrese una opcion");
            opcion = scann.nextInt();
            while ((opcion != 1) && (opcion != 2) && (opcion != 3)) {
                System.out.println("Opcion invalida, intente nuevamente");
                opcion = scann.nextInt();
            }
            switch (opcion) {
                case 1:
                    int value = 1;
                    int row = 0;
                    int column = 0;
                    int alfa = MIN_VALUE;
                    int beta = MAX_VALUE;
                    Board boardGame = new Board();
                    Boolean validPlay = false;
                    Boolean turn = false;
                    int cont = 0;
                    int deep = 2;
                    while (!(boardGame.completeBoard())) {
                        cont++;
                        if (turn == false) {
                                do {
                                    System.out.println(boardGame.toString());
                                    System.out.println("Ingrese el valor a colocar y su respectiva posicion");
                                    System.out.println("Ingresar valor:");
                                    value = scann.nextInt();
                                    System.out.println("Ingresar posiciones");
                                    System.out.println("Row: ");
                                    row = scann.nextInt();
                                    System.out.println("Column: ");
                                    column = scann.nextInt();
                                    validPlay = (!boardGame.isValidNumber(value, row, column) || (!boardGame.isValidPos(row, column)));
                                    if (validPlay)
                                        System.out.println("Invalid position, try again");
                                } while (validPlay);
                                boardGame.setCell(value, row, column);
                            turn = true;
                        } else {
                        	Boolean hardDeep = false;
                            if (cont > 10) {
                            	deep = 1;
                            	hardDeep = true;
                            	System.out.println("HardDeep");
                            }
                            boardGame.minimaxAlfaBeta(boardGame, alfa, beta, turn, deep, hardDeep);
                            turn = false;
                            }
                        }
                    if (turn) {
                        System.out.println("WINNER WINNER CHICKEN DINNER");
                    } else {
                        System.out.println("GAME OVER");
                    }
                    break;
                case 2:
                    opcion = 2;
                    break;
                case 3:
                	 Board boardGame2 = new Board();
                	 int alfa2 = MIN_VALUE;
                	 int beta2 = MAX_VALUE;
                	 Boolean turn2 = true;
                	 int deep2 = 2;
                	 int cont2 = 0;
                	 Boolean endGame = false;
                	 Boolean ia1Win = false;
                	 while (!(endGame)) {
                		 cont2++;
                		 Boolean hardDeep = false;
                		 if (cont2 > 10) {
                			 deep2 = 1;
                			 hardDeep = true;
                			 System.out.println("HardDeep");
                		 }
                		 System.out.println("Juega IA1");
                		 boardGame2.minimaxAlfaBeta(boardGame2, alfa2, beta2, turn2, deep2, hardDeep);
                		 System.out.println(boardGame2.toString());
                		 endGame = boardGame2.completeBoard();
                		 try {
                			 Thread.sleep(100);
                		 }catch(Exception e) { }
                		 if (!(endGame)) {
                			cont2++;
                			System.out.println("Juega IA2");
                		 	boardGame2.minimaxAlfaBeta(boardGame2, alfa2, beta2, turn2, deep2, hardDeep);
                		 	System.out.println(boardGame2.toString());
                		 	endGame = boardGame2.completeBoard();
                		 	try {
                   			 Thread.sleep(100);
                		 	}catch(Exception e) { }
                		 } else {
                			 ia1Win = true;
                		 }
                			 
                	 }
                	 if (ia1Win) {
                         System.out.println("GANO IA1");
                     } else {
                         System.out.println("GANO IA2");
                     }
            }
        }

    }

}
