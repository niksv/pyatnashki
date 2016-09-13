import java.util.*;

class Store {
    private static final String[] ways = {"right", "left", "down", "up"};
    private Map<Long, Move> map = new HashMap<>();

    boolean put(long field, long parent, int direction) {
        if (map.containsKey(field)) {
            return false;
        }

        map.put(field, new Move(parent, direction));
        return true;
    }

    void printWay(long field) {
        System.out.println("Final way");
        List<String> way = new ArrayList<>();

        while (map.containsKey(field) && map.containsKey(map.get(field).state)) {
            Move move = map.get(field);
            way.add(ways[move.direction]);
            field = move.state;
        }

        Collections.reverse(way);
        way.forEach(System.out::println);
        System.out.println("Way length: " + way.size());
    }

    private class Move {
        Move(long state, int direction) {
            this.state = state;
            this.direction = direction;
        }

        long state;
        int direction;
    }
}
