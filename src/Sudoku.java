public class Sudoku {

    public static void main(String[] args) {
        Board board = new Board();
        board.generateBoard();
        board.populateBoard();
        board.printBoard();
    }
}
