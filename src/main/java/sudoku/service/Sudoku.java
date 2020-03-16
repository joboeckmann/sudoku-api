package sudoku.service;

import sudoku.model.Square;
import sudoku.model.UIBoardLayout;

public class Sudoku {

    public static void main(String[] args) {
        Square[][] board = new Square[9][9];
        GeneralBoard.generateBoard(board);
        PopulateBoardAndCry.populateBoard(board);
        GeneralBoard.printBoard(board);
//        UIBoardLayout ui =  new UIBoardLayout();
//        ui.convertFromNormalLayout(board);
//        ui.printBoard();
    }
}
