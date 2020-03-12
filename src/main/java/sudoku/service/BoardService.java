package sudoku.service;

import org.springframework.stereotype.Service;
import sudoku.model.Square;
import sudoku.model.UIBoardLayout;

@Service
public class BoardService {

    public UIBoardLayout retrieveBoard(){
        Square[][] board = new Square[9][9];
        GeneralBoard.generateBoard(board);
        PopulateBoardAndCry.populateBoard(board);
        GeneralBoard.printBoard(board);
        UIBoardLayout ui =  new UIBoardLayout();
        ui.convertFromNormalLayout(board);
        ui.printBoard();
        ui.removeAtRandom();
        return ui;
    }

    public boolean validate(UIBoardLayout board) {
        for (int i =0; i < 9; i++){
            for (int j =0; j < 9; j++){
                if (board.getUserBoard()[i][j] != board.getCompleteBoard()[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
