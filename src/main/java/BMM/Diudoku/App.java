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
            while ((opcion != 1) && (opcion != 2)) {
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
                            if (cont > 20) {         //Valor de la cantidad de jugadas random al inicio del juego
                              boardGame.minimaxAlfaBeta(boardGame, alfa, beta, turn, 1);
                              turn = false;
                            } else {
                              int value1, row1, col1;
                              Random r = new Random();
                              value1 = r.ints(1, 10).findFirst().getAsInt();
                              row1 = r.ints(0, 9).findFirst().getAsInt();
                              col1 = r.ints(0, 9).findFirst().getAsInt();
                              while (!boardGame.isValidNumber(value1, row1, col1) || (!boardGame.isValidPos(row1, col1))) {
                                value1 = r.ints(1, 10).findFirst().getAsInt();
                                row1 = r.ints(0, 9).findFirst().getAsInt();
                                col1 = r.ints(0, 9).findFirst().getAsInt();                              
                              } 
                                System.out.println("Row: " + row1 + "Column: " + col1);
                                boardGame.setCell(value1, row1, col1);
                              turn = false;
                            }
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
            }
        }

    }

}
