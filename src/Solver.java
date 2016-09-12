import java.util.*;


public class Solver {
    private static final short[] xWays = {0, 0, -1, 1};
    private static final short[] yWays = {-1, 1, 0, 0};
    public static final String[] ways = {"right", "left", "down", "up"};
    private volatile int iteration = 0;
    private volatile long time = 0;
    private static boolean end = false;

//    Store store;

    //    Map<Integer, Integer> map;
//    Path map = new Path();
//    Set<Integer> used = Collections.synchronizedSet(new HashSet<Integer>());
    Used used = new Used();
//    Collections.

    public Solver(Long field) {
//        this.store = new Store(field);
//        this.map = Collections.synchronizedMap(new HashMap<Integer, Integer>());
//        this.map.put(field.hashCode(), null);
//        map.put(0, field.hashCode(), -1);
        used.add(field);
    }

    public void solveThreads(int threadCount, Long stField) throws CloneNotSupportedException {
        Thread thread = new Thread(new ThreadSolve(threadCount - 1, stField));
        thread.start();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < threadCount - 1; i++) {
//            new Thread(new ThreadSolve()).start();
//        }
    }

    private class ThreadSolve implements Runnable {
        int child = 0;
        Long startField;
        Queue<Long> queue;


        ThreadSolve(int child, Long startField) {
            this.child = child;
            this.startField = startField;
            queue = new LinkedList<>();
            queue.add(startField);
        }

        @Override
        public void run() {
            while (!end && !queue.isEmpty()) {
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
                            if (iteration % 100000 == 0) {
                                System.out.println(Thread.currentThread() + " " + iteration + " " + queue.size() + " " + used.set.size() + "  " + (System.currentTimeMillis() - time));
                                time = System.currentTimeMillis();
                            }
//                                        map.put(hash, field.getLong(), k);
                            if (LongField.isFinal(next)) {
                                printWay(next);
                                end = true;
                                return;
                            }

                            if (iteration % 10000 == 0 && child > 0) {
                                Thread t = new Thread(new ThreadSolve(child - 1, next));
                                t.start();
                                child -= 1;
                            } else {
                                queue.add(next);
                            }
//                                        store.put(nField);
                        }

                    }
                }
//                            store.putAll(fields);
            }
        }

    }

    public void printWay(long field) {
        System.out.println("end");
//        List<String> way = new ArrayList<String>();
//        long hash = field.getLong();
//
//        while (map.states.containsKey(hash)) {
//            way.add(ways[map.getWay(hash)]);
//            hash = map.getParent(hash);
//        }
//
//        Collections.reverse(way);
//        for (String s : way) {
//            System.out.println(s);
//        }
    }
}
