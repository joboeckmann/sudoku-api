public class Square {
    int value;
    Square nextInRow;
    Square nextInCol;
    Square prev;
    Square nextInBox;
    Square [] availableNums;

    public Square () {
        availableNums = new Square[9];
    }

    @Override
    public String toString(){
        return ""+value;
    }

}
