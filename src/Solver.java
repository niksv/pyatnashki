import java.util.*;


public class Solver {
    public static final short[] xWays = {0, 0, -1, 1};
    public static final short[] yWays = {-1, 1, 0, 0};
    public static final String[] ways = {"right", "left", "down", "up"};
    volatile int iteration = 0;
    volatile long time = 0;
    public static boolean end = false;

    Store store;

//    Map<Integer, Integer> map;
    Path map = new Path();
//    Set<Integer> used = Collections.synchronizedSet(new HashSet<Integer>());
    Used used = new Used();
//    Collections.

    public Solver(Field field) {
        this.store = new Store(field);
//        this.map = Collections.synchronizedMap(new HashMap<Integer, Integer>());
//        this.map.put(field.hashCode(), null);
//        map.put(0, field.hashCode(), -1);
        used.add(field.hashCode());
    }

    public void solveThreads(int threadCount) throws CloneNotSupportedException {
        Thread thread = new Thread(new ThreadSolve());
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < threadCount - 1; i++) {
            new Thread(new ThreadSolve()).start();
        }
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

    class ThreadSolve implements Runnable {

        @Override
        public void run() {
            while(!end) {
                Field field = store.get();
                if (field == null) {
                    return;
                }
//            if (field.isFinal()) {
//                printWay(field);
//                return;
//            }
                boolean found = false;
                for (int i = 0; i < field.field.length && !found; i++) {
                    for (int j = 0; j < field.field.length; j++) {
                        if (field.field[i][j] == 0) {
                            List<Field> fields = new ArrayList<>();
                            for (int k = 0; k < 4; k++) {
                                int ni = i + xWays[k];
                                int nj = j + yWays[k];

                                if (ni >= 0 && ni < field.field.length && nj >= 0 && nj < field.field.length) {
//                                    Field nField = null;
//                                    try {
//                                        nField = field.clone();
//                                    } catch (CloneNotSupportedException e) {
//                                        e.printStackTrace();
//                                    }
//                                    nField.field[i][j] = field.field[ni][nj];
//                                    nField.field[ni][nj] = 0;
                                    field.field[i][j] = field.field[ni][nj];
                                    field.field[ni][nj] = 0;

                                    int hash = field.hashCode();

                                    field.field[ni][nj] = field.field[i][j];
                                    field.field[i][j] = 0;

                                    if (used.add(hash)) {
                                        Field nField = null;
                                        try {
                                            nField = field.clone();
                                        } catch (CloneNotSupportedException e) {
                                            e.printStackTrace();
                                        }
                                        nField.field[i][j] = field.field[ni][nj];
                                        nField.field[ni][nj] = 0;

                                        iteration++;
                                        if (iteration % 100000 == 0) {

                                            System.out.println(iteration + " " + store.queue.size() + " " + used.set.size() + "  " + (System.currentTimeMillis() - time));
                                            time = System.currentTimeMillis();
//                                            System.out.println(store.queue.size());
                                        }
                                        map.put(hash, field.hashCode(), k);

                                        if (nField.isFinal()) {
                                            printWay(nField);
                                            end = true;
                                            return;
                                        }
                                        fields.add(nField);
//                                        store.put(nField);
                                    }

                                }
                            }
                            store.putAll(fields);
                            found = true;
                            break;
                        }
                    }
                }
            }

        }
    }

    public void printWay(Field field) {
        System.out.println("end");
        List<String> way = new ArrayList<String>();
        int hash = field.hashCode();

        while (map.states.containsKey(hash)) {
            way.add(ways[map.getWay(hash)]);
            hash = map.getParent(hash);
        }

        Collections.reverse(way);
        for (String s : way) {
            System.out.println(s);
        }
    }
}
