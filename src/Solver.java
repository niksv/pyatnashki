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

//    public void solve() throws CloneNotSupportedException {
//        while(!store.queue.isEmpty() && !end) {
//            Field field = store.get();
////            if (field.isFinal()) {
////                printWay(field);
////                return;
////            }
//            boolean found = false;
//            for (int i = 0; i < field.size && !found; i++) {
//                for (int j = 0; j < field.size; j++) {
//                    if (field.field[i][j] == 0) {
//                        for (int k = 0; k < 4; k++) {
//                            int ni = i + xWays[k];
//                            int nj = j + yWays[k];
//
//                            if (ni >= 0 && ni < field.size && nj >= 0 && nj < field.size) {
//                                Field nField = field.clone();
//                                nField.field[i][j] = field.field[ni][nj];
//                                nField.field[ni][nj] = 0;
//
////                                if (!map.containsKey(nField)) {
////                                    map.put(nField, field);
//                                int hash = nField.hashCode();
//                                if (!used.contains(hash)) {
//                                    used.add(hash);
//                                    iteration++;
//                                    if (iteration % 100000 == 0){
//                                        System.out.println(iteration);
//                                    }
//
//                                    if (nField.isFinal()) {
//                                        printWay(nField);
//                                        end = true;
//                                        return;
//                                    }
//                                    store.put(nField);
//                                }
//
//                            }
//                        }
//                        found = true;
//                        break;
//                    }
//                }
//            }
//        }
//
//
//    }

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
                int pos = LongField.getZero(fieldAsLong);
                int i = pos / 4;
                int j = pos % 4;

                for (int k = 0; k < 4; k++) {
                    int ni = i + xWays[k];
                    int nj = j + yWays[k];

                    if (ni >= 0 && ni < 4 && nj >= 0 && nj < 4) {
                        field.field[i][j] = field.field[ni][nj];
                        field.field[ni][nj] = 0;

                        Long hash = field.getLong();

                        field.field[ni][nj] = field.field[i][j];
                        field.field[i][j] = 0;

                        if (used.add(hash)) {
//                                        Field nField = new Field(fieldAsLong);


                            iteration++;
                            if (iteration % 100000 == 0) {

                                System.out.println(Thread.currentThread() + " " + iteration + " " + queue.size() + " " + used.set.size() + "  " + (System.currentTimeMillis() - time));
                                time = System.currentTimeMillis();
//                                            System.out.println(store.queue.size());
                            }
//                                        map.put(hash, field.getLong(), k);


                            if (Field.isFinal(hash)) {
                                printWay(new Field(hash));
                                end = true;
                                return;
                            }

                            if (iteration % 10000 == 0 && child > 0) {
                                Thread t = new Thread(new ThreadSolve(child - 1, hash));
                                t.start();
                                child -= 1;
                            } else {
                                queue.add(hash);
                            }
//                                        store.put(nField);
                        }

                    }
                }
//                            store.putAll(fields);
                break;
            }
        }

    }

    public void printWay(Field field) {
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
