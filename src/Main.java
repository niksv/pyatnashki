import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    private static final short[] xWays = {0, 0, -1, 1};
    private static final short[] yWays = {-1, 1, 0, 0};

    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
//        field.field = new short[][]{
//                {9,11,2,1},
//                {10,12,6,5},
//                {8,4,15,14},
//                {0,3,7,13}};
//        field.field = new short[][]{
//                {1,4,8,6},
//                {5,10,2,12},
//                {9,14,7,15},
//                {0,13,3,11}};
        short [][] field = new short[][]{
                {5,11,7,9},
                {3,15,12,4},
                {1,14,13,2},
                {6,8,10,0}};

//        field.field = new short[][]{
//                {0,1,2,3},
//                {5,6,7,4},
//                {9,10,11,8},
//                {13,14,15,12}};
//        System.out.println(Field.isFinal(field.getLong()));
//        System.out.println(field.isFinal());
//        Field field = new Field(3);
//        field.field = new short[][]{
//                {5,4,2},
//                {7,1,3},
//                {8,6,0}};
//                {0,1},
//                {2,3},
//        };
//        Thread.sleep(1000);
        solve(LongField.getLong(field));
    }

    static void solve(long field) {
        Queue<Long> queue = new PriorityQueue<>(1000, new FieldComparator());
        Store store = new Store();
        store.put(field, -1, -1);
        queue.add(field);
        int iteration = 0;

        while (!queue.isEmpty()) {
            Long fieldAsLong = queue.poll();
            int zeroPos = LongField.getZero(fieldAsLong);
            int i = zeroPos / 4;
            int j = zeroPos % 4;

            for (int k = 0; k < 4; k++) {
                int ni = i + xWays[k];
                int nj = j + yWays[k];

                if (ni >= 0 && ni < 4 && nj >= 0 && nj < 4) {
                    long next = LongField.move(fieldAsLong, zeroPos, ni * 4 + nj);
                    if (store.put(next, fieldAsLong, k)) {
                        iteration++;

                        if (LongField.isFinal(next)) {
                            store.printWay(next);
                            System.out.println(iteration);
                            return;
                        }
                        queue.add(next);
                    }
                }
            }
        }
    }

}
