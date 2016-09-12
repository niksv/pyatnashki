import java.util.*;


public class Solver {
    private static final short[] xWays = {0, 0, -1, 1};
    private static final short[] yWays = {-1, 1, 0, 0};
    private static final String[] ways = {"right", "left", "down", "up"};
    private volatile int iteration = 0;
    private Path path = new Path();
    private Used used = new Used();
    PriorityQueue<Long> queue;


    Solver(Long field) {
        path.put(0, field, -1);
        used.add(field);
        queue = new PriorityQueue<>(1000, new FieldComparator());
    }

    void solve(long field) {
        used.add(field);
        queue.add(field);
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
                    if (used.add(next)) {
                        iteration++;
                        path.put(next, fieldAsLong, k);
                        if (LongField.isFinal(next)) {
                            printWay(next);
                            System.out.println(iteration);
                            return;
                        }
                        queue.add(next);
                    }

                }
            }
        }
    }

    private void printWay(long field) {
        System.out.println("end");
        List<String> way = new ArrayList<String>();

        while (path.states.containsKey(field)) {
            way.add(ways[path.getWay(field)]);
            field = path.getParent(field);
        }

        Collections.reverse(way);
        for (String s : way) {
            System.out.println(s);
        }
    }
}
