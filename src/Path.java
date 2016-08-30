import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Path {
    Map<Integer, Integer> states = Collections.synchronizedMap(new HashMap<Integer, Integer>());
    Map<Integer, Integer> ways = Collections.synchronizedMap(new HashMap<Integer, Integer>());

    synchronized public void put(int hash, int parentHash, int way) {
        states.put(hash, parentHash);
        ways.put(hash, way);
    }

    synchronized public int getWay(int hash) {
        return ways.get(hash);
    }

    synchronized public int getParent(int hash) {
        return states.get(hash);
    }


}
