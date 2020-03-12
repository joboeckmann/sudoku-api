package sudoku.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Square {
    public int value;
    public Square nextInRow;
    public Square nextInCol;
    public Square nextInBox;
    public HashMap<Integer, List<Square>> availableNums;

    public Square () {
        availableNums = new HashMap<>();
        availableNums.put(0, new ArrayList<>());
        availableNums.put(1, new ArrayList<>());
        availableNums.put(2, new ArrayList<>());
        availableNums.put(3, new ArrayList<>());
        availableNums.put(4, new ArrayList<>());
        availableNums.put(5, new ArrayList<>());
        availableNums.put(6, new ArrayList<>());
        availableNums.put(7, new ArrayList<>());
        availableNums.put(8, new ArrayList<>());
        value = -1;

    }



    public void updateEverything(Square square, boolean add){
        List squares =  square.availableNums.get(value);
        if (add) {
            squares.add(square);
        } else {
            squares.remove(square);
        }
        square.availableNums.put(value,squares);
        updateRow(this, this.nextInRow, this.value,add);
        updateCol(this, this.nextInCol, this.value,add);
        updateBox(this, this.nextInBox, this.value,add );
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

    @Override
    public String toString(){
        return ""+value;
    }

}
