public class Sudoku {

    public static void main(String[] args) {
        Square[][] board = new Square[9][9];
        GeneralBoard.generateBoard(board);
        PopulateBoardAndCry.populateBoard(board);
        GeneralBoard.printBoard(board);
    }
}
