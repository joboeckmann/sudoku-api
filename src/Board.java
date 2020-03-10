import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;

public class Board {

    Square[][] board = new Square[9][9];



    public void generateBoard() {
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
                if (j>0){
                    board[i][j].prev= board[i][j-1];
                }
                if (i < 8) {
                    board[i][j].nextInCol = board[i + 1][j];
                }
                if (((i + 1) % 3 == 0 )&& ((j + 1) % 3 == 0)) {}
                 else if ((i < 8 )&& ((j + 1) % 3 == 0)) {
                        board[i][j].nextInBox = board[i + 1][j - 2];
                    } else if (j < 8) {
                        board[i][j].nextInBox = board[i][j + 1];
                    }

            }
        }
    }



    public void populateBoard() {
        List<Integer> randomNums = generateRandomNumbers();
        Square square;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                square = board[i][j];
                int value = findAvailableNum(square, randomNums);
                square.value = value;
                updateRow(square.nextInRow, value,square);
                updateCol(square.nextInCol, value,square);
                updateBox(square, value,square );
                printBoard();
            }

            randomNums = generateRandomNumbers();
        }
    }

    private void updateRow(Square square, int value, Square taken) {
        if (square == null) {
            return;
        }
        square.availableNums[value] = taken;
        updateRow(square.nextInRow, value, taken);
    }

    private void updateCol(Square square, int value, Square taken) {
        if (square == null) {
            return;
        }
        square.availableNums[value] = taken;
        updateCol(square.nextInCol, value, taken);
    }

    private void updateBox(Square square, int value, Square taken) {
        if (square == null) {
            return;
        }
        square.availableNums[value] = taken;
        updateBox(square.nextInBox, value, taken);
    }

    private int findAvailableNum(Square square, List<Integer> listOfNums) {
        int num = 9;
        int finalNumber= -1;
        Square[] availableNums = square.availableNums;
        try {
            for (int i = 0; i < 9; i++) {
                num = listOfNums.get(i);
                if (availableNums[num] == null) {
                    listOfNums.remove(i);
                    finalNumber = num;
                    break;
                }
            }
        }catch (IndexOutOfBoundsException e){

        }

        return finalNumber;
    }


    private List<Integer> generateRandomNumbers() {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 9; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }


    public void printBoard() {
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

    public void printChecker(){
        int v = 1;
        for (int i = 0; i<9; i++){
            for (int j =0; j<9; j++){
                board[i][j].value = v;
                v++;
            }
        }
        printBoard();
        for (int i = 0; i<9; i++){
            for (int j =0; j<9; j++){
                System.out.println("Value: " +board[i][j].value);
                System.out.println( board[i][j].nextInRow != null? "next row value: " + board[i][j].nextInRow.value: "next row value: " + null);
                System.out.println(board[i][j].nextInCol != null? "next col: "+  board[i][j].nextInCol.value: "next col: "+ null);
                System.out.println( board[i][j].nextInBox != null? "next box: "+ board[i][j].nextInBox.value: "next box: "+null);
                if (j%3 == 0 ){
                    printBoard();
                }
            }
        }


    }
}
