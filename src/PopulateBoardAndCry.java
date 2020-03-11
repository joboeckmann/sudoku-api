import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;
import java.util.stream.Collectors;

public class PopulateBoardAndCry {


    public static void populateBoard(Square[][] board) {
        boolean success;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                 Square square = board[i][j];
                int value = findAvailableNum(square);
                if (value == -1){
                    success = reorganizeBoard(square);
                    if (!success){
                        System.out.println("reset "+i + " "+j);
                        resetRow(board[i]);
                        j = -1;
                        GeneralBoard.printBoard(board);
                    }
                } else {
                    square.value = value;
                    square.updateEverything(square, true);
                    GeneralBoard.printBoard(board);
                }
            }

        }
    }

    private static void resetRow(Square[] squares) {
        for (int i = 0; i< squares.length; i++) {
           if (squares[i].value!= -1) {
               squares[i].updateEverything(squares[i], false);
           }
            squares[i].value = -1;
        }
    }

    public static boolean reorganizeBoard(Square square){
        System.out.println("flippy");
        Map<Integer, Map<Integer, List<Square>>> keysSize = createKeySizeMap(square.availableNums);
        Map.Entry<Integer,Map<Integer, List<Square>>> entry = keysSize.entrySet().iterator().next();
        SquareValue updatedSquare = findSimpleFlipperIfPossible(entry.getValue());
        if (updatedSquare != null){
            square.value = updatedSquare.square.value;
            square.updateEverything(square, true);
            updatedSquare.square.updateEverything(updatedSquare.square, false);
            updatedSquare.square.value = updatedSquare.newValue;
            updatedSquare.square.updateEverything(updatedSquare.square, true);
            return true;

        }
        return false;
    }

    private static Map<Integer, Map<Integer,List<Square>>> createKeySizeMap(HashMap<Integer, List<Square>> availableNums) {
        HashMap<Integer, Map<Integer, List<Square>>> sizeMap = new HashMap();
        for (Map.Entry<Integer, List<Square>> entry: availableNums.entrySet()){
            int value = entry.getKey();
            HashMap < Integer, List<Square>> map = new HashMap<>();
            Map existingMap =sizeMap.get(entry.getValue().size());
            if (existingMap != null) {
                existingMap.put(value, entry.getValue());
                sizeMap.put(entry.getValue().size(), existingMap);
            }
            else {
                sizeMap.put(entry.getValue().size(), map);
            }
        }
        Map<Integer,Map<Integer, List<Square>>> sorted =sizeMap.entrySet().stream().sorted((a,b) -> a.getKey() - b.getKey()).collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), (m0, m1) -> m0.putAll(m1));
        return sorted;
    }

    private static SquareValue findSimpleFlipperIfPossible(Map<Integer,List<Square>> map) {
       for (Map.Entry<Integer, List<Square>> entry: map.entrySet()){
           for (Square s: entry.getValue()) {
               int value = findAvailableNum(s);
               if (value != -1) {
                    SquareValue sv = new SquareValue();
                    sv.newValue = value;
                    sv.square = s;
                   return sv;
               }
           }
        }
        return null;
    }


    private static int findAvailableNum(Square square) {
        List<Integer> listOfNums = generateRandomNumbers();
        int num = 9;
        int finalNumber= -1;
        HashMap<Integer,List<Square>> availableNums = square.availableNums;
        try {
            for (int i = 0; i < 9; i++) {
                num = listOfNums.get(i);
                if (availableNums.get(num).isEmpty()) {
                    finalNumber = num;
                    break;
                }
            }
        }catch (IndexOutOfBoundsException e){

        }

        return finalNumber;
    }


    private static List<Integer> generateRandomNumbers() {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 9; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }

    public static class SquareValue {
        public Integer newValue;
        public Square square;

    }

}
