package sudoku.service;

import sudoku.model.Square;

public class GeneralBoard {

    public static void generateBoard(Square[][] board) {
        for (int i2 = 0; i2 < 9; i2++) {
            for (int j = 0; j < 9; j++) {
                board[i2][j] = new Square();
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j < 8) {
                    board[i][j].nextInRow = board[i][j + 1];

                }
                if (j==8){
                    board[i][j].nextInRow= board[i][0];
                }
                if (i < 8) {
                    board[i][j].nextInCol = board[i + 1][j];
                }
                if (i ==8 ){
                    board[i][j].nextInCol = board[0][j];
                }
                if (((i + 1) % 3 == 0 )&& ((j + 1) % 3 == 0)) {
                    board[i][j].nextInBox = board[i-2][j-2];
                }
                else if ((i < 8 )&& ((j + 1) % 3 == 0)) {
                    board[i][j].nextInBox = board[i + 1][j - 2];
                } else if (j < 8) {
                    board[i][j].nextInBox = board[i][j + 1];
                }
            }

        }
    }

    public static void printBoard(Square[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print(" ");
                }
                System.out.print(board[i][j].toString()+" ");
            }
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("__________________");
    }

    public static void printChecker(Square[][] board){
        int v = 1;
        for (int i = 0; i<9; i++){
            for (int j =0; j<9; j++){
                board[i][j].value = v;
                v++;
            }
        }
        printBoard(board);
        for (int i = 0; i<9; i++){
            for (int j =0; j<9; j++){
                System.out.println("Value: " +board[i][j].value);
                System.out.println( board[i][j].nextInRow != null? "next row value: " + board[i][j].nextInRow.value: "next row value: " + null);
                System.out.println(board[i][j].nextInCol != null? "next col: "+  board[i][j].nextInCol.value: "next col: "+ null);
                System.out.println( board[i][j].nextInBox != null? "next box: "+ board[i][j].nextInBox.value: "next box: "+null);
                if (j%3 == 0 ){
                    printBoard(board);
                }
            }
        }


    }

}
