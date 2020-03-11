import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;

public class PopulateBoardAndCry {


    public static void populateBoard(Square[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                 Square square = board[i][j];
                int value = findAvailableNum(square);
                if (value == -1){
                    System.out.println("pos: "+i+" "+j);
                    value = reorganizeBoard(square, 0, -1);
                    if (value == -1){
                        return;
                    }
                } else {
                    square.value = value;
                    square.updateEverything(square, true);
                    GeneralBoard.printBoard(board);
                }
            }

        }
    }

    public static int reorganizeBoard(Square square, int retries){
        System.out.println("flippy");
//        Map<Integer, List<Square>> sorted = square.availableNums.entrySet().stream()
//                .sorted((a,b) -> b.getKey() - a.getKey())
//                .sorted(Comparator.comparingInt(a -> a.getValue().size()))
//                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), (m0, m1) -> m0.putAll(m1));
        Map<Integer, Integer> keysSize = square.avai
        Map.Entry<Integer,List<Square>> entry = sorted.entrySet().iterator().next();
        Integer key = entry.getKey();
        square.value = key;
        square.updateEverything(square, true);
        for (int i = 0; i< square.availableNums.get(key).size(); i++){
            System.out.println("how many are here: "+square.availableNums.get(key).size());
            Square updatedSquare = square.availableNums.get(key).get(i);
            System.out.println("who are you "+ updatedSquare);
            square.updateEverything(updatedSquare, false);
            List<Square> squares = updatedSquare.availableNums.get(key);
            squares.add(square);
            updatedSquare.availableNums.put(key, squares);
            int value = findAvailableNum(updatedSquare);
            if (value == -1 ){
                if (retries < 3) {
                    System.out.println("flippy double flip: " + retries);
                    reorganizeBoard(updatedSquare, retries++);
                }
            } else {
                updatedSquare.value = value;
                square.updateEverything(updatedSquare, true);
                return value;
            }
        }
        return key;
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

}
