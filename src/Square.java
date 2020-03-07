public class Square {
    int value;
    Square nextInRow;
    Square nextInCol;
    Square prev;
    Square nextInBox;
    int[] availableNums;

    public Square () {
        availableNums = new int[9];
    }

    @Override
    public String toString(){
        return ""+value;
    }

}
