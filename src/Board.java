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



    public void populateBoard() {
        List<Integer> randomNums = generateRandomNumbers();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                 Square square = board[i][j];
                int value = findAvailableNum(square, randomNums);
                if (value == -1){
                    reorganizeBoard(square);
                    return;
                } else {
                    square.value = value;
                    updateEverything(square, true);
                    printBoard();
                }
            }

            randomNums = generateRandomNumbers();
        }
    }

    public void reorganizeBoard(Square square){
        Map<Integer, List<Square>> sorted = square.availableNums.entrySet().stream()
                .sorted((a,b) -> b.getKey() - a.getKey())
                .sorted((a, b) -> a.getValue().size() - b.getValue().size())
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), (m0, m1) -> m0.putAll(m1));
        Map.Entry<Integer,List<Square>> entry = sorted.entrySet().iterator().next();
        Integer key = entry.getKey();
        square.value = key;
        updateEverything(square, true);
        for (int i = 0; i< square.availableNums.get(key).size(); i++){
            Square updatedSquare = square.availableNums.get(key).get(i);
            updateEverything(updatedSquare, false);
            updatedSquare.value = -1;
            printBoard();

        }

    }

    public void updateEverything(Square square, boolean add){
        updateRow(square, square.nextInRow, square.value,add);
        updateCol(square, square.nextInCol, square.value,add);
        updateBox(square, square.nextInBox, square.value,add );
    }

    private void updateRow(Square startSquare, Square square, int value, boolean add) {
        if (square == startSquare) {
            return;
        }
        List squares =  square.availableNums.get(value);
         if (add) {
             squares.add(startSquare);
         } else {
             squares.remove(startSquare);
         }
        square.availableNums.put(value,squares);
        updateRow(startSquare, square.nextInRow, value, add);
    }

    private void updateCol(Square startSquare,Square square, int value, boolean add) {
        if (square == startSquare) {
            return;
        }
        List squares =  square.availableNums.get(value);
        if (add) {
            squares.add(startSquare);
        } else {
            squares.remove(startSquare);
        }
        square.availableNums.put(value,squares);
        updateCol(startSquare, square.nextInCol, value, add);
    }

    private void updateBox(Square startSquare, Square square, int value, boolean add) {
        if (square == startSquare) {
            return;
        }
        List squares =  square.availableNums.get(value);
        if (add) {
            squares.add(startSquare);
        } else {
            squares.remove(startSquare);
        }
        square.availableNums.put(value,squares);
        updateBox(startSquare, square.nextInBox, value, add);
    }

    private int findAvailableNum(Square square, List<Integer> listOfNums) {
        int num = 9;
        int finalNumber= -1;
        HashMap<Integer,List<Square>> availableNums = square.availableNums;
        try {
            for (int i = 0; i < 9; i++) {
                num = listOfNums.get(i);
                if (availableNums.get(num).isEmpty()) {
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
