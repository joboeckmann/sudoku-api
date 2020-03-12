package sudoku.model;


import java.util.Random;

public class UIBoardLayout {
    private int[][] completeBoard;
    private Integer[][] board;
    private int[][] userBoard;

    public UIBoardLayout(){};

    public int[][] getCompleteBoard() {
        return completeBoard;
    }

    public void setCompleteBoard(int[][] completeBoard) {
        this.completeBoard = completeBoard;
    }

    public Integer[][] getBoard() {
        return board;
    }

    public void setBoard(Integer[][] board) {
        this.board = board;
    }

    public int[][] getUserBoard() {
        return userBoard;
    }

    public void setUserBoard(int[][] userBoard) {
        this.userBoard = userBoard;
    }

    public void convertFromNormalLayout(Square[][] goodBoard){
        completeBoard= new int[9][9];
        for (int i =0; i< 9; i++){
            for(int j= 0; j<9; j++) {
                if (i == 0 || i == 3 || i == 6) {
                    if (j == 3 || j ==4 || j ==5) {
                        completeBoard[i + 1][j-3] = goodBoard[i][j].value +1 ;
                    } else if (j == 6 || j == 7 || j == 8) {
                        completeBoard[i + 2][j -6] = goodBoard[i][j].value +1 ;
                    } else {
                        completeBoard[i][j] = goodBoard[i][j].value +1 ;
                    }
                } else if (i == 1 || i == 4 || i == 7) {
                    if (j == 0 || j == 1 || j == 2) {
                        completeBoard[i -1 ][j+3] = goodBoard[i][j].value +1 ;
                    } else if (j == 6 || j == 7 || j == 8)  {
                        completeBoard[i + 1][j-3] = goodBoard[i][j].value +1 ;
                    } else {
                        completeBoard[i][j] = goodBoard[i][j].value +1 ;
                    }
                } else if (i == 2 || i == 5 || i == 8) {
                    if (j == 0 || j == 1 || j == 2) {
                        completeBoard[i -2 ][j+6] = goodBoard[i][j].value +1;
                    } else if (j == 3 || j == 4 || j == 5)  {
                        completeBoard[i -1][j+3] = goodBoard[i][j].value +1;
                    } else {
                        completeBoard[i][j] = goodBoard[i][j].value +1;
                    }
                }
            }

        }
    }


    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print(" ");
                }
                System.out.print(completeBoard[i][j]+" ");
            }
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("__________________");
    }

    public void removeAtRandom() {
        board = new Integer[9][9];
        for (int i =0; i< 9; i++){
            for (int j= 0; j<9; j++){
                board[i][j] = completeBoard[i][j];
            }
        }
        Random rand = new Random();
        for (int i = 0; i<10; i++){
            board[rand.nextInt(9)][rand.nextInt(9)] = null;
        }
    }
}
