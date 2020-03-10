import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Square {
    int value;
    Square nextInRow;
    Square nextInCol;
    Square nextInBox;
    HashMap<Integer, List<Square>> availableNums;

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

    }

    @Override
    public String toString(){
        return ""+value;
    }

}
