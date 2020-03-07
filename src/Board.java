import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;

public class Board {

    Square[][] board = new Square[9][9];

    Square startSquare = board[0][0];

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
                    if (j<0){
                        board[i][j].prev= board[i][j-1];
                    }
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
                updateRow(square.nextInRow, value);
                updateCol(square.nextInCol, value);
                updateBox(square, value);
            }
            printBoard();
            randomNums = generateRandomNumbers();
        }
    }

    private void updateRow(Square square, int value) {
        if (square == null) {
            return;
        }
        square.availableNums[value] = 1;
        updateRow(square.nextInRow, value);
    }

    private void updateCol(Square square, int value) {
        if (square == null) {
            return;
        }
        square.availableNums[value] = 1;
        updateCol(square.nextInCol, value);
    }

    private void updateBox(Square square, int value) {
        if (square == null) {
            return;
        }
        square.availableNums[value] = 1;
        updateBox(square.nextInBox, value);
    }

    private int findAvailableNum(Square square, List<Integer> listOfNums) {
        int num = 9;
        int[] availableNums = square.availableNums;
//        for (int i = 0; i < 9; i++) {
//            System.out.print(availableNums[i]);
//        }
//        System.out.println();
        try {
            for (int i = 0; i < 9; i++) {
                //    System.out.println(listOfNums);
                num = listOfNums.get(i);
                if (availableNums[num] == 0) {
                    listOfNums.remove(i);
                    break;
                }
            }
        }catch (IndexOutOfBoundsException e){

        }
        return num;
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
                System.out.print(board[i][j].toString());
            }
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
            System.out.println();
        }
    }
}
