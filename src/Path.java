import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Path {
    Map<Long, Long> states = Collections.synchronizedMap(new HashMap<Long, Long>());
    Map<Long, Integer> ways = Collections.synchronizedMap(new HashMap<Long, Integer>());

    synchronized public void put(long hash, long parentHash, int way) {
        states.put(hash, parentHash);
        ways.put(hash, way);
    }

    synchronized public int getWay(long hash) {
        return ways.get(hash);
    }

    synchronized public long getParent(long hash) {
        return states.get(hash);
    }


}
